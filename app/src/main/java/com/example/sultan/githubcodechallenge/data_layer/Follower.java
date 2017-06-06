package com.example.sultan.githubcodechallenge.data_layer;

public class Follower {

    String login;
    String avatar_url;
    String url;

    public String getLogin() {
        return login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getUrl() {
        return url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
