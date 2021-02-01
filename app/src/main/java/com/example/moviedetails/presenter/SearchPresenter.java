package com.example.moviedetails.presenter;

import android.content.Context;

import com.example.moviedetails.interfaces.ListingView;
import com.example.moviedetails.model.list_model.ListingResponse;
import com.example.moviedetails.network.BaseRetrofitHandler;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPresenter {
    private final String TAG = SearchPresenter.class.getSimpleName();
    private ListingView listingView;
    private Context mContext;

    /**
     * SearchPresenter  Constructor
     *
     * @param mContext
     * @param listingView
     */
    public SearchPresenter(Context mContext, ListingView listingView) {
        this.listingView = listingView;
        this.mContext = mContext;
    }

    /**
     * method to call get Search movies list data api
     *
     * @param
     */
    public void getSearchData(String apiKey,String query) {
        Observable<ListingResponse> observable = BaseRetrofitHandler.getInstance()
                .apiService.getSearchData(apiKey,query);
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(ListingResponse -> ListingResponse).subscribe(new Observer<ListingResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ListingResponse response) {
                listingView.onMoviesSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                listingView.onMoviesFailed(e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
