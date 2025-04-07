package com.example.test_xml.validationServices;

import com.example.test_xml.model.xmlDto.*;

public interface CommonValidationService {

    void validateGender(String gender, boolean required);

    void validateBirthDate(String birthdate, boolean required);

    void validateBirthPlace(String birthPlace, boolean required);

    void validateSsn(String ssn, boolean required);

    void validatePassportNumber(String passportNumber, boolean required);

    void validatePassportCountry(String passportCountry, boolean required);

    void validateComments(String comments, boolean required);

    void validateTaxRegNumber(String taxRegNumber, boolean required);

    void validateTaxNumber(String taxNumber, boolean required);

    void validateAddress(TAddress address, boolean required);

    void validateEmail(String email, boolean required);

    void validatePhone(TPhone phone, boolean required);

    void validateNationality(String nationality1, boolean required);

    void validateDeceased(Deceased deceased, boolean required);

    void validateTPersonIdentification(TPersonIdentification identification, boolean required);

    void validateStringCharacterLimits(String paragraph, boolean required, int characterLimit, String name);

    void validateStringNullable(String paragraph, boolean required, String name);

    void validateCents(String amount, boolean required, String name);

    void validateSourceOfWealth(String sourceOfWealth, boolean required);

    void validateEmployerName(String employerName, boolean required);

    void validateOccupation(String occupation, boolean required);

    void validateResidence(String residence, boolean required);

    void validateIdNumber(String idNumber, boolean required);

    void validateAlias(String alias, boolean required);

    void validatePassport(Passport passport, boolean required);

    void validateMothersName(String mothersName, boolean required);
    
    void validateLastName(String lastName, boolean required);

    void validatePrefix(String prefix, boolean required);

    void validateMiddleName(String middleName, boolean required);

    void validateTitle(String title, boolean required);

    void validateFirstName(String firstName, boolean required);

    void validateFundsCode(String fundsCode, boolean required);

    void validateFundsComment(String fundsComment, String fundsCode,boolean required);

    void validateCountry(String country, boolean required);

    void validateForeignCurrency(TForeignCurrency foreignCurrency, boolean required);
}
