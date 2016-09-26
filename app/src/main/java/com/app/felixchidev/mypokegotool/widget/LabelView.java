package com.app.felixchidev.mypokegotool.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.felixchidev.mypokegotool.R;

/**
 * Created by FelixChi on 24/9/2016.
 */
public class LabelView extends TextView
{
    public static LabelView create(ViewGroup parent)
    {
        return (LabelView) LayoutInflater.from(parent.getContext()).inflate(R.layout.label_view_layout, parent, false);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LabelView(Context context) {
        super(context);
    }

    public LabelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public void setBGColor(@ColorInt int color)
    {
        ((GradientDrawable)getBackground()).setColor(color);
    }
}
