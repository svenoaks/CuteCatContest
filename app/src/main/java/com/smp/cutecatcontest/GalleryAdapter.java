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
    private List<GalleryData> listData;

    public static class GalleryData
    {
        protected String author;
        protected String votes;
       
        protected static final String AUTHOR_PREFIX = "By: ";
        protected static final String VOTES_PREFIX = "Votes: ";

    }
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


    public GalleryAdapter(List<GalleryData> listData, Context context)
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
        GalleryData ci = listData.get(position);
        Picasso.with(context).load(R.drawable.kitten).into(holder.image);
        holder.author.setText(ci.author);
        holder.votes.setText(ci.votes);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return listData.size();
    }
}