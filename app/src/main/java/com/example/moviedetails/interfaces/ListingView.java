package com.example.moviedetails.interfaces;

import com.example.moviedetails.model.list_model.ListingResponse;

public interface ListingView {
    void onMoviesSuccess(ListingResponse response);

    void onMoviesFailed(String error);
}
