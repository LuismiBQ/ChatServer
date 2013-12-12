package com.chatserver.persistence;

import com.chatserver.core.ChatMessage;

import java.util.List;

public class PersistentMessages {
    private MessagesList messages = new MessagesList();

    public List<ChatMessage> subList(int a, int b) {
        return messages.subList(a, b);
    }

    public int size() {
        return messages.size();
    }

    public void add(ChatMessage chatMessage) {
        messages.add(chatMessage);
    }

    public List<ChatMessage> getEmptyList() {
        return new MessagesList();
    }

    public void setMessages(MessagesList messages) {
        this.messages = messages;
    }
}
