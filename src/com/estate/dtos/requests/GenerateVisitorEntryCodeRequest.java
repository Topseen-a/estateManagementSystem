package com.estate.dtos.requests;

import lombok.Data;

@Data
public class GenerateVisitorEntryCodeRequest {

    private String residentId;
    private String visitorName;
    private String visitorPhoneNumber;
    private String purposeOfVisit;
}
