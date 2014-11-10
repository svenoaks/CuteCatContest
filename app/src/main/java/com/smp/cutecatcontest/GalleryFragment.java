package com.smp.cutecatcontest;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class GalleryFragment extends Fragment {

    private static final String ARG_POSITION = "position";

    private int position;

    @InjectView(R.id.recycler_view) RecyclerView mRecyclerView;

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

        // specify an adapter (see also next example)
       mAdapter = new GalleryAdapter(createList(30), this.getActivity());
       mRecyclerView.setAdapter(mAdapter);
       return view;
    }
    private void calculateSpans()
    {
        DisplayMetrics displayMetrics = GalleryFragment.this.getActivity().getResources().getDisplayMetrics();

        float dpHeight = displayMetrics.heightPixels / displayMetrics.density;
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;

        mLayoutManager.setSpanCount((int) dpWidth/CARD_WIDTH_MIN);
    }
    private List<GalleryAdapter.GalleryData> createList(int size) {

        List<GalleryAdapter.GalleryData> result = new ArrayList<GalleryAdapter.GalleryData>();
        for (int i=1; i <= size; i++) {
            GalleryAdapter.GalleryData ci = new GalleryAdapter.GalleryData();
            ci.author = GalleryAdapter.GalleryData.AUTHOR_PREFIX + i;
            ci.votes = GalleryAdapter.GalleryData.VOTES_PREFIX + i;

            result.add(ci);

        }

        return result;
    }

}