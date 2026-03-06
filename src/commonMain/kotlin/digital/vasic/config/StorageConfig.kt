package digital.vasic.config

import kotlinx.serialization.Serializable

/**
 * Sealed class representing different types of storage configurations.
 */
@Serializable
sealed class StorageConfig {
    abstract val name: String
    abstract val storageType: StorageType
    abstract val isEnabled: Boolean
    abstract val priority: Int
    abstract val metadata: Map<String, String>
    abstract fun withEnabled(isEnabled: Boolean): StorageConfig
    abstract fun withPriority(priority: Int): StorageConfig
    abstract fun withMetadata(metadata: Map<String, String>): StorageConfig

    @Serializable
    data class WebDavConfig(
        override val name: String,
        val url: String,
        val username: String,
        val password: String,
        val authenticationType: WebDavAuthenticationType = WebDavAuthenticationType.BASIC,
        val sslEnabled: Boolean = true,
        val verifyCertificate: Boolean = true,
        val connectionTimeout: Int = 30000,
        val readTimeout: Int = 60000,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.WEBDAV
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class FtpConfig(
        override val name: String,
        val host: String,
        val port: Int = 21,
        val username: String,
        val password: String,
        val rootPath: String = "/",
        val passiveMode: Boolean = true,
        val secureFtp: Boolean = false,
        val encoding: String = "UTF-8",
        val connectionTimeout: Int = 30000,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.FTP
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class SftpConfig(
        override val name: String,
        val host: String,
        val port: Int = 22,
        val username: String? = null,
        val password: String? = null,
        val privateKeyPath: String? = null,
        val privateKeyPassphrase: String? = null,
        val knownHostsPath: String? = null,
        val strictHostKeyChecking: Boolean = true,
        val rootPath: String = "/",
        val useSsl: Boolean = true,
        val connectionTimeout: Int = 30000,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.SFTP
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class SmbConfig(
        override val name: String,
        val host: String,
        val share: String,
        val domain: String? = null,
        val username: String,
        val password: String,
        val path: String = "/",
        val port: Int = 445,
        val encryption: Boolean = true,
        val signing: Boolean = true,
        val useSsl: Boolean = false,
        val connectionTimeout: Int = 30000,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.SMB
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class GoogleDriveConfig(
        override val name: String,
        val clientId: String,
        val clientSecret: String,
        val refreshToken: String? = null,
        val accessToken: String? = null,
        val rootFolderId: String? = null,
        val teamDriveId: String? = null,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.GOOGLE_DRIVE
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class DropboxConfig(
        override val name: String,
        val accessToken: String,
        val appKey: String,
        val appSecret: String,
        val refreshToken: String? = null,
        val rootPath: String = "",
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.DROPBOX
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class OneDriveConfig(
        override val name: String,
        val clientId: String,
        val clientSecret: String,
        val refreshToken: String? = null,
        val accessToken: String? = null,
        val driveType: OneDriveDriveType = OneDriveDriveType.ME,
        val driveId: String? = null,
        val rootFolderId: String? = null,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.ONEDRIVE
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }

    @Serializable
    data class GitConfig(
        override val name: String,
        val repositoryUrl: String,
        val branch: String = "main",
        val username: String? = null,
        val password: String? = null,
        val personalAccessToken: String? = null,
        val privateKeyPath: String? = null,
        val privateKeyPassphrase: String? = null,
        val localCachePath: String,
        val autoSync: Boolean = true,
        val commitAuthorName: String = "Yole",
        val commitAuthorEmail: String = "yole@example.com",
        val connectionTimeout: Int = 30000,
        override val isEnabled: Boolean = true,
        override val priority: Int = 100,
        override val metadata: Map<String, String> = emptyMap()
    ) : StorageConfig() {
        override val storageType = StorageType.GIT
        override fun withEnabled(isEnabled: Boolean) = copy(isEnabled = isEnabled)
        override fun withPriority(priority: Int) = copy(priority = priority)
        override fun withMetadata(metadata: Map<String, String>) = copy(metadata = metadata)
    }
}

@Serializable
enum class StorageType {
    WEBDAV, FTP, SFTP, SMB, GOOGLE_DRIVE, DROPBOX, ONEDRIVE, GIT;

    val displayName: String get() = when (this) {
        WEBDAV -> "WebDAV"; FTP -> "FTP"; SFTP -> "SFTP"; SMB -> "SMB/CIFS"
        GOOGLE_DRIVE -> "Google Drive"; DROPBOX -> "Dropbox"; ONEDRIVE -> "OneDrive"; GIT -> "Git"
    }

    val supportsFolders: Boolean get() = this != FTP

    val supportsEncryption: Boolean get() = this != FTP

    val defaultPort: Int get() = when (this) {
        WEBDAV -> 443; FTP -> 21; SFTP -> 22; SMB -> 445
        GOOGLE_DRIVE -> 443; DROPBOX -> 443; ONEDRIVE -> 443; GIT -> 22
    }
}

@Serializable
enum class WebDavAuthenticationType { BASIC, DIGEST, OAUTH, NONE }

@Serializable
enum class OneDriveDriveType { ME, BUSINESS, SHAREPOINT, GROUP }
