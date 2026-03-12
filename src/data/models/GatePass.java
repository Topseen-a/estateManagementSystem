package data.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
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