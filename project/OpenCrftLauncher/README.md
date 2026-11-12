# OpenCrft Launcher — Android starter (v0.1.0)

This is the first **launcher shell** for OpenCrft. It is a real Android Studio/Gradle source project with a dark custom Compose UI and a GitHub Actions workflow that builds a debug APK.

## Important current limitations

This first build is **not yet capable of downloading or launching Minecraft**. The Play button is intentionally a placeholder. Microsoft/Ely.by authentication, offline profiles, skin/cape handling, renderer integration, Modrinth downloads, mod installation, and Java/game runtime integration are planned milestones, not implemented features.

A real Minecraft launcher needs a compatible and properly licensed game-launching/runtime implementation, version manifests, asset/library downloads, authentication, native ABI support, and renderer compatibility testing. We will add those incrementally and test each layer.

## Build from an Android tablet (cloud build)

You can use GitHub in your tablet browser; the APK is compiled on GitHub's cloud runner, not on the tablet.

1. Download and extract `OpenCrftLauncher-source.zip` on your tablet.
2. Sign in to GitHub and create a **new empty public or private repository** named `OpenCrftLauncher` (do not add a README, license, or gitignore).
3. Open the repository in GitHub Codespaces: tap **Code → Codespaces → Create codespace on main**. If Codespaces is unavailable on your account, use another Linux cloud IDE that supports Git and Java 17.
4. In the Codespaces terminal, upload the extracted project folder using the file explorer, or upload the ZIP and run:
   ```bash
   unzip OpenCrftLauncher-source.zip
   ```
   If the extracted folder is nested, `cd` into the folder that contains `settings.gradle.kts`.
5. Push the project:
   ```bash
   git add .
   git commit -m "Initial OpenCrft Android launcher"
   git branch -M main
   git push -u origin main
   ```
6. In GitHub, open the repository's **Actions** tab. The `Build OpenCrft APK` workflow should run automatically.
7. Open the completed workflow run, scroll to **Artifacts**, and download `OpenCrft-debug-apk`.
8. Extract the downloaded artifact ZIP. Install `app-debug.apk` on your Android tablet. Android may ask you to allow installation from that browser/files app.

You can also start a build manually from **Actions → Build OpenCrft APK → Run workflow**.

## Build locally (Android Studio on a computer)

Requires JDK 17, Android SDK platform 36, and Gradle 8.13:
```bash
gradle :app:assembleDebug
```
The APK will be at:
`app/build/outputs/apk/debug/app-debug.apk`

## Project structure

- `app/src/main/java/com/opencrft/launcher/MainActivity.kt` — Compose launcher shell
- `app/src/main/AndroidManifest.xml` — Android app manifest
- `.github/workflows/build-apk.yml` — cloud APK build workflow

## Next development milestones

1. Persistent settings and account/profile model.
2. Secure Microsoft authentication and Ely.by authentication (using their documented flows).
3. Minecraft version metadata and game file installation.
4. Runtime/Java management and actual game process launch.
5. Modrinth API browsing and safe mod/resource-pack/shader installation.
6. Renderer integrations and device/driver compatibility checks.
7. Crash log collection and diagnostics.

Do not paste account passwords or access tokens into source code or chat. Use official sign-in flows and secure token storage.
