package com.demo.craftscc.core.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.demo.craftscc.R;

import icepick.Icepick;


public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    public boolean isAlive() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !isFinishing() && !isDestroyed();
        } else {
            return !isFinishing();
        }
    }

    protected void alertNetwork() {
        if (isAlive()) {
            Toast.makeText(this, R.string.error_common_no_internet, Toast.LENGTH_SHORT).show();
        }
    }

    protected void alertCustom(int resId) {
        if (isAlive()) {
            Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
        }
    }

}
