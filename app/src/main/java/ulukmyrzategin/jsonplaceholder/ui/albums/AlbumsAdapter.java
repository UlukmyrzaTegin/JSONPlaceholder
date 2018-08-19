package ulukmyrzategin.jsonplaceholder.ui.albums;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ulukmyrzategin.jsonplaceholder.R;
import ulukmyrzategin.jsonplaceholder.data.model.AlbumsModel;

/**
 * Created by $TheSusanin on 16.08.2018 16:43.
 */
public class AlbumsAdapter extends ArrayAdapter {
    private Context mContext;

     AlbumsAdapter(@NonNull Context context, ArrayList<AlbumsModel> albumsModels) {
        super(context, 0, albumsModels);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.items_albums_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        AlbumsModel model = (AlbumsModel) getItem(position);
        if (model != null)
            holder.tvText.setText(model.getTitle());
        holder.tvAlbumsText.setText(String.valueOf(model.getUserId()));
        return  convertView;
    }

    private class ViewHolder{
        private TextView tvText, tvAlbumsText;

        private ViewHolder(View view) {
            tvText = view.findViewById(R.id.tvText);
            tvAlbumsText = view.findViewById(R.id.tvAlbumsText);
        }
    }
}
