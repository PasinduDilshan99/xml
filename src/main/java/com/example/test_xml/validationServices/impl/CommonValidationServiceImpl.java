package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.xmlDto.*;
import com.example.test_xml.validationServices.CommonValidationService;
import com.example.test_xml.validationServices.EnumValidationService;
import com.example.test_xml.util.RegexPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonValidationServiceImpl implements CommonValidationService {

    private final Logger logger = LoggerFactory.getLogger(CommonValidationServiceImpl.class);

    private final EnumValidationService enumValidationService;

    @Autowired
    public CommonValidationServiceImpl(EnumValidationService enumValidationService) {
        this.enumValidationService = enumValidationService;
    }

    @Override
    public void validateGender(String gender, boolean required) {
        if (required) {
            if (gender.trim().isEmpty() || gender.trim() == null) {
                logger.error("Gender value is null. But its required.");
                throw new ValidationErrorException("Gender value is null. But its required.");
            }
        }
        if (gender != null && !gender.isEmpty()) {
            enumValidationService.validateGenderType(gender);
        }
    }

    @Override
    public void validateBirthDate(String birthdate, boolean required) {
        if (required) {
            if (birthdate.trim().isEmpty() || birthdate.trim() == null) {
                logger.error("Birthday value is null. But its required.");
                throw new ValidationErrorException("Birthday value is null. But its required.");
            }
        }
    }

    @Override
    public void validateBirthPlace(String birthPlace, boolean required) {
        if (required) {
            if (birthPlace.trim().isEmpty() || birthPlace.trim() == null) {
                logger.error("birthPlace value is null. But its required.");
                throw new ValidationErrorException("birthPlace value is null. But its required.");
            }
        }
    }

    @Override
    public void validateSsn(String ssn, boolean required) {
        if (required) {
            if (ssn.trim().isEmpty() || ssn.trim() == null) {
                logger.error("Ssn value is null. But its required.");
                throw new ValidationErrorException("Ssn value is null. But its required.");
            }
        }
        if (ssn != null && !ssn.isEmpty()) {
            if (!ssn.matches(RegexPattern.OLD_NIC_PATTERN) && !ssn.matches(RegexPattern.NEW_NIC_PATTERN)) {
                logger.error("Invalid SSN(NIC) format.Requested NIC : {}",ssn);
                throw new ValidationErrorException("Invalid SSN(NIC) format.Requested NIC : "+ ssn);
            }
        }
    }

    @Override
    public void validatePassportNumber(String passportNumber, boolean required) {
        if (required && (passportNumber == null || passportNumber.trim().isEmpty())) {
            logger.error("Passport number is required but missing.");
            throw new IllegalArgumentException("Passport number is required but missing.");
        }

        if (passportNumber != null && !passportNumber.isEmpty()) {
            if (!passportNumber.matches(RegexPattern.PASSPORT_NUMBER_PATTERN)) {
                logger.error("Invalid passport number format.");
                throw new IllegalArgumentException("Invalid passport number format.");
            }
        }
    }


    @Override
    public void validatePassportCountry(String passportCountry, boolean required) {
        if (required) {
            if (passportCountry.trim().isEmpty() || passportCountry.trim() == null) {
                logger.error("Passport country value is null. But its required.");
                throw new ValidationErrorException("Passport country value is null. But its required.");
            }
        }
        if (passportCountry != null && !passportCountry.isEmpty()) {
            enumValidationService.validateCountryCode(passportCountry);
        }

    }

    @Override
    public void validateComments(String comments, boolean required) {
        if (required && (comments == null || comments.trim().isEmpty())) {
            logger.error("Comments are required but missing.");
            throw new IllegalArgumentException("Comments are required but missing.");
        }

        if (comments != null && !comments.isEmpty()) {
            if (comments.length() > 4000) {
                logger.error("Comments must be less than 4000 characters.");
                throw new IllegalArgumentException("Comments must be less than 4000 characters.");
            }
        }
    }


    @Override
    public void validateTaxRegNumber(String taxRegNumber, boolean required) {
        if (required && (taxRegNumber == null || taxRegNumber.trim().isEmpty())) {
            logger.error("Tax register number is required but missing.");
            throw new IllegalArgumentException("Tax register number is required but missing.");
        }
    }

    @Override
    public void validateTaxNumber(String taxNumber, boolean required) {
        if (required && (taxNumber == null || taxNumber.trim().isEmpty())) {
            logger.error("Tax number is required but missing.");
            throw new IllegalArgumentException("Tax number is required but missing.");
        }
    }

    @Override
    public void validateAddress(TAddress address, boolean required) {
        if (required && address == null) {
            logger.error("Address details are required but missing.");
            throw new ValidationErrorException("Address details are required but missing.");
        }

        if (required) {
            if (address.getAddressType() == null) {
                logger.error("Address type is required.");
                throw new ValidationErrorException("Address type is required.");
            }
            if (address.getAddress() == null || address.getAddress().isEmpty()) {
                logger.error("Address is required.");
                throw new ValidationErrorException("Address is required.");
            }
            if (address.getCity() == null || address.getCity().isEmpty()) {
                logger.error("City is required.");
                throw new ValidationErrorException("City is required.");
            }
            if (address.getCountryCode() == null) {
                logger.error("Country code is required.");
                throw new ValidationErrorException("Country code is required.");
            }
        }

        if (address != null) {
            if (address.getAddressType() != null) {
                enumValidationService.validateContactType(address.getAddressType().toString());
            }
            if (address.getAddress() != null && address.getAddress().length() > 100) {
                logger.error("Invalid address format.");
                throw new ValidationErrorException("Invalid address format.");
            }
            if (address.getCity() != null) {
                logger.error("Invalid city format.");
                throw new ValidationErrorException("Invalid city format.");
            }
            if (address.getZip() != null && address.getZip().length() > 10) {
                logger.error("Zip code must be at most 10 characters.");
                throw new ValidationErrorException("Zip code must be at most 10 characters.");
            }
            if (address.getCountryCode().toString() != null) {
                enumValidationService.validateCountryCode(address.getCountryCode().toString());
            }
            if (address.getState() != null && address.getState().length() > 255) {
                logger.error("State must be at most 255 characters.");
                throw new ValidationErrorException("State must be at most 255 characters.");
            }
            if (address.getComments() != null && address.getComments().length() > 4000) {
                logger.error("Comments must be less than 4000 characters.");
                throw new ValidationErrorException("Comments must be less than 4000 characters.");
            }
        }
    }


    @Override
    public void validateEmail(String email, boolean required) {
        if (required && (email == null || email.trim().isEmpty())) {
            logger.error("Email is required but missing.");
            throw new ValidationErrorException("Email is required but missing.");
        }

        if (email != null && !email.isEmpty()) {
            if (!email.matches(RegexPattern.EMAIL_ADDRESS_PATTERN)) {
                logger.error("Invalid email format.");
                throw new ValidationErrorException("Invalid email format.");
            }
        }
    }


    @Override
    public void validatePhone(TPhone phone, boolean required) {
        if (required && phone == null) {
            logger.error("Phone details are required but missing.");
            throw new ValidationErrorException("Phone details are required but missing.");
        }
        if (required) {
            if (phone.getTphContactType() == null) {
                logger.error("Contact type is required.");
                throw new ValidationErrorException("Contact type is required.");
            }
            if (phone.getTphCommunicationType() == null) {
                logger.error("Communication type is required.");
                throw new IllegalArgumentException("Communication type is required.");
            }
            if (phone.getTphCountryPrefix() == null || phone.getTphCountryPrefix().length() > 3) {
                logger.error("Invalid country prefix. Cannot be greater than 4 characters.");
                throw new IllegalArgumentException("Invalid country prefix. Cannot be greater than 4 characters.");
            }
            if (phone.getTphNumber() == null || phone.getTphNumber().isEmpty()) {
                logger.error("Phone number is required.");
                throw new IllegalArgumentException("Phone number is required.");
            }
        }

        if (phone != null) {

            if (phone.getTphContactType().toString() != null && !phone.getTphContactType().toString().isEmpty()) {
                enumValidationService.validateContactType(phone.getTphContactType().toString());
            }

            if (phone.getTphCommunicationType().toString() != null && !phone.getTphCommunicationType().toString().isEmpty()) {
                enumValidationService.validateCommunicationType(phone.getTphCommunicationType().toString());
            }

            if (!phone.getTphNumber().matches(RegexPattern.MOBILE_NUMBER_PATTERN)) {
                logger.error("Phone number must contain only digits.");
                throw new IllegalArgumentException("Phone number must contain only digits.");
            }

            if (phone.getTphNumber().length() == 10 && !phone.getTphNumber().startsWith("0")) {
                logger.error("Local numbers must start with '0' and have exactly 10 digits.");
                throw new IllegalArgumentException("Local numbers must start with '0' and have exactly 10 digits.");
            }

            if (phone.getTphExtension() != null && phone.getTphExtension().length() > 10) {
                logger.error("Extension must be at most 10 characters.");
                throw new IllegalArgumentException("Extension must be at most 10 characters.");
            }

            if (phone.getComments() != null && phone.getComments().length() > 4000) {
                logger.error("Comments must be less than 4000 characters.");
                throw new IllegalArgumentException("Comments must be less than 4000 characters.");
            }
        }
    }


    @Override
    public void validateNationality(String nationality, boolean required) {
        validateStringNullable(nationality, required, "Nationality");
        if (nationality != null) {
            enumValidationService.validateCountryCode(nationality);
        }
    }

    @Override
    public void validateDeceased(Deceased deceased, boolean required) {
        if (required && deceased == null) {
            logger.error("Deceased is null. but its required.");
            throw new ValidationErrorException("Deceased is null. but its required.");
        }
    }

    @Override
    public void validateTPersonIdentification(TPersonIdentification identification, boolean required) {
        if (identification == null) {
            return;
        }
        if (required && identification.getType().toString() == null) {
            logger.error("Identification type is null. but its required.");
            throw new ValidationErrorException("Identification type is null. but its required.");
        }
        if (required && identification.getNumber() == null) {
            logger.error("Identification number is null. but its required.");
            throw new ValidationErrorException("Identification number is null. but its required.");
        }
        if (required && identification.getIssueCountry().toString() == null) {
            logger.error("Identification issue country is null. but its required.");
            throw new ValidationErrorException("Identification issue country is null. but its required.");
        }

        if (identification.getType() != null) {
            enumValidationService.validateIdentifierType(identification.getType().toString());
        }
        if (identification.getIssueCountry() != null) {
            enumValidationService.validateCountryCode(identification.getIssueCountry().toString());
        }
        if (identification.getComments() != null && identification.getComments().length() > 4000) {
            logger.error("Comments must be less than 4000 characters.");
            throw new IllegalArgumentException("Comments must be less than 4000 characters.");
        }
    }

    @Override
    public void validateStringCharacterLimits(String paragraph, boolean required, int characterLimit, String name) {

        validateStringNullable(paragraph, required, name);

        if (paragraph != null && paragraph.length() > characterLimit) {
            logger.error("{} exceeds the character limit of {}", name, characterLimit);
            throw new ValidationErrorException(name + " exceeds the character limit of " + characterLimit);
        }
    }

    @Override
    public void validateStringNullable(String paragraph, boolean required, String name) {
        if (required && (paragraph == null || paragraph.isEmpty())) {
            logger.error("{} is required but is empty or null.", name);
            throw new ValidationErrorException(name + " is required but is empty or null.");
        }
    }

    @Override
    public void validateCents(String amount, boolean required, String name) {
        validateStringNullable(amount, required, name);
        boolean isValid = amount.length() >= 3 && amount.charAt(amount.length() - 3) == '.';
        if (!isValid) {
            logger.error("{} value need cents value. but it : {}", name, amount);
            throw new ValidationErrorException(name + " value need cents value. but it: " + amount);
        }
    }

    @Override
    public void validateSourceOfWealth(String sourceOfWealth, boolean required) {
        validateStringCharacterLimits(sourceOfWealth, required, 255, "Source of Wealth");
    }

    @Override
    public void validateEmployerName(String employerName, boolean required) {
        validateStringCharacterLimits(employerName, required, 255, "Employer name");
    }

    @Override
    public void validateOccupation(String occupation, boolean required) {
        validateStringCharacterLimits(occupation, required, 255, "Occupation");
    }

    @Override
    public void validateResidence(String residence, boolean required) {
        validateStringNullable(residence, required, "Residence");
        if (residence != null) {
            enumValidationService.validateCountryCode(residence);
        }
    }

    @Override
    public void validateIdNumber(String idNumber, boolean required) {
        validateStringCharacterLimits(idNumber, required, 255, "Id number");
    }

    @Override
    public void validateAlias(String alias, boolean required) {
        validateStringCharacterLimits(alias, required, 100, "Alias");
    }

    @Override
    public void validatePassport(Passport passport, boolean required) {
        if (passport != null) {
            validatePassportNumber(passport.getPassportNumber(), required);
            validatePassportCountry(passport.getPassportCountry(), required);
        }
    }

    @Override
    public void validateMothersName(String mothersName, boolean required) {
        validateStringCharacterLimits(mothersName, required, 100, "Mothers name");
    }

    @Override
    public void validateLastName(String lastName, boolean required) {
        validateStringCharacterLimits(lastName, required, 100, "Last name");
        if (lastName != null) {
            validateUpperCases(lastName, required, "Last name");
        }
    }

    private void validateUpperCases(String paragraph, boolean required, String name) {
        validateStringNullable(paragraph, required, name);
        if (!paragraph.equals(paragraph.toUpperCase())) {
            logger.error("{} must be capitalize all letters. but request : {}", name, paragraph);
            throw new ValidationErrorException(name + " must be capitalize all letters. but request : " + paragraph);
        }
    }

    @Override
    public void validatePrefix(String prefix, boolean required) {
        validateStringCharacterLimits(prefix, required, 100, "Prefix");
    }

    @Override
    public void validateMiddleName(String middleName, boolean required) {
        validateStringCharacterLimits(middleName, required, 100, "Middle name");
    }

    @Override
    public void validateTitle(String title, boolean required) {
        validateStringCharacterLimits(title, required, 30, "Title");
    }

    @Override
    public void validateFirstName(String firstName, boolean required) {
        validateStringCharacterLimits(firstName, required, 100, "First name");
    }

    @Override
    public void validateFundsCode(String fundsCode, boolean required) {
        validateStringNullable(fundsCode, required, "Funds code");
        if (fundsCode != null) {
            enumValidationService.validateFundsType(fundsCode);
        }
    }

    @Override
    public void validateFundsComment(String fundsComment, String fundsCode, boolean required) {
        validateStringCharacterLimits(fundsComment, required, 255, "Funds comment");
        if (fundsCode.trim().equalsIgnoreCase("other")) {
            validateStringNullable(fundsComment, true, "Funds comment");
        }
    }

    @Override
    public void validateCountry(String country, boolean required) {
        validateStringNullable(country, required, "Country");
        if (country != null) {
            enumValidationService.validateCountryCode(country);
        }
    }

    @Override
    public void validateForeignCurrency(TForeignCurrency foreignCurrency, boolean required) {
        if (required && foreignCurrency == null) {
            logger.error("Foreign currency cannot be null. but its null");
            throw new ValidationErrorException("Foreign currency cannot be null. but its null");
        }
        if (foreignCurrency != null) {
            enumValidationService.validateCurrencyCode(foreignCurrency.getForeignCurrencyCode().toString());
            validateStringNullable(foreignCurrency.getForeignAmount().toString(), true, "Foreign currency amount");
        }
        if (foreignCurrency != null) {
            if (!foreignCurrency.getForeignExchangeRate().toString().equals("1")) {
                logger.error("Foreign exchange rate must be 1.");
                throw new ValidationErrorException("Foreign exchange rate must be 1.");
            }
        }
    }

}
