package ulukmyrzategin.jsonplaceholder.ui.albums.photos;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ulukmyrzategin.jsonplaceholder.data.model.PhotosModel;
import ulukmyrzategin.jsonplaceholder.data.network.RetrofitService;

/**
 * Created by $TheSusanin on 17.08.2018 10:48.
 */
public class PhotoPresenter implements PhotosContract.Presenter {
    private PhotosContract.View mView;
    private RetrofitService mService;

    PhotoPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void loadPhoto(int id) {
        mView.showLoadingIndicator();
        mService.getPhotos(id)
                .enqueue(new Callback<ArrayList<PhotosModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<PhotosModel>> call, @NonNull Response<ArrayList<PhotosModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                                mView.OnSuccess(response.body());
                        } else {
                            if (isViewAttached())
                                mView.onError(response.message());
                        }
                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<PhotosModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.onError(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }
                    }
                });

    }

    @Override
    public void bind(PhotosContract.View view) {
        mView = view;

    }

    @Override
    public void unbind() {
        mView = null;

    }

    private boolean isViewAttached() {
        return mView != null;
    }
}
