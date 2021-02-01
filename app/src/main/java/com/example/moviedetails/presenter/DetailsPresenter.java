package com.example.moviedetails.presenter;

import android.content.Context;

import com.example.moviedetails.interfaces.DetailsView;
import com.example.moviedetails.interfaces.ListingView;
import com.example.moviedetails.model.details_model.DetailsResponse;
import com.example.moviedetails.model.list_model.ListingResponse;
import com.example.moviedetails.network.BaseRetrofitHandler;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsPresenter {
    private final String TAG = DetailsPresenter.class.getSimpleName();
    private DetailsView detailsView;
    private Context mContext;

    /**
     * ListingPresenter  Constructor
     *
     * @param mContext
     * @param detailsView
     */
    public DetailsPresenter(Context mContext, DetailsView detailsView) {
        this.detailsView = detailsView;
        this.mContext = mContext;
    }

    /**
     * method to call get movies details data api
     *
     * @param
     */
    public void getData(String id, String apiKey) {
        Observable<DetailsResponse> observable = BaseRetrofitHandler.getInstance()
                .apiService.getMovieDetails(id, apiKey);
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(DetailsResponse -> DetailsResponse).subscribe(new Observer<DetailsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(DetailsResponse response) {
                detailsView.onDetailsSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                detailsView.onFailed(e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
