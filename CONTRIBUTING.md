# Contributing to Ultra-Fast Attribute Swap

Thank you for your interest in contributing to the Ultra-Fast Attribute Swap Minecraft mod! This document provides guidelines and instructions for contributing.

## 🚀 Quick Start

1. **Fork the repository** on GitHub
2. **Clone your fork** locally
3. **Create a feature branch** for your changes
4. **Make your changes** and test them
5. **Submit a pull request** to the main repository

## 💡 Types of Contributions

### 🐛 Bug Reports
- Use the GitHub Issues template
- Include detailed reproduction steps
- Provide Minecraft version and mod list
- Include relevant logs

### ✨ Feature Requests
- Use the Feature Request template
- Explain the use case and benefits
- Consider implementation complexity
- Check for duplicate requests

### 📝 Code Contributions
- Follow the existing code style
- Add comments for complex logic
- Update documentation as needed
- Test thoroughly before submitting

### 📚 Documentation
- Fix typos and improve clarity
- Add examples and tutorials
- Update outdated information
- Translate documentation

### 🧪 Testing
- Test on different Minecraft versions
- Test with various mod combinations
- Test in singleplayer and multiplayer
- Report bugs and issues found

## 🔧 Development Setup

### Prerequisites
- Java 21 JDK or higher
- Git
- IDE (IntelliJ IDEA recommended)
- Minecraft 1.21.5 with Fabric Loader

### Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/rithzer016-lang/ultra-fast-attribute-swap.git
   cd ultra-fast-attribute-swap
   ```

2. Import as Gradle project in your IDE

3. Generate sources:
   ```bash
   ./gradlew genSources
   ```

4. Build the mod:
   ```bash
   ./gradlew build
   ```

5. Copy JAR to Minecraft mods folder and test

## 📋 Code Style Guidelines

### Java Code
- Use 4 spaces for indentation (no tabs)
- Follow Java naming conventions
- Add Javadoc comments for public methods
- Keep methods short and focused

### Class Structure
```java
package com.rithzer.ultrafastattributeswap;

import statements;

/**
 * Class description with Javadoc
 */
public class ClassName {
    // Constants first
    private static final String CONSTANT = "value";
    
    // Fields next
    private int field;
    
    // Constructors
    public ClassName() {
        // Implementation
    }
    
    // Methods
    public void method() {
        // Implementation
    }
}
```

### Mixins
- Use clear and descriptive names
- Add comments explaining injection points
- Keep mixins focused on single responsibility

### Commit Messages
Follow conventional commit format:
```
type(scope): description

[optional body]

[optional footer]
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
 = `style`: Code style changes (formatting)
- `refactor`: Code refactoring
- `test`: Adding or fixing tests
- `chore`: Maintenance tasks

**Example:**
```
feat(AttributeSwapHandler): add Wind Burst enchantment priority

Adds automatic prioritization of maces with Wind Burst 
enchantment during weapon selection process.

Fixes #123
```

## 🧪 Testing Guidelines

### Manual Testing
1. **Basic Functionality**:
   - Test sword-to-mace swapping
   - Verify Wind Burst prioritization
   - Check return to original slot

2. **Edge Cases**:
   - No mace in hotbar
   - Multiple maces available
   - Different sword types
   - Server disconnection during swap

3. **Performance**:
   - Measure swap timing
   - Test with multiple enemies
   - Monitor for lag or desync

4. **Compatibility**:
   - Test with other mods
   - Test in multiplayer
   - Test on different platforms

### Automated Testing
- Unit tests for helper methods
- Integration tests for core functionality
- Performance benchmarks

## 🚦 Pull Request Process

### Before Submitting
1. **Test thoroughly**: Ensure your changes work correctly
2. **Update documentation**: Keep README and docs current
3. **Follow code style**: Match existing patterns
4. **Check for conflicts**: Rebase on latest main branch

### Pull Request Checklist
- [ ] Code follows style guidelines
- [ ] Documentation updated
- [ ] Tests added/updated
- [ ] CHANGELOG.md updated
- [ ] No merge conflicts
- [ ] Builds successfully
- [ ] Logs clean

### Review Process
1. Maintainers will review your PR
2. Address any feedback or requested changes
3. Once approved, your PR will be merged
4. Your contribution will be credited in CHANGELOG.md

## 🐛 Bug Fix Guidelines

### Investigation
1. **Reproduce the bug**: Confirm it exists
2. **Identify root cause**: Debug and trace the issue
3. **Check similar issues**: Avoid duplicate fixes
4. **Consider edge cases**: Ensure fix is comprehensive

### Fix Criteria
- Minimal code changes
- No performance regression
- Backward compatible
- Well documented

## ✨ Feature Development

### Proposal Process
1. **Open a feature request**: Discuss with community
2. **Get approval**: Wait for maintainer approval
3. **Implement feature**: Follow development guidelines
4. **Submit PR**: Include tests and documentation

### Feature Requirements
- Solves real use case
- Maintains performance
- Follows mod's design philosophy
- Includes proper documentation

## 📖 Documentation Contributions

### Types of Documentation
- **README.md**: Main project documentation
- **CHANGELOG.md**: Version history
- **BUILD_INSTRUCTIONS.md**: Build process
- **CONTRIBUTING.md**: This file
- **Code comments**: Inline documentation

### Writing Style
- Clear and concise language
- Use code examples when helpful
- Include images/screenshots for UI changes
- Keep information up to date

## 🏆 Recognition

### Contribution Types
- **Code**: Bug fixes, features, optimizations
- **Documentation**: Guides, tutorials, translations
- **Testing**: Bug reports, compatibility testing
- **Community**: Support, feedback, suggestions

### Recognition
- Contributors listed in CHANGELOG.md
- GitHub contributors graph
- Special thanks in README.md
- Contributor role in community discussions

## 🤝 Community Guidelines

### Code of Conduct
- Be respectful and inclusive
- Provide constructive feedback
- Help newcomers get started
- Maintain a positive environment

### Communication
- Use GitHub Issues for discussions
- Be patient with maintainers
- Provide detailed information
- Follow the issue templates

## ❓ Getting Help

### Resources
- **Documentation**: Check README and project docs
- **Issues**: Search existing issues for solutions
- **Discussions**: Use GitHub Discussions
- **Fabric Community**: Join Fabric Discord

### Support Channels
- GitHub Issues: Bug reports and feature requests
- GitHub Discussions: General questions and help
- Fabric Discord: Community support

## 📜 Legal Considerations

### License
This project is licensed under MIT. By contributing:
- You agree to license your contributions under the same license
- You confirm you have the right to contribute the code
- You grant permission to use your contributions

### Intellectual Property
- Respect third-party licenses
- Give credit for inspiration and code
- Don't include copyrighted material without permission

---

## 🎉 Thank You!

Your contributions make this project better for everyone. Whether you're fixing bugs, adding features, improving documentation, or just providing feedback, your help is greatly appreciated!

**Questions?** Don't hesitate to reach out through GitHub Issues or Discussions.

**Ready to contribute?** Check out the [good first issues](https://github.com/rithzer016-lang/ultra-fast-attribute-swap/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22) to get started!