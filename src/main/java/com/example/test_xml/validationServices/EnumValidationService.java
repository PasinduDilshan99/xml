package com.example.test_xml.validationServices;

public interface EnumValidationService {
    void validateAccountPersonRole(String role);
    void validateAccountStatus(String status);
    void validateAccountType(String accountType);
    void validateCityCode(String cityCode);
    void validateCommunicationType(String communicationType);
    void validateConductionType(String conductionType);
    void validateContactType(String contactType);
    void validateCountryCode(String code);
    void validateCurrencyCode(String currencyCode);
    void validateEntityLegalForm(String legalFormCode);
    void validateEntityPersonRole(String roleCode);
    void validateFundsType(String fundsTypeCode);
    void validateGenderType(String genderCode);
    void validateIdentifierType(String identifierCode);
    void validatePartyType(String partyType);
    void validateReportCode(String reportCode);
    void validateReportIndicator(String reportIndicator);
    void validateSubmissionType(String submissionType);
    void validateCity(String city);
}
