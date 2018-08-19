package ulukmyrzategin.jsonplaceholder.ui.albums;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.StartApplication;
import ulukmyrzategin.jsonplaceholder.data.model.AlbumsModel;
import ulukmyrzategin.jsonplaceholder.ui.BaseFragment;
import ulukmyrzategin.jsonplaceholder.ui.albums.photos.PhotoActivity;

/**
 * Created by $TheSusanin on 16.08.2018 17:20.
 */
public class AlbumsFragment extends BaseFragment implements AlbumsContract.View, AdapterView.OnItemClickListener {
    private ListView mListView;
    private LinearLayout mLinearFragment;
    private AlbumsContract.Presenter mPresenter;
    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getContext() != null)
            mPresenter = new AlbumsPresenter(StartApplication.getApp(getContext()).getService());
        mPresenter.bind(this);
        mPresenter.loadAlbums();

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);
        mListView = view.findViewById(R.id.lvAlbumsPostsComments);
        mLinearFragment = view.findViewById(R.id.linearFragment);
        mToolbar = view.findViewById(R.id.toolbar);
        mToolbar.setVisibility(View.GONE);
        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        startActivity(new Intent(getContext(), PhotoActivity.class).putExtra("id", ((AlbumsModel) adapterView.getItemAtPosition(i)).getUserId()));
    }

    @Override
    public void onSuccess(ArrayList<AlbumsModel> models) {
        if (getContext() != null)
            mListView.setAdapter(new AlbumsAdapter(getContext(), models));

    }

    @Override
    public void randomPhoto(String str) {
        Glide.with(getContext())
                .load(str)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                            mLinearFragment.setBackground(resource);
                    }
                });

    }

    @Override
    public void onFailture(String msg) {
        Toasty.error(getContext(), msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unbind();
    }

    @Override
    public void showLoadingIndicator() {
        showProgressBar();

    }

    @Override
    public void hideLoadingIndicator() {
        dismissProgressBar();

    }
}
