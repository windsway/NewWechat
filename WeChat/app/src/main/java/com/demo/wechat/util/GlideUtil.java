package com.demo.wechat.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;
import com.demo.wechat.R;

import jp.wasabeef.glide.transformations.BitmapTransformation;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;

public class GlideUtil extends AppGlideModule {

    public static void loadImg(Context context, Object obj, ImageView imageView) {
        if (obj instanceof String) {
            Glide.with(context).load(obj).apply(initOptions())
                    .skipMemoryCache(isSkipMemoryCache()).error(getErrorImage())
                    .fallback(getErrorImage()).placeholder(getPlaceholder()).into(imageView);
        }
        if (obj instanceof Bitmap) {
            Glide.with(context).load(obj).apply(initOptions())
                    .skipMemoryCache(isSkipMemoryCache()).error(getErrorImage())
                    .fallback(getErrorImage()).placeholder(getPlaceholder()).into(imageView);
        }
        if (obj instanceof Drawable) {
            Glide.with(context).load(obj).apply(initOptions())
                    .skipMemoryCache(isSkipMemoryCache()).error(getErrorImage())
                    .fallback(getErrorImage()).placeholder(getPlaceholder()).into(imageView);
        }

    }

    // 加载圆形图片
    public static void loadCicrleImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions())
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

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

    // 加载黑白图片
    public static void loadGrayscaleImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions(new GrayscaleTransformation()))
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

    // 加载圆形图片
    public static void loadGrayscaleImage(Context context, String url, ImageView imageView, int radius, RoundedCornersTransformation.CornerType cornerType) {
        Glide.with(context).load(url).apply(initOptions(new RoundedCornersTransformation(radius, 0, cornerType)))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 裁剪图片尺寸大小
    public static void loadCropTransformationImage(Context context, String url, ImageView imageView, int width, int height, CropTransformation.CropType cropType) {
        Glide.with(context).load(url).apply(initOptions(new CropTransformation(width, height, cropType)))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 加载gif
    public static void loadGifImage(Context context, String url, ImageView imageView) {
        Glide.with(context).asGif().apply(initOptions())
                .skipMemoryCache(isSkipMemoryCache())
                .load(url)
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 加载高斯模糊
    public static void loadTransformImage(String url, ImageView imageView, int ambiguity) {
        Glide.with(imageView.getContext()).load(url).skipMemoryCache(isSkipMemoryCache())
                .fallback(getErrorImage())
                .placeholder(getPlaceholder())
                .error(getErrorImage())
                .apply(initOptions(new BlurTransformation(ambiguity)))
                .into(imageView);
    }

    // 加载缩略图
    public static void loadThumbnailImage(String url, ImageView imageView, float sizeMultiplier) {
        Glide.with(imageView.getContext()).load(url)
                .skipMemoryCache(isSkipMemoryCache())
                .thumbnail(sizeMultiplier)//缩略的参数
                .apply(initOptions())
                .into(imageView);
    }

    // 设置滤镜（陈旧）
    public static void loadSepiaFilterTransformationImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions(new SepiaFilterTransformation(1.0f)))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 滤镜（高亮）
    public static void loadBrightnessFilterTransformationImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions(new BrightnessFilterTransformation(0.5f)))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage())
                .placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 滤镜马赛克
    public static void loadPixelationFilterTransformationImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions(new PixelationFilterTransformation(20f)))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage()).placeholder(getPlaceholder())
                .fallback(getErrorImage())
                .circleCrop()
                .into(imageView);
    }

    // 设置滤镜（素描）
    public static void loadSketchFilterTransformationImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).apply(initOptions(new SketchFilterTransformation()))
                .skipMemoryCache(isSkipMemoryCache())
                .error(getErrorImage()).placeholder(getPlaceholder())
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

    private static RequestOptions initOptions() {
        return new RequestOptions().skipMemoryCache(isSkipMemoryCache()).onlyRetrieveFromCache(true).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
    }

    private static boolean isSkipMemoryCache() {
        return true;
    }

    // 清除内容缓存
    public static void clearMemory(Context context) {
        Glide.get(context).clearMemory();
    }


    // 清除磁盘缓存
    public static void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    // 设置加载效果
    private static RequestOptions bitmapTransform(BitmapTransformation transformation) {
        return new RequestOptions();
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
