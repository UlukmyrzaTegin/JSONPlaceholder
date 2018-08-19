package ulukmyrzategin.jsonplaceholder.ui.albums.fullscreen;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.ui.BaseActivity;

/**
 * Created by $TheSusanin on 16.08.2018 15:36.
 */
public class FullScreen extends BaseActivity {
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        mImageView = findViewById(R.id.ivFullScreen);

        initToolbar(true);
        Glide.with(this)
                .load(getIntent().getStringExtra("photo"))
                .into(mImageView);
    }
}
