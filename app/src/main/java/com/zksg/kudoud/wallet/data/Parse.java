package com.zksg.kudoud.wallet.data;

import com.google.gson.annotations.SerializedName;

public class Parse {


    @SerializedName("parsed")
    private ParsedDTO parsed;
    @SerializedName("program")
    private String program;
    @SerializedName("space")
    private int space;

    public ParsedDTO getParsed() {
        return parsed;
    }

    public void setParsed(ParsedDTO parsed) {
        this.parsed = parsed;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public static class ParsedDTO {
        @SerializedName("info")
        private InfoDTO info;
        @SerializedName("type")
        private String type;

        public InfoDTO getInfo() {
            return info;
        }

        public void setInfo(InfoDTO info) {
            this.info = info;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public static class InfoDTO {
            @SerializedName("isNative")
            private boolean isNative;
            @SerializedName("mint")
            private String mint;
            @SerializedName("owner")
            private String owner;
            @SerializedName("state")
            private String state;
            @SerializedName("tokenAmount")
            private TokenAmountDTO tokenAmount;

            public boolean isIsNative() {
                return isNative;
            }

            public void setIsNative(boolean isNative) {
                this.isNative = isNative;
            }

            public String getMint() {
                return mint;
            }

            public void setMint(String mint) {
                this.mint = mint;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public TokenAmountDTO getTokenAmount() {
                return tokenAmount;
            }

            public void setTokenAmount(TokenAmountDTO tokenAmount) {
                this.tokenAmount = tokenAmount;
            }

            public static class TokenAmountDTO {
                @SerializedName("amount")
                private String amount;
                @SerializedName("decimals")
                private int decimals;
                @SerializedName("uiAmount")
                private double uiAmount;
                @SerializedName("uiAmountString")
                private String uiAmountString;

                public String getAmount() {
                    return amount;
                }

                public void setAmount(String amount) {
                    this.amount = amount;
                }

                public int getDecimals() {
                    return decimals;
                }

                public void setDecimals(int decimals) {
                    this.decimals = decimals;
                }

                public double getUiAmount() {
                    return uiAmount;
                }

                public void setUiAmount(double uiAmount) {
                    this.uiAmount = uiAmount;
                }

                public String getUiAmountString() {
                    return uiAmountString;
                }

                public void setUiAmountString(String uiAmountString) {
                    this.uiAmountString = uiAmountString;
                }
            }
        }
    }
}
