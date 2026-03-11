package data.models;


import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class GatePass {
    private String id;
    private String code;
    private Visitor visitor;
    private String residentId;
    private LocalTime validTill;
    private LocalDateTime dateGenerated;
    private Type passType;
    private boolean isValid;
}