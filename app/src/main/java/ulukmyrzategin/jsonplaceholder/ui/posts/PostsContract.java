package ulukmyrzategin.jsonplaceholder.ui.posts;

import java.util.ArrayList;

import ulukmyrzategin.jsonplaceholder.data.model.PostsModel;
import ulukmyrzategin.jsonplaceholder.ui.IProgress;
import ulukmyrzategin.jsonplaceholder.ui.Lifecycle;

/**
 * Created by $TheSusanin on 18.08.2018 15:48.
 */
public interface PostsContract {

    interface View extends IProgress {

        void onSuccessP(ArrayList<PostsModel> postsModels);
        void onError(String msg);
    }

    interface Presenter extends Lifecycle<View>{

        void loadPosts();
    }
}
