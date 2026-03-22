package com.estate.dtos.responses;

import com.estate.data.models.Type;
import lombok.Data;

@Data
public class ValidateCodeResponse {

    private String residentName;
    private String visitorName;
    private String codeType;
    private String createdBy;
    private boolean isValid;
}
