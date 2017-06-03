package com.example.sultan.githubcodechallenge;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

     GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {

            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }

        // Handle search intent
        handleIntent(getIntent());

        gridView = (GridView)findViewById(R.id.gridview);








        FollowersGridViewAdapter booksAdapter = new FollowersGridViewAdapter(this, GithubService.getInstance().getData());
        gridView.setAdapter(booksAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setMaxWidth(Integer.MAX_VALUE);

        return true;
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Log.d("search", query);

            // Tag used to cancel the request
            String tag_json_obj = "json_obj_req";
            String url = "https://api.github.com/users/"+query+"/followers";
            JsonArrayRequest req = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("volley", response.toString());




                            JsonElement jelement = new JsonParser().parse(response.toString());
                            JsonArray jarray = jelement.getAsJsonArray();

                            for( JsonElement followerEle : jarray){
                                JsonObject follower = followerEle.getAsJsonObject();

                                Log.d("gson", follower.get("login").toString());
                                Log.d("gson", follower.get("avatar_url").toString());
                                Log.d("gson", follower.get("html_url").toString());


                                String profileUrl = "https://api.github.com/users/" + follower.get("login").toString();
                                profileUrl = profileUrl.replace("\"", "");
                                String url = follower.get("avatar_url").toString();
                                url = url.replace("\"", "");
                                String name = follower.get("login").toString();
                                name = name.replace("\"", "");

                                GithubService.getInstance().addData(name,url,profileUrl);
                                FollowersGridViewAdapter booksAdapter = new FollowersGridViewAdapter(getApplicationContext(), GithubService.getInstance().getData());
                                gridView.setAdapter(booksAdapter);
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


            // Now I must refresh the grid
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
