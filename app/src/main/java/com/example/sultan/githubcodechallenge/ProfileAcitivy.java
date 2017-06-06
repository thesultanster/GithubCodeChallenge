package com.example.sultan.githubcodechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sultan.githubcodechallenge.data_layer.User;
import com.example.sultan.githubcodechallenge.service_layer.GithubService;
import com.example.sultan.githubcodechallenge.service_layer.GithubUserInterface;
import com.squareup.picasso.Picasso;

public class ProfileAcitivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acitivy);


        final ImageView ivProfileImage;
        final ImageView ivProfileBackground;

        final TextView tvUsername;
        final TextView tvName;
        final TextView tvFollowers;
        final TextView tvFollowing;
        final TextView tvRepos;
        final TextView tvLocation;
        final TextView tvEmail;

        ivProfileImage = (ImageView) findViewById(R.id.ivProfilePicture);
        ivProfileBackground = (ImageView) findViewById(R.id.ivProfileBackground);

        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvName = (TextView) findViewById(R.id.tvName);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvRepos = (TextView) findViewById(R.id.tvRepos);
        tvLocation = (TextView) findViewById(R.id.tvLocaiton);
        tvEmail = (TextView) findViewById(R.id.tvEmail);


        Intent myIntent = getIntent(); // gets the previously created intent
        String profileUrl = myIntent.getStringExtra("profileUrl");


        GithubService.getInstance().getUserDataFromGithub(profileUrl, new GithubUserInterface(){

            @Override
            public void onDataFetched(User user) {
                tvUsername.setText(user.getLogin());
                tvName.setText(user.getName());
                tvFollowers.setText(String.valueOf(user.getFollowers()));
                tvFollowing.setText(String.valueOf(user.getFollowing()));
                tvRepos.setText(String.valueOf(user.getPublic_repos()));
                tvLocation.setText("Location: " +user.getLocation());
                tvEmail.setText("Email: " + user.getEmail());

                Picasso.with(getApplicationContext()).load(user.getAvatar_url().replace("\"","")).into(ivProfileImage);
                Picasso.with(getApplicationContext()).load(user.getAvatar_url().replace("\"","")).into(ivProfileBackground);


            }
        });

        /*

        */


    }
}
