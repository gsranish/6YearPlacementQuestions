package com.anish.asyncApiCall;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

/**
 * AES encryption/decryption utility.
 * @author ANISH KUMAR
 */
public class AESUtils {

    private static final int DEFAULT_ITERATION_COUNT = 1000;
    private static final int DEFAULT_KEY_SIZE = 256;
    private static final String DEFAULT_IV = "554495832998beb0b0f7c198c46fbaba";
    private static final String AES_KEY = "b914741dca31bfae4c2d4d003780b00e";
    private final int iterationCount;
    private final int keySize;
    public AESUtils() {
        this(DEFAULT_KEY_SIZE, DEFAULT_ITERATION_COUNT);
    }
    public AESUtils(int keySize, int iterationCount) {
        this.keySize = keySize;
        this.iterationCount = iterationCount;
    }
    // Encryption
    public String encrypt(String key, String iv, String plaintext) {
        byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, generateKey(key), iv,
                plaintext.getBytes(StandardCharsets.UTF_8)
        );
        return base64(encrypted);
    }
    public String encrypt(String key, String plaintext) {
        return encrypt(key, DEFAULT_IV, plaintext);
    }
    public String encrypt(String salt, String iv, String passphrase, String plaintext) {
        SecretKey key = generateKey(salt, passphrase);
        byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes(StandardCharsets.UTF_8));
        return base64(encrypted);
    }
    // Decryption
    public String decrypt(String key, String iv, String encryptedData) {
        byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, generateKey(key), iv, base64(encryptedData));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
    public String decrypt(String key, String ciphertext) {
        return decrypt(key, DEFAULT_IV, ciphertext);
    }
    public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
        SecretKey key = generateKey(salt, passphrase);
        byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
        return new String(decrypted, StandardCharsets.UTF_8);
    }
    // AES operation
    private byte[] doFinal(int cipherMode, SecretKey key, String iv, byte[] data) {
        try {
            // Create Cipher per operation because Cipher is NOT thread-safe.
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(hex(iv));
            cipher.init(cipherMode, key, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException
                 | IllegalBlockSizeException | BadPaddingException e) {
            throw failure(e);
        }
    }
    // Key generation
    public static SecretKey generateKey(String key) {
        byte[] keyBytes = hex(key);
        return new SecretKeySpec(keyBytes, "AES");
    }
    public String generateHexKey(String salt, String passphrase) {
        SecretKey key = generateKey(salt, passphrase);
        return hex(key.getEncoded());
    }
    private SecretKey generateKey(String salt, String passphrase) {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
            byte[] generatedKey = factory.generateSecret(spec).getEncoded();
            return new SecretKeySpec(generatedKey, "AES");
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw failure(e);
        }
    }
    // Utility methods
    public static String randomHex(int length) {
        byte[] randomBytes = new byte[length];
        new SecureRandom().nextBytes(randomBytes);
        return hex(randomBytes);
    }
    public static String base64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }
    public static byte[] base64(String value) {
        return Base64.decodeBase64(value);
    }
    public static String hex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }
    public static byte[] hex(String value) {
        try {
            return Hex.decodeHex(value.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Invalid hexadecimal value", e
            );
        }
    }
    private IllegalStateException failure(Exception e) {
        return new IllegalStateException("AESUtils error: " + e.getMessage(),e);
    }

    static void main() {
        String originalValue = "HelloAnishKumar";
        AESUtils utils = new AESUtils();
        System.out.println("Original value: " + originalValue);
        String encryptedData = utils.encrypt(AES_KEY, originalValue);
        System.out.println("Encrypted value: " + encryptedData);
        String decryptedData = utils.decrypt(AES_KEY, encryptedData);
        System.out.println("Decrypted value: " + decryptedData);
    }
}