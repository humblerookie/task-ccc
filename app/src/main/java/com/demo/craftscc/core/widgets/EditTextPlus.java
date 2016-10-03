package com.demo.craftscc.core.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

import com.demo.craftscc.R;
import com.demo.craftscc.core.utils.TypefaceUtil;


public class EditTextPlus extends EditText {

    private static final String TAG = "EditTextPlus";

    public EditTextPlus(Context context) {
        super(context);
    }

    public EditTextPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont(context, attrs);
    }

    public EditTextPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont(context, attrs);
    }

    private void setCustomFont(Context ctx, AttributeSet attrs) {
        TypedArray a = ctx.obtainStyledAttributes(attrs, R.styleable.TextViewPlus);
        String customFont = a.getString(R.styleable.EditTextPlus_editTextCustomFont);
        if (customFont != null) {
            setCustomFont(ctx, customFont);
        }
        a.recycle();
    }

    public boolean setCustomFont(Context ctx, String asset) {
        setTypeface(TypefaceUtil.getTypeface(asset));
        return true;
    }


}
