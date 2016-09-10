package com.example.viking.tsx6;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by viking on 21/7/16.
 */
public class About_Adapter extends
        RecyclerView.Adapter<About_Adapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public YouTubePlayerView youTubePlayerView;
        public TextView textView;

        public ViewHolder(View itmView) {
            super(itmView);
            //youTubePlayerView = (YouTubePlayerView) itemView.findViewById(R.id.youtube_view);
            //youTubePlayerView.initialize(Config.DEVELOPER_KEY,this);
            textView=(TextView)itemView.findViewById(R.id.about);

        }

    }

    // Pass in the contact array into the constructor
    public About_Adapter(){
    }

    @Override
    public About_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cardview_about, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView textView = holder.textView;
        textView.setTypeface(Splash_screen.typeface_3);


    }


    // Return the total count of items
    @Override
    public int getItemCount() {
        return 1;
    }

}