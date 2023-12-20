package com.example.sample.common.utils;

import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CryptoConverter implements AttributeConverter<String, String> {

    private final Aes128Util aes128Util;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if(attribute == null) return null;

        return aes128Util.encrypted(attribute);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if(dbData == null) return null;

        return aes128Util.decrypted(dbData);
    }
}
