package com.zyad.social_platform_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zyad.social_platform_api.entity.Message;
import com.zyad.social_platform_api.repository.MessageRepository;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository mr){
        this.messageRepository = mr;
    }

    public Message newMessageService(Message messageToAdd){
        return messageRepository.save(messageToAdd);
    }

    public List<Message> getAllMessagesService(){
        return messageRepository.findAll();
    }

    public Message getMessageByIdService(int messageId){
        Optional<Message> m = messageRepository.findById(messageId);
        if (m.isPresent()){
            return m.get();
        } return null;
    }

    public void deleteMessageByIdService(int messageId){
        messageRepository.deleteById(messageId);
    }

    public Message updateMessageByIdService(String textVar, int keyVar){
        Optional<Message> m = messageRepository.findById(keyVar);
        if (m.isPresent()){
            Message msg = m.get();
            msg.setMessageText(textVar);
            messageRepository.save(msg);
            return msg;
        } return null;
    }

    public List<Message> getAllMessagesByUserService(int postedBy){
        return messageRepository.findByPostedBy(postedBy);
    }

}
