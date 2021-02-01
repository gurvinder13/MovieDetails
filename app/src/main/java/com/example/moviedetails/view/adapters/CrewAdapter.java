package com.example.moviedetails.view.adapters;

import android.content.Context;
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
import com.example.moviedetails.model.cast_crew_model.CastData;
import com.example.moviedetails.model.cast_crew_model.CrewData;

import java.util.List;

import static com.example.moviedetails.utils.Config.FOSTER_PATH;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.ViewHolder> {
    private List<CrewData> listingResults;
    private Context context;

    public CrewAdapter(List<CrewData> listingResults, Context context) {
        this.listingResults = listingResults;
        this.context = context;
    }

    @NonNull
    @Override
    public CrewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cast_items_layout, parent, false);
        CrewAdapter.ViewHolder myViewHolder = new CrewAdapter.ViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CrewAdapter.ViewHolder holder, int position) {
        CrewData list = listingResults.get(position);
        holder.tvTitle.setText(list.getName());
        String path = FOSTER_PATH + listingResults.get(position).getProfilePath();
        Glide.with(context).load(path).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)).into(holder.ivCoverImage);

    }

    @Override
    public int getItemCount() {
        return listingResults.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView ivCoverImage;


        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_cast_name);
            ivCoverImage = itemView.findViewById(R.id.imgViewCover);

        }
    }
}