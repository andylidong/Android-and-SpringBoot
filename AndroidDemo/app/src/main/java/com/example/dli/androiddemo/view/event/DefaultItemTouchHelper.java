package com.example.dli.androiddemo.view.event;


import android.support.v7.widget.helper.ItemTouchHelper;

public class DefaultItemTouchHelper extends TempItemTouchHelper {

    private DefaultItemTouchHelpCallback itemTouchHelpCallback;

    public DefaultItemTouchHelper(DefaultItemTouchHelpCallback.OnItemTouchCallbackListener onItemTouchCallbackListener) {
        super(new DefaultItemTouchHelpCallback(onItemTouchCallbackListener));
        itemTouchHelpCallback = (DefaultItemTouchHelpCallback) getCallback();
        this.setDragEnable(true);
        this.setSwipeEnable(true);
    }

    /**
     * 设置是否可以被拖拽
     *
     * @param canDrag 是true，否false
     */
    public void setDragEnable(boolean canDrag) {
        itemTouchHelpCallback.setDragEnable(canDrag);
    }

    /**
     * 设置是否可以被滑动
     *
     * @param canSwipe 是true，否false
     */
    public void setSwipeEnable(boolean canSwipe) {
        itemTouchHelpCallback.setSwipeEnable(canSwipe);
    }
}

class TempItemTouchHelper extends ItemTouchHelper {

    private Callback callback;

    public TempItemTouchHelper(Callback callback) {
        super(callback);
        this.callback = callback;
    }

    public Callback getCallback() {
        return callback;
    }
}