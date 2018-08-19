package ulukmyrzategin.jsonplaceholder.ui.albums;

import java.util.ArrayList;

import ulukmyrzategin.jsonplaceholder.data.model.AlbumsModel;
import ulukmyrzategin.jsonplaceholder.ui.IProgress;
import ulukmyrzategin.jsonplaceholder.ui.Lifecycle;

/**
 * Created by $TheSusanin on 16.08.2018 16:34.
 */
public interface AlbumsContract {

    interface View extends IProgress {
        void onSuccess(ArrayList<AlbumsModel> models);

        void  randomPhoto(String str);

        void onFailture(String msg);
    }

    interface Presenter extends Lifecycle<View> {
        void loadAlbums();
    }
}
