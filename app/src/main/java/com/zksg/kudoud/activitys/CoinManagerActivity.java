package com.zksg.kudoud.activitys;

import static com.zksg.kudoud.wallet.constants.Constants.UI_TOKENS;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

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
import com.zksg.kudoud.adapters.CoinManagerSearchTokensdapter;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.entitys.JubToken;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CoinManagerActivityViewModel;
import com.zksg.kudoud.utils.ObjectSerializationUtils;
import com.zksg.kudoud.utils.SearchTokenUtils;
import com.zksg.kudoud.utils.TokenConverter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CoinManagerActivity extends BaseActivity {
    CoinManagerActivityViewModel mCoinManagerActivityViewModel;
    String keyAlias;


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
                .addBindingParam(BR.addedadapter,new CoinManagerLocalTokensdapter(this))
                .addBindingParam(BR.searchadapter,new CoinManagerSearchTokensdapter(this))
                .addBindingParam(BR.editfocus, this.onEditFocusChangeListener)
                .addBindingParam(BR.searchTextWatcher, this.onSearchTextWatcher);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        keyAlias=getIntent().getStringExtra("keyAlias");
        initObserve(keyAlias);
        initData(keyAlias);
    }

    private void initObserve(String keyAlias){

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



    }



    private void initData(String keyAlias){


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

       List<UiWalletToken> hotDatas=initHots(tokens);
       initSearchData(tokens,hotDatas);

    }

    public List<UiWalletToken> initHots(ArrayList<UiWalletToken> tokens){

        //2.初始化热门代币列表

        /**本地摸你json数据 流程： MainActivity里面 请求api服务拿到总数据源--->生成热门数据源--->保存总数据源,保存热门数据源 ，这里提取的是热门数据源
         *
         */
        String jub_str="[{\"address\":\"So11111111111111111111111111111111111111112\",\"chainId\":101,\"decimals\":9,\"name\":\"Wrapped SOL\",\"symbol\":\"SOL\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/So11111111111111111111111111111111111111112/logo.png\",\"tags\":[\"old-registry\"],\"extensions\":{\"coingeckoId\":\"wrapped-solana\"}},{\"address\":\"EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v\",\"chainId\":101,\"decimals\":6,\"name\":\"USD Coin\",\"symbol\":\"USDC\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/EPjFWdd5AufqSSqeM2qN1xzybapC8G4wEGGkZwyTDt1v/logo.png\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"usd-coin\"}},{\"address\":\"Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB\",\"chainId\":101,\"decimals\":6,\"name\":\"USDT\",\"symbol\":\"USDT\",\"logoURI\":\"https://raw.githubusercontent.com/solana-labs/token-list/main/assets/mainnet/Es9vMFrzaCERmJfrF4H2FYD4KCoNkY11McCe8BenwNYB/logo.svg\",\"tags\":[\"old-registry\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"tether\"}},{\"address\":\"EKpQGSJtjMFqKZ9KQanSqYXRcF8fBopzLHYxdM65zcjm\",\"chainId\":101,\"decimals\":6,\"name\":\"dogwifhat\",\"symbol\":\"$WIF\",\"logoURI\":\"https://bafkreibk3covs5ltyqxa272uodhculbr6kea6betidfwy3ajsav2vjzyum.ipfs.nftstorage.link\",\"tags\":[\"community\",\"solana-fm\"],\"extensions\":{\"coingeckoId\":\"dogwifcoin\"}},{\"address\":\"7GCihgDB8fe6KNjn2MYtkzZcRjQy3t9GHdC8uHYmW2hr\",\"chainId\":101,\"decimals\":9,\"name\":\"Popcat\",\"symbol\":\"POPCAT\",\"logoURI\":\"https://bafkreidvkvuzyslw5jh5z242lgzwzhbi2kxxnpkic5wsvyno5ikvpr7reu.ipfs.nftstorage.link\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"popcat\"}},{\"address\":\"7WPMh9BkBLeBd1zf3Jd3e1qy2BxgZsVAH6QdfA7Jg1wp\",\"chainId\":101,\"decimals\":6,\"name\":\"United Nations Coin\",\"symbol\":\"UN\",\"logoURI\":\"https://gateway.irys.xyz/4TjBRCLZAmKaWkxWoz6Vj-xZ0M5sGEFNGeFBbRNaFMo\",\"tags\":[\"unknown\"]},{\"address\":\"5mbK36SZ7J19An8jFochhQS4of8g6BwUjbeCSxBSoWdp\",\"chainId\":101,\"decimals\":6,\"name\":\"michi\",\"symbol\":\"$michi\",\"logoURI\":\"https://i.ibb.co/GxG0314/5mb-K36-SZ7-J19-An8j-Fochh-QS4of8g6-Bw-Ujbe-CSx-BSo-Wdp.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"michicoin\"}},{\"address\":\"JUPyiwrYJFskUPiHa7hkeR8VUtAeFoSYbKedZNsDvCN\",\"chainId\":101,\"decimals\":6,\"name\":\"Jupiter\",\"symbol\":\"JUP\",\"logoURI\":\"https://static.jup.ag/jup/icon.png\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"jupiter-exchange-solana\"}},{\"address\":\"MEW1gQWJ3nEXg2qgERiKu7FAFj79PHvQVREQUzScPP5\",\"chainId\":101,\"decimals\":5,\"name\":\"cat in a dogs world\",\"symbol\":\"MEW\",\"logoURI\":\"https://bafkreidlwyr565dxtao2ipsze6bmzpszqzybz7sqi2zaet5fs7k53henju.ipfs.nftstorage.link/\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"cat-in-a-dogs-world\"}},{\"address\":\"69kdRLyP5DTRkpHraaSZAQbWmAwzF9guKjZfzMXzcbAs\",\"chainId\":101,\"decimals\":6,\"name\":\"American Coin\",\"symbol\":\"USA\",\"logoURI\":\"https://arweave.net/xUs-YuP__T2cCUofTOJmYcHIzFHj5s8TdH-O-g9qn3w\",\"tags\":[\"community\"],\"extensions\":{\"coingeckoId\":\"american-coin\"}}]";

        List<JubToken> datas=parseTokenData(jub_str);

        //初始化热门代币
        List<UiWalletToken> convertResault=TokenConverter.convertJubTokensToUiWalletTokens(datas);
        boolean isRemoveAll= TokenConverter.FilterJubTokens(convertResault,tokens);
        if(isRemoveAll){
            mCoinManagerActivityViewModel.hotdatas.postValue(convertResault);
        }
        return convertResault;


    }

    private void initSearchData(List<UiWalletToken> tokens,List<UiWalletToken> convertResault){

        //初始化总的数据源
        List<UiWalletToken> amountDatas= new ArrayList<>();
        amountDatas.addAll(tokens);
        amountDatas.addAll(convertResault);
        mCoinManagerActivityViewModel.amountdatas.postValue(amountDatas);
        //用于备份原始数据 在amountdatascache里面查数据 ，把结果post到amountdatas这里
        mCoinManagerActivityViewModel.amountdatascache.postValue(amountDatas);
        //关闭 清理图标
        mCoinManagerActivityViewModel.clearAll.set(false);
        //还原edittext显示内容
        mCoinManagerActivityViewModel.empty.set("");

    }


    public static List<JubToken> parseTokenData(String jsonString) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<JubToken>>() {}.getType();
        return gson.fromJson(jsonString, listType);
    }

    private final View.OnFocusChangeListener onEditFocusChangeListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            // 当焦点获取时，启动光标闪烁动画
            if (hasFocus) {
                //显示搜索布局
                mCoinManagerActivityViewModel.showSearchLayout.set(true);
            } else {
                //隐藏搜索布局
                mCoinManagerActivityViewModel.showSearchLayout.set(false);
                initData(keyAlias);

            }
        }
    };

    private final TextWatcher onSearchTextWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            //获取当前的amountdatascache 数据
            List<UiWalletToken> cacheTokens=mCoinManagerActivityViewModel.amountdatascache.getValue();

            if(charSequence==null)return;

            if(charSequence.toString().trim().length()==0){
                mCoinManagerActivityViewModel.amountdatas.postValue(cacheTokens);
                mCoinManagerActivityViewModel.clearAll.set(false);
            }else{
                if(mCoinManagerActivityViewModel.showSearchLayout.get()){
                    mCoinManagerActivityViewModel.clearAll.set(true);
                }else{
                    mCoinManagerActivityViewModel.clearAll.set(false);
                }
            }


            //在这里查询
            List<UiWalletToken> searchResult= SearchTokenUtils.searchTokens(cacheTokens,charSequence.toString().trim());
            mCoinManagerActivityViewModel.amountdatas.postValue(searchResult);

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    public class ClickProxy {
        public void close() {
            finish();

        }

        public void clearAllText(){
            mCoinManagerActivityViewModel.empty.set("");
        }

        public void cancelSearchModel(){

            //关闭搜索模式总开关
            mCoinManagerActivityViewModel.showSearchLayout.set(false);

//            initData();
        }

    }
}
