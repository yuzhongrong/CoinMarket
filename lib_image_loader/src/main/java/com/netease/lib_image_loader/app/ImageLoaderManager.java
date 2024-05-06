package com.netease.lib_image_loader.app;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;
import static com.netease.lib_network.constants.config.ipfs_base_url;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PictureDrawable;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.netease.lib_image_loader.R;
import com.netease.lib_image_loader.image.CornerTransform;
import com.netease.lib_image_loader.image.CustomRequestListener;
import com.netease.lib_image_loader.image.ImageUtils;

import java.io.File;
import java.util.concurrent.ExecutionException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class ImageLoaderManager {

	private ImageLoaderManager() {
	}

	public static ImageLoaderManager getInstance() {
		return ImageLoaderManager.SingletonHolder.instance;
	}


	//静态内部类
	private static class SingletonHolder {
		private static ImageLoaderManager instance = new ImageLoaderManager();
	}

	/**
	 * 加载ImageView的图片
	 */
	public void displayImageForView(ImageView view, String url){
		Glide.with(view.getContext())
				.asBitmap()
				.load(url)
				.apply(initCommonRequestOption())
				.transition(withCrossFade())
				.into(view);

	}

	public File getImageFile(Context context, String url) throws ExecutionException, InterruptedException {
		return Glide.with(context)
				.downloadOnly()
				.load(url)
				.submit()
				.get();

	}
	/**
	 * 加载圆形图片
	 * @param imageView
	 * @param url
	 */
	public void displayImageForCircle(final ImageView imageView, final String url) {
		Glide.with(imageView.getContext())
				.asBitmap()
				.load(url)
				.apply(initCommonRequestOption())
				.into(new BitmapImageViewTarget(imageView) {
					@Override
					protected void setResource(final Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
				});
	}



	/**
	 * 加载圆角图片
	 * @param imageView
	 * @param url
	 */
	public void displayImageForCorner(final ImageView imageView, String url, int corner) {
		CornerTransform transformation = new CornerTransform(imageView.getContext(), ImageUtils.dip2px(imageView.getContext(), corner));
		transformation.setExceptCorner(false, false, false, false);

		Glide.with(imageView.getContext())
				.asBitmap()
				.load(url)
				.apply(initCommonRequestOption())
				.transform(transformation)
				.into(imageView);


	}


	public void displayImageForSvg(final ImageView imageView, String url) {

//		RequestBuilder<PictureDrawable> requestBuilder = Glide.with(imageView.getContext())
//				.as(PictureDrawable.class)
////				.transition(withCrossFade())
//				.listener(new SvgSoftwareLayerSetter());
//
//		requestBuilder.load(url).into(imageView);

	}


	public void displayLocalImageForCorner(final ImageView imageView, int resId, int corner) {
		CornerTransform transformation = new CornerTransform(imageView.getContext(), ImageUtils.dip2px(imageView.getContext(), corner));
		transformation.setExceptCorner(false, false, false, false);
		Glide.with(imageView.getContext())
				.asBitmap()
				.load(resId)
				.apply(initCommonRequestOption())
				.transform(transformation)
				.into(imageView);


	}

	public void displayLocalImageForCorner(final ImageView imageView, int resId, int corner,int weith,int hight) {
		CornerTransform transformation = new CornerTransform(imageView.getContext(), ImageUtils.dip2px(imageView.getContext(), corner));
		transformation.setExceptCorner(false, false, false, false);
		Glide.with(imageView.getContext())
				.asBitmap()
				.load(resId)
				.override(weith,hight)
				.apply(initCommonRequestOption())
				.transform(transformation)
				.into(imageView);


	}



	public void displayImageForCornerIpfs(final ImageView imageView, String url, int corner) {
		String resultUrl=ipfs_base_url+url;
		Log.d("---resultUrl-->",resultUrl);
//		if(url!=null&&url.startsWith("ipfs://")){
//			resultUrl=ipfs_base_url+url.substring(7);
//		}else{
//			resultUrl=url;
//		}
		CornerTransform transformation = new CornerTransform(imageView.getContext(), ImageUtils.dip2px(imageView.getContext(), corner));
		transformation.setExceptCorner(false, false, false, false);
		Glide.with(imageView.getContext())
				.asBitmap()
				.load(resultUrl)
				.apply(initCommonRequestOption())
				.transform(transformation)
				.into(imageView);



	}

	/**
	 * 加载圆角图片 圆角5dp
	 * @param imageView
	 * @param url
	 */
	public void displayImageForCorner(final ImageView imageView, String url) {
		displayImageForCorner(imageView, url, 5);
	}


	/**
	 * 加载圆角图片 圆角5dp
	 * @param imageView
	 * @param url
	 */
	public void displayImageForCorner1(final ImageView imageView, String url,int corner) {
//		displayImageForCorner(imageView, url, corner);
		Log.d("ImageLoaderManager ","load img start");
		displayImageForCornerIpfs(imageView, url, corner);
	}

	public void displayImageForViewGroup(final ViewGroup group, String url, final int radius) {
		Glide.with(group.getContext())
				.asBitmap()
				.load(url)
				.apply(initCommonRequestOption())
				.into(new SimpleTarget<Bitmap>() {//设置宽高
					@Override
					public void onResourceReady(@NonNull Bitmap resource,
												@Nullable Transition<? super Bitmap> transition) {
						final Bitmap res = resource;
						Disposable subscribe = Observable.just(resource)
								.map(new Function<Bitmap, Drawable>() {
									@Override
									public Drawable apply(Bitmap bitmap) {
										return new BitmapDrawable(
												//毛玻璃效果
												ImageUtils.doBlur(res, radius, true)
										);
									}
								})
								.subscribeOn(Schedulers.io())
								.observeOn(AndroidSchedulers.mainThread())
								.subscribe(new Consumer<Drawable>() {
									@Override
									public void accept(Drawable drawable) {
										group.setBackground(drawable);
									}
								});
					}
				});
	}
	public void displayImageForViewGroup(final ImageView view, String url, final int radius) {
		Glide.with(view.getContext())
				.asBitmap()
				.load(url)
				.apply(initCommonRequestOption())
				.into(new SimpleTarget<Bitmap>() {//设置宽高
					@Override
					public void onResourceReady(@NonNull Bitmap resource,
												@Nullable Transition<? super Bitmap> transition) {
						final Bitmap res = resource;
						Disposable subscribe = Observable.just(resource)
								.map(new Function<Bitmap, Drawable>() {
									@Override
									public Drawable apply(Bitmap bitmap) {
										//毛玻璃效果
										return new BitmapDrawable(ImageUtils.doBlur(res, radius, true));
									}
								})
								.subscribeOn(Schedulers.io())
								.observeOn(AndroidSchedulers.mainThread())
								.subscribe(new Consumer<Drawable>() {
									@Override
									public void accept(Drawable drawable) {
										view.setBackground(drawable);
									}
								});
					}
				});
	}
	public void displayImageForNotification(Context context, RemoteViews rv, int id,
											Notification notification, int NOTIFICATION_ID, String url) {
		this.displayImageForTarget(context,
				initNotificationTarget(context, id, rv, notification, NOTIFICATION_ID), url);
	}
	/*
	 * 初始化Notification Target
	 */
	private NotificationTarget initNotificationTarget(Context context, int id, RemoteViews rv,
													  Notification notification, int NOTIFICATION_ID) {
		return new NotificationTarget(context, id, rv, notification, NOTIFICATION_ID);
	}

	private void displayImageForTarget(Context context, Target target, String url) {
		this.displayImageForTarget(context, target, url, null);
	}

	/**
	 * 为非view加载图片
	 */
	private void displayImageForTarget(Context context, Target target, String url,
									   CustomRequestListener requestListener) {
		Glide.with(context)
				.asBitmap()
				.load(url)
				.apply(initCommonRequestOption())
				.transition(withCrossFade())
				.fitCenter()
				.listener(requestListener)
				.into(target);
	}

	@SuppressLint("CheckResult")
	private RequestOptions initCommonRequestOption() {

		return new RequestOptions()
				//.placeholder(R.mipmap.ic_album_demo)//loading时显示的图片
				.error(R.mipmap.ic_unlink)//load失败时显示的图片
				.timeout(30000)
				.diskCacheStrategy(DiskCacheStrategy.ALL)//缓存策略
				.skipMemoryCache(false)//使用内存缓存
				.priority(Priority.NORMAL);
	}
}
