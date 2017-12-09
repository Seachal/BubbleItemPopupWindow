package com.seachal.library;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.view.View;

import com.seachal.library.interfaces.BasePopupItemViewListener;
import com.seachal.library.interfaces.OnPopupSubscribeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * *
 * *
 * Project_Name:BubblePopupWindow
 *
 * @author zhangxc
 * @date 2017/12/7 下午7:02
 * *
 */

public abstract class BasePopupItemAdapter<V extends BasePopupItemView<T>, T> {

    private Context context;
    /**
     * 根布局
     */
    private BubbleRelativeLayout rootView;

    /**
     * 数据源
     */
    private List<T> sourceData;

    /**
     * 已选项目
     */
    private List<T> selectItems;

    /**
     * View和item的对应关系 ？？？
     */
    private Map<V, T> viewMap;

    /**
     * item选择操作的订阅接口
     */
    private OnPopupSubscribeListener mOnPopupSubscribeListener;


    public void setRootView(BubbleRelativeLayout rootView) {
        this.rootView = rootView;
    }

    public List<T> getSourceData() {
        return sourceData;
    }

    public void setSourceData(List<T> sourceData) {
        this.sourceData = sourceData;
    }

    public List<T> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<T> selectItems) {
        this.selectItems = selectItems;
    }


    public OnPopupSubscribeListener getOnPopupSubscribeListener() {
        return mOnPopupSubscribeListener;
    }

    public void setOnPopupSubscribeListener(OnPopupSubscribeListener<T> onPopupSubscribeListener) {
        mOnPopupSubscribeListener = onPopupSubscribeListener;
    }


    public BasePopupItemAdapter(Context context, List<T> sourceData, List<T> selectItems) {
        this.context = context;
        this.sourceData = sourceData;
        this.selectItems = selectItems;
        viewMap = new ArrayMap<>();
    }

    public Context getContext() {
        return context;
    }

    /**
     * 添加Item组
     *
     * @param
     */
    public void addAllItem() {
        if (sourceData == null || sourceData.size() <= 0) {
            return;
        }
        for (int i = 0; i < sourceData.size(); i++) {
            if (sourceData.get(i) == null) {
                continue;
            }

            final BasePopupItemView<T> view = addItem(sourceData.get(i));
            if (i==0){
                view.divider.setVisibility(View.GONE);
            }
            initItemViews((V) view);
            /**
             * 给每一个控件设置监听,在被Onclick执行触发
             */
            view.setListener(new BasePopupItemViewListener<T>() {
                @Override
                public void onItemSelect(T item) {
                    singleSelectMode(item);
                    if (mOnPopupSubscribeListener != null) {
                        //得到选中的item列表，
                        mOnPopupSubscribeListener.onSubscribe(getSelectedItemList());
                    }
                }
            });
            // View为key， 循环加入所有的view和view的item
            viewMap.put((V) view, sourceData.get(i));

            rootView.addView(view);
        }
    }


    /**
     *   通过比较item的值，确定那个ItemView为选中
     *
     * @param item
     */
    private void singleSelectMode(T item) {

        for (BasePopupItemView<T> view : viewMap.keySet()) {
            if (checkIsItemSame((V) view, item)) {
                view.setItemSelected(true);
            } else {
                view.setItemSelected(false);
            }
        }
    }
    private void initItemViews(V view) {
        //如果没有选中项，什么也不做
        if (selectItems == null || selectItems.size() <= 0) {
            return;
        }

        for (T select : selectItems) {
            if (checkIsItemNull(select)) {
                continue;
            }
            //如果相等，则把view设置为选中状态值
            if (checkIsItemSame(view, select)) {
                view.setItemSelected(true);
                break;
            }
        }
    }

    protected abstract boolean checkIsItemNull(T select);

    protected abstract boolean checkIsItemSame(V view, T select);


    protected abstract BasePopupItemView<T> addItem(T item);

    // @SuppressWarnings("SuspiciousMethodCalls")
    public List<T> getSelectedItemList() {
        List<T> selectedList = new ArrayList<>();
        for (BasePopupItemView<T> view : viewMap.keySet()) {
            //如果itemview是选中状态
            if (view.isItemSelected()) {
                //取出view的item
                T item = viewMap.get(view);
                //加入到记录选中列表。
                selectedList.add(item);
            }
        }
        return selectedList;
    }

    /**
     * 绑定控件
     *
     * @param rootView
     */
    public void bindView(BubbleRelativeLayout rootView) {
        if (rootView == null) {
            throw new NullPointerException("未初始化TagFlowLayout");
        }
        this.rootView = rootView;
    }

    public BubbleRelativeLayout getRootView() {
        return rootView;
    }
}
