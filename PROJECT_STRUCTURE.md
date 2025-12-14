# Project Structure

This document outlines the complete file structure of the Ultra-Fast Attribute Swap Minecraft mod.

## Directory Tree

```
ultra-fast-attribute-swap/
│
├── .gitignore                 # Git ignore rules for build files and IDE
├── BUILD_INSTRUCTIONS.md      # Detailed build and deployment guide
├── CHANGELOG.md              # Version history and release notes
├── LICENSE                   # MIT License
├── PROJECT_STRUCTURE.md      # This file - project structure documentation
├── README.md                 # Main documentation and user guide
│
├── build.gradle              # Gradle build configuration
├── gradle.properties         # Project properties and versions
├── settings.gradle           # Gradle settings
│
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── rithzer/
│       │           └── ultrafastattributeswap/
│       │               │
│       │               ├── UltraFastAttributeSwapClient.java    # Main mod entry point
│       │               ├── AttributeSwapHandler.java           # Core swapping logic
│       │               │
│       │               └── mixins/
│       │                   ├── MinecraftMixin.java             # Attack input capture
│       │                   ├── LocalPlayerMixin.java          # Player attack hooks
│       │                   └── ClientPacketListenerMixin.java  # Network sync
│       │
│       └── resources/
│           ├── fabric.mod.json                                # Mod metadata
│           ├── ultra-fast-attribute-swap.mixins.json          # Mixin config
│           └── assets/
│               └── ultra-fast-attribute-swap/
│                   └── icon.png                               # Mod icon
│
└── README.md (root)          # Main project documentation
```

## File Descriptions

### Root Directory Files

#### Documentation
- **README.md**: Complete user guide with features, installation, usage, and troubleshooting
- **BUILD_INSTRUCTIONS.md**: Step-by-step build process and setup guide
- **CHANGELOG.md**: Version history with all changes and releases
- **PROJECT_STRUCTURE.md**: This file showing complete project layout
- **LICENSE**: MIT License for open-source distribution

#### Configuration
- **.gitignore**: Excludes build artifacts, IDE files, and temporary files
- **build.gradle**: Main Gradle build script with dependencies
- **gradle.properties**: Project versions and Gradle settings
- **settings.gradle**: Gradle project configuration

### Source Code (`src/main/`)

#### Java Source (`src/main/java/com/rithzer/ultrafastattributeswap/`)

**Main Classes:**
- **UltraFastAttributeSwapClient.java**: 
  - Fabric client mod entry point
  - Initializes mod components
  - Registers tick event handlers

- **AttributeSwapHandler.java**: 
  - Core attribute swapping logic
  - Attack input detection and timing
  - Mace selection and priority system
  - Hotbar slot management
  - Public helper methods for mixins

**Mixin Classes (`src/main/java/com/rithzer/ultrafastattributeswap/mixins/`):**
- **MinecraftMixin.java**: 
  - Hooks into attack sequence startup
  - Captures attack input detection

- **LocalPlayerMixin.java**: 
  - Injects into player attack method
  - Ensures proper timing for swaps
  - Prevents desync during attacks

- **ClientPacketListenerMixin.java**: 
  - Handles network packets
  - Prevents server slot override during swaps
  - Maintains client-server synchronization

#### Resources (`src/main/resources/`)

**Configuration Files:**
- **fabric.mod.json**: 
  - Mod metadata and identification
  - Entry point definitions
  - Dependency specifications
  - Mixin configuration reference

- **ultra-fast-attribute-swap.mixins.json**: 
  - Mixin class mappings
  - Package configuration
  - Compatibility settings

**Assets (`src/main/resources/assets/ultra-fast-attribute-swap/`):**
- **icon.png**: Mod icon displayed in Minecraft mod list

## Key Features by File

### Core Functionality
- **UltraFastAttributeSwapClient**: Loads mod and registers events
- **AttributeSwapHandler**: Implements the swap timing and logic
- **Mixins**: Hook into Minecraft code for seamless integration

### Performance Guarantees
- **≤1 tick completion**: Achieved through precise timing in AttributeSwapHandler
- **Zero desync**: ClientPacketListenerMixin prevents network issues
- **Atomic operations**: LocalPlayerMixin ensures clean attacks

### Smart Selection
- **Wind Burst priority**: AttributeSwapHandler prioritizes enchanted maces
- **Damage comparison**: Secondary selection based on attack damage
- **Hotbar scanning**: Fastest possible mace location

## Build Output

After building, the following files are generated:

```
build/
├── libs/
│   ├── ultra-fast-attribute-swap-1.0.0.jar         # Main mod file
│   └── ultra-fast-attribute-swap-1.0.0-sources.jar # Source code
│
├── classes/           # Compiled Java classes
├── resources/         # Processed resource files
├── reports/          # Build reports and test results
└── tmp/              # Temporary build files
```

### Deployment File
The main JAR file (`ultra-fast-attribute-swap-1.0.0.jar`) should be placed in:
- Windows: `%APPDATA%\.minecraft\mods`
- Mac: `~/Library/Application Support/minecraft/mods`
- Linux: `~/.minecraft/mods`

## Code Architecture

### Event Flow
```
Attack Input → AttributeSwapHandler → Slot Change → 
Attack Execution → Slot Restoration
```

### Mixin Injection Points
```
MinecraftMixin → Attack start detection
LocalPlayerMixin → Attack method hooks
ClientPacketListenerMixin → Network packet handling
```

### Data Flow
```
Player Input → Input Detection → Slot Swap → 
Attack Processing → Slot Restore → Network Sync
```

## Dependencies

### Compile-Time
- **Minecraft 1.21.5**: Target game version
- **Fabric API**: Modding framework
- **Fabric Loader**: Mod loader
- **Parchment**: Mappings for development

### Runtime
- **Java 21**: Required JRE version
- **Fabric Loader 0.15.0+**: Minimum loader version

## Performance Characteristics

### Tick Performance
- **Latency**: <50ms (≤1 game tick at 20 TPS)
- **Memory**: Minimal footprint
- **CPU**: Low overhead
- **Network**: No additional bandwidth

### Scalability
- Single-player: Full functionality
- Multi-player: Fully compatible
- Server: Client-side only

## Development Tools

### Recommended IDEs
- IntelliJ IDEA (recommended)
- Eclipse
- VS Code

### Build Tools
- Gradle 8.x
- Java 21 JDK

### Testing
- Manual testing in Minecraft
- Debug logging available
- Mixin error detection

## Version Control

### Branch Strategy
- `main`: Stable releases
- `develop`: Development work
- Feature branches as needed

### Tags
- Semantic versioning: `v1.0.0`
- Release tags for each version

---

This structure ensures a clean, maintainable, and professional Minecraft mod project that follows Fabric modding best practices.