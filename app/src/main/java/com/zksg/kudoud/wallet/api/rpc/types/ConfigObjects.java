/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc.types;

import com.zksg.kudoud.wallet.api.rpc.types.RpcSendTransactionConfig.Encoding;

import java.util.List;


/**
 * 
 */
public class ConfigObjects {

    /**
     * 
     */
    public static class SignaturesForAddress {

        /**  */
        private long limit;
        
        /**  */
        private String before;
        
        /**  */
        private String until;
        
        /**  */
        private SolanaCommitment commitment;

        /**
         * 
         */
        public SignaturesForAddress() {

        }

     
        /**
         * 
         *
         * @param limit 
         * @param commitment 
         */
        public SignaturesForAddress(long limit, SolanaCommitment commitment) {
            this.limit = limit;
            this.commitment = commitment;
        }

        /**
         * 
         *
         * @return 
         */
        public long getLimit() {
            return limit;
        }

        /**
         * 
         *
         * @return 
         */
        public SolanaCommitment getCommitment() {
            return commitment;
        }

        /**
         * 
         *
         * @param limit 
         */
        public void setLimit(long limit) {
            this.limit = limit;
        }

        /**
         * 
         *
         * @return 
         */
        public String getBefore() {
            return before;
        }

        /**
         * 
         *
         * @param before 
         */
        public void setBefore(String before) {
            this.before = before;
        }

        /**
         * 
         *
         * @return 
         */
        public String getUntil() {
            return until;
        }

        /**
         * 
         *
         * @param until 
         */
        public void setUntil(String until) {
            this.until = until;
        }

    }

    /**
     * 
     */
    public static class Memcmp {
        
        /**  */
        private long offset;
        
        /**  */
        private String bytes;

        /**
         * 
         */
        public Memcmp() {
        }

        /**
         * 
         *
         * @param offset 
         * @param bytes 
         */
        public Memcmp(long offset, String bytes) {
            this.offset = offset;
            this.bytes = bytes;
        }

        /**
         * 
         *
         * @return 
         */
        public long getOffset() {
            return offset;
        }

        /**
         * 
         *
         * @param offset 
         */
        public void setOffset(long offset) {
            this.offset = offset;
        }

        /**
         * 
         *
         * @return 
         */
        public String getBytes() {
            return bytes;
        }

        /**
         * 
         *
         * @param bytes 
         */
        public void setBytes(String bytes) {
            this.bytes = bytes;
        }

    }


    public static class DataSize {
        private long dataSize;

        public DataSize(long dataSize) {
            this.dataSize = dataSize;
        }

        public long getDatasize() {
            return dataSize;
        }

        public void setDatasize(long dataSize) {
            this.dataSize = dataSize;
        }
    }

    /**
     * 
     */
    public static class Filter {
        
        /**  */
        private Memcmp memcmp;

        /**
         * 
         *
         * @return 
         */
        public Memcmp getMemcmp() {
            return memcmp;
        }

        /**
         * 
         *
         * @param memcmp 
         */
        public void setMemcmp(Memcmp memcmp) {
            this.memcmp = memcmp;
        }


        /**
         * 
         */
        public Filter() {
        }

        /**
         * 
         *
         * @param memcmp 
         */
        public Filter(Memcmp memcmp) {
            this.memcmp = memcmp;
        }


    }


    public static class Filter1 {

        /**  */
        private long dataSize;


        public Filter1() {
        }

        /**
         *
         *
         * @para
         */
        public Filter1(long dataSize) {
            this.dataSize = dataSize;
        }

        public long getDataSize() {
            return dataSize;
        }

        public void setDataSize(long dataSize) {
            this.dataSize = dataSize;
        }
    }

    /**
     * 
     */
    public static class ProgramAccountConfig {
        
        /**  */
        private Encoding encoding = null;
        /**  */
        private List<Object> filters = null;

        /**
         * 
         */
        public ProgramAccountConfig() {
        }

        /**
         * 
         *
         * @param filters 
         */
        public ProgramAccountConfig(List<Object> filters) {
            this.filters = filters;
        }

        /**
         * 
         *
         * @param encoding 
         */
        public ProgramAccountConfig(Encoding encoding) {
            this.encoding = encoding;
        }

        /**
         * 
         *
         * @return 
         */
        public Encoding getEncoding() {
            return encoding;
        }

        /**
         * 
         *
         * @param encoding 
         */
        public void setEncoding(Encoding encoding) {
            this.encoding = encoding;
        }

        /**
         * 
         *
         * @return 
         */
        public List<Object> getFilters() {
            return filters;
        }

        /**
         * 
         *
         * @param filters 
         */
        public void setFilters(List<Object> filters) {
            this.filters = filters;
        }

    }
}
