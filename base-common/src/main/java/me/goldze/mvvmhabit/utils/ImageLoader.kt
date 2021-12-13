package me.goldze.mvvmhabit.utils

import android.app.Activity
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.haha.fastproject.base.R

/**
 * @author zhe.chen
 * @date 4/16/21 09:55
 * Des:
 */
object ImageLoader {

    private val mRequestOption = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .placeholder(R.color.placeholder)
        .centerCrop()
        .dontAnimate()


    @JvmStatic
    fun ImageView.load(path: String) {
        this.context.apply {
            if (this is Activity) {
                if (this.isFinishing || this.isDestroyed) return
            }
            Glide.with(this)
                .load(path)
                .apply(mRequestOption)
                .into(this@load)
        }
    }

    @JvmStatic
    fun ImageView.load(path: String, @DrawableRes placeholderRes: Int) {
        this.context.apply {
            if (this is Activity) {
                if (this.isFinishing || this.isDestroyed) return
            }
            Glide.with(this)
                .load(path)
                .apply(mRequestOption)
                .into(this@load)
        }
    }


}