package com.example.android.shoppinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.android.shoppinglist.model.SLItem;

import java.util.List;


public class SLAdapter extends RecyclerView.Adapter<SLAdapter.SLViewHolder> {

    public static final int SL_ITEM_DESCRIPTION_MAX = 50;

    private final List<SLItem> slItems;
    private final LayoutInflater layoutInflater;
    private final OnItemClickListener itemClickListener;



    public SLAdapter(Context context, List<SLItem> slItems, OnItemClickListener itemClickListener) {
        this.slItems = slItems;
        this.layoutInflater = LayoutInflater.from(context);
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public SLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View slItemView = layoutInflater.inflate(R.layout.sl_item, parent, false);
        return new SLViewHolder(slItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SLViewHolder slViewHolder, int position) {
        final SLItem slItem = slItems.get(position);

        slViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(slItem);
            }
        });

        slViewHolder.textViewTitle.setText(slItem.getTitle());

        String slItemDescription = slItem.getDescription();
        if (slItemDescription.length() > SL_ITEM_DESCRIPTION_MAX) {
            slItemDescription = slItemDescription.substring(0, SL_ITEM_DESCRIPTION_MAX - 3) + "...";
        }
        slViewHolder.textViewDescription.setText(slItemDescription);
        slViewHolder.textViewDescription.setVisibility(
                (slItemDescription == null || slItemDescription.isEmpty())
                ? View.GONE
                : View.VISIBLE
        );
    }

    @Override
    public int getItemCount() {
        return slItems.size();
    }

    public static class SLViewHolder
            extends RecyclerView.ViewHolder
    {
        public final TextView textViewTitle;
        public final TextView textViewDescription;

        public SLViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.sl_item_title);
            textViewDescription = itemView.findViewById(R.id.sl_item_description);
        }
    }

}
