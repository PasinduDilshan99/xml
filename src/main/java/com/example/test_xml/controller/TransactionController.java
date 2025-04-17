package com.example.test_xml.controller;

import com.example.test_xml.model.response.*;
import com.example.test_xml.service.PreProcessService;
import com.example.test_xml.service.TransactionService;
import com.example.test_xml.util.Constant;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private PreProcessService pePreProcessService;

    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/test")
    public String test(){
        logger.info(Constant.DOTS + " Start the execute test controller. " + Constant.DOTS);
        logger.info("-------------------------------------- Test --------------------------------------");
        pePreProcessService.formatDateInCorrectFormat("2024-03-13 15:59:38.0");
        logger.info(Constant.DOTS + " End execute test controller. " + Constant.DOTS);
        return "test";
    }

    @GetMapping("/get-user-types")
    public List<ModifyUserTypesResponse> getUserTypes(){
        logger.info(Constant.DOTS + " Start the execute get user types. " + Constant.DOTS);
        List<ModifyUserTypesResponse> userTypes = transactionService.getUserTypes();
        logger.info(Constant.DOTS + " End execute get user types. " + Constant.DOTS);
        return userTypes;
    }

    @GetMapping("/get-merchant-details")
    public List<MerchantDetails> getMerchantDetails(){
        logger.info(Constant.DOTS + " Start the execute get merchant details. " + Constant.DOTS);
        List<MerchantDetails> merchantDetails = transactionService.getMerchantDetails();
        logger.info(Constant.DOTS + " End execute get merchant details. " + Constant.DOTS);
        return merchantDetails;
    }

    @GetMapping("/get-customer-details")
    public List<CustomerDetails> getCustomerDetails(){
        logger.info(Constant.DOTS + " Start the execute get customer details. " + Constant.DOTS);
        List<CustomerDetails> customerDetails = transactionService.getCustomerDetails();
        logger.info(Constant.DOTS + " End execute get customer details. " + Constant.DOTS);
        return customerDetails;
    }

    @GetMapping("/get-reseller-details")
    public List<ReSellerDetails> getReSellerDetails(){
        logger.info(Constant.DOTS + " Start the execute get re seller details. " + Constant.DOTS);
        List<ReSellerDetails> reSellerDetails = transactionService.getReSellerDetails();
        logger.info(Constant.DOTS + " End execute get re seller details. " + Constant.DOTS);
        return reSellerDetails;
    }

    @GetMapping("/get-transactions")
    public List<TransactionResponse> getTransactions(){
        logger.info(Constant.DOTS + " Start the execute get transactions. " + Constant.DOTS);
        List<TransactionResponse> transactions = transactionService.getTransactions();
        logger.info(Constant.DOTS + " End execute get transactions. " + Constant.DOTS);
        return transactions;
    }

    @GetMapping(value = "/get-transaction-xml", produces = "application/xml")
    public String getTransactionsXml() throws JsonProcessingException {
        logger.info(Constant.DOTS + " Start the execute get transactions in xml format. " + Constant.DOTS);
        String transactionsXml = transactionService.getTransactionsXml();
        logger.info(Constant.DOTS + " End execute get transactions in xml format. " + Constant.DOTS);
        return transactionsXml;
    }

}
