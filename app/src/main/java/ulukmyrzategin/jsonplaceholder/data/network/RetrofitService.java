package ulukmyrzategin.jsonplaceholder.data.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ulukmyrzategin.jsonplaceholder.config.AppConstants;
import ulukmyrzategin.jsonplaceholder.data.model.AlbumsModel;
import ulukmyrzategin.jsonplaceholder.data.model.CommentsModel;
import ulukmyrzategin.jsonplaceholder.data.model.PhotosModel;
import ulukmyrzategin.jsonplaceholder.data.model.PostsModel;

/**
 * Created by $TheSusanin on 15.08.2018 17:55.
 */
public interface RetrofitService {

    @GET(AppConstants.POSTS_ENDPOINT)
    Call<ArrayList<PostsModel>> getPosts();

    @GET(AppConstants.COMMENTS_ENDPOINT)
    Call<ArrayList<CommentsModel>> getComments (@Query("postId" ) int postId);

    @GET(AppConstants.ALBUMS_ENDPOINT)
    Call<ArrayList<AlbumsModel>> getAlbums();

    @GET(AppConstants.PHOTOS_ENDPOINT)
    Call<ArrayList<PhotosModel>> getPhotos (@Query("albumId") int albumId);

}
