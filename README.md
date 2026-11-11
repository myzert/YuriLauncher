<div align="center">
  <img width="300" src="YuriLauncher/src/main/res/drawable/app_name_title.png" alt="Yuri Launcher">
  
  <p><strong>A Minecraft: Java Edition Launcher for Android</strong></p>

  <p>
    <a href="https://github.com/myzert/YuriLauncher"><img src="https://img.shields.io/badge/Author-myzert-blue?style=flat-square&logo=github" alt="Author"></a>
    <a href="https://www.gnu.org/licenses/gpl-3.0"><img src="https://img.shields.io/badge/License-GPL%20v3-green?style=flat-square" alt="License"></a>
    <a href="https://github.com/myzert/YuriLauncher/actions"><img src="https://img.shields.io/badge/Build-Android%20CI-brightgreen?style=flat-square&logo=github-actions" alt="Build"></a>
  </p>

  <p>
    <a href="/README.md">English</a> •
    <a href="/README_ZH_CN.md">简体中文</a> •
    <a href="/README-ZH_TW.md">繁體中文</a>
  </p>
</div>

---

## 📖 Overview

**Yuri Launcher** is a powerful Minecraft: Java Edition launcher for Android devices, developed and maintained by **[myzert](https://github.com/myzert)**.

> [!IMPORTANT]
> ### 🌟 Credits & Acknowledgements
> Yuri Launcher is built upon the wonderful foundation provided by:
> - **[ZalithLauncher](https://github.com/ZalithLauncher/ZalithLauncher)** — developed by **[MovTery](https://github.com/MovTery)**
> - **[PojavLauncher](https://github.com/PojavLauncherTeam/PojavLauncher)** — developed by **[PojavLauncherTeam](https://github.com/PojavLauncherTeam)**
> 
> Sincere gratitude and all original credits go to the creators, developers, and contributors of ZalithLauncher and PojavLauncher for their extraordinary open-source work!

---

## ✨ Features

- 🎨 **Modern & Fluid UI** — Redesigned layout with both light and dark theme support.
- 📁 **Integrated File Manager** — Browse and manage game directories without restrictive Android permissions issues.
- ⚡ **Enhanced Version Management** — Custom game directories, version isolation, and support for external storage.
- 🎮 **Multiple Renderers & Plugins** — Includes GL4ES, Vulkan Zink, VirGL, and renderer plugin compatibility.
- 📦 **In-Launcher Downloader** — Easily search and download Mods, Modpacks, Resource Packs, Saves, and Shaders.
- 🖱️ **Custom Virtual Controls** — Highly customizable on-screen controls, virtual mouse, and gamepad remapping.
- ☕ **Multi-Java Runtimes** — Pre-packaged with Java 8, 17, 21, and support for Java 25.

---

## 📸 Screenshots

| Light Mode | Dark Mode | In-Game Interface |
| :---: | :---: | :---: |
| ![Light](/.github/images/Screenshot_Launcher_Light_EN_US.jpg) | ![Dark](/.github/images/Screenshot_Launcher_Dark_EN_US.jpg) | ![Game](/.github/images/Screenshot_Game_EN_US.jpg) |

---

## 🛠️ Building From Source

### Prerequisites
- JDK 17+
- Android SDK (API 34) & NDK (25.2.9519653)

### Build Commands
```bash
# Clone the repository
git clone https://github.com/myzert/YuriLauncher.git
cd YuriLauncher

# Build JRE dependencies
./gradlew jre_lwjgl3glfw:build

# Assemble Debug APK
./gradlew YuriLauncher:assembleDebug

# Assemble Release APK (e.g. for arm64)
./gradlew YuriLauncher:assembleRelease -Darch=arm64
```

---

## 👥 Contributors & Credits

YuriLauncher is actively developed and maintained thanks to incredible open-source projects and contributors:

### 🌟 Core Contributors
| Contributor | Role & Contribution | Profile / Repository |
| :--- | :--- | :--- |
| **[myzert](https://github.com/myzert)** | Lead Creator, Rebranding & Maintainer | [![GitHub](https://img.shields.io/badge/GitHub-myzert-7B1FA2?style=flat&logo=github)](https://github.com/myzert) |
| **[MovTery](https://github.com/MovTery)** | Creator of ZalithLauncher, Original Launcher Architecture | [![GitHub](https://img.shields.io/badge/GitHub-MovTery-black?style=flat&logo=github)](https://github.com/MovTery) |
| **[PojavLauncherTeam](https://github.com/PojavLauncherTeam)** | PojavLauncher Core, Minecraft Java on Android engine | [![GitHub](https://img.shields.io/badge/GitHub-PojavLauncher-orange?style=flat&logo=github)](https://github.com/PojavLauncherTeam/PojavLauncher) |
| **[MobileGL-Dev](https://github.com/MobileGL-Dev)** | MobileGlues OpenGL ES translation layer | [![GitHub](https://img.shields.io/badge/GitHub-MobileGlues-purple?style=flat&logo=github)](https://github.com/MobileGL-Dev/MobileGlues-release) |
| **[FCL-Team](https://github.com/FCL-Team)** | FoldCraftLauncher renderer plugins & extensions | [![GitHub](https://img.shields.io/badge/GitHub-FCL--Team-green?style=flat&logo=github)](https://github.com/FCL-Team) |
| **[Vera-Firefly](https://space.bilibili.com/1412062866)** | UI/UX & Visual Contributions | [Bilibili Space](https://space.bilibili.com/1412062866) |
| **[ShirosakiMio](https://space.bilibili.com/35801833)** | Launcher Components & Assets | [Bilibili Space](https://space.bilibili.com/35801833) |

### 💖 Upstream Open Source Acknowledgements
- **[HMCL](https://github.com/HMCL-dev/HMCL)** - Hello Minecraft! Launcher
- **[Caciocavallo](https://github.com/PojavLauncherTeam/caciocavallo)** - AWT-bridge on Android
- **[LWJGL](https://www.lwjgl.org/)** - Lightweight Java Game Library

---

## 📜 License

Yuri Launcher is open-source software licensed under the **GNU General Public License v3.0 (GPL-3.0)**.
See the [LICENSE](LICENSE) file for more information.
