package com.chatserver.persistence;

import com.chatserver.core.ChatMessage;

import java.util.LinkedList;

// Es una obviedad, pero si no daba problemas al hacer el mock de un tipo genérico.
public class MessagesList extends LinkedList<ChatMessage> {}
