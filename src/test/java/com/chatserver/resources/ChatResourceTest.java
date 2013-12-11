package com.chatserver.resources;

import com.chatserver.core.ChatMessage;
import com.chatserver.core.ServerResponse;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.LinkedList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class ChatResourceTest {
    @Test
    public void testReturnMessagesWithNoMessages() throws Exception {
        ChatResource chatResource = new ChatResource();

        ServerResponse serverResponse = chatResource.returnMessages(0);

        assertTrue(serverResponse.getMessages().isEmpty());
        assertEquals(0, serverResponse.getNextSeq());
    }

    @Test
    public void testReturnOneMessage() throws Exception {
        ChatResource chatResource = new ChatResource();

        ChatMessage chatMessage = new ChatMessage("User1", "jsdlafjk");
        chatResource.getChatMessages().add(chatMessage);

        ServerResponse serverResponse = chatResource.returnMessages(0);

        assertEquals(1, serverResponse.getMessages().size());
        assertEquals(1, serverResponse.getNextSeq());
    }

    @Test
    public void testReturnDifficultMessage() throws Exception {
        ChatResource chatResource = new ChatResource();

        ChatMessage difficultMessage = new ChatMessage("UserZ", "áéíóúÁÉÍÓÚñÑçÇ");
        chatResource.getChatMessages().add(difficultMessage);

        ServerResponse serverResponse = chatResource.returnMessages(0);

        assertEquals(difficultMessage, serverResponse.getMessages().get(0));
    }

    @Test
    public void testReturnFromThirdMessage() throws Exception {
        ChatResource chatResource = new ChatResource();

        LinkedList<ChatMessage> chatMessages = new LinkedList<>();
        chatMessages.add(new ChatMessage("User1", "jsdlafjk"));
        chatMessages.add(new ChatMessage("User1", "bjsdsflasdffjk"));
        chatMessages.add(new ChatMessage("User2", "xbjsdsflasdffjsafk"));

        chatResource.getChatMessages().addAll(chatMessages);

        ServerResponse serverResponse = chatResource.returnMessages(2);

        assertEquals(1, serverResponse.getMessages().size());
        assertEquals(3, serverResponse.getNextSeq());
    }

    @Test
    public void testAddOneMessage() throws Exception {
        ChatResource chatResource = new ChatResource();
        ChatMessage chatMessage = new ChatMessage("User1", "kjsdfl");

        Response response = chatResource.addMessage(chatMessage);

        assertEquals(1, chatResource.getChatMessages().size());
        assertEquals(chatMessage, chatResource.getChatMessages().get(0));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAddDifficultMessage() throws Exception {
        ChatResource chatResource = new ChatResource();
        ChatMessage difficultMessage = new ChatMessage("UserZ", "áéíóúÁÉÍÓÚñÑçÇ");

        Response response = chatResource.addMessage(difficultMessage);

        assertEquals(1, chatResource.getChatMessages().size());
        assertEquals(difficultMessage, chatResource.getChatMessages().get(0));
        assertEquals(200, response.getStatus());
    }

    @Test
    public void testAddBadMessage() throws Exception {
        ChatResource chatResource = new ChatResource();

        Response response = chatResource.addMessage(null);

        assertTrue(chatResource.getChatMessages().isEmpty());
        assertEquals(500, response.getStatus());
    }
}
