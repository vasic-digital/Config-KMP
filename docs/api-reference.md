# Config-KMP API Reference

## StorageConfig (sealed class)

Abstract properties:
- `name: String` - Configuration display name
- `storageType: StorageType` - Protocol type
- `isEnabled: Boolean` - Whether config is active
- `priority: Int` - Priority ordering (lower = higher priority)
- `metadata: Map<String, String>` - Custom key-value metadata

Abstract methods:
- `withEnabled(isEnabled: Boolean): StorageConfig`
- `withPriority(priority: Int): StorageConfig`
- `withMetadata(metadata: Map<String, String>): StorageConfig`

### WebDavConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `url` | `String` | required |
| `username` | `String` | required |
| `password` | `String` | required |
| `authenticationType` | `WebDavAuthenticationType` | `BASIC` |
| `sslEnabled` | `Boolean` | `true` |
| `verifyCertificate` | `Boolean` | `true` |
| `connectionTimeout` | `Int` | `30000` |
| `readTimeout` | `Int` | `60000` |

### FtpConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `host` | `String` | required |
| `port` | `Int` | `21` |
| `username` | `String` | required |
| `password` | `String` | required |
| `rootPath` | `String` | `"/"` |
| `passiveMode` | `Boolean` | `true` |
| `secureFtp` | `Boolean` | `false` |
| `encoding` | `String` | `"UTF-8"` |
| `connectionTimeout` | `Int` | `30000` |

### SftpConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `host` | `String` | required |
| `port` | `Int` | `22` |
| `username` | `String?` | `null` |
| `password` | `String?` | `null` |
| `privateKeyPath` | `String?` | `null` |
| `privateKeyPassphrase` | `String?` | `null` |
| `knownHostsPath` | `String?` | `null` |
| `strictHostKeyChecking` | `Boolean` | `true` |
| `rootPath` | `String` | `"/"` |
| `useSsl` | `Boolean` | `true` |
| `connectionTimeout` | `Int` | `30000` |

### SmbConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `host` | `String` | required |
| `share` | `String` | required |
| `domain` | `String?` | `null` |
| `username` | `String` | required |
| `password` | `String` | required |
| `path` | `String` | `"/"` |
| `port` | `Int` | `445` |
| `encryption` | `Boolean` | `true` |
| `signing` | `Boolean` | `true` |
| `useSsl` | `Boolean` | `false` |
| `connectionTimeout` | `Int` | `30000` |

### GoogleDriveConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `clientId` | `String` | required |
| `clientSecret` | `String` | required |
| `refreshToken` | `String?` | `null` |
| `accessToken` | `String?` | `null` |
| `rootFolderId` | `String?` | `null` |
| `teamDriveId` | `String?` | `null` |

### DropboxConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `accessToken` | `String` | required |
| `appKey` | `String` | required |
| `appSecret` | `String` | required |
| `refreshToken` | `String?` | `null` |
| `rootPath` | `String` | `""` |

### OneDriveConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `clientId` | `String` | required |
| `clientSecret` | `String` | required |
| `refreshToken` | `String?` | `null` |
| `accessToken` | `String?` | `null` |
| `driveType` | `OneDriveDriveType` | `ME` |
| `driveId` | `String?` | `null` |
| `rootFolderId` | `String?` | `null` |

### GitConfig

| Parameter | Type | Default |
|-----------|------|---------|
| `repositoryUrl` | `String` | required |
| `branch` | `String` | `"main"` |
| `username` | `String?` | `null` |
| `password` | `String?` | `null` |
| `personalAccessToken` | `String?` | `null` |
| `privateKeyPath` | `String?` | `null` |
| `privateKeyPassphrase` | `String?` | `null` |
| `localCachePath` | `String` | required |
| `autoSync` | `Boolean` | `true` |
| `commitAuthorName` | `String` | `"Yole"` |
| `commitAuthorEmail` | `String` | `"yole@example.com"` |
| `connectionTimeout` | `Int` | `30000` |

## StorageType (enum)

Entries: `WEBDAV`, `FTP`, `SFTP`, `SMB`, `GOOGLE_DRIVE`, `DROPBOX`, `ONEDRIVE`, `GIT`

Properties:
- `displayName: String` - Human-readable protocol name
- `supportsFolders: Boolean` - `false` only for FTP
- `supportsEncryption: Boolean` - `false` only for FTP
- `defaultPort: Int` - Protocol default port

## WebDavAuthenticationType (enum)

Entries: `BASIC`, `DIGEST`, `OAUTH`, `NONE`

## OneDriveDriveType (enum)

Entries: `ME`, `BUSINESS`, `SHAREPOINT`, `GROUP`

## StorageInfo (data class)

| Property | Type | Default |
|----------|------|---------|
| `id` | `String` | required |
| `name` | `String` | required |
| `storageType` | `StorageType` | required |
| `baseUrl` | `String?` | `null` |
| `isConnected` | `Boolean` | `false` |
| `lastSync` | `String?` | `null` |

## QuotaInfo (data class)

| Property | Type | Default |
|----------|------|---------|
| `usedBytes` | `Long` | `0L` |
| `totalBytes` | `Long` | `0L` |
| `availableBytes` | `Long` | `0L` |
| `usedPercentage` | `Double` | `0.0` |

## FileInfo (data class)

| Property | Type | Default |
|----------|------|---------|
| `name` | `String` | required |
| `path` | `String` | required |
| `size` | `Long` | required |
| `isDirectory` | `Boolean` | `false` |
| `lastModified` | `String?` | `null` |
