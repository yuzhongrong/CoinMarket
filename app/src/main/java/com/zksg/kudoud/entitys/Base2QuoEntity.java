package com.zksg.kudoud.entitys;

import com.netease.lib_network.entitys.DexScreenTokenInfo;

import java.util.List;

public class Base2QuoEntity {
    private String contract;
    private List<DexScreenTokenInfo.PairsDTO> mPairsDTO;

    public Base2QuoEntity(String contract, List<DexScreenTokenInfo.PairsDTO> mPairsDTO) {
        this.contract = contract;
        this.mPairsDTO = mPairsDTO;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public List<DexScreenTokenInfo.PairsDTO> getmPairsDTO() {
        return mPairsDTO;
    }

    public void setmPairsDTO(List<DexScreenTokenInfo.PairsDTO> mPairsDTO) {
        this.mPairsDTO = mPairsDTO;
    }
}
