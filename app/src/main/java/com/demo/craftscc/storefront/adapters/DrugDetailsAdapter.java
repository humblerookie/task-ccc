package com.demo.craftscc.storefront.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.craftscc.R;
import com.demo.craftscc.core.model.Drug;
import com.demo.craftscc.core.utils.GlideUtil;
import com.demo.craftscc.core.utils.OnItemClickListener;
import com.demo.craftscc.core.widgets.TextViewPlus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by anvith on 10/4/16.
 */

public class DrugDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int VIEW_COUNT = 2;

    public static final int VIEW_HEADER = 0;

    public static final int VIEW_DESCRIPTION = 1;


    private Drug item;


    public DrugDetailsAdapter(Drug item) {
        this.item = item;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_HEADER) {
            return new HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drugdetail_header, parent, false), item.getImages());
        } else {
            return new DescriptionHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drugdetail_description, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof DescriptionHolder) {
            final DescriptionHolder descriptionHolder = (DescriptionHolder) holder;
            Context context = descriptionHolder.description.getContext();
            descriptionHolder.name.setText(item.getName());
            descriptionHolder.description.setText(item.getDescription());
            descriptionHolder.discountedPrice.setText(context.getString(R.string.title_price_placeholder, ((int) (item.getPrice() * (1 - item.getDiscount() / 100))) + ""));
            descriptionHolder.sellerName.setText(item.getSeller());
            descriptionHolder.undiscountedPrice.setText(context.getString(R.string.title_price_placeholder, item.getPrice() + ""));
            descriptionHolder.rating.setText(context.getString(R.string.title_rating_placeholder, item.getRating() + ""));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VIEW_HEADER;
        } else {
            return VIEW_DESCRIPTION;
        }
    }

    @Override
    public int getItemCount() {
        return VIEW_COUNT;
    }


    static class HeaderHolder extends RecyclerView.ViewHolder implements OnItemClickListener {

        @BindView(R.id.main_image)
        ImageView mainImage;


        @BindView(R.id.image_list)
        RecyclerView imageList;

        List<String> images;

        private int lastPosition;

        public HeaderHolder(View itemView, List<String> images) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.images = images;

            GlideUtil.loadImage(new GlideUtil.Config()
                    .setActivity((Activity) itemView.getContext())
                    .setUrl(images.get(0))
                    .setImageView(mainImage));
            imageList.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            imageList.setAdapter(new HorizontalImageListAdapter(images, this, (Activity) itemView.getContext()));
        }

        @Override
        public void onItemClicked(int position) {
            if (lastPosition != position) {
                GlideUtil.loadImage(new GlideUtil.Config()
                        .setActivity((Activity) itemView.getContext())
                        .setUrl(images.get(position))
                        .setImageView(mainImage));
                lastPosition = position;
            }
        }
    }


    static class DescriptionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.drug_name)
        TextViewPlus name;

        @BindView(R.id.product_description)
        TextViewPlus description;

        @BindView(R.id.rating)
        TextViewPlus rating;

        @BindView(R.id.undiscounted_price)
        TextViewPlus undiscountedPrice;

        @BindView(R.id.price)
        TextViewPlus discountedPrice;

        @BindView(R.id.seller_name)
        TextViewPlus sellerName;

        public DescriptionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
