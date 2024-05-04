/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc.types;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 
 */
public class RpcTokenAccount extends RpcResultObject {

    /**  */
    private List<Value> value;

    /**
     * 
     *
     * @return 
     */
    public List<Value> getValue() {
        return value;
    }

    /**
     * 
     *
     * @param value 
     */
    public void setValue(List<Value> value) {
        this.value = value;
    }
    
    /**
     * 
     *
     * @return 
     */
    public String getAddress() {
        return Optional.ofNullable(getValue())//
                .orElse(new ArrayList<>())//
                .stream()//
                .findFirst()//
                .map(Value::getPubkey)//
                .orElse(null);
    }

    /**
     * 
     */
    public static class Value {
        @SerializedName("account")
        private AccountDTO account;
        @SerializedName("pubkey")
        private String pubkey;

        public AccountDTO getAccount() {
            return account;
        }

        public void setAccount(AccountDTO account) {
            this.account = account;
        }

        public String getPubkey() {
            return pubkey;
        }

        public void setPubkey(String pubkey) {
            this.pubkey = pubkey;
        }

        public static class AccountDTO {
            @SerializedName("data")
            private DataDTO data;
            @SerializedName("executable")
            private boolean executable;
            @SerializedName("lamports")
            private int lamports;
            @SerializedName("owner")
            private String owner;
            @SerializedName("rentEpoch")
            private String rentEpoch;
            @SerializedName("space")
            private int space;

            public DataDTO getData() {
                return data;
            }

            public void setData(DataDTO data) {
                this.data = data;
            }

            public boolean isExecutable() {
                return executable;
            }

            public void setExecutable(boolean executable) {
                this.executable = executable;
            }

            public int getLamports() {
                return lamports;
            }

            public void setLamports(int lamports) {
                this.lamports = lamports;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public String getRentEpoch() {
                return rentEpoch;
            }

            public void setRentEpoch(String rentEpoch) {
                this.rentEpoch = rentEpoch;
            }

            public int getSpace() {
                return space;
            }

            public void setSpace(int space) {
                this.space = space;
            }

            public static class DataDTO {
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
        }

    }

}

//[
// {
//    "account":{
//       "data":{
//          "parsed":{
//             "info":{
//                "isNative":false,
//                "mint":"4zMMC9srt5Ri5X14GAgXhaHii3GnPAEERYPJgZJDncDU",
//                "owner":"7zVnNnnzWuCGMpULLZYq4nG7zzNcarLcfJJxdeDAcNPM",
//                "state":"initialized",
//                "tokenAmount":{
//                   "amount":"0",
//                   "decimals":6,
//                   "uiAmount":0.0,
//                   "uiAmountString":"0"
//                }
//             },
//             "type":"account"
//          },
//          "program":"spl-token",
//          "space":165
//       },
//       "executable":false,
//       "lamports":2039280,
//       "owner":"TokenkegQfeZyiNwAJbNbGKPFXCWuBvf9Ss623VQ5DA",
//       "rentEpoch":319
//    },
//    "pubkey":"22b2P8j2WBmcEbAHXZP2S7cxMKpfBQyh1m2gB1wNPZ8e"
// }
//]
