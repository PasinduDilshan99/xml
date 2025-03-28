package com.example.test_xml.controller;

import com.example.test_xml.model.response.GetUserTypesResponse;
import com.example.test_xml.repository.CommonRepository;
import com.example.test_xml.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/deposits")
public class DepositController {

    @Autowired
    private DepositService depositService;

    @Autowired
    private CommonRepository commonRepository;

//    @GetMapping(value = "/xml", produces = "application/xml")
//    public String getDepositsXml() throws Exception {
//        return depositService.getXmlDeposits();
//    }

    @GetMapping(value = "/a")
    public List<GetUserTypesResponse> te(){
        return commonRepository.getUserTypes();
    }

    @GetMapping(value = "/test", produces = "application/xml")
    public String getText() throws Exception {
        return depositService.getTest();
    }

}