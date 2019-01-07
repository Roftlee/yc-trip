package com.yc.trip.web.utils;

import java.security.Key;
import java.util.Map;

/**
 * @author: chenzw
 * @create: 2018/11/26 15:36
 */
public class RSACoderTest {

    private static String publicKey;
    private static String privateKey;

    public static void setUp() throws Exception {
        Map<String, Key> keyMap = RSACoder.initKey();
        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    public static void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "dounine";
        byte[] encodedData = RSACoder.encryptByPublicKey(inputStr, publicKey);
        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
    }

    public static void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "dounine";
        byte[] data = inputStr.getBytes();
        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
        byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);
        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSACoder.sign(encodedData, privateKey);
        System.err.println("签名:" + sign);
        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
    }

    public static void main(String[] args) throws Exception{
        setUp();
        test();
    }
}
