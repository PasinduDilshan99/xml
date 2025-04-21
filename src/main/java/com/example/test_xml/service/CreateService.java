package com.example.test_xml.service;

import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.response.TransactionResponse;
import com.example.test_xml.model.xmlDto.DirectorId;
import com.example.test_xml.model.xmlDto.Transaction;
import com.example.test_xml.model.xmlDto.from.FromEntity;
import com.example.test_xml.model.xmlDto.from.FromPerson;
import com.example.test_xml.model.xmlDto.pae.TEntityMyClient;
import com.example.test_xml.model.xmlDto.pae.TPersonMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;

public interface CreateService {
    FromEntity createTEntityMyClient(CommonDetails from);

    FromPerson createTPersonMyClient(CommonDetails from);

    ToAccount createTAccountMyClient(CommonDetails to);

    DirectorId createDirectorId(CommonDetails to);

    Transaction createTransaction(TransactionResponse transactionResponse1);
}
