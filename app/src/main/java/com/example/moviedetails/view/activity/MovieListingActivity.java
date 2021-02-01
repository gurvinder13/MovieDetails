package com.example.moviedetails.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moviedetails.databinding.ActivityMovieListingBinding;
import com.example.moviedetails.interfaces.ListingView;
import com.example.moviedetails.model.list_model.ListingResponse;
import com.example.moviedetails.presenter.ListingPresenter;
import com.example.moviedetails.view.adapters.ListingAdapter;

import static com.example.moviedetails.utils.Config.API_KEY;

public class MovieListingActivity extends AppCompatActivity implements ListingView {
    private ActivityMovieListingBinding binding;
    private ListingPresenter presenter;
    private LinearLayoutManager mManager;
    private ListingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieListingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ListingPresenter(this, this);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        presenter.getData(API_KEY, "", "1");
        binding.view.ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onMoviesSuccess(ListingResponse response) {
        adapter = new ListingAdapter(response.getResults(), this);
        binding.rvList.setLayoutManager(mManager);
        binding.rvList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMoviesFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}