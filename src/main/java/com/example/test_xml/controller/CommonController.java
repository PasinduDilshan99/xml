package com.example.test_xml.controller;

import com.example.test_xml.model.response.*;
import com.example.test_xml.service.CommonService;
import com.example.test_xml.service.DepositService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common/v0")
public class CommonController {

    private final CommonService commonService;
    private final DepositService depositService;

    @Autowired
    public CommonController(CommonService commonService, DepositService depositService) {
        this.commonService = commonService;
        this.depositService = depositService;
    }

    @GetMapping("/get-user-types")
    public List<ModifyUserTypesResponse> getUserTypes(){
        return commonService.getUserTypes();
    }

    @GetMapping("/get-merchant-details")
    public List<MerchantDetails> getMerchantDetails(){
        return commonService.getMerchantDetails();
    }

    @GetMapping("/get-customer-details")
    public List<CustomerDetails> getCustomerDetails(){
        return commonService.getCustomerDetails();
    }

    @GetMapping("/get-reseller-details")
    public List<ReSellerDetails> getReSellerDetails(){
        return commonService.getReSellerDetails();
    }

    @GetMapping("/get-transactions")
    public List<Transactions> getTransactions(){
        return commonService.getTransactions();
    }

    @GetMapping(value = "/get-transaction-xml", produces = "application/xml")
    public String getTransactionsXml() throws JsonProcessingException {
        return commonService.getTransactionsXml();
    }

}
