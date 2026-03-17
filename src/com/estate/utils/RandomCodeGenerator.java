package com.estate.utils;

import com.estate.data.repositories.GatePassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RandomCodeGenerator {

    @Autowired
    private GatePassRepository gatePassRepository;

    public String generateUniqueCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        } while (gatePassRepository.existsByCode(code));
        return code;
    }
}
