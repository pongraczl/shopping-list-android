package com.example.android.shoppinglist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SLItem implements Parcelable {
    private String title;
    private String description;


    public SLItem() {}

    public SLItem(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    /* START of Implementation of Parcelable interface */

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(title);
        out.writeString(description);
    }

    private SLItem(Parcel in) {
        title = in.readString();
        description = in.readString();
    }

    public static final Creator<SLItem> CREATOR = new Creator<SLItem>() {
        @Override
        public SLItem createFromParcel(Parcel in) {
            return new SLItem(in);
        }

        @Override
        public SLItem[] newArray(int size) {
            return new SLItem[size];
        }
    };

    /* END of implementation of Parcelable interface */
}
