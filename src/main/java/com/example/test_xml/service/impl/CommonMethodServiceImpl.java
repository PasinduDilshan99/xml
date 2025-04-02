package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.response.ExtractNameResponse;
import com.example.test_xml.service.CommonMethodService;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class CommonMethodServiceImpl implements CommonMethodService {

    @Override
    public ExtractNameResponse extractFirstNameAndLastName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return new ExtractNameResponse(null,null);
        }
        String trimmedInput = name.trim();
        int lastSpaceIndex = trimmedInput.lastIndexOf(" ");
        if (lastSpaceIndex == -1) {
            return new ExtractNameResponse(name, null);
        }
        String firstName = trimmedInput.substring(0, lastSpaceIndex);
        String lastName = trimmedInput.substring(lastSpaceIndex + 1);
        return new ExtractNameResponse(firstName, lastName);
    }


    @Override
    public String decodeBase64(String encodeValue) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodeValue);
        return new String(decodedBytes);
    }

    @Override
    public String convertNationality(String nationality1) {
        if (nationality1.trim().toLowerCase().equals("sri lankan")){
            return CountryCodes.LK.toString();
        }
        return CountryCodes.AC.toString();
    }
}
