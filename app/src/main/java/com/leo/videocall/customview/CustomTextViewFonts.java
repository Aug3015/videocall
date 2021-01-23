package com.leo.videocall.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import com.leo.videocall.R;
import com.leo.videocall.utils.Constants;
import com.leo.videocall.utils.FontCache;


public class CustomTextViewFonts extends AppCompatTextView {

    private int currentColor = Color.BLACK;
    private String typeFont = Constants.DEFAULT_FONT;

    public CustomTextViewFonts(Context context) {
        super(context);
    }

    public CustomTextViewFonts(Context context, AttributeSet attrs) {
        super(context, attrs);
        currentColor = getCurrentTextColor();
        try {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomTextViewFonts, 0, 0);

            typeFont = a.getString(R.styleable.CustomTextViewFonts_font_type);
            setTypeface(FontCache.getTypeface(context,typeFont));
            a.recycle();
        } catch (Exception ex) {
            try {
                setTypeface(FontCache.getTypeface(context,typeFont));
            }catch (Exception ex1) {
                setTypeface(Typeface.DEFAULT);
            }
        }
    }

    public void setTypeFontByName(Context context, String fontName) {
        String fontFile= String.format("fonts/%s",fontName);
        setTypeFont(context,fontFile);
    }

    public void setTypeFont(Context context, String typeFont){
        this.typeFont = typeFont;
        setTypeface(FontCache.getTypeface(context,typeFont));
    }

    public String getTypeFont() {
        return typeFont;
    }
}
