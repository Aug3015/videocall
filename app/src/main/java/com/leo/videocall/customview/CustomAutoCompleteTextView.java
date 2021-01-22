package com.leo.videocall.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.leo.videocall.R;
import com.leo.videocall.utils.Constants;
import com.leo.videocall.utils.FontCache;


public class CustomAutoCompleteTextView extends AppCompatAutoCompleteTextView {
    private String typeFont = Constants.DEFAULT_FONT;


    public CustomAutoCompleteTextView(Context context) {
        super(context);
    }

    public CustomAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        try {
            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomTextViewFonts, 0, 0);

            typeFont = a.getString(R.styleable.CustomTextViewFonts_font_type);

            setTypeface(Typeface.createFromAsset(context.getAssets(), typeFont));
            a.recycle();
        } catch (Exception ex) {
            try {
                setTypeface(Typeface.createFromAsset(context.getAssets(), Constants.DEFAULT_FONT));
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
}
