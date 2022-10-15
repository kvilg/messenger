package com.example.demo.servis;

import com.example.demo.model.Messenger;
import com.example.demo.model.User;
import com.example.demo.repo.RepoMessenger;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MessengerService {

    @Autowired
    private RepoMessenger messengerData;
    @Autowired
    private UserRepo userData;



    public void createMessenger(String userId,String friendId ) throws Exception {
        if(userData.findByLogin(userId) == null){
            throw new Exception("user is not defend");
        }
        if(userData.findByLogin(friendId) == null){
            throw new Exception("user is not defend");
        }

        List<Messenger> messengerList = messengerData.findAll();

        for (Messenger messenger : messengerList) {
            Set<User> userSet = messenger.getUsersInMessenger();
            if (userSet.size() == 2) {
                List<User> userList = new LinkedList<>(userSet);
                if ((Objects.equals(userList.get(0).getLogin(), userId) && Objects.equals(userList.get(1).getLogin(), friendId))
                        || (Objects.equals(userList.get(1).getLogin(), userId) && Objects.equals(userList.get(0).getLogin(), friendId))) {
                    throw new Exception("Messenger is created");
                }
            }
        }




        User user = userData.findByLogin(userId);

        User friend = userData.findByLogin(friendId);

        Messenger room = new Messenger(user,friend);

        Messenger newRoom = messengerData.save(room);

        user.addMessenger(newRoom);

        userData.save(user);
    }
}
