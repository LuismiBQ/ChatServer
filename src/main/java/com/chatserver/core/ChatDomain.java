package com.chatserver.core;

import com.chatserver.persistence.PersistentMessages;

import javax.ws.rs.core.Response;

public class ChatDomain {
    private PersistentMessages chatMessages = new PersistentMessages();

    public Response addMessage(ChatMessage chatMessage) {
        if (chatMessage == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        chatMessages.add(chatMessage);
        return Response.ok().build();
    }

    public ServerResponse returnMessages(int next_seq) {
        if (next_seq < 0 || next_seq >= chatMessages.size())
            return new ServerResponse(chatMessages.size(), chatMessages.getEmptyList());

        return new ServerResponse(chatMessages.size(),
                chatMessages.subList(next_seq, chatMessages.size()));
    }

    public void setChatMessages(PersistentMessages chatMessages) {
        this.chatMessages = chatMessages;
    }
}
