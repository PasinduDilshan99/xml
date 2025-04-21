package com.example.test_xml.service;

import com.example.test_xml.model.xmlDto.TPersonRegistrationInReport;

public interface CommonService {
    String setUserTypesAccordingToWalletTypeAndAccountType(String accountType, String walletType);
    TPersonRegistrationInReport createReportingPerson();
}
