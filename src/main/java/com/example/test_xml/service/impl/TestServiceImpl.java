package com.example.test_xml.service.impl;

import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;
import com.example.test_xml.repository.TransactionRepository;
import com.example.test_xml.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TestServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<MerchantDetails> getMerchantDetails() {
        List<Integer> accountIds = Arrays.asList(1126, 1127, 1128);
        return transactionRepository.getMerchantDetails(accountIds);
    }

    @Override
    public List<CustomerDetails> getCustomerDetails() {
        List<Integer> accountIds = Arrays.asList(1126, 1127, 1128);
        return transactionRepository.getCustomerDetails(accountIds);
    }

    @Override
    public List<ReSellerDetails> getReSellerDetails() {
        List<Integer> accountIds = Arrays.asList(2123, 2244, 2245, 138, 411, 412, 413);
        return transactionRepository.getReSellerDetails(accountIds);
    }

}
