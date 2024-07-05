package com.zksg.kudoud.wallet.data.jupswap;
import org.bitcoinj.core.Base58;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
public class JupiterTransaction {

    private List<byte[]> signatures;
    private Message message;

    public JupiterTransaction() {
        this.signatures = new ArrayList<>();
    }

    public void addSignature(byte[] signature) {
        this.signatures.add(signature);
    }

    public List<byte[]> getSignatures() {
        return signatures;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public byte[] serialize() {
        ByteBuffer buffer = ByteBuffer.allocate(2048); // 初始容量，可以根据需要调整

        // 序列化签名数量
        buffer.put((byte) signatures.size());

        // 序列化签名
        for (byte[] signature : signatures) {
            buffer.put(signature);
        }

        // 序列化消息
        buffer.put(message.serialize());

        // 调整缓冲区大小以匹配实际数据
        byte[] serializedData = new byte[buffer.position()];
        buffer.flip();
        buffer.get(serializedData);

        return serializedData;
    }

    public byte[] serializeMessage() {
        return message.serialize();
    }


}
