package com.demo.craftscc.core.activities;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.demo.craftscc.R;


public class BaseActivity extends AppCompatActivity {

    protected boolean isAlive() {
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

}
