package com.example.test_xml.service.validations.impl;

import com.example.test_xml.model.xmlDto.DirectorId;
import com.example.test_xml.model.xmlDto.Passport;
import com.example.test_xml.service.validations.CommonValidationService;
import com.example.test_xml.service.validations.DirectorIdValidationService;
import org.springframework.stereotype.Service;

@Service
public class DirectorIdValidationServiceImpl implements DirectorIdValidationService {

    private final CommonValidationService commonValidationService;

    public DirectorIdValidationServiceImpl(CommonValidationService commonValidationService) {
        this.commonValidationService = commonValidationService;
    }

    @Override
    public void validateDirectorId(DirectorId directorId, boolean required) {
        commonValidationService.validateGender(directorId.getGender().toString(), false);
        commonValidationService.validateTitle(directorId.getTitle(), false);
        commonValidationService.validateFirstName(directorId.getFirstName(), true);
        commonValidationService.validateMiddleName(directorId.getMiddleName(), false);
        commonValidationService.validatePrefix(directorId.getPrefix(), false);
        commonValidationService.validateLastName(directorId.getLastName(), true);
        commonValidationService.validateBirthDate(directorId.getBirthdate(), false);
        commonValidationService.validateBirthPlace(directorId.getBirthPlace(), false);
        commonValidationService.validateMothersName(directorId.getMothersName(), false);
        commonValidationService.validateAlias(directorId.getAlias(), false);
        commonValidationService.validateSsn(directorId.getSsn(), false);
        for (Passport passport : directorId.getPassports()) {
            commonValidationService.validatePassport(passport, false);
        }
        commonValidationService.validateIdNumber(directorId.getIdNumber(), false);
        commonValidationService.validateNationality(directorId.getNationality1(), false);
        commonValidationService.validateNationality(directorId.getNationality2(), false);
        commonValidationService.validateNationality(directorId.getNationality3(), false);
        commonValidationService.validateResidence(directorId.getResidence(), false);
        commonValidationService.validatePhone(directorId.getPhone(), false);
        commonValidationService.validateAddress(directorId.getAddress(), false);
        commonValidationService.validateEmail(directorId.getEmail(), false);
        commonValidationService.validateOccupation(directorId.getOccupation(), false);
        commonValidationService.validateEmployerName(directorId.getEmployerName(), false);
        commonValidationService.validateAddress(directorId.getEmployerAddressId(), false);
        commonValidationService.validatePhone(directorId.getEmployerPhoneId(), false);
        commonValidationService.validateTPersonIdentification(directorId.getIdentification(), false);
        commonValidationService.validateDeceased(directorId.getDeceased(), false);
        commonValidationService.validateTaxNumber(directorId.getTaxNumber(), false);
        commonValidationService.validateTaxRegNumber(directorId.getTaxRegNumber(), false);
        commonValidationService.validateSourceOfWealth(directorId.getSourceOfWealth(), false);
        commonValidationService.validateComments(directorId.getComments(), false);
    }
}
