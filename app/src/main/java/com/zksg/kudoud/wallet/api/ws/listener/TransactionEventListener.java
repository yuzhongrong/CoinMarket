/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.ws.listener;


/**
 * 
 */
public interface TransactionEventListener {
    
    /**
     * 
     *
     * @param signature 
     */
    public void onTransactiEvent(String signature);
}
