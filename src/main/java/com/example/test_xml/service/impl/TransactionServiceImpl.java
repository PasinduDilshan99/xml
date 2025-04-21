package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.response.*;
import com.example.test_xml.model.xmlDto.*;
import com.example.test_xml.repository.TransactionRepository;
import com.example.test_xml.service.*;
import com.example.test_xml.validationServices.FromAndToValidationService;
import com.example.test_xml.validationServices.TransactionValidationService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final DetailsMapService detailsMapService;
    private final TransactionValidationService transactionValidationService;
    private final CommonService commonService;
    private final CreateService createService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  DetailsMapService detailsMapService,
                                  TransactionValidationService transactionValidationService,
                                  CommonService commonService,
                                  CreateService createService) {
        this.transactionRepository = transactionRepository;
        this.detailsMapService = detailsMapService;
        this.transactionValidationService = transactionValidationService;
        this.commonService = commonService;
        this.createService =createService;
    }

    @Value("${report.details.rentity.branch}")
    private String reportRentityBranch;

    @Value("${report.details.address.address}")
    private String reportAddressAddress;

    @Value("${report.details.address.city}")
    private String reportAddressCity;

    @Override
    public List<ModifyUserTypesResponse> getUserTypes() {
        List<GetUserTypesResponse> getUserTypesResponses = transactionRepository.getUserTypes();
        List<ModifyUserTypesResponse> modifyUserTypesResponses = new ArrayList<>();

        for (GetUserTypesResponse getUserTypesResponse : getUserTypesResponses) {
            ModifyUserTypesResponse modifyUserTypesResponse = new ModifyUserTypesResponse();
            modifyUserTypesResponse.setTransactionId(getUserTypesResponse.getTransactionId());
            modifyUserTypesResponse.setTxnAmount(getUserTypesResponse.getTxnAmount());
            modifyUserTypesResponse.setTransactionDescription(getUserTypesResponse.getTransactionDescription());
            modifyUserTypesResponse.setDateTransaction(getUserTypesResponse.getDateTransaction());
            modifyUserTypesResponse.setTransmodeCode(getUserTypesResponse.getTransmodeCode());
            modifyUserTypesResponse.setTransactionNumber(getUserTypesResponse.getTransactionNumber());
            modifyUserTypesResponse.setFromAccNo(getUserTypesResponse.getFromAccNo());
            modifyUserTypesResponse.setToAccNo(getUserTypesResponse.getToAccNo());
            modifyUserTypesResponse.setFromUserTypes(UserTypes.valueOf(commonService.setUserTypesAccordingToWalletTypeAndAccountType(getUserTypesResponse.getFromAccType(), getUserTypesResponse.getFromWalletType())));
            modifyUserTypesResponse.setToUserTypes(UserTypes.valueOf(commonService.setUserTypesAccordingToWalletTypeAndAccountType(getUserTypesResponse.getToAccType(), getUserTypesResponse.getToWalletType())));
            modifyUserTypesResponses.add(modifyUserTypesResponse);
        }
        return modifyUserTypesResponses;
    }


    @Override
    public List<TransactionResponse> getTransactions() {
        List<ModifyUserTypesResponse> modifyUserTypesResponses = getUserTypes();

        List<TransactionResponse> transactions = new ArrayList<>();
        List<Integer> customerList = new ArrayList<>();
        List<Integer> merchantList = new ArrayList<>();
        List<Integer> resellerList = new ArrayList<>();

        for (ModifyUserTypesResponse modifyUserTypesResponse : modifyUserTypesResponses) {
            if (modifyUserTypesResponse.getFromUserTypes().equals(UserTypes.CUSTOMER)) {
                customerList.add(modifyUserTypesResponse.getFromAccNo());
            }
            if (modifyUserTypesResponse.getFromUserTypes().equals(UserTypes.RETAILER)) {
                resellerList.add(modifyUserTypesResponse.getFromAccNo());
            }
            if (modifyUserTypesResponse.getFromUserTypes().equals(UserTypes.MERCHANT)) {
                merchantList.add(modifyUserTypesResponse.getFromAccNo());
            }
            if (modifyUserTypesResponse.getToUserTypes().equals(UserTypes.CUSTOMER)) {
                customerList.add(modifyUserTypesResponse.getToAccNo());
            }
            if (modifyUserTypesResponse.getToUserTypes().equals(UserTypes.RETAILER)) {
                resellerList.add(modifyUserTypesResponse.getToAccNo());
            }
            if (modifyUserTypesResponse.getToUserTypes().equals(UserTypes.MERCHANT)) {
                merchantList.add(modifyUserTypesResponse.getToAccNo());
            }
        }

        customerList = new ArrayList<>(new HashSet<>(customerList));
        merchantList = new ArrayList<>(new HashSet<>(merchantList));
        resellerList = new ArrayList<>(new HashSet<>(resellerList));

        List<CustomerDetails> customerDetails = transactionRepository.getCustomerDetails(customerList);
        List<MerchantDetails> merchantDetails = transactionRepository.getMerchantDetails(merchantList);
        List<ReSellerDetails> reSellerDetails = transactionRepository.getReSellerDetails(resellerList);

        List<CommonDetails> commonDetails = new ArrayList<>();
        for (CustomerDetails customerDetails1 : customerDetails) {
            commonDetails.add(detailsMapService.mapCustomerToCommon(customerDetails1));
        }
        for (MerchantDetails merchantDetails1 : merchantDetails) {
            commonDetails.add(detailsMapService.mapMerchantToCommon(merchantDetails1));
        }
        for (ReSellerDetails reSellerDetails1 : reSellerDetails) {
            commonDetails.add(detailsMapService.mapReSellerToCommon(reSellerDetails1));
        }

        for (ModifyUserTypesResponse modifyUserTypesResponse : modifyUserTypesResponses) {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setTransactionNumber(modifyUserTypesResponse.getTransactionNumber());
            transactionResponse.setTxnAmount(modifyUserTypesResponse.getTxnAmount());
            transactionResponse.setFromAccountId(modifyUserTypesResponse.getFromAccNo());
            transactionResponse.setToAccountId(modifyUserTypesResponse.getToAccNo());
            transactionResponse.setTransactionDescription(modifyUserTypesResponse.getTransactionDescription());
            transactionResponse.setDateTransaction(modifyUserTypesResponse.getDateTransaction());
            transactionResponse.setTransmodeCode(modifyUserTypesResponse.getTransmodeCode());

            for (CommonDetails commonDetails1 : commonDetails) {
                if (modifyUserTypesResponse.getFromAccNo() == commonDetails1.getId()) {
                    transactionResponse.setFrom(commonDetails1);
                    break;
                }
            }
            for (CommonDetails commonDetails1 : commonDetails) {
                if (modifyUserTypesResponse.getToAccNo() == commonDetails1.getId()) {
                    transactionResponse.setTo(commonDetails1);
                    break;
                }
            }
            transactions.add(transactionResponse);
        }
        return transactions;
    }

    @Override
    public String getTransactionsXml() throws JsonProcessingException {

        List<TransactionResponse> transactions = getTransactions();
        List<Transaction> transactionList = new ArrayList<>();
        for (TransactionResponse transactionResponse1 : transactions) {
            Transaction transaction = createService.createTransaction(transactionResponse1);
            transactionValidationService.validateTransaction(transaction); // transaction validation
            transactionList.add(transaction);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Report report = new Report(
                153, //
                null, // optional --> reportRentityBranch
                SubmissionTypes.E, //
                ReportCodes.EFT, //
                null,
                null,
                timestamp.toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")), //
                Currencies.LKR,
                commonService.createReportingPerson(), // optional --> createReportingPerson()
                null, // optional --> new TAddress(ContactTypes.CORS, reportAddressAddress, null, reportAddressCity, null, CountryCodes.LK, null, null),
                null,
                null,
                transactionList, //
                new ReportIndicator(ReportIndicatorTypes.EFT) //
        );
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        return xmlMapper.writeValueAsString(report);
    }

}
