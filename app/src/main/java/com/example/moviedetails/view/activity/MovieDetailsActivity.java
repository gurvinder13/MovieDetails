package com.example.moviedetails.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviedetails.R;
import com.example.moviedetails.databinding.ActivityMovieDetailsBinding;
import com.example.moviedetails.databinding.ActivityMovieListingBinding;
import com.example.moviedetails.interfaces.CastCrewView;
import com.example.moviedetails.interfaces.DetailsView;
import com.example.moviedetails.interfaces.ListingView;
import com.example.moviedetails.model.cast_crew_model.CastCrewResponse;
import com.example.moviedetails.model.details_model.DetailsResponse;
import com.example.moviedetails.model.list_model.ListingResponse;
import com.example.moviedetails.presenter.CastCrewPresenter;
import com.example.moviedetails.presenter.DetailsPresenter;
import com.example.moviedetails.presenter.SimilarPresenter;
import com.example.moviedetails.view.adapters.CastAdapter;
import com.example.moviedetails.view.adapters.CrewAdapter;
import com.example.moviedetails.view.adapters.ListingAdapter;
import com.example.moviedetails.view.adapters.SimilarAdapter;

import static com.example.moviedetails.utils.Config.API_KEY;
import static com.example.moviedetails.utils.Config.FOSTER_PATH;

public class MovieDetailsActivity extends AppCompatActivity implements DetailsView, CastCrewView, ListingView {
    private ActivityMovieDetailsBinding binding;
    private String movieId;
    private DetailsPresenter detailsPresenter;
    private CastCrewPresenter castCrewPresenter;
    private SimilarPresenter similarPresenter;
    private LinearLayoutManager mCastManager;
    private LinearLayoutManager mCrewManager;
    private LinearLayoutManager mSimilarManager;
    private CastAdapter castAdapter;
    private CrewAdapter crewAdapter;
    private SimilarAdapter similarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movie_id");
        detailsPresenter = new DetailsPresenter(this, this);
        castCrewPresenter = new CastCrewPresenter(this, this);
        similarPresenter = new SimilarPresenter(this, this);
        mCastManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCrewManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mSimilarManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        castCrewPresenter.getData(movieId, API_KEY);
        similarPresenter.getSimilarData(movieId, API_KEY);
        detailsPresenter.getData(movieId, API_KEY);

    }

    @Override
    public void onCastSuccess(CastCrewResponse response) {
        castAdapter = new CastAdapter(response.getCast(), this);
        binding.rvCast.setLayoutManager(mCastManager);
        binding.rvCast.setAdapter(castAdapter);
        castAdapter.notifyDataSetChanged();
        crewAdapter = new CrewAdapter(response.getCrew(), this);
        binding.rvCrew.setLayoutManager(mCrewManager);
        binding.rvCrew.setAdapter(crewAdapter);
        crewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCastFailed(String error) {

    }

    @Override
    public void onDetailsSuccess(DetailsResponse response) {
        binding.tvTitle.setText(String.valueOf(response.getTitle()));
        binding.tvReleaseDate.setText("Release Date :"+String.valueOf(response.getReleaseDate()));
        binding.tvLang.setText("Langauge :"+String.valueOf(response.getOriginalLanguage()));
        binding.tvGenr.setText(String.valueOf(response.getOverview()));
        String path = FOSTER_PATH + response.getBackdropPath();
        Glide.with(this).load(path).apply(new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)).into(binding.imgViewCover);

    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onMoviesSuccess(ListingResponse response) {
        similarAdapter = new SimilarAdapter(response.getResults(), this);
        binding.rvSimilar.setLayoutManager(mSimilarManager);
        binding.rvSimilar.setAdapter(similarAdapter);
        similarAdapter.notifyDataSetChanged();
    }

    @Override
    public void onMoviesFailed(String error) {

    }
}