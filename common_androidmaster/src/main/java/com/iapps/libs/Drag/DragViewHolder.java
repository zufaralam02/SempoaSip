package com.iapps.libs.Drag;

import android.view.View;
import android.widget.FrameLayout;

import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.iapps.common_library.R;

/**
 * Created by marcelsantoso.
 * <p/>
 * 6/22/16
 */
public class DragViewHolder extends AbstractDraggableItemViewHolder {
    FrameLayout mContainer;

    public DragViewHolder(View itemView) {
        super(itemView);
        mContainer = (FrameLayout) itemView.findViewById(R.id.container);
    }
}
