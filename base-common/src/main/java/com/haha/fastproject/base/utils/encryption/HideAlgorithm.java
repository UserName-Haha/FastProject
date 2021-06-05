package com.haha.fastproject.base.utils.encryption;


import com.haha.fastproject.base.global.config.AppConfig;
import com.haha.fastproject.base.global.constant.Constant;
import com.haha.fastproject.base.utils.AesEncryptionUtil;
import com.haha.fastproject.base.utils.HexUtil;

import java.util.EnumMap;
import java.util.Random;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * @author zhe.chen
 * @date 2019-12-21 17:29
 * Des:此类用于对数据进行加密并重新排列组合
 */
public class HideAlgorithm {

    private static String TAG = "HideAlgorithm";
    private static final String ConfuseTypeA1_RandomNum_Data_IV = "A1";
    private static final String ConfuseTypeA2_IV_RandomNum_Data = "A2";
    private static final String ConfuseTypeA3_Data_IV_RandomNum = "A3";

    public static String[] typeArr = {ConfuseTypeA1_RandomNum_Data_IV, ConfuseTypeA2_IV_RandomNum_Data, ConfuseTypeA3_Data_IV_RandomNum};

    /**
     * 加密并重新排列组合数据
     *
     * @param rawData 明文数据
     * @return 返回16进制混淆加密后数据
     */
    public static String confuse(String rawData) {
        StringBuilder confuseSb = new StringBuilder();
        //获取所需数据
        String randomHexIV = getHexRandomIV();
        String randomHexStr = HexUtil.parseByte2HexStr(getRandomStr(16).getBytes());
        //加密数据
        String encryptJsonHexData = AesEncryptionUtil.encrypt(rawData, Constant.HEX_KEY, randomHexIV);
        //随机获取一种数据组合类型，根据数据组合类型，将数据重新排列组合
        String randomConfuseType = getRandomConfuseType();
        switch (randomConfuseType) {
            case ConfuseTypeA1_RandomNum_Data_IV:
                confuseSb.append(randomHexStr);
                confuseSb.append(encryptJsonHexData);
                confuseSb.append(randomHexIV);
                break;
            case ConfuseTypeA2_IV_RandomNum_Data:
                confuseSb.append(randomHexIV);
                confuseSb.append(randomHexStr);
                confuseSb.append(encryptJsonHexData);
                break;
            case ConfuseTypeA3_Data_IV_RandomNum:
                confuseSb.append(encryptJsonHexData);
                confuseSb.append(randomHexIV);
                confuseSb.append(randomHexStr);
                break;
            default:
                break;
        }
        //最后一位添加Type
        confuseSb.append(randomConfuseType);
        return confuseSb.toString();
    }

    /**
     * 解析二进制字节数组
     *
     * @param bytes
     * @return
     */
    public static EnumMap<DecryptData, String> parse(byte[] bytes) {
        String hexStr = HexUtil.parseByte2HexStr(bytes);
        return parse(hexStr);
    }

    /**
     * 解析16进制数据，
     *
     * @param hexStr 16进制数据
     * @return 解密后的数据
     */
    public static EnumMap<DecryptData, String> parse(String hexStr) {
        String hexResponse = "";
        String hexIv = "";
        String hexType = hexStr.substring(hexStr.length() - 2, hexStr.length());
        String newHexStr = hexStr.substring(0, hexStr.length() - 2);//去掉类型字节的十六进制新字符串
        switch (hexType) {
            case ConfuseTypeA1_RandomNum_Data_IV:
                //(32位随机数+data+iv)
                hexResponse = hexStr.substring(getRandomNumLenght(), newHexStr.length() - getAesIVLength());
                hexIv = hexStr.substring(newHexStr.length() - getAesIVLength(), newHexStr.length());
                break;
            case ConfuseTypeA2_IV_RandomNum_Data:
                //(iv+32位随机数+data)
                hexResponse = hexStr.substring(getAesIVLength() + getRandomNumLenght(), newHexStr.length());
                hexIv = hexStr.substring(0, getAesIVLength());
                break;
            case ConfuseTypeA3_Data_IV_RandomNum:
                //(data+iv+32位随机数)
                hexResponse = hexStr.substring(0, newHexStr.length() - getAesIVLength() - getRandomNumLenght());
                hexIv = hexStr.substring(newHexStr.length() - getAesIVLength() + getRandomNumLenght(), newHexStr.length() - getRandomNumLenght());
                break;
            default:
                KLog.e(TAG, "无法解析,未知数据组合类型,可能未加密：" + hexType);
                break;
        }
        EnumMap<DecryptData, String> decryptDataMap = new EnumMap<>(DecryptData.class);
        decryptDataMap.put(DecryptData.HEX_IV, hexIv);
        decryptDataMap.put(DecryptData.HEX_RESPONSE, hexResponse);
        decryptDataMap.put(DecryptData.HEX_TYPE, hexType);
        return decryptDataMap;
    }

    /**
     * 获取随机数的 字节 长度（目前暂定为32位）
     *
     * @return
     */
    private static int getRandomNumLenght() {
        return 32;
    }

    /**
     * 获取约定的Aes中IV的 字节 长度
     *
     * @return
     */
    private static int getAesIVLength() {
        return 32;
    }

    /**
     * 随机选取一种混淆类型（目前就3种,A1、A2、A3）
     *
     * @return
     */
    private static String getRandomConfuseType() {
        String mRandomConfuseType = ConfuseTypeA1_RandomNum_Data_IV;
        Random random = new Random();
        int arrIdx = random.nextInt(typeArr.length - 1);
        if (arrIdx <= typeArr.length) {
            mRandomConfuseType = typeArr[arrIdx];
        }
        return mRandomConfuseType;
    }

    /**
     * 获取十六进制随机IV
     *
     * @return 16位十六进制字符串
     */
    private static String getHexRandomIV() {
        String randomStr = getRandomStr(16);
        return HexUtil.parseByte2HexStr(randomStr.getBytes());
    }

    /**
     * 随机获取指定位数的字符串
     *
     * @param length
     * @return
     */
    private static String getRandomStr(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString().toUpperCase();
    }


    public enum DecryptData {
        HEX_TYPE, HEX_RESPONSE, HEX_IV
    }

}
