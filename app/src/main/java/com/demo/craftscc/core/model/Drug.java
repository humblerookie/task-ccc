package com.demo.craftscc.core.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.demo.craftscc.core.utils.NonObfuscable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anvith on 10/3/16.
 */

public class Drug implements Parcelable, NonObfuscable {

    private int id;
    private String name;
    private String description;
    private float price;
    private float discount;
    private int status;
    private String seller;
    private int category;
    private List<String> image;
    private float rating;
    private int reviews;

    public Drug() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<String> getImages() {
        return image;
    }

    public void setImages(List<String> image) {
        this.image = image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Drug(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        price = in.readFloat();
        discount = in.readFloat();
        status = in.readInt();
        seller = in.readString();
        category = in.readInt();
        if (in.readByte() == 0x01) {
            image = new ArrayList<String>();
            in.readList(image, String.class.getClassLoader());
        } else {
            image = null;
        }
        rating = in.readFloat();
        reviews = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeFloat(price);
        dest.writeFloat(discount);
        dest.writeInt(status);
        dest.writeString(seller);
        dest.writeInt(category);
        if (image == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(image);
        }
        dest.writeFloat(rating);
        dest.writeInt(reviews);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Drug> CREATOR = new Parcelable.Creator<Drug>() {
        @Override
        public Drug createFromParcel(Parcel in) {
            return new Drug(in);
        }

        @Override
        public Drug[] newArray(int size) {
            return new Drug[size];
        }
    };

    public float getDiscountedPrice() {
        return (int) (getPrice() * (1 - getDiscount() / 100));
    }
}