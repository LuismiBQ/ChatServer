package com.chatserver.resources;

import com.chatserver.core.ChatDomain;
import com.chatserver.core.ChatMessage;
import com.chatserver.core.ServerResponse;
import com.chatserver.persistence.PersistentMessages;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class ChatDomainTest {
    private ChatDomain chatDomain = new ChatDomain();
    private PersistentMessages mockedChatMessages = mock(PersistentMessages.class);

    @Before
    public void setUp() {
        chatDomain.setChatMessages(mockedChatMessages);
    }

    @Test
    public void testReturnMessagesWithNegativeLastSeq() throws Exception {
        reset(mockedChatMessages);

        ServerResponse serverResponse = chatDomain.returnMessages(-1);

        assertTrue(serverResponse.getMessages().isEmpty());

    }

    @Test
    public void testReturnMessagesWithNoMessages() throws Exception {
        reset(mockedChatMessages);

        when(mockedChatMessages.size()).thenReturn(0);

        ServerResponse serverResponse = chatDomain.returnMessages(0);

        assertEquals(0, serverResponse.getNextSeq());
        assertTrue(serverResponse.getMessages().isEmpty());
    }

    @Test
    public void testReturnOneMessage() throws Exception {
        reset(mockedChatMessages);

        ChatMessage chatMessage = new ChatMessage("User1", "jsdlafjk");
        List<ChatMessage> messagesToReturn = new LinkedList<>();
        messagesToReturn.add(chatMessage);

        when(mockedChatMessages.subList(0, 1)).thenReturn(messagesToReturn);
        when(mockedChatMessages.size()).thenReturn(1);

        ServerResponse serverResponse = chatDomain.returnMessages(0);

        assertSame(messagesToReturn, serverResponse.getMessages());
        assertEquals(1, serverResponse.getNextSeq());
    }

    @Test
    public void testReturnDifficultMessage() throws Exception {
        reset(mockedChatMessages);

        ChatMessage difficultMessage = new ChatMessage("UserZ", "áéíóúÁÉÍÓÚñÑçÇ");
        List<ChatMessage> messagesToReturn = new LinkedList<>();
        messagesToReturn.add(difficultMessage);

        when(mockedChatMessages.subList(0, 1)).thenReturn(messagesToReturn);
        when(mockedChatMessages.size()).thenReturn(1);

        ServerResponse serverResponse = chatDomain.returnMessages(0);

        assertSame(messagesToReturn, serverResponse.getMessages());
        assertEquals(1, serverResponse.getNextSeq());
    }

    @Test
    public void testReturnFromThirdMessage() throws Exception {
        reset(mockedChatMessages);

        List<ChatMessage> messagesToReturn = new LinkedList<>();
        messagesToReturn.add(new ChatMessage("User2", "xbjsdsflasdffjsafk"));

        when(mockedChatMessages.subList(2, 3)).thenReturn(messagesToReturn);
        when(mockedChatMessages.size()).thenReturn(3);

        ServerResponse serverResponse = chatDomain.returnMessages(2);

        assertSame(messagesToReturn, serverResponse.getMessages());
        assertEquals(3, serverResponse.getNextSeq());
    }

    @Test
    public void testReturnWhenLastSeqGreaterThanMessagesSize() throws Exception {
        reset(mockedChatMessages);

        when(mockedChatMessages.size()).thenReturn(2);

        ServerResponse serverResponse = chatDomain.returnMessages(7);

        assertTrue(serverResponse.getMessages().isEmpty());
        assertEquals(2, serverResponse.getNextSeq());
    }

    @Test
    public void testAddOneMessage() throws Exception {
        reset(mockedChatMessages);

        ChatMessage chatMessage = new ChatMessage("User1", "kjsdfl");

        Response response = chatDomain.addMessage(chatMessage);

        verify(mockedChatMessages).add(chatMessage);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddDifficultMessage() throws Exception {
        reset(mockedChatMessages);

        ChatMessage difficultMessage = new ChatMessage("UserZ", "áéíóúÁÉÍÓÚñÑçÇ");

        Response response = chatDomain.addMessage(difficultMessage);

        verify(mockedChatMessages).add(difficultMessage);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAddBadMessage() throws Exception {
        reset(mockedChatMessages);

        Response response = chatDomain.addMessage(null);

        verify(mockedChatMessages, times(0)).add(any(ChatMessage.class));
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
}
