package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String login;

    private String password;


    @ManyToMany( mappedBy = "userInMessenger")
    private Set<Messenger> messengersUser = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friendsUser = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_friendSend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friendSend_id")
    )
    private Set<User> friendsSendUser = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "user_iAmFriendSend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "iAmFriendSend_id")
    )
    private Set<User> iAmFriendsSendUser = new HashSet<>();

    public User() {


    }


    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String ssss) {
        this.name = ssss;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long idUser) {
        this.id = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




    public void addMessenger(Messenger messenger) {
        messengersUser.add(messenger);
    }

    public Set<User> getFriend() {
        return friendsUser;
    }

    public void addFriend(User friend) {
        friendsUser.add(friend);
    }

    public Set<Messenger> getMessengersUser() {
        return messengersUser;
    }

    public Set<User> getFriendsUser() {
        return friendsUser;
    }

    public Set<Messenger> getMessengerUser() {
        return messengersUser;
    }


    public void setMessengersUser(Set<Messenger> messengersUser) {
        this.messengersUser = messengersUser;
    }

    public void setFriendsUser(Set<User> friendsUser) {
        this.friendsUser = friendsUser;
    }

    public Set<User> getFriendsSendUser() {
        return friendsSendUser;
    }

    public void setFriendsSendUser(Set<User> friendsSendUser) {
        this.friendsSendUser = friendsSendUser;
    }

    public Set<User> getIAmFriendsSendUser() {
        return iAmFriendsSendUser;
    }

    public void setIAmFriendsSendUser(Set<User> iAmFriendsSendUser) {
        this.iAmFriendsSendUser = iAmFriendsSendUser;
    }

    public void removeSendFriend(User iAmFriendsSendUser) {
        this.iAmFriendsSendUser.remove(iAmFriendsSendUser);
    }

    public void removeSendFriendForMe(User friend) {
        this.friendsSendUser.remove(friend);
    }

    public void addIAmSendFriend(User friend) {
        this.iAmFriendsSendUser.add(friend);
    }

    public void addSendFriend(User user) {
        this.friendsSendUser.add(user);
    }
}