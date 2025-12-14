# Build Instructions

This guide will help you build the Ultra-Fast Attribute Swap mod from source.

## Prerequisites

- **Java 21 or higher** (JDK)
- **Git** (for cloning the repository)
- **Internet connection** (for downloading dependencies)

## Quick Build

### Step 1: Clone Repository
```bash
git clone https://github.com/rithzer016-lang/ultra-fast-attribute-swap.git
cd ultra-fast-attribute-swap
```

### Step 2: Build with Gradle
```bash
# Linux/Mac
./gradlew build

# Windows
gradlew.bat build
```

### Step 3: Find the Mod JAR
After successful build, find the JAR file at:
```
build/libs/ultra-fast-attribute-swap-1.0.0.jar
```

## Detailed Build Process

### Setup Development Environment

1. **Install Java 21**
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
   - Verify installation: `java -version` (should show version 21+)

2. **Clone the Repository**
   ```bash
   git clone https://github.com/rithzer016-lang/ultra-fast-attribute-swap.git
   cd ultra-fast-attribute-swap
   ```

3. **Generate IDE Sources** (Optional)
   ```bash
   # For IntelliJ IDEA
   ./gradlew genSources
   
   # For Eclipse
   ./gradlew eclipse
   ```

### Build Commands

#### Clean Build
```bash
./gradlew clean build
```

#### Build Without Tests (Faster)
```bash
./gradlew build -x test
```

#### Build and Refresh Dependencies
```bash
./gradlew clean build --refresh-dependencies
```

#### Create Distribution
```bash
./gradlew distZip
```

### Build Output

After successful build:
- **Main JAR**: `build/libs/ultra-fast-attribute-swap-1.0.0.jar`
- **Sources JAR**: `build/libs/ultra-fast-attribute-swap-1.0.0-sources.jar`
- **Build Reports**: `build/reports/`

### Troubleshooting Build Issues

#### Issue: Gradle Not Found
**Solution**: Ensure you're using the Gradle wrapper:
```bash
./gradlew build  # Linux/Mac
gradlew.bat build  # Windows
```

#### Issue: Java Version Error
**Solution**: Set JAVA_HOME to Java 21:
```bash
export JAVA_HOME=/path/to/jdk-21
```

#### Issue: Dependencies Download Fails
**Solution**: Retry with refreshed dependencies:
```bash
./gradlew build --refresh-dependencies
```

#### Issue: Out of Memory
**Solution**: Increase Gradle memory in `gradle.properties`:
```properties
org.gradle.jvmargs=-Xmx2G
```

## Development Workflow

### 1. Make Code Changes
Edit source files in `src/main/java/`

### 2. Rebuild Incrementally
```bash
./gradlew build
```

### 3. Test in Minecraft
1. Copy JAR to Minecraft mods folder
2. Launch Minecraft with Fabric Loader
3. Test the mod functionality

### 4. Debug Issues
- Check logs: `.minecraft/logs/latest.log`
- Enable debug logging in mod
- Use breakpoints in IDE

## IDE Setup

### IntelliJ IDEA
1. Open project
2. Import as Gradle project
3. Run `./gradlew genSources`
4. Set JDK to version 21
5. Create run configuration for Minecraft client

### Eclipse
1. Import as existing Gradle project
2. Run `./gradlew eclipse`
3. Set JDK to version 21
4. Refresh project

### Visual Studio Code
1. Install Java Extension Pack
2. Open project folder
3. Install Gradle Extension
4. Set Java version to 21

## Advanced Build Options

### Build for Different Minecraft Versions
Edit `gradle.properties`:
```properties
minecraft_version=1.21.5
```

### Create Custom Build Variants
```bash
# Development build
./gradlew build -Penv=dev

# Production build
./gradlew build -Penv=prod
```

### Optimize Build Performance
In `gradle.properties`:
```properties
org.gradle.parallel=true
org.gradle.caching=true
org.gradle.jvmargs=-Xmx4G
```

## Deployment

### Release Build
```bash
./gradlew clean build
```

### Signing Build (Optional)
```bash
./gradlew build -Pkeystore=<path> -PkeyAlias=<alias>
```

## CI/CD Integration

### GitHub Actions Example
```yaml
name: Build
on: [push, pull_request]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v2
        with:
          java-version: '21'
          distribution: 'temurin'
      - name: Grant execute permission
        run: chmod +x gradlew
      - name: Build
        run: ./gradlew build
```

## Post-Build

### Testing Installation
1. Locate Minecraft mods folder:
   - Windows: `%APPDATA%\.minecraft\mods`
   - Mac: `~/Library/Application Support/minecraft/mods`
   - Linux: `~/.minecraft/mods`

2. Copy the built JAR file to the mods folder

3. Launch Minecraft and verify mod appears in mod list

## Support

For build issues:
1. Check error logs in `build/reports/`
2. Verify Java version: `java -version`
3. Try clean build: `./gradlew clean build`
4. Check project issues on GitHub

## Additional Resources

- [Fabric Wiki](https://fabricmc.net/wiki/start)
- [Gradle Documentation](https://docs.gradle.org/)
- [Minecraft Modding Tutorials](https://fabricmc.net/wiki/tutorials)

---

**Note**: This mod requires Minecraft 1.21.5 and Fabric Loader 0.15.0+ to run properly.