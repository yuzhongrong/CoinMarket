package com.zksg.kudoud.wallet.data.jupswap;
import android.util.Log;

import com.paymennt.crypto.lib.Base58;
import com.zksg.kudoud.wallet.data.SolanaAccount;
import com.zksg.kudoud.wallet.utils.TweetNaclFast;

import org.bouncycastle.math.ec.custom.djb.Curve25519;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
public class SolanaTransactionSerializer {

    public static JupiterTransaction parseTransaction(String json) throws JSONException {
        JSONObject jsonObj = new JSONObject(json);

        // 解析签名
        JSONArray signaturesArray = jsonObj.getJSONArray("signatures");
        List<byte[]> signatures = new ArrayList<>();
        for (int i = 0; i < signaturesArray.length(); i++) {
            JSONObject signatureObj = signaturesArray.getJSONObject(i);
            byte[] signature = new byte[64];
            for (int j = 0; j < 64; j++) {
                signature[j] = (byte) signatureObj.getInt(String.valueOf(j));
            }
            signatures.add(signature);
        }

        // 解析消息
        JSONObject messageObj = jsonObj.getJSONObject("message");
        Header header = new Header();
        header.numRequiredSignatures = messageObj.getJSONObject("header").getInt("numRequiredSignatures");
        header.numReadonlySignedAccounts = messageObj.getJSONObject("header").getInt("numReadonlySignedAccounts");
        header.numReadonlyUnsignedAccounts = messageObj.getJSONObject("header").getInt("numReadonlyUnsignedAccounts");

        JSONArray accountKeysArray = messageObj.getJSONArray("accountKeys");
        List<String> accountKeys = new ArrayList<>();
        for (int i = 0; i < accountKeysArray.length(); i++) {
            accountKeys.add(accountKeysArray.getString(i));
        }

        String recentBlockhash = messageObj.getString("recentBlockhash");

        JSONArray instructionsArray = messageObj.getJSONArray("instructions");
        List<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < instructionsArray.length(); i++) {
            JSONObject instructionObj = instructionsArray.getJSONObject(i);
            Instruction instruction = new Instruction();
            instruction.programIdIndex = (byte) instructionObj.getInt("programIdIndex");
            JSONArray accountsArray = instructionObj.getJSONArray("accounts");
            instruction.accounts = new byte[accountsArray.length()];
            for (int j = 0; j < accountsArray.length(); j++) {
                instruction.accounts[j] = (byte) accountsArray.getInt(j);
            }
            instruction.data = instructionObj.getString("data");
            instructions.add(instruction);
        }

        Message message = new Message();
        message.setHeader(header);
        for (String accountKey : accountKeys) {
            message.addAccountKey(accountKey);
        }
        message.setRecentBlockhash(Base58Util.decode(recentBlockhash));
        for (Instruction instruction : instructions) {
            message.addInstruction(instruction);
        }

        JupiterTransaction transaction = new JupiterTransaction();
        for (byte[] signature : signatures) {
            transaction.addSignature(signature);
        }
        transaction.setMessage(message);

        return transaction;
    }

    public static byte[] serializeTransaction(JupiterTransaction transaction) {
        return transaction.serialize();
    }


    public static byte[] signTransaction(JupiterTransaction transaction, SolanaAccount mSolanaAccount) {
        TweetNaclFast.Signature signatureProvider = new TweetNaclFast.Signature(new byte[0],mSolanaAccount.getSecretKey());
        byte[] signature = signatureProvider.detached(transaction.serializeMessage());
        Log.d("----tx-sign--->", Base58.encode(signature));
        transaction.getSignatures().remove(0);
        transaction.getSignatures().add(signature);
        return serializeTransaction(transaction);

    }

}
