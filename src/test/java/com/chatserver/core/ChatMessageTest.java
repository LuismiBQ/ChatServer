package com.chatserver.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ChatMessageTest {
    private ChatMessage chatMessage = new ChatMessage("User1", "saldfjal");

    @Test
    public void testEquals() throws Exception {
        ChatMessage chatMessage2 = new ChatMessage("User1", "saldfjal");

        assertEquals(chatMessage, chatMessage);
        assertEquals(chatMessage, chatMessage2);
    }

    @Test
    public void testNotEquals() throws Exception {
        ChatMessage chatMessages[] = {new ChatMessage("User12", "saldfjal"),
                                      new ChatMessage("User1", "ssafdasfaldfjal"),
                                      new ChatMessage("User12", "ssafdasfaldfjal")};

        for (ChatMessage chatMessageN: chatMessages)
            assertNotEquals(chatMessage, chatMessageN);

        Object obj = new Object();
        assertNotEquals(chatMessage, obj);
    }
}
