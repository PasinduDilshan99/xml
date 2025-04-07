package com.example.test_xml.controller;

import com.example.test_xml.model.response.*;
import com.example.test_xml.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/common/v0")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/get-user-types")
    public List<ModifyUserTypesResponse> getUserTypes(){
        return transactionService.getUserTypes();
    }

    @GetMapping("/get-merchant-details")
    public List<MerchantDetails> getMerchantDetails(){
        return transactionService.getMerchantDetails();
    }

    @GetMapping("/get-customer-details")
    public List<CustomerDetails> getCustomerDetails(){
        return transactionService.getCustomerDetails();
    }

    @GetMapping("/get-reseller-details")
    public List<ReSellerDetails> getReSellerDetails(){
        return transactionService.getReSellerDetails();
    }

    @GetMapping("/get-transactions")
    public List<TransactionResponse> getTransactions(){
        return transactionService.getTransactions();
    }

    @GetMapping(value = "/get-transaction-xml", produces = "application/xml")
    public String getTransactionsXml() throws JsonProcessingException {
        return transactionService.getTransactionsXml();
    }

}
