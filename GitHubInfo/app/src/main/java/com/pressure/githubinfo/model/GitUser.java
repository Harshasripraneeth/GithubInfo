package com.pressure.githubinfo.model;

import android.graphics.Bitmap;

import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;

public class GitUser {

    @SerializedName("login")
    private String login;

    public String getProfileavatar() {
        return profileavatar;
    }

    public void setProfileavatar(String profileavatar) {
        this.profileavatar = profileavatar;
    }

    @SerializedName("avatar_url")
    private String profileavatar;
    @SerializedName("name")
    private String name;
    @SerializedName("followers")
    private String follower;
    @SerializedName("following")
    private String following;
    @SerializedName("email")
    private String email;
    @SerializedName("created_at")
    private String created;
    @SerializedName("updated_at")
    private String updated;

    //for visibility of progress bar and show repositories button
    private boolean completed = false;


    public GitUser(String login, String name, String follower, String following, String email, String created, String updated, String profileavatar) {
        this.setLogin(login);
        this.setName(name);
        this.setFollower(follower);
        this.setFollowing(following);
        this.setEmail(email);
        this.setCreated(created);
        this.setUpdated(updated);
        this.setProfileavatar(profileavatar);
    }
    public boolean isDataEmpty(String s)
    {
        if( s.indexOf("null")!= -1)
            return true;

        return false;
    }
    public String getEmptyString(String s)
    {
        return s.replace("null","NOT ENTERED");
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
