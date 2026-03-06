# Config-KMP Architecture

## Overview

Config-KMP is a pure data library providing serializable configuration types for 8 storage protocols. It has no runtime dependencies beyond kotlinx-serialization and targets all KMP platforms.

## Design Principles

1. **Pure Data** - No I/O, no networking, no coroutines. Config types are plain serializable data classes.
2. **Immutable Configs** - All modifications return new copies via `copy()`.
3. **Sealed Hierarchy** - `StorageConfig` sealed class ensures exhaustive `when` handling.
4. **Protocol-Specific** - Each protocol gets its own config class with protocol-relevant parameters.

## Type Hierarchy

```
StorageConfig (sealed)
├── WebDavConfig    → StorageType.WEBDAV
├── FtpConfig       → StorageType.FTP
├── SftpConfig      → StorageType.SFTP
├── SmbConfig       → StorageType.SMB
├── GoogleDriveConfig → StorageType.GOOGLE_DRIVE
├── DropboxConfig   → StorageType.DROPBOX
├── OneDriveConfig  → StorageType.ONEDRIVE
└── GitConfig       → StorageType.GIT
```

## Common Interface

All configs share:
- `name` - Display name
- `storageType` - Protocol enum
- `isEnabled` - Active flag
- `priority` - Ordering preference
- `metadata` - Custom key-value pairs
- `withEnabled()` / `withPriority()` / `withMetadata()` - Copy-based mutation

## Companion Types

- `StorageType` - Protocol enum with display names, ports, capability flags
- `WebDavAuthenticationType` - BASIC, DIGEST, OAUTH, NONE
- `OneDriveDriveType` - ME, BUSINESS, SHAREPOINT, GROUP
- `StorageInfo` - Lightweight storage metadata
- `QuotaInfo` - Storage quota information
- `FileInfo` - File/directory metadata
