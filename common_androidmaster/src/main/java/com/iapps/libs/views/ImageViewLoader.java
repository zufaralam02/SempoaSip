package com.iapps.libs.views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.iapps.common_library.R;
import com.iapps.external.photoview.PhotoView;
import com.iapps.libs.helpers.BaseConstants;
import com.iapps.libs.helpers.BaseHelper;
import com.iapps.libs.helpers.BaseUIHelper;
import com.iapps.libs.helpers.CircleTransform;
import com.iapps.libs.helpers.RoundedShadowTransform;
import com.iapps.libs.objects.ListenerDoubleTap;
import com.iapps.libs.objects.ListenerLoad;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.util.Calendar;

public class ImageViewLoader
        extends RelativeLayout implements View.OnClickListener {
    private ImageView   image;
    private ImageView   imageOverlay;
    private ProgressBar progress;
    @SuppressWarnings("unused")
    private boolean isFade   = true, isSquareToWidth = false,
            isSquareToHeight = false, popup = false, isCircular = false;
    private long         delay;
    private int          placeholder;
    private int          imgFail;
    private ListenerLoad listenerLoad;
    private boolean      doWrapHeight, doWrapWidth, doBlur;
    public int imgHeight, imgWidth;

    public ImageViewLoader(Context context) {
        this(context, null);
    }

    public ImageViewLoader(Context context, AttributeSet attr) {
        super(context, attr);

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.image_view_loader, this, true);

        image = (ImageView) this.findViewById(R.id.image_loader);
        imageOverlay = (ImageView) this.findViewById(R.id.imgOverlay);
        progress = (ProgressBar) this.findViewById(R.id.loader);

        // Default scale type
        image.setScaleType(ScaleType.CENTER_CROP);
    }

    public void wrapToHeight() {
        doWrapHeight = true;
        image.setScaleType(ScaleType.FIT_CENTER);
        image.setAdjustViewBounds(true);
    }

    public void wrapToWidth() {
        doWrapWidth = true;
        image.setScaleType(ScaleType.FIT_CENTER);
        image.setAdjustViewBounds(true);
    }

    public void hideProgress() {
        progress.setVisibility(View.GONE);
        this.image.setVisibility(View.VISIBLE);
    }

    public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        this.image.setVisibility(View.INVISIBLE);
    }

    public void showFail() {
        hideProgress();
        if (imgFail == 0) {
            image.setScaleType(ScaleType.FIT_CENTER);
            image.setImageResource(R.drawable.ic_no_image);
        } else {
            image.setImageResource(imgFail);
        }
        this.image.setVisibility(View.VISIBLE);
    }

    // ================================================================================
    // Image Loader Functions
    // ================================================================================
    public void loadImage(String url) {
        this.loadImage(url, 0, false);
    }

    public void loadImage(String url, boolean isCircular) {
        this.loadImage(url, 0, isCircular);
    }

    public void loadImage(String url, int resPlaceHolder, boolean isCircular) {
        this.isCircular = isCircular;

        if (isCircular)
            this.loadImage(url, resPlaceHolder, new CircleTransform());
        else
            this.loadImage(url, resPlaceHolder, null);
    }

    /**
     * Rounded corner & shadow
     *
     * @param url
     * @param radius
     * @param margin
     */
    public void loadImage(String url, int radius, int margin) {
        this.loadImage(
                url,
                0,
                new RoundedShadowTransform((int) BaseUIHelper
                        .convertDpToPixel(radius, getContext()), margin));
    }

    /**
     * @param url
     * @param resPlaceHolder
     * @param radius
     * @param margin
     */
    public void loadImage(String url, int resPlaceHolder, int radius, int margin) {
        this.loadImage(url, resPlaceHolder, new RoundedShadowTransform(radius, margin));
    }

    public void loadImage(final String url, int resPlaceHolder, Transformation transformation) {
        this.placeholder = resPlaceHolder;
        showProgress();

        if (BaseHelper.isEmpty(url) || url.equalsIgnoreCase("0") || url.startsWith("https://localhost") ||
                url.endsWith("uploads/")) {
            hideProgress();
            if (placeholder == 0)
                showFail();
            else
                loadImage(placeholder);
            return;
        }

        Log.d("ImageViewLoader", "Load : " + url);
        if (this.image != null && this.progress != null) {
            if (this.placeholder > 0) {
                this.image.setImageResource(placeholder);
            }

            DisplayImageOptions options = null;
            if (transformation == null)
                options = new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();
            else
                options = new DisplayImageOptions.Builder()
                        .displayer(new RoundedBitmapDisplayer(1000))
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .build();

            ImageLoader.getInstance().displayImage(url, this.image, options, new ImageLoadingListener() {
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                    showProgress();
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    if (placeholder == 0)
                        showFail();
                    else
                        loadImage(placeholder);

                    Log.d(BaseConstants.LOG, "Failed to load : " + url);

                    if (listenerLoad != null)
                        listenerLoad.onFail();

                    hideProgress();
                }

                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    if (listenerLoad != null)
                        listenerLoad.onSuccess();
                    Log.d(BaseConstants.LOG, "Load : " + url);
                    hideProgress();

                    imgHeight = loadedImage.getHeight();
                    imgWidth = loadedImage.getWidth();

                    if (doBlur) {
                        Bitmap blurredBitmap = BlurBuilder.blur(getContext(), loadedImage);
                        image.setImageBitmap(blurredBitmap);
                    }
                }

                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                    // TODO Auto-generated method stub

                }
            });
        }
    }

    public void loadImage(int resImage) {
        if (resImage != 0) {
            if (!this.isCircular)
                Picasso
                        .with(this.getContext())
                        .load(resImage)
                        .into(this.image, new Callback() {
                            @Override
                            public void onSuccess() {
                                if (doBlur) {
                                    Bitmap blurredBitmap = BlurBuilder.blur(getContext(),
                                                                            ((BitmapDrawable) image.getDrawable()).getBitmap());
                                    image.setImageBitmap(blurredBitmap);
                                }
                            }

                            @Override
                            public void onError() {
                            }
                        });
            else
                Picasso
                        .with(this.getContext())
                        .load(resImage)
                        .transform(new RoundedShadowTransform(this.getHeight(), 0))
                        .into(this.image, new Callback() {
                            @Override
                            public void onSuccess() {

                                if (doBlur) {
                                    Bitmap blurredBitmap = BlurBuilder.blur(getContext(),
                                                                            ((BitmapDrawable) image.getDrawable()).getBitmap());
                                    image.setImageBitmap(blurredBitmap);
                                }
                            }

                            @Override
                            public void onError() {

                            }
                        });
        }
        hideProgress();
    }

    // ================================================================================
    // Getter & Setter
    // ================================================================================
    public ImageView getImage() {
        return image;
    }

    public boolean isFade() {
        return isFade;
    }

    public void setFade(boolean isFade) {
        this.isFade = isFade;
    }

    public void setOnDoubleTapListener(ListenerDoubleTap listenerDoubleTap) {
        this.setOnClickListener(new ListenerClick(listenerDoubleTap));
    }

    public void hideOverlay() {
        this.imageOverlay.setVisibility(View.GONE);
    }

    public void setImageOverlay(int res) {
        if (res == 0)
            this.imageOverlay.setVisibility(View.GONE);
        else
            this.imageOverlay.setImageResource(res);
    }

    public void setListenerLoad(ListenerLoad listenerLoad) {
        this.listenerLoad = listenerLoad;
    }

    public ImageViewLoader setImgFail(int imgFail) {
        this.imgFail = imgFail;
        return this;
    }

    public boolean isCircular() {
        return isCircular;
    }

    public ImageViewLoader setCircular(boolean circular) {
        isCircular = circular;
        return this;
    }

    // ================================================================================
    // Double Tap Functions
    // ================================================================================
    public class ListenerClick implements OnClickListener {
        private ListenerDoubleTap listener;

        public ListenerClick(ListenerDoubleTap listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            long curTime  = Calendar.getInstance().getTimeInMillis();
            long diffTime = curTime - delay;

            if (diffTime < 500) {
                listener.onDoubleTap(v);
                delay = 0;
            } else {
                delay = curTime;
            }

        }
    }

    // ================================================================================
    // Popup
    // ================================================================================
    public void setPopupOnClick(boolean popup) {
        this.popup = popup;

        if (popup) {
            this.setOnClickListener(this);
        }
    }

    public void popupImage() {
        final Dialog popup = new Dialog(getContext());
        popup.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = ((LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.popup_image,
                                                                            null);
        popup.setContentView(view);

        PhotoView img = (PhotoView) view.findViewById(R.id.img);
        img.setImageDrawable(this.image.getDrawable());
        img.setSquareToWidth(true);
        img.setZoomable(true);
        img.setScaleType(ScaleType.FIT_CENTER);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(popup.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = (int) (BaseUIHelper.getScreenWidth(getContext()) - BaseUIHelper.convertDpToPixel(32, getContext()));
        popup.show();
        popup.getWindow().setAttributes(lp);

        // img.set
        // img.setImageOverlay(0);
        // img.hideProgress();

        popup.setCancelable(true);
        popup.show();
    }

    @Override
    public void onClick(View v) {
        popupImage();
    }

    // ================================================================================
    // Make it always square, adjusting to width
    // ================================================================================
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (isSquareToWidth) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
            // image.getLayoutParams().height = widthMeasureSpec;
        } else if (isSquareToHeight) {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
            // image.getLayoutParams().width = heightMeasureSpec;
        } else
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public boolean isSquareToWidth() {
        return isSquareToWidth;
    }

    public boolean isSquareToHeight() {
        return isSquareToHeight;
    }

    public void setSquareToWidth(boolean isSquareToWidth) {
        this.isSquareToWidth = isSquareToWidth;
    }

    public void setSquareToHeight(boolean isSquareToHeight) {
        this.isSquareToHeight = isSquareToHeight;
    }

    // ================================================================================
    // Convert to upload format
    // ================================================================================
    public String getUploadFormat() {
        try {
            Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
            if (bitmap != null) {
                showProgress();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream);
                byte[] image = stream.toByteArray();

                hideProgress();
                return Base64.encodeToString(image, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // ================================================================================
    // Blur
    // ================================================================================
    public ImageViewLoader blur() {
        doBlur = true;
        return this;
    }

    public static class BlurBuilder {
        private static final float BITMAP_SCALE = 0.7f;
        private static final float BLUR_RADIUS  = 25f;

        public static Bitmap blur(Context context, Bitmap image) {
            int width  = Math.round(image.getWidth() * BITMAP_SCALE);
            int height = Math.round(image.getHeight() * BITMAP_SCALE);

            Bitmap inputBitmap  = Bitmap.createScaledBitmap(image, width, height, false);
            Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

            RenderScript        rs           = RenderScript.create(context);
            ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            Allocation          tmpIn        = Allocation.createFromBitmap(rs, inputBitmap);
            Allocation          tmpOut       = Allocation.createFromBitmap(rs, outputBitmap);
            theIntrinsic.setRadius(BLUR_RADIUS);
            theIntrinsic.setInput(tmpIn);
            theIntrinsic.forEach(tmpOut);
            tmpOut.copyTo(outputBitmap);

            return outputBitmap;
        }
    }
}
