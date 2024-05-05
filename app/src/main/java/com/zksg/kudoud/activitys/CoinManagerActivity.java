package com.zksg.kudoud.activitys;

import static com.zksg.kudoud.wallet.constants.Constants.UI_TOKENS;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.GsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.netease.lib_common_ui.utils.GsonUtil;
import com.tencent.mmkv.MMKV;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.CoinManagerListdapter;
import com.zksg.kudoud.adapters.CoinManagerLocalTokensdapter;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.entitys.JubToken;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CoinManagerActivityViewModel;
import com.zksg.kudoud.utils.ObjectSerializationUtils;
import com.zksg.kudoud.utils.TokenConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CoinManagerActivity extends BaseActivity {
    CoinManagerActivityViewModel mCoinManagerActivityViewModel;


    public CoinManagerActivityViewModel getViewModel(){
        return mCoinManagerActivityViewModel;
    }

    @Override
    protected void initViewModel() {
        mCoinManagerActivityViewModel=getActivityScopeViewModel(CoinManagerActivityViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_coin_manager, BR.vm,mCoinManagerActivityViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                .addBindingParam(BR.hotadapter,new CoinManagerListdapter(this))
                .addBindingParam(BR.addedadapter,new CoinManagerLocalTokensdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    private void initData(){
        String keyAlias=getIntent().getStringExtra("keyAlias");

        //订阅添加
        mCoinManagerActivityViewModel.localdatas.observe(this, uiWalletToken -> {
            //保存自己到本地即可
            List<UiWalletToken> newLocalDatas= mCoinManagerActivityViewModel.localdatas.getValue();
            try {
                byte[] newLocalDatasbytes= ObjectSerializationUtils.serializeObject(newLocalDatas);
                MMKV.mmkvWithID(UI_TOKENS).encode(keyAlias,newLocalDatasbytes);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        });

//        Log.d("---datas-->",datas.size()+"");
        //从网络获取热门代币
//        mCoinManagerActivityViewModel.localdatas.postValue(null);

        //1.先初始化本地已有的币种列表
        ArrayList<UiWalletToken> tokens;
        try {
            byte[] tokensbytes=MMKV.mmkvWithID(UI_TOKENS).decodeBytes(keyAlias);
            tokens=(ArrayList<UiWalletToken>)ObjectSerializationUtils.deserializeObject(tokensbytes);
            mCoinManagerActivityViewModel.localdatas.postValue(tokens);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        initHots(tokens);

    }

    public void initHots(ArrayList<UiWalletToken> tokens){

        //2.初始化热门代币列表

        //本地摸你json数据
        String jub_str="[{\"address\":\"So11111111111111111111111111111111111111112\",\"chainId\":101,\"decimals\":9,\"name\":\"Wrapped SOL\",\"symbol\":\"SOL\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/So11111111111111111111111111111111111111112/logo.png\",\"tags\":[\"old-registry\"],\"extensions\":{\"coingeckoId\":\"wrapped-solana\"}},{\"address\":\"EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v\",\"chainId\":101,\"decimals\":6,\"name\":\"USD Coin\",\"symbol\":\"USDC\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"usd-coin\"}},{\"address\":\"Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB\",\"chainId\":101,\"decimals\":6,\"name\":\"USDT\",\"symbol\":\"USDT\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB/logo.svg\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"tether\"}},{\"address\":\"EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm\",\"chainId\":101,\"decimals\":6,\"name\":\"dogwifhat\",\"symbol\":\"$WIF\",\"logoURI\":\"https://bafkreibk3covs5ltyqxa272uodhculbr6kea6betidfwy3ajsav2vjzyum.ipfs.nftstorage.link\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"dogwifcoin\"}},{\"address\":\"7GCihgDB8fe6KNjn2MYtkzZcRjQy3t9GHdC8uHYmW2hr\",\"chainId\":101,\"decimals\":9,\"name\":\"Popcat\",\"symbol\":\"POPCAT\",\"logoURI\":\"https://bafkreidvkvuzyslw5jh5z242lgzwzhbi2kxxnpkic5wsvyno5ikvpr7reu.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"popcat\"}},{\"address\":\"7WPMh9BkBLeBd1zf3Jd3e1qy2BxgZsVAH6QdfA7Jg1wp\",\"chainId\":101,\"decimals\":6,\"name\":\"United Nations Coin\",\"symbol\":\"UN\",\"logoURI\":\"https://gateway.irys.xyz/4TjBRCLZAmKaWkxWoz6Vj-xZ0M5sGEFNGeFBbRNaFMo\",\"tags\":[\"unknown\"]},{\"address\":\"5mbK36SZ7J19An8jFochhQS4of8g6BwUjbeCSxBSoWdp\",\"chainId\":101,\"decimals\":6,\"name\":\"michi\",\"symbol\":\"$michi\",\"logoURI\":\"https://i.ibb.co/GxG0314/5mb-K36-SZ7-J19-An8j-Fochh-QS4of8g6-Bw-Ujbe-CSx-BSo-Wdp.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"michicoin\"}},{\"address\":\"JUPyiwrYJFskUPiHa7hkeR8VUtAeFoSYbKedZNsDvCN\",\"chainId\":101,\"decimals\":6,\"name\":\"Jupiter\",\"symbol\":\"JUP\",\"logoURI\":\"https://static.jup.ag/jup/icon.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"jupiter-exchange-solana\"}},{\"address\":\"MEW1gQWJ3nEXg2qgERiKu7FAFj79PHvQVREQUzScPP5\",\"chainId\":101,\"decimals\":5,\"name\":\"cat in a dogs world\",\"symbol\":\"MEW\",\"logoURI\":\"https://bafkreidlwyr565dxtao2ipsze6bmzpszqzybz7sqi2zaet5fs7k53henju.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"cat-in-a-dogs-world\"}},{\"address\":\"69kdRLyP5DTRkpHraaSZAQbWmAwzF9guKjZfzMXzcbAs\",\"chainId\":101,\"decimals\":6,\"name\":\"American Coin\",\"symbol\":\"USA\",\"logoURI\":\"https://arweave.net/xUs-YuP__T2cCUofTOJmYcHIzFHj5s8TdH-O-g9qn3w\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"american-coin\"}}]";

        List<JubToken> datas=parseTokenData(jub_str);

        //初始化热门代币
        List<UiWalletToken> convertResault=TokenConverter.convertJubTokensToUiWalletTokens(datas);
        boolean isRemoveAll= TokenConverter.FilterJubTokens(convertResault,tokens);
        if(isRemoveAll){
            mCoinManagerActivityViewModel.hotdatas.postValue(convertResault);
        }


    }


    public static List<JubToken> parseTokenData(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<JubToken>>() {}.getType();
        return gson.fromJson(jsonString, listType);
    }


    public class ClickProxy {
        public void close() {
            finish();

        }
    }
}
