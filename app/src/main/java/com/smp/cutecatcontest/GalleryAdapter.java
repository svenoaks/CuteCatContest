package com.smp.cutecatcontest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private Context context;
    private List<CatData> listData;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView image;
        protected TextView author;
        protected TextView votes;

        public ViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image_gallery);
            author =  (TextView) v.findViewById(R.id.text_author);
            votes = (TextView) v.findViewById(R.id.text_votes);
        }
    }


    public GalleryAdapter(List<CatData> listData, Context context)
    {
        this.listData = listData;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_gallery, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CatData cd = listData.get(position);
        Picasso.with(context).load(cd.getImageUrl()).into(holder.image);
        holder.author.setText("By: " + cd.getAuthor());
        holder.votes.setText("Votes: " + cd.getVotes());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return listData.size();
    }
}