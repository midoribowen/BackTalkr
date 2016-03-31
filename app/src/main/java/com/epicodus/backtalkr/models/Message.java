package com.epicodus.backtalkr.models;

import org.parceler.Parcel;

import java.util.UUID;

/**
 * Created by Guest on 3/30/16.
 */

@Parcel
public class Message {
    String messageId;
    String content;
    String username;
    String categoryId;

    public Message() {

    }

    public Message(String messageId, String content, String username, String categoryId) {
        this.messageId = messageId;
        this.content = content;
        this.username = username;
        this.categoryId = categoryId;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }

    public String getCategoryId() {
        return categoryId;
    }


}
