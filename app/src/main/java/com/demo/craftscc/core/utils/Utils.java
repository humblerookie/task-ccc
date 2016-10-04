package com.demo.craftscc.core.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.demo.craftscc.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by anvith on 9/28/16.
 */

public class Utils {
    private static final String TAG = "Utils";

    public static String toCamelCase(String input) {
        String[] words = input.toLowerCase().split(" ");
        StringBuilder sb = new StringBuilder();
        if (words.length > 0 && words[0].length() > 0) {
            sb.append(Character.toUpperCase(words[0].charAt(0))
                    + words[0].subSequence(1, words[0].length()).toString().toLowerCase());
            for (int i = 1; i < words.length; i++) {
                if (words[i].length() > 0) {
                    sb.append(" ");
                    sb.append(Character.toUpperCase(words[i].charAt(0))
                            + words[i].subSequence(1, words[i].length()).toString().toLowerCase());
                }
            }
        }
        return sb.toString();
    }

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(TAG, ex.toString());
            return null;
        }
        return json;
    }

    public static ProgressDialog createProgressDialog(Context context) {
        ProgressDialog pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        return pd;
    }
}
