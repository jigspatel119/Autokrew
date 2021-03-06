package com.autokrew.customfonts;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomFont_GillSansBold extends TextView{

	public CustomFont_GillSansBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFont_GillSansBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFont_GillSansBold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"GillSans-Bold.ttf");
        setTypeface(tf);
    }
	

}
