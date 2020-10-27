package com.demo.wechat.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.demo.wechat.R;

import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class GlideUtil extends AppGlideModule {

    // 加载方形图片
    public static void loadsAquareImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions(new CropSquareTransformation()))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 加载圆角
    public static void loadGrayscaleImage(Context context, String url, ImageView imageView, int radius) {
        Glide.with(context).load(url).apply(initOptions(new RoundedCornersTransformation(radius, 0, RoundedCornersTransformation.CornerType.ALL)))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    /**
     * NONE 表示什么都不缓存
     * DATA 表示缓存原始图片
     * RESOURCE 缓存转换过后的图片
     * ALL 缓存所有的图片
     * AUTOMATIC 根据图片资源选择适合的
     *
     * @return
     */
    private static RequestOptions initOptions(BitmapTransformation bitmapTransformation) {
        return new RequestOptions()
                .transform(bitmapTransformation)
                .skipMemoryCache(isSkipMemoryCache())// 是否允许内存缓存
                .onlyRetrieveFromCache(true)// 是否从缓存中加载图片
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
    }

    private static boolean isSkipMemoryCache() {
        return true;
    }

    // 设置默认的加载错误的图片
    private static int getErrorImage() {
        return R.mipmap.ic_launcher;
    }

    // 设置占位图
    private static int getPlaceholder() {
        return R.mipmap.ic_launcher;
    }
}
