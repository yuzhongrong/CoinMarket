package com.zksg.kudoud.utils;

import android.text.TextUtils;

import com.tencent.mmkv.MMKV;
import com.zksg.kudoud.entitys.SelectWalletEntity;
import com.zksg.kudoud.state.SharedViewModel;
import com.zksg.kudoud.utils.manager.SimpleWallet;
import com.zksg.kudoud.utils.manager.SolanaWalletManager;

import java.util.List;

public class WalletUtils {

    public static List<SimpleWallet> getAllLocalSimpleWalletByNetwork(String group){
        if(TextUtils.isEmpty(group))return null;
        try {
            String[] allkey = MMKV.mmkvWithID(group).allKeys();
            if(allkey==null||allkey.length==0)return null;
            List<SimpleWallet> simpleWallets= MapUtils.mapToList(group,allkey);
            return simpleWallets;
        } catch (Exception e) {
//            throw new RuntimeException(e);
            return null;
        }
    }


    public static SimpleWallet getCurrentSimpleWallet(SharedViewModel sharedViewModel){
        SelectWalletEntity selectWallet= sharedViewModel.getOneSelectWallet().getValue();
        String network=selectWallet.getNetwrokgroup();
        String keyAlias=selectWallet.getKeyAlias();
      return   SolanaWalletManager.getOneSimpleWalletFromMMKV(network,keyAlias);
    }

}
