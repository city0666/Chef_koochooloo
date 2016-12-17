package com.xtronlabs.koochooloo.view;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;

import com.xtronlabs.koochooloo.util.TypefaceUtil;


public class KoochoolooCheckBox extends AppCompatCheckBox {
    public KoochoolooCheckBox(Context context) {
        super(context);
        init(context);
    }

    public KoochoolooCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KoochoolooCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context c) {
        /*TypedArray ta = c.obtainStyledAttributes(R.styleable.KoochoolooLabel);
        try {
            String font = ta.getString(R.styleable.KoochoolooLabel_customFont);
            if (font != null)
                setTypeface(TypefaceUtil.get(c, font));
        } finally {
            ta.recycle();
        }*/

        setTypeface(TypefaceUtil.get(c, "font.ttf"));

    }

}
