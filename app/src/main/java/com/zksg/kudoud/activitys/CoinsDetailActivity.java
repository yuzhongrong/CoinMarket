package com.zksg.kudoud.activitys;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.ToastUtils;
import com.kunminx.architecture.ui.page.BaseActivity;
import com.kunminx.architecture.ui.page.DataBindingConfig;
import com.lxj.xpopup.XPopup;
import com.netease.lib_network.entitys.DexScreenTokenInfo;
import com.zksg.kudoud.BR;
import com.zksg.kudoud.R;
import com.zksg.kudoud.adapters.CommonKlineDataPagerAdapter;
import com.zksg.kudoud.adapters.MemePoolListdapter;
import com.zksg.kudoud.adapters.SimpleFragmentPagerAdapter;
import com.zksg.kudoud.beans.Kline24ChangeChannelEnum;
import com.zksg.kudoud.dialogs.ResetFactoryDialog;
import com.zksg.kudoud.fragments.ChartKLineFragment;
import com.zksg.kudoud.state.AboutViewModel;
import com.zksg.kudoud.state.CoinsDetailViewModel;
import com.zksg.kudoud.utils.IntentUtils;
import com.zksg.kudoud.utils.StringUtils;

import java.util.List;

public class CoinsDetailActivity extends BaseDialogActivity {
    CoinsDetailViewModel mCoinsDetailViewModel;

    public CoinsDetailViewModel getSharedViewModel() {
        return mCoinsDetailViewModel;
    }

    @Override
    protected void initViewModel() {
        mCoinsDetailViewModel=getActivityScopeViewModel(CoinsDetailViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.activity_coins_detail, BR.vm,mCoinsDetailViewModel)
                .addBindingParam(BR.click,new ClickProxy()).addBindingParam(BR.adapter,new MemePoolListdapter(this));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitData();
    }


    private void InitData(){
        String contract=getIntent().getExtras().getString("contract");
//        CommonKlineDataPagerAdapter adapter=new CommonKlineDataPagerAdapter(getSupportFragmentManager(), new Kline24ChangeChannelEnum[]{Kline24ChangeChannelEnum.K_5M,Kline24ChangeChannelEnum.K_1H,Kline24ChangeChannelEnum.K_6H,Kline24ChangeChannelEnum.K_24H});
//        mCoinsDetailViewModel.tabAdapter.set(adapter);

        //init data
        mCoinsDetailViewModel.loadingVisible.observe(this,it->{
            if(it){showDialog();}else{dismissDialog();}
        });

        mCoinsDetailViewModel.tokenInfo.observe(this, dexScreenTokenInfo -> {
            mCoinsDetailViewModel.mPairsDTO.postValue(dexScreenTokenInfo.getPairs().get(0));
        });
        mCoinsDetailViewModel.getTokenInfo(contract);

    }

    public class ClickProxy {
        public void showFactoryReset(){
            new XPopup.Builder(CoinsDetailActivity.this).asCustom(new ResetFactoryDialog(CoinsDetailActivity.this)).show();
        }

        public void startTwitter(){
            String username="";
            List<DexScreenTokenInfo.PairsDTO.InfoDTO.SocialsDTO> socials=mCoinsDetailViewModel.mPairsDTO.getValue().getInfo().getSocials();
            for(DexScreenTokenInfo.PairsDTO.InfoDTO.SocialsDTO item:socials){
                if(item.getType().equals("twitter")){
                    username= StringUtils.extractUsernameFromUrl(item.getUrl());
                }
            }
            if(username.equals("")){
                ToastUtils.showShort(R.string.str_Invalid_twitter);
                return;
            }
            IntentUtils.openTwitter(CoinsDetailActivity.this,username);
        }


        public void startTelegram(){
            String username="";
            List<DexScreenTokenInfo.PairsDTO.InfoDTO.SocialsDTO> socials=mCoinsDetailViewModel.mPairsDTO.getValue().getInfo().getSocials();
            for(DexScreenTokenInfo.PairsDTO.InfoDTO.SocialsDTO item:socials){
                if(item.getType().equals("telegram")){
                    username= StringUtils.extractUsernameFromUrlTG(item.getUrl());
                }
            }
            if(username.equals("")){
                ToastUtils.showShort(R.string.str_Invalid_telegram);
                return;
            }
            IntentUtils.openTelegram(CoinsDetailActivity.this,username);
        }


        public void startWeb(){
            String url="";
            List<DexScreenTokenInfo.PairsDTO.InfoDTO.WebsitesDTO> webs=mCoinsDetailViewModel.mPairsDTO.getValue().getInfo().getWebsites();
            if(webs==null||webs.size()==0){
                ToastUtils.showShort(R.string.str_Invalid_website);
                return;
            }
            for(DexScreenTokenInfo.PairsDTO.InfoDTO.WebsitesDTO item:webs){
                if(item.getLabel().equals("Website")){
                    url=item.getUrl();
                }
            }
            if(url.equals("")){
                ToastUtils.showShort(R.string.str_Invalid_website);
                return;
            }

            IntentUtils.openWebsite(CoinsDetailActivity.this,url);

        }




    }



}
