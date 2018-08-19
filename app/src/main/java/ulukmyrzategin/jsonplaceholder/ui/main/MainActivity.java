package ulukmyrzategin.jsonplaceholder.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.ui.BaseActivity;
import ulukmyrzategin.jsonplaceholder.ui.albums.AlbumsFragment;
import ulukmyrzategin.jsonplaceholder.ui.posts.PostsFragment;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);

        initToolbar(false);
        switchFragment(new AlbumsFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_albums:
                switchFragment(new AlbumsFragment());
                return true;
            case R.id.navigation_posts:
                switchFragment(new PostsFragment());
                return true;
        }

        return false;
    }

    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, fragment.getClass().getCanonicalName())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
