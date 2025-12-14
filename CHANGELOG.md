# Changelog

All notable changes to the Ultra-Fast Attribute Swap mod will be documented in this file.

## [1.0.0] - 2025-12-14

### Added
- **Ultra-fast attribute swapping** - Complete weapon swap within ≤1 game tick
- **Wind Burst enchantment priority** - Automatically prioritizes maces with Wind Burst
- **Seamless hotbar switching** - Uses legitimate slot changes for zero visual glitches
- **Real input handling** - Monitors actual attack inputs for proper timing
- **Network desync prevention** - ClientPacketListenerMixin prevents synchronization issues
- **Attack detection** - Precise timing for sword-to-mace transitions
- **Smart mace selection** - Chooses best mace based on enchantments and damage
- **Tick-completion guarantee** - All operations complete within single game tick

### Technical Features
- **AttributeSwapHandler** - Core logic for timing and execution
- **LocalPlayerMixin** - Hooks into player attack sequences
- **MinecraftMixin** - Captures attack input detection
- **ClientPacketListenerMixin** - Maintains network synchronization
- **Fabric API integration** - Compatible with Fabric ecosystem

### Files Created
- Complete mod structure with proper package organization
- Mixin configuration for client-side operation
- Gradle build scripts for compilation
- Comprehensive documentation (README, LICENSE, CHANGELOG)
- Proper resource structure (assets, icons)
- Developer-friendly setup (gitignore, settings)

### Supported Items
- **Swords**: Wooden, Iron, Diamond, Netherite, Golden
- **Maces**: Any mace with priority given to Wind Burst enchantment

### Performance
- Ultra-low latency (<50ms at 20 TPS)
- Zero tick delay between operations
- No additional network bandwidth usage
- Client-side only operation

### Initial Release Notes
This is the initial release of the Ultra-Fast Attribute Swap mod. The mod is fully functional and ready for use in Minecraft 1.21.5 with Fabric Loader.

## [Planned] - Future Versions

### Potential Features
- Configuration file support
- Custom keybindings
- Visual feedback options
- Expanded weapon compatibility
- Server-side support
- Performance optimization settings