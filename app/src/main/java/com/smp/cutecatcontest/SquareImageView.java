package com.smp.cutecatcontest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Steve on 11/9/14.
 */
public class SquareImageView extends ImageView
{
    public SquareImageView(Context context)
    {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        // Set a square layout.
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }


}
