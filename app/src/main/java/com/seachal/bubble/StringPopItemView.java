package com.seachal.bubble;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.seachal.library.BasePopupItemView;


public class StringPopItemView extends BasePopupItemView<String> {

    public StringPopItemView(Context context) {
        this(context, null);
    }

    public StringPopItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public StringPopItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setItem(String item) {
        super.setItem(item);
        textView.setText(item);
    }
}
