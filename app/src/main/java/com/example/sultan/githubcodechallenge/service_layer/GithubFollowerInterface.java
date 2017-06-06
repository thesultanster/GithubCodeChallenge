package com.example.sultan.githubcodechallenge.service_layer;

import com.example.sultan.githubcodechallenge.data_layer.Follower;
import com.example.sultan.githubcodechallenge.data_layer.User;

public interface GithubFollowerInterface {
    void onFollowerFetched(Follower follower);
}
