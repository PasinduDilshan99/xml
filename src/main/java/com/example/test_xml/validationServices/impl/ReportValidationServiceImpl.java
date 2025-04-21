package com.example.test_xml.validationServices.impl;

import com.example.test_xml.exception.ValidationErrorException;
import com.example.test_xml.model.enums.Currencies;
import com.example.test_xml.model.enums.ReportCodes;
import com.example.test_xml.model.enums.SubmissionTypes;
import com.example.test_xml.model.xmlDto.Report;
import com.example.test_xml.model.xmlDto.ReportIndicator;
import com.example.test_xml.model.xmlDto.TPersonRegistrationInReport;
import com.example.test_xml.model.xmlDto.Transaction;
import com.example.test_xml.validationServices.CommonValidationService;
import com.example.test_xml.validationServices.EnumValidationService;
import com.example.test_xml.validationServices.ReportValidationService;
import com.example.test_xml.validationServices.TPersonRegistrationValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportValidationServiceImpl implements ReportValidationService {

    private final Logger logger = LoggerFactory.getLogger(ReportValidationServiceImpl.class);

    private final CommonValidationService commonValidationService;
    private final EnumValidationService enumValidationService;
    private final TPersonRegistrationValidationService tPersonRegistrationValidationService;

    @Autowired
    public ReportValidationServiceImpl(CommonValidationService commonValidationService,
                                       EnumValidationService enumValidationService,
                                       TPersonRegistrationValidationService tPersonRegistrationValidationService) {
        this.commonValidationService = commonValidationService;
        this.enumValidationService = enumValidationService;
        this.tPersonRegistrationValidationService = tPersonRegistrationValidationService;
    }

    @Override
    public void validateReport(Report report) {
        validateRentityId(report.getRentityId(), true); //
        validateRentityBranch(report.getRentityBranch(), false);
        validateSubmissionCode(report.getSubmissionCode(), true); //
        validateReportCode(report.getReportCode(), true); //
        validateEntityReference(report.getEntityReference(), false);
        validateFiuRefNumber(report.getFiuRefNumber(), false);
        validateSubmissionDate(report.getSubmissionDate(), true); //
        validateCurrencyCodeLocal(report.getCurrencyCodeLocal(), true); //
        validateReportingPerson(report.getReportingPerson(), false);
        commonValidationService.validateAddress(report.getLocation(), false);
        validateReason(report.getReason(), false);
        validateAction(report.getAction(), false);
        validateTransactions(report.getTransaction(), true); //
        validateReportIndicator(report.getReportIndicator(), true); //
    }

    private void validateRentityId(Integer rentityId, boolean required) {
        commonValidationService.valdiateInteger(rentityId, required);
    }

    private void validateRentityBranch(String rentityBranch, boolean required) {
        commonValidationService.validateStringCharacterLimits(rentityBranch, required, 255, "Rentity Branch");
    }

    private void validateSubmissionCode(SubmissionTypes submissionCode, boolean required) {
        commonValidationService.validateEnumNullable(submissionCode.toString(), "Submission Code", required);
        enumValidationService.validateSubmissionType(submissionCode.toString());

        // validate submission type must be E
        if (!submissionCode.equals(SubmissionTypes.E)) {
            logger.error("Submission code is {}.but its need to be E.", submissionCode);
            throw new ValidationErrorException("Submission code is " + submissionCode + " .but its need to be E.");
        }
    }

    private void validateReportCode(ReportCodes reportCodes, boolean required) {
        commonValidationService.validateEnumNullable(reportCodes.toString(), "Report Code", required);
        enumValidationService.validateReportCode(reportCodes.toString());

        // validate Report type must be EFT
        if (!reportCodes.equals(ReportCodes.EFT)) {
            logger.error("Report code is {}.but its need to be EFT.", reportCodes);
            throw new ValidationErrorException("Report code is " + reportCodes + " .but its need to be EFT.");
        }
    }

    private void validateEntityReference(String entityReference, boolean required) {
        commonValidationService.validateStringCharacterLimits(entityReference, required, 255, "Entity reference");
    }

    private void validateFiuRefNumber(String fiuRefNumber, boolean required) {
        commonValidationService.validateStringCharacterLimits(fiuRefNumber, required, 255, "Fiu ref number");

    }

    private void validateSubmissionDate(String submissionDate, boolean required) {
        commonValidationService.validateDateFormat(submissionDate, required, "Submission date");
        commonValidationService.validateDateIsInPast(submissionDate, "Submission date");
    }

    private void validateCurrencyCodeLocal(Currencies currencyCodeLocal, boolean required) {
        commonValidationService.validateEnumNullable(currencyCodeLocal.toString(), "Currency Code", required);
        enumValidationService.validateCurrencyCode(currencyCodeLocal.toString());

        // validate currency code must be LK
        if (!currencyCodeLocal.equals(Currencies.LKR)) {
            logger.error("Currency code is {}.but its need to be LKR.", currencyCodeLocal);
            throw new ValidationErrorException("Currency code is " + currencyCodeLocal + " .but its need to be LKR.");
        }
    }

    private void validateReportingPerson(TPersonRegistrationInReport reportingPerson, boolean required) {
        if (required && reportingPerson == null) {
            logger.error("Reporting person is null. but its required.");
            throw new ValidationErrorException("Reporting person is null. but its required.");
        }
        if (reportingPerson != null) {
            tPersonRegistrationValidationService.validateTPersonRegistration(reportingPerson);
        }
    }

    private void validateReason(String reason, boolean required) {
        commonValidationService.validateStringCharacterLimits(reason, required, 4000, "Reason");
    }

    private void validateAction(String action, boolean required) {
        commonValidationService.validateStringCharacterLimits(action, required, 4000, "Action");
    }

    private void validateTransactions(List<Transaction> transaction, boolean required) {
        if (required && transaction.isEmpty()) {
            logger.error("Transaction is null. but its required.");
            throw new ValidationErrorException("Transaction is null. but its required.");
        }
    }

    private void validateReportIndicator(ReportIndicator reportIndicator, boolean required) {
        if (required && reportIndicator.getIndicator() == null) {
            logger.error("Reporting indicator is null. but its required.");
            throw new ValidationErrorException("Reporting indicator is null. but its required.");
        }
        if (reportIndicator != null) {
            enumValidationService.validateReportIndicator(reportIndicator.getIndicator().toString());
        }
    }

}
