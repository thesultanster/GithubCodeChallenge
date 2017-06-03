package com.example.sultan.githubcodechallenge.service_layer;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sultan.githubcodechallenge.Follower;
import com.example.sultan.githubcodechallenge.app.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Sultan on 6/2/2017.
 */

public class GithubService {

    ArrayList<Follower> data;

    private static GithubService ourInstance = new GithubService();

    public static GithubService getInstance() {
        return ourInstance;
    }

    private GithubService() {
        data = new ArrayList<Follower>();

    }

    public ArrayList<Follower> getData(){
        return data;
    }
    public ArrayList<Follower> getDataFromGitHub(String usernameToSearch){




        return data;
    }

    public void addData( String name, String url, String profileUrl){
        data.add(new Follower(name, url, profileUrl));
    }

}
