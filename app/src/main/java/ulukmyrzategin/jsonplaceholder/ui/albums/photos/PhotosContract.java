package ulukmyrzategin.jsonplaceholder.ui.albums.photos;

import java.util.ArrayList;

import ulukmyrzategin.jsonplaceholder.data.model.PhotosModel;
import ulukmyrzategin.jsonplaceholder.ui.IProgress;
import ulukmyrzategin.jsonplaceholder.ui.Lifecycle;

/**
 * Created by $TheSusanin on 16.08.2018 19:48.
 */
public interface PhotosContract {
    interface View extends IProgress {

        void OnSuccess(ArrayList<PhotosModel> photosModels);

        void onError(String msg);
    }

    interface Presenter extends Lifecycle<View> {
        void loadPhoto(int id);
    }
}
