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

public class ListingPresenter {
    private final String TAG = ListingPresenter.class.getSimpleName();
    private ListingView listingView;
    private Context mContext;

    /**
     * ListingPresenter  Constructor
     *
     * @param mContext
     * @param listingView
     */
    public ListingPresenter(Context mContext, ListingView listingView) {
        this.listingView = listingView;
        this.mContext = mContext;
    }

    /**
     * method to call get movies list data api
     *
     * @param
     */
    public void getData(String apiKey, String lang, String page) {
        Observable<ListingResponse> observable = BaseRetrofitHandler.getInstance()
                .apiService.getMovieList(apiKey, lang, page);
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
