package com.example.test_xml.service.validations.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.xmlDto.pae.TEntityMyClient;
import com.example.test_xml.service.validations.CommonValidationService;
import com.example.test_xml.service.validations.DirectorIdValidationService;
import com.example.test_xml.service.validations.EnumValidationService;
import com.example.test_xml.service.validations.TEntityMyClientValidationService;
import com.example.test_xml.util.RegexPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TEntityMyClientValidationServiceImpl implements TEntityMyClientValidationService {

    private final Logger logger = LoggerFactory.getLogger(TEntityMyClientValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;
    private final DirectorIdValidationService directorIdValidationService;

    @Autowired
    public TEntityMyClientValidationServiceImpl(CommonValidationService commonValidationService,
                                                EnumValidationService enumValidationService,
                                                DirectorIdValidationService directorIdValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
        this.directorIdValidationService = directorIdValidationService;
    }

    @Override
    public void validateTEntityMyClient(TEntityMyClient tEntityMyClient) {
        validateName(tEntityMyClient.getName(), true);
        validateCommercialName(tEntityMyClient.getName(), tEntityMyClient.getCommercialName(), false);
        validateIncorporationLegalForm(tEntityMyClient.getIncorporationLegalForm(), true);
        validateIncorporationNumber(tEntityMyClient.getIncorporationNumber(), false);
        validateBusiness(tEntityMyClient.getBusiness(), true);
        commonValidationService.validatePhone(tEntityMyClient.getPhone(), false);
        commonValidationService.validateAddress(tEntityMyClient.getAddress(), true);
        commonValidationService.validateEmail(tEntityMyClient.getEmail(), false);
        validateUrl(tEntityMyClient.getUrl(), false);
        validateIncorporationState(tEntityMyClient.getIncorporationState(), false);
        validateIncorporationCountryCode(tEntityMyClient.getIncorporationCountryCode(), true);
        directorIdValidationService.validateDirectorId(tEntityMyClient.getDirectorId(), true);
        validateIncorporationDate(tEntityMyClient.getIncorporationDate(), false);
        validateBusinessClosed(tEntityMyClient.getBusinessClosed(), false);
        validateDateBusinessClosed(tEntityMyClient.getDateBusinessClosed(), false);
        commonValidationService.validateTaxNumber(tEntityMyClient.getTaxNumber(), false);
        commonValidationService.validateTaxRegNumber(tEntityMyClient.getTaxRegNumber(), false);
        commonValidationService.validateComments(tEntityMyClient.getComments(), false);
    }

    private void validateCommercialName(String name, String commercialName, boolean required) {
        if (commercialName != null && commercialName.equals(name)) {
            logger.error("Commercial name must be different from the registered name.");
            throw new ValidationErrorException("Commercial name must be different from the registered name.");
        }
        commonValidationService.validateStringCharacterLimits(commercialName, required, 255, "Commercial name");
    }

    private void validateIncorporationNumber(String incorporationNumber, boolean required) {
        if (required) {
            if (incorporationNumber == null || incorporationNumber.isEmpty()) {
                throw new ValidationErrorException("Incorporation number is required.");
            }
            if (incorporationNumber.contains(" ")) {
                throw new ValidationErrorException("Incorporation number should not contain spaces.");
            }
            if (!incorporationNumber.equals(incorporationNumber.toUpperCase())) {
                throw new ValidationErrorException("Incorporation number must be in uppercase.");
            }
            if (!incorporationNumber.matches(RegexPattern.INCORPORATION_NUMBER_PATTERN)) {
                throw new ValidationErrorException("Incorporation number must start with two uppercase letters followed by digits (e.g., PV1234).");
            }
            commonValidationService.validateStringCharacterLimits(incorporationNumber, required, 50, "Incorporation number");
        }

    }

    private void validateBusiness(String business, boolean required) {
        commonValidationService.validateStringCharacterLimits(business, required, 255, "Business");
    }

    private void validateIncorporationLegalForm(String incorporationLegalForm, boolean required) {
        if (required && (incorporationLegalForm == null || incorporationLegalForm.isEmpty())) {
            logger.error("Incorporation legal form is null. but its required.");
            throw new ValidationErrorException("Incorporation legal form is null. but its required.");
        }
        enumValidationService.validateEntityLegalForm(incorporationLegalForm);
    }

    private void validateUrl(String url, boolean required) {
        commonValidationService.validateStringCharacterLimits(url, required, 255, "Url");
    }

    private void validateIncorporationCountryCode(String incorporationCountryCode, boolean required) {
        if (required && (incorporationCountryCode.isEmpty())) {
            logger.error("Incorporation country code is null. but its required.");
            throw new ValidationErrorException("Incorporation country code is null. but its required.");
        }
        enumValidationService.validateCountryCode(incorporationCountryCode);
    }

    private void validateIncorporationState(String incorporationState, boolean required) {
        commonValidationService.validateStringCharacterLimits(incorporationState, required, 255, "Incorporation state");
    }

    private void validateIncorporationDate(String incorporationDate, boolean required) {
        commonValidationService.validateStringNullable(incorporationDate, required, "Incorporation date");
    }

    private void validateBusinessClosed(Boolean businessClosed, boolean required) {
        commonValidationService.validateStringNullable(businessClosed.toString(), required, "Business closed");
    }

    private void validateDateBusinessClosed(String dateBusinessClosed, boolean required) {
        commonValidationService.validateStringNullable(dateBusinessClosed, required, "Date business closed");
    }

    private void validateName(String name, boolean required) {
        commonValidationService.validateStringCharacterLimits(name, required, 255, "Name");
    }

}
