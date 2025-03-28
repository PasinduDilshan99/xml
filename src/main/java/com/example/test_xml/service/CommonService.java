package com.example.test_xml.service;

import com.example.test_xml.model.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface CommonService {
    List<ModifyUserTypesResponse> getUserTypes();

    List<MerchantDetails> getMerchantDetails();

    List<CustomerDetails> getCustomerDetails();

    List<ReSellerDetails> getReSellerDetails();

    List<Transactions> getTransactions();

    String getTransactionsXml() throws JsonProcessingException;
}
