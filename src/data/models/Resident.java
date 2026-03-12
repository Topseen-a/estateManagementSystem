package data.models;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@RequiredArgsConstructor
public class Resident {
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String phoneNumber;
    @NonNull
    private String houseAddress;
    private String email;
    private LocalDateTime dateRegistered;
    private boolean isEnabled;
}
