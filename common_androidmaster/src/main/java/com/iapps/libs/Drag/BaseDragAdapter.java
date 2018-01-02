package com.iapps.libs.Drag;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.iapps.adapters.BaseBean;

import java.util.List;

/**
 * Created by marcelsantoso.
 * <p/>
 * 6/22/16
 */
public abstract class BaseDragAdapter
        extends RecyclerView.Adapter<DragViewHolder>
        implements DraggableItemAdapter<DragViewHolder> {
    private List<Object> items;
    private Context      context;

    //    public BaseDragAdapter(Context context) {
    //
    //        this.context = context;
    //        setHasStableIds(true);
    //        items = new ArrayList<>();
    //    }

    public BaseDragAdapter(Context context, List<? extends Object> items) {
        this.context = context;
        setHasStableIds(true);
        this.items = (List<Object>) items;
    }

    public Context getContext() {
        return context;
    }

    public List<Object> getItems() {
        return items;
    }

    public BaseDragAdapter setItems(List<? extends Object> items) {
        this.items = (List<Object>) items;
        return this;
    }

    public void clearItems() {
        items.clear();
    }

    public void addItem(BaseBean obj) {
        items.add(obj);
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return ((BaseBean) items.get(position)).getId();
    }

    @Override
    public DragViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(cellLayout(), parent, false);
        return objectHolder(v);
    }


    @Override
    public void onBindViewHolder(DragViewHolder holder, int position) {
        setView(holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onMoveItem(int fromPosition, int toPosition) {
        Object movedItem = items.remove(fromPosition);
        items.add(toPosition, movedItem);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public boolean onCheckCanStartDrag(DragViewHolder holder, int position, int x, int y) {
        return true;
    }

    @Override
    public ItemDraggableRange onGetItemDraggableRange(DragViewHolder holder, int position) {
        return null;
    }

    @Override
    public boolean onCheckCanDrop(int draggingPosition, int dropPosition) {
        return true;
    }

    public abstract int cellLayout();

    public abstract DragViewHolder objectHolder(View v);

    public abstract void setView(DragViewHolder objectHolder, int position);

    public abstract void itemSelected(int position);

    public void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }
}
