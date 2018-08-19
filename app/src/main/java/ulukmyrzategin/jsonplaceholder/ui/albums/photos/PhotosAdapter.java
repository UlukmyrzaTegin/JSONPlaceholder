package ulukmyrzategin.jsonplaceholder.ui.albums.photos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.data.model.PhotosModel;

/**
 * Created by $TheSusanin on 16.08.2018 19:37.
 */
public class PhotosAdapter extends ArrayAdapter {
    private Context mContext;

    public PhotosAdapter(@NonNull Context context, ArrayList<PhotosModel> photosModels) {
        super(context, 0, photosModels);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

      final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_photos_gridview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PhotosModel model = (PhotosModel) getItem(position);
        if (model != null)
            Glide.with(mContext)
            .load(model.getUrl())
            .into(holder.ivPhotos);
        holder.tvPhotos.setText(String.valueOf(model.getAlbumId()));

        return convertView;
    }

    private class ViewHolder{
     private TextView tvPhotos;
     private ImageView ivPhotos;

        private ViewHolder(View view) {
            tvPhotos = view.findViewById(R.id.tvPhotos);
            ivPhotos = view.findViewById(R.id.ivPhotos);
        }
    }
}
