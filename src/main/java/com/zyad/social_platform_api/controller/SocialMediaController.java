package com.zyad.social_platform_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.zyad.social_platform_api.entity.Account;
import com.zyad.social_platform_api.entity.Message;
import com.zyad.social_platform_api.service.AccountService;
import com.zyad.social_platform_api.service.MessageService;

@RestController
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(AccountService ar, MessageService ms){
        this.accountService = ar;
        this.messageService = ms;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> newUser(@RequestBody Account pojo) {
        if (pojo.getUsername() == null || pojo.getPassword() == null) {
            return ResponseEntity.status(400).build();
        }

        if (pojo.getUsername().isBlank() || pojo.getPassword().length() < 4) {
            return ResponseEntity.status(400).build();
        }

        if (accountService.user(pojo.getUsername()) != null) {
            return ResponseEntity.status(409).build();
        }

        Account newAccount = accountService.newUserService(pojo);
        return ResponseEntity.ok(newAccount);
    }


    @PostMapping("/login")
    public ResponseEntity<Account> userLogin(@RequestBody Account pojo){
        Account logged = accountService.login(pojo.getUsername(), pojo.getPassword());
        if (logged == null){
            return ResponseEntity.status(401).build();
        } return ResponseEntity.status(200).body(logged);
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> newMessage(@RequestBody Message pojo){
        if (pojo.getMessageText().isBlank() || pojo.getMessageText().length() > 255){
            return ResponseEntity.status(400).build();
        } else if (accountService.findID(pojo.getPostedBy())) {
            Message newMessage = messageService.newMessageService(pojo);
            return ResponseEntity.status(200).body(newMessage);
        } else {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> allMessages(){
        List<Message> all = messageService.getAllMessagesService();
        return ResponseEntity.status(200).body(all);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        Message retrieve = messageService.getMessageByIdService(messageId);
        if (retrieve == null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok(retrieve);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int messageId){
        if (messageService.getMessageByIdService(messageId) == null){
            return ResponseEntity.ok().build();
        }
        messageService.deleteMessageByIdService(messageId);
        return ResponseEntity.ok(1);
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById(@RequestBody Message m, @PathVariable int messageId) {
        Message existing = messageService.getMessageByIdService(messageId);
        if (existing == null) {
            return ResponseEntity.status(400).build();
        }

        String text = m.getMessageText();
        if (text == null || text.isBlank() || text.length() > 255) {
            return ResponseEntity.status(400).build();
        }

        messageService.updateMessageByIdService(text, messageId);
        return ResponseEntity.ok(1);
    }


    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> AllMessagesByUser(@PathVariable int accountId){
        List<Message> all = messageService.getAllMessagesByUserService(accountId);
        return ResponseEntity.status(200).body(all);
    }
}
