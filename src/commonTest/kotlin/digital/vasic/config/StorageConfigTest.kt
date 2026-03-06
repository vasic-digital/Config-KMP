package digital.vasic.config

import kotlin.test.*

class StorageConfigWebDavTest {
    @Test fun testWebDavConfigCreation() {
        val config = StorageConfig.WebDavConfig(name = "My WebDAV", url = "https://dav.example.com", username = "user", password = "pass")
        assertEquals("My WebDAV", config.name)
        assertEquals("https://dav.example.com", config.url)
        assertEquals(StorageType.WEBDAV, config.storageType)
        assertTrue(config.isEnabled)
        assertEquals(100, config.priority)
    }
    @Test fun testWebDavConfigDefaults() {
        val config = StorageConfig.WebDavConfig(name = "Test", url = "https://test.com", username = "u", password = "p")
        assertEquals(WebDavAuthenticationType.BASIC, config.authenticationType)
        assertTrue(config.sslEnabled)
        assertTrue(config.verifyCertificate)
        assertEquals(30000, config.connectionTimeout)
        assertEquals(60000, config.readTimeout)
    }
    @Test fun testWebDavWithEnabled() {
        val config = StorageConfig.WebDavConfig(name = "Test", url = "https://test.com", username = "u", password = "p")
        assertFalse(config.withEnabled(false).isEnabled)
    }
    @Test fun testWebDavWithPriority() {
        val config = StorageConfig.WebDavConfig(name = "Test", url = "https://test.com", username = "u", password = "p")
        assertEquals(50, config.withPriority(50).priority)
    }
    @Test fun testWebDavWithMetadata() {
        val config = StorageConfig.WebDavConfig(name = "Test", url = "https://test.com", username = "u", password = "p")
        assertEquals("value", (config.withMetadata(mapOf("key" to "value")) as StorageConfig.WebDavConfig).metadata["key"])
    }
}

class StorageConfigFtpTest {
    @Test fun testFtpConfigCreation() {
        val config = StorageConfig.FtpConfig(name = "My FTP", host = "ftp.example.com", username = "user", password = "pass")
        assertEquals(StorageType.FTP, config.storageType)
        assertEquals(21, config.port)
        assertEquals("/", config.rootPath)
        assertTrue(config.passiveMode)
        assertFalse(config.secureFtp)
    }
    @Test fun testFtpWithEnabled() {
        val config = StorageConfig.FtpConfig(name = "Test", host = "ftp.test.com", username = "u", password = "p")
        assertFalse(config.withEnabled(false).isEnabled)
    }
}

class StorageConfigSftpTest {
    @Test fun testSftpConfigCreation() {
        val config = StorageConfig.SftpConfig(name = "My SFTP", host = "sftp.example.com")
        assertEquals(StorageType.SFTP, config.storageType)
        assertEquals(22, config.port)
        assertNull(config.username)
        assertNull(config.password)
        assertTrue(config.strictHostKeyChecking)
    }
    @Test fun testSftpConfigWithKeyAuth() {
        val config = StorageConfig.SftpConfig(name = "Key SFTP", host = "sftp.example.com", privateKeyPath = "/home/user/.ssh/id_rsa", privateKeyPassphrase = "secret")
        assertEquals("/home/user/.ssh/id_rsa", config.privateKeyPath)
        assertEquals("secret", config.privateKeyPassphrase)
    }
}

class StorageConfigSmbTest {
    @Test fun testSmbConfigCreation() {
        val config = StorageConfig.SmbConfig(name = "My SMB", host = "smb.example.com", share = "documents", username = "user", password = "pass")
        assertEquals(StorageType.SMB, config.storageType)
        assertEquals(445, config.port)
        assertEquals("documents", config.share)
        assertNull(config.domain)
        assertTrue(config.encryption)
    }
}

