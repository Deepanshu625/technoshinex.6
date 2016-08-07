package com.example.viking.tsx6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.viking.tsx6.Fragments.Sponsors;

/**
 * Created by viking on 21/7/16.
 */
public class Sponsors_Adapter extends RecyclerView.Adapter<Sponsors_Adapter.ViewHolder> {
    private int[] imglist;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        //  public TextView textView;

        public ViewHolder(View itmView) {
            super(itmView);
            imageView = (ImageView) itemView.findViewById(R.id.image_view_sponsors);
        }

    }
    public Sponsors_Adapter(int[] contacts) {
        imglist = contacts;
    }
    @Override
    public void onBindViewHolder(Sponsors_Adapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        ImageView im=viewHolder.imageView;

        // Set item views based on the data model
        im.setImageResource(imglist[position]);
        im.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        im.setPadding(10, 10, 10, 10);
    }
    @Override
    public Sponsors_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cardview_sponsors, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Return the total count of items
    @Override
    public int getItemCount() {
        return imglist.length;
    }
}
