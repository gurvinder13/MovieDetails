package com.example.moviedetails.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.moviedetails.R;
import com.example.moviedetails.databinding.ActivityMovieListingBinding;
import com.example.moviedetails.databinding.ActivitySearchBinding;
import com.example.moviedetails.interfaces.ListingView;
import com.example.moviedetails.model.list_model.ListingResponse;
import com.example.moviedetails.presenter.SearchPresenter;
import com.example.moviedetails.view.adapters.ListingAdapter;
import com.example.moviedetails.view.adapters.SearchAdapter;

import static com.example.moviedetails.utils.Config.API_KEY;

public class SearchActivity extends AppCompatActivity implements ListingView {
    private ActivitySearchBinding binding;
    private SearchPresenter searchPresenter;
    private String searchkey="";
    private LinearLayoutManager mManager;
    private SearchAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        searchPresenter=new SearchPresenter(this,this);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.etSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchkey = newText;
                searchPresenter.getSearchData(API_KEY,searchkey);


                return false;
            }
        });

    }

    @Override
    public void onMoviesSuccess(ListingResponse response) {
        adapter = new SearchAdapter(response.getResults(), this);
        binding.rvSearch.setLayoutManager(mManager);
        binding.rvSearch.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onMoviesFailed(String error) {

    }
}