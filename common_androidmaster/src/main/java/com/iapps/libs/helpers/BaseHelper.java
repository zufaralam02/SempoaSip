package com.iapps.libs.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.PhoneNumberUtils;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.iapps.adapters.BaseRecyclerAdapter;
import com.iapps.common_library.R;
import com.iapps.libs.Drag.BaseDragAdapter;
import com.iapps.libs.generics.GenericFragment;
import com.iapps.libs.views.ImageViewLoader;
import com.miguelbcr.ui.rx_paparazzo.RxPaparazzo;
import com.miguelbcr.ui.rx_paparazzo.entities.Options;
import com.miguelbcr.ui.rx_paparazzo.entities.Response;
import com.miguelbcr.ui.rx_paparazzo.entities.size.CustomMaxSize;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.format.DateTimeFormat;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rx.functions.Action1;

public class BaseHelper {
    private static final String  MIN_AGO                = "min ago.";
    private static final String  MINS_AGO               = "mins ago.";
    private static final String  HOUR_AGO               = "hour ago.";
    private static final String  HOURS_AGO              = "hours ago.";
    private static final String  DAY_AGO                = "day ago.";
    private static final String  DAYS_AGO               = "days ago.";
    private static final String  MIN_LEFT               = "min left.";
    private static final String  MINS_LEFT              = "mins left.";
    private static final String  HOUR_LEFT              = "hour left.";
    private static final String  HOURS_LEFT             = "hours left.";
    private static final String  DAY_TO_GO              = "day to go.";
    private static final String  DAYS_TO_GO             = "days to go.";
    public static final  int     REQUEST_EMAIL_CODE     = 7221;
    public static final  int     REQUEST_GET_IMAGE_CODE = 9882;
    public static final  String  MIME_CSV               = "text/csv";
    public static final  String  TEMP_PHOTO_FILE        = "tmp.JPEG";
    public static final  String  DATE_JSON              = "yyyy-MM-dd";
    public static final  String  DATE_JSON_FULL         = "yyyy-MM-dd HH:mm:ss";
    public static final  String  DATE_YMD               = "yyyy-MM-dd";
    public static final  String  DATE_MD                = "dd MMM";
    public static final  String  DATE_MDY               = DATE_MD + ", yyyy";
    public static final  String  DATE_MDYHMS            = DATE_MDY + " HH:mm:ss";
    public static final  String  DATE_EDMY              = "EE, dd MMMM yy";
    public static final  String  DATE_EDMYHMS           = "EE, dd MMMM yyyy HH:mm:ss";
    public static final  String  DATE_HA                = "h a";
    public static final  String  DATE_HMA               = "h mm a";
    public static final  String  DATE_HM                = "HH:mm";
    public static final  String  YES                    = "Y";
    public static final  String  PLAY_STORE_LINK        = "https://play.google.com/store/apps/details?id=";
    // Debug state
    public static final  String  LOG                    = "log";
    public static final  boolean IS_DEBUGGING           = true;
    public static final  String  EXACT                  = "exact";
    public static final  String  START                  = "start";
    public static final  String  END                    = "end";
    public static final  String  ANYWHERE               = "anywhere";

    /**
     * Check if a string is empty (length == 0)
     *
     * @param string , to be checked
     *
     * @return true if empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.trim().length() == 0 || string.equalsIgnoreCase("null");
    }

    /**
     * Change the state of a view to View.GONE
     *
     * @param v , view to be updated
     */
    public static void goneView(View v) {
        if (v != null) {
            v.setVisibility(View.GONE);
        }

    }

