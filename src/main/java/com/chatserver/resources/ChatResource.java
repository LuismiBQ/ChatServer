package com.chatserver.resources;

import com.chatserver.core.ChatDomain;
import com.chatserver.core.ChatMessage;
import com.chatserver.core.Constants;
import com.chatserver.core.ServerResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(Constants.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ChatResource {
    private ChatDomain chatDomain = new ChatDomain();

    @POST
    public Response addMessage(ChatMessage chatMessage) {
        return chatDomain.addMessage(chatMessage);
    }

    @GET
    public ServerResponse returnMessages(@QueryParam("next_seq") int next_seq) {
        return chatDomain.returnMessages(next_seq);
    }

    public void setChatDomain(ChatDomain chatDomain) {
        this.chatDomain = chatDomain;
    }
}
