package com.smp.cutecatcontest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import com.shamanland.fab.ShowHideOnScroll;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GalleryFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    @InjectView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    //@InjectView(R.id.floating_button_gallery) com.shamanland.fab.FloatingActionButton mFloatingActionButton;
    @InjectView(R.id.flipper)
    ViewFlipper mFlipper;
    private RecyclerView.Adapter mAdapter;
    private GridLayoutManager mLayoutManager;

    public static GalleryFragment newInstance(int position) {
        GalleryFragment f = new GalleryFragment();
        Bundle b = new Bundle();
        b.putInt(ARG_POSITION, position);
        f.setArguments(b);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(ARG_POSITION);
    }

    private static int CARD_WIDTH_MIN = 150;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       View view =  inflater.inflate(R.layout.fragment_gallery, null);
       ButterKnife.inject(this, view);
       mRecyclerView.setHasFixedSize(true);

       mLayoutManager = new GridLayoutManager(this.getActivity(), 2);
       mRecyclerView.setLayoutManager(mLayoutManager);
       calculateSpans();
       mRecyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {

           @Override
           public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom)
           {
               calculateSpans();
           }
       });

       retrieveGallery();

       //mRecyclerView.setOnTouchListener(new ShowHideOnScroll(mFloatingActionButton));
       return view;
    }

    private static final int VIEW_RECYCLER_NUM = 1;
    private static final int VIEW_PROGRESS_NUM = 0;

    private void retrieveGallery()
    {
        GalleryClient.get("gallery/cutest", null, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable)
            {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString)
            {
                Gson gson = new Gson();
                CatData[] cats = gson.fromJson(responseString, CatData[].class);
                mAdapter = new GalleryAdapter(Arrays.asList(cats), GalleryFragment.this.getActivity());
                mRecyclerView.setAdapter(mAdapter);
                mFlipper.setDisplayedChild(VIEW_RECYCLER_NUM);
            }
        });
    }
    private void calculateSpans()
    {
        DisplayMetrics displayMetrics = GalleryFragment.this.getActivity().getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        mLayoutManager.setSpanCount((int) dpWidth/CARD_WIDTH_MIN);
    }
    private static List<CatData> createList() {
/*
        List<CatData> result = new ArrayList<CatData>();
        for (int i=1; i <= 30; i++) {
            CatData c = new CatDataBuilder().createCatData();
            switch (i % 6)
            {
                case 0:
                    c.setAuthor("Joe");
                    c.setVotes("4");
                    c.setImageUrl("http://www.findcatnames.com/wp-content/uploads/2014/09/453768-cats-cute-300x225.jpg");
                    break;
                case 1:
                    c.setAuthor("Steve");
                    c.setVotes("7");
                    c.setImageUrl("http://images.fanpop.com/images/image_uploads/so-freakin-cute--cats-248645_500_375.jpg");
                    break;
                case 2:
                    c.setAuthor("Jane");
                    c.setVotes("9");
                    c.setImageUrl("http://images.fanpop.com/images/image_uploads/so-freakin-cute--cats-248642_500_375.jpg");
                    break;
                case 3:
                    c.setAuthor("Jille");
                    c.setVotes("10");
                    c.setImageUrl("http://images.fanpop.com/images/image_uploads/so-freakin-cute--cats-248639_500_375.jpg");
                    break;
                case 4:
                    c.setAuthor("Dan");
                    c.setVotes("12");
                    c.setImageUrl("http://images.fanpop.com/images/image_uploads/so-freakin-cute--cats-248633_500_375.jpg");
                    break;
                case 5:
                    c.setAuthor("Jen");
                    c.setVotes("123");
                    c.setImageUrl("http://images.fanpop.com/images/image_uploads/so-freakin-cute--cats-248624_500_375.jpg");
                    break;
            }
            result.add(c);
        }
        return result;*/
        return null;
    }

}