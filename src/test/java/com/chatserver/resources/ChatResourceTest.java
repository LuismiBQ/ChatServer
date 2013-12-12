package com.chatserver.resources;


import com.chatserver.core.ChatDomain;
import com.chatserver.core.ChatMessage;
import com.chatserver.core.Constants;
import com.chatserver.core.ServerResponse;
import com.sun.jersey.api.client.ClientResponse;
import com.yammer.dropwizard.testing.ResourceTest;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ChatResourceTest extends ResourceTest {
    private ChatResource chatResource = new ChatResource();
    private ChatDomain mockedChatDomain = mock(ChatDomain.class);

    @Override
    protected void setUpResources() {
        chatResource.setChatDomain(mockedChatDomain);
        addResource(chatResource);
    }

    @Test
    public void testReturnOneMessage() throws Exception {
        reset(mockedChatDomain);

        int nextSeq = 0;
        int expectedNextSeq = 1;
        ChatMessage chatMessage = new ChatMessage("User1", "jsdlafjk");
        List<ChatMessage> messagesReturned = new LinkedList<>();
        messagesReturned.add(chatMessage);

        ServerResponse expectedServerResponse = new ServerResponse(expectedNextSeq, messagesReturned);

        when(mockedChatDomain.returnMessages(nextSeq)).thenReturn(expectedServerResponse);

        ServerResponse serverResponse = client()
                .resource(Constants.PATH)
                .queryParam("next_seq", Integer.toString(nextSeq))
                .type(MediaType.APPLICATION_JSON)
                .get(ServerResponse.class);

        verify(mockedChatDomain).returnMessages(nextSeq);
        assertEquals(expectedServerResponse, serverResponse);
    }

    @Test
    public void testReturnDifficultMessage() throws Exception {
        reset(mockedChatDomain);

        int nextSeq = 0;
        int expectedNextSeq = 1;
        ChatMessage difficultMessage = new ChatMessage("UserZ", "áéíóúÁÉÍÓÚñÑçÇ");
        List<ChatMessage> messagesReturned = new LinkedList<>();
        messagesReturned.add(difficultMessage);

        ServerResponse expectedServerResponse = new ServerResponse(expectedNextSeq, messagesReturned);

        when(mockedChatDomain.returnMessages(nextSeq)).thenReturn(expectedServerResponse);

        ServerResponse serverResponse = client()
                .resource(Constants.PATH)
                .queryParam("next_seq", Integer.toString(nextSeq))
                .type(MediaType.APPLICATION_JSON)
                .get(ServerResponse.class);

        verify(mockedChatDomain).returnMessages(nextSeq);
        assertEquals(expectedServerResponse, serverResponse);
    }

    @Test
    public void testReturnFromThirdMessage() throws Exception {
        reset(mockedChatDomain);

        int nextSeq = 2;
        int expectedNextSeq = 3;
        LinkedList<ChatMessage> messagesReturned = new LinkedList<>();
        messagesReturned.add(new ChatMessage("User2", "xbjsdsflasdffjsafk"));

        ServerResponse expectedServerResponse = new ServerResponse(expectedNextSeq, messagesReturned);

        when(mockedChatDomain.returnMessages(nextSeq)).thenReturn(expectedServerResponse);

        ServerResponse serverResponse = client()
                .resource(Constants.PATH)
                .queryParam("next_seq", Integer.toString(nextSeq))
                .type(MediaType.APPLICATION_JSON)
                .get(ServerResponse.class);

        verify(mockedChatDomain).returnMessages(nextSeq);
        assertEquals(expectedServerResponse, serverResponse);
    }

    @Test
    public void testReturnWhenLastSeqGreaterThanMessagesSize() throws Exception {
        reset(mockedChatDomain);

        int nextSeq = 7;
        int expectedNextSeq = 0;
        LinkedList<ChatMessage> messagesReturned = new LinkedList<>();

        ServerResponse expectedServerResponse = new ServerResponse(expectedNextSeq, messagesReturned);

        when(mockedChatDomain.returnMessages(nextSeq)).thenReturn(expectedServerResponse);

        ServerResponse serverResponse = client()
                .resource(Constants.PATH)
                .queryParam("next_seq", Integer.toString(nextSeq))
                .type(MediaType.APPLICATION_JSON)
                .get(ServerResponse.class);

        verify(mockedChatDomain).returnMessages(nextSeq);
        assertEquals(expectedServerResponse, serverResponse);
    }

    @Test
    public void testAddOneMessage() throws Exception {
        reset(mockedChatDomain);

        ChatMessage messageToSend = new ChatMessage("User2", "xbjsdsflasdffjsafk");
        when(mockedChatDomain.addMessage(any(ChatMessage.class))).thenReturn(Response.ok().build());

        ClientResponse response = client()
                .resource(Constants.PATH)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, messageToSend);

        verify(mockedChatDomain, times(1)).addMessage(any(ChatMessage.class));
        assertEquals(Response.ok().build().getStatus(), response.getStatus());
    }

    @Test
    public void testAddDifficultMessage() throws Exception {
        reset(mockedChatDomain);

        ChatMessage difficultMessage = new ChatMessage("UserZ", "áéíóúÁÉÍÓÚñÑçÇ");
        when(mockedChatDomain.addMessage(difficultMessage)).thenReturn(Response.ok().build());

        ClientResponse response = client()
                .resource(Constants.PATH)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, difficultMessage);

        verify(mockedChatDomain, times(1)).addMessage(difficultMessage);
        assertEquals(Response.ok().build().getStatus(), response.getStatus());
    }

    @Test
    public void testAddBadMessage() throws Exception {
        reset(mockedChatDomain);

        when(mockedChatDomain.addMessage(null))
                .thenReturn(Response.status(Response.Status.BAD_REQUEST).build());

        ClientResponse response = client()
                .resource(Constants.PATH)
                .type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, null);

        verify(mockedChatDomain, times(1)).addMessage(any(ChatMessage.class));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
