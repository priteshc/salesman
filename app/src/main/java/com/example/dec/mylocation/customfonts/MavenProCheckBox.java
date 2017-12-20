package com.example.dec.mylocation.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by one on 3/12/15.
 */
public class MavenProCheckBox extends CheckBox {

    public MavenProCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MavenProCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MavenProCheckBox(Context context) {
        super(context);
        init();
    }

    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/MavenPro-Regular.ttf");
            setTypeface(tf);
        }
    }

}