package com.myserver.server.service.implementation;

import com.myserver.server.enumeration.Status;
import com.myserver.server.model.Server;
import com.myserver.server.repository.ServerRepository;
import com.myserver.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;

import static java.lang.Boolean.TRUE;

// Dependency Injection
@RequiredArgsConstructor
@Service
@Transactional
@Slf4j // Printing to console
public class ServerServiceImplementation implements ServerService {
    private final ServerRepository serverRepository;

    // Required Args Annotation already do that
    //public ServerServiceImplementation(ServerRepository serverRepository) {
    //    this.serverRepository = serverRepository;
    //}

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server); // JPA check if id not existed then create
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> List(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get(); // get() to get the actual server
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server); //JPA smart to know ID existing and to update
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server with id {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    private String setServerImageUrl() {
        String[] imageNames = { "server1.png", "server2.png" };
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(2)]).toUriString();
    }
}
