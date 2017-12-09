package com.seachal.library;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seachal.library.interfaces.BasePopupItemViewListener;

/**
 * *
 * *
 * Project_Name:BubblePopupWindow
 *
 * @author zhangxc
 * @date 2017/12/7 下午4:38
 * *
 */

public class BasePopupItemView<T> extends LinearLayout implements View.OnClickListener {

    private T item;
    public View divider;



    // BaseItemView 用于显示的textView
    public TextView textView;

    private BasePopupItemViewListener<T> listener;

    private boolean isItemSelected;

    public BasePopupItemView(Context context) {
        super(context);
    }

    public BasePopupItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BasePopupItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setListener(BasePopupItemViewListener<T> listener) {
        this.listener = listener;
    }

    public View getDivider() {
        return divider;
    }

    public void setDivider(View divider) {
        this.divider = divider;
    }
    private void init() {

        //   divider = LayoutInflater.from(getContext()).inflate(R.layout.item_divider_line,this,false);
        divider = new View(getContext());
        divider.setBackgroundColor(Color.GRAY);
        LinearLayout.LayoutParams lylp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                10);
        lylp.gravity = Gravity.CENTER;
        divider.setLayoutParams(lylp);
        divider.setPadding(0,0,0,130);

        textView = new TextView(getContext());
        textView.setGravity(Gravity.CENTER);
        addView(divider);
        addView(textView);
        setOnClickListener(this);
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    @Override
    public void onClick(View v) {
        if (listener == null) {
            return;
        }
        //触发选中监听
        listener.onItemSelect(item);
    }


    /**
     * 判断是否被选中
     * //     * @return
     * //
     */
    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
    }

    public TextView getTextView() {
        return textView;
    }


}
