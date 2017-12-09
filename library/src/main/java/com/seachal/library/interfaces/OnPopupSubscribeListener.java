package com.seachal.library.interfaces;

import java.util.List;

/**
 * *
 * *
 * Project_Name:BubblePopupWindow
 *
 * @author zhangxc
 * @date 2017/12/7 下午6:52
 * *
 */

public interface OnPopupSubscribeListener<T> {

    void onSubscribe(List<T> selectedItem);
}
