package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.xmlDto.*;
import com.example.test_xml.model.xmlDto.pae.TPersonMyClient;
import com.example.test_xml.validationServices.CommonValidationService;
import com.example.test_xml.validationServices.EnumValidationService;
import com.example.test_xml.validationServices.TPersonMyClientValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TPersonMyClientValidationImpl implements TPersonMyClientValidation {

    private final Logger logger = LoggerFactory.getLogger(TPersonMyClientValidationImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;

    @Autowired
    public TPersonMyClientValidationImpl(CommonValidationService commonValidationService, EnumValidationService enumValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
    }

    @Override
    public void validateTPersonMyClient(TPersonMyClient tPersonMyClient) {
        // comment this before deploy
        if (tPersonMyClient.getNationality1() == null) {
            tPersonMyClient.setNationality1(CountryCodes.LK.toString());
        }
        if (tPersonMyClient.getResidence() == null) {
            tPersonMyClient.setResidence(CountryCodes.LK.toString());
        }
        commonValidationService.validateGender(tPersonMyClient.getGender(), false);
        commonValidationService.validateTitle(tPersonMyClient.getTitle(), false);
        commonValidationService.validateFirstName(tPersonMyClient.getFirstName(), true);
        commonValidationService.validateMiddleName(tPersonMyClient.getMiddleName(), false);
        commonValidationService.validatePrefix(tPersonMyClient.getPrefix(), false);
        commonValidationService.validateLastName(tPersonMyClient.getLastName(), true);
        commonValidationService.validateBirthDate(tPersonMyClient.getBirthdate(), true);
        commonValidationService.validateBirthPlace(tPersonMyClient.getBirthPlace(), false);
        commonValidationService.validateMothersName(tPersonMyClient.getMothersName(), false);
        commonValidationService.validateAlias(tPersonMyClient.getAlias(), false);
        commonValidationService.validateSsn(tPersonMyClient.getSsn(), true);
        if (tPersonMyClient.getPassports() != null) {
            for (Passport passport : tPersonMyClient.getPassports()) {
                if (passport.getPassportCountry() != null) {
                    commonValidationService.validatePassportCountry(passport.getPassportCountry(), false);
                }
                if (passport.getPassportNumber() != null) {
                    commonValidationService.validatePassportNumber(passport.getPassportNumber(), false);
                }
            }
        }
        commonValidationService.validateIdNumber(tPersonMyClient.getIdNumber(), false);
        commonValidationService.validateNationality(tPersonMyClient.getNationality1(), true);
        commonValidationService.validateNationality(tPersonMyClient.getNationality2(), false);
        commonValidationService.validateNationality(tPersonMyClient.getNationality3(), false);
        commonValidationService.validateResidence(tPersonMyClient.getResidence(), true);
        commonValidationService.validatePhone(tPersonMyClient.getPhone(), false);
        commonValidationService.validateAddress(tPersonMyClient.getAddress(), false);
        commonValidationService.validateEmail(tPersonMyClient.getEmail(), false);
        commonValidationService.validateOccupation(tPersonMyClient.getOccupation(), true);
        commonValidationService.validateEmployerName(tPersonMyClient.getEmployerName(), false);
        commonValidationService.validateAddress(tPersonMyClient.getEmployerAddressId(), false);
        commonValidationService.validatePhone(tPersonMyClient.getEmployerPhoneId(), false);
        commonValidationService.validateTPersonIdentification(tPersonMyClient.getIdentification(), false);
        commonValidationService.validateDeceased(tPersonMyClient.getDeceased(), false);
        commonValidationService.validateTaxNumber(tPersonMyClient.getTaxNumber(), false);
        commonValidationService.validateTaxRegNumber(tPersonMyClient.getTaxRegNumber(), false);
        commonValidationService.validateSourceOfWealth(tPersonMyClient.getSourceOfWealth(), false);
        commonValidationService.validateComments(tPersonMyClient.getComments(), false);
    }

    private void validateResidence(String residence, boolean required) {
        if (required && residence.trim().isEmpty()) {
            logger.error("Residence is required but it is null.");
            throw new ValidationErrorException("Residence is required but it is null.");
        }
        if (residence != null) {
            enumValidationService.validateCountryCode(residence);
        }
    }

    private void validateOccupation(String occupation, boolean required) {
        if (required && occupation.trim().isEmpty()) {
            logger.error("Occupation is required but it is null.");
            throw new ValidationErrorException("Occupation is required but it is null.");
        }
    }

    private void validateEmployerName(String employerName, boolean required) {
        if (required && employerName.trim().isEmpty()) {
            logger.error("Employer name is required but it is null.");
            throw new ValidationErrorException("Employer name is required but it is null.");
        }
    }

    private void validateEmployerPhoneId(TPhone employerPhoneId, boolean required) {
        commonValidationService.validatePhone(employerPhoneId, required);
    }

    private void validateDeceased(Deceased deceased, boolean required) {
        commonValidationService.validateDeceased(deceased, required);
    }

    private void validateIdentification(TPersonIdentification identification, boolean required) {
        commonValidationService.validateTPersonIdentification(identification, required);
    }

    private void validateSourceOfWealth(String sourceOfWealth, boolean required) {
        if (required && sourceOfWealth.trim().isEmpty()) {
            logger.error("Source of wealth is required but it is null.");
            throw new ValidationErrorException("Source of wealth is required but it is null.");
        }
    }

    private void validateIdNumber(String idNumber, boolean required) {
        if (required && idNumber.trim().isEmpty()) {
            logger.error("Id number is required but it is null.");
            throw new ValidationErrorException("Id number is required but it is null.");
        }
    }

    private void validateEmployerAddressId(TAddress employerAddressId, boolean required) {
        commonValidationService.validateAddress(employerAddressId, required);
    }

    private void validateAlias(String alias, boolean required) {
        commonValidationService.validateStringCharacterLimits(alias, required, 100, "Alias");
    }

    private void validateMothersName(String mothersName, boolean required) {
        commonValidationService.validateStringCharacterLimits(mothersName, required, 100, "Mothers name");
    }

    private void validatePrefix(String prefix, boolean required) {
        commonValidationService.validateStringCharacterLimits(prefix, required, 100, "Prefix");
    }

    private void validateLastName(String lastName, boolean required) {
        commonValidationService.validateStringCharacterLimits(lastName, required, 100, "Last name");
        if (!lastName.equals(lastName.toUpperCase())) {
            logger.error("Last name is required in Upper Case. but its in like : {}", lastName);
            throw new ValidationErrorException("Last name is required in Upper Case. but its in like : " + lastName);
        }
    }

    private void validateMiddleName(String middleName, boolean required) {
        commonValidationService.validateStringCharacterLimits(middleName, required, 100, "Middle name");
    }

    private void validateFirstName(String firstName, boolean required) {
        commonValidationService.validateStringCharacterLimits(firstName, required, 100, "First name");
    }

    private void validateTitle(String title, boolean required) {
        commonValidationService.validateStringCharacterLimits(title, required, 30, "Title");
    }


}
