package com.example.dec.mylocation.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * Created by one on 3/12/15.
 */
public class MavenProRadioButton extends RadioButton {

    public MavenProRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MavenProRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MavenProRadioButton(Context context) {
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