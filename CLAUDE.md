# Config-KMP

## Project Overview

Kotlin Multiplatform storage configuration library. Package: `digital.vasic.config`.

## Build Commands

```bash
./gradlew desktopTest    # Run tests
./gradlew build          # Build all targets
```

## Architecture

- `StorageConfig.kt` - Sealed class with 8 config variants (WebDav, FTP, SFTP, SMB, GoogleDrive, Dropbox, OneDrive, Git), StorageType enum, auth type enums
- `StorageModels.kt` - StorageInfo, QuotaInfo, FileInfo data classes

## Key Patterns

- All config types are `@Serializable` (kotlinx-serialization)
- `withEnabled()`, `withPriority()`, `withMetadata()` return new copies (immutable)
- StorageType enum provides displayName, defaultPort, supportsFolders, supportsEncryption
- No coroutines dependency — pure data types only

## Dependencies

- kotlinx-serialization-json (serialization of config types)
