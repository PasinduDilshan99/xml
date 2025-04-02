package com.example.test_xml.service;

import com.example.test_xml.model.response.ExtractNameResponse;

public interface CommonMethodService {
    ExtractNameResponse extractFirstNameAndLastName(String name);
    String decodeBase64(String encodeValue);

    String convertNationality(String nationality1);
}
