package com.daniil.messenger;

public class User {
    public String email;
    public String linkPhoto;
    public String uid;
    public String nick;

    public User(){}

    public User(String email, String linkPhoto, String uid, String nick){
        this.email = email;
        this.linkPhoto = linkPhoto;
        this.uid = uid;
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkPhoto() {
        return linkPhoto;
    }

    public void setLinkPhoto(String linkPhoto) {
        this.linkPhoto = linkPhoto;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }
}
