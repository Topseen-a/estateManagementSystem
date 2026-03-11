package data.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Resident {
    private String id;
    private String name;
    private String phoneNumber;
    private String houseAddress;
    private String email;
    private LocalDateTime dateRegistered;
    private boolean isEnabled;
}
