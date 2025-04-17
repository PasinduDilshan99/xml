package com.example.test_xml.service;

import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;

public interface DetailsMapService {

    CommonDetails mapCustomerToCommon(CustomerDetails customer);

    CommonDetails mapReSellerToCommon(ReSellerDetails reSeller);

    CommonDetails mapMerchantToCommon(MerchantDetails merchant);

}
