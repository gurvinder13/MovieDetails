package com.example.moviedetails.interfaces;

import com.example.moviedetails.model.details_model.DetailsResponse;

public interface DetailsView {
    void onDetailsSuccess(DetailsResponse response);

    void onFailed(String error);
}
