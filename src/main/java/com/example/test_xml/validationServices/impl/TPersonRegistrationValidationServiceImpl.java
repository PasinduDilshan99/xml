package com.example.test_xml.validationServices.impl;

import com.example.test_xml.model.xmlDto.TPersonRegistrationInReport;
import com.example.test_xml.validationServices.CommonValidationService;
import com.example.test_xml.validationServices.EnumValidationService;
import com.example.test_xml.validationServices.TPersonRegistrationValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TPersonRegistrationValidationServiceImpl implements TPersonRegistrationValidationService {

    private final Logger logger = LoggerFactory.getLogger(TPersonRegistrationValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;

    @Autowired
    public TPersonRegistrationValidationServiceImpl(CommonValidationService commonValidationService, EnumValidationService enumValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
    }

    @Override
    public void validateTPersonRegistration(TPersonRegistrationInReport tPersonRegistrationInReport) {
        commonValidationService.validateGender(tPersonRegistrationInReport.getGender().toString(), false);
        commonValidationService.validateTitle(tPersonRegistrationInReport.getTitle(), false);
        commonValidationService.validateFirstName(tPersonRegistrationInReport.getFirstName(), true);
        commonValidationService.validateMiddleName(tPersonRegistrationInReport.getMiddleName(), false);
        commonValidationService.validatePrefix(tPersonRegistrationInReport.getPrefix(), false);
        commonValidationService.validateLastName(tPersonRegistrationInReport.getLastName(), true);
        commonValidationService.validateBirthDate(tPersonRegistrationInReport.getBirthdate(), false);
        commonValidationService.validateBirthPlace(tPersonRegistrationInReport.getBirthPlace(), false);
        commonValidationService.validateMothersName(tPersonRegistrationInReport.getMothersName(), false);
        commonValidationService.validateAlias(tPersonRegistrationInReport.getAlias(), false);
        commonValidationService.validateSsn(tPersonRegistrationInReport.getSsn(), false);
        commonValidationService.validatePassport(tPersonRegistrationInReport.getPassport(), false);
        commonValidationService.validateIdNumber(tPersonRegistrationInReport.getIdNumber(), false);
        commonValidationService.validateNationality(tPersonRegistrationInReport.getNationality1(), false);
        commonValidationService.validateNationality(tPersonRegistrationInReport.getNationality2(), false);
        commonValidationService.validateNationality(tPersonRegistrationInReport.getNationality3(), false);
        commonValidationService.validateResidence(tPersonRegistrationInReport.getResidence(), false);
        commonValidationService.validatePhone(tPersonRegistrationInReport.getPhone(), false);
        commonValidationService.validateAddress(tPersonRegistrationInReport.getAddress(), false);
        commonValidationService.validateEmail(tPersonRegistrationInReport.getEmail(), false);
        commonValidationService.validateOccupation(tPersonRegistrationInReport.getOccupation(), false);
        commonValidationService.validateEmployerName(tPersonRegistrationInReport.getEmployerName(), false);
        commonValidationService.validateAddress(tPersonRegistrationInReport.getEmployerAddressId(), false);
        commonValidationService.validatePhone(tPersonRegistrationInReport.getEmployerPhoneId(), false);
        commonValidationService.validateTPersonIdentification(tPersonRegistrationInReport.getIdentification(), false);
        commonValidationService.validateDeceased(tPersonRegistrationInReport.getDeceased(), false);
        commonValidationService.validateTaxNumber(tPersonRegistrationInReport.getTaxNumber(), false);
        commonValidationService.validateTaxRegNumber(tPersonRegistrationInReport.getTaxRegNumber(), false);
        commonValidationService.validateSourceOfWealth(tPersonRegistrationInReport.getSourceOfWealth(), false);
        commonValidationService.validateComments(tPersonRegistrationInReport.getComments(), false);
    }

}
