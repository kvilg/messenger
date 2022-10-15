package com.example.demo.servis;


import com.example.demo.model.Message;
import com.example.demo.model.Messenger;
import com.example.demo.model.User;
import com.example.demo.repo.RepoMessage;
import com.example.demo.repo.RepoMessenger;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private UserRepo userData;

    @Autowired
    private RepoMessage messageData;

    @Autowired
    private RepoMessenger messengerData;

    public void sendMessageInMessenger(Long idMessenger, String idUser, String textMessage) throws Exception {
        if(idMessenger == null ){
            throw new Exception("name is null");
        }
        if(idUser == null ){
            throw new Exception("idUser is null");
        }
        if(textMessage == null ){
            throw new Exception("textMessage is null");
        }


        if(userData.findByLogin(idUser) == null){
            throw new Exception("not found user");
        }
        if(!messengerData.findById(idMessenger).isPresent()){
            throw new Exception("not found messenger");
        }

        User user = userData.findByLogin(idUser);

        Messenger messenger = messengerData.findById(idMessenger).get();

        Message message = new Message(textMessage,user);



        messageData.save(message);

        messenger.addMessage(message);

        messengerData.save(messenger);



    }

}
