package ulukmyrzategin.jsonplaceholder.ui.albums.fullscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.Placeholder;
import android.support.v7.app.AppCompatActivity;

import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.ui.main.MainActivity;

/**
 * Created by $TheSusanin on 19.08.2018 11:27.
 */
public class SplashScreenActivity extends AppCompatActivity {

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                SplashScreenActivity.this.finish();
            }
        };
        mHandler.postDelayed(mRunnable, 3000);
    }
}
