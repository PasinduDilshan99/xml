package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.validationServices.CommonValidationService;
import com.example.test_xml.validationServices.EnumValidationService;
import com.example.test_xml.validationServices.FromAndToValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FromAndToValidationServiceImpl implements FromAndToValidationService {

    private final Logger logger = LoggerFactory.getLogger(FromAndToValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;

    @Autowired
    public FromAndToValidationServiceImpl(CommonValidationService commonValidationService, EnumValidationService enumValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
    }

    @Override
    public void validateTFromMyClient(TFromMyClient tFromMyClient) {
        commonValidationService.validateFundsCode(tFromMyClient.getFromFundsCode().toString(), true);
        commonValidationService.validateFundsComment(tFromMyClient.getFromFundsComment(), tFromMyClient.getFromFundsCode().toString(), false);
        commonValidationService.validateForeignCurrency(tFromMyClient.getFromForeignCurrency(), false);
        validateTConductor(tFromMyClient.getTConductor(), false);
        validateFromParty(tFromMyClient, true);
        commonValidationService.validateCountry(tFromMyClient.getFromCountry().toString(), true);
    }

    @Override
    public void validateTToMyClient(TToMyClient tToMyClient) {
        commonValidationService.validateFundsCode(tToMyClient.getToFundsCode().toString(), true);
        commonValidationService.validateFundsComment(tToMyClient.getToFundsComment(), tToMyClient.getToFundsCode().toString(), false);
        commonValidationService.validateForeignCurrency(tToMyClient.getToForeignCurrency(), false);
        validateToParty(tToMyClient, true);
        commonValidationService.validateCountry(tToMyClient.getToCountry().toString(), true);
    }

    private void validateToParty(TToMyClient tToMyClient, boolean required) {
        if (required && tToMyClient.getToAccount() == null) {
            logger.error("T to my client cannot be null.");
            throw new ValidationErrorException("T to my client cannot be null.");
        }
    }

    private void validateFromParty(TFromMyClient tFromMyClient, boolean required) {
        if (required && tFromMyClient.getTEntityMyClient() == null && tFromMyClient.getTPersonMyClient() == null) {
            logger.error("T entity my client and t person my client both cannot be null.");
            throw new ValidationErrorException("T entity my client and t person my client both cannot be null.");
        }
    }

    private void validateTConductor(String tConductor, boolean required) {
        if (required && tConductor == null) {
            logger.error("tConductor is null. its required.");
            throw new ValidationErrorException("tConductor is null. its required.");
        }
    }

}
