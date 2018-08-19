package ulukmyrzategin.jsonplaceholder.ui.posts.comments;

import java.util.ArrayList;

import ulukmyrzategin.jsonplaceholder.data.model.CommentsModel;
import ulukmyrzategin.jsonplaceholder.ui.IProgress;
import ulukmyrzategin.jsonplaceholder.ui.Lifecycle;

/**
 * Created by $TheSusanin on 18.08.2018 16:24.
 */
public interface CommentsContract {

    interface View extends IProgress{

        void onSuccess(ArrayList<CommentsModel> commentsModels);

        void onError(String msg);
    }

    interface Presenter extends Lifecycle<View> {

        void loadComments(int postId);
    }
}
