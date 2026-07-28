package com.anish.asyncApiCall;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HexFormat;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES encryption/decryption utility.
 * Java 25 implementation with no external dependencies.
 * @author ANISH KUMAR
 */
public final class NewAESUtils {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String KEY_DERIVATION_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int DEFAULT_ITERATION_COUNT = 100_000;
    private static final int DEFAULT_KEY_SIZE = 256;
    private static final String DEFAULT_IV = "554495832998beb0b0f7c198c46fbaba";
    private static final String DEFAULT_SALT = "3ce4c1fbd9f92863c8bb29a8b293188f";
    private static final String DEFAULT_PASSPHRASE = "1f491ea331dafff3bd8c38e3c3830e97";
    private static final String AES_KEY = "b914741dca31bfae4c2d4d003780b00e";
    private static final HexFormat HEX_FORMAT = HexFormat.of();
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private final int iterationCount;
    private final int keySize;

    public NewAESUtils() {
        this(DEFAULT_KEY_SIZE, DEFAULT_ITERATION_COUNT);
    }
    public NewAESUtils(int keySize, int iterationCount) {
        this.keySize = keySize;
        this.iterationCount = iterationCount;
    }

    // Encryption
    public String encrypt(String plaintext) {
        return encrypt(AES_KEY, DEFAULT_IV, plaintext);
    }

    public String encrypt(String key, String plaintext) {
        return encrypt(key, DEFAULT_IV, plaintext);
    }

    public String encrypt(String key, String iv, String plaintext) {
        SecretKey secretKey = generateKey(key);
        byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, secretKey, iv, plaintext.getBytes(StandardCharsets.UTF_8));
        return base64Encode(encrypted);
    }

    public String encrypt(String salt, String iv, String passphrase, String plaintext) {
        SecretKey secretKey = generateKeyFromPassphrase(salt, passphrase);
        byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, secretKey, iv, plaintext.getBytes(StandardCharsets.UTF_8));
        return base64Encode(encrypted);
    }
    // Decryption
    public String decrypt(String ciphertext) {
        return decrypt(DEFAULT_SALT, DEFAULT_IV, DEFAULT_PASSPHRASE, ciphertext);
    }

    public String decrypt(String key, String ciphertext) {
        return decrypt(key, DEFAULT_IV, ciphertext);
    }

    public String decrypt(String key, String iv, String ciphertext) {
        SecretKey secretKey = generateKey(key);
        byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, secretKey, iv, base64Decode(ciphertext));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
        SecretKey secretKey = generateKeyFromPassphrase(salt, passphrase);
        byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, secretKey, iv, base64Decode(ciphertext));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    // AES
    private byte[] doFinal(int cipherMode, SecretKey key, String iv, byte[] data) {

        try {
            Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
            byte[] ivBytes = hexDecode(iv);
            validateIv(ivBytes);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);
            cipher.init(cipherMode, key, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("AES encryption/decryption failed", e
            );
        }
    }

    // AES Key
    public static SecretKey generateKey(String hexKey) {
        byte[] keyBytes = hexDecode(hexKey);
        validateAesKey(keyBytes);
        return new SecretKeySpec(keyBytes, AES_ALGORITHM);
    }

    // PBKDF2 Key
    private SecretKey generateKeyFromPassphrase(String salt, String passphrase) {
        char[] password = passphrase.toCharArray();
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_DERIVATION_ALGORITHM);
            KeySpec specification =
                    new PBEKeySpec(password, hexDecode(salt), iterationCount, keySize);
            byte[] generatedKey = factory.generateSecret(specification).getEncoded();
            return new SecretKeySpec(generatedKey, AES_ALGORITHM);
        } catch (GeneralSecurityException e) {
            throw new IllegalStateException("Unable to generate AES key", e);
        } finally {
            // Avoid keeping password characters around longer than necessary.
            java.util.Arrays.fill(password, '\0');
        }
    }

    public String generateHexKey(String salt, String passphrase) {
        SecretKey key = generateKeyFromPassphrase(salt, passphrase);
        return hexEncode(key.getEncoded());
    }

    // Random generation
    public static String randomHex(int byteLength) {
        if (byteLength <= 0) {
            throw new IllegalArgumentException("Length must be greater than zero");
        }
        byte[] bytes = new byte[byteLength];
        SECURE_RANDOM.nextBytes(bytes);
        return hexEncode(bytes);
    }

    // Base64
    public static String base64Encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] base64Decode(String value) {
        return Base64.getDecoder().decode(value);
    }

    // Hex
    public static String hexEncode(byte[] data) {
        return HEX_FORMAT.formatHex(data);
    }

    public static byte[] hexDecode(String value) {
        try {
            return HEX_FORMAT.parseHex(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid hexadecimal value", e);
        }
    }
    // Validation
    private static void validateAesKey(byte[] key) {
        int length = key.length;
        if (length != 16 && length != 24 && length != 32) {
            throw new IllegalArgumentException("""
                    Invalid AES key length: %d bytes.
                    AES requires 16, 24, or 32 byte keys.
                    """.formatted(length).trim());
        }
    }
    private static void validateIv(byte[] iv) {
        if (iv.length != 16) {
            throw new IllegalArgumentException("AES CBC IV must be exactly 16 bytes");
        }
    }

    // Test

    static void main() {
        AESUtils aesUtils = new AESUtils();
        String original = "Hello Anish - Java 25";
        System.out.println("Original  : " + original);
        String encrypted = aesUtils.encrypt(AES_KEY, original);
        System.out.println("Encrypted : " + encrypted);
        String decrypted = aesUtils.decrypt(AES_KEY, encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}