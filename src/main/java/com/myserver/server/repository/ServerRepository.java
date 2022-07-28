package com.myserver.server.repository;

import com.myserver.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

//This class interface manage extend jpa Server Model and primary key is Long
// JPA repository help to create and update/ delete data in database
public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
    // Find Ip Address when its unique
}
