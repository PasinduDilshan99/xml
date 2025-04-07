package com.example.test_xml.validationServices;

import com.example.test_xml.model.xmlDto.Transaction;

public interface TransactionValidationService {
    void validateTransaction(Transaction transaction);
}
