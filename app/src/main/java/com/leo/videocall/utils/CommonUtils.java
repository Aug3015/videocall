package com.leo.videocall.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leo.videocall.MyApplication;
import com.leo.videocall.R;
import com.leo.videocall.customview.CustomEditTextFonts;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class CommonUtils {
    public static ProgressDialog showLoadingDialog(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.show();
        if (progressDialog.getWindow() != null) {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        return progressDialog;
    }

    public static void collapseHorizontal(final View v, Animation.AnimationListener listener) {
        final int initialWidth = v.getMeasuredWidth();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().width = initialWidth - (int) (initialWidth * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialWidth / v.getContext().getResources().getDisplayMetrics().density));
        if (listener != null) {
            a.setAnimationListener(listener);
        }
        v.startAnimation(a);
    }

    public static void expandHorizontal(final View v, final int width, Animation.AnimationListener listener) {
        v.measure(width, ViewGroup.LayoutParams.MATCH_PARENT);
        final int targetWidth = v.getMeasuredWidth();
        v.setVisibility(View.VISIBLE);

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.getLayoutParams().width = width;
                } else {
                    v.getLayoutParams().width = (int) (targetWidth * interpolatedTime);
                }
                v.requestLayout();

            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetWidth / v.getContext().getResources().getDisplayMetrics().density));
        if (listener != null) {
            a.setAnimationListener(listener);
        }
        v.startAnimation(a);
    }

    public static String getDecimalFormat(String value) {
        StringTokenizer lst = new StringTokenizer(value, ".");
        String str1 = value;
        String str2 = "";
        if (lst.countTokens() > 1) {
            str1 = lst.nextToken();
            str2 = lst.nextToken();
        }
        String str3 = "";
        int i = 0;
        int j = -1 + str1.length();
        if (str1.charAt(-1 + str1.length()) == '.') {
            j--;
            str3 = ".";
        }
        for (int k = j; ; k--) {
            if (k < 0) {
                if (str2.length() > 0)
                    str3 = str3 + "," + str2;
                return str3;
            }
            if (i == 3) {
                str3 = "," + str3;
                i = 0;
            }
            str3 = str1.charAt(k) + str3;
            i++;
        }
    }

    public static String formatVNDNumber(double value) {
//        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//        formatter.applyPattern("# ### ### ###");
//        String number = formatter.format(value);
        DecimalFormat fmt = new DecimalFormat();
        DecimalFormatSymbols fmts = new DecimalFormatSymbols();

        fmts.setGroupingSeparator(' ');

        fmt.setGroupingSize(3);
        fmt.setGroupingUsed(true);
        fmt.setDecimalFormatSymbols(fmts);
        String number = fmt.format(value);
        return number + " Đ";
    }

    public static String formatVNDNumberWithoutCurrency(double value) {
//        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
//        formatter.applyPattern("# ### ### ###");
//        String number = formatter.format(value);
        DecimalFormat fmt = new DecimalFormat();
        DecimalFormatSymbols fmts = new DecimalFormatSymbols();
        fmts.setGroupingSeparator(' ');
        fmt.setGroupingSize(3);
        fmt.setGroupingUsed(true);
        fmt.setDecimalFormatSymbols(fmts);
        String number = fmt.format(value);
        return number;
    }

//    public static String getExactUrl(String firstUrl) {
//        if (firstUrl == null) {
//            return "";
//        }
//        if (firstUrl.contains("http://")) {
//            return firstUrl;
//        }
//        String result = MyApplication.getInstance().BASE_IMAGE_URL + firstUrl;
//        return result;
//    }

    public static float convertDpToPx(Context context, int dpValue) {
        return dpValue * context.getResources().getDisplayMetrics().density;
    }

    public static boolean isEditTextEmpty(EditText editText) {
        if ("".equals(editText.getText().toString().trim())) {
            editText.setError("Vui lòng nhập đầy đủ thông tin");
            return true;
        }
        return false;
    }

    public static boolean isCustomEditTextEmpty(CustomEditTextFonts editText) {
        if ("".equals(editText.getText().toString().trim())) {
            editText.setError("Vui lòng nhập đầy đủ thông tin");
            return true;
        }
        return false;
    }
    public static boolean checkPassport(EditText editText) {

        if (editText.getText() != null) {
            if (editText.getText().toString().trim().length() > 10 || editText.getText().toString().trim().length() < 5) {
                editText.setError("Passport phải từ 5-10 ký tự");
            }
            return true;
        }
        return false;
    }
    public static boolean checkNumberPhone(EditText editText) {

        if (editText.getText() != null) {
            if (editText.getText().toString().trim().length() > 15 || editText.getText().toString().trim().length() < 10) {
                editText.setError("Số điện thoại phải từ 10-15 ký tự");
            }
            return true;
        }
        return false;
    }

    public static boolean validateEmail(EditText edtEmail) {
        String email = edtEmail.getText().toString().trim();
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        if (Pattern.matches(regex, email)) {
            return true;
        }
        edtEmail.setError("Email không hợp lệ");
        return false;
    }

    public static String convertDateFromServer(String serverDate) {
        return convertDateFromServer(serverDate, "dd-MM-yyyy HH:mm");
    }

    public static long convertDateFromServerToMilliseconds(String serverDate) {
        long result = 0L;
        if (serverDate.indexOf("(") >= 0 && serverDate.indexOf(")") >= 0) {
            String timeStamp = serverDate.substring(serverDate.indexOf("(") + 1, serverDate.indexOf(")"));
            result = Long.parseLong(timeStamp);
        }
        return result;
    }

    public static String convertDateFromServer(String serverDate, String outputFormat) {
        String result = "";
        if (serverDate == null) {
            return result;
        }
        if (serverDate.indexOf("(") >= 0 && serverDate.indexOf(")") >= 0) {
            String timeStamp = serverDate.substring(serverDate.indexOf("(") + 1, serverDate.indexOf(")"));
            Date date = new Date(Long.parseLong(timeStamp));
            result = convertDateToString(date, outputFormat);
        }
        return result;
    }

    public static String convertDateToString(Date date, String patternOutput) {
        SimpleDateFormat formatOut = new SimpleDateFormat(patternOutput);
        return formatOut.format(date);
    }

    public static void hideKeyboardFrom(Context context, View view) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }

    public static String convertDateFormat(String inputDateString, String outputFormat) {
        String result = "";
        if (inputDateString == null) {
            return result;
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy HH:mm").parse(inputDateString);
            SimpleDateFormat formatOut = new SimpleDateFormat(outputFormat);
            result = formatOut.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }

//    public static void showCustomToast(AppCompatActivity activity, String message, int duration) {
//        LayoutInflater inflater = activity.getLayoutInflater();
//        View layout = inflater.inflate(R.layout.toast_layout,
//                (ViewGroup) activity.findViewById(R.id.toast_layout_root));
//
//
//        TextView text = layout.findViewById(R.id.text);
//        if (message != null) {
//            text.setText(message);
//            Toast toast = new Toast(activity.getApplicationContext());
//            toast.setGravity(Gravity.CENTER_VERTICAL, 0, -270);
//            toast.setDuration(duration);
//            toast.setView(layout);
//            toast.show();
//        }
//    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        } else {
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

}
