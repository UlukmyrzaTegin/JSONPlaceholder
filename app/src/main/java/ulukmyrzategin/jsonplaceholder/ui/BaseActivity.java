package ulukmyrzategin.jsonplaceholder.ui;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import ulukmyrzategin.jsonplaceholder.R;

/**
 * Created by $TheSusanin on 16.08.2018 15:37.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mDialog;
    private Toolbar mToolbar;


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }

    protected  void showProgressBar() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(getString(R.string.please_wait));
            mDialog.setCancelable(false);
            mDialog.show();
        }
    }

    protected void dismissProgressBar() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        mDialog = null;
    }

    protected void initToolbar(boolean flag) {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null && flag) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return  super.onOptionsItemSelected(item);
    }
}

