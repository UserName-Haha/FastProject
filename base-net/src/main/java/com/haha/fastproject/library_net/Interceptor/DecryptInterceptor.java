package com.haha.fastproject.library_net.Interceptor;

import android.text.TextUtils;

import com.haha.fastproject.base.global.config.AppConfig;
import com.haha.fastproject.base.global.constant.Constant;
import com.haha.fastproject.base.utils.AesEncryptionUtil;
import com.haha.fastproject.base.utils.encryption.HideAlgorithm;

import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author zhe.chen
 * @date 2019-12-20 10:19
 * Des:网络数据解密拦截器
 */
public class DecryptInterceptor implements Interceptor {


    private String TAG = "DecryptInterceptor";
    public static byte[] faceUnityBytesKey;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response encryptionResponse = chain.proceed(request);
        ResponseBody encryptionBody = encryptionResponse.body();
        byte[] bytes = encryptionBody.bytes();
        //解析数据，获取密文Response
        EnumMap<HideAlgorithm.DecryptData, String> parseData = HideAlgorithm.parse(bytes);
        String hexType = parseData.get(HideAlgorithm.DecryptData.HEX_TYPE);
        String hexIv = parseData.get(HideAlgorithm.DecryptData.HEX_IV);
        String hexResponse = parseData.get(HideAlgorithm.DecryptData.HEX_RESPONSE);
        String content = "";
        if (!Arrays.asList(HideAlgorithm.typeArr).contains(hexType)) {
            //如果当前数据加密类型不在已知的加密类型中，则默认返回原始数据
            content = new String(bytes);
//            KLog.json(TAG, "请求Request\n" + request.toString());
//            KLog.json(TAG, "解密后的Response\n" + content);
            return getNewResponse(encryptionResponse, content);
        }

        if (!TextUtils.isEmpty(hexResponse)) {
            //解析密文Response
            content = AesEncryptionUtil.decrypt(hexResponse, Constant.HEX_KEY, hexIv);
        }
//        KLog.json(TAG, "请求Request\n" + request.toString());
//        KLog.json(TAG, "\"解密后的Response\"\n" + content);
        if (chain.request().url().toString().contains("other/xx")) {
            //如果当前请求的接口为faceunity key动态下发接口则解密获取二进制文件
            faceUnityBytesKey = AesEncryptionUtil.decryptToByte(hexResponse, Constant.HEX_KEY, hexIv);
        }
        //将解密后的明文数据构造新的Response返回给客户端
        return getNewResponse(encryptionResponse, content);
    }

    private Response getNewResponse(Response encryptionResponse, String content) {
        encryptionResponse.close();
        MediaType mediaType = MediaType.parse("text/plain; charset=utf-8");
        ResponseBody newResponseBody = ResponseBody.create(mediaType, content);
        return encryptionResponse.newBuilder().body(newResponseBody).build();
    }
}