    /**
     * Change the state of a view to View.VISIBLE
     *
     * @param v , view to be updated
     */
    public static void visibleView(View v) {
        if (v != null) {
            v.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Change the state of a view to View.INVISIBLE
     *
     * @param v , view to be updated
     */
    public static void invisibleView(View v) {
        if (v != null) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    public final static boolean isValidEmail(String emailAddress) {
        if (TextUtils.isEmpty(emailAddress)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches();
        }
    }

    public static boolean isSameDay(DateTime first, DateTime second) {
        return DateTimeComparator.getDateOnlyInstance().compare(first, second) == 0 ? true : false;
    }

    public static boolean isEmpty(EditText edt) {
        return isEmpty(edt.getText().toString());
    }

    public static String formatDateTime(DateTime date, String format) {
        return DateTimeFormat.forPattern(format).withLocale(Locale.US).print(date);
    }

    public static String formatDateTime(String dateTime, String formatFrom, String formatTo) {
        try {
            return DateTime.parse(dateTime, DateTimeFormat.forPattern(formatFrom).withLocale(Locale.US))
                           .toString(formatTo);
        } catch (Exception e) {
            return "";
        }
    }

    public static DateTime parseDateTime(String dateString, String format) {
        if (isEmpty(dateString))
            return null;
        return DateTime.parse(dateString, DateTimeFormat.forPattern(format).withLocale(Locale.US));
    }

    public static String extractDate(EditText edt) {
        return BaseHelper.formatDateTime(edt.getText().toString(), DATE_MDY,
                                         DATE_JSON);
    }

    public static String showDate(DateTime date) {
        return formatDateTime(date, DATE_MDY);
    }

    public static String showTime(DateTime time) {
        return formatDateTime(time, DATE_HMA);
    }

    public static DateTime dateFromJson(String time) {
        return BaseHelper.parseDateTime(time, DATE_JSON);
    }

    public static DateTime dateFromJsonFull(String time) {
        return BaseHelper.parseDateTime(time, DATE_JSON_FULL);
    }

    public static String showTimeNoMinute(DateTime time) {
        return formatDateTime(time, DATE_HA);
    }

    public static String extractTime(EditText edt) {
        return BaseHelper.formatDateTime(edt.getText().toString(), DATE_HMA,
                                         DATE_HM);
    }

    public static String extractTimeNoMinute(EditText edt) {
        return BaseHelper.formatDateTime(edt.getText().toString(), DATE_HA,
                                         "H");
    }

    public static String formatDouble(double d) {
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        d = Math.round(d);
        return formatter.format(d);
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isAlphaNumeric(String s) {

        String pattern = "^[a-zA-Z0-9 ]*$";
        return s.matches(pattern);
    }

    public static String getHashKey(Context ctx) {
        try {
            PackageInfo info = ctx.getPackageManager().getPackageInfo(
                    ctx.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String key = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:", key);

                return key;
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * Format distance to be displayed to the user
     *
     * @param d , distance in metres
     *
     * @return formatted {@link String}
     */
    public static String formatDistance(double d) {
        DecimalFormat formatter = new DecimalFormat("#,### 'm'");
        d = Math.round(d);
        if (d > 1000) {
            formatter = new DecimalFormat("#,###.# 'km'");
            d = d / 1000;
        }

        return formatter.format(d);
    }

    public static String formatCurrency(double d) {
        DecimalFormat formatter = new DecimalFormat("'$'#,##0.00");
        return formatter.format(d);
    }

    public static String formatRupiah(double d) {
        // Use this weird formatter, because IDR & $ are having different ,. behavior
        DecimalFormat formatter = new DecimalFormat("'Rp. '#,###,###,###.##");
        return formatter.format(round(d, 2));
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    public static String breakLine(String text) {
        return text.replace("\\n", System.getProperty("line.separator"));
    }

    public static GridLayoutManager setupRecyclerView(RecyclerView rv, BaseRecyclerAdapter adapter) {
        final GridLayoutManager mLayoutManager = new WrapContentLinearLayoutManager(adapter.getContext(),
                                                                                    adapter.getNumGrid(),
                                                                                    GridLayoutManager.VERTICAL, false);
        if (adapter.showHeader())
            mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position == 0 ? mLayoutManager.getSpanCount() : 1;
                }
            });
        else
            mLayoutManager.setSpanCount(adapter.getNumGrid());
        rv.setLayoutManager(mLayoutManager);
        rv.setAdapter(adapter);
//        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(rv.getContext(), R.dimen.margin_small_x);
//        rv.addItemDecoration(itemDecoration);
        rv.setNestedScrollingEnabled(false);

        return mLayoutManager;
    }

    public static GridLayoutManager setupDragGrid(RecyclerView recyclerView, BaseDragAdapter adapter, int column) {
        // Setup D&D feature and RecyclerView
        RecyclerViewDragDropManager dragMgr = new RecyclerViewDragDropManager();

        dragMgr.setInitiateOnMove(false);
        dragMgr.setInitiateOnLongPress(true);


        recyclerView.setLayoutManager(new LinearLayoutManager(adapter.getContext()));
        recyclerView.setAdapter(dragMgr.createWrappedAdapter(adapter));

        dragMgr.attachRecyclerView(recyclerView);

        //noinspection ConstantConditions
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(adapter.getContext(), column,
                                                                          GridLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLayoutManager);

        return (GridLayoutManager) mLayoutManager;
    }

    public static class WrapContentLinearLayoutManager extends GridLayoutManager {
        public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        public WrapContentLinearLayoutManager(Context context, int spanCount) {
            super(context, spanCount);
        }

        public WrapContentLinearLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
            super(context, spanCount, orientation, reverseLayout);
        }

        //... constructor
        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("probe", "meet a IOOBE in RecyclerView");
            }
        }
    }

    public static class ItemOffsetDecoration extends RecyclerView.ItemDecoration {
        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }

    public static void colorWidget(View view, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(view.getBackground());
        DrawableCompat.setTint(wrappedDrawable.mutate(), view.getContext().getResources().getColor(color));
        view.setBackgroundDrawable(wrappedDrawable);
    }

    public static void colorWidget(View view, String color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(view.getBackground());
        DrawableCompat.setTint(wrappedDrawable.mutate(), Color.parseColor(color));
        view.setBackgroundDrawable(wrappedDrawable);
    }

    // ================================================================================
    // ALERT
    // ================================================================================
    public static AlertDialog showAlert(
            Context context, String title, String message,
            OnClickListener listener) {
        if (listener == null) {
            listener = new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            };
        }

        AlertDialog d = new Builder(context).setMessage(message)
                                            .setTitle(title)
                                            .setCancelable(true)
                                            .setNeutralButton(android.R.string.ok, listener)
                                            .show();
        return d;
    }

    public static AlertDialog showAlert(Context context, String title, String message) {
        return showAlert(context, title, message, null);
    }

    public static AlertDialog showAlert(Context context, String message) {
        return showAlert(context, null, message, null);
    }

    public static AlertDialog showAlert(Context context, int titleResId, int messageResId) {
        String title = null;
        try {
            title = context.getString(titleResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = null;
        try {
            message = context.getString(messageResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return showAlert(context, title, message, null);

    }

    public static AlertDialog showAlert(Context context, int messageResId) {

        String message = null;
        try {
            message = context.getString(messageResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return showAlert(context, null, message, null);

    }

    public static Builder buildAlert(Context context, String title, String message) {
        Builder dlg = new Builder(context).setMessage(message).setTitle(title)
                                          .setCancelable(true);
        return dlg;
    }

    public static void showInternetError(Context context) {
        showAlert(context, "Error", "No Internet connection", null);
    }

    public static void showUnknownResponseError(Context context) {
        showAlert(context, "Error", "Unknown response from server. Please try again later", null);
    }

    /**
     * Show confirm dialog popup
     *
     * @param context
     * @param title
     * @param message
     * @param l
     */
    public static Builder confirm(
            Context context, String title, String message, final ConfirmListener l,
            final CancelListener c) {
        Builder b = BaseHelper.buildAlert(context, title, message);
        if (c != null)
            b.setNegativeButton("No",
                                new OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        c.onNo();
                                    }
                                });
        else
            b.setNegativeButton("No", null);

        b.setPositiveButton("Yes",
                            new OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    l.onYes();
                                }
                            });

        b.show();

        return b;
    }

    public static Builder confirm(
            Context context, String title, String message, final ConfirmListener l) {
        return confirm(context, title, message, l, null);
    }

    /**
     * Show confirm dialog popup
     *
     * @param context
     * @param titleResId
     * @param messageResId
     * @param l
     */
    public static Builder confirm(
            Context context, int titleResId, int messageResId, final ConfirmListener l) {
        String title = null;
        try {
            title = context.getString(titleResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String message = null;
        try {
            message = context.getString(messageResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return confirm(context, title, message, l, null);
    }

    /**
     * Show confirm dialog
     *
     * @param context
     * @param messageResId
     * @param l
     */
    public static Builder confirm(
            Context context, int messageResId, final ConfirmListener l) {

        String message = null;
        try {
            message = context.getString(messageResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return confirm(context, null, message, l, null);
    }

    public interface ConfirmListener {
        void onYes();
    }

    public interface CancelListener {
        void onNo();
    }

    /**
     * Show a prompt dialog to the user
     *
     * @param context
     * @param titleResId
     * @param positiveButtonResId
     * @param negativeButtonResId
     * @param l
     */
    public static void prompt(
            Context context, int titleResId, int positiveButtonResId, int negativeButtonResId,
            final PromptListener l) {
        prompt(context, titleResId, positiveButtonResId, negativeButtonResId,
               InputType.TYPE_TEXT_FLAG_CAP_SENTENCES, l);
    }

    /**
     * Show a prompt dialog to the user with custom input type
     *
     * @param context
     * @param titleResId
     * @param positiveButtonResId
     * @param negativeButtonResId
     * @param inputType
     * @param l
     */
    public static void prompt(
            Context context, int titleResId, int positiveButtonResId, int negativeButtonResId,
            int inputType, final PromptListener l) {
        String title = null;

        try {
            title = context.getString(titleResId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String positiveButton = null;
        try {
            positiveButton = context.getString(positiveButtonResId);
        } catch (Exception e) {
            e.printStackTrace();
            positiveButton = "Done";
        }

        String negativeButton = null;
        try {
            negativeButton = context.getString(negativeButtonResId);
        } catch (Exception e) {
            e.printStackTrace();
            negativeButton = "Cancel";
        }

        prompt(context, title, positiveButton, negativeButton, inputType, l);
    }

    public static void prompt(
            Context context, String title, String positiveButton, String negativeButton,
            int inputType, final PromptListener l) {
        final EditText input = new EditText(context);
        input.setInputType(inputType);
        input.setSingleLine();

        new Builder(context).setTitle(title).setView(input)
                            .setPositiveButton(positiveButton, new OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String mInput = input.getText().toString().trim();
                                    if (l != null) {
                                        l.onYes(mInput);
                                    }
                                }
                            }).setNegativeButton(negativeButton, new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();
    }

    /**
     * Show required popup message
     *
     * @param context
     * @param field
     */
    public static void showRequired(Context context, String field) {
        showAlert(context, "Please provide:", field,
                  null);
    }

    public static void showRequired(Context context, int resId) {
        try {
            String f = context.getString(resId);
            showRequired(context, f);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // ================================================================================
    // Image Picker
    // ================================================================================
    public static void pickImage(GenericFragment fragment, ImageViewLoader img) {
        img.hideProgress();
        pickImage(fragment, img.getImage(), null);
    }

    public static void pickImage(GenericFragment fragment, ImageViewLoader img, ImagePickDone listenerDone) {
        img.hideProgress();
        pickImage(fragment, img.getImage(), listenerDone);
    }

    public static void pickImage(final GenericFragment fragment, final ImageView img,
                                 final ImagePickDone listenerDone) {
        Builder bld = BaseHelper.buildAlert(fragment.getActivity(),
                                            "Select image source", null);
        CharSequence cs[];
        cs = new String[2];
        cs[0] = "Gallery";
        cs[1] = "Camera";
        bld.setItems(cs, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        picGallery(fragment, img, listenerDone);
                        break;
                    case 1:
                        // camera
                        PackageManager pm = fragment.getActivity().getApplicationContext()
                                                    .getPackageManager();

                        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                            picCamera(fragment, img, listenerDone);
                        } else {
                            BaseHelper.showAlert(fragment.getActivity(), null,
                                                 "You dont have a camera", null);
                        }

                        break;

                    default:
                        // nothing
                }
            }
        });
        bld.setNeutralButton(android.R.string.cancel, null);
        bld.setCancelable(true);
        bld.show();
    }

    public static void pickImageSquare(final GenericFragment fragment, final ImageView img,
                                       final ImagePickDone listenerDone) {
        Builder bld = BaseHelper.buildAlert(fragment.getActivity(),
                                            "Select image source", null);
        CharSequence cs[];
        cs = new String[2];
        cs[0] = "Gallery";
        cs[1] = "Camera";
        bld.setItems(cs, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        picGallery(fragment, img, listenerDone, 1, 1);
                        break;
                    case 1:
                        // camera
                        PackageManager pm = fragment.getActivity().getApplicationContext()
                                                    .getPackageManager();

                        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                            picCamera(fragment, img, listenerDone, 1, 1);
                        } else {
                            BaseHelper.showAlert(fragment.getActivity(), null,
                                                 "You dont have a camera", null);
                        }

                        break;

                    default:
                        // nothing
                }
            }
        });
        bld.setNeutralButton(android.R.string.cancel, null);
        bld.setCancelable(true);
        bld.show();
    }

    public static void pickImageCustom(final GenericFragment fragment, final ImageView img, final int width,
                                       final int height,
                                       final ImagePickDone listenerDone) {
        Builder bld = BaseHelper.buildAlert(fragment.getActivity(),
                                            "Select image source", null);
        CharSequence cs[];
        cs = new String[2];
        cs[0] = "Gallery";
        cs[1] = "Camera";
        bld.setItems(cs, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        picGallery(fragment, img, listenerDone, width, height);
                        break;
                    case 1:
                        // camera
                        PackageManager pm = fragment.getActivity().getApplicationContext()
                                                    .getPackageManager();

                        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                            picCamera(fragment, img, listenerDone, width, height);
                        } else {
                            BaseHelper.showAlert(fragment.getActivity(), null,
                                                 "You dont have a camera", null);
                        }

                        break;

                    default:
                        // nothing
                }
            }
        });
        bld.setNeutralButton(android.R.string.cancel, null);
        bld.setCancelable(true);
        bld.show();
    }

