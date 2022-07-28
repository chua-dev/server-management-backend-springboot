package com.myserver.server.service;

import com.myserver.server.model.Server;

import java.io.IOException;
import java.util.Collection;

// A List of services that we will use in this application
public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> List(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
