package com.chatserver.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Iterator;
import java.util.List;

public class ServerResponse {
    @JsonProperty
    private int nextSeq;
    @JsonProperty
    private List<ChatMessage> messages;

    private ServerResponse() {}

    public ServerResponse(int nextSeq, List<ChatMessage> messages) {
        this.setNextSeq(nextSeq);
        this.setMessages(messages);
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setNextSeq(int nextSeq) {
        this.nextSeq = nextSeq;
    }

    public boolean equals(Object rhs) {
        if ( !(rhs instanceof  ServerResponse) )
            return false;

        ServerResponse rhsCasted = (ServerResponse) rhs;

        if (nextSeq != rhsCasted.nextSeq)
            return false;

        if (messages.size() != rhsCasted.messages.size())
            return false;

        Iterator<ChatMessage> iterator1 = messages.iterator();
        Iterator<ChatMessage> iterator2 = rhsCasted.messages.iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            ChatMessage chatMessage1 = iterator1.next();
            ChatMessage chatMessage2 = iterator2.next();

            if ( !(chatMessage1.equals(chatMessage2)) )
                return false;
        }

        return true;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }
}
