package com.myserver.server.resource;

import com.myserver.server.model.Response;
import com.myserver.server.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

// Basically A Controller
@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResponse {
    private final ServerServiceImplementation serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("servers", serverService.list(30)))
                        .message("Server Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

}
