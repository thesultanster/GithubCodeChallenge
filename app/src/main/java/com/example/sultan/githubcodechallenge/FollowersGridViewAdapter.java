package com.example.sultan.githubcodechallenge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sultan.githubcodechallenge.data_layer.Follower;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Sultan on 6/2/2017.
 */

public class FollowersGridViewAdapter extends BaseAdapter {

    private final Context mContext;
    private List<Follower> data;

    // Constructor to construct da adaptaaaaa
    public FollowersGridViewAdapter(Context context, List<Follower> data) {
        this.mContext = context;
        this.data = data;
    }

    void clearAdapter(){
        data.clear();
        notifyDataSetChanged();
    }


    void addCell(Follower follower){
        if(data.contains(follower)){
            return;
        }

        data.add(follower);
        notifyDataSetChanged();
    }

    // returns count of grid children
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // Get a child by index
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }


    // Bind data and return view
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //  Get book object
        final Follower follower = data.get(position);

        // Get my cell view to bind data to
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.cell_follower, null);
        }

        // Inflate View objects needed for the cell
        final CircleImageView ivProfilePicture = (CircleImageView) convertView.findViewById(R.id.ivProfilePicture);
        final TextView tvFollowerName = (TextView) convertView.findViewById(R.id.tvFollowerName);

        // BIIIIND THE DAATAAAAA

        Picasso.with(mContext).load(follower.getAvatar_url()).into(ivProfilePicture);
        tvFollowerName.setText(follower.getLogin());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProfileAcitivy.class);
                intent.putExtra("profileUrl", follower.getUrl());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return convertView;
    }

}
