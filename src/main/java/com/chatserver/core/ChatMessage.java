package com.chatserver.core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ChatMessage {
    @JsonProperty
    private String nick;
    @JsonProperty
    private String message;

    private ChatMessage() {}

    public ChatMessage(String nick, String message) {
        this.nick = nick;
        this.message = message;
    }

    public boolean equals(Object rhs) {
        if( !(rhs instanceof  ChatMessage) )
            return false;

        ChatMessage rhsCasted = (ChatMessage) rhs;

        return nick.equals(rhsCasted.nick) &&
               message.equals(rhsCasted.message);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
