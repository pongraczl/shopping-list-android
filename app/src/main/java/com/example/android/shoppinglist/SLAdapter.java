package com.example.android.shoppinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.shoppinglist.model.SLItem;

import java.util.LinkedList;
import java.util.List;


public class SLAdapter extends RecyclerView.Adapter<SLAdapter.SLViewHolder> {

    private final List<SLItem> slItems;
    private final LayoutInflater layoutInflater;

    public SLAdapter(Context context, List<SLItem> slItems) {
        this.slItems = slItems;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View slItemView = layoutInflater.inflate(R.layout.sl_item, parent, false);
        return new SLViewHolder(slItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SLViewHolder slViewHolder, int position) {
        SLItem slItem = slItems.get(position);

        slViewHolder.textViewTitle.setText(slItem.getTitle());

        String slItemDescription = slItem.getDescription();
        int slItemDescriptionMax = 50;
        if (slItemDescription.length() > slItemDescriptionMax) {
            slItemDescription = slItemDescription.substring(0, slItemDescriptionMax - 3) + "...";
        }
        slViewHolder.textViewDescription.setText(slItemDescription);
    }

    @Override
    public int getItemCount() {
        return slItems.size();
    }

    public static class SLViewHolder
            extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        public final TextView textViewTitle;
        public final TextView textViewDescription;

        public SLViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.sl_item_title);
            textViewDescription = itemView.findViewById(R.id.sl_item_description);
        }

        @Override
        public void onClick(View v) {
            //TODO
        }
    }
}
