package com.chatserver.resources;

import com.chatserver.core.ChatMessage;
import com.chatserver.core.ServerResponse;
import com.google.common.base.Optional;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;

@Path("/api/chat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {
    @GET
    @Timed
    public ServerResponse returnMessages(@QueryParam("next_seq") int next_seq) {
        return new ServerResponse(next_seq, new LinkedList<ChatMessage>());
    }
}
