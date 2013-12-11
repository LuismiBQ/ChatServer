package com.chatserver.core;

import java.util.List;

public class ServerResponse {
    private int nextSeq;
    private List<ChatMessage> messages;

    public ServerResponse(int nextSeq, List<ChatMessage> messages) {
        this.nextSeq = nextSeq;
        this.messages = messages;
    }

    public int getNextSeq() {
        return nextSeq;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
}
