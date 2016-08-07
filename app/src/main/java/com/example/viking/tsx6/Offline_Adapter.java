package com.example.viking.tsx6;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by viking on 21/7/16.
 */
public class Offline_Adapter extends RecyclerView.Adapter<Offline_Adapter.ViewHolder> {

    public Context context;
    private int[] imglist,titlelist,contentlist,tag_line;
    public View contact;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView card_title,card_content;
        public Button button;
        //  public TextView textView;

        public ViewHolder(View itmView) {
            super(itmView);

            imageView = (ImageView) itemView.findViewById(R.id.img);
            card_title = (TextView) itemView.findViewById(R.id.card_title);
            card_content = (TextView) itemView.findViewById(R.id.card_content);
            button = (Button) itemView.findViewById(R.id.card_button);
        }

    }
    public Offline_Adapter(int[] contacts,int[] title,int[] tagline,int[] content) {
        imglist = contacts;
        titlelist = title;
        contentlist = content;
        tag_line = tagline;


    }
    @Override
    public void onBindViewHolder(Offline_Adapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        ImageView im=viewHolder.imageView;
        im.setImageBitmap(decodeSampledBitmap(context.getResources(),imglist[position],200,200));

        // Set item views based on the data model
        //im.setImageResource(imglist[position]);
        //im.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //im.setPadding(10, 10, 10, 10);

        TextView tx1 = viewHolder.card_title;
        TextView tx2 = viewHolder.card_content;
        tx1.setText(titlelist[position]);
        tx2.setText(tag_line[position]);

        viewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context, R.style.DialogAnimation);
                dialog.setContentView(R.layout.for_dialog);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.background_image);
                //imageView.setImageResource(imglist[position - 1]);
                TextView textView = (TextView) dialog.findViewById(R.id.detailTitle);
                TextView title = (TextView) dialog.findViewById(R.id.title);

                title.setText(titlelist[position]);
                //title.setText(setEventname(getString(R.array.Offline_Title)));
                textView.setText(contentlist[position]);
                // imageView.setImageResource(imglist[position - 1]);
                imageView.setImageBitmap(decodeSampledBitmap(context.getResources(), imglist[position], 200, 200));
                BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                Bitmap blurred = MainActivity.blurRenderScript(bitmap, 25);//second parameter is radius
                imageView.setImageBitmap(blurred);

                //dialog.getWindow().setBackgroundDrawableResource(myImageList[position - 1]);
                dialog.getWindow().setFormat(PixelFormat.TRANSLUCENT);
                dialog.show();
            }
        });
    }
    @Override
    public Offline_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.card_view, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }
    // Return the total count of items
    @Override
    public int getItemCount() {
        return imglist.length;
    }

    public static Bitmap decodeSampledBitmap(Resources res,int resId,int reqwidth,int reqheight)
    {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res,resId,options);

        options.inSampleSize = calculate_image_size(options,reqwidth,reqheight);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
    public static int calculate_image_size(BitmapFactory.Options options,int reqwidth,int reqheight)
    {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(height>reqheight || width>reqwidth)
        {
            final int halfHeight = height/2;
            final int halfWidth = width/2;

            while((halfHeight/inSampleSize)>=reqheight && (halfWidth/inSampleSize)>= reqwidth)
            {
                inSampleSize=inSampleSize*2;
            }
        }
        return inSampleSize;
    }

}
