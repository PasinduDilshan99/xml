package com.example.test_xml.service;

import com.example.test_xml.model.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface TransactionService {
    List<ModifyUserTypesResponse> getUserTypes();

    List<MerchantDetails> getMerchantDetails();

    List<CustomerDetails> getCustomerDetails();

    List<ReSellerDetails> getReSellerDetails();

    List<TransactionResponse> getTransactions();

    String getTransactionsXml() throws JsonProcessingException;
}
