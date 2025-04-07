package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.InvalidDataErrorException;
import com.example.test_xml.model.xmlDto.Transaction;
import com.example.test_xml.validationServices.CommonValidationService;
import com.example.test_xml.validationServices.TransactionValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionValidationServiceImpl implements TransactionValidationService {

    private final CommonValidationService commonValidationService;
    private final EnumValidationServiceImpl enumValidationService;

    private final Logger logger = LoggerFactory.getLogger(TransactionValidationServiceImpl.class);

    @Value("${minimum.transaction.value}")
    private String minimumLocalValue;

    @Autowired
    public TransactionValidationServiceImpl(CommonValidationService commonValidationService, EnumValidationServiceImpl enumValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
    }

    @Override
    public void validateTransaction(Transaction transaction) {
        validateTransactionNumber(transaction.getTransactionNumber(), true);
        validateInternalRefNumber(transaction.getInternalRefNumber(), false);
        validateTransactionLocation(transaction.getTransactionLocation(), transaction.getTransmodeCode().toString(), false);
        validateTransactionDescription(transaction.getTransactionDescription(), true);
        validateDateTransaction(transaction.getDateTransaction(), true);
        validateTeller(transaction.getTeller(), false);
        validateAuthorized(transaction.getAuthorized(), false);
        validateLateDeposit(transaction.getLateDeposit(), false);
        validateDatePosting(transaction.getDatePosting(), false);
        validateValueDate(transaction.getValueDate(), false);
        validateTransmodeCode(transaction.getTransmodeCode().toString(), true);
        validateTransmodeComment(transaction.getTransmodeComment(), transaction.getTransmodeCode().toString(), false);
        validateAmountLocal(transaction.getAmountLocal(), true);
        validateGoodServices(transaction.getGoodsServices(), false);
        commonValidationService.validateComments(transaction.getComments(), false);
    }

    private void validateAmountLocal(BigDecimal amountLocal, boolean required) {
        commonValidationService.validateStringNullable(amountLocal.toString(), required, "Amount local");
        if (required && amountLocal.compareTo(BigDecimal.ZERO) <= 0) {
            logger.error("Transaction amount is less than or equal to 0. Requested value is: {}", amountLocal);
            throw new InvalidDataErrorException("Transaction amount must be greater than 0. Requested value is: " + amountLocal);
        }

        if (required && amountLocal.compareTo(BigDecimal.valueOf(Long.parseLong(minimumLocalValue))) < 0) {
            logger.error("Transaction amount is less than {}. Requested value is: {}", minimumLocalValue, amountLocal);
            throw new InvalidDataErrorException("Transaction amount must be at least " + minimumLocalValue + ". Requested value is: " + amountLocal);
        }
    }

    private void validateGoodServices(String goodsServices, boolean required) {
        commonValidationService.validateStringNullable(goodsServices, required, "Good services");
    }

    private void validateTransmodeComment(String transmodeComment, String transmodeCode, boolean required) {
        commonValidationService.validateStringCharacterLimits(transmodeComment, required, 50, "Transmode comment");
        if (transmodeCode.equalsIgnoreCase("other")) {
            commonValidationService.validateStringNullable(transmodeComment, true, "Transmode comment");
        }
    }

    private void validateTransmodeCode(String transmodeCode, boolean required) {
        commonValidationService.validateStringNullable(transmodeCode, required, "Transmode code");
        enumValidationService.validateConductionType(transmodeCode);
    }

    private void validateValueDate(String valueDate, boolean required) {
        commonValidationService.validateStringNullable(valueDate, required, "Value date");
    }

    private void validateDatePosting(String datePosting, boolean required) {
        commonValidationService.validateStringNullable(datePosting, required, "Date posting");
    }

    private void validateLateDeposit(Boolean lateDeposit, boolean required) {
//        commonValidationService.validateStringNullable(lateDeposit.toString(), required, "Late deposit");
    }

    private void validateAuthorized(String authorized, boolean required) {
        commonValidationService.validateStringCharacterLimits(authorized, required, 50, "Authorized");
    }

    private void validateTeller(String teller, boolean required) {
        commonValidationService.validateStringCharacterLimits(teller, required, 50, "Teller");
    }

    private void validateDateTransaction(String dateTransaction, boolean required) {
        commonValidationService.validateStringNullable(dateTransaction, required, "Date transaction");
    }

    private void validateTransactionDescription(String transactionDescription, boolean required) {
        commonValidationService.validateStringCharacterLimits(transactionDescription, required, 4000, "Transaction description");
    }

    private void validateTransactionLocation(String transactionLocation, String transmodeCode, boolean required) {
        commonValidationService.validateStringCharacterLimits(transactionLocation, required, 255, "Transaction location");
        if (transmodeCode.equalsIgnoreCase("branch")) {
            commonValidationService.validateStringNullable(transactionLocation, required, "Transaction location");
        }
    }

    private void validateInternalRefNumber(String internalRefNumber, boolean required) {
        commonValidationService.validateStringCharacterLimits(internalRefNumber, required, 50, "Internal ref number");
    }

    private void validateTransactionNumber(String transactionNumber, boolean required) {
        commonValidationService.validateStringCharacterLimits(transactionNumber, required, 50, "Transaction number");
    }
}
