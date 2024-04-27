package com.zksg.kudoud.utils.manager;
import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import com.google.gson.Gson;
import com.paymennt.crypto.bip32.Network;
import com.paymennt.crypto.bip32.wallet.AbstractWallet;
import com.paymennt.solanaj.wallet.SolanaWallet;
import com.tencent.mmkv.MMKV;
import com.zksg.kudoud.R;
import com.zksg.kudoud.callback.WalletCreateCallback;
import com.zksg.kudoud.contants.CoinType;
import com.zksg.kudoud.entitys.TokenInfoEntity;
import com.zksg.kudoud.utils.MnemonicUtils;
import com.zksg.kudoud.utils.ObjectSerializationUtils;

import org.bitcoinj.crypto.MnemonicException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SolanaWalletManager {
    private static final String KEY_ALIAS_PREFIX = "SolanaWallet_";


    public static void createWallet(Context context, String name, String password, WalletCreateCallback callback){
        Exception err = null;
        String keyAlias=null;
        try {
            String mnemonic=MnemonicUtils.generateMnemonic();
            if(mnemonic!=null){
                SolanaWallet solanaWallet = new SolanaWallet(mnemonic, null, Network.MAINNET);
                //前3个参数是因为业务需求所以才封装了个mysolana类,用于list展示钱包场景
                String address=solanaWallet.getAddress(0, AbstractWallet.Chain.EXTERNAL, null);
                keyAlias=saveMysolana(context,address,name,solanaWallet,password);
            }
        } catch (MnemonicException.MnemonicLengthException e) {
            err= new RuntimeException(e);
        } catch (IOException e) {
            err= new RuntimeException(e);
        } catch (Exception e) {
            err= new RuntimeException(e);
        } finally {
            if(err!=null){//创建失败
                callback.walletCreateFail(err);
            }else{//创建成功
                callback.walletCreateComplete(keyAlias);
            }
            
        }
//

    }

//    private static void saveWallet(String walletName, SolanaWallet wallet) throws Exception {
//        String keyAlias = KEY_ALIAS_PREFIX + walletName;
//        String walletJson = new Gson().toJson(wallet);
//        byte[] encryptedWallet = encrypt(walletJson.getBytes(), keyAlias);
//        saveToKeystore(encryptedWallet, keyAlias);
//    }


    /**
     *
     * @param context
     * @param walletName 钱包接受地址
     * @param name 钱包名字
     * @param wallet 真正加密的solana钱包
     * @param password 用户输入的密码主要是为了解密 wallet byte[] 数据用的
     * @throws Exception
     */
    private static String saveMysolana(Context context, String walletName,String name, SolanaWallet wallet,String password) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        String walletJson = new Gson().toJson(wallet);
        byte[] encryptedWallet = encrypt(walletJson.getBytes(), keyAlias,password);

        //初始化的时候 构建一个sol地址token出来，所有的钱包都是这个样子
        List<TokenInfoEntity> tokens=new ArrayList<>();
        //一般第一个token都使用本地的图片
        tokens.add(new TokenInfoEntity("So11111111111111111111111111111111111111112","",R.mipmap.ic_solana_common,context.getString(R.string.str_wallet_default_name)));
        SimpleWallet mySimpleWallet=new SimpleWallet(keyAlias, CoinType.SOLANA.getKey(), name,walletName,tokens);
        byte[]  simpleWallet = ObjectSerializationUtils.serializeObject(mySimpleWallet);

        saveToMmkv(simpleWallet, keyAlias,encryptedWallet);
        return keyAlias;
    }
    //更新钱包里面的token



    /**
     *功能：获取mysolana钱包基本信息  主要用户多个钱包列表展示
     * @param walletName 钱包地址
     * @return
     * @throws Exception
     */
    public static SimpleWallet getWalletFromMysolana(String walletName) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        byte[] encryptedWallet = loadFromKeystore(keyAlias);
        //my solana 是没有加密的 ，只有里面的encryptData是加密的
        String mywallet=new String(encryptedWallet);
        return new Gson().fromJson(mywallet, SimpleWallet.class);
    }


    /**功能：获取mysolana中的加密数据，主要用户导出私钥等敏感操作
     *
     * @param walletName :钱包的唯一标识
     * @param password :用户输入的密码
     * @return
     * @throws Exception
     */
//    public static SolanaWallet getMysolanaEncryptData(String walletName,String password) throws Exception {
//        SimpleWallet mySolanaWallet=getWalletFromMysolana(walletName);
//        byte[] encryptdata=mySolanaWallet.getEncryptData();
//        String walletJson = new String(decrypt(encryptdata, mySolanaWallet.getKeyAlias(),password));
//        return new Gson().fromJson(walletJson, SolanaWallet.class);
//    }



    public static SolanaWallet getWallet(Context context, String walletName,String password) throws Exception {
        String keyAlias = KEY_ALIAS_PREFIX + walletName;
        byte[] encryptedWallet = loadFromKeystore(keyAlias);
        String walletJson = new String(decrypt(encryptedWallet, keyAlias,password));
        return new Gson().fromJson(walletJson, SolanaWallet.class);
    }


//    public static SolanaWallet getWallet(Context context, String walletName) throws Exception {
//        String keyAlias = KEY_ALIAS_PREFIX + walletName;
//        byte[] encryptedWallet = loadFromKeystore(keyAlias);
//        String walletJson = new String(decrypt(encryptedWallet, keyAlias));
//        return new Gson().fromJson(walletJson, SolanaWallet.class);
//    }

