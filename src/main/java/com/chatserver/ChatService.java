package com.chatserver;

import com.chatserver.resources.ChatResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class ChatService extends Service<ServerConfiguration> {
    public static void main(String[] args) throws Exception {
        new ChatService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        bootstrap.setName("ChatService");
    }

    @Override
    public void run(ServerConfiguration configuration, Environment environment) {
        environment.addResource(new ChatResource());
    }

}

