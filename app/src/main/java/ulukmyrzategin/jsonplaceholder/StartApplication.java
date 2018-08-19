package ulukmyrzategin.jsonplaceholder;

import android.app.Application;
import android.content.Context;

import ulukmyrzategin.jsonplaceholder.data.network.NetworkBuilder;
import ulukmyrzategin.jsonplaceholder.data.network.RetrofitService;

/**
 * Created by $TheSusanin on 15.08.2018 18:18.
 */
public class StartApplication extends Application {

    private RetrofitService mService;

    @Override
    public void onCreate() {
        super.onCreate();
        mService = NetworkBuilder.initService();
    }

    public static StartApplication getApp(Context context) {
        return (StartApplication) context.getApplicationContext();
    }

    public RetrofitService getService() {
        return mService;
    }
}
