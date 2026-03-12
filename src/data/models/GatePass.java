package data.models;


import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class GatePass {

    @Id
    private String id;
    private String code;
    private Visitor visitor;
    private String residentId;
    private LocalTime validTill;
    private LocalDateTime dateGenerated =  LocalDateTime.now();
    private Type passType;
    private boolean isValid;
}