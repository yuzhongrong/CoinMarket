package com.netease.lib_network.entitys;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DexScreenTokenInfo1 implements Serializable {


    @SerializedName("schemaVersion")
    private String schemaVersion;
    @SerializedName("pairs")
    private List<PairsDTO> pairs;

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public List<PairsDTO> getPairs() {
        return pairs;
    }

    public void setPairs(List<PairsDTO> pairs) {
        this.pairs = pairs;
    }

    public static class PairsDTO implements Serializable{
        @SerializedName("chainId")
        private String chainId;
        @SerializedName("dexId")
        private String dexId;
        @SerializedName("url")
        private String url;
        @SerializedName("pairAddress")
        private String pairAddress;
        @SerializedName("baseToken")
        private BaseTokenDTO baseToken;
        @SerializedName("quoteToken")
        private QuoteTokenDTO quoteToken;
        @SerializedName("priceNative")
        private String priceNative;
        @SerializedName("priceUsd")
        private String priceUsd;
        @SerializedName("txns")
        private TxnsDTO txns;
        @SerializedName("volume")
        private VolumeDTO volume;
        @SerializedName("priceChange")
        private PriceChangeDTO priceChange;
        @SerializedName("liquidity")
        private LiquidityDTO liquidity;
        @SerializedName("fdv")
        private int fdv;
        @SerializedName("pairCreatedAt")
        private long pairCreatedAt;
        @SerializedName("info")
        private InfoDTO info;

        public String getChainId() {
            return chainId;
        }

        public void setChainId(String chainId) {
            this.chainId = chainId;
        }

        public String getDexId() {
            return dexId;
        }

        public void setDexId(String dexId) {
            this.dexId = dexId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getPairAddress() {
            return pairAddress;
        }

        public void setPairAddress(String pairAddress) {
            this.pairAddress = pairAddress;
        }

        public BaseTokenDTO getBaseToken() {
            return baseToken;
        }

        public void setBaseToken(BaseTokenDTO baseToken) {
            this.baseToken = baseToken;
        }

        public QuoteTokenDTO getQuoteToken() {
            return quoteToken;
        }

        public void setQuoteToken(QuoteTokenDTO quoteToken) {
            this.quoteToken = quoteToken;
        }

        public String getPriceNative() {
            return priceNative;
        }

        public void setPriceNative(String priceNative) {
            this.priceNative = priceNative;
        }

        public String getPriceUsd() {
            return priceUsd;
        }

        public void setPriceUsd(String priceUsd) {
            this.priceUsd = priceUsd;
        }

        public TxnsDTO getTxns() {
            return txns;
        }

        public void setTxns(TxnsDTO txns) {
            this.txns = txns;
        }

        public VolumeDTO getVolume() {
            return volume;
        }

        public void setVolume(VolumeDTO volume) {
            this.volume = volume;
        }

        public PriceChangeDTO getPriceChange() {
            return priceChange;
        }

        public void setPriceChange(PriceChangeDTO priceChange) {
            this.priceChange = priceChange;
        }

        public LiquidityDTO getLiquidity() {
            return liquidity;
        }

        public void setLiquidity(LiquidityDTO liquidity) {
            this.liquidity = liquidity;
        }

        public int getFdv() {
            return fdv;
        }

        public void setFdv(int fdv) {
            this.fdv = fdv;
        }

        public long getPairCreatedAt() {
            return pairCreatedAt;
        }

        public void setPairCreatedAt(long pairCreatedAt) {
            this.pairCreatedAt = pairCreatedAt;
        }

        public InfoDTO getInfo() {
            return info;
        }

        public void setInfo(InfoDTO info) {
            this.info = info;
        }

        public static class BaseTokenDTO {
            @SerializedName("address")
            private String address;
            @SerializedName("name")
            private String name;
            @SerializedName("symbol")
            private String symbol;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }
        }

        public static class QuoteTokenDTO {
            @SerializedName("address")
            private String address;
            @SerializedName("name")
            private String name;
            @SerializedName("symbol")
            private String symbol;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSymbol() {
                return symbol;
            }

            public void setSymbol(String symbol) {
                this.symbol = symbol;
            }
        }

        public static class TxnsDTO {
            @SerializedName("m5")
            private M5DTO m5;
            @SerializedName("h1")
            private H1DTO h1;
            @SerializedName("h6")
            private H6DTO h6;
            @SerializedName("h24")
            private H24DTO h24;

            public M5DTO getM5() {
                return m5;
            }

            public void setM5(M5DTO m5) {
                this.m5 = m5;
            }

            public H1DTO getH1() {
                return h1;
            }

            public void setH1(H1DTO h1) {
                this.h1 = h1;
            }

            public H6DTO getH6() {
                return h6;
            }

            public void setH6(H6DTO h6) {
                this.h6 = h6;
            }

            public H24DTO getH24() {
                return h24;
            }

            public void setH24(H24DTO h24) {
                this.h24 = h24;
            }

            public static class M5DTO {
                @SerializedName("buys")
                private int buys;
                @SerializedName("sells")
                private int sells;

                public int getBuys() {
                    return buys;
                }

                public void setBuys(int buys) {
                    this.buys = buys;
                }

                public int getSells() {
                    return sells;
                }

                public void setSells(int sells) {
                    this.sells = sells;
                }
            }

            public static class H1DTO {
                @SerializedName("buys")
                private int buys;
                @SerializedName("sells")
                private int sells;

                public int getBuys() {
                    return buys;
                }

                public void setBuys(int buys) {
                    this.buys = buys;
                }

                public int getSells() {
                    return sells;
                }

                public void setSells(int sells) {
                    this.sells = sells;
                }
            }

            public static class H6DTO {
                @SerializedName("buys")
                private int buys;
                @SerializedName("sells")
                private int sells;

                public int getBuys() {
                    return buys;
                }

                public void setBuys(int buys) {
                    this.buys = buys;
                }

                public int getSells() {
                    return sells;
                }

                public void setSells(int sells) {
                    this.sells = sells;
                }
            }

            public static class H24DTO {
                @SerializedName("buys")
                private int buys;
                @SerializedName("sells")
                private int sells;

                public int getBuys() {
                    return buys;
                }

                public void setBuys(int buys) {
                    this.buys = buys;
                }

                public int getSells() {
                    return sells;
                }

                public void setSells(int sells) {
                    this.sells = sells;
                }
            }
        }

        public static class VolumeDTO {
            @SerializedName("h24")
            private double h24;
            @SerializedName("h6")
            private double h6;
            @SerializedName("h1")
            private double h1;
            @SerializedName("m5")
            private double m5;

            public double getH24() {
                return h24;
            }

            public void setH24(double h24) {
                this.h24 = h24;
            }

            public double getH6() {
                return h6;
            }

            public void setH6(double h6) {
                this.h6 = h6;
            }

            public double getH1() {
                return h1;
            }

            public void setH1(double h1) {
                this.h1 = h1;
            }

            public double getM5() {
                return m5;
            }

            public void setM5(double m5) {
                this.m5 = m5;
            }
        }

        public static class PriceChangeDTO {
            @SerializedName("m5")
            private double m5;
            @SerializedName("h1")
            private double h1;
            @SerializedName("h6")
            private double h6;
            @SerializedName("h24")
            private double h24;

            public double getM5() {
                return m5;
            }

            public void setM5(double m5) {
                this.m5 = m5;
            }

            public double getH1() {
                return h1;
            }

            public void setH1(double h1) {
                this.h1 = h1;
            }

            public double getH6() {
                return h6;
            }

            public void setH6(double h6) {
                this.h6 = h6;
            }

            public double getH24() {
                return h24;
            }

            public void setH24(double h24) {
                this.h24 = h24;
            }
        }

        public static class LiquidityDTO {
            @SerializedName("usd")
            private double usd;
            @SerializedName("base")
            private double base;
            @SerializedName("quote")
            private double quote;

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }

            public double getBase() {
                return base;
            }

            public void setBase(double base) {
                this.base = base;
            }

            public double getQuote() {
                return quote;
            }

            public void setQuote(double quote) {
                this.quote = quote;
            }
        }

        public static class InfoDTO {
            @SerializedName("imageUrl")
            private String imageUrl;
            @SerializedName("websites")
            private List<WebsitesDTO> websites;
            @SerializedName("socials")
            private List<SocialsDTO> socials;

            public String getImageUrl() {
                return imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public List<WebsitesDTO> getWebsites() {
                return websites;
            }

            public void setWebsites(List<WebsitesDTO> websites) {
                this.websites = websites;
            }

            public List<SocialsDTO> getSocials() {
                return socials;
            }

            public void setSocials(List<SocialsDTO> socials) {
                this.socials = socials;
            }

            public static class WebsitesDTO {
                @SerializedName("label")
                private String label;
                @SerializedName("url")
                private String url;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class SocialsDTO {
                @SerializedName("type")
                private String type;
                @SerializedName("url")
                private String url;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }
    }
}
