package com.zksg.kudoud.callback

interface QuoGasCallback {
    fun postQuoGas(value: Map<String, TokenInfo>)
}

data class TokenInfo(
    val price: Double,
    val decimal: Int
)