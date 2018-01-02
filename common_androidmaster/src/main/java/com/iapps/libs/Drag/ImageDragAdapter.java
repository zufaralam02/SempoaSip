package com.iapps.libs.Drag;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.iapps.common_library.R;
import com.iapps.libs.helpers.BaseHelper;
import com.iapps.libs.views.ImageViewLoader;

import java.util.List;


/**
 * Created by marcelsantoso.
 * <p/>
 * 5/23/16
 */
public abstract class ImageDragAdapter extends BaseDragAdapter {
    int allowEditPos = 0;

    public ImageDragAdapter(Context context, List<? extends Object> items) {
        super(context, items);
    }

    @Override
    public int cellLayout() {
        return R.layout.drag_image;
    }

    @Override
    public DragViewHolder objectHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    public void setView(DragViewHolder objectHolder, final int position) {
        ViewHolder holder = (ViewHolder) objectHolder;
        BImage     item   = (BImage) getItem(position);

        holder.img.setSquareToWidth(true);
        if (BaseHelper.isEmpty(item.getImgUrl()))
            holder.img.loadImage(R.drawable.ic_camera_border);
        else
            holder.img.loadImage(item.getImgUrl());

        if (position <= allowEditPos) {
            if (!((BImage) getItem(position)).isHasImage())
                // set placeholder enable
                holder.img.setBackgroundColor(Color.TRANSPARENT);

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemSelected(position);
                }
            });
        } else {
            holder.img.setBackgroundColor(Color.parseColor("#eeeeee"));
        }

        if (position < allowEditPos) {
            holder.tvMain.setVisibility(View.VISIBLE);
            holder.tvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(position);
                }
            });
        } else
            holder.tvMain.setVisibility(View.INVISIBLE);
    }

    public ImageDragAdapter setAllowEditPos(int allowEditPos) {
        this.allowEditPos = allowEditPos;
        return this;
    }

    public int getAllowEditPos() {
        return allowEditPos;
    }

    public class ViewHolder extends DragViewHolder {
        //        public LinearLayout ll;
        TextView        tvMain;
        ImageViewLoader img;

        public ViewHolder(View v) {
            super(v);
            //            ll = (LinearLayout) v.findViewById(R.id.ll);
            img = (ImageViewLoader) v.findViewById(R.id.img);
            tvMain = (TextView) v.findViewById(R.id.tvMain);
        }
    }

    @Override
    public boolean onCheckCanStartDrag(DragViewHolder holder, int position, int x, int y) {
        return ((BImage) getItem(position)).isHasImage();
    }

    @Override
    public void removeItem(int position) {
        deleteItem(position);
//        allowEditPos--;
//        BImage b = (BImage) getItem(position);
//        b.setImgUrl("");
//        b.setHasImage(false);
//
//        getItems().remove(position);
//        getItems().add(b);
//
//        notifyDataSetChanged();
    }

    public abstract void deleteItem(int position);
}