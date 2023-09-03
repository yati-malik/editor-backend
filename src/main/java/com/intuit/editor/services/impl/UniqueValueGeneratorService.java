package com.intuit.editor.services.impl;

import com.intuit.editor.services.IUniqueValueGeneratorService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UniqueValueGeneratorService implements IUniqueValueGeneratorService {
    public String generateUniqueValue() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
