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
}
