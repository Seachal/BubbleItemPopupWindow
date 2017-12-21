package com.seachal.bubble;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.seachal.library.BubblePopupWindow;
import com.seachal.library.BubbleRelativeLayout;
import com.seachal.library.interfaces.OnPopupSubscribeListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "LocationMain";

    private StringBasePopItemViewAdapter adapter;
    private StringBasePopItemViewAdapter adapter1;
    private List<String> sourceData;
    private List<String> sourceData1;
    private List<String> selectItems;
    private List<String> selectItems1;

    private BubblePopupWindow leftTopWindow;
    private BubblePopupWindow leftTopWindow2;

    private BubblePopupWindow rightTopWindow;
    private BubblePopupWindow leftBottomWindow;
    private BubblePopupWindow rightBottomWindow;
    private BubblePopupWindow centerWindow;

    LayoutInflater inflater;

    private TextView tv1;
    private TextView tv2;

    private int windowWidth;
    private int windowHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftTopWindow = new BubblePopupWindow(MainActivity.this);
        leftTopWindow2 = new BubblePopupWindow(MainActivity.this);
        rightTopWindow = new BubblePopupWindow(MainActivity.this);
        leftBottomWindow = new BubblePopupWindow(MainActivity.this);
        rightBottomWindow = new BubblePopupWindow(MainActivity.this);


        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        windowWidth = outMetrics.widthPixels;
        windowHeight = outMetrics.heightPixels;

//    public static int getScreenWidth(Context context) {
//        return context.getResources().getDisplayMetrics().widthPixels;
//    }

        initData1();

        // centerWindow = new BubblePopupWindow(MainActivity.this);
        //下面的操作是初始化弹出数据
        final ArrayList<String> strList = new ArrayList<>();
        strList.add("选项item1");
        strList.add("选项item2");
        strList.add("选项item3");

        ArrayList<View.OnClickListener> clickList = new ArrayList<>();
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击事件触发", Toast.LENGTH_SHORT).show();
            }
        };
        clickList.add(clickListener);
        clickList.add(clickListener);
        clickList.add(clickListener);

        //具体初始化逻辑看下面的图
        centerWindow = new BubblePopupWindow(MainActivity.this, strList, clickList);
        // centerWindow = new BubblePopupWindow(MainActivity.this)

        inflater = LayoutInflater.from(this);
    }

    //Bottom
    public void leftTop(View view) {
        View bubbleView = inflater.inflate(R.layout.layout_popup_view, null);
        tv1 = (TextView) bubbleView.findViewById(R.id.tv_content1);
        tv2 = (TextView) bubbleView.findViewById(R.id.tv_content2);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击事件触发" + tv1.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "点击事件触发" + tv2.getText(), Toast.LENGTH_SHORT).show();

            }
        });
        leftTopWindow.setBubbleView(bubbleView);
        leftTopWindow.show(view, Gravity.BOTTOM);


    }


    //Bottom2
    public void leftTop2(View view) {
        BubbleRelativeLayout bubbleView = new BubbleRelativeLayout(MainActivity.this);
        bubbleView.setOrientation(LinearLayout.VERTICAL);
        bubbleView.setBackgroundColor(Color.TRANSPARENT);
        adapter = new StringBasePopItemViewAdapter(this, sourceData, selectItems);
        adapter.setOnPopupSubscribeListener(new OnPopupSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {

                Toast.makeText(MainActivity.this, "点击事件触发" + selectedItem.get(0), Toast.LENGTH_SHORT).show();
                leftTopWindow.dismiss();
            }

        });
        bubbleView.setAdapter(adapter);
        leftTopWindow.setContentViewpop(bubbleView);
        bubbleView.setBubbleParams(BubbleRelativeLayout.BubbleLegOrientation.TOP,
                view.getWidth()/2);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        Log.i(TAG, "location[0]:" + location[0]);
        Log.i(TAG, "location[1]:" + location[1]);
        Log.i(TAG, "windowWidth:" + windowWidth);
        Log.i(TAG, "windowHeight:" + windowHeight);

        // rightTopWindow.showAtLocation(view,Gravity.NO_GRAVITY,location[0]-(bubbleView.getMeasuredWidth()),location[1]+view.getHeight());