class StorageConfigCloudTest {
    @Test fun testGoogleDriveConfig() {
        val config = StorageConfig.GoogleDriveConfig(name = "GDrive", clientId = "id123", clientSecret = "secret456")
        assertEquals(StorageType.GOOGLE_DRIVE, config.storageType)
        assertNull(config.refreshToken)
        assertNull(config.rootFolderId)
    }
    @Test fun testDropboxConfig() {
        val config = StorageConfig.DropboxConfig(name = "Dropbox", accessToken = "token", appKey = "key", appSecret = "secret")
        assertEquals(StorageType.DROPBOX, config.storageType)
        assertEquals("", config.rootPath)
    }
    @Test fun testOneDriveConfig() {
        val config = StorageConfig.OneDriveConfig(name = "OneDrive", clientId = "id", clientSecret = "secret")
        assertEquals(StorageType.ONEDRIVE, config.storageType)
        assertEquals(OneDriveDriveType.ME, config.driveType)
    }
    @Test fun testOneDriveBusinessConfig() {
        val config = StorageConfig.OneDriveConfig(name = "OneDrive Business", clientId = "id", clientSecret = "secret", driveType = OneDriveDriveType.BUSINESS)
        assertEquals(OneDriveDriveType.BUSINESS, config.driveType)
    }
    @Test fun testGitConfig() {
        val config = StorageConfig.GitConfig(name = "GitHub", repositoryUrl = "https://github.com/user/repo.git", localCachePath = "/tmp/cache")
        assertEquals(StorageType.GIT, config.storageType)
        assertEquals("main", config.branch)
        assertTrue(config.autoSync)
    }
    @Test fun testGitConfigWithPat() {
        val config = StorageConfig.GitConfig(name = "GitHub", repositoryUrl = "https://github.com/user/repo.git", personalAccessToken = "ghp_xxx", localCachePath = "/tmp/cache")
        assertEquals("ghp_xxx", config.personalAccessToken)
    }
}

class StorageTypeTest {
    @Test fun testDisplayNames() {
        assertEquals("WebDAV", StorageType.WEBDAV.displayName)
        assertEquals("FTP", StorageType.FTP.displayName)
        assertEquals("SFTP", StorageType.SFTP.displayName)
        assertEquals("SMB/CIFS", StorageType.SMB.displayName)
        assertEquals("Google Drive", StorageType.GOOGLE_DRIVE.displayName)
        assertEquals("Dropbox", StorageType.DROPBOX.displayName)
        assertEquals("OneDrive", StorageType.ONEDRIVE.displayName)
        assertEquals("Git", StorageType.GIT.displayName)
    }
    @Test fun testSupportsFolders() {
        assertTrue(StorageType.WEBDAV.supportsFolders)
        assertFalse(StorageType.FTP.supportsFolders)
        assertTrue(StorageType.SFTP.supportsFolders)
    }
    @Test fun testSupportsEncryption() {
        assertTrue(StorageType.WEBDAV.supportsEncryption)
        assertFalse(StorageType.FTP.supportsEncryption)
    }
    @Test fun testDefaultPorts() {
        assertEquals(443, StorageType.WEBDAV.defaultPort)
        assertEquals(21, StorageType.FTP.defaultPort)
        assertEquals(22, StorageType.SFTP.defaultPort)
        assertEquals(445, StorageType.SMB.defaultPort)
        assertEquals(22, StorageType.GIT.defaultPort)
    }
    @Test fun testAllStorageTypesExist() {
        assertEquals(8, StorageType.entries.size)
    }
}

class WebDavAuthenticationTypeTest {
    @Test fun testAllAuthTypes() {
        assertEquals(4, WebDavAuthenticationType.entries.size)
    }
}

class OneDriveDriveTypeTest {
    @Test fun testAllDriveTypes() {
        assertEquals(4, OneDriveDriveType.entries.size)
    }
}

class StorageModelsTest {
    @Test fun testStorageInfo() {
        val info = StorageInfo(id = "1", name = "test", storageType = StorageType.WEBDAV)
        assertEquals("1", info.id)
        assertNull(info.baseUrl)
        assertFalse(info.isConnected)
    }
    @Test fun testQuotaInfo() {
        val quota = QuotaInfo(usedBytes = 100, totalBytes = 1000, availableBytes = 900, usedPercentage = 10.0)
        assertEquals(100L, quota.usedBytes)
        assertEquals(10.0, quota.usedPercentage)
    }
    @Test fun testFileInfo() {
        val file = FileInfo(name = "test.txt", path = "/docs/test.txt", size = 1024)
        assertEquals("test.txt", file.name)
        assertFalse(file.isDirectory)
        assertNull(file.lastModified)
    }
    @Test fun testFileInfoDirectory() {
        val dir = FileInfo(name = "docs", path = "/docs", size = 0, isDirectory = true)
        assertTrue(dir.isDirectory)
    }
}
