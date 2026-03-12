package data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Resident {

    @Id
    private String id;
    private String name;
    private String phoneNumber;
    private String houseAddress;
    private String email;
    private LocalDateTime dateRegistered = LocalDateTime.now();
    private boolean isEnabled = true;

    @Override
    public boolean equals(Object object) {
        if (object instanceof Resident resident) {
            return this.getId().equals(resident.getId()) || this.getPhoneNumber().equals(resident.getPhoneNumber());
        }
        return false;
    }
}
