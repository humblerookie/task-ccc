package com.demo.craftscc.storefront.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.craftscc.R;
import com.demo.craftscc.core.utils.GlideUtil;
import com.demo.craftscc.core.utils.OnItemClickListener;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalImageListAdapter extends RecyclerView.Adapter<HorizontalImageListAdapter.ViewHolder> {

    private List<String> items;

    private WeakReference<OnItemClickListener> listenerWeakReference;

    private WeakReference<Activity> activityWeakReference;

    public HorizontalImageListAdapter(List<String> items, OnItemClickListener onItemClickListener, Activity activity) {
        this.items = items;
        listenerWeakReference = new WeakReference<>(onItemClickListener);
        activityWeakReference = new WeakReference<>(activity);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal_imagelist, parent, false));
        holder.setListenerWeakReference(listenerWeakReference);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GlideUtil.loadImage(new GlideUtil.Config()
                .setActivity(activityWeakReference.get())
                .setImageView(holder.icon)
                .setUrl(items.get(position))
                .setPlaceholder(android.R.drawable.gallery_thumb));
        holder.setPos(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon_small)
        ImageView icon;

        private WeakReference<OnItemClickListener> listenerWeakReference;

        private int pos;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerWeakReference.get().onItemClicked(pos);
                }
            });
        }


        public void setListenerWeakReference(WeakReference<OnItemClickListener> listenerWeakReference) {
            this.listenerWeakReference = listenerWeakReference;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
    }

    public void resetItems(List<String> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
