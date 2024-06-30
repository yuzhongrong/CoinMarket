package com.zksg.kudoud.utils

import android.util.Base64
import android.util.Log
import com.zksg.kudoud.entitys.TransationSwap
import com.zksg.kudoud.wallet.data.*

object SolanaTransationHelper {

    fun CreateTransationForSwap(mSolanaAccount: SolanaAccount,swap: TransationSwap?):String? {
        val transaction = SolanaTransaction()
        transaction.setRecentBlockHash(swap?.recentBlockhash)
        //构造命令集
        swap?.instructions?.forEach {
            var instruction = SolanaTransactionInstruction()
            //制作 ProgramId
            instruction.setProgramId(it.programId)
            //制作 mkeys
            var mkeys = mutableListOf<AccountMeta>()
            it.keys.forEach {
                var mAccountMeta=AccountMeta(SolanaPublicKey(it.pubkey),it.isIsSigner,it.isIsWritable)
                mkeys.add(mAccountMeta)
            }
            instruction.keys=mkeys
            instruction.data= it.data

            transaction.addInstruction(instruction)
        }

//        transaction.feePayer = SolanaPublicKey(store?.feePayer)
        transaction.sign(mSolanaAccount)
        val base64String = Base64.encodeToString(transaction.serialize(), Base64.DEFAULT)
        Log.d("---transation--->",base64String)
        return base64String



    }


}