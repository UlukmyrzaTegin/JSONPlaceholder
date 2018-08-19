package ulukmyrzategin.jsonplaceholder.ui.albums;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ulukmyrzategin.jsonplaceholder.data.model.AlbumsModel;
import ulukmyrzategin.jsonplaceholder.data.model.PhotosModel;
import ulukmyrzategin.jsonplaceholder.data.network.RetrofitService;

/**
 * Created by $TheSusanin on 16.08.2018 17:23.
 */
public class AlbumsPresenter implements AlbumsContract.Presenter {
    private AlbumsContract.View mView;
    private RetrofitService mService;

    AlbumsPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void loadAlbums() {
        mView.showLoadingIndicator();
        mService.getAlbums()
                .enqueue(new Callback<ArrayList<AlbumsModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<AlbumsModel>> call, @NonNull Response<ArrayList<AlbumsModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isAttached()) {
                                mView.onSuccess(randomList(response.body()));
                                randomPhoto(response.body());
                            }
                        } else {
                            if (isAttached())
                                mView.onFailture(response.message());
                        }
                        mView.hideLoadingIndicator();

                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<AlbumsModel>> call, @NonNull Throwable t) {
                        if (isAttached()) {
                            mView.onFailture(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }
                    }
                });

    }


    private void randomPhoto(ArrayList<AlbumsModel> albumsModels) {
        final Random random = new Random();
        int albumId = albumsModels.get(random.nextInt(albumsModels.size())).getUserId();

        mService.getPhotos(albumId)
                .enqueue(new Callback<ArrayList<PhotosModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<PhotosModel>> call, @NonNull Response<ArrayList<PhotosModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isAttached())
                                mView.randomPhoto(response.body().get(random.nextInt(response.body().size())).getUrl());
                        }

                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<PhotosModel>> call, @NonNull Throwable t) {

                    }
                });
    }

    private ArrayList<AlbumsModel> randomList(ArrayList<AlbumsModel> albumsModels) {
        ArrayList<AlbumsModel> ranAlbumsList = new ArrayList<>();
        Random random = new Random();

        int Low = 10;
        int High = 15;
        int result = random.nextInt(High - Low) + Low;

        for (int i = 0; i < result; i++) {
            ranAlbumsList.add(albumsModels.get(random.nextInt(albumsModels.size())));
            Log.d("Presenter", "randomList: " + ranAlbumsList.get(i).getTitle());
        }

        return ranAlbumsList;
    }

    @Override
    public void bind(AlbumsContract.View view) {
        mView = view;
    }

    @Override
    public void unbind() {
        mView = null;
    }

    private boolean isAttached() {
        return mView != null;
    }
}
