/************************************************************************ 
 * Copyright PointCheckout, Ltd.
 * 
 */
package com.zksg.kudoud.wallet.api.rpc;


/**
 * 
 */
public enum Cluster {
    
    /**  */
    DEVNET("https://api.devnet.solana.com"),
    
    /**  */
    TESTNET("https://api.testnet.solana.com"),
    
    /**  */
    MAINNET("https://api.mainnet-beta.solana.com"),

    QUICKNODE("https://sleek-purple-darkness.solana-mainnet.quiknode.pro/39c7e391a14030cdbf8c2bffcafaebed460dd85c"),
    ALCHEMY("https://solana-mainnet.g.alchemy.com/v2/2hnePJn18uVTg7FWp6sNWEZ7gF096WsK");



    /**  */
    private String endpoint;

    /**
     * 
     *
     * @param endpoint 
     */
    Cluster(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * 
     *
     * @return 
     */
    public String getEndpoint() {
        return endpoint;
    }
}