//        leftTopWindow.showAtLocation(view, Gravity.NO_GRAVITY,
//                windowWidth - leftTopWindow.getMeasuredWidth(), location[1] + view.getHeight());
        leftTopWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                location[0], location[1] + view.getHeight());
        setBacgroundAlpha(leftTopWindow);
        Log.i(TAG, "location[0]:" + location[0]);
        Log.i(TAG, "view.getWidth():" + view.getWidth());
        Log.i(TAG, "rightTopWindow.getMeasuredWidth():" + leftTopWindow.getMeasuredWidth());
        Log.i(TAG, "windowWidth-leftTopWindow.getMeasuredWidth():"
                + (windowWidth - leftTopWindow.getMeasuredWidth()));


    }

    //Left
    public void rightTop(View view) {
        BubbleRelativeLayout bubbleView = new BubbleRelativeLayout(MainActivity.this);
        bubbleView.setOrientation(LinearLayout.VERTICAL);
        bubbleView.setBackgroundColor(Color.TRANSPARENT);
        adapter1 = new StringBasePopItemViewAdapter(this, sourceData1, selectItems1);
        adapter1.setOnPopupSubscribeListener(new OnPopupSubscribeListener<String>() {
            @Override
            public void onSubscribe(List<String> selectedItem) {

                Toast.makeText(MainActivity.this, "点击事件触发" + selectedItem.get(0), Toast.LENGTH_SHORT).show();
                rightTopWindow.dismiss();
            }

        });
        bubbleView.setAdapter(adapter1);
        rightTopWindow.setContentViewpop(bubbleView);
        bubbleView.setBubbleParams(BubbleRelativeLayout.BubbleLegOrientation.TOP,
                rightTopWindow.getMeasuredWidth() - (view.getWidth() / 2));


        int[] location = new int[2];
        view.getLocationOnScreen(location);


        Log.i(TAG, "location[0]:" + location[0]);
        Log.i(TAG, "location[1]:" + location[1]);


        Log.i(TAG, "windowWidth:" + windowWidth);
        Log.i(TAG, "windowHeight:" + windowHeight);


        // rightTopWindow.showAtLocation(view,Gravity.NO_GRAVITY,location[0]-(bubbleView.getMeasuredWidth()),location[1]+view.getHeight());
        rightTopWindow.showAtLocation(view, Gravity.NO_GRAVITY,
                windowWidth - rightTopWindow.getMeasuredWidth(), location[1] + view.getHeight());


        setBacgroundAlpha(rightTopWindow);


        Log.i(TAG, "location[0]:" + location[0]);
        Log.i(TAG, "view.getWidth():" + view.getWidth());
        Log.i(TAG, "rightTopWindow.getMeasuredWidth():" + rightTopWindow.getMeasuredWidth());
        Log.i(TAG, "location[0]-bubbleView.getMeasuredWidth():"
                + (location[0] - rightTopWindow.getMeasuredWidth()));
        // Log.i(TAG, "location[0]-bubbleView.getMeasuredWidth():"
        Log.i(TAG, "windowWidth-rightTopWindow.getMeasuredWidth():"
                + (windowWidth - rightTopWindow.getMeasuredWidth()));


    }

    //Top
    public void leftBottom(View view) {
        View bubbleView = inflater.inflate(R.layout.layout_popup_view, null);
        leftBottomWindow.setBubbleView(bubbleView);
        leftBottomWindow.show(view);
    }

    //Right
    public void rightBottom(View view) {
        View bubbleView = inflater.inflate(R.layout.layout_popup_view, null);
        rightBottomWindow.setBubbleView(bubbleView);
        rightBottomWindow.show(view, Gravity.RIGHT, 0);
    }


    public void center(View view) {
        //尖角偏移量为0
        centerWindow.show(view, Gravity.BOTTOM, 0);
    }


    private void initData1() {
        sourceData = new ArrayList<>();
        sourceData.add("111.");
        sourceData.add("2017年11月");
        //sourceData.add("汽车汽车汽车汽车汽车汽车汽车汽车汽车汽车汽车");
        sourceData.add("汽车汽车汽车汽车汽车");
        // sourceData.add("汽车汽车汽车");
        sourceData.add("eeee");
        sourceData.add("eeee");
        sourceData.add("sssss");
        selectItems = new ArrayList<>();

        sourceData1 = new ArrayList<>();
        sourceData1.add("111.");
        sourceData1.add("2017年12月");
        sourceData1.add("汽车汽车汽车汽车汽车汽");
        //sourceData1.add("汽车汽车汽车汽车汽车汽车汽车汽车汽车汽车汽车");
        // sourceData1.add("汽车汽车汽车");
        sourceData1.add("社会");
        sourceData1.add("搞笑");
        sourceData1.add("军事");
        selectItems1 = new ArrayList<>();
    }

    private void setBacgroundAlpha(PopupWindow popupWindow) {
        popupWindow.update();
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 0.2f;
        window.setAttributes(lp);
    }
}





