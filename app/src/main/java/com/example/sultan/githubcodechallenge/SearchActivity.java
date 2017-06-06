package com.example.sultan.githubcodechallenge;

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

import com.example.sultan.githubcodechallenge.data_layer.Follower;
import com.example.sultan.githubcodechallenge.data_layer.User;
import com.example.sultan.githubcodechallenge.service_layer.GithubService;
import com.example.sultan.githubcodechallenge.service_layer.GithubFollowerInterface;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

     GridView gridView;
    FollowersGridViewAdapter booksAdapter;

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

        // Intitialize Gridview
        gridView = (GridView)findViewById(R.id.gridview);
        booksAdapter = new FollowersGridViewAdapter(getApplicationContext(), new ArrayList<Follower>() );
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

            // Clear existing results to replace with new results
            booksAdapter.clearAdapter();

            // Call service and update gridview
            GithubService.getInstance().getFollowersFromGitHub(query, new GithubFollowerInterface() {
                @Override
                public void onFollowerFetched(Follower follower) {
                    booksAdapter.addCell(follower);
                }
            });

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
