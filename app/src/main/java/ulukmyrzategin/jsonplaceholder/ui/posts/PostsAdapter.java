package ulukmyrzategin.jsonplaceholder.ui.posts;

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
import ulukmyrzategin.jsonplaceholder.data.model.PostsModel;

/**
 * Created by $TheSusanin on 18.08.2018 15:36.
 */
public class PostsAdapter extends ArrayAdapter {
    private Context mContext;

    public PostsAdapter(@NonNull Context context, ArrayList<PostsModel> postsModels) {
        super(context, 0, postsModels);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.itmes_posts_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        PostsModel model = (PostsModel) getItem(position);
        if (model != null) {
            holder.tvText.setText(String.valueOf(model.getUserId()));
            holder.tvPostsText.setText(model.getTitle());
        }
        return convertView;
    }

    private class ViewHolder{
        private TextView tvText, tvPostsText;

        private ViewHolder(View view) {
            tvText = view.findViewById(R.id.tvText);
            tvPostsText = view.findViewById(R.id.tvPostsText);
        }
    }
}
