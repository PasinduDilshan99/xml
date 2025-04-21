package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.enums.GenderTypes;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.enums.db.WalletType;
import com.example.test_xml.model.xmlDto.TPersonRegistrationInReport;
import com.example.test_xml.service.CommonService;
import com.example.test_xml.validationServices.ReportingPersonValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

    private final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    private final ReportingPersonValidationService reportingPersonValidationService;

    @Value("${report.details.rentity.branch}")
    private String reportRentityBranch;

    @Value("${report.details.address.address}")
    private String reportAddressAddress;

    @Value("${report.details.address.city}")
    private String reportAddressCity;

    @Value("${reporting.person.details.first.name}")
    private String reportingPersonFirstName;

    @Value("${reporting.person.details.last.name}")
    private String reportingPersonLastName;

    @Value("${reporting.person.details.ssn}")
    private String reportingPersonSsn;

    @Value("${reporting.person.details.id.number}")
    private String reportingPersonIdNumber;

    @Value("${reporting.person.details.email}")
    private String reportingPersonEmail;

    @Autowired
    public CommonServiceImpl(ReportingPersonValidationService reportingPersonValidationService) {
        this.reportingPersonValidationService = reportingPersonValidationService;
    }

    @Override
    public String setUserTypesAccordingToWalletTypeAndAccountType(String accountType, String walletType) {
        if (walletType.equals(WalletType.CUS.toString()) && accountType.equals(WalletType.CUS.toString())) {
            return UserTypes.CUSTOMER.toString();
        }
        if (walletType.equals(WalletType.RES.toString()) && accountType.equals(WalletType.RES.toString())) {
            return UserTypes.RETAILER.toString();
        }
        if (walletType.equals(WalletType.RES.toString()) && accountType.equals(WalletType.MER.toString())) {
            // MMRETAILER
            return UserTypes.MERCHANT.toString();
        }
        if (walletType.equals(WalletType.MER.toString()) && accountType.equals(WalletType.MER.toString())) {
            // VENDOR
            return UserTypes.MERCHANT.toString();
        }
        if (walletType.equals(WalletType.UTL.toString()) && accountType.equals(WalletType.MER.toString())) {
            // UTILITY
            return UserTypes.MERCHANT.toString();
        }
        if (walletType.equals(WalletType.DIS.toString()) && accountType.equals(WalletType.DIS.toString())) {
            // DISTRIBUTOR
            return UserTypes.CUSTOMER.toString();
        }
        if (walletType.equals(WalletType.TCA.toString()) && accountType.equals(WalletType.TCA.toString())) {
            return UserTypes.CUSTOMER.toString();
        }
        return UserTypes.CUSTOMER.toString();
    }

    @Override
    public TPersonRegistrationInReport createReportingPerson() {
        TPersonRegistrationInReport reportingPerson = new TPersonRegistrationInReport(
                GenderTypes.F,  // gender
                null,            // title
                reportingPersonFirstName,           // firstName
                null,             // middleName
                null,            // prefix
                reportingPersonLastName,            // lastName
                null, // birthdate
                null,       // birthPlace
                null,       // mothersName
                null,             // alias
                reportingPersonSsn,    // ssn
                null,   // passport
                reportingPersonIdNumber,       // idNumber
                CountryCodes.LK.toString(),            // nationality1
                null,         // nationality2
                null,             // nationality3
                null,     // residence
                null,     // phone
                null,   // address
                reportingPersonEmail, // email
                null,  // occupation
                null,          // employerName
                null,       // employerAddressId
                null,         // employerPhoneId
                null, // identification
                null,       // deceased
                null,         // taxNumber
                null,         // taxRegNumber
                null,  // sourceOfWealth
                null// comments
        );
        reportingPersonValidationService.validateReportingPerson(reportingPerson);
        return reportingPerson;
    }

}
