package me.goldze.mvvmhabit.binding.viewadapter.image;

import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import me.goldze.mvvmhabit.utils.ImageLoader;

/**
 * Created by goldze on 2017/6/18.
 */
public final class ViewAdapter {
    @BindingAdapter(value = {"url", "placeholderRes"}, requireAll = false)
    public static void setImageUri(ImageView imageView, String url, int placeholderRes) {
        if (!TextUtils.isEmpty(url)) {
            if (placeholderRes == 0) {
                ImageLoader.load(imageView, url);
            } else {
                ImageLoader.load(imageView, url, placeholderRes);
            }
        }
    }
}

