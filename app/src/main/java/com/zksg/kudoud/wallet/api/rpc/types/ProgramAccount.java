/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc.types;

//import com.blankj.utilcode.util.GsonUtils;
//import com.netease.lib_common_ui.utils.GsonUtil;
import com.blankj.utilcode.util.GsonUtils;
import com.zksg.kudoud.wallet.data.Parse;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;


/**
 * 
 */
public class ProgramAccount {

    /**  */
    private Account account;
    
    /**  */
    private String pubkey;

    /**
     * 
     */
    public ProgramAccount() {
    }

    /**
     * 
     *
     * @param pa 
     */
    @SuppressWarnings({ "rawtypes" })
    public ProgramAccount(AbstractMap pa) {
        this.account = (Account) new Account(pa.get("account"));
        this.pubkey = (String) pa.get("pubkey");
    }

    /**
     * 
     *
     * @return 
     */
    public Account getAccount() {
        return account;
    }

    /**
     * 
     *
     * @param account 
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * 
     *
     * @return 
     */
    public String getPubkey() {
        return pubkey;
    }

    /**
     * 
     *
     * @param pubkey 
     */
    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    /**
     * 
     */
    public final class Account {
        
        /**  */
        private String data;
        private Parse parse;
        
        /**  */
        private boolean executable;
        
        /**  */
        private double lamports;
        
        /**  */
        private String owner;
        
        /**  */
        private double rentEpoch;
        
        /**  */
        private String encoding;

        /**
         * 
         */
        public Account() {

        }

        /**
         * 
         *
         * @param acc 
         */
        @SuppressWarnings({ "rawtypes", "unchecked" })
        public Account(Object acc) {
            AbstractMap account = (AbstractMap) acc;

            Object rawData = account.get("data");
            if (rawData instanceof List) {
                List<String> dataList = ((List<String>) rawData);

                this.data = dataList.get(0);
                this.encoding = (String) dataList.get(1);
            } else if (rawData instanceof String) {
                this.data = (String) rawData;
            }else if(rawData instanceof Map){
               String json= GsonUtils.toJson(rawData);

               this.parse= GsonUtils.fromJson(json,Parse.class);
            }

            this.executable = (boolean) account.get("executable");
            this.lamports = new BigDecimal((Integer) account.get("lamports")).doubleValue();
            this.owner = (String) account.get("owner");
            this.rentEpoch = new BigDecimal((BigInteger)account.get("rentEpoch")).doubleValue(); //(double) account.get("rentEpoch");
        }

        /**
         * 
         *
         * @return 
         */
        public String getData() {
            return data;
        }

        /**
         * 
         *
         * @param data 
         */
        public void setData(String data) {
            this.data = data;
        }


        public Parse getParse() {
            return parse;
        }

        /**
         *
         *
         * @param parse
         */
        public void setParse(Parse parse) {
            this.parse = parse;
        }

        /**
         * 
         *
         * @return 
         */
        public boolean isExecutable() {
            return executable;
        }

        /**
         * 
         *
         * @param executable 
         */
        public void setExecutable(boolean executable) {
            this.executable = executable;
        }

        /**
         * 
         *
         * @return 
         */
        public double getLamports() {
            return lamports;
        }

        /**
         * 
         *
         * @param lamports 
         */
        public void setLamports(double lamports) {
            this.lamports = lamports;
        }

        /**
         * 
         *
         * @return 
         */
        public String getOwner() {
            return owner;
        }

        /**
         * 
         *
         * @param owner 
         */
        public void setOwner(String owner) {
            this.owner = owner;
        }

        /**
         * 
         *
         * @return 
         */
        public double getRentEpoch() {
            return rentEpoch;
        }

        /**
         * 
         *
         * @param rentEpoch 
         */
        public void setRentEpoch(double rentEpoch) {
            this.rentEpoch = rentEpoch;
        }

        /**
         * 
         *
         * @return 
         */
        public String getEncoding() {
            return encoding;
        }

        /**
         * 
         *
         * @param encoding 
         */
        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

    }
}
