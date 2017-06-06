package com.example.sultan.githubcodechallenge.data_layer;

public class User {

    String login = "dancingblueberry";
    String name = "asdf";
    String avatar_url = "https://avatars0.githubusercontent.com/u/4336872?v=3";
    String url = "https://api.github.com/users/dancingblueberry";
    String html_url = "https://github.com/dancingblueberry";
    String location;
    String email;
    int public_repos = 0;
    int followers = 0;
    int following = 0;




    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public  String getUrl(){
        return  avatar_url;
    }

    public  String getProfileUrl(){
        return  html_url;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    public int getPublic_repos() {
        return public_repos;
    }

    public String getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
