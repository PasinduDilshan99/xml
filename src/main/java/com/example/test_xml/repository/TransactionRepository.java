package com.example.test_xml.repository;

import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.GetUserTypesResponse;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;
import java.util.List;

public interface TransactionRepository {

    List<GetUserTypesResponse> getUserTypes();

    List<MerchantDetails> getMerchantDetails(List<Integer> accountIds);

    List<CustomerDetails> getCustomerDetails(List<Integer> accountIds);

    List<ReSellerDetails> getReSellerDetails(List<Integer> accountIds);

}
