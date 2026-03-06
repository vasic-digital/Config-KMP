# Config-KMP User Guide

## Overview

Config-KMP provides serializable configuration types for 8 storage protocols. Use it to define, store, and transmit storage configurations across Kotlin Multiplatform targets.

## Getting Started

### Add Dependency

```bash
git submodule add git@github.com:vasic-digital/Config-KMP.git
```

```kotlin
// settings.gradle.kts
includeBuild("Config-KMP")
```

### Create Configurations

Each protocol has a dedicated config class with protocol-specific parameters plus common fields (name, isEnabled, priority, metadata).

```kotlin
// Network protocols
val webdav = StorageConfig.WebDavConfig(name = "Work", url = "https://dav.work.com", username = "me", password = "secret")
val ftp = StorageConfig.FtpConfig(name = "FTP", host = "ftp.example.com", username = "user", password = "pass")
val sftp = StorageConfig.SftpConfig(name = "SFTP", host = "sftp.example.com")
val smb = StorageConfig.SmbConfig(name = "NAS", host = "nas.local", share = "docs", username = "user", password = "pass")

// Cloud protocols
val gdrive = StorageConfig.GoogleDriveConfig(name = "GDrive", clientId = "id", clientSecret = "secret")
val dropbox = StorageConfig.DropboxConfig(name = "Dropbox", accessToken = "token", appKey = "key", appSecret = "secret")
val onedrive = StorageConfig.OneDriveConfig(name = "OneDrive", clientId = "id", clientSecret = "secret")
val git = StorageConfig.GitConfig(name = "GitHub", repositoryUrl = "https://github.com/user/repo.git", localCachePath = "/tmp/cache")
```

### Modify Configurations

All configs are immutable data classes. Use `with*` methods to create modified copies:

```kotlin
val disabled = webdav.withEnabled(false)
val highPriority = webdav.withPriority(1)
val tagged = webdav.withMetadata(mapOf("env" to "prod"))
```

### Serialize/Deserialize

```kotlin
val json = Json.encodeToString(StorageConfig.serializer(), webdav)
val restored = Json.decodeFromString(StorageConfig.serializer(), json)
```

## StorageType Enum

Provides protocol metadata:

| Property | Description |
|----------|-------------|
| `displayName` | Human-readable name ("Google Drive") |
| `defaultPort` | Default port (443, 21, 22, 445) |
| `supportsFolders` | Whether protocol supports folder operations |
| `supportsEncryption` | Whether protocol supports encryption |

## Storage Models

- `StorageInfo` - Lightweight storage metadata (id, name, type, connection status)
- `QuotaInfo` - Storage quota information (used/total/available bytes, percentage)
- `FileInfo` - File metadata (name, path, size, isDirectory, lastModified)
