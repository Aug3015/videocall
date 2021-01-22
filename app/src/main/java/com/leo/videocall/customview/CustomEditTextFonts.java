package com.leo.videocall.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.leo.videocall.R;
import com.leo.videocall.utils.Constants;
import com.leo.videocall.utils.FontCache;

public class CustomEditTextFonts extends AppCompatEditText {

    private String storeString;
    private String dotsString;
    private int currentColor = Color.BLACK;
    private String typeFont = Constants.DEFAULT_FONT;

    public CustomEditTextFonts(Context context) {
        super(context);
    }

    public CustomEditTextFonts(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentColor = getCurrentTextColor();
        try {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomTextViewFonts, 0, 0);

            typeFont = a.getString(R.styleable.CustomTextViewFonts_font_type);

            setTypeface(Typeface.createFromAsset(context.getAssets(), typeFont));
            a.recycle();
        } catch (Exception ex) {
            try {
                setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.DEFAULT_FONT));
            } catch (Exception ex1) {
                setTypeface(Typeface.DEFAULT);
            }
        }

    }

    public void setTypeFont(Context context, String typeFont) {
        this.typeFont = typeFont;
        setTypeface(FontCache.getTypeface(context, typeFont));
    }

    public String getTypeFont() {
        return typeFont;
    }

}

