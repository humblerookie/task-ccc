package com.demo.craftscc.storefront.adapters;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.craftscc.R;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.DrugItemClickListener;
import com.demo.craftscc.core.widgets.TextViewPlus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreFrontAdapter extends RecyclerView.Adapter<StoreFrontAdapter.ViewHolder> {

    private List<String> items;
    private ArrayList<ArrayList<Drug>> drugs;

    private WeakReference<DrugItemClickListener> listenerWeakReference;

    public StoreFrontAdapter(List<String> items, ArrayList<ArrayList<Drug>> drugs, DrugItemClickListener drugItemClickListener) {
        this.items = items;
        listenerWeakReference = new WeakReference<>(drugItemClickListener);
        this.drugs = drugs;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_storefront_horizontal, parent, false));
        holder.setAdapter(new HorizontalDrugListAdapter(new ArrayList<Drug>(), listenerWeakReference.get()));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(items.get(position));
        holder.getAdapter().resetItems(drugs.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void resetData(List<String> items, ArrayList<ArrayList<Drug>> drugs) {
        this.items = items;
        this.drugs = drugs;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.category_label)
        TextViewPlus title;

        @BindView(R.id.category_list)
        RecyclerView recyclerView;

        private HorizontalDrugListAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(title.getContext());
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        public HorizontalDrugListAdapter getAdapter() {
            return adapter;
        }

        public void setAdapter(HorizontalDrugListAdapter adapter) {
            this.adapter = adapter;
            recyclerView.setAdapter(adapter);
        }
    }
}
