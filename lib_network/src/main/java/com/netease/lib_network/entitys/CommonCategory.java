package com.netease.lib_network.entitys;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CommonCategory implements Serializable {


    @SerializedName("total")
    private int total;
    @SerializedName("pageNO")
    private int pageNO;
    @SerializedName("pageSize")
    private int pageSize;
    @SerializedName("data")
    private List<DataDTO> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNO() {
        return pageNO;
    }

    public void setPageNO(int pageNO) {
        this.pageNO = pageNO;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO  {
        @SerializedName("pair")
        private String pair;
        @SerializedName("chain")
        private String chain;
        @SerializedName("amm")
        private String amm;
        @SerializedName("target_token")
        private String targetToken;
        @SerializedName("token0_address")
        private String token0Address;
        @SerializedName("token0_symbol")
        private String token0Symbol;
        @SerializedName("reserve0")
        private double reserve0;
        @SerializedName("token0_logo_url")
        private String token0LogoUrl;
        @SerializedName("token1_address")
        private String token1Address;
        @SerializedName("token1_symbol")
        private String token1Symbol;
        @SerializedName("reserve1")
        private double reserve1;
        @SerializedName("token1_logo_url")
        private String token1LogoUrl;
        @SerializedName("init_reserve0")
        private double initReserve0;
        @SerializedName("init_reserve1")
        private double initReserve1;
        @SerializedName("tvl")
        private double tvl;
        @SerializedName("init_tvl")
        private double initTvl;
        @SerializedName("tvl_ratio")
        private double tvlRatio;
        @SerializedName("current_price_usd")
        private double currentPriceUsd;
        @SerializedName("lp_holders")
        private int lpHolders;
        @SerializedName("lp_locked_percent")
        private double lpLockedPercent;
        @SerializedName("lp_locked_to")
        private String lpLockedTo;
        @SerializedName("lp_lock_platform")
        private String lpLockPlatform;
        @SerializedName("price_change_1m")
        private double priceChange1m;
        @SerializedName("price_change_5m")
        private double priceChange5m;
        @SerializedName("price_change_15m")
        private double priceChange15m;
        @SerializedName("price_change_30m")
        private double priceChange30m;
        @SerializedName("price_change_1h")
        private double priceChange1h;
        @SerializedName("price_change_4h")
        private double priceChange4h;
        @SerializedName("price_change_24h")
        private double priceChange24h;
        @SerializedName("tx_1m_count")
        private int tx1mCount;
        @SerializedName("tx_5m_count")
        private int tx5mCount;
        @SerializedName("tx_15m_count")
        private int tx15mCount;
        @SerializedName("tx_30m_count")
        private int tx30mCount;
        @SerializedName("tx_1h_count")
        private int tx1hCount;
        @SerializedName("tx_4h_count")
        private int tx4hCount;
        @SerializedName("tx_24h_count")
        private int tx24hCount;
        @SerializedName("buys_tx_1m_count")
        private int buysTx1mCount;
        @SerializedName("buys_tx_5m_count")
        private int buysTx5mCount;
        @SerializedName("buys_tx_15m_count")
        private int buysTx15mCount;
        @SerializedName("buys_tx_30m_count")
        private int buysTx30mCount;
        @SerializedName("buys_tx_1h_count")
        private int buysTx1hCount;
        @SerializedName("buys_tx_4h_count")
        private int buysTx4hCount;
        @SerializedName("buys_tx_24h_count")
        private int buysTx24hCount;
        @SerializedName("sells_tx_1m_count")
        private int sellsTx1mCount;
        @SerializedName("sells_tx_5m_count")
        private int sellsTx5mCount;
        @SerializedName("sells_tx_15m_count")
        private int sellsTx15mCount;
        @SerializedName("sells_tx_30m_count")
        private int sellsTx30mCount;
        @SerializedName("sells_tx_1h_count")
        private int sellsTx1hCount;
        @SerializedName("sells_tx_4h_count")
        private int sellsTx4hCount;
        @SerializedName("sells_tx_24h_count")
        private int sellsTx24hCount;
        @SerializedName("volume_u_1m")
        private double volumeU1m;
        @SerializedName("volume_u_5m")
        private double volumeU5m;
        @SerializedName("volume_u_15m")
        private double volumeU15m;
        @SerializedName("volume_u_30m")
        private double volumeU30m;
        @SerializedName("volume_u_1h")
        private double volumeU1h;
        @SerializedName("volume_u_4h")
        private double volumeU4h;
        @SerializedName("volume_u_24h")
        private double volumeU24h;
        @SerializedName("buy_volume_u_1m")
        private double buyVolumeU1m;
        @SerializedName("buy_volume_u_5m")
        private double buyVolumeU5m;
        @SerializedName("buy_volume_u_15m")
        private double buyVolumeU15m;
        @SerializedName("buy_volume_u_30m")
        private double buyVolumeU30m;
        @SerializedName("buy_volume_u_1h")
        private double buyVolumeU1h;
        @SerializedName("buy_volume_u_4h")
        private double buyVolumeU4h;
        @SerializedName("buy_volume_u_24h")
        private double buyVolumeU24h;
        @SerializedName("sell_volume_u_1m")
        private double sellVolumeU1m;
        @SerializedName("sell_volume_u_5m")
        private double sellVolumeU5m;
        @SerializedName("sell_volume_u_15m")
        private double sellVolumeU15m;
        @SerializedName("sell_volume_u_30m")
        private double sellVolumeU30m;
        @SerializedName("sell_volume_u_1h")
        private double sellVolumeU1h;
        @SerializedName("sell_volume_u_4h")
        private double sellVolumeU4h;
        @SerializedName("sell_volume_u_24h")
        private double sellVolumeU24h;
        @SerializedName("makers_1m")
        private int makers1m;
        @SerializedName("makers_5m")
        private int makers5m;
        @SerializedName("makers_15m")
        private int makers15m;
        @SerializedName("makers_30m")
        private int makers30m;
        @SerializedName("makers_1h")
        private int makers1h;
        @SerializedName("makers_4h")
        private int makers4h;
        @SerializedName("makers_24h")
        private int makers24h;
        @SerializedName("buyers_1m")
        private int buyers1m;
        @SerializedName("buyers_5m")
        private int buyers5m;
        @SerializedName("buyers_15m")
        private int buyers15m;
        @SerializedName("buyers_30m")
        private int buyers30m;
        @SerializedName("buyers_1h")
        private int buyers1h;
        @SerializedName("buyers_4h")
        private int buyers4h;
        @SerializedName("buyers_24h")
        private int buyers24h;
        @SerializedName("sellers_1m")
        private int sellers1m;
        @SerializedName("sellers_5m")
        private int sellers5m;
        @SerializedName("sellers_15m")
        private int sellers15m;
        @SerializedName("sellers_30m")
        private int sellers30m;
        @SerializedName("sellers_1h")
        private int sellers1h;
        @SerializedName("sellers_4h")
        private int sellers4h;
        @SerializedName("sellers_24h")
        private int sellers24h;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("sniper_tx_count")
        private int sniperTxCount;
        @SerializedName("rusher_tx_count")
        private int rusherTxCount;
        @SerializedName("last_trade_at")
        private String lastTradeAt;
        @SerializedName("dynamic_tag")
        private String dynamicTag;
        @SerializedName("tag")
        private String tag;
        @SerializedName("market_cap")
        private double marketCap;
        @SerializedName("market_cap_diff")
        private int marketCapDiff;
        @SerializedName("holders")
        private int holders;
        @SerializedName("holders_diff")
        private int holdersDiff;
        @SerializedName("risk_score")
        private int riskScore;
        @SerializedName("risk_level")
        private int riskLevel;
        @SerializedName("appendix")
        private String appendix;
        @SerializedName("smart_money_buy_count_24h")
        private int smartMoneyBuyCount24h;
        @SerializedName("smart_money_sell_count_24h")
        private int smartMoneySellCount24h;
        @SerializedName("holders_top10_ratio")
        private double holdersTop10Ratio;
        @SerializedName("dev_balance_ratio_cur")
        private double devBalanceRatioCur;
        @SerializedName("insider_balance_ratio_cur")
        private double insiderBalanceRatioCur;
        @SerializedName("sniper_balance_ratio_cur")
        private double sniperBalanceRatioCur;
        @SerializedName("reply_count")
        private int replyCount;
        @SerializedName("progress")
        private double progress;
        @SerializedName("first_half_elapsed_time")
        private double firstHalfElapsedTime;
        @SerializedName("second_half_elapsed_time")
        private double secondHalfElapsedTime;
        @SerializedName("winner_count")
        private int winnerCount;
        @SerializedName("winner_ratio")
        private double winnerRatio;
        @SerializedName("up_count_7d")
        private int upCount7d;
        @SerializedName("up_count_14d")
        private int upCount14d;
        @SerializedName("up_seq")
        private String upSeq;
        @SerializedName("bigorder_cnt_1h")
        private int bigorderCnt1h;
        @SerializedName("bigorder_buy_cnt_1h")
        private int bigorderBuyCnt1h;
        @SerializedName("bigorder_sell_cnt_1h")
        private int bigorderSellCnt1h;
        @SerializedName("has_broken_issue_price")
        private boolean hasBrokenIssuePrice;
        @SerializedName("listing_at")
        private int listingAt;

        public String getPair() {
            return pair;
        }

        public void setPair(String pair) {
            this.pair = pair;
        }

        public String getChain() {
            return chain;
        }

        public void setChain(String chain) {
            this.chain = chain;
        }

        public String getAmm() {
            return amm;
        }

        public void setAmm(String amm) {
            this.amm = amm;
        }

        public String getTargetToken() {
            return targetToken;
        }

        public void setTargetToken(String targetToken) {
            this.targetToken = targetToken;
        }

        public String getToken0Address() {
            return token0Address;
        }

        public void setToken0Address(String token0Address) {
            this.token0Address = token0Address;
        }

        public String getToken0Symbol() {
            return token0Symbol;
        }

        public void setToken0Symbol(String token0Symbol) {
            this.token0Symbol = token0Symbol;
        }

        public double getReserve0() {
            return reserve0;
        }

        public void setReserve0(double reserve0) {
            this.reserve0 = reserve0;
        }

        public String getToken0LogoUrl() {
            return token0LogoUrl;
        }

        public void setToken0LogoUrl(String token0LogoUrl) {
            this.token0LogoUrl = token0LogoUrl;
        }

        public String getToken1Address() {
            return token1Address;
        }

        public void setToken1Address(String token1Address) {
            this.token1Address = token1Address;
        }

        public String getToken1Symbol() {
            return token1Symbol;
        }

        public void setToken1Symbol(String token1Symbol) {
            this.token1Symbol = token1Symbol;
        }

        public double getReserve1() {
            return reserve1;
        }

        public void setReserve1(double reserve1) {
            this.reserve1 = reserve1;
        }

        public String getToken1LogoUrl() {
            return token1LogoUrl;
        }

        public void setToken1LogoUrl(String token1LogoUrl) {
            this.token1LogoUrl = token1LogoUrl;
        }

        public double getInitReserve0() {
            return initReserve0;
        }

        public void setInitReserve0(double initReserve0) {
            this.initReserve0 = initReserve0;
        }

        public double getInitReserve1() {
            return initReserve1;
        }

        public void setInitReserve1(double initReserve1) {
            this.initReserve1 = initReserve1;
        }

        public double getTvl() {
            return tvl;
        }

        public void setTvl(double tvl) {
            this.tvl = tvl;
        }

        public double getInitTvl() {
            return initTvl;
        }

        public void setInitTvl(double initTvl) {
            this.initTvl = initTvl;
        }

        public double getTvlRatio() {
            return tvlRatio;
        }

        public void setTvlRatio(double tvlRatio) {
            this.tvlRatio = tvlRatio;
        }

        public double getCurrentPriceUsd() {
            return currentPriceUsd;
        }

        public void setCurrentPriceUsd(double currentPriceUsd) {
            this.currentPriceUsd = currentPriceUsd;
        }

        public int getLpHolders() {
            return lpHolders;
        }

        public void setLpHolders(int lpHolders) {
            this.lpHolders = lpHolders;
        }

        public double getLpLockedPercent() {
            return lpLockedPercent;
        }

        public void setLpLockedPercent(int lpLockedPercent) {
            this.lpLockedPercent = lpLockedPercent;
        }

        public String getLpLockedTo() {
            return lpLockedTo;
        }

        public void setLpLockedTo(String lpLockedTo) {
            this.lpLockedTo = lpLockedTo;
        }

        public String getLpLockPlatform() {
            return lpLockPlatform;
        }

        public void setLpLockPlatform(String lpLockPlatform) {
            this.lpLockPlatform = lpLockPlatform;
        }

        public double getPriceChange1m() {
            return priceChange1m;
        }

        public void setPriceChange1m(double priceChange1m) {
            this.priceChange1m = priceChange1m;
        }

        public double getPriceChange5m() {
            return priceChange5m;
        }

        public void setPriceChange5m(double priceChange5m) {
            this.priceChange5m = priceChange5m;
        }

        public double getPriceChange15m() {
            return priceChange15m;
        }

        public void setPriceChange15m(double priceChange15m) {
            this.priceChange15m = priceChange15m;
        }

        public double getPriceChange30m() {
            return priceChange30m;
        }

        public void setPriceChange30m(double priceChange30m) {
            this.priceChange30m = priceChange30m;
        }

        public double getPriceChange1h() {
            return priceChange1h;
        }

        public void setPriceChange1h(double priceChange1h) {
            this.priceChange1h = priceChange1h;
        }

        public double getPriceChange4h() {
            return priceChange4h;
        }

        public void setPriceChange4h(double priceChange4h) {
            this.priceChange4h = priceChange4h;
        }

        public double getPriceChange24h() {
            return priceChange24h;
        }

        public void setPriceChange24h(double priceChange24h) {
            this.priceChange24h = priceChange24h;
        }

        public int getTx1mCount() {
            return tx1mCount;
        }

        public void setTx1mCount(int tx1mCount) {
            this.tx1mCount = tx1mCount;
        }

        public int getTx5mCount() {
            return tx5mCount;
        }

        public void setTx5mCount(int tx5mCount) {
            this.tx5mCount = tx5mCount;
        }

        public int getTx15mCount() {
            return tx15mCount;
        }

        public void setTx15mCount(int tx15mCount) {
            this.tx15mCount = tx15mCount;
        }

        public int getTx30mCount() {
            return tx30mCount;
        }

        public void setTx30mCount(int tx30mCount) {
            this.tx30mCount = tx30mCount;
        }

        public int getTx1hCount() {
            return tx1hCount;
        }

        public void setTx1hCount(int tx1hCount) {
            this.tx1hCount = tx1hCount;
        }

        public int getTx4hCount() {
            return tx4hCount;
        }

        public void setTx4hCount(int tx4hCount) {
            this.tx4hCount = tx4hCount;
        }

        public int getTx24hCount() {
            return tx24hCount;
        }

        public void setTx24hCount(int tx24hCount) {
            this.tx24hCount = tx24hCount;
        }

        public int getBuysTx1mCount() {
            return buysTx1mCount;
        }

        public void setBuysTx1mCount(int buysTx1mCount) {
            this.buysTx1mCount = buysTx1mCount;
        }

        public int getBuysTx5mCount() {
            return buysTx5mCount;
        }

        public void setBuysTx5mCount(int buysTx5mCount) {
            this.buysTx5mCount = buysTx5mCount;
        }

        public int getBuysTx15mCount() {
            return buysTx15mCount;
        }

        public void setBuysTx15mCount(int buysTx15mCount) {
            this.buysTx15mCount = buysTx15mCount;
        }

        public int getBuysTx30mCount() {
            return buysTx30mCount;
        }

        public void setBuysTx30mCount(int buysTx30mCount) {
            this.buysTx30mCount = buysTx30mCount;
        }

        public int getBuysTx1hCount() {
            return buysTx1hCount;
        }

        public void setBuysTx1hCount(int buysTx1hCount) {
            this.buysTx1hCount = buysTx1hCount;
        }

        public int getBuysTx4hCount() {
            return buysTx4hCount;
        }

        public void setBuysTx4hCount(int buysTx4hCount) {
            this.buysTx4hCount = buysTx4hCount;
        }

        public int getBuysTx24hCount() {
            return buysTx24hCount;
        }

        public void setBuysTx24hCount(int buysTx24hCount) {
            this.buysTx24hCount = buysTx24hCount;
        }

        public int getSellsTx1mCount() {
            return sellsTx1mCount;
        }

        public void setSellsTx1mCount(int sellsTx1mCount) {
            this.sellsTx1mCount = sellsTx1mCount;
        }

        public int getSellsTx5mCount() {
            return sellsTx5mCount;
        }

        public void setSellsTx5mCount(int sellsTx5mCount) {
            this.sellsTx5mCount = sellsTx5mCount;
        }

        public int getSellsTx15mCount() {
            return sellsTx15mCount;
        }

        public void setSellsTx15mCount(int sellsTx15mCount) {
            this.sellsTx15mCount = sellsTx15mCount;
        }

        public int getSellsTx30mCount() {
            return sellsTx30mCount;
        }

        public void setSellsTx30mCount(int sellsTx30mCount) {
            this.sellsTx30mCount = sellsTx30mCount;
        }

        public int getSellsTx1hCount() {
            return sellsTx1hCount;
        }

        public void setSellsTx1hCount(int sellsTx1hCount) {
            this.sellsTx1hCount = sellsTx1hCount;
        }

        public int getSellsTx4hCount() {
            return sellsTx4hCount;
        }

        public void setSellsTx4hCount(int sellsTx4hCount) {
            this.sellsTx4hCount = sellsTx4hCount;
        }

        public int getSellsTx24hCount() {
            return sellsTx24hCount;
        }

        public void setSellsTx24hCount(int sellsTx24hCount) {
            this.sellsTx24hCount = sellsTx24hCount;
        }

        public double getVolumeU1m() {
            return volumeU1m;
        }

        public void setVolumeU1m(double volumeU1m) {
            this.volumeU1m = volumeU1m;
        }

        public double getVolumeU5m() {
            return volumeU5m;
        }

        public void setVolumeU5m(double volumeU5m) {
            this.volumeU5m = volumeU5m;
        }

        public double getVolumeU15m() {
            return volumeU15m;
        }

        public void setVolumeU15m(double volumeU15m) {
            this.volumeU15m = volumeU15m;
        }

        public double getVolumeU30m() {
            return volumeU30m;
        }

        public void setVolumeU30m(double volumeU30m) {
            this.volumeU30m = volumeU30m;
        }

        public double getVolumeU1h() {
            return volumeU1h;
        }

        public void setVolumeU1h(double volumeU1h) {
            this.volumeU1h = volumeU1h;
        }

        public double getVolumeU4h() {
            return volumeU4h;
        }

        public void setVolumeU4h(double volumeU4h) {
            this.volumeU4h = volumeU4h;
        }

        public double getVolumeU24h() {
            return volumeU24h;
        }

        public void setVolumeU24h(double volumeU24h) {
            this.volumeU24h = volumeU24h;
        }

        public double getBuyVolumeU1m() {
            return buyVolumeU1m;
        }

        public void setBuyVolumeU1m(double buyVolumeU1m) {
            this.buyVolumeU1m = buyVolumeU1m;
        }

        public double getBuyVolumeU5m() {
            return buyVolumeU5m;
        }

        public void setBuyVolumeU5m(double buyVolumeU5m) {
            this.buyVolumeU5m = buyVolumeU5m;
        }

        public double getBuyVolumeU15m() {
            return buyVolumeU15m;
        }

        public void setBuyVolumeU15m(double buyVolumeU15m) {
            this.buyVolumeU15m = buyVolumeU15m;
        }

        public double getBuyVolumeU30m() {
            return buyVolumeU30m;
        }

        public void setBuyVolumeU30m(double buyVolumeU30m) {
            this.buyVolumeU30m = buyVolumeU30m;
        }

        public double getBuyVolumeU1h() {
            return buyVolumeU1h;
        }

        public void setBuyVolumeU1h(double buyVolumeU1h) {
            this.buyVolumeU1h = buyVolumeU1h;
        }

        public double getBuyVolumeU4h() {
            return buyVolumeU4h;
        }

        public void setBuyVolumeU4h(double buyVolumeU4h) {
            this.buyVolumeU4h = buyVolumeU4h;
        }

        public double getBuyVolumeU24h() {
            return buyVolumeU24h;
        }

        public void setBuyVolumeU24h(double buyVolumeU24h) {
            this.buyVolumeU24h = buyVolumeU24h;
        }

        public double getSellVolumeU1m() {
            return sellVolumeU1m;
        }

        public void setSellVolumeU1m(double sellVolumeU1m) {
            this.sellVolumeU1m = sellVolumeU1m;
        }

        public double getSellVolumeU5m() {
            return sellVolumeU5m;
        }

        public void setSellVolumeU5m(double sellVolumeU5m) {
            this.sellVolumeU5m = sellVolumeU5m;
        }

        public double getSellVolumeU15m() {
            return sellVolumeU15m;
        }

        public void setSellVolumeU15m(double sellVolumeU15m) {
            this.sellVolumeU15m = sellVolumeU15m;
        }

        public double getSellVolumeU30m() {
            return sellVolumeU30m;
        }

        public void setSellVolumeU30m(double sellVolumeU30m) {
            this.sellVolumeU30m = sellVolumeU30m;
        }

        public double getSellVolumeU1h() {
            return sellVolumeU1h;
        }

        public void setSellVolumeU1h(double sellVolumeU1h) {
            this.sellVolumeU1h = sellVolumeU1h;
        }

        public double getSellVolumeU4h() {
            return sellVolumeU4h;
        }

        public void setSellVolumeU4h(double sellVolumeU4h) {
            this.sellVolumeU4h = sellVolumeU4h;
        }

        public double getSellVolumeU24h() {
            return sellVolumeU24h;
        }

        public void setSellVolumeU24h(double sellVolumeU24h) {
            this.sellVolumeU24h = sellVolumeU24h;
        }

        public int getMakers1m() {
            return makers1m;
        }

        public void setMakers1m(int makers1m) {
            this.makers1m = makers1m;
        }

        public int getMakers5m() {
            return makers5m;
        }

        public void setMakers5m(int makers5m) {
            this.makers5m = makers5m;
        }

        public int getMakers15m() {
            return makers15m;
        }

        public void setMakers15m(int makers15m) {
            this.makers15m = makers15m;
        }

        public int getMakers30m() {
            return makers30m;
        }

        public void setMakers30m(int makers30m) {
            this.makers30m = makers30m;
        }

        public int getMakers1h() {
            return makers1h;
        }

        public void setMakers1h(int makers1h) {
            this.makers1h = makers1h;
        }

        public int getMakers4h() {
            return makers4h;
        }

        public void setMakers4h(int makers4h) {
            this.makers4h = makers4h;
        }

        public int getMakers24h() {
            return makers24h;
        }

        public void setMakers24h(int makers24h) {
            this.makers24h = makers24h;
        }

        public int getBuyers1m() {
            return buyers1m;
        }

        public void setBuyers1m(int buyers1m) {
            this.buyers1m = buyers1m;
        }

        public int getBuyers5m() {
            return buyers5m;
        }

        public void setBuyers5m(int buyers5m) {
            this.buyers5m = buyers5m;
        }

        public int getBuyers15m() {
            return buyers15m;
        }

        public void setBuyers15m(int buyers15m) {
            this.buyers15m = buyers15m;
        }

        public int getBuyers30m() {
            return buyers30m;
        }

        public void setBuyers30m(int buyers30m) {
            this.buyers30m = buyers30m;
        }

        public int getBuyers1h() {
            return buyers1h;
        }

        public void setBuyers1h(int buyers1h) {
            this.buyers1h = buyers1h;
        }

        public int getBuyers4h() {
            return buyers4h;
        }

        public void setBuyers4h(int buyers4h) {
            this.buyers4h = buyers4h;
        }

        public int getBuyers24h() {
            return buyers24h;
        }

        public void setBuyers24h(int buyers24h) {
            this.buyers24h = buyers24h;
        }

        public int getSellers1m() {
            return sellers1m;
        }

        public void setSellers1m(int sellers1m) {
            this.sellers1m = sellers1m;
        }

        public int getSellers5m() {
            return sellers5m;
        }

        public void setSellers5m(int sellers5m) {
            this.sellers5m = sellers5m;
        }

        public int getSellers15m() {
            return sellers15m;
        }

        public void setSellers15m(int sellers15m) {
            this.sellers15m = sellers15m;
        }

        public int getSellers30m() {
            return sellers30m;
        }

        public void setSellers30m(int sellers30m) {
            this.sellers30m = sellers30m;
        }

        public int getSellers1h() {
            return sellers1h;
        }

        public void setSellers1h(int sellers1h) {
            this.sellers1h = sellers1h;
        }

        public int getSellers4h() {
            return sellers4h;
        }

        public void setSellers4h(int sellers4h) {
            this.sellers4h = sellers4h;
        }

        public int getSellers24h() {
            return sellers24h;
        }

        public void setSellers24h(int sellers24h) {
            this.sellers24h = sellers24h;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public int getSniperTxCount() {
            return sniperTxCount;
        }

        public void setSniperTxCount(int sniperTxCount) {
            this.sniperTxCount = sniperTxCount;
        }

        public int getRusherTxCount() {
            return rusherTxCount;
        }

        public void setRusherTxCount(int rusherTxCount) {
            this.rusherTxCount = rusherTxCount;
        }

        public String getLastTradeAt() {
            return lastTradeAt;
        }

        public void setLastTradeAt(String lastTradeAt) {
            this.lastTradeAt = lastTradeAt;
        }

        public String getDynamicTag() {
            return dynamicTag;
        }

        public void setDynamicTag(String dynamicTag) {
            this.dynamicTag = dynamicTag;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public double getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(double marketCap) {
            this.marketCap = marketCap;
        }

        public int getMarketCapDiff() {
            return marketCapDiff;
        }

        public void setMarketCapDiff(int marketCapDiff) {
            this.marketCapDiff = marketCapDiff;
        }

        public int getHolders() {
            return holders;
        }

        public void setHolders(int holders) {
            this.holders = holders;
        }

        public int getHoldersDiff() {
            return holdersDiff;
        }

        public void setHoldersDiff(int holdersDiff) {
            this.holdersDiff = holdersDiff;
        }

        public int getRiskScore() {
            return riskScore;
        }

        public void setRiskScore(int riskScore) {
            this.riskScore = riskScore;
        }

        public int getRiskLevel() {
            return riskLevel;
        }

        public void setRiskLevel(int riskLevel) {
            this.riskLevel = riskLevel;
        }

        public String getAppendix() {
            return appendix;
        }

        public void setAppendix(String appendix) {
            this.appendix = appendix;
        }

        public int getSmartMoneyBuyCount24h() {
            return smartMoneyBuyCount24h;
        }

        public void setSmartMoneyBuyCount24h(int smartMoneyBuyCount24h) {
            this.smartMoneyBuyCount24h = smartMoneyBuyCount24h;
        }

        public int getSmartMoneySellCount24h() {
            return smartMoneySellCount24h;
        }

        public void setSmartMoneySellCount24h(int smartMoneySellCount24h) {
            this.smartMoneySellCount24h = smartMoneySellCount24h;
        }

        public double getHoldersTop10Ratio() {
            return holdersTop10Ratio;
        }

        public void setHoldersTop10Ratio(double holdersTop10Ratio) {
            this.holdersTop10Ratio = holdersTop10Ratio;
        }

        public double getDevBalanceRatioCur() {
            return devBalanceRatioCur;
        }

        public void setDevBalanceRatioCur(double devBalanceRatioCur) {
            this.devBalanceRatioCur = devBalanceRatioCur;
        }

        public double getInsiderBalanceRatioCur() {
            return insiderBalanceRatioCur;
        }

        public void setInsiderBalanceRatioCur(double insiderBalanceRatioCur) {
            this.insiderBalanceRatioCur = insiderBalanceRatioCur;
        }

        public double getSniperBalanceRatioCur() {
            return sniperBalanceRatioCur;
        }

        public void setSniperBalanceRatioCur(double sniperBalanceRatioCur) {
            this.sniperBalanceRatioCur = sniperBalanceRatioCur;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public double getProgress() {
            return progress;
        }

        public void setProgress(double progress) {
            this.progress = progress;
        }

        public double getFirstHalfElapsedTime() {
            return firstHalfElapsedTime;
        }

        public void setFirstHalfElapsedTime(double firstHalfElapsedTime) {
            this.firstHalfElapsedTime = firstHalfElapsedTime;
        }

        public double getSecondHalfElapsedTime() {
            return secondHalfElapsedTime;
        }

        public void setSecondHalfElapsedTime(double secondHalfElapsedTime) {
            this.secondHalfElapsedTime = secondHalfElapsedTime;
        }

        public int getWinnerCount() {
            return winnerCount;
        }

        public void setWinnerCount(int winnerCount) {
            this.winnerCount = winnerCount;
        }

        public double getWinnerRatio() {
            return winnerRatio;
        }

        public void setWinnerRatio(double winnerRatio) {
            this.winnerRatio = winnerRatio;
        }

        public int getUpCount7d() {
            return upCount7d;
        }

        public void setUpCount7d(int upCount7d) {
            this.upCount7d = upCount7d;
        }

        public int getUpCount14d() {
            return upCount14d;
        }

        public void setUpCount14d(int upCount14d) {
            this.upCount14d = upCount14d;
        }

        public String getUpSeq() {
            return upSeq;
        }

        public void setUpSeq(String upSeq) {
            this.upSeq = upSeq;
        }

        public int getBigorderCnt1h() {
            return bigorderCnt1h;
        }

        public void setBigorderCnt1h(int bigorderCnt1h) {
            this.bigorderCnt1h = bigorderCnt1h;
        }

        public int getBigorderBuyCnt1h() {
            return bigorderBuyCnt1h;
        }

        public void setBigorderBuyCnt1h(int bigorderBuyCnt1h) {
            this.bigorderBuyCnt1h = bigorderBuyCnt1h;
        }

        public int getBigorderSellCnt1h() {
            return bigorderSellCnt1h;
        }

        public void setBigorderSellCnt1h(int bigorderSellCnt1h) {
            this.bigorderSellCnt1h = bigorderSellCnt1h;
        }

        public boolean isHasBrokenIssuePrice() {
            return hasBrokenIssuePrice;
        }

        public void setHasBrokenIssuePrice(boolean hasBrokenIssuePrice) {
            this.hasBrokenIssuePrice = hasBrokenIssuePrice;
        }

        public int getListingAt() {
            return listingAt;
        }

        public void setListingAt(int listingAt) {
            this.listingAt = listingAt;
        }
    }
}
