package com.xtronlabs.koochooloo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xtronlabs.koochooloo.R;
import com.xtronlabs.koochooloo.util.TypefaceUtil;


public class KoochoolooLabel extends TextView {
    public KoochoolooLabel(Context context) {
        super(context);
        init(context);
    }

    public KoochoolooLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public KoochoolooLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    protected void init(Context c){
        TypedArray ta = c.obtainStyledAttributes(R.styleable.KoochoolooLabel);
        try {
            String font = ta.getString(R.styleable.KoochoolooLabel_customFont);
            if (font != null)
                setTypeface(TypefaceUtil.get(c, font));
        } finally {
            ta.recycle();
        }
    }

}
