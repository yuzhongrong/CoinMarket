package com.zksg.kudoud.wallet.data.jupswap;

import com.paymennt.crypto.lib.Base58;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private Header header;
    private List<String> accountKeys;
    private byte[] recentBlockhash;
    private List<Instruction> instructions;

    public Message() {
        this.accountKeys = new ArrayList<>();
        this.instructions = new ArrayList<>();
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Header getHeader() {
        return header;
    }

    public void addAccountKey(String key) {
        this.accountKeys.add(key);
    }

    public List<String> getAccountKeys() {
        return accountKeys;
    }

    public void setRecentBlockhash(byte[] recentBlockhash) {
        this.recentBlockhash = recentBlockhash;
    }

    public byte[] getRecentBlockhash() {
        return recentBlockhash;
    }

    public void addInstruction(Instruction instruction) {
        this.instructions.add(instruction);
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(2048);

        // 序列化 header
        buffer.put((byte) header.numRequiredSignatures);
        buffer.put((byte) header.numReadonlySignedAccounts);
        buffer.put((byte) header.numReadonlyUnsignedAccounts);

        // 序列化 accountKeys
        buffer.put((byte) accountKeys.size());
        for (String key : accountKeys) {
            buffer.put(Base58Util.decode(key));
        }

        // 序列化 recentBlockhash
        buffer.put(recentBlockhash);

        // 序列化 instructions
        buffer.put((byte) instructions.size());
        int amountLenth=0;
        for (Instruction instruction : instructions) {
            buffer.put(instruction.programIdIndex);
            // 序列化 accounts
            buffer.put(instruction.accounts);
            // 序列化 data
            byte[] decodedData = Base58Util.decode(instruction.data);
            buffer.put(decodedData);
            amountLenth+=instruction.getLength();
        }

//        byte[] serializedData = new byte[buffer.position()];
//        buffer.flip();
//        buffer.get(serializedData);
        int size = buffer.position();
        byte[] serializedData = new byte[size];
        buffer.rewind(); // 重置位置指针
        buffer.get(serializedData, 0, size);
        return serializedData;
    }
}

class Header {
    public int numRequiredSignatures;
    public int numReadonlySignedAccounts;
    public int numReadonlyUnsignedAccounts;
}

class Instruction {
    public byte programIdIndex;
    public byte[] accounts;
    public String data;

    public Instruction() {
        this.accounts = new byte[]{};
    }
    int getLength() {
        byte[] decodedData = Base58Util.decode(data);
        // 1 byte for programIdIndex + length of accounts (VarUInt) + accounts array length + length of data (VarUInt) + decoded data length
        return 1 + VarUInt.getBytes(accounts.length).length + accounts.length + VarUInt.getBytes(decodedData.length).length + decodedData.length;
    }

}
