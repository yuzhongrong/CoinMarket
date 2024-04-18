package com.zksg.kudoud.entitys;

import com.netease.lib_network.entitys.DexScreenTokenInfo;

public class BindingParams_BS {
    private DexScreenTokenInfo.PairsDTO mPairsDTO;
    private int mType;

    public BindingParams_BS(DexScreenTokenInfo.PairsDTO mPairsDTO, int mType) {
        this.mPairsDTO = mPairsDTO;
        this.mType = mType;
    }

    public DexScreenTokenInfo.PairsDTO getmPairsDTO() {
        return mPairsDTO;
    }

    public void setmPairsDTO(DexScreenTokenInfo.PairsDTO mPairsDTO) {
        this.mPairsDTO = mPairsDTO;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }
}
