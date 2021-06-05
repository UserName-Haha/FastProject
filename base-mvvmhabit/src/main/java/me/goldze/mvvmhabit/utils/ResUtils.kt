package me.goldze.mvvmhabit.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import me.goldze.mvvmhabit.base.BaseApplication

/**
 * @author zhe.chen
 * @date 4/26/21 10:29
 * Des:
 */
object ResUtils {

    fun getContext(): Context = BaseApplication.getInstance()

    /**
     * 得到Resource对象
     */
    @JvmStatic
    fun getResources(): Resources {
        return getContext().resources
    }

    /**
     * 得到String.xml中定义的字符信息
     */
    @JvmStatic
    fun getString(res: Int): String {
        return getResources().getString(res)
    }

    /**
     * 得到String.xml中定义的字符信息,带占位符
     */
    @JvmStatic
    fun getString(res: Int, vararg formatArgs: Any?): String {
        return getResources().getString(res, *formatArgs)
    }

    /**
     * 得到String.xml中定义的字符数组信息
     */
    @JvmStatic
    fun getStrings(res: Int): Array<String?> {
        return getResources().getStringArray(res)
    }

    /**
     * 得到color.xml中定义的颜色信息
     */
    @JvmStatic
    fun getColor(res: Int): Int {
        return getResources().getColor(res)
    }

    /**
     * 得到Drawable资源
     */
    @JvmStatic
    fun getDrawable(res: Int): Drawable {
        return getResources().getDrawable(res)
    }

    /**
     * 获取资源数组
     *
     * @param resId
     * @return
     */
    @JvmStatic
    fun getIntArray(res: Int): IntArray {
        return getResources().getIntArray(res)
    }

    /**
     * 获取String数组
     *
     * @param resId
     * @return
     */
    @JvmStatic
    fun getStringArray(res: Int): Array<String?> {
        return getResources().getStringArray(res)
    }


}