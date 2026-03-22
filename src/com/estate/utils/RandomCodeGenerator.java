package com.estate.utils;

import com.estate.data.repositories.GatePassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RandomCodeGenerator {

    private final GatePassRepository gatePassRepository;

    public String generateUniqueCode() {
        String code;
        do {
            code = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();
        } while (gatePassRepository.findByCode(code).isPresent());
        return code;
    }
}
