package com.haha.fastproject.base.utils;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES加密解密算法 
 */
public class AesEncryptionUtil {
    /**
     * 算法/模式/填充
     **/
    private static final String CipherMode = "AES/CBC/PKCS5Padding";


    /**
     * 创建AES加密Key
     *
     * @param bytes 字节数据
     * @return
     */
    private static SecretKeySpec createKey(byte[] bytes) {
        return new SecretKeySpec(bytes, "AES");
    }

    /**
     * 创建加密IV
     *
     * @param bytes
     * @return
     */
    private static IvParameterSpec createIV(byte[] bytes) {
        return new IvParameterSpec(bytes);
    }

    /**
     * 加密(结果为16进制字符串)
     **/
    public static String encrypt(String content, String hexPassword, String hexIv) {
        byte[] data = null;
        try {
            data = content.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = encrypt(data, hexPassword, hexIv);
        String result = HexUtil.parseByte2HexStr(data);
        return result;
    }

    /**
     * 解密(输出结果为字符串)
     **/
    public static String decrypt(String content, String password, String iv) {
        byte[] data = null;
        try {
            data = HexUtil.parseHexStr2Byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, password, iv);
        if (data == null)
            return null;
        String result = null;
        try {
            result = new String(data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static byte[] decryptToByte(String content, String password, String iv) {
        byte[] data = null;
        try {
            data = HexUtil.parseHexStr2Byte(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data = decrypt(data, password, iv);
        if (data == null)
            return null;
        return data;
    }

    /**
     * 加密字节数据
     **/
    private static byte[] encrypt(byte[] content, String hexPassword, String hexIv) {
        try {
            SecretKeySpec key = createKey(HexUtil.parseHexStr2Byte(hexPassword));
            Cipher cipher = Cipher.getInstance(CipherMode);
            cipher.init(Cipher.ENCRYPT_MODE, key, createIV(HexUtil.parseHexStr2Byte(hexIv)));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密字节数组
     **/
    private static byte[] decrypt(byte[] content, String password, String iv) {
        try {
            SecretKeySpec key = createKey(HexUtil.parseHexStr2Byte(password));
            Cipher cipher = Cipher.getInstance(CipherMode);
            IvParameterSpec iv1 = createIV(HexUtil.parseHexStr2Byte(iv));
            cipher.init(Cipher.DECRYPT_MODE, key, iv1);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

