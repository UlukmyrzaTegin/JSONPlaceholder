package ulukmyrzategin.jsonplaceholder.ui.posts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.StartApplication;
import ulukmyrzategin.jsonplaceholder.data.model.PostsModel;
import ulukmyrzategin.jsonplaceholder.ui.BaseFragment;
import ulukmyrzategin.jsonplaceholder.ui.posts.comments.CommentsActivity;

/**
 * Created by $TheSusanin on 18.08.2018 15:53.
 */
public class PostsFragment extends BaseFragment implements PostsContract.View, AdapterView.OnItemClickListener {
    private ListView mListView;
    private PostsContract.Presenter mPresenter;
    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContext() != null)
            mPresenter = new PostsPresenter(StartApplication.getApp(getContext()).getService());
        mPresenter.bind(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewPosts = inflater.inflate(R.layout.fragment_listview, container, false);
        mListView = viewPosts.findViewById(R.id.lvAlbumsPostsComments);
        mToolbar = viewPosts.findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.GONE);

        return viewPosts;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setOnItemClickListener(this);
        mPresenter.loadPosts();
    }

    @Override
    public void onSuccessP(ArrayList<PostsModel> postsModels) {
        if (getContext() != null)
            mListView.setAdapter(new PostsAdapter(getContext(), postsModels));

    }

    @Override
    public void onError(String msg) {
        Toasty.error(getContext(), msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();

    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        startActivity(new Intent(new Intent(getContext(), CommentsActivity.class).putExtra("postId", ((PostsModel) adapterView.getItemAtPosition(i)).getUserId())));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
