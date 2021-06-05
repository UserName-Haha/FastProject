package com.haha.fastproject.base.utils

import me.goldze.mvvmhabit.base.BaseApplication
import java.text.SimpleDateFormat

/**
 * @author zhe.chen
 * @date 2021/5/25 10:45
 * Des:
 */
fun Float.dp(): Int {
    val scale: Float = BaseApplication.getInstance().getResources().getDisplayMetrics().density
    return (this * scale + 0.5f).toInt()
}

fun Long.format(pattern: String): String {
    val format = SimpleDateFormat(pattern)
    return format.format(this)
}

