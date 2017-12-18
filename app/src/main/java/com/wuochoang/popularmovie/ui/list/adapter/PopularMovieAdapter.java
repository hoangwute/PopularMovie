package com.wuochoang.popularmovie.ui.list.adapter;

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
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;
import com.squareup.picasso.Picasso;
import com.wuochoang.popularmovie.R;
import com.wuochoang.popularmovie.base.OnItemClickListener;
import com.wuochoang.popularmovie.common.utils.Utils;
import com.wuochoang.popularmovie.model.Genre;
import com.wuochoang.popularmovie.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by CHUNGNHIM on 20/10/2017.
 */

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.MyViewHolder> {
    private List<Movie> movieList;
    private Context context;
    private OnItemClickListener<Movie> onItemClickListener;

    public PopularMovieAdapter(Activity context, List<Movie> movieList, @Nullable OnItemClickListener<Movie> onItemClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.onItemClickListener = onItemClickListener;
    }

    public PopularMovieAdapter(Activity context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
        this.onItemClickListener = onItemClickListener;
    }

    public void updateData(List<Movie> movieList) {
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    @Override
    public PopularMovieAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new PopularMovieAdapter.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(PopularMovieAdapter.MyViewHolder holder, int position) {
        final Movie movie = movieList.get(position);
        Glide.with(context).load(movie.getPosterPath()).listener(GlidePalette.with(movie.getPosterPath())
                .use(GlidePalette.Profile.MUTED)
                .intoBackground(holder.listBackground, GlidePalette.Swatch.RGB)
                .intoTextColor(holder.txtMovieTitle, GlidePalette.Swatch.BODY_TEXT_COLOR)
                .intoTextColor(holder.txtMovieGenres, GlidePalette.Swatch.TITLE_TEXT_COLOR)
                .crossfade(true)).into(holder.imgMovieBanner);
        holder.txtMovieTitle.setText(movie.getTitle());
        for(int i = 0 ; i < movie.getGenreIds().size(); i++) {
            if(i != movie.getGenreIds().size() - 1) {
                holder.txtMovieGenres.append(Utils.mapGenreIdToName(movie.getGenreIds().get(i)) + ", ");
            }
            else
                holder.txtMovieGenres.append(Utils.mapGenreIdToName(movie.getGenreIds().get(i)));
        }
        holder.itemView.setTag(holder.getAdapterPosition());
        holder.itemView.setOnClickListener(view -> {
            int position1 = (int) view.getTag();
            if (position1 != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(movieList.get(position1));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgMovieBanner)
        ImageView imgMovieBanner;
        @BindView(R.id.txtMovieTitle)
        TextView txtMovieTitle;
        @BindView(R.id.txtMovieGenres)
        TextView txtMovieGenres;
        @BindView(R.id.listBackground)
        LinearLayout listBackground;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
