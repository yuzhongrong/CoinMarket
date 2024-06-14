package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuoEntity {
    @SerializedName("inputMint")
    private String inputMint;
    @SerializedName("inAmount")
    private String inAmount;
    @SerializedName("outputMint")
    private String outputMint;
    @SerializedName("outAmount")
    private String outAmount;
    @SerializedName("otherAmountThreshold")
    private String otherAmountThreshold;
    @SerializedName("swapMode")
    private String swapMode;
    @SerializedName("slippageBps")
    private int slippageBps;
    @SerializedName("computedAutoSlippage")
    private int computedAutoSlippage;
    @SerializedName("platformFee")
    private PlatformFeeDTO platformFee;
    @SerializedName("priceImpactPct")
    private String priceImpactPct;
    @SerializedName("routePlan")
    private List<RoutePlanDTO> routePlan;
    @SerializedName("contextSlot")
    private int contextSlot;
    @SerializedName("timeTaken")
    private double timeTaken;

    public String getInputMint() {
        return inputMint;
    }

    public void setInputMint(String inputMint) {
        this.inputMint = inputMint;
    }

    public String getInAmount() {
        return inAmount;
    }

    public void setInAmount(String inAmount) {
        this.inAmount = inAmount;
    }

    public String getOutputMint() {
        return outputMint;
    }

    public void setOutputMint(String outputMint) {
        this.outputMint = outputMint;
    }

    public String getOutAmount() {
        return outAmount;
    }

    public void setOutAmount(String outAmount) {
        this.outAmount = outAmount;
    }

    public String getOtherAmountThreshold() {
        return otherAmountThreshold;
    }

    public void setOtherAmountThreshold(String otherAmountThreshold) {
        this.otherAmountThreshold = otherAmountThreshold;
    }

    public String getSwapMode() {
        return swapMode;
    }

    public void setSwapMode(String swapMode) {
        this.swapMode = swapMode;
    }

    public int getSlippageBps() {
        return slippageBps;
    }

    public void setSlippageBps(int slippageBps) {
        this.slippageBps = slippageBps;
    }

    public int getComputedAutoSlippage() {
        return computedAutoSlippage;
    }

    public void setComputedAutoSlippage(int computedAutoSlippage) {
        this.computedAutoSlippage = computedAutoSlippage;
    }

    public PlatformFeeDTO getPlatformFee() {
        return platformFee;
    }

    public void setPlatformFee(PlatformFeeDTO platformFee) {
        this.platformFee = platformFee;
    }

    public String getPriceImpactPct() {
        return priceImpactPct;
    }

    public void setPriceImpactPct(String priceImpactPct) {
        this.priceImpactPct = priceImpactPct;
    }

    public List<RoutePlanDTO> getRoutePlan() {
        return routePlan;
    }

    public void setRoutePlan(List<RoutePlanDTO> routePlan) {
        this.routePlan = routePlan;
    }

    public int getContextSlot() {
        return contextSlot;
    }

    public void setContextSlot(int contextSlot) {
        this.contextSlot = contextSlot;
    }

    public double getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public static class PlatformFeeDTO {
        @SerializedName("amount")
        private String amount;
        @SerializedName("feeBps")
        private int feeBps;

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getFeeBps() {
            return feeBps;
        }

        public void setFeeBps(int feeBps) {
            this.feeBps = feeBps;
        }
    }

    public static class RoutePlanDTO {
        @SerializedName("swapInfo")
        private SwapInfoDTO swapInfo;
        @SerializedName("percent")
        private int percent;

        public SwapInfoDTO getSwapInfo() {
            return swapInfo;
        }

        public void setSwapInfo(SwapInfoDTO swapInfo) {
            this.swapInfo = swapInfo;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public static class SwapInfoDTO {
            @SerializedName("ammKey")
            private String ammKey;
            @SerializedName("label")
            private String label;
            @SerializedName("inputMint")
            private String inputMint;
            @SerializedName("outputMint")
            private String outputMint;
            @SerializedName("inAmount")
            private String inAmount;
            @SerializedName("outAmount")
            private String outAmount;
            @SerializedName("feeAmount")
            private String feeAmount;
            @SerializedName("feeMint")
            private String feeMint;

            public String getAmmKey() {
                return ammKey;
            }

            public void setAmmKey(String ammKey) {
                this.ammKey = ammKey;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getInputMint() {
                return inputMint;
            }

            public void setInputMint(String inputMint) {
                this.inputMint = inputMint;
            }

            public String getOutputMint() {
                return outputMint;
            }

            public void setOutputMint(String outputMint) {
                this.outputMint = outputMint;
            }

            public String getInAmount() {
                return inAmount;
            }

            public void setInAmount(String inAmount) {
                this.inAmount = inAmount;
            }

            public String getOutAmount() {
                return outAmount;
            }

            public void setOutAmount(String outAmount) {
                this.outAmount = outAmount;
            }

            public String getFeeAmount() {
                return feeAmount;
            }

            public void setFeeAmount(String feeAmount) {
                this.feeAmount = feeAmount;
            }

            public String getFeeMint() {
                return feeMint;
            }

            public void setFeeMint(String feeMint) {
                this.feeMint = feeMint;
            }
        }
    }
}
