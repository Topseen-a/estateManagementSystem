package com.estate.dtos.requests;

import com.estate.data.models.Type;

import java.time.LocalDateTime;

public class GenerateCodeRequest {

    private String residentId;
    private LocalDateTime validTill;
    private Type type;
}
