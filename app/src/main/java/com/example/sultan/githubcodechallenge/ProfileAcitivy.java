package com.example.sultan.githubcodechallenge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sultan.githubcodechallenge.app.AppController;
import com.example.sultan.githubcodechallenge.service_layer.GithubService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProfileAcitivy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_acitivy);


        final ImageView ivProfileImage;
        final TextView tvUsername;
        final TextView tvName;
        final TextView tvFollowers;
        final TextView tvFollowing;
        final TextView tvRepos;
        final TextView tvLocation;
        final TextView tvEmail;

        ivProfileImage = (ImageView) findViewById(R.id.ivProfilePicture);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvName = (TextView) findViewById(R.id.tvName);
        tvFollowers = (TextView) findViewById(R.id.tvFollowers);
        tvFollowing = (TextView) findViewById(R.id.tvFollowing);
        tvRepos = (TextView) findViewById(R.id.tvRepos);
        tvLocation = (TextView) findViewById(R.id.tvLocaiton);
        tvEmail = (TextView) findViewById(R.id.tvEmail);




        Intent myIntent = getIntent(); // gets the previously created intent
        String profileUrl = myIntent.getStringExtra("profileUrl");
        Log.d("asdf",profileUrl);


        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                profileUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("volley", response.toString());

                        JsonElement jelement = new JsonParser().parse(response.toString());
                        JsonObject object = jelement.getAsJsonObject();

                        tvUsername.setText(object.get("login").toString());
                        tvName.setText(object.get("name").toString());
                        tvFollowers.setText(object.get("followers").toString());
                        tvFollowing.setText(object.get("following").toString());
                        tvRepos.setText(object.get("public_repos").toString());
                        tvLocation.setText(object.get("location").toString());
                        tvEmail.setText(object.get("email").toString());

                        Picasso.with(getApplicationContext()).load(object.get("avatar_url").toString().replace("\"","")).into(ivProfileImage);


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
            }
        });

// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}
