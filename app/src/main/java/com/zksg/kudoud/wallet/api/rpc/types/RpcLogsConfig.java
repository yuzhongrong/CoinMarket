/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc.types;


/**
 * 
 */
public class RpcLogsConfig {

    /**  */
    private SolanaCommitment commitment;

    /**
     * 
     *
     * @param commitment 
     */
    public RpcLogsConfig(SolanaCommitment commitment) {
        this.commitment = commitment;
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
     * @param commitment 
     */
    public void setCommitment(SolanaCommitment commitment) {
        this.commitment = commitment;
    }

}
