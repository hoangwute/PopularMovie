package com.wuochoang.popularmovie.ui.detail.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.florent37.glidepalette.GlidePalette;
import com.olddog.common.ConvertUtils;
import com.squareup.picasso.Picasso;
import com.wuochoang.popularmovie.R;
import com.wuochoang.popularmovie.base.OnItemClickListener;
import com.wuochoang.popularmovie.common.utils.Utils;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.Trailer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CHUNGNHIM on 20/10/2017.
 */

public class MovieTrailersAdapter extends RecyclerView.Adapter<MovieTrailersAdapter.MyViewHolder> {
    private List<Trailer> trailerList;
    private Context context;
    private OnItemClickListener<Movie> onItemClickListener;

    public MovieTrailersAdapter(Activity context, List<Trailer> trailerList, @Nullable OnItemClickListener<Movie> onItemClickListener) {
        this.context = context;
        this.trailerList = trailerList;
        this.onItemClickListener = onItemClickListener;
    }

    public MovieTrailersAdapter(Activity context, List<Trailer> trailerList) {
        this.context = context;
        this.trailerList = trailerList;
    }

    public void updateData(List<Trailer> trailerList) {
        this.trailerList.addAll(trailerList);
        notifyDataSetChanged();
    }

    public List<Trailer> getTrailerList() {
        return trailerList;
    }

    @Override
    public  MovieTrailersAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trailer, parent, false);
        return new  MovieTrailersAdapter.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder( MovieTrailersAdapter.MyViewHolder holder, int position) {
        final Trailer trailer = trailerList.get(position);
        Picasso.with(context).load(trailer.getTrailerThumbnail()).centerCrop()
                .resize(ConvertUtils.px2dp(200), ConvertUtils.px2dp(200))
                .into(holder.imgMovieTrailerThumbnail);
    }

    @Override
    public int getItemCount() {
        return trailerList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgMovieTrailerThumbnail)
        ImageView imgMovieTrailerThumbnail;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
