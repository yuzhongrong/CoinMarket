package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CheckToken implements Serializable {

    @SerializedName("token_contract")
    private TokenContractDTO tokenContract;
    @SerializedName("nft_contract")
    private NftContractDTO nftContract;

    public TokenContractDTO getTokenContract() {
        return tokenContract;
    }

    public void setTokenContract(TokenContractDTO tokenContract) {
        this.tokenContract = tokenContract;
    }

    public NftContractDTO getNftContract() {
        return nftContract;
    }

    public void setNftContract(NftContractDTO nftContract) {
        this.nftContract = nftContract;
    }

    public static class TokenContractDTO {
        @SerializedName("contract_data")
        private ContractDataDTO contractData;
        @SerializedName("vote_support")
        private int voteSupport;
        @SerializedName("vote_against")
        private int voteAgainst;
        @SerializedName("my_vote")
        private int myVote;
        @SerializedName("tx_count")
        private int txCount;
        @SerializedName("daily_activity_addr")
        private int dailyActivityAddr;

        public ContractDataDTO getContractData() {
            return contractData;
        }

        public void setContractData(ContractDataDTO contractData) {
            this.contractData = contractData;
        }

        public int getVoteSupport() {
            return voteSupport;
        }

        public void setVoteSupport(int voteSupport) {
            this.voteSupport = voteSupport;
        }

        public int getVoteAgainst() {
            return voteAgainst;
        }

        public void setVoteAgainst(int voteAgainst) {
            this.voteAgainst = voteAgainst;
        }

        public int getMyVote() {
            return myVote;
        }

        public void setMyVote(int myVote) {
            this.myVote = myVote;
        }

        public int getTxCount() {
            return txCount;
        }

        public void setTxCount(int txCount) {
            this.txCount = txCount;
        }

        public int getDailyActivityAddr() {
            return dailyActivityAddr;
        }

        public void setDailyActivityAddr(int dailyActivityAddr) {
            this.dailyActivityAddr = dailyActivityAddr;
        }

        public static class ContractDataDTO {
            @SerializedName("analysis_big_wallet")
            private String analysisBigWallet;
            @SerializedName("analysis_lp_current_adequate")
            private String analysisLpCurrentAdequate;
            @SerializedName("analysis_lp_current_volume")
            private int analysisLpCurrentVolume;
            @SerializedName("analysis_scam_wallet")
            private String analysisScamWallet;
            @SerializedName("approve_gas")
            private String approveGas;
            @SerializedName("burn_amount")
            private int burnAmount;
            @SerializedName("buy_gas")
            private String buyGas;
            @SerializedName("chain")
            private String chain;
            @SerializedName("data_source")
            private String dataSource;
            @SerializedName("decimal")
            private String decimal;
            @SerializedName("dex")
            private List<DexDTO> dex;
            @SerializedName("err_code")
            private String errCode;
            @SerializedName("err_msg")
            private String errMsg;
            @SerializedName("has_black_method")
            private int hasBlackMethod;
            @SerializedName("has_code")
            private int hasCode;
            @SerializedName("has_mint_method")
            private int hasMintMethod;
            @SerializedName("has_not_burned_lp")
            private int hasNotBurnedLp;
            @SerializedName("has_owner_removed_risk")
            private int hasOwnerRemovedRisk;
            @SerializedName("has_top10_holder_amount_over_30")
            private int hasTop10HolderAmountOver30;
            @SerializedName("holders")
            private int holders;
            @SerializedName("lock_amount")
            private int lockAmount;
            @SerializedName("owner")
            private String owner;
            @SerializedName("pair_holders")
            private int pairHolders;
            @SerializedName("pair_holders_rank")
            private List<PairHoldersRankDTO> pairHoldersRank;
            @SerializedName("pair_lock_percent")
            private double pairLockPercent;
            @SerializedName("pair_total")
            private double pairTotal;
            @SerializedName("query_count")
            private int queryCount;
            @SerializedName("risk_score")
            private int riskScore;
            @SerializedName("sell_gas")
            private String sellGas;
            @SerializedName("token")
            private String token;
            @SerializedName("token_holders_rank")
            private List<TokenHoldersRankDTO> tokenHoldersRank;
            @SerializedName("token_lock_percent")
            private int tokenLockPercent;
            @SerializedName("total")
            private String total;

            public String getAnalysisBigWallet() {
                return analysisBigWallet;
            }

            public void setAnalysisBigWallet(String analysisBigWallet) {
                this.analysisBigWallet = analysisBigWallet;
            }

            public String getAnalysisLpCurrentAdequate() {
                return analysisLpCurrentAdequate;
            }

            public void setAnalysisLpCurrentAdequate(String analysisLpCurrentAdequate) {
                this.analysisLpCurrentAdequate = analysisLpCurrentAdequate;
            }

            public int getAnalysisLpCurrentVolume() {
                return analysisLpCurrentVolume;
            }

            public void setAnalysisLpCurrentVolume(int analysisLpCurrentVolume) {
                this.analysisLpCurrentVolume = analysisLpCurrentVolume;
            }

            public String getAnalysisScamWallet() {
                return analysisScamWallet;
            }

            public void setAnalysisScamWallet(String analysisScamWallet) {
                this.analysisScamWallet = analysisScamWallet;
            }

            public String getApproveGas() {
                return approveGas;
            }

            public void setApproveGas(String approveGas) {
                this.approveGas = approveGas;
            }

            public int getBurnAmount() {
                return burnAmount;
            }

            public void setBurnAmount(int burnAmount) {
                this.burnAmount = burnAmount;
            }

            public String getBuyGas() {
                return buyGas;
            }

            public void setBuyGas(String buyGas) {
                this.buyGas = buyGas;
            }

            public String getChain() {
                return chain;
            }

            public void setChain(String chain) {
                this.chain = chain;
            }

            public String getDataSource() {
                return dataSource;
            }

            public void setDataSource(String dataSource) {
                this.dataSource = dataSource;
            }

            public String getDecimal() {
                return decimal;
            }

            public void setDecimal(String decimal) {
                this.decimal = decimal;
            }

            public List<DexDTO> getDex() {
                return dex;
            }

            public void setDex(List<DexDTO> dex) {
                this.dex = dex;
            }

            public String getErrCode() {
                return errCode;
            }

            public void setErrCode(String errCode) {
                this.errCode = errCode;
            }

            public String getErrMsg() {
                return errMsg;
            }

            public void setErrMsg(String errMsg) {
                this.errMsg = errMsg;
            }

            public int getHasBlackMethod() {
                return hasBlackMethod;
            }

            public void setHasBlackMethod(int hasBlackMethod) {
                this.hasBlackMethod = hasBlackMethod;
            }

            public int getHasCode() {
                return hasCode;
            }

            public void setHasCode(int hasCode) {
                this.hasCode = hasCode;
            }

            public int getHasMintMethod() {
                return hasMintMethod;
            }

            public void setHasMintMethod(int hasMintMethod) {
                this.hasMintMethod = hasMintMethod;
            }

            public int getHasNotBurnedLp() {
                return hasNotBurnedLp;
            }

            public void setHasNotBurnedLp(int hasNotBurnedLp) {
                this.hasNotBurnedLp = hasNotBurnedLp;
            }

            public int getHasOwnerRemovedRisk() {
                return hasOwnerRemovedRisk;
            }

            public void setHasOwnerRemovedRisk(int hasOwnerRemovedRisk) {
                this.hasOwnerRemovedRisk = hasOwnerRemovedRisk;
            }

            public int getHasTop10HolderAmountOver30() {
                return hasTop10HolderAmountOver30;
            }

            public void setHasTop10HolderAmountOver30(int hasTop10HolderAmountOver30) {
                this.hasTop10HolderAmountOver30 = hasTop10HolderAmountOver30;
            }

            public int getHolders() {
                return holders;
            }

            public void setHolders(int holders) {
                this.holders = holders;
            }

            public int getLockAmount() {
                return lockAmount;
            }

            public void setLockAmount(int lockAmount) {
                this.lockAmount = lockAmount;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public int getPairHolders() {
                return pairHolders;
            }

            public void setPairHolders(int pairHolders) {
                this.pairHolders = pairHolders;
            }

            public List<PairHoldersRankDTO> getPairHoldersRank() {
                return pairHoldersRank;
            }

            public void setPairHoldersRank(List<PairHoldersRankDTO> pairHoldersRank) {
                this.pairHoldersRank = pairHoldersRank;
            }

            public double getPairLockPercent() {
                return pairLockPercent;
            }

            public void setPairLockPercent(double pairLockPercent) {
                this.pairLockPercent = pairLockPercent;
            }

            public double getPairTotal() {
                return pairTotal;
            }

            public void setPairTotal(double pairTotal) {
                this.pairTotal = pairTotal;
            }

            public int getQueryCount() {
                return queryCount;
            }

            public void setQueryCount(int queryCount) {
                this.queryCount = queryCount;
            }

            public int getRiskScore() {
                return riskScore;
            }

            public void setRiskScore(int riskScore) {
                this.riskScore = riskScore;
            }

            public String getSellGas() {
                return sellGas;
            }

            public void setSellGas(String sellGas) {
                this.sellGas = sellGas;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public List<TokenHoldersRankDTO> getTokenHoldersRank() {
                return tokenHoldersRank;
            }

            public void setTokenHoldersRank(List<TokenHoldersRankDTO> tokenHoldersRank) {
                this.tokenHoldersRank = tokenHoldersRank;
            }

            public int getTokenLockPercent() {
                return tokenLockPercent;
            }

            public void setTokenLockPercent(int tokenLockPercent) {
                this.tokenLockPercent = tokenLockPercent;
            }

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
            }

            public static class DexDTO {
                @SerializedName("amm")
                private String amm;
                @SerializedName("liquidity")
                private String liquidity;
                @SerializedName("name")
                private String name;
                @SerializedName("pair")
                private String pair;

                public String getAmm() {
                    return amm;
                }

                public void setAmm(String amm) {
                    this.amm = amm;
                }

                public String getLiquidity() {
                    return liquidity;
                }

                public void setLiquidity(String liquidity) {
                    this.liquidity = liquidity;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getPair() {
                    return pair;
                }

                public void setPair(String pair) {
                    this.pair = pair;
                }
            }

            public static class PairHoldersRankDTO {
                @SerializedName("address")
                private String address;
                @SerializedName("lock")
                private List<?> lock;
                @SerializedName("mark")
                private String mark;
                @SerializedName("percent")
                private String percent;
                @SerializedName("quantity")
                private String quantity;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public List<?> getLock() {
                    return lock;
                }

                public void setLock(List<?> lock) {
                    this.lock = lock;
                }

                public String getMark() {
                    return mark;
                }

                public void setMark(String mark) {
                    this.mark = mark;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }
            }

            public static class TokenHoldersRankDTO {
                @SerializedName("address")
                private String address;
                @SerializedName("is_contract")
                private int isContract;
                @SerializedName("is_lp")
                private int isLp;
                @SerializedName("mark")
                private String mark;
                @SerializedName("percent")
                private String percent;
                @SerializedName("quantity")
                private String quantity;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getIsContract() {
                    return isContract;
                }

                public void setIsContract(int isContract) {
                    this.isContract = isContract;
                }

                public int getIsLp() {
                    return isLp;
                }

                public void setIsLp(int isLp) {
                    this.isLp = isLp;
                }

                public String getMark() {
                    return mark;
                }

                public void setMark(String mark) {
                    this.mark = mark;
                }

                public String getPercent() {
                    return percent;
                }

                public void setPercent(String percent) {
                    this.percent = percent;
                }

                public String getQuantity() {
                    return quantity;
                }

                public void setQuantity(String quantity) {
                    this.quantity = quantity;
                }
            }
        }
    }

    public static class NftContractDTO {
        @SerializedName("contract_data")
        private Object contractData;
        @SerializedName("vote_support")
        private int voteSupport;
        @SerializedName("vote_against")
        private int voteAgainst;
        @SerializedName("my_vote")
        private int myVote;

        public Object getContractData() {
            return contractData;
        }

        public void setContractData(Object contractData) {
            this.contractData = contractData;
        }

        public int getVoteSupport() {
            return voteSupport;
        }

        public void setVoteSupport(int voteSupport) {
            this.voteSupport = voteSupport;
        }

        public int getVoteAgainst() {
            return voteAgainst;
        }

        public void setVoteAgainst(int voteAgainst) {
            this.voteAgainst = voteAgainst;
        }

        public int getMyVote() {
            return myVote;
        }

        public void setMyVote(int myVote) {
            this.myVote = myVote;
        }
    }
}
