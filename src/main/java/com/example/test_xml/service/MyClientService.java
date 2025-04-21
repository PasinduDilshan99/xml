package com.example.test_xml.service;

import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;

public interface MyClientService {

    TFromMyClient convertDataToTFromMyClient(CommonDetails from);

    TToMyClient convertDataTOTTOMyClient(CommonDetails to);

}
