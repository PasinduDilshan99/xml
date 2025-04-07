package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.enums.*;
import com.example.test_xml.validationServices.EnumValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EnumValidationServiceImpl implements EnumValidationService {

    private final Logger logger = LoggerFactory.getLogger(EnumValidationServiceImpl.class);

    @Override
    public void validateAccountPersonRole(String role) {
        for (AccountPersonRoleTypes type : AccountPersonRoleTypes.values()) {
            if (type.name().equalsIgnoreCase(role)) {
                return;
            }
        }
        logger.error("Invalid account person role: {}", role);
        throw new ValidationErrorException("Invalid account person role: " + role);
    }

    @Override
    public void validateAccountStatus(String status) {
        for (AccountStatusTypes statusType : AccountStatusTypes.values()) {
            if (statusType.name().equalsIgnoreCase(status)) {
                return;
            }
        }
        logger.error("Invalid account status: {} ", status);
        throw new ValidationErrorException("Invalid account status: " + status);
    }

    @Override
    public void validateAccountType(String accountType) {
        for (AccountTypes accountTypeEnum : AccountTypes.values()) {
            if (accountTypeEnum.name().equalsIgnoreCase(accountType)) {
                return;
            }
        }
        logger.error("Invalid account type: {} ", accountType);
        throw new ValidationErrorException("Invalid account type: " + accountType);
    }

    @Override
    public void validateCityCode(String cityCode) {
        for (CityCodes city : CityCodes.values()) {
            if (city.name().equalsIgnoreCase(cityCode)) {
                return;
            }
        }
        logger.error("Invalid city code: {}", cityCode);
        throw new ValidationErrorException("Invalid city code: " + cityCode);
    }

    @Override
    public void validateCommunicationType(String communicationType) {
        for (CommunicationTypes type : CommunicationTypes.values()) {
            if (type.name().equalsIgnoreCase(communicationType)) {
                return;
            }
        }
        logger.error("Invalid communication type: {}", communicationType);
        throw new ValidationErrorException("Invalid communication type: " + communicationType);
    }

    @Override
    public void validateConductionType(String conductionType) {
        for (ConductionTypes type : ConductionTypes.values()) {
            if (type.name().equalsIgnoreCase(conductionType)) {
                return;
            }
        }
        logger.error("Invalid conduction type: {}", conductionType);
        throw new ValidationErrorException("Invalid conduction type: " + conductionType);
    }

    @Override
    public void validateContactType(String contactType) {
        for (ContactTypes type : ContactTypes.values()) {
            if (type.name().equalsIgnoreCase(contactType)) {
                return;
            }
        }
        logger.error("Invalid contact type: {}", contactType);
        throw new ValidationErrorException("Invalid contact type: " + contactType);
    }

    @Override
    public void validateCountryCode(String code) {
        for (CountryCodes country : CountryCodes.values()) {
            if (country.name().equalsIgnoreCase(code)) {
                return;
            }
        }
        logger.error("Invalid country code: {}", code);
        throw new ValidationErrorException("Invalid country code: " + code);
    }

    @Override
    public void validateCurrencyCode(String currencyCode) {
        for (Currencies currency : Currencies.values()) {
            if (currency.name().equalsIgnoreCase(currencyCode)) {
                return;
            }
        }
        logger.error("Invalid currency code: {}", currencyCode);
        throw new ValidationErrorException("Invalid currency code: " + currencyCode);
    }

    @Override
    public void validateEntityLegalForm(String legalFormCode) {
        for (EntityLegalFormTypes legalForm : EntityLegalFormTypes.values()) {
            if (legalForm.name().equalsIgnoreCase(legalFormCode)) {
                return;
            }
        }
        logger.error("Invalid entity legal form code: {}", legalFormCode);
        throw new ValidationErrorException("Invalid entity legal form code: " + legalFormCode);
    }

    @Override
    public void validateEntityPersonRole(String roleCode) {
        for (EntityPersonRoleTypes role : EntityPersonRoleTypes.values()) {
            if (role.name().equalsIgnoreCase(roleCode)) {
                return;
            }
        }
        logger.error("Invalid entity person role code: {}", roleCode);
        throw new ValidationErrorException("Invalid entity person role code: " + roleCode);
    }

    @Override
    public void validateFundsType(String fundsTypeCode) {
        for (FundsTypes type : FundsTypes.values()) {
            if (type.name().equalsIgnoreCase(fundsTypeCode)) {
                return;
            }
        }
        logger.error("Invalid funds type code: {}", fundsTypeCode);
        throw new ValidationErrorException("Invalid funds type code: " + fundsTypeCode);
    }

    @Override
    public void validateGenderType(String genderCode) {
        for (GenderTypes gender : GenderTypes.values()) {
            if (gender.name().equalsIgnoreCase(genderCode)) {
                return;
            }
        }
        logger.error("Invalid gender code: {}", genderCode);
        throw new ValidationErrorException("Invalid gender code: " + genderCode);
    }

    @Override
    public void validateIdentifierType(String identifierCode) {
        for (IdentifierTypes identifier : IdentifierTypes.values()) {
            if (identifier.name().equalsIgnoreCase(identifierCode)) {
                return;
            }
        }
        logger.error("Invalid identifier code: {}", identifierCode);
        throw new ValidationErrorException("Invalid identifier code: " + identifierCode);
    }

    @Override
    public void validatePartyType(String partyType) {
        for (PartyTypes type : PartyTypes.values()) {
            if (type.name().equalsIgnoreCase(partyType)) {
                return;
            }
        }
        logger.error("Invalid party type: {}", partyType);
        throw new ValidationErrorException("Invalid party type: " + partyType);
    }

    @Override
    public void validateReportCode(String reportCode) {
        for (ReportCodes code : ReportCodes.values()) {
            if (code.name().equalsIgnoreCase(reportCode)) {
                return;
            }
        }
        logger.error("Invalid report code: {}", reportCode);
        throw new ValidationErrorException("Invalid report code: " + reportCode);
    }

    @Override
    public void validateReportIndicator(String reportIndicator) {
        for (ReportIndicatorTypes indicator : ReportIndicatorTypes.values()) {
            if (indicator.name().equalsIgnoreCase(reportIndicator)) {
                return;
            }
        }
        logger.error("Invalid report indicator: {}", reportIndicator);
        throw new ValidationErrorException("Invalid report indicator: " + reportIndicator);
    }

    @Override
    public void validateSubmissionType(String submissionType) {
        for (SubmissionTypes type : SubmissionTypes.values()) {
            if (type.name().equalsIgnoreCase(submissionType)) {
                return;
            }
        }
        logger.error("Invalid submission type: {}", submissionType);
        throw new ValidationErrorException("Invalid submission type: " + submissionType);
    }

    @Override
    public void validateCity(String city) {
        for (Cities c : Cities.values()) {
            if (c.name().equalsIgnoreCase(city)) {
                return;
            }
        }
        logger.error("Invalid city name: {}", city);
        throw new ValidationErrorException("Invalid city name: {}" + city);
    }

}
