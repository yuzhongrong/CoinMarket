package com.zksg.kudoud.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializationUtils {

    // 将对象转换为 byte 数组
    public static byte[] serializeObject(Object object) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        oos.flush();
        byte[] bytes = bos.toByteArray();
        bos.close();
        oos.close();
        return bytes;
    }

    // 从 byte 数组中还原对象
    public static Object deserializeObject(byte[] bytes) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object object = ois.readObject();
        bis.close();
        ois.close();
        return object;
    }
}


