package ulukmyrzategin.jsonplaceholder.ui.albums.photos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import es.dmoral.toasty.Toasty;
import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.StartApplication;
import ulukmyrzategin.jsonplaceholder.data.model.PhotosModel;
import ulukmyrzategin.jsonplaceholder.ui.BaseActivity;
import ulukmyrzategin.jsonplaceholder.ui.albums.fullscreen.FullScreen;

/**
 * Created by $TheSusanin on 16.08.2018 19:02.
 */
public class PhotoActivity extends BaseActivity implements AdapterView.OnItemClickListener, PhotosContract.View {

    private GridView mGridView;
    private PhotosContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        mGridView = findViewById(R.id.gvPhotos);
        mGridView.setOnItemClickListener(this);

        initToolbar(true);
        init();
    }

    private void init() {
        mPresenter = new PhotoPresenter(StartApplication.getApp(this).getService());
        mPresenter.bind(this);
        mPresenter.loadPhoto(getIntent().getIntExtra("id", 0));

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
      startActivity(new Intent(getApplicationContext(), FullScreen.class).putExtra("photo", ((PhotosModel) adapterView.getItemAtPosition(i)).getUrl()));
    }

    @Override
    public void OnSuccess(ArrayList<PhotosModel> photosModels) {
        mGridView.setAdapter(new PhotosAdapter(this, photosModels));

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
}
