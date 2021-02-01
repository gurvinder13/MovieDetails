package com.example.moviedetails.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviedetails.R;
import com.example.moviedetails.model.list_model.ListingResults;
import com.example.moviedetails.view.activity.MovieDetailsActivity;

import java.util.List;

import static com.example.moviedetails.utils.Config.FOSTER_PATH;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ViewHolder> {
    private List<ListingResults> listingResults;
    private Context context;

    public ListingAdapter(List<ListingResults> listingResults, Context context) {
        this.listingResults = listingResults;
        this.context = context;
    }

    @NonNull
    @Override
    public ListingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_layout, parent, false);
        ListingAdapter.ViewHolder myViewHolder = new ListingAdapter.ViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListingAdapter.ViewHolder holder, int position) {
        ListingResults list = listingResults.get(position);
        holder.tvTitle.setText(list.getTitle());
        holder.tvRelease.setText(list.getReleaseDate());
        holder.tvOther.setText(list.getOverview());
        String path = FOSTER_PATH + listingResults.get(position).getPosterPath();
        Glide.with(context).load(path).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)).into(holder.ivCoverImage);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MovieDetailsActivity.class);
            intent.putExtra("movie_id", String.valueOf(list.getId()));
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return listingResults.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvRelease, tvOther;
        private ImageView ivCoverImage;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tvReleaseDate);
            tvOther = itemView.findViewById(R.id.tvOther);
            ivCoverImage = itemView.findViewById(R.id.imgViewCover);

        }
    }
}