package com.iapps.libs.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Point;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.iapps.common_library.R;
import com.squareup.picasso.Callback.EmptyCallback;
import com.squareup.picasso.Picasso;

public class BaseUIHelper {
    // ================================================================================
    // Base Functions
    // ================================================================================
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenHeight(Context ctx) {
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int     height;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            height = display.getHeight(); // deprecated
        } else {
            Point size = new Point();
            display.getSize(size);
            height = size.y;
        }

        return height;
    }

    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static int getScreenWidth(Context ctx) {
        WindowManager wm = (WindowManager) ctx
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int     width;

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            width = display.getWidth(); // deprecated

        } else {
            Point size = new Point();
            display.getSize(size);
            width = size.x;

        }

        return width;
    }

    /**
     * This method convets dp unit to equivalent device specific value in pixels.
     *
     * @param dp      A value in dp(Device independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     *
     * @return A float value to represent Pixels equivalent to dp according to device
     */
    public static float convertDpToPixel(float dp, Context context) {
        Resources      resources = context.getResources();
        DisplayMetrics metrics   = resources.getDisplayMetrics();
        float          px        = dp * (metrics.densityDpi / 160f);
        return px;
    }

    /**
     * This method converts device specific pixels to device independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     *
     * @return A float value to represent db equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources      resources = context.getResources();
        DisplayMetrics metrics   = resources.getDisplayMetrics();
        float          dp        = px / (metrics.densityDpi / 160f);
        return dp;

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            inputManager.hideSoftInputFromWindow(activity.getCurrentFocus()
                                                         .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static void rotate(float direction, ImageView compass) {
        if (compass == null) {
            return;
        }
        Matrix matrix = new Matrix();
        matrix.postRotate(direction, compass.getMeasuredWidth() / 2,
                          compass.getMeasuredHeight() / 2);
        compass.setImageMatrix(matrix);
    }

    /**
     * Load image from url
     *
     * @param context
     * @param url
     * @param resPlaceholder
     * @param img
     */
    public static void loadImage(
            Context context, final String url, int resPlaceholder, final ImageView img) {
        // Init animation & start animating with no scale type set to imageview
        // To avoid cropped progressBar
        Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        img.setScaleType(ScaleType.FIT_CENTER);
        img.startAnimation(rotate);

        // Load normally
        Picasso
                .with(context)
                .load(url)
                .placeholder(resPlaceholder)
                .noFade()

                .into(img, new EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        super.onSuccess();
                        // Stop animation
                        img.clearAnimation();

                        // Set image back to centerCrop
                        img.setScaleType(ScaleType.CENTER_CROP);
                    }

                    @Override
                    public void onError() {
                        super.onError();
                        // Stop animation
                        img.clearAnimation();

                        // Show cross image
                        img.setImageResource(R.drawable.ic_cross_dark);
                    }
                });
    }

    // ================================================================================
    // Animation
    // ================================================================================
    public static void animSlideInFromBottom(View view) {
        BaseUIHelper.animSlideIn(view, R.anim.up_from_bottom);
    }

    public static void animSlideInFromTop(View view) {
        BaseUIHelper.animSlideIn(view, R.anim.down_from_top);
    }

    private static void animSlideIn(View view, int resAnim) {
        view.setAlpha(1.0f);
        Animation animation = AnimationUtils.loadAnimation(view.getContext(), resAnim);
        view.startAnimation(animation);
        view.setVisibility(View.VISIBLE);
    }

    public static void animSlideOutFromTop(final View view) {
        view.animate()
            .translationY(0)
            .setDuration(100)
            .alpha(0.0f)
            .setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.GONE);
                }
            });
    }

    public static void animSlideOutFromBottom(final View view) {
        TranslateAnimation animate = new TranslateAnimation(0, 0, 0, -view.getHeight());
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        view.setVisibility(View.GONE);
    }

    public static void animRotate(View view) {
        Animation animRotate = AnimationUtils.loadAnimation(view.getContext(),
                                                            R.anim.rotate);
        view.startAnimation(animRotate);
    }

    public static void animFadeIn(View view) {
        BaseUIHelper.animate(view, R.anim.fadein);
    }

    public static void animFadeOut(View view) {
        BaseUIHelper.animate(view, R.anim.fadeout);
    }

    public static void animate(View view, int resAnim) {
        Animation animFadein = AnimationUtils.loadAnimation(view.getContext(),
                                                            resAnim);
        view.startAnimation(animFadein);
    }

    public static void animExpand(final View v) {
        v.measure(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        final int targtetHeight = v.getMeasuredHeight();

        v.getLayoutParams().height = 0;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LayoutParams.WRAP_CONTENT
                        : (int) (targtetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        a.setDuration((int) (targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public static void animCollapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    // ================================================================================
    // SwipeRefresh
    // ================================================================================

    @SuppressLint("InlinedApi")
    public static void setRefreshColor(SwipeRefreshLayout sr) {
        sr.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    // ================================================================================
    // Color Functions
    // ================================================================================
    public static int getLighterColor(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        if (r >= 180 && g >= 180 && b >= 180) {
            // Darken color
            r = (int) (r * (1 - 0.1));
            g = (int) (g * (1 - 0.1));
            b = (int) (b * (1 - 0.1));
        } else {
            // Lighten color
            r = r + (int) (0.15 * (255 - r));
            g = g + (int) (0.15 * (255 - g));
            b = b + (int) (0.15 * (255 - b));
        }

        return Color.parseColor("#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b));
    }

    public static int getDarkerColor(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        if (r >= 180 && g >= 180 && b >= 180) {
            // Lighten color
            r = r + (int) (0.15 * (255 - r));
            g = g + (int) (0.15 * (255 - g));
            b = b + (int) (0.15 * (255 - b));
        } else {
            // Darken color
            r = (int) (r * (1 - 0.1));
            g = (int) (g * (1 - 0.1));
            b = (int) (b * (1 - 0.1));
        }

        return Color.parseColor("#" + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b));
    }

    public static boolean isColorLight(int color) {
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);

        if (r >= 180 && g >= 180 && b >= 180) {
            return true;
        }

        return false;
    }
}
