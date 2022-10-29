package com.example.demo.servis;

import com.example.demo.model.Messenger;
import com.example.demo.model.User;
import com.example.demo.repo.RepoMessenger;
import com.example.demo.repo.UserRepo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepo userData;
    @Autowired
    private RepoMessenger roomData;

//    @PersistenceContext // or even @Autowired
//    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;



    public List<User> getAll() {
        return  this.userData.findAll();
    }
    public User getByLogin(String login) {
        return this.userData.getByLogin(login);
    }

    public List<User> findUser(String name){

        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);

        cr.select(root).where(cb.like(root.get("name"), "%"+name+"%"));

        Query<User> query = session.createQuery(cr);


        return query.getResultList();
    }



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User u = getByLogin(login);
        if (Objects.isNull(u)) {
            throw new UsernameNotFoundException(String.format("User %s is not found", login));
        }
        return new org.springframework.security.core.userdetails.User(u.getLogin(), u.getPassword(), true, true, true, true, new HashSet<>());
    }



    public void registration(String name, String login,String password) throws Exception {
        if(login == null || password == null){
            throw new NullPointerException();
        }
        User user = userData.findByLogin(login);
        if(user != null){
            throw new Exception("user is created");
        }
        User newUser = new User(name,login,password);
        userData.save(newUser);

    }

    public void subscribeMessenger(Long idUser, Long idRoom) throws Exception {
        if(idUser == null || idRoom == null){
            throw new NullPointerException();
        }
        if(!userData.findById(idUser).isPresent()){
            throw new Exception("not found user");
        }
        if(!roomData.findById(idRoom).isPresent()){
            throw new Exception("not found room");
        }


        User user = userData.findById(idUser).get();

        Messenger messenger = roomData.findById(idRoom).get();


        Set<Messenger> rooms = user.getMessengerUser();

        Set<User> users = messenger.getUsersInMessenger();

        for ( Messenger r : rooms ){
            if (Objects.equals(r.getId(), idUser)){
                throw new Exception("user is subscribe");
            }
        }



        for ( User u : users ){
            if (Objects.equals(u.getId(), idUser)){
                throw new Exception("user is subscribe");
            }
        }

        user.addMessenger(messenger);

        messenger.addUser(user);

        userData.save(user);

        roomData.save(messenger);

    }

    public void addFriend(String reqUser, String reqFriend) throws Exception {
        if (reqUser == null || reqFriend == null) {
            throw new NullPointerException();
        }
        if (userData.findByLogin(reqUser) == null) {
            throw new Exception("not found user");
        }
        if (userData.findByLogin(reqFriend)== null) {
            throw new Exception("not found user");
        }


        User user = userData.findByLogin(reqUser);
        Set<User> userSendFriend = user.getFriendsSendUser();

        User friend =  userData.findByLogin(reqFriend);

        int index = 0;
        for (User f:userSendFriend) {
            if(Objects.equals(f.getLogin(), reqFriend)){
                index = 1;
            }
            if(index == 0){
                throw new Exception("send to friend not found");
            }
        }





        Set<User> users = user.getFriend();

        Set<User> friends = friend.getFriend();

        for ( User u : users ){
            if (Objects.equals(u.getLogin(), reqFriend)){
                throw new Exception("user is friend");
            }
        }

        for ( User f : friends ){
            if (Objects.equals(f.getLogin(), reqUser)){
                throw new Exception("user is friend");
            }
        }


        user.addFriend(friend);

        friend.removeSendFriend(user);
        user.removeSendFriendForMe(friend);
        userData.save(user);
        userData.save(friend);

    }


    public void addFriendSend(String reqUser, String reqFriend) throws Exception {
        if (reqUser == null || reqFriend == null) {
            throw new NullPointerException();
        }
        if (userData.findByLogin(reqUser) == null) {
            throw new Exception("not found user");
        }
        if (userData.findByLogin(reqFriend)== null) {
            throw new Exception("not found user");
        }
        if(Objects.equals(reqUser,reqFriend)){
            throw new Exception("user and friend is equals");
        }


        User user = userData.findByLogin(reqUser);
        User friend = userData.findByLogin(reqFriend);

        Set<User> userSendFriend = user.getIAmFriendsSendUser();


        int index = 0;
        for (User f:userSendFriend) {
            if(Objects.equals(f.getLogin(), reqFriend)){
                index = 1;
            }
            if(index == 1){
                throw new Exception("send to friend found");
            }
        }

        user.addIAmSendFriend(friend);
        friend.addSendFriend(user);
        userData.save(user);
        userData.save(friend);

    }




}
