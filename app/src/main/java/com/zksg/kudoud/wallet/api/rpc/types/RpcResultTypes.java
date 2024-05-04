/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc.types;

/**
 * 
 */
public class RpcResultTypes {

    /**
     * 
     */
    public static class ValueLong extends RpcResultObject {

        /**  */
        private long value;

        /**
         * 
         *
         * @return 
         */
        public long getValue() {
            return value;
        }

        /**
         * 
         *
         * @param value 
         */
        public void setValue(long value) {
            this.value = value;
        }

    }

}