    public static void picCamera(final GenericFragment fragment, final ImageView imageView,
                                 final ImagePickDone listenerDone) {
        picCamera(fragment, imageView, listenerDone, 0, 0);
    }

    public static void picCamera(final GenericFragment fragment, final ImageView imageView,
                                 final ImagePickDone listenerDone, int x, int y) {
        Options options = new Options();
        options.setMaxResultSize(720, 720);

        if (x > 0 && y > 0)
            options.setAspectRatio(x, y);

        RxPaparazzo.takeImage(fragment)
                   .size(new CustomMaxSize(500))
                   .crop(options)
                   .usingCamera()
                   .subscribe(
                           new Action1<Response<GenericFragment, String>>() {
                               @Override
                               public void call(final Response<GenericFragment, String> response) {
                                   if (response.resultCode() != Activity.RESULT_OK) {
                                       response.targetUI().imagePickFail();
                                       return;
                                   }

                                   final ProgressDialog load = ProgressDialog.show(fragment.getActivity(), "",
                                                                                   "Processing..");

                                   File imageFile = new File(response.data());

                                   Picasso.with(fragment.getActivity()).setLoggingEnabled(true);
                                   Picasso.with(fragment.getActivity()).invalidate(new File(response.data()));
                                   Picasso.with(fragment.getActivity()).load(imageFile).into(imageView, new Callback() {
                                       @Override
                                       public void onSuccess() {
                                           load.dismiss();
                                           if (listenerDone != null)
                                               listenerDone.onDonePick(imageView);
                                       }

                                       @Override
                                       public void onError() {
                                           load.dismiss();
                                           response.targetUI().imagePickFail();
                                       }
                                   });
                               }
                           }
                   );
    }

