package com.chatserver.core;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ServerResponseTest {
    private ServerResponse serverResponse;

    @Before
    public void setUp() {
        int nextSeq = 1;
        List<ChatMessage> messages = new LinkedList<>();
        ChatMessage chatMessage = new ChatMessage("User1", "salfjl");
        messages.add(chatMessage);

        serverResponse = new ServerResponse(nextSeq, messages);
    }

    private ServerResponse createEqualServerResponse() {
        int nextSeq = 1;
        List<ChatMessage> messages = new LinkedList<>();
        ChatMessage chatMessage = new ChatMessage("User1", "salfjl");
        messages.add(chatMessage);

        return new ServerResponse(nextSeq, messages);
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(serverResponse, serverResponse);

        ServerResponse serverResponse2 = createEqualServerResponse();
        assertEquals(serverResponse, serverResponse2);
    }

    private ServerResponse createNotEqualServerResponse2() {
        int nextSeq = 0;
        List<ChatMessage> messages = new LinkedList<>();

        return new ServerResponse(nextSeq, messages);
    }

    private ServerResponse createNotEqualServerResponse3() {
        int nextSeq = 1;
        List<ChatMessage> messages = new LinkedList<>();
        ChatMessage chatMessage = new ChatMessage("User133", "masdfñsalfjlasdf");
        messages.add(chatMessage);

        return new ServerResponse(nextSeq, messages);
    }

    private ServerResponse createNotEqualServerResponse4() {
        int nextSeq = 2;
        List<ChatMessage> messages = new LinkedList<>();
        ChatMessage chatMessage1 = new ChatMessage("User1", "salfjl");
        ChatMessage chatMessage2 = new ChatMessage("User133", "masdfñsalfjlasdf");
        messages.add(chatMessage1);
        messages.add(chatMessage2);

        return new ServerResponse(nextSeq, messages);
    }

    private ServerResponse createNotEqualServerResponse5() {
        int nextSeq = 1;
        List<ChatMessage> messages = new LinkedList<>();
        ChatMessage chatMessage1 = new ChatMessage("User1", "salfjl");
        ChatMessage chatMessage2 = new ChatMessage("User133", "masdfñsalfjlasdf");
        messages.add(chatMessage1);
        messages.add(chatMessage2);

        return new ServerResponse(nextSeq, messages);
    }


    private ServerResponse createNotEqualServerResponse6() {
        int nextSeq = 1;
        List<ChatMessage> messages = new LinkedList<>();
        ChatMessage chatMessage1 = new ChatMessage("User1", "salfjl");
        ChatMessage chatMessage2 = new ChatMessage("User133", "masdfñsalfjlasdf");
        ChatMessage chatMessage3 = new ChatMessage("Userasdf133", "masdfñsalfjlasdf");
        messages.add(chatMessage1);
        messages.add(chatMessage2);
        messages.add(chatMessage3);

        return new ServerResponse(nextSeq, messages);
    }

    @Test
    public void testNotEquals() throws Exception {
        Object obj = new Object();
        assertNotEquals(serverResponse, obj);

        assertNotEquals(serverResponse, createNotEqualServerResponse2());
        assertNotEquals(serverResponse, createNotEqualServerResponse3());
        assertNotEquals(serverResponse, createNotEqualServerResponse4());
        assertNotEquals(serverResponse, createNotEqualServerResponse5());
        assertNotEquals(serverResponse, createNotEqualServerResponse6());
    }
}
