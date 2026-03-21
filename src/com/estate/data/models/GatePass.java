package com.estate.data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Document(collection = "gate_passes")
public class GatePass {

    @Id
    private String id;
    private String code;
    private Visitor visitor;
    private String residentId;
    private LocalTime validTill;
    private LocalDateTime dateGenerated =  LocalDateTime.now();
    private Type passType;
    private boolean isValid = true;
}