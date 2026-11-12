package com.opencrft.launcher

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Forest = Color(0xFF10231B)
private val Panel = Color(0xFF1A3428)
private val Mint = Color(0xFF8BE0A8)
private val Muted = Color(0xFFB7CDBF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { OpenCrftApp() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenCrftApp() {
    var selectedTab by remember { mutableStateOf("Home") }
    var selectedVersion by remember { mutableStateOf("1.21.11") }
    var notice by remember { mutableStateOf("Welcome to OpenCrft. Game launching is coming in a later build.") }

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Mint,
            background = Forest,
            surface = Panel,
            onPrimary = Forest,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Forest,
            bottomBar = {
                NavigationBar(containerColor = Color(0xFF0B1913)) {
                    val tabs = listOf("Home", "Discover", "Instances", "Settings")
                    val icons = listOf(Icons.Default.Home, Icons.Default.Explore, Icons.Default.Layers, Icons.Default.Settings)
                    tabs.forEachIndexed { index, tab ->
                        NavigationBarItem(
                            selected = selectedTab == tab,
                            onClick = { selectedTab = tab },
                            icon = { Icon(icons[index], contentDescription = tab) },
                            label = { Text(tab) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Mint,
                                selectedTextColor = Mint,
                                indicatorColor = Panel,
                                unselectedIconColor = Muted,
                                unselectedTextColor = Muted
                            )
                        )
                    }
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = RoundedCornerShape(14.dp), color = Panel) {
                        Icon(Icons.Default.Android, null, tint = Mint, modifier = Modifier.padding(12.dp).size(28.dp))
                    }
                    Spacer(Modifier.width(12.dp))
                    Column {
                        Text("OPENCRFT", fontSize = 23.sp, fontWeight = FontWeight.Black, letterSpacing = 2.sp)
                        Text("MINECRAFT JAVA LAUNCHER", color = Muted, fontSize = 10.sp, letterSpacing = 1.3.sp)
                    }
                    Spacer(Modifier.weight(1f))
                    AssistChip(onClick = { notice = "Account management is planned. Microsoft and Ely.by sign-in will be added after the game-launch core." }, label = { Text("Offline") })
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            Brush.linearGradient(listOf(Color(0xFF315D40), Color(0xFF14291F), Color(0xFF263F31))),
                            RoundedCornerShape(24.dp)
                        )
                        .padding(20.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("YOUR WORLD.", color = Mint, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
                        Text("Your Minecraft.
Your way.", fontSize = 29.sp, fontWeight = FontWeight.ExtraBold, lineHeight = 32.sp)
                        Text("A performance-first launcher for Android", color = Muted, fontSize = 12.sp)
                    }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(20.dp)) {
                    Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text("Selected instance", color = Muted, fontSize = 12.sp)
                                Text("My Minecraft", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            }
                            Icon(Icons.Default.SportsEsports, contentDescription = null, tint = Mint, modifier = Modifier.size(30.dp))
                        }
                        var expanded by remember { mutableStateOf(false) }
                        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                            OutlinedTextField(
                                value = selectedVersion,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text("Game version") },
                                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                modifier = Modifier.menuAnchor().fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedTextColor = Color.White,
                                    unfocusedTextColor = Color.White,
                                    focusedBorderColor = Mint,
                                    unfocusedBorderColor = Muted,
                                    focusedLabelColor = Mint,
                                    unfocusedLabelColor = Muted
                                )
                            )
                            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                                listOf("1.21.11", "1.21.10", "1.21.8", "1.20.1").forEach { version ->
                                    DropdownMenuItem(text = { Text(version) }, onClick = {
                                        selectedVersion = version
                                        expanded = false
                                        notice = "Version selection is currently a UI preview; installation support is planned."
                                    })
                                }
                            }
                        }
                        Button(
                            onClick = { notice = "Not ready yet: this preview does not launch Minecraft. Next milestone: integrate a licensed, compatible game runtime and launcher." },
                            modifier = Modifier.fillMaxWidth().height(54.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Mint, contentColor = Forest),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(Icons.Default.PlayArrow, null)
                            Spacer(Modifier.width(8.dp))
                            Text("PLAY MINECRAFT", fontWeight = FontWeight.ExtraBold, letterSpacing = 1.sp)
                        }
                    }
                }

                Text("QUICK ACCESS", color = Mint, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.5.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    QuickTile("Mods", Icons.Default.Extension, Modifier.weight(1f)) { selectedTab = "Discover" }
                    QuickTile("Skins", Icons.Default.Face, Modifier.weight(1f)) { notice = "Skin and cape studio is planned." }
                    QuickTile("Renderers", Icons.Default.Tune, Modifier.weight(1f)) { notice = "Renderer profiles are planned; compatibility checks come first." }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF20372B))) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("PROJECT STATUS", color = Mint, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                        Text("Launcher shell preview • v0.1.0", fontWeight = FontWeight.SemiBold)
                        Text(notice, color = Muted, fontSize = 12.sp)
                    }
                }

                when (selectedTab) {
                    "Discover" -> InfoSection("Modrinth Discover", "Search and install mods, resource packs, shaders and modpacks from inside OpenCrft. API integration is a future milestone.")
                    "Instances" -> InfoSection("Game instances", "Separate profiles will keep versions, mods, settings and game files isolated.")
                    "Settings" -> InfoSection("Performance settings", "Planned: memory allocation, Java runtime selection, resolution, renderer profiles and diagnostics.")
                }
                Text("OpenCrft is an independent community project concept and is not affiliated with Mojang or Microsoft.", color = Muted, fontSize = 10.sp)
            }
        }
    }
}

@Composable
private fun QuickTile(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = modifier, colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.fillMaxWidth().padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Icon(icon, null, tint = Mint, modifier = Modifier.size(24.dp))
            Text(title, fontSize = 11.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun InfoSection(title: String, description: String) {
    Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Text(title, color = Mint, fontWeight = FontWeight.Bold)
            Text(description, color = Muted, fontSize = 13.sp)
        }
    }
}
