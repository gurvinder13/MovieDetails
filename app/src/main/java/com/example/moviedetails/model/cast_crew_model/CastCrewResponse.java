package com.example.moviedetails.model.cast_crew_model;

import com.example.moviedetails.model.list_model.ListingResults;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastCrewResponse {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("cast")
    @Expose
    private List<CastData> cast ;
    @SerializedName("crew")
    @Expose
    private List<CrewData> crew ;

    public int getId() {
        return id;
    }

    public List<CastData> getCast() {
        return cast;
    }

    public List<CrewData> getCrew() {
        return crew;
    }
}
