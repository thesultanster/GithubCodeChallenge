package com.example.sultan.githubcodechallenge.service_layer;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sultan.githubcodechallenge.data_layer.Follower;
import com.example.sultan.githubcodechallenge.data_layer.User;
import com.example.sultan.githubcodechallenge.app.AppController;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by Sultan on 6/2/2017.
 */

public class GithubService {


    private static GithubService ourInstance = new GithubService();

    public static GithubService getInstance() {
        return ourInstance;
    }

    private GithubService() {

    }

    public void getUserDataFromGithub(String profileUrl, final GithubUserInterface githubUserInterface){

        String tag_json_obj = "json_obj_req";
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                profileUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("volley", response.toString());

                        JsonElement jelement = new JsonParser().parse(response.toString());
                        JsonObject object = jelement.getAsJsonObject();

                        Gson gson = new Gson();
                        User user = gson.fromJson(object.toString(), User.class);

                        githubUserInterface.onDataFetched(user);


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

    public void getFollowersFromGitHub(String usernameToSearch, final GithubFollowerInterface githubServiceInterface){

        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";
        String url = "https://api.github.com/users/"+usernameToSearch+"/followers";
        JsonArrayRequest req = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("volley", response.toString());

                        JsonElement jelement = new JsonParser().parse(response.toString());
                        JsonArray jarray = jelement.getAsJsonArray();

                        for( JsonElement followerEle : jarray){
                            JsonObject followerJson = followerEle.getAsJsonObject();


                            String profileUrl = "https://api.github.com/users/" + followerJson.get("login").toString();
                            profileUrl = profileUrl.replace("\"", "");
                            String url = followerJson.get("avatar_url").toString();
                            url = url.replace("\"", "");
                            String name = followerJson.get("login").toString();
                            name = name.replace("\"", "");

                            Gson gson = new Gson();
                            Follower follower = gson.fromJson(followerJson.toString(), Follower.class);
                            githubServiceInterface.onFollowerFetched(follower);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("volley", "Error: " + error.getMessage());
            }
        });
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req, tag_json_obj);

    }

}
