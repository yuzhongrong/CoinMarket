package com.zksg.kudoud.utils.manager;
import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import com.github.mikephil.charting.utils.DataTimeUtil;
import com.google.gson.Gson;
import com.paymennt.crypto.bip32.Network;
import com.paymennt.crypto.bip32.wallet.AbstractWallet;
import com.paymennt.solanaj.wallet.SolanaWallet;
import com.zksg.kudoud.R;
import com.zksg.kudoud.entitys.TokenInfoEntity;
import com.zksg.kudoud.utils.DateUtils;
import com.zksg.kudoud.utils.MnemonicUtils;
import com.zksg.kudoud.utils.ObjectSerializationUtils;

import org.bitcoinj.crypto.MnemonicException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyStore;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class SolanaWalletManager {
    private static final String KEY_ALIAS_PREFIX = "SolanaWallet_";


    public static void createWallet(Context context,String name,String password){
        try {
            String mnemonic=MnemonicUtils.generateMnemonic();
            if(mnemonic!=null){
                SolanaWallet solanaWallet = new SolanaWallet(mnemonic, null, Network.MAINNET);
                //前3个参数是因为业务需求所以才封装了个mysolana类,用于list展示钱包场景
                saveMysolana(context,solanaWallet.getAddress(0, AbstractWallet.Chain.EXTERNAL, null),name,solanaWallet,password);
            }
        } catch (MnemonicException.MnemonicLengthException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//

    }

    private static void saveWallet(String walletName, SolanaWallet wallet) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        String walletJson = new Gson().toJson(wallet);
        byte[] encryptedWallet = encrypt(walletJson.getBytes(), keyAlias);
        saveToKeystore(encryptedWallet, keyAlias);
    }


    /**
     *
     * @param context
     * @param walletName 钱包接受地址
     * @param name 钱包名字
     * @param wallet 真正加密的solana钱包
     * @param password 用户输入的密码主要是为了解密 wallet byte[] 数据用的
     * @throws Exception
     */
    private static void saveMysolana(Context context, String walletName,String name, SolanaWallet wallet,String password) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        String walletJson = new Gson().toJson(wallet);
        byte[] encryptedWallet = encrypt(walletJson.getBytes(), keyAlias,password);

        //初始化的时候 构建一个sol地址token出来，所有的钱包都是这个样子
        List<TokenInfoEntity> tokens=new ArrayList<>();
        //一般第一个token都使用本地的图片
        tokens.add(new TokenInfoEntity(walletName,"",R.mipmap.ic_solana_common,context.getString(R.string.str_wallet_default_name)));

        MySolanaWallet mySolanaWallet=new MySolanaWallet(keyAlias,name,walletName,tokens,encryptedWallet);
        byte[] result= ObjectSerializationUtils.serializeObject(mySolanaWallet);
        saveToKeystore(result, keyAlias);
    }
    //更新钱包里面的token

    public static void updateMysolanaTokenInfo(String walletName,TokenInfoEntity entity) throws Exception {
        MySolanaWallet mySolanaWallet=getWalletFromMysolana(walletName);
        mySolanaWallet.getTokens().add(entity);
        byte[] result= ObjectSerializationUtils.serializeObject(mySolanaWallet);
        saveToKeystore(result, mySolanaWallet.getKeyAlias());

    }

    /**
     *功能：获取mysolana钱包基本信息  主要用户多个钱包列表展示
     * @param walletName 钱包地址
     * @return
     * @throws Exception
     */
    public static MySolanaWallet getWalletFromMysolana(String walletName) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        byte[] encryptedWallet = loadFromKeystore(keyAlias);
        //my solana 是没有加密的 ，只有里面的encryptData是加密的
        String mywallet=new String(encryptedWallet);
        return new Gson().fromJson(mywallet, MySolanaWallet.class);
    }


    /**功能：获取mysolana中的加密数据，主要用户导出私钥等敏感操作
     *
     * @param walletName :钱包的唯一标识
     * @param password :用户输入的密码
     * @return
     * @throws Exception
     */
    public static SolanaWallet getMysolanaEncryptData(String walletName,String password) throws Exception {
        MySolanaWallet mySolanaWallet=getWalletFromMysolana(walletName);
        byte[] encryptdata=mySolanaWallet.getEncryptData();
        String walletJson = new String(decrypt(encryptdata, mySolanaWallet.getKeyAlias(),password));
        return new Gson().fromJson(walletJson, SolanaWallet.class);
    }



    public static SolanaWallet getWallet(Context context, String walletName,String password) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        byte[] encryptedWallet = loadFromKeystore(keyAlias);
        String walletJson = new String(decrypt(encryptedWallet, keyAlias,password));
        return new Gson().fromJson(walletJson, SolanaWallet.class);
    }


    public static SolanaWallet getWallet(Context context, String walletName) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        byte[] encryptedWallet = loadFromKeystore(keyAlias);
        String walletJson = new String(decrypt(encryptedWallet, keyAlias));
        return new Gson().fromJson(walletJson, SolanaWallet.class);
    }

    public static List<SolanaWallet> getAllWallets(Context context) throws Exception {
        List<SolanaWallet> wallets = new ArrayList<>();
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (alias.startsWith(KEY_ALIAS_PREFIX)) {
                String walletName = alias.replace(KEY_ALIAS_PREFIX, "");
                SolanaWallet wallet = getWallet(context, walletName);
                wallets.add(wallet);
            }
        }
        return wallets;
    }


    public static List<MySolanaWallet> getAllMySolanaWallet() throws Exception {
        List<MySolanaWallet> wallets = new ArrayList<>();
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (alias.startsWith(KEY_ALIAS_PREFIX)) {
                String walletName = alias.replace(KEY_ALIAS_PREFIX, "");
                MySolanaWallet wallet = getWalletFromMysolana(walletName);
                wallets.add(wallet);
            }
        }
        return wallets;
    }

    public static void deleteWallet(Context context, String walletName) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keyStore.deleteEntry(keyAlias);
    }

    private static byte[] encrypt(byte[] input, String keyAlias) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKeyPair(keyAlias).getPublic());
        return cipher.doFinal(input);
    }

    private static byte[] encrypt(byte[] input, String keyAlias,String password) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getKeyPair(keyAlias,password).getPublic());
        return cipher.doFinal(input);
    }

    private static byte[] decrypt(byte[] input, String keyAlias) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getKeyPair(keyAlias).getPrivate());
        return cipher.doFinal(input);
    }

    private static byte[] decrypt(byte[] input, String keyAlias, String password) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,getKeyPair(keyAlias, password).getPrivate());
        return cipher.doFinal(input);
    }

    private static KeyPair getKeyPair(String keyAlias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        PublicKey publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, null);
        return new KeyPair(publicKey, privateKey);
    }

    private static KeyPair getKeyPair(String keyAlias,String password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        PublicKey publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());
        return new KeyPair(publicKey, privateKey);
    }

    private static void saveToKeystore(byte[] data, String keyAlias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        keyStore.setEntry(keyAlias, new KeyStore.SecretKeyEntry(new SecretKeySpec(data, "AES")), null);
    }

    private static byte[] loadFromKeystore(String keyAlias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        KeyStore.Entry entry = keyStore.getEntry(keyAlias, null);
        return ((KeyStore.SecretKeyEntry) entry).getSecretKey().getEncoded();
    }


}
