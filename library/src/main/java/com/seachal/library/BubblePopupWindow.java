package com.seachal.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyh.
 * @date 2016/8/25.
 */
public class BubblePopupWindow extends PopupWindow  implements PopupWindow.OnDismissListener{

    public static final String TAG ="LocationPop";

    private BubbleRelativeLayout bubbleView;
    private Context context;

    public BubblePopupWindow(Context context) {
        this.context = context;
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setOutsideTouchable(false);
        setClippingEnabled(false);

        ColorDrawable dw = new ColorDrawable(0);
        setBackgroundDrawable(dw);
        setOnDismissListener(this);
        setAnimationStyle(R.style.popp_anim);
    }


    /**
     * @param contentList 点击item的内容文字
     * @param clickList   点击item的事件
     *                    文字和click事件的list是对应绑定的
     */
    public BubblePopupWindow(Context context, List<String> contentList, List<View.OnClickListener> clickList) {
        this.context = context;
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        setOutsideTouchable(false);
        setClippingEnabled(false);

        ColorDrawable dw = new ColorDrawable(0);
        setBackgroundDrawable(dw);
        // windowManager = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);

        setOnDismissListener(this);
        setAnimationStyle(R.style.popp_anim);
        initLayout(contentList, clickList);
    }


    /**
     * /**
     *
     * @param contentList 点击item内容的文字
     * @param clickList   点击item的事件
     */
    public void initLayout(List<String> contentList, List<View.OnClickListener> clickList) {
        bubbleView = new BubbleRelativeLayout(context);
        bubbleView.setOrientation(LinearLayout.VERTICAL);
        bubbleView.setBackgroundColor(Color.TRANSPARENT);
        //这是根布局
        // rootView = (ViewGroup) View.inflate(activity, R.layout.item_root_hintpopupwindow, null);
        // linearLayout = (ViewGroup) rootView.findViewById(R.id.linearLayout);
        //格式化点击item, 将文字和click事件一一绑定上去
        List<View> list = new ArrayList<>();
        for (int x = 0; x < contentList.size(); x++) {
            View view = View.inflate(context, R.layout.item_hint_popupwindow, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_content);
            View v_line = view.findViewById(R.id.v_line);
            textView.setText(contentList.get(x));
            bubbleView.addView(view);
            list.add(view);
            //   第一个的分割线不占空间
            if (x == 0) {
                v_line.setVisibility(View.GONE);
            } else {
                v_line.setVisibility(View.VISIBLE);
            }
        }
        for (int x = 0; x < list.size(); x++) {
            list.get(x).setOnClickListener(clickList.get(x));
        }
        setContentView(bubbleView);
    }

    public void setContentViewpop(BubbleRelativeLayout bubbleView) {
//        bubbleView = new BubbleRelativeLayout(context);
//        bubbleView.setOrientation(LinearLayout.VERTICAL);
//        bubbleView.setBackgroundColor(Color.TRANSPARENT);
        this.bubbleView = bubbleView;
        setContentView(bubbleView);
    }

    public void setBubbleView(View view) {
        bubbleView = new BubbleRelativeLayout(context);
        bubbleView.setOrientation(LinearLayout.VERTICAL);
        bubbleView.setBackgroundColor(Color.TRANSPARENT);
        bubbleView.addView(view);
        setContentView(bubbleView);
    }

    public void setParam(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void show(View parent) {
        show(parent, Gravity.TOP, getMeasuredWidth() / 2);
    }

    public void show(View parent, int gravity) {
        show(parent, gravity, getMeasuredWidth() / 2);
    }

    /**
     * 显示弹窗
     *
     * @param parent
     * @param gravity
     * @param bubbleOffset 气泡尖角位置偏移量。默认位于中间
     */
    public void show(View parent, int gravity, float bubbleOffset) {
        BubbleRelativeLayout.BubbleLegOrientation orientation = BubbleRelativeLayout.BubbleLegOrientation.LEFT;
        if (!this.isShowing()) {
            switch (gravity) {
                case Gravity.BOTTOM:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.TOP;
                    break;
                case Gravity.TOP:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.BOTTOM;
                    break;
                case Gravity.RIGHT:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.LEFT;
                    break;
                case Gravity.LEFT:
                    orientation = BubbleRelativeLayout.BubbleLegOrientation.RIGHT;
                    break;
                default:
                    break;
            }
            bubbleView.setBubbleParams(orientation, bubbleOffset); // 设置气泡布局方向及尖角偏移

            int[] location = new int[2];
            parent.getLocationOnScreen(location);

            //弹回框显示的位置
            switch (gravity) {

                case Gravity.BOTTOM:
                    showAtLocation(parent, Gravity.NO_GRAVITY, location[0]-(parent.getWidth()/2),location[1] + (parent.getHeight() ) );
                    Log.i(TAG,"location[0]:" + location[0]);
                    Log.i(TAG,"location[1]:" + location[1]);
                    Log.i(TAG,"getMeasuredWidth():" + getMeasuredWidth());
                    Log.i(TAG,"getMeasureHeight():" + getMeasureHeight());

                    Log.i(TAG,"parent.getHeight():" + parent.getHeight());
                    Log.i(TAG,"parent.getWidth():" + parent.getWidth());
                    break;
                case Gravity.TOP:
                    showAtLocation(parent, Gravity.NO_GRAVITY, location[0], location[1] - getMeasureHeight());

                    Log.i(TAG,"getMeasuredWidth():" + getMeasuredWidth());
                    Log.i(TAG,"getMeasureHeight():" + getMeasureHeight());

                    Log.i(TAG,"parent.getHeight():" + parent.getHeight());
                    Log.i(TAG,"parent.getWidth():" + parent.getWidth());
                    break;
                case Gravity.RIGHT:
                    showAtLocation(parent, Gravity.NO_GRAVITY, location[0] + parent.getWidth(), location[1] - (parent.getHeight() / 2));

                    Log.i(TAG,"getMeasuredWidth():" + getMeasuredWidth());
                    Log.i(TAG,"getMeasureHeight():" + getMeasureHeight());

                    Log.i(TAG,"parent.getHeight():" + parent.getHeight());
                    Log.i(TAG,"parent.getWidth():" + parent.getWidth());
                    break;
                case Gravity.LEFT:

                    Log.i(TAG,"getMeasuredWidth():" + getMeasuredWidth());
                    Log.i(TAG,"getMeasureHeight():" + getMeasureHeight());

                    Log.i(TAG,"parent.getHeight():" + parent.getHeight());
                    Log.i(TAG,"parent.getWidth():" + parent.getWidth());
                    showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - getMeasuredWidth(), location[1] - (parent.getHeight() / 2));
                    break;
                default:
                    break;
            }
        } else {
            this.dismiss();
        }
    }

    /**
     * 测量高度
     *
     * @return
     */
    public int getMeasureHeight() {
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popHeight = getContentView().getMeasuredHeight();
        return popHeight;
    }

    /**
     * 测量宽度
     *
     * @return
     */
    public int getMeasuredWidth() {
        getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popWidth = getContentView().getMeasuredWidth();
        return popWidth;
    }

    @Override
    public void onDismiss() {
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = 1.0f;
        ((Activity)context).getWindow().setAttributes(lp);
    }
}
