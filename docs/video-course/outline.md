# Config-KMP Video Course Outline

## Module 1: Introduction (5 min)
- What Config-KMP does
- Storage protocol overview
- When to use this library

## Module 2: Configuration Types (10 min)
- StorageConfig sealed class hierarchy
- Creating protocol-specific configs
- Common fields: name, isEnabled, priority, metadata

## Module 3: Protocol Details (15 min)
- Network protocols: WebDAV, FTP, SFTP, SMB
- Cloud protocols: Google Drive, Dropbox, OneDrive
- Version control: Git

## Module 4: Immutable Operations (5 min)
- withEnabled, withPriority, withMetadata
- Copy-based immutability pattern

## Module 5: Serialization (10 min)
- kotlinx-serialization integration
- JSON serialization of sealed class hierarchy
- Storing configs in files or databases

## Module 6: StorageType Enum (5 min)
- Display names, default ports
- Capability flags (folders, encryption)

## Module 7: Storage Models (5 min)
- StorageInfo for lightweight metadata
- QuotaInfo for storage quotas
- FileInfo for file/directory metadata

## Module 8: Integration (10 min)
- Adding Config-KMP to a KMP project
- Using configs with storage service layers
- Cross-platform considerations
