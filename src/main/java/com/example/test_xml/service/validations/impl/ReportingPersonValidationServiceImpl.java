package com.example.test_xml.service.validations.impl;

import com.example.test_xml.model.xmlDto.TPersonRegistrationInReport;
import com.example.test_xml.service.validations.CommonValidationService;
import com.example.test_xml.service.validations.EnumValidationService;
import com.example.test_xml.service.validations.ReportingPersonValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReportingPersonValidationServiceImpl implements ReportingPersonValidationService {

    private final Logger logger = LoggerFactory.getLogger(ReportingPersonValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;

    public ReportingPersonValidationServiceImpl(CommonValidationService commonValidationService, EnumValidationService enumValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
    }

    @Override
    public void validateReportingPerson(TPersonRegistrationInReport reportingPerson) {
        commonValidationService.validateGender(reportingPerson.getGender().toString(), false);
        commonValidationService.validateTitle(reportingPerson.getTitle(), false);
        commonValidationService.validateFirstName(reportingPerson.getFirstName(), true);
        commonValidationService.validateMiddleName(reportingPerson.getMiddleName(), false);
        commonValidationService.validatePrefix(reportingPerson.getPrefix(), false);
        commonValidationService.validateLastName(reportingPerson.getLastName(), true);
        commonValidationService.validateBirthDate(reportingPerson.getBirthdate(), false);
        commonValidationService.validateBirthPlace(reportingPerson.getBirthPlace(), false);
        commonValidationService.validateMothersName(reportingPerson.getMothersName(), false);
        commonValidationService.validateAlias(reportingPerson.getAlias(), false);
        commonValidationService.validateSsn(reportingPerson.getSsn(), false);
        commonValidationService.validatePassport(reportingPerson.getPassport(), false);
        commonValidationService.validateIdNumber(reportingPerson.getIdNumber(), false);
        commonValidationService.validateNationality(reportingPerson.getNationality1(), false);
        commonValidationService.validateNationality(reportingPerson.getNationality2(), false);
        commonValidationService.validateNationality(reportingPerson.getNationality3(), false);
        commonValidationService.validateResidence(reportingPerson.getResidence(), false);
        commonValidationService.validatePhone(reportingPerson.getPhone(), false);
        commonValidationService.validateAddress(reportingPerson.getAddress(), false);
        commonValidationService.validateEmail(reportingPerson.getEmail(), false);
        commonValidationService.validateOccupation(reportingPerson.getOccupation(), false);
        commonValidationService.validateEmployerName(reportingPerson.getEmployerName(), false);
        commonValidationService.validateAddress(reportingPerson.getEmployerAddressId(), false);
        commonValidationService.validatePhone(reportingPerson.getEmployerPhoneId(), false);
        commonValidationService.validateTPersonIdentification(reportingPerson.getIdentification(), false);
        commonValidationService.validateDeceased(reportingPerson.getDeceased(), false);
        commonValidationService.validateTaxNumber(reportingPerson.getTaxNumber(), false);
        commonValidationService.validateTaxRegNumber(reportingPerson.getTaxRegNumber(), false);
        commonValidationService.validateSourceOfWealth(reportingPerson.getSourceOfWealth(), false);
        commonValidationService.validateComments(reportingPerson.getComments(), false);
    }

}
