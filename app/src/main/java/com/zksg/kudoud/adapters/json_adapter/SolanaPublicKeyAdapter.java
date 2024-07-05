package com.zksg.kudoud.adapters.json_adapter;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.zksg.kudoud.wallet.data.SolanaPublicKey;

import java.io.IOException;
public class SolanaPublicKeyAdapter extends TypeAdapter<SolanaPublicKey>{

    @Override
    public void write(JsonWriter out, SolanaPublicKey value) throws IOException {
        out.value(String.valueOf(value.getPublicKey()));
    }

    @Override
    public SolanaPublicKey read(JsonReader in) throws IOException {
        String publicKey = in.nextString();
        return new SolanaPublicKey(publicKey);
    }
}
