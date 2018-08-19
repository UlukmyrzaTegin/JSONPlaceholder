package ulukmyrzategin.jsonplaceholder.ui;

import android.app.ProgressDialog;
import android.support.v4.app.Fragment;

import ulukmyrzategin.jsonplaceholder.R;

/**
 * Created by $TheSusanin on 16.08.2018 16:04.
 */
public abstract class BaseFragment extends Fragment {
    private ProgressDialog mDialog;

    protected void showProgressBar() {
        if (mDialog == null) {
            mDialog = new ProgressDialog(getContext());
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
}
