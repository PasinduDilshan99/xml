package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.response.*;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.service.CreateService;
import com.example.test_xml.service.MyClientService;
import com.example.test_xml.validationServices.TEntityMyClientValidationService;
import com.example.test_xml.validationServices.TPersonMyClientValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyClientServiceImpl implements MyClientService {

    private final Logger logger = LoggerFactory.getLogger(MyClientServiceImpl.class);

    private final TPersonMyClientValidation tPersonMyClientValidation;
    private final TEntityMyClientValidationService tEntityMyClientValidationService;
    private final CreateService createService;

    @Autowired
    public MyClientServiceImpl(TPersonMyClientValidation tPersonMyClientValidation,
                               TEntityMyClientValidationService tEntityMyClientValidationService,
                               CreateService createService) {
        this.tPersonMyClientValidation = tPersonMyClientValidation;
        this.tEntityMyClientValidationService = tEntityMyClientValidationService;
        this.createService = createService;
    }

    @Override
    public TFromMyClient convertDataToTFromMyClient(CommonDetails from) {
        TFromMyClient tFromMyClient = new TFromMyClient();
        tFromMyClient.setFromFundsCode(FundsTypes.MOBL); //
        tFromMyClient.setFromFundsComment(null);
//        if (tFromMyClient.getFromFundsCode() != null && tFromMyClient.getFromFundsCode().equals(FundsTypes.OTH)) { // <-- if fund_code is other
//            tFromMyClient.setFromFundsComment("comment"); // chane this
//        } else {
//            tFromMyClient.setFromFundsComment(null);
//        }
        tFromMyClient.setFromForeignCurrency(null);
        tFromMyClient.setTConductor(null);
        if (from.getUserType().equals(UserTypes.CUSTOMER)) {
            // From person
            tFromMyClient.setTPersonMyClient(createService.createTPersonMyClient(from));
            tPersonMyClientValidation.validateTPersonMyClient(tFromMyClient.getTPersonMyClient());  // tPersonMyClient validation
        } else if (from.getUserType().equals(UserTypes.MERCHANT) || from.getUserType().equals(UserTypes.RETAILER)) {
            // From entity
            tFromMyClient.setTEntityMyClient(createService.createTEntityMyClient(from));
            tEntityMyClientValidationService.validateTEntityMyClient(tFromMyClient.getTEntityMyClient());  // tEntityMyClient validation
        }
        tFromMyClient.setFromCountry(CountryCodes.LK); //
        return tFromMyClient;
    }

    @Override
    public TToMyClient convertDataTOTTOMyClient(CommonDetails to) {
        TToMyClient tToMyClient = new TToMyClient();
        tToMyClient.setToFundsCode(FundsTypes.MOBL);
        tToMyClient.setToFundsComment(null); // --> required if to_funds_code is other
        tToMyClient.setToForeignCurrency(null);
        tToMyClient.setToAccount(createService.createTAccountMyClient(to));
        tToMyClient.setToCountry(CountryCodes.LK);
        return tToMyClient;
    }

}
