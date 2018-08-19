package ulukmyrzategin.jsonplaceholder.ui.posts.comments;

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
import ulukmyrzategin.jsonplaceholder.data.model.CommentsModel;

/**
 * Created by $TheSusanin on 18.08.2018 16:29.
 */
public class CommentsAdapter extends ArrayAdapter {
    private ArrayList<CommentsModel> mCommentsModels;
    private Context mContext;

    public CommentsAdapter(@NonNull Context context, ArrayList<CommentsModel> commentsModels) {
        super(context,0, commentsModels);
        this.mCommentsModels = commentsModels;
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comments_listview, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CommentsModel model = (CommentsModel) getItem(position);
        if (model != null) {
            holder.tvName.setText(model.getName());
            holder.tvEmail.setText(model.getEmail());
            holder.tvBody.setText(model.getBody());
            holder.tvUserID.setText(String.valueOf(model.getPostId()));
            holder.tvId.setText(String.valueOf(model.getId()));
        }

        return convertView;
    }

    private class ViewHolder{
        private TextView tvName, tvBody, tvEmail, tvUserID, tvId;

        private ViewHolder(View view) {
            tvBody = view.findViewById(R.id.tvBody);
            tvName = view.findViewById(R.id.tvName);
            tvEmail = view.findViewById(R.id.tvEmail);
            tvUserID = view.findViewById(R.id.tvUserID);
            tvId = view.findViewById(R.id.tvId);

        }
    }
}
