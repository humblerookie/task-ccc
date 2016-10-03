package com.demo.craftscc.storefront.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.craftscc.R;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.DrugItemListener;
import com.demo.craftscc.core.utils.GlideUtil;
import com.demo.craftscc.core.widgets.TextViewPlus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalDrugListAdapter extends RecyclerView.Adapter<HorizontalDrugListAdapter.ViewHolder> {

    private List<Drug> items;

    private WeakReference<DrugItemListener> listenerWeakReference;

    public HorizontalDrugListAdapter(List<Drug> items, DrugItemListener drugItemListener) {
        this.items = items;
        listenerWeakReference = new WeakReference<>(drugItemListener);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_horizontal, parent, false));
        holder.setListenerWeakReference(listenerWeakReference);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Drug item = items.get(position);
        Context context = holder.discount.getContext();
        holder.price.setText(context.getString(R.string.title_price_placeholder, (item.getPrice() * (1 - item.getDiscount() / 100)) + ""));
        holder.name.setText(item.getName());
        holder.undiscountedPrice.setText(context.getString(R.string.title_price_placeholder, item.getPrice() + ""));
        holder.discount.setText(context.getString(R.string.title_discount_placeholder, item.getDiscount() + ""));
        GlideUtil.loadImage(new GlideUtil.Config()
                .setActivity((Activity) holder.discount.getContext())
                .setImageView(holder.icon)
                .setUrl(item.getIcons().get(0)));
        holder.setDrug(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.price)
        TextViewPlus price;

        @BindView(R.id.name)
        TextViewPlus name;

        @BindView(R.id.undiscounted_price)
        TextViewPlus undiscountedPrice;

        @BindView(R.id.discount)
        TextViewPlus discount;

        @BindView(R.id.image)
        ImageView icon;

        private Drug drug;

        private WeakReference<DrugItemListener> listenerWeakReference;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listenerWeakReference.get().onItemClick(drug);
                }
            });
        }

        public void setDrug(Drug drug) {
            this.drug = drug;
        }

        public void setListenerWeakReference(WeakReference<DrugItemListener> listenerWeakReference) {
            this.listenerWeakReference = listenerWeakReference;
        }
    }

    public void resetItems(ArrayList<Drug> items) {
        this.items = items;
        notifyDataSetChanged();
    }
}
