package com.estate.dtos.responses;


import lombok.Data;

@Data
public class OnboardResidentResponse {

    private String residentId;
    private String residentName;
    private String dateRegistered;
}
