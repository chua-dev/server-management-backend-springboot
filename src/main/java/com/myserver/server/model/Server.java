package com.myserver.server.model;

import com.myserver.server.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id //Imported Annotation targeting the Long id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; // ID and Primary Key
    @Column(unique = true)
    @NotEmpty(message = "IP Address cannot be empty")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;
}
