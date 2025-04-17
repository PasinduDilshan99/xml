package com.example.test_xml.service;

import com.example.test_xml.model.response.ExtractNameResponse;

public interface PreProcessService {
    ExtractNameResponse extractFirstNameAndLastName(String name);

    String decodeBase64(String encodeValue);

    String formatDateInCorrectFormat(String date);

    String convertNationality(String nationality1);

    String createAddressUsingAddressLines(String addressLine1,String addressLine2,String addressLine3);

    String reduceFirstNameCharacters(String firstName);

    String mobileNumberStandardization(String mobileNumber);
    boolean mobileOrNot(String mobileNumber);
}
