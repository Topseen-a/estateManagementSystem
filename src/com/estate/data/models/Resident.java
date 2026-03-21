package com.estate.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "residents")
public class Resident {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String houseAddress;
    private String email;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    private boolean isEnabled = true;
}
