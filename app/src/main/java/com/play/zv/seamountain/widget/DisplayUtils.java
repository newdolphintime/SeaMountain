package com.play.zv.seamountain.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.ViewConfiguration;

import com.play.zv.seamountain.R;

/**
 * Created by Zv on 2017/01/10.
 */
public class DisplayUtils {
    // data
    private int dpi = 0;

    /** <br> life cycle. */

    public DisplayUtils(Context context) {
        dpi = context.getResources().getDisplayMetrics().densityDpi;
    }

    public float dpToPx(int dp) {
        if (dpi == 0) {
            return 0;
        }
        return (float) (dp * (dpi / 160.0));
    }


}

