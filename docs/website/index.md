# Config-KMP

Kotlin Multiplatform storage configuration library.

## About

Config-KMP provides serializable configuration types for 8 storage protocols: WebDAV, FTP, SFTP, SMB, Google Drive, Dropbox, OneDrive, and Git. All types are kotlinx-serialization compatible and work across Android, Desktop, iOS, and Web platforms.

## Quick Start

```kotlin
val config = StorageConfig.WebDavConfig(
    name = "My Storage",
    url = "https://dav.example.com",
    username = "user",
    password = "pass"
)

val json = Json.encodeToString(StorageConfig.serializer(), config)
```

## Links

- [GitHub](https://github.com/vasic-digital/Config-KMP)
- [GitLab](https://gitlab.com/vasic-digital/config-kmp)
- [User Guide](../user-guide.md)
- [API Reference](../api-reference.md)
- [Architecture](../architecture.md)
