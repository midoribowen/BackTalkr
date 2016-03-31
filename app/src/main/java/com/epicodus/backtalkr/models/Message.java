package com.epicodus.backtalkr.models;

import org.parceler.Parcel;

import java.util.UUID;

/**
 * Created by Guest on 3/30/16.
 */

@Parcel
public class Message {
    String content;
    UUID userId;
    public Message() {

    }

    public Message(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setUserId(String userId) {
        String userIdString = userId;
        UUID userIdUUID = UUID.fromString(userIdString);
        this.userId = userIdUUID;
    }
}