    public static void picGallery(final GenericFragment fragment, final ImageView imageView,
                                  final ImagePickDone listenerDone) {
        picGallery(fragment, imageView, listenerDone, 0, 0);
    }

    public static void picGallery(final GenericFragment fragment, final ImageView imageView,
                                  final ImagePickDone listenerDone, int x, int y) {
        Options options = new Options();
        options.setMaxResultSize(720, 720);

        if (x > 0 && y > 0)
            options.setAspectRatio(x, y);

        RxPaparazzo.takeImage(fragment)
                   .crop(options)
                   .size(new CustomMaxSize(500))
                   .usingGallery()
                   .subscribe(
                           new Action1<Response<GenericFragment, String>>() {
                               @Override
                               public void call(final Response<GenericFragment, String> response) {
                                   if (response.resultCode() != Activity.RESULT_OK) {
                                       response.targetUI().imagePickFail();
                                       return;
                                   }

                                   final ProgressDialog load = ProgressDialog.show(fragment.getActivity(), "",
                                                                                   "Processing..");

                                   File imageFile = new File(response.data());

                                   Picasso.with(fragment.getActivity()).setLoggingEnabled(true);
                                   Picasso.with(fragment.getActivity())
                                          .invalidate(new File(response.data()));
                                   Picasso.with(fragment.getActivity()).load(imageFile).into(imageView, new Callback() {
                                       @Override
                                       public void onSuccess() {
                                           load.dismiss();
                                           if (listenerDone != null)
                                               listenerDone.onDonePick(imageView);
                                       }

                                       @Override
                                       public void onError() {
                                           load.dismiss();
                                           response.targetUI().imagePickFail();
                                       }
                                   });
                               }
                           }
                   );
    }

