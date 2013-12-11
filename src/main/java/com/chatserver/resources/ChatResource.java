package com.chatserver.resources;

import com.chatserver.core.ChatMessage;
import com.chatserver.core.ServerResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;

@Path("/api/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatResource {
    LinkedList<ChatMessage> chatMessages = new LinkedList<>();

    @POST
    public Response addMessage(ChatMessage chatMessage) {
        if (chatMessage == null)
            return Response.serverError().build();

        chatMessages.add(chatMessage);
        return Response.ok().build();
    }

    @GET
    public ServerResponse returnMessages(@QueryParam("next_seq") int next_seq) {
        if (next_seq >= chatMessages.size())
            return new ServerResponse(chatMessages.size(), new LinkedList<ChatMessage>());

        return new ServerResponse(chatMessages.size(),
                chatMessages.subList(next_seq, chatMessages.size()));
    }

    public LinkedList<ChatMessage> getChatMessages() {
        return chatMessages;
    }
}
