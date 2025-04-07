package com.example.test_xml.validationServices;

import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;

public interface FromAndToValidationService {
    void validateTFromMyClient(TFromMyClient tFromMyClient);
    void validateTToMyClient(TToMyClient tToMyClient);
}