    /**
     * Take a picture and resize it to specific size
     *
     * @param fragment
     * @param imageView
     * @param listenerDone
     * @param x
     * @param y
     */
    public static void picGalleryCustomSize(final GenericFragment fragment, final ImageView imageView,
                                            final BaseHelper.ImagePickDone listenerDone, final int x, final int y) {
        Options options = new Options();
        options.setMaxResultSize(x, y);

        if (x > 0 && y > 0)
            options.setAspectRatio(x, y);

        RxPaparazzo.takeImage(fragment)
                   .crop(options)
                   .size(new CustomMaxSize(500))
                   .usingGallery()
                   .subscribe(
                           new Action1<Response<GenericFragment, String>>() {
                               @Override
                               public void call(final Response<GenericFragment, String> response) {
                                   if (response.resultCode() != Activity.RESULT_OK) {
                                       response.targetUI().imagePickFail();
                                       return;
                                   }
                                   File imageFile = new File(response.data());

                                   Picasso.with(fragment.getActivity()).setLoggingEnabled(true);
                                   Picasso.with(fragment.getActivity())
                                          .invalidate(new File(response.data()));
                                   Picasso.with(fragment.getActivity()).load(imageFile).into(imageView, new Callback() {
                                       @Override
                                       public void onSuccess() {
                                           Bitmap bm = Bitmap.createScaledBitmap(
                                                   ((BitmapDrawable) imageView.getDrawable()).getBitmap(), x, y, true);
                                           Log.d("Bitmap height", (Integer.toString(bm.getHeight())));
                                           Log.d("Bitmap width", (Integer.toString(bm.getWidth())));
                                           imageView.setImageBitmap(bm);

                                           if (listenerDone != null)
                                               listenerDone.onDonePick(imageView);
                                       }

                                       @Override
                                       public void onError() {

                                       }
                                   });
                               }
                           }
                   );
    }

    public abstract static class ImagePickListener {
        public abstract void onPickSuccess();

        public void onPickFail() {
            // Do nothing
        }
    }

    public static abstract class ImagePickDone {
        public abstract void onDonePick(ImageView img);

    }

