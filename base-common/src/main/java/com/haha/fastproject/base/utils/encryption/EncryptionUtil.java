package com.haha.fastproject.base.utils.encryption;


import com.haha.fastproject.base.global.config.AppConfig;
import com.haha.fastproject.base.global.constant.Constant;
import com.haha.fastproject.base.utils.AesEncryptionUtil;
import com.haha.fastproject.base.utils.HexUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.EnumMap;

/**
 * @author zhe.chen
 * @date 2019-12-23 10:21
 * Des:
 */
public class EncryptionUtil {

    /**
     * 加密发送的socket请求数据
     *
     * @param msg 明文需要加密的数据
     * @return 加密后的二进制数组
     */
    public static byte[] encryptRequest(String msg) {
        String confuseMsg = HideAlgorithm.confuse(msg);
        return HexUtil.parseHexStr2Byte(confuseMsg);
    }

    /**
     * 解密响应数据
     *
     * @param bytes 需要进行解密的二进制数组
     * @return 解密后的Json数据
     */
    public static String decryptResponse(byte[] bytes) {
        String decryptMessage = "";
        EnumMap<HideAlgorithm.DecryptData, String> parseMessage = HideAlgorithm.parse(bytes);
        if (parseMessage != null && !parseMessage.isEmpty()) {
            String response = parseMessage.get(HideAlgorithm.DecryptData.HEX_RESPONSE);
            String iv = parseMessage.get(HideAlgorithm.DecryptData.HEX_IV);
            decryptMessage = AesEncryptionUtil.decrypt(response, Constant.HEX_KEY, iv);
        }
        return decryptMessage;
    }


    /**
     * 密码加密
     *
     * @param password 密码明文
     * @return 密文密码
     */
    public static String encryptionPassword(String password) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

}
