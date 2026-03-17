package com.estate.dtos.requests;

import lombok.Data;

import java.time.LocalTime;

@Data
public class GenerateResidentEntryCodeRequest {

    private String residentId;
    private LocalTime validTill;
}
