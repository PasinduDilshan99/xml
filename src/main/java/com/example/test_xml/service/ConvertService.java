package com.example.test_xml.service;

import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;

public interface ConvertService {

    ToAccount convertDataToToAccount(CommonDetails to);

    TFromMyClient convertDataToFromMyClient(CommonDetails from);

    TToMyClient convertDataTOTTOMyClient(CommonDetails to);

}
