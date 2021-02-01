package com.example.moviedetails.interfaces;

import com.example.moviedetails.model.cast_crew_model.CastCrewResponse;

public interface CastCrewView {
    void onCastSuccess(CastCrewResponse response);

    void onCastFailed(String error);
}
