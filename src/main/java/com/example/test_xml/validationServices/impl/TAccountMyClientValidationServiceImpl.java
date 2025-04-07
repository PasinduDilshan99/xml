package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.enums.AccountTypes;
import com.example.test_xml.model.xmlDto.pae.TEntityMyClient;
import com.example.test_xml.model.xmlDto.pae.TPersonMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;
import com.example.test_xml.util.RegexPattern;
import com.example.test_xml.validationServices.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TAccountMyClientValidationServiceImpl implements TAccountMyClientValidationService {

    private final Logger logger = LoggerFactory.getLogger(TAccountMyClientValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;
    private final TEntityMyClientValidationService tEntityMyClientValidationService;
    private final TPersonMyClientValidation tPersonMyClientValidation;

    @Autowired
    public TAccountMyClientValidationServiceImpl(CommonValidationService commonValidationService,
                                                 EnumValidationService enumValidationService,
                                                 TEntityMyClientValidationService tEntityMyClientValidationService,
                                                 TPersonMyClientValidation tPersonMyClientValidation) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
        this.tEntityMyClientValidationService = tEntityMyClientValidationService;
        this.tPersonMyClientValidation = tPersonMyClientValidation;
    }

    public void validateTAccountMyClient(ToAccount toAccount) {
        validateInsitutionName(toAccount.getInstitutionName(), true);
        validateSwift(toAccount.getSwift(), true);
        validateNonBankInsitution(toAccount.getNonBankInstitution(), false);
        validateBranch(toAccount.getBranch(), true);
        validateAccount(toAccount.getAccount(), true);
        validateCurrencyCode(toAccount.getCurrencyCode().toString(), true);
        validateAccountName(toAccount.getAccountName(), false);
        validateIban(toAccount.getIban(), false);
        validateClientNumber(toAccount.getClientNumber(), false);
        validatePersonalAccountType(toAccount.getPersonalAccountType(), true);
        validateTEntity(toAccount.getTEntity(), false);
        validateSignatory(toAccount.getSignatory(), false);
        validateOpened(toAccount.getOpened(), false);
        validateClosed(toAccount.getClosed(), false);
        validateBalance(toAccount.getBalance(), false);
        validateDateBalance(toAccount.getDateBalance(), false);
        validateStatusCode(toAccount.getStatusCode(), true);
        validateBeneficiary(toAccount.getBeneficiary(), false);
        validateBeneficiaryComment(toAccount.getBeneficiaryComment(), false);
        commonValidationService.validateComments(toAccount.getComments(), false);
    }

    private void validateCurrencyCode(String currencyCode, boolean required) {
        commonValidationService.validateStringNullable(currencyCode, required, "Currency code");
        enumValidationService.validateCountryCode(currencyCode);
    }

    private void validateSwift(String swift, boolean required) {
        commonValidationService.validateStringNullable(swift, required, "Swift");

        if (swift != null && !swift.trim().isEmpty()) {
            swift = swift.trim();

            if (swift.matches(RegexPattern.SWIFT_PATTERN)) {
                return;
            } else if (swift.matches(RegexPattern.LANKA_CLEAR_PATTERN)) {
                return;
            } else if (swift.matches(RegexPattern.LANKA_FIN_PATTERN)) {
                return;
            } else {
                throw new ValidationErrorException("Invalid SWIFT, LankaClear, or LankaFIN code format.");
            }
        }
    }

    private void validateNonBankInsitution(Boolean nonBankInstitution, boolean required) {
        commonValidationService.validateStringNullable(nonBankInstitution.toString(), required, "Non bank institution");
    }

    private void validateInsitutionName(String institutionName, boolean required) {
        commonValidationService.validateStringCharacterLimits(institutionName, required, 255, "Insitutution name");
    }

    private void validateBranch(String branch, boolean required) {
        commonValidationService.validateStringCharacterLimits(branch, required, 255, "Branch");
    }

    private void validateAccount(String account, boolean required) {
        commonValidationService.validateStringCharacterLimits(account, required, 50, "Account");
    }

    private void validateAccountName(String accountName, boolean required) {
        commonValidationService.validateStringCharacterLimits(accountName, required, 50, "Account name");
    }

    private void validateIban(String iban, boolean required) {
        commonValidationService.validateStringCharacterLimits(iban, required, 34, "Iban");
    }

    private void validateClientNumber(String clientNumber, boolean required) {
        commonValidationService.validateStringCharacterLimits(clientNumber, required, 30, "Client number");
    }

    private void validatePersonalAccountType(AccountTypes personalAccountType, boolean required) {
        commonValidationService.validateStringNullable(personalAccountType.toString(), required, "Personal account type");
        enumValidationService.validateAccountType(personalAccountType.toString());
    }

    private void validateTEntity(TEntityMyClient tEntity, boolean required) {
        if (required && tEntity == null) {
            logger.error("tEntity is null. but its required");
            throw new ValidationErrorException("tEntity is null. but its required");
        }
        if (tEntity != null) {
            tEntityMyClientValidationService.validateTEntityMyClient(tEntity);
        }
    }

    private void validateSignatory(TPersonMyClient signatory, boolean required) {
        if (required && signatory == null) {
            logger.error("signatory is null. but its required");
            throw new ValidationErrorException("signatory is null. but its required");
        }
        if (signatory != null) {
            tPersonMyClientValidation.validateTPersonMyClient(signatory);
        }
    }

    private void validateOpened(String opened, boolean required) {
        commonValidationService.validateStringNullable(opened, required, "Opened");
    }

    private void validateClosed(String closed, boolean required) {
        commonValidationService.validateStringNullable(closed, required, "Closed");
    }

    private void validateBalance(BigDecimal balance, boolean required) {
        commonValidationService.validateCents(balance.toString(), required, "balance");
        commonValidationService.validateStringNullable(balance.toString(), required, "Balance");
    }

    private void validateDateBalance(String dateBalance, boolean required) {
        commonValidationService.validateStringNullable(dateBalance, required, "Date balance");
    }

    private void validateStatusCode(String statusCode, boolean required) {
        commonValidationService.validateStringNullable(statusCode, required, "Status code");
        enumValidationService.validateAccountStatus(statusCode);
    }

    private void validateBeneficiary(String beneficiary, boolean required) {
        commonValidationService.validateStringCharacterLimits(beneficiary, required, 50, "Beneficiary");
    }

    private void validateBeneficiaryComment(String beneficiaryComment, boolean required) {
        commonValidationService.validateStringCharacterLimits(beneficiaryComment, required, 255, "Beneficiary comment");
    }

}
