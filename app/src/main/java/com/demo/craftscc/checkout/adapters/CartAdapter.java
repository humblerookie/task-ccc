package com.demo.craftscc.checkout.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.craftscc.R;
import com.demo.craftscc.checkout.models.CartItem;
import com.demo.craftscc.checkout.utils.CartHelper;
import com.demo.craftscc.core.CraftsCCApplication;
import com.demo.craftscc.core.utils.GlideUtil;
import com.demo.craftscc.core.widgets.TextViewPlus;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by anvith on 10/4/16.
 */

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int ITEM_NORMAL = 0;

    public static final int ITEM_FOOTER = 1;

    private HashMap<Integer, CartItem> cartItemHashMap;

    private WeakReference<ItemActionListener> itemActionListenerWeakReference;

    private ArrayList<Integer> keyset;

    private CartHelper cartHelper;

    public CartAdapter(HashMap<Integer, CartItem> cartItemHashMap, ItemActionListener itemActionListener, CartHelper cartHelper) {
        this.cartItemHashMap = cartItemHashMap;
        keyset = new ArrayList<>(cartItemHashMap.keySet());
        this.itemActionListenerWeakReference = new WeakReference<>(itemActionListener);
        this.cartHelper = cartHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_NORMAL) {
            return new CartHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false), itemActionListenerWeakReference);
        }
        return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_footer, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hold, int position) {

        if (getItemViewType(position) == ITEM_NORMAL) {
            CartHolder holder = (CartHolder) hold;
            holder.setPos(position);
            CartItem cartItem = cartItemHashMap.get(keyset.get(position));
            holder.setCartItem(cartItem);
            holder.name.setText(cartItem.getDrug().getName());
            holder.price.setText(CraftsCCApplication.getInstance().getString(R.string.title_price_placeholder, cartItem.getDrug().getDiscountedPrice()));
            holder.quantity.setText(cartItem.getQuantity() + "");
            GlideUtil.loadImage(new GlideUtil.Config()
                    .setActivity((Activity) holder.name.getContext())
                    .setImageView(holder.icon)
                    .setUrl(cartItem.getDrug().getImages().get(0)).setPlaceholder(android.R.drawable.gallery_thumb));
            holder.itemTotal.setText(CraftsCCApplication.getInstance().getString(R.string.title_price_placeholder, (cartItem.getDrug().getDiscountedPrice() * cartItem.getQuantity())));
        } else {
            FooterHolder footerHolder = (FooterHolder) hold;
            footerHolder.orderSubTotal.setText(cartHelper.getCartTotalValue() + "");
            footerHolder.orderTotal.setText(cartHelper.getCartTotalValue() + "");
        }
    }

    @Override
    public int getItemCount() {
        return cartItemHashMap.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position == cartItemHashMap.size() ? ITEM_FOOTER : ITEM_NORMAL;
    }

    public void resetData(HashMap<Integer, CartItem> cartItemHashMap) {
        this.cartItemHashMap = cartItemHashMap;
        notifyDataSetChanged();
    }

    public static interface ItemActionListener {

        void decrementItem(int pos, int drugId);

        void incrementItem(int pos, int drugId);

        void removeItem(int pos, int drugId);
    }

    static class CartHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.drug_name)
        TextViewPlus name;

        @BindView(R.id.price)
        TextViewPlus price;

        @BindView(R.id.quantity)
        TextViewPlus quantity;

        @BindView(R.id.item_total)
        TextViewPlus itemTotal;

        @BindView(R.id.icon)
        ImageView icon;

        private CartItem cartItem;

        private int pos;

        private WeakReference<ItemActionListener> itemActionListenerWeakReference;

        public CartHolder(View itemView, WeakReference<ItemActionListener> itemActionListenerWeakReference) {
            super(itemView);
            this.itemActionListenerWeakReference = itemActionListenerWeakReference;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.reduce_qty)
        void reduceQuantity() {
            itemActionListenerWeakReference.get().decrementItem(pos, cartItem.getDrug().getId());
        }

        @OnClick(R.id.increase_qty)
        void incrementQuantity() {
            itemActionListenerWeakReference.get().incrementItem(pos, cartItem.getDrug().getId());
        }

        @OnClick(R.id.clear_item)
        void clearItem() {
            itemActionListenerWeakReference.get().removeItem(pos, cartItem.getDrug().getId());
        }

        public CartItem getCartItem() {
            return cartItem;
        }

        public void setCartItem(CartItem cartItem) {
            this.cartItem = cartItem;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }
    }

    static class FooterHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_total)
        TextViewPlus orderTotal;

        @BindView(R.id.item_sub_total)
        TextViewPlus orderSubTotal;

        public FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