//    public static List<SolanaWallet> getAllWallets(Context context) throws Exception {
//        List<SolanaWallet> wallets = new ArrayList<>();
//        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
//        keyStore.load(null);
//        Enumeration<String> aliases = keyStore.aliases();
//        while (aliases.hasMoreElements()) {
//            String alias = aliases.nextElement();
//            if (alias.startsWith(KEY_ALIAS_PREFIX)) {
//                String walletName = alias.replace(KEY_ALIAS_PREFIX, "");
//                SolanaWallet wallet = getWallet(context, walletName);
//                wallets.add(wallet);
//            }
//        }
//        return wallets;
//    }


    public static List<SimpleWallet> getAllMySolanaWallet() throws Exception {
        List<SimpleWallet> wallets = new ArrayList<>();
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (alias.startsWith(KEY_ALIAS_PREFIX)) {
                String walletName = alias.replace(KEY_ALIAS_PREFIX, "");
                SimpleWallet wallet = getWalletFromMysolana(walletName);
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




    //由于长度问题所以不能使用这种方式
//    private static byte[] encrypt(byte[] input, String keyAlias,String password) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, getKeyPair(keyAlias,password).getPublic());
//        return cipher.doFinal(input);
//    }

//    private static byte[] decrypt(byte[] input, String keyAlias, String password) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE,getKeyPair(keyAlias, password).getPrivate());
//        return cipher.doFinal(input);
//    }

    //使用新的方式来加密和解密
    private static byte[] encrypt(byte[] input, String keyAlias, String password) throws Exception {
        // 生成AES密钥
        SecretKey secretKey = generateAESKey();

        // 使用AES加密数据
        byte[] encryptedData = encryptAES(input, secretKey);

        // 使用RSA加密AES密钥
        PublicKey publicKey = getKeyPair(keyAlias, password).getPublic();
        byte[] encryptedKey = encryptRSA(secretKey.getEncoded(), publicKey);

        // 将加密后的AES密钥和加密后的数据组合在一起
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(encryptedKey);
        outputStream.write(encryptedData);
        return outputStream.toByteArray();
    }

    // 生成AES密钥
    private static SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    // 使用AES加密数据
    private static byte[] encryptAES(byte[] input, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(input);
    }

    // 使用RSA加密数据
    private static byte[] encryptRSA(byte[] input, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(input);
    }

    private static byte[] decrypt(byte[] encryptedData, String keyAlias, String password) throws Exception {
        // 使用RSA解密AES密钥
        PrivateKey privateKey = getKeyPair(keyAlias, password).getPrivate();
        byte[] encryptedKey = Arrays.copyOfRange(encryptedData, 0, 256); // 假设RSA加密的密钥长度为256字节
        byte[] decryptedKey = decryptRSA(encryptedKey, privateKey);

        // 使用AES解密数据
        byte[] encryptedDataContent = Arrays.copyOfRange(encryptedData, 256, encryptedData.length);
        SecretKey secretKey = new SecretKeySpec(decryptedKey, "AES");
        return decryptAES(encryptedDataContent, secretKey);
    }

    // 使用RSA解密数据
    private static byte[] decryptRSA(byte[] input, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(input);
    }

    // 使用AES解密数据
    private static byte[] decryptAES(byte[] input, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
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
        // 检查证书是否存在
        if (!keyStore.containsAlias(keyAlias)) {
            // 如果不存在，先生成密钥对并存储到密钥库中
            createKeyPair(keyAlias);
        }

        PublicKey publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, password.toCharArray());
        return new KeyPair(publicKey, privateKey);
    }


    private static void createKeyPair(String keyAlias) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");
        KeyGenParameterSpec keyGenParameterSpec = new KeyGenParameterSpec.Builder(keyAlias, KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                .setKeySize(2048)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                .build();
        keyPairGenerator.initialize(keyGenParameterSpec);
        keyPairGenerator.generateKeyPair();
    }

    /**
     * 保存加密后的数据到mmkv keystore只管理密钥对的 不存储数据
     * @param simplewallet ：加密后的solanawallet 对象
     * @param keyAlias :别名
     *@param encryptedWallet :  带私钥的solana钱包
     * @throws Exception
     */
    private static void saveToMmkv(byte[] simplewallet, String keyAlias,byte[] encryptedWallet){
        //这个组用于展示页面基本信息
        MMKV.mmkvWithID(CoinType.SOLANA.getKey()).encode(keyAlias,simplewallet);
        //这个组提供查询获取加密数据功能--例如公钥私钥
        MMKV.defaultMMKV().encode(keyAlias,encryptedWallet);

//       SolanaWallet solanaWallet= getSolanaWalletFromMMKV(keyAlias);
//        HdPrivateKey privateKey= solanaWallet.getPrivateKey(0, AbstractWallet.Chain.EXTERNAL, null);
//        SolanaAccount solanaAccount=new SolanaAccount(privateKey);
//        Log.d("-----privatekey-->",Base58.encode(solanaAccount.getSecretKey()));



    }


    /**
     * 用户具体钱包获取SolanaWallet
     * @param keyAlias ：钱包别名
     * @return SolanaWallet ：SolanaWallet对象
     */
    public static SimpleWallet getOneSimpleWalletFromMMKV(String networkgroup,String keyAlias){
        SolanaWallet solanaWallet=null;
        //从mmkv中获取mysolana 钱包对象
        byte[] simpleWalletByte=MMKV.mmkvWithID(networkgroup).decodeBytes(keyAlias);
        SimpleWallet msimplewallet= null;
        try {
            //获取 MySolanaWallet 对象
            msimplewallet = (SimpleWallet) ObjectSerializationUtils.deserializeObject(simpleWalletByte);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return msimplewallet;


    }

    private static byte[] loadFromKeystore(String keyAlias) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        KeyStore.Entry entry = keyStore.getEntry(keyAlias, null);
        return ((KeyStore.SecretKeyEntry) entry).getSecretKey().getEncoded();
    }


}
