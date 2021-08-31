/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.carrental.util;

//import java.util.Base64;
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author HP
 */
public class EncryptDecryptUtils {

    private static final org.apache.logging.log4j.Logger LOGGER
            = org.apache.logging.log4j.LogManager.getLogger(EncryptDecryptUtils.class);

//    public static KeyGenerator keyGenerator = null;
//    public static SecretKey secretKey = null;
//    static Cipher cipher;
//
//    static {
//        try {
//            keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(128);
//            secretKey = keyGenerator.generateKey();
//            cipher = Cipher.getInstance("AES");
//        } catch (Exception e) {
//            LOGGER.error(e, e);
//        }
//    }
//
    public static void main(String[] args) throws Exception {
//        String plainText = "admin123";
//        LOGGER.info("Plain Text Before Encryption: " + plainText);
//
//        String encryptedText = encrypt(plainText);
//        LOGGER.info("Encrypted Text After Encryption: " + encryptedText);

        String encrypt = encrypt("password@123");
        LOGGER.info("encrypt: " + encrypt);
    }
//
//    public static String encrypt(String plainText)
//            throws Exception {
//        byte[] plainTextByte = plainText.getBytes();
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//        byte[] encryptedByte = cipher.doFinal(plainTextByte);
//        Base64.Encoder encoder = Base64.getEncoder();
//        String encryptedText = encoder.encodeToString(encryptedByte);
//        return encryptedText;
//    }
//
//    public static String decrypt(String encryptedText)
//            throws Exception {
//        Base64.Decoder decoder = Base64.getDecoder();
//        byte[] encryptedTextByte = decoder.decode(encryptedText);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
//        String decryptedText = new String(decryptedByte);
//        return decryptedText;
//    }
    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static String myKey = "0sl%UxcUV4ktq4Ob&&@p9VH@bXpo";

    public static void setKey() {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e, e);
        } catch (Exception e) {
            LOGGER.error(e, e);
        }
    }

    public synchronized static String encrypt(String strToEncrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            LOGGER.error("Error while decrypting: " + strToEncrypt, e);
        }
        return null;
    }

    public synchronized static String decrypt(String strToDecrypt) {
        try {
            setKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            LOGGER.error("Error while decrypting: " + strToDecrypt, e);
            return "";
        }

    }
}
