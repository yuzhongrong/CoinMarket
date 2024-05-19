package com.zksg.kudoud.wallet.utils;

public class SolanaConverter {

    private static final long SOL_FACTOR = 1_000_000_000L; // 1 SOL = 1,000,000,000 Lamports

    /**
     * 将 Lamports 转换为 SOL
     * @param lamports 需要转换的 Lamports 数量
     * @return 转换后的 SOL 数量
     */
    public static double lamportsToSol(long lamports) {
        return (double) lamports / SOL_FACTOR;
    }

    /**
     * 将 SOL 转换为 Lamports
     * @param sol 需要转换的 SOL 数量
     * @return 转换后的 Lamports 数量
     */
    public static long solToLamports(double sol) {
        return (long) (sol * SOL_FACTOR);
    }

}
