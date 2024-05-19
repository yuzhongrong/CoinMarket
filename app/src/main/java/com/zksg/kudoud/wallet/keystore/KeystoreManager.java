package com.zksg.kudoud.wallet.keystore;
import android.content.Context;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.io.IOException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class KeystoreManager {
    private static final String RSA_KEY_ALIAS = "SolanaWalletRSAKey";
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    private static final String AES_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final String RSA_TRANSFORMATION = "RSA/ECB/PKCS1Padding";

    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void initialize() throws Exception {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
        keyStore.load(null);
        if (!keyStore.containsAlias(RSA_KEY_ALIAS)) {
            generateRSAKeyPair();
        }

    }

    // 生成RSA密钥对
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void generateRSAKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, ANDROID_KEYSTORE);
        keyPairGenerator.initialize(
                new KeyGenParameterSpec.Builder(
                        RSA_KEY_ALIAS,
                        KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT
                ).setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                        .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                        .setUserAuthenticationRequired(true)
                        .setInvalidatedByBiometricEnrollment(true)
                        .build()
        );
        keyPairGenerator.generateKeyPair();
    }

    // 生成AES密钥
    private static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES);
        keyGenerator.init(256); // 使用256位AES密钥
        return keyGenerator.generateKey();
    }

    // 获取RSA公钥
    private static PublicKey getRSAPublicKey() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return keyStore.getCertificate(RSA_KEY_ALIAS).getPublicKey();
    }

    // 获取RSA私钥
    private static PrivateKey getRSAPrivateKey() throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, UnrecoverableKeyException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return (PrivateKey) keyStore.getKey(RSA_KEY_ALIAS, null);
    }

    // 使用RSA公钥加密AES密钥
    private static byte[] encryptAESKeyWithRSA(SecretKey aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, getRSAPublicKey());
        return cipher.doFinal(aesKey.getEncoded());
    }

    // 使用RSA私钥解密AES密钥
    private static SecretKey decryptAESKeyWithRSA(byte[] encryptedAESKey) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA_TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, getRSAPrivateKey());
        byte[] aesKeyBytes = cipher.doFinal(encryptedAESKey);
        return new SecretKeySpec(aesKeyBytes, KeyProperties.KEY_ALGORITHM_AES);
    }

    // 使用AES加密数据
    private static byte[] encryptDataWithAES(byte[] data, SecretKey aesKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.ENCRYPT_MODE, aesKey, spec);
        return cipher.doFinal(data);
    }

    // 使用AES解密数据
    private static byte[] decryptDataWithAES(byte[] encryptedData, SecretKey aesKey, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_TRANSFORMATION);
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        cipher.init(Cipher.DECRYPT_MODE, aesKey, spec);
        return cipher.doFinal(encryptedData);
    }

    // 加密钱包数据
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static EncryptedData encryptWalletData(byte[] walletData) throws Exception {
        SecretKey aesKey = generateAESKey();
        byte[] encryptedAESKey = encryptAESKeyWithRSA(aesKey);
        byte[] iv = new byte[12];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);
        byte[] encryptedData = encryptDataWithAES(walletData, aesKey, iv);
        return new EncryptedData(Base64.getEncoder().encodeToString(encryptedData), Base64.getEncoder().encodeToString(encryptedAESKey), iv);
    }

    // 解密钱包数据
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static byte[] decryptWalletData(EncryptedData encryptedData) throws Exception {
        byte[] encryptedAESKey = Base64.getDecoder().decode(encryptedData.getEncryptedAESKey());
        SecretKey aesKey = decryptAESKeyWithRSA(encryptedAESKey);
        byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedData.getEncryptedData());
        return decryptDataWithAES(encryptedDataBytes, aesKey, encryptedData.getIv());



    }

    // 包装加密数据的类
    public static class EncryptedData implements Serializable {
        private String encryptedData;
        private String encryptedAESKey;
        private byte[] iv;

        public EncryptedData(String encryptedData, String encryptedAESKey, byte[] iv) {
            this.encryptedData = encryptedData;
            this.encryptedAESKey = encryptedAESKey;
            this.iv = iv;
        }

        public String getEncryptedData() {
            return encryptedData;
        }

        public String getEncryptedAESKey() {
            return encryptedAESKey;
        }

        public byte[] getIv() {
            return iv;
        }
    }

}
