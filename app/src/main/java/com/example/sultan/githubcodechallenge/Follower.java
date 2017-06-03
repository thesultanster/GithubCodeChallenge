package com.example.sultan.githubcodechallenge;

/**
 * Created by Sultan on 6/2/2017.
 */

public class Follower {

    String login = "dancingblueberry";
    int id = 4336872;
    String avatar_url = "https://avatars0.githubusercontent.com/u/4336872?v=3";
    String gravatar_id = "";
    String url = "https://api.github.com/users/dancingblueberry";
    String html_url = "https://github.com/dancingblueberry";
    String followers_url = "https://api.github.com/users/dancingblueberry/followers";
    String following_url = "https://api.github.com/users/dancingblueberry/following{/other_user}";
    String gists_url = "https://api.github.com/users/dancingblueberry/gists{/gist_id}";
    String starred_url = "https://api.github.com/users/dancingblueberry/starred{/owner}{/repo}";
    String subscriptions_url = "https://api.github.com/users/dancingblueberry/subscriptions";
    String organizations_url = "https://api.github.com/users/dancingblueberry/orgs";
    String repos_url = "https://api.github.com/users/dancingblueberry/repos";
    String events_url = "https://api.github.com/users/dancingblueberry/events{/privacy}";
    String received_events_url = "https://api.github.com/users/dancingblueberry/received_events";
    String type = "User";
    boolean site_admin = false;

    public Follower(String name) {
        this.login = name;
    }

    public Follower(String name, String url, String profile_url) {
        this.login = name;
        this.avatar_url = url;
        this.html_url = profile_url;
    }

    public String getName() {
        return login;
    }

    public  String getUrl(){
        return  avatar_url;
    }

    public  String getProfileUrl(){
        return  html_url;
    }

}
