package com.demo.craftscc.storefront.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.craftscc.R;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.DrugItemClickListener;
import com.demo.craftscc.core.widgets.TextViewPlus;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anvith on 10/4/16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private WeakReference<DrugItemClickListener> drugItemClickListenerWeakReference;

    private List<Drug> items;

    private String[] types;

    public SearchAdapter(DrugItemClickListener drugItemClickListener, List<Drug> items) {
        this.drugItemClickListenerWeakReference = new WeakReference<>(drugItemClickListener);
        this.items = items;
        types = CraftsCCApplication.getInstance().getResources().getStringArray(R.array.item_types);
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_result, parent, false), drugItemClickListenerWeakReference);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {

        holder.setDrug(items.get(position));
        holder.name.setText(items.get(position).getName());
        holder.description.setText(CraftsCCApplication.getInstance().getResources().getString(R.string.storefront_search_description_placeholder, types[items.get(position).getCategory()]));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void resetData(List<Drug> drugs) {
        items.clear();
        items.addAll(drugs);
        notifyDataSetChanged();
    }

    static class SearchHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.search_name)
        TextViewPlus name;

        @BindView(R.id.search_description)
        TextViewPlus description;

        private Drug drug;

        private WeakReference<DrugItemClickListener> drugItemClickListenerWeakReference;

        public SearchHolder(View itemView, final WeakReference<DrugItemClickListener> drugItemClickListenerWeakReference) {
            super(itemView);
            this.drugItemClickListenerWeakReference = drugItemClickListenerWeakReference;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drugItemClickListenerWeakReference.get().onItemClick(drug);
                }
            });

        }

        public Drug getDrug() {
            return drug;
        }

        public void setDrug(Drug drug) {
            this.drug = drug;
        }
    }
}
