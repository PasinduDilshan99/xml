package com.example.test_xml.service;

import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;

public interface ConvertService {
    CommonDetails mapCustomerToCommon(CustomerDetails customer);
    CommonDetails mapReSellerToCommon(ReSellerDetails reSeller);
    CommonDetails mapMerchantToCommon(MerchantDetails merchant);

    ToAccount convertDataToToAccount(CommonDetails to);

    TFromMyClient convertDataToFromMyClient(CommonDetails from);

    TToMyClient convertDataTOTTOMyClient(CommonDetails to);
}
