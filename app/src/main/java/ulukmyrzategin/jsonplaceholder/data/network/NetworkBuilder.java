package ulukmyrzategin.jsonplaceholder.data.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ulukmyrzategin.jsonplaceholder.config.AppConstants;

import static retrofit2.converter.gson.GsonConverterFactory.*;

/**
 * Created by $TheSusanin on 15.08.2018 18:14.
 */
public class NetworkBuilder {

    private static RetrofitService sService = null;

    public static RetrofitService initService() {
        if (sService == null) {
            return new Retrofit.Builder()
                    .baseUrl(AppConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(RetrofitService.class);
        }
        return sService;
    }
}
