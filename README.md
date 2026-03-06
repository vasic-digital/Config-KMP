# Config-KMP

Kotlin Multiplatform storage configuration library with serializable config types for 8 storage protocols.

## Features

- **StorageConfig** - Sealed class hierarchy with 8 protocol-specific config variants
- **StorageType** - Enum with display names, default ports, capability flags
- **StorageModels** - StorageInfo, QuotaInfo, FileInfo data classes
- **Serialization** - All types are kotlinx-serialization compatible

## Platforms

- Android
- Desktop (JVM)
- iOS (x64, arm64, simulator)
- Web (Wasm/JS)

## Installation

Add as a git submodule:

```bash
git submodule add git@github.com:vasic-digital/Config-KMP.git
```

Then in your `settings.gradle.kts`:

```kotlin
includeBuild("Config-KMP")
```

## Usage

```kotlin
import digital.vasic.config.*

// WebDAV configuration
val webdav = StorageConfig.WebDavConfig(
    name = "My WebDAV",
    url = "https://dav.example.com",
    username = "user",
    password = "pass"
)

// Cloud storage configurations
val gdrive = StorageConfig.GoogleDriveConfig(
    name = "Google Drive",
    clientId = "id",
    clientSecret = "secret"
)

// Config operations
val disabled = webdav.withEnabled(false)
val highPriority = webdav.withPriority(1)
val tagged = webdav.withMetadata(mapOf("env" to "production"))

// StorageType utilities
val port = StorageType.WEBDAV.defaultPort     // 443
val name = StorageType.GOOGLE_DRIVE.displayName // "Google Drive"
val folders = StorageType.SFTP.supportsFolders  // true

// Storage models
val info = StorageInfo(id = "1", name = "test", storageType = StorageType.WEBDAV)
val quota = QuotaInfo(usedBytes = 100, totalBytes = 1000)
val file = FileInfo(name = "doc.txt", path = "/docs/doc.txt", size = 1024)
```

## Supported Protocols

| Protocol | Config Class | Default Port |
|----------|-------------|-------------|
| WebDAV | `WebDavConfig` | 443 |
| FTP | `FtpConfig` | 21 |
| SFTP | `SftpConfig` | 22 |
| SMB/CIFS | `SmbConfig` | 445 |
| Google Drive | `GoogleDriveConfig` | 443 |
| Dropbox | `DropboxConfig` | 443 |
| OneDrive | `OneDriveConfig` | 443 |
| Git | `GitConfig` | 22 |

## License

Apache-2.0
