package com.example.moviedetails.network;

import com.example.moviedetails.model.cast_crew_model.CastCrewResponse;
import com.example.moviedetails.model.details_model.DetailsResponse;
import com.example.moviedetails.model.list_model.ListingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * Movies listing  Api Call
     */
    @GET(ApiEndPoints.MOVIE_LIST)
    Observable<ListingResponse> getMovieList(@Query("api_key") String api_key,
                                             @Query("language") String language,
                                             @Query("page") String page);

    /**
     * Movie details Api Call
     */
    @GET(ApiEndPoints.MOVIE_DETAILS)
    Observable<DetailsResponse> getMovieDetails(@Path("movie_id") String movie_id,
                                                @Query("api_key") String api_key);


    /**
     * Similar Movies Api Call
     */
    @GET(ApiEndPoints.MOVIE_SIMILAR)
    Observable<ListingResponse> getSimilarMovie(@Path("movie_id") String movie_id,
                                                @Query("api_key") String api_key);


    /**
     * Movies Cast n Crew Api Call
     */
    @GET(ApiEndPoints.MOVIE_CAST_CREW)
    Observable<CastCrewResponse> getCastrMovie(@Path("movie_id") String movie_id,
                                               @Query("api_key") String api_key);


    /**
     * Movies Search  Api Call
     */
    @GET(ApiEndPoints.MOVIE_SEARCH)
    Observable<ListingResponse> getSearchData(@Query("api_key") String api_key,
                                              @Query("query") String query);

}