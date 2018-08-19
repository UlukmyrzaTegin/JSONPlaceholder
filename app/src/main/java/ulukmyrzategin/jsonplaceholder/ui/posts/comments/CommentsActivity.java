package ulukmyrzategin.jsonplaceholder.ui.posts.comments;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import es.dmoral.toasty.Toasty;
import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.StartApplication;
import ulukmyrzategin.jsonplaceholder.data.model.CommentsModel;
import ulukmyrzategin.jsonplaceholder.ui.BaseActivity;

/**
 * Created by $TheSusanin on 18.08.2018 16:09.
 */
public class CommentsActivity extends BaseActivity implements CommentsContract.View {
    private CommentsContract.Presenter mPresenter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_listview);
        mListView = findViewById(R.id.lvAlbumsPostsComments);

        initToolbar(true);
        init();
    }

    private void init() {
        mPresenter = new CommentsPresenter(StartApplication.getApp(this).getService());
        mPresenter.bind(this);
        mPresenter.loadComments(getIntent().getIntExtra("postId", 0));
    }

    @Override
    public void onSuccess(ArrayList<CommentsModel> commentsModels) {
        mListView.setAdapter(new CommentsAdapter(this, commentsModels));

    }

    @Override
    public void onError(String msg) {
        Toasty.error(this, msg, Toast.LENGTH_LONG).show();

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
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }
}
