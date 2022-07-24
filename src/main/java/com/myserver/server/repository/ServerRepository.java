package com.myserver.server.repository;

import com.myserver.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

//This class interface manage Server Model and primary key is Long
public interface ServerRepository extends JpaRepository<Server, Long> {
}
