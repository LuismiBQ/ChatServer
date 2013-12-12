package com.chatserver.persistence;

import com.chatserver.core.ChatMessage;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class PersistentMessagesTest {
    private MessagesList messages = mock(MessagesList.class);
    private PersistentMessages persistentMessages = new PersistentMessages();

    @Before
    public void setUp() throws Exception {
        persistentMessages.setMessages(messages);
    }

    @Test
    public void testSubList03() throws Exception {
        reset(messages);

        ChatMessage message1 = new ChatMessage("sdkf", "jksdlafjl");
        ChatMessage message2 = new ChatMessage("sdxfkf", "dfzrerzjksdlafjl");
        ChatMessage message3 = new ChatMessage("zzzsxfkf", "dperiqwqfzrerzjksdlafjl");

        List<ChatMessage> list03 = new LinkedList<>();
        list03.add(message1);
        list03.add(message2);
        list03.add(message3);

        when(messages.subList(0, 3)).thenReturn(list03);

        assertSame(list03, persistentMessages.subList(0, 3));
    }

    @Test
    public void testSubList23() throws Exception {
        reset(messages);

        ChatMessage message3 = new ChatMessage("zzzsxfkf", "dperiqwqfzrerzjksdlafjl");

        List<ChatMessage> list23 = new LinkedList<>();
        list23.add(message3);

        when(messages.subList(2, 3)).thenReturn(list23);

        assertSame(list23, persistentMessages.subList(2, 3));
    }

    @Test
    public void testSize() throws Exception {
        int sizes[] = {1, 2, 7, 0, 9};

        for (int expectedSize: sizes) {
            reset(messages);
            when(messages.size()).thenReturn(expectedSize);
            assertEquals(expectedSize, persistentMessages.size());
        }
    }

    @Test
    public void testAdd() throws Exception {
        reset(messages);

        ChatMessage message = new ChatMessage("zzfkf", "dperiqsdlafjl");
        persistentMessages.add(message);

        verify(messages).add(message);
    }
}