    // ================================================================================
    // Intent
    // ================================================================================
    public static void intentCall(Context context, String number) {
        if (BaseHelper.isEmpty(number)) {
            BaseHelper.showAlert(context, "No phone number detected");
            return;
        }

        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + number.replace(" ", "")));
        context.startActivity(intent);
    }

    public static void intentEmail(Context context, String email, String subject) {
        BaseHelper.intentEmail(context, email, subject, "");
    }

    public static void intentEmail(Context context, String email, String subject, String text) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{
                email

        });
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);

        context.startActivity(Intent.createChooser(emailIntent, "Send mail.."));
    }

    public static void intentWeb(Context context, String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }

    public static void intentPlaystore(Context context, String packageName) {
        //        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(
                    "https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public static void intentDirection(Context context, double latitude, double longitude) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                                   Uri.parse("http://maps.google.com/maps?daddr=" + latitude + "," + longitude));
        context.startActivity(intent);
    }

    public static void intentFacebook(Context ctx, String url) {
        if (BaseHelper.isEmpty(url))
            return;

        Uri uri;
        try {
            ctx.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            // http://stackoverflow.com/a/24547437/1048340
            uri = Uri.parse("fb://facewebmodal/f?href=" + url);
        } catch (NameNotFoundException e) {
            uri = Uri.parse(url);
        }
        ctx.startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    public static void intentTwitter(Context ctx, String twitterUsername) {
        Intent intent = null;
        try {
            // get the Twitter app if possible
            ctx.getPackageManager().getPackageInfo("com.twitter.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + twitterUsername));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            // no Twitter app, revert to browser
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + twitterUsername));
        }
        ctx.startActivity(intent);


        //        if (BaseHelper.isEmpty(url))
        //            return;
        //
        //        PackageInfo info;
        //        Intent      intent;
        //        try {
        //            info = ctx.getPackageManager().getPackageInfo("com.twitter.android", 0);
        //            if (info.applicationInfo.enabled)
        //                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=" + twitterId));
        //            else
        //                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //
        //            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        //            ctx.startActivity(intent);
        //        } catch (Exception e) {
        //            e.printStackTrace();
        //            Helper.showAlert(ctx, "You don't have this apps");
        //        }
    }

    public static void intentInstagram(Context ctx, String url) {
        if (BaseHelper.isEmpty(url))
            return;

        final Intent intent = new Intent(Intent.ACTION_VIEW);
        try {
            if (ctx.getPackageManager().getPackageInfo("com.instagram.android", 0) != null) {
                if (url.endsWith("/")) {
                    url = url.substring(0, url.length() - 1);
                }
                final String username = url.substring(url.lastIndexOf("/") + 1);
                // http://stackoverflow.com/questions/21505941/intent-to-open-instagram-user-profile-on-android
                intent.setData(Uri.parse("http://instagram.com/_u/" + username));
                intent.setPackage("com.instagram.android");
            } else {
                intent.setData(Uri.parse(url));
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            intent.setData(Uri.parse(url));
        }
        ctx.startActivity(intent);
    }

    public static void intentYoutube(Context ctx, String youtubeChannel) {
        String url    = "http://www.youtube.com/user/" + youtubeChannel;
        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            if (ctx.getPackageManager().getPackageInfo("com.google.android.youtube", 0) != null) {
                intent.setPackage("com.google.android.youtube");
                intent.setData(Uri.parse(url));
            } else {
                intent.setData(Uri.parse(url));
            }
        } catch (NameNotFoundException e) {
            intent.setData(Uri.parse(url));
            e.printStackTrace();
        }
        ctx.startActivity(intent);
    }

    public static void intentYoutubeVideo(Context ctx, String id) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            ctx.startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                                       Uri.parse("http://www.youtube.com/watch?v=" + id));
            ctx.startActivity(intent);
        }
    }

    public static void intentPinterest(Context ctx, String url) {
        if (BaseHelper.isEmpty(url))
            return;

        // Intent intent = new Intent(Intent.ACTION_VIEW);
        // com.pinterest
        Intent intent = new Intent(Intent.ACTION_VIEW);

        try {
            if (ctx.getPackageManager().getPackageInfo("com.pinterest", 0) != null) {
                intent.setPackage("com.pinterest");
                intent.setData(Uri.parse(url));
                ctx.startActivity(intent);
            } else {
                intent.setData(Uri.parse(url));
            }
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            intent.setData(Uri.parse(url));
        }
        ctx.startActivity(intent);
    }

    public static void intentWhatsapp(Context ctx) {
        openApps(ctx, "com.whatsapp");
    }

    public static void intentLine(Context ctx) {
        openApps(ctx, "jp.naver.line.android");
    }

    public static void intentBbm(Context ctx) {
        openApps(ctx, "com.bbm");
    }

    public static void intentTelegram(Context ctx) {
        openApps(ctx, "org.telegram.messenger");
    }

    public static void openApps(Context ctx, String packageName) {
        PackageManager manager = ctx.getPackageManager();
        Intent         i       = manager.getLaunchIntentForPackage(packageName);
        if (i != null) {
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            ctx.startActivity(i);
        }
    }

    public static void copyToClipboardCo(Context ctx, String message, String toastMessage) {
        ClipboardManager clipboard = (ClipboardManager) ctx.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData         clip      = ClipData.newPlainText("label", message);
        clipboard.setPrimaryClip(clip);

        Toast.makeText(ctx, toastMessage, Toast.LENGTH_SHORT).show();
    }

    public static void copyToClipboardCo(Context ctx, String message) {
        copyToClipboardCo(ctx, message, "Copied to clipboard");
    }

    // ================================================================================
    // Spannable Text
    // ================================================================================
    public static Spannable bold(String text, int from, int to) {
        Spannable span = new SpannableString(text);

        return BaseHelper.bold(span, from, to);
    }

    public static Spannable bold(Spannable text, int from, int to) {
        text.setSpan(new StyleSpan(Typeface.BOLD),
                     from, to,
                     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return text;
    }

    public static Spannable fontSize(String text, int from, int to, int size) {
        Spannable span = new SpannableString(text);
        return BaseHelper.fontSize(span, from, to, size);
    }

    public static Spannable fontSize(Spannable text, int from, int to, int size) {
        text.setSpan(new AbsoluteSizeSpan(size),
                     from, to, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return text;
    }

    public static Spannable fontColor(String text, int from, int to, String color) {
        return BaseHelper.fontColor(new SpannableString(text), from, to, color);
    }

    public static Spannable fontColor(Spannable span, int from, int to, String color) {
        if (!color.startsWith("#"))
            color = "#" + color;

        span.setSpan(new ForegroundColorSpan(Color.parseColor(color)), from, to,
                     Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

    // ================================================================================
    // Encrypt
    // ================================================================================
    public static String encryptMd5(String text) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(text.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    // ================================================================================
    // Facebook Parser
    // ================================================================================
    /*public static Object getObjectFromFacebook(com.facebook.Response m, String key) {
        if(m == null || m.getGraphObject() == null)
            return null;

        Map<String, Object> map = m.getGraphObject().asMap();
        return map.get(key);
    }*/

    // ================================================================================
    // Picker
    // ================================================================================
    public static void datePicker(final EditText edt) {
        datePicker(edt, DATE_MDY);
    }

    public static void datePicker(final EditText edt, final String format) {
        edt.setFocusable(false);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar newCalendar = Calendar.getInstance();
                DatePickerDialog datePicker = new DatePickerDialog(edt.getContext(),
                                                                   new DatePickerDialog.OnDateSetListener() {
                                                                       public void onDateSet(DatePicker view, int year,
                                                                                             int monthOfYear,
                                                                                             int dayOfMonth) {
                                                                           Calendar newDate = Calendar.getInstance();
                                                                           newDate.set(year, monthOfYear, dayOfMonth);
                                                                           SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                                                                   format, Locale.US);
                                                                           edt.setText(dateFormatter.format(
                                                                                   newDate.getTime()));
                                                                       }

                                                                   }, newCalendar.get(Calendar.YEAR),
                                                                   newCalendar.get(Calendar.MONTH),
                                                                   newCalendar.get(Calendar.DAY_OF_MONTH));

                datePicker.getDatePicker().setSpinnersShown(true);
                datePicker.getDatePicker().setCalendarViewShown(false);
                datePicker.show();
            }
        });
    }

    public static void timePicker(final EditText edt) {
        edt.setFocusable(false);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePicker = new TimePickerDialog(edt.getContext(),
                                                                   new TimePickerDialog.OnTimeSetListener() {
                                                                       @Override
                                                                       public void onTimeSet(TimePicker view,
                                                                                             int hourOfDay,
                                                                                             int minute) {
                                                                           String time = Integer.toString(hourOfDay) +
                                                                                   ":" + Integer.toString(minute);
                                                                           edt.setText(parseDateTime(time,
                                                                                                     DATE_HM)
                                                                                               .toString(
                                                                                                       DATE_HMA));
                                                                       }
                                                                   }, 12, 0, true);
                timePicker.show();
            }
        });
    }

    public static void timePickerNoMinute(final EditText edt) {
        edt.setFocusable(false);
        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout     linearLayout  = new RelativeLayout(edt.getContext());
                final NumberPicker aNumberPicker = new NumberPicker(edt.getContext());
                aNumberPicker.setMaxValue(23);
                aNumberPicker.setMinValue(0);

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(50, 50);
                RelativeLayout.LayoutParams numPicerParams = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                numPicerParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

                linearLayout.setLayoutParams(params);
                linearLayout.addView(aNumberPicker, numPicerParams);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(edt.getContext());
                alertDialogBuilder.setTitle("Select time");
                alertDialogBuilder.setView(linearLayout);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Ok",
                                           new DialogInterface.OnClickListener() {
                                               public void onClick(DialogInterface dialog, int id) {
                                                   Calendar newDate = Calendar.getInstance();
                                                   newDate.set(1970, 1, 1, aNumberPicker.getValue(), 0);
                                                   SimpleDateFormat dateFormatter = new SimpleDateFormat(
                                                           DATE_HA, Locale.US);
                                                   edt.setText(dateFormatter.format(newDate.getTime()));
                                               }
                                           })
                        .setNegativeButton("Cancel",
                                           new DialogInterface.OnClickListener() {
                                               public void onClick(DialogInterface dialog, int id) {
                                                   dialog.cancel();
                                               }
                                           });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public static EditText promptPin(
            Context context, String title, String positiveButton, String negativeButton, final PromptListener l) {
        final EditText input = new EditText(context);
        input.setSingleLine();
        input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        input.setTransformationMethod(PasswordTransformationMethod.getInstance());
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});

        new Builder(context).setTitle(title).setView(input)
                            .setPositiveButton(positiveButton, new OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    String mInput = input.getText().toString().trim();
                                    if (l != null) {
                                        l.onYes(mInput);
                                    }
                                }
                            }).setNegativeButton(negativeButton, new OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Do nothing.
            }
        }).show();

        return input;
    }

    public interface PromptListener {
        void onYes(String input);
    }

    // ================================================================================
    // Validate
    // ================================================================================
    public static boolean validateEditTexts(EditText[] alEdt) {
        for (EditText editText : alEdt) {
            if (BaseHelper.isEmpty(editText)) {
                BaseHelper.showRequired(editText.getContext(), editText.getHint().toString());

                return false;
            }

            if (editText.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)) {
                if (!validateEmail(editText))
                    return false;
            } else if (editText.getInputType() == InputType.TYPE_CLASS_NUMBER) {
                try {
                    Long.parseLong(editText.getText().toString());
                } catch (Exception e) {
                    showAlert(editText.getContext(), "Invalid " + editText.getHint().toString());
                    return false;
                }
            } else if (editText.getInputType() == InputType.TYPE_CLASS_PHONE) {
                if (!PhoneNumberUtils.isGlobalPhoneNumber(editText.getText().toString())) {
                    showAlert(editText.getContext(), "Invalid " + editText.getHint().toString());
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validateEmail(EditText edt) {
        if (BaseHelper.isValidEmail(edt.getText().toString()))
            return true;

        BaseHelper.showRequired(edt.getContext(), edt.getHint().toString());
        return false;
    }

    // ================================================================================
    // Server
    // ================================================================================
    public static String toSearchQuery(String key, EditText edt) {
        return BaseHelper.toSearchQuery(key, edt.getText().toString(), EXACT);
    }

    public static String toSearchQuery(String key, EditText edt, String type) {
        return BaseHelper.toSearchQuery(key, edt.getText().toString(), type);
    }

    public static String toSearchQuery(String key, String value) {
        return BaseHelper.toSearchQuery(key, value, EXACT);
    }

    public static String toSearchQuery(String key, int value) {
        return BaseHelper.toSearchQuery(key, Integer.toString(value), EXACT);
    }

    public static String toSearchQuery(String key, String value, String type) {
        if (BaseHelper.isEmpty(key) || BaseHelper.isEmpty(value) || BaseHelper
                .isEmpty(type)) {
            return "";
        }

        return key + "," + value + "," + type + ";";
    }

    // ================================================================================
    // Credit Card
    // ================================================================================
    private static final int  CARD_NUMBER_TOTAL_SYMBOLS    = 19; // size of pattern 0000-0000-0000-0000
    private static final int  CARD_NUMBER_TOTAL_DIGITS     = 16; // max numbers of digits in pattern: 0000 x 4
    private static final int  CARD_NUMBER_DIVIDER_MODULO   = 5; // means divider position is every 5th symbol beginning with 1
    private static final int  CARD_NUMBER_DIVIDER_POSITION = CARD_NUMBER_DIVIDER_MODULO -
            1; // means divider position is every 4th symbol beginning with 0
    private static final char CARD_NUMBER_DIVIDER          = '-';
    private static final int  CARD_DATE_TOTAL_SYMBOLS      = 5; // size of pattern MM/YY
    private static final int  CARD_DATE_TOTAL_DIGITS       = 4; // max numbers of digits in pattern: MM + YY
    private static final int  CARD_DATE_DIVIDER_MODULO     = 3; // means divider position is every 3rd symbol beginning with 1
    private static final int  CARD_DATE_DIVIDER_POSITION   = CARD_DATE_DIVIDER_MODULO -
            1; // means divider position is every 2nd symbol beginning with 0
    private static final char CARD_DATE_DIVIDER            = '/';
    private static final int  CARD_CVC_TOTAL_SYMBOLS       = 3;

    private static boolean isInputCorrect(Editable s, int size, int dividerPosition, char divider) {
        boolean isCorrect = s.length() <= size;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && (i + 1) % dividerPosition == 0) {
                isCorrect &= divider == s.charAt(i);
            } else {
                isCorrect &= Character.isDigit(s.charAt(i));
            }
        }
        return isCorrect;
    }

    private static String concatString(char[] digits, int dividerPosition, char divider) {
        final StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 0) {
                formatted.append(digits[i]);
                if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                    formatted.append(divider);
                }
            }
        }

        return formatted.toString();
    }

    private static char[] getDigitArray(final Editable s, final int size) {
        char[] digits = new char[size];
        int    index  = 0;
        for (int i = 0; i < s.length() && index < size; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                digits[index] = current;
                index++;
            }
        }
        return digits;
    }

    public static class CCCardNumberValidator implements TextWatcher {
        // Change this to what you want... ' ', '-' etc..
        private static final char space = ' ';

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isInputCorrect(s, CARD_NUMBER_TOTAL_SYMBOLS, CARD_NUMBER_DIVIDER_MODULO, CARD_NUMBER_DIVIDER)) {
                s.replace(0, s.length(),
                          concatString(getDigitArray(s, CARD_NUMBER_TOTAL_DIGITS), CARD_NUMBER_DIVIDER_POSITION,
                                       CARD_NUMBER_DIVIDER));

            }
        }
    }

    public static class CCCvvValidator implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > CARD_CVC_TOTAL_SYMBOLS) {
                s.delete(CARD_CVC_TOTAL_SYMBOLS, s.length());
            }
        }
    }

    public static class CCExpiryValidator implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!isInputCorrect(s, CARD_DATE_TOTAL_SYMBOLS, CARD_DATE_DIVIDER_MODULO, CARD_DATE_DIVIDER)) {
                s.replace(0, s.length(),
                          concatString(getDigitArray(s, CARD_DATE_TOTAL_DIGITS), CARD_DATE_DIVIDER_POSITION,
                                       CARD_DATE_DIVIDER));
            }
        }
    }

    // ================================================================================
    // External links
    // ================================================================================
    public static String getMapUrl(Context context, String googleKey, int zoom, String latitude, String longitude) {
        String url;
        if ((latitude == "0.0" && longitude == "0.0"))
            return "";

        url = "https://maps.googleapis.com/maps/api/staticmap?";
        url += "key=" + googleKey;
        url += "&maptype=hybrid&center=" + latitude + "," + longitude;
        // Sensor sensitive places
        url += "&sensor=" + "true";
        // Zoom in
        url += "&zoom=" + Integer.toString(zoom);
        // Marker color
        url += "&markers=" + "green";
        // Marker location {latitude,longitude}
        url += "|" + latitude + "," + longitude;
        // Get exact size
        url += "&size="
                // Calculate screen width - white space left and right
                + Math.round(BaseUIHelper.convertPixelsToDp(
                BaseUIHelper.getScreenWidth(context), context))
                + "x"
                // Height of the image
                + Math.round(BaseUIHelper.convertDpToPixel(150, context));

        return url;
    }

    public static String getYoutubePreview(String url) {
        String id = getYoutubeId(url);
        if (BaseHelper.isEmpty(id))
            return "";

        return "http://img.youtube.com/vi/" + id + "/0.jpg";
    }

    public static String getYoutubePreviewById(String id) {
        if (BaseHelper.isEmpty(id))
            return "";

        return "http://img.youtube.com/vi/" + id + "/0.jpg";
    }

    public static String getYoutubeId(String ytUrl) {
        final String reg = "(?:youtube(?:-nocookie)?\\.com\\/(?:[^\\/\\n\\s]+\\/\\S+\\/|(?:v|e(?:mbed)?)\\/|\\S*?[?&]v=)|youtu\\.be\\/)([a-zA-Z0-9_-]{11})";

        if (ytUrl == null || ytUrl.trim().length() <= 0)
            return null;

        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ytUrl);

        if (matcher.find())
            return matcher.group(1);

        return "";
    }

    public static boolean isValidYoutubeUrl(String url) {
        return !isEmpty(getYoutubeId(url));
    }
}
