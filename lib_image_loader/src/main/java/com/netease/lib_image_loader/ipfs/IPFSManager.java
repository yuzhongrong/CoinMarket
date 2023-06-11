package com.netease.lib_image_loader.ipfs;

import android.util.Log;

import java.io.IOException;

import io.ipfs.api.IPFS;

/**
 *  由于没有合适的节点所以没有使用到这个ipfs,等后面有合适的节点再说
 */
public class IPFSManager {
    private static IPFSManager sInstance;
    private IPFS mIPFS;


    private IPFSManager() {}

    public static IPFSManager getInstance() {
        if (sInstance == null) {
            synchronized (IPFSManager.class) {
                if (sInstance == null) {
                    sInstance = new IPFSManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized IPFS getIPFSInstance() {
        if (mIPFS == null) {
            mIPFS = new IPFS("43.134.110.40",5001);
            try {
                mIPFS.refs.local();
            } catch (IOException e) {
                Log.e("IPFSManager ",e.getMessage());
            }
        }
        return mIPFS;
    }

}
