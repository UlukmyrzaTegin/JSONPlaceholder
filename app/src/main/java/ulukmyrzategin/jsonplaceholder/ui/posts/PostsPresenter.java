package ulukmyrzategin.jsonplaceholder.ui.posts;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ulukmyrzategin.jsonplaceholder.data.model.PostsModel;
import ulukmyrzategin.jsonplaceholder.data.network.RetrofitService;

/**
 * Created by $TheSusanin on 18.08.2018 15:59.
 */
public class PostsPresenter implements PostsContract.Presenter {
    private RetrofitService mService;
    private PostsContract.View mView;

    PostsPresenter(RetrofitService service) {
        mService = service;
    }

    @Override
    public void loadPosts() {
        mView.showLoadingIndicator();
        mService.getPosts()
                .enqueue(new Callback<ArrayList<PostsModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<PostsModel>> call,@NonNull Response<ArrayList<PostsModel>> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            if (isViewAttached())
                                mView.onSuccessP(randomList(response.body()));
                        } else {
                            if (isViewAttached())
                                mView.onError(response.message());
                        }
                        mView.hideLoadingIndicator();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<PostsModel>> call, @NonNull Throwable t) {
                        if (isViewAttached()) {
                            mView.onError(t.getLocalizedMessage());
                            mView.hideLoadingIndicator();
                        }

                    }
                });

    }

    private ArrayList<PostsModel> randomList (ArrayList<PostsModel> postsModels) {
        ArrayList<PostsModel> ranPostList = new ArrayList<>();
        Random random = new Random();

        int Low = 15;
        int High = 20;
        int result = random.nextInt(High - Low) + Low;
        for (int i = 0; i < result; i++) {
            ranPostList.add(postsModels.get(random.nextInt(postsModels.size())));
            Log.d("PostsPresenter", "randomList: " + ranPostList.get(i).getTitle());
        }
        return ranPostList;
    }

    @Override
    public void bind(PostsContract.View view) {
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
