package com.zksg.kudoud.adapters;

import static com.zksg.kudoud.wallet.constants.Constants.TOKEN_SOL_CONTRACT;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.kunminx.architecture.ui.adapter.SimpleDataBindingAdapter;
import com.zksg.kudoud.R;
import com.zksg.kudoud.activitys.CoinWalletDetailActivity;
import com.zksg.kudoud.activitys.SendCoinActivity;
import com.zksg.kudoud.callback.DialogCloseCallback;
import com.zksg.kudoud.databinding.ItemMemeWalletBinding;
import com.zksg.kudoud.entitys.UiWalletToken;
import com.zksg.kudoud.state.MeFragmentViewModel;
import com.zksg.kudoud.utils.DiffUtils;
import com.zksg.kudoud.utils.IntentUtils;

import java.util.List;
import java.util.stream.Collectors;

public class MemeWalletListDialogdapter extends SimpleDataBindingAdapter<UiWalletToken, ItemMemeWalletBinding> {

    private MeFragmentViewModel meFragmentViewModel;
    private Context mContex;
    public MemeWalletListDialogdapter(Context context, MeFragmentViewModel vm, DialogCloseCallback callback) {
        super(context, R.layout.item_meme_wallet, DiffUtils.getInstance().geTokenInfoEntityCallback());
        this.mContex=context;
        this.meFragmentViewModel=vm;
        setOnItemClickListener((item, position) -> {
//            Intent intent=new Intent(context, CoinWalletDetailActivity.class);
//            intent.putExtra("token",item);
//            intent.putExtra("sol",datas.get(0));
//            intent.putExtra("wallet",vm.mSimpleWallet.get().getAddress());
//            context.startActivity(intent);
            callback.closeAction(true);
            List<UiWalletToken> datas=meFragmentViewModel.uitokenInfos.getValue().stream().filter(f->f.getMint().equals(TOKEN_SOL_CONTRACT)).collect(Collectors.toList());
            IntentUtils.openIntent(context,new Intent(context, SendCoinActivity.class).putExtra("token",item).putExtra("sol",datas.get(0)));
        });
    }
    @Override
    protected void onBindItem(ItemMemeWalletBinding binding, UiWalletToken item, RecyclerView.ViewHolder holder) {
        binding.setUiwallettoken(item);
        binding.setVm(this.meFragmentViewModel);
    }

    public MeFragmentViewModel getMeFragmentViewModel(){
        return this.meFragmentViewModel;
    }

}
