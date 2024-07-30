package com.zksg.kudoud.entitys;

import com.netease.lib_network.entitys.DexScreenTokenInfo1;

public class BindingParams_BS {
    private DexScreenTokenInfo1.PairsDTO mPairsDTO;
    private int mType;

    public BindingParams_BS(DexScreenTokenInfo1.PairsDTO mPairsDTO, int mType) {
        this.mPairsDTO = mPairsDTO;
        this.mType = mType;
    }

    public DexScreenTokenInfo1.PairsDTO getmPairsDTO() {
        return mPairsDTO;
    }

    public void setmPairsDTO(DexScreenTokenInfo1.PairsDTO mPairsDTO) {
        this.mPairsDTO = mPairsDTO;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
