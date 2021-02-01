package com.example.moviedetails.presenter;

import android.content.Context;

import com.example.moviedetails.interfaces.CastCrewView;
import com.example.moviedetails.interfaces.DetailsView;
import com.example.moviedetails.model.cast_crew_model.CastCrewResponse;
import com.example.moviedetails.model.details_model.DetailsResponse;
import com.example.moviedetails.network.BaseRetrofitHandler;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CastCrewPresenter {
    private final String TAG = CastCrewPresenter.class.getSimpleName();
    private CastCrewView castCrewView;
    private Context mContext;

    /**
     * ListingPresenter  Constructor
     *
     * @param mContext
     * @param castCrewView
     */
    public CastCrewPresenter(Context mContext, CastCrewView castCrewView) {
        this.castCrewView = castCrewView;
        this.mContext = mContext;
    }

    /**
     * method to call get movies cast crew details data api
     *
     * @param
     */
    public void getData(String id, String apiKey) {
        Observable<CastCrewResponse> observable = BaseRetrofitHandler.getInstance()
                .apiService.getCastrMovie(id, apiKey);
        observable.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .map(CastCrewResponse -> CastCrewResponse).subscribe(new Observer<CastCrewResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(CastCrewResponse response) {
                castCrewView.onCastSuccess(response);
            }

            @Override
            public void onError(Throwable e) {
                castCrewView.onCastFailed(e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
