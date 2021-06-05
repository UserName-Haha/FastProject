package com.haha.fastproject.base.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/**
 * Create By zhe.chen on 2019/7/17.
 */
public class DeviceUtils {

    /**
     * 获取厂商名
     **/
    public static String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取产品名
     **/
    public static String getDeviceProduct() {
        return Build.PRODUCT;
    }

    /**
     * 获取手机品牌
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    /**
     * 获取手机型号
     */
    public static String getDeviceModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机主板名
     */
    public static String getDeviceBoard() {
        return Build.BOARD;
    }

    /**
     * 获取当前的系统版本
     *
     * @return
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 设备名
     **/
    public static String getDeviceDevice() {
        return Build.DEVICE;
    }

    /**
     * 获取手机sim卡id 需要READ_PHONE_STATE权限
     *
     * @param context 全局context
     * @return sim id
     */
    @SuppressLint("MissingPermission")
    public static String getSubscriberId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getSubscriberId();
    }

    /**
     * 获取设备AndroidID
     *
     * @param context 上下文
     * @return AndroidID
     */
    @SuppressLint("HardwareIds")
    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }



}
