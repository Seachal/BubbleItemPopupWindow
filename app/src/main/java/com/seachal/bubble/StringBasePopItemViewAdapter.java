package com.seachal.bubble;

import android.content.Context;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seachal.library.BasePopupItemAdapter;
import com.seachal.library.BasePopupItemView;

import java.util.List;


public class StringBasePopItemViewAdapter extends BasePopupItemAdapter<StringPopItemView, String> {

    StringBasePopItemViewAdapter(Context context, List<String> data) {

        this(context, data, null);
    }

    public StringBasePopItemViewAdapter(Context context, List<String> data, List<String> selectItems) {
        super(context, data, selectItems);
    }


    @Override
    protected boolean checkIsItemSame(StringPopItemView view, String item) {
        return TextUtils.equals(view.getItem(), item);
    }

    @Override
    protected BasePopupItemView<String> addItem(String item) {
        StringPopItemView itemView = new StringPopItemView(getContext());
        itemView.setOrientation(LinearLayout.VERTICAL);
        itemView.setGravity(Gravity.CENTER_HORIZONTAL);
        itemView.setPadding(25, 10, 25, 10);


        TextView textView = itemView.getTextView();
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        //textView.setPadding(0,30,0,0);

        itemView.setItem(item);
        return itemView;
    }


    @Override
    protected boolean checkIsItemNull(String item) {
        return TextUtils.isEmpty(item);
    }


}
