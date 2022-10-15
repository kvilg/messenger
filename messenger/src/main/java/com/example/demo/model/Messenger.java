package com.example.demo.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Messenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long name;

    @ManyToMany
    @JoinTable(
            name = "user_messenger",
            joinColumns = @JoinColumn(name = "messenger_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> userInMessenger = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "messengerId")
    private Set<Message> massageInMessenger = new HashSet<>();


    public Messenger(){

    }
    public Messenger(User user, User friend) {
        addUserInMessenger(user);
        addUserInMessenger(friend);
    }


    public void addUserInMessenger(User user){
        userInMessenger.add(user);
    }

    public Long getId() {
        return id;
    }

    public void addMessage(Message message) {
        massageInMessenger.add(message);
    }

    public Set<User> getUsersInMessenger() {
        return userInMessenger;
    }

    public void addUser(User user) {
        userInMessenger.add(user);
    }
}
