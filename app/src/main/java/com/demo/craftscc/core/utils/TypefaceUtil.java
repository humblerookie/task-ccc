package com.demo.craftscc.core.utils;

import android.graphics.Typeface;

import com.demo.craftscc.R;
import com.demo.craftscc.core.CraftsCCApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anvith on 9/27/16.
 */

public class TypefaceUtil {

    private static Map<String, Typeface> typefaceMap = new HashMap<>();

    public static enum Type {

        NORMAL(CraftsCCApplication.getInstance().getString(R.string.font_regular)),
        BOLD(CraftsCCApplication.getInstance().getString(R.string.font_bold)),
        THIN(CraftsCCApplication.getInstance().getString(R.string.font_thin)),
        BOLD_ITALIC(CraftsCCApplication.getInstance().getString(R.string.font_bold_italic));

        final String val;

        Type(String val) {
            this.val = val;
        }

        public Type fromString(String s) {
            for (Type t : Type.values()) {
                if (t.toString().equals(s)) {
                    return t;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return val;
        }
    }

    public static Typeface getTypeface(Type font) {

        if (typefaceMap.containsKey(font.toString())) {
            return typefaceMap.get(font.toString());
        }

        Typeface typeface = Typeface.createFromAsset(CraftsCCApplication.getInstance().getAssets(), font.toString());
        typefaceMap.put(font.toString(), typeface);
        return typeface;
    }

    public static Typeface getTypeface(String font) {

        if (typefaceMap.containsKey(font)) {
            return typefaceMap.get(font);
        }

        Typeface typeface = Typeface.createFromAsset(CraftsCCApplication.getInstance().getAssets(), font.toString());
        typefaceMap.put(font, typeface);
        return typeface;
    }


}
