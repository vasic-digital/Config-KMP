# Config-KMP Agent Guidelines

## Testing

Tests in `src/commonTest/`. Run with `./gradlew desktopTest`.

Test files:
- `StorageConfigTest.kt` - All config variants, StorageType, auth enums, storage models

## Rules

- Never remove or disable tests
- All changes must pass existing tests
- All config types must remain @Serializable
- withEnabled/withPriority/withMetadata must return new copies (immutability)
- StorageType enum entries must match the 8 protocol configs
