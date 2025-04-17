package com.example.test_xml.service.impl;

import com.example.test_xml.exception.InvalidDataErrorException;
import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.response.ExtractNameResponse;
import com.example.test_xml.service.PreProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;

@Service
public class PreProcessServiceImpl implements PreProcessService {

    private final Logger logger = LoggerFactory.getLogger(PreProcessServiceImpl.class);

    @Value("${max.first.name.characters.limit}")
    private String maxFirstNameCharacters;

    @Value("${max.address.characters.limit}")
    private String maxAddressCharacters;


    @Override
    public ExtractNameResponse extractFirstNameAndLastName(String name) {
        if (name == null || name.trim().isEmpty()) {
            logger.error("Name is required to extract first name and last name.");
            throw new ValidationErrorException("Name is required to extract first name and last name.");
        }

        String cleanedName = name.replace(",", "")
                .replace(".", " ")
                .replaceAll("\\s+", " ")
                .trim();

        String[] parts = cleanedName.split(" ");

        if (parts.length == 1) {
            return new ExtractNameResponse(parts[0], null);
        }

        String firstName = parts[parts.length - 1];
        StringBuilder lastNameBuilder = new StringBuilder();

        for (int i = 0; i < parts.length - 1; i++) {
            lastNameBuilder.append(parts[i]);
            if (i < parts.length - 2) {
                lastNameBuilder.append(" ");
            }
        }

        String lastName = lastNameBuilder.toString();

        return new ExtractNameResponse(lastName, firstName);
    }

    @Override
    public String formatDateInCorrectFormat(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, inputFormatter);
        return dateTime.format(outputFormatter);
    }


    @Override
    public String decodeBase64(String encodeValue) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodeValue);
        return new String(decodedBytes);
    }

    @Override
    public String convertNationality(String nationality1) {
        if (nationality1 != null && nationality1.equalsIgnoreCase("sri lankan")) {
            return CountryCodes.LK.toString();
        }
        return CountryCodes.AC.toString();
    }

    @Override
    public String reduceFirstNameCharacters(String firstName) {
        return reduceCharacters(firstName, Integer.parseInt(maxFirstNameCharacters), "First name");
    }

    @Override
    public String createAddressUsingAddressLines(String addressLine1, String addressLine2, String addressLine3) {
        StringBuilder addressBuilder = new StringBuilder();

        if (addressLine1 != null && !addressLine1.isEmpty() && !addressLine1.equals("null")) {
            addressBuilder.append(addressLine1);
        }
        if (addressLine2 != null && !addressLine2.isEmpty() && !addressLine2.equals("null")) {
            if (addressBuilder.length() > 0) addressBuilder.append(", ");
            addressBuilder.append(addressLine2);
        }
        if (addressLine3 != null && !addressLine3.isEmpty() && !addressLine3.equals("null")) {
            if (addressBuilder.length() > 0) addressBuilder.append(", ");
            addressBuilder.append(addressLine3);
        }

        String address = addressBuilder.toString();

        if (address.length() > 99) {
            return reduceAddressCharacters(address);
        } else {
            return address;
        }
    }


    private String reduceAddressCharacters(String address) {
        return reduceCharacters(address, Integer.parseInt(maxAddressCharacters), "Address");
    }

    private String reduceCharacters(String characters, int maxLength, String name) {
        if (characters != null && characters.length() > maxLength) {
            String updateFirstName = characters.substring(0, maxLength);
            return updateFirstName + "_";
        } else {
            logger.error("Cannot reduce characters in {} because length is not greater than {}.", name, maxLength);
            throw new ValidationErrorException("Cannot reduce characters in " + name + " because length is not greater than " + maxLength);
        }
    }

    @Override
    public boolean mobileOrNot(String mobileNumber) {
        if (mobileNumber.startsWith("+94")) {
            return true;
        } else if (mobileNumber.startsWith("94")) {
            return true;
        } else if (mobileNumber.startsWith("07")) {
            return true;
        } else if (mobileNumber.startsWith("7")) {
            return true;
        } else if (mobileNumber.startsWith("0")) {
            return false;
        } else {
            logger.error("Error occurred when check mobile Number. {}", mobileNumber);
            throw new InvalidDataErrorException("Error occurred when check mobile Number.");
        }
    }

    @Override
    public String mobileNumberStandardization(String mobileNumber) {
        if (mobileNumber.startsWith("+94")) {
            return mobileNumber.substring(3) + "0";
        } else if (mobileNumber.startsWith("94")) {
            return mobileNumber.substring(2) + "0";
        } else if (mobileNumber.startsWith("07")) {
            return mobileNumber;
        } else if (mobileNumber.startsWith("7")) {
            return "0" + mobileNumber;
        } else {
            logger.error("Error occurred when mobile number standardization. {}", mobileNumber);
            throw new InvalidDataErrorException("Mobile Number is not in correct format.");
        }
    }
}
