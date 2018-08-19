package ulukmyrzategin.jsonplaceholder.ui.posts.comments;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ulukmyrzategin.jsonplaceholder.data.model.CommentsModel;
import ulukmyrzategin.jsonplaceholder.data.network.RetrofitService;

/**
 * Created by $TheSusanin on 18.08.2018 16:56.
 */
public class CommentsPresenter implements CommentsContract.Presenter {
    private RetrofitService mService;
    private CommentsContract.View mView;

    CommentsPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void loadComments(int postId) {
        mView.showLoadingIndicator();
        mService.getComments(postId)
                .enqueue(new Callback<ArrayList<CommentsModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<CommentsModel>> call, @NonNull Response<ArrayList<CommentsModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                                mView.onSuccess(response.body());
                        } else {
                            if (isViewAttached())
                                mView.onError(response.message());
                        }
                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<CommentsModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.onError(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }

                    }
                });

    }

    @Override
    public void bind(CommentsContract.View view) {
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
