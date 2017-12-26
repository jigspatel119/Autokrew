package com.autokrew.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFont_GillSansSemiBold extends TextView{

	public CustomFont_GillSansSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFont_GillSansSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFont_GillSansSemiBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"GillSans-SemiBold.ttf");
        setTypeface(tf);
    }
	

}
