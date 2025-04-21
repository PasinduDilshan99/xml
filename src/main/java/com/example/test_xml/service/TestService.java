package com.example.test_xml.service;

import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;

import java.util.List;

public interface TestService {
    List<MerchantDetails> getMerchantDetails();

    List<CustomerDetails> getCustomerDetails();

    List<ReSellerDetails> getReSellerDetails();
}
