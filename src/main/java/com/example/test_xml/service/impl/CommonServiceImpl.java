package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.enums.db.WalletType;
import com.example.test_xml.model.response.*;
import com.example.test_xml.model.xmlDto.*;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;
import com.example.test_xml.repository.CommonRepository;
import com.example.test_xml.service.CommonService;
import com.example.test_xml.service.ConvertService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Service
public class CommonServiceImpl implements CommonService {

    private final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    private final CommonRepository commonRepository;

    private final ConvertService convertService;

    @Autowired
    public CommonServiceImpl(CommonRepository commonRepository, ConvertService convertService) {
        this.commonRepository = commonRepository;
        this.convertService = convertService;
    }

    @Override
    public List<ModifyUserTypesResponse> getUserTypes() {
        List<GetUserTypesResponse> getUserTypesResponses = commonRepository.getUserTypes();
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
            modifyUserTypesResponse.setFromUserTypes(UserTypes.valueOf(setUserType(getUserTypesResponse.getFromAccType(), getUserTypesResponse.getFromWalletType())));
            modifyUserTypesResponse.setToUserTypes(UserTypes.valueOf(setUserType(getUserTypesResponse.getToAccType(), getUserTypesResponse.getToWalletType())));
            modifyUserTypesResponses.add(modifyUserTypesResponse);
        }
        return modifyUserTypesResponses;
    }

    @Override
    public List<MerchantDetails> getMerchantDetails() {
        List<Integer> accountIds = Arrays.asList(1126, 1127, 1128);
        return commonRepository.getMerchantDetails(accountIds);
    }

    @Override
    public List<CustomerDetails> getCustomerDetails() {
        List<Integer> accountIds = Arrays.asList(1126, 1127, 1128);
        return commonRepository.getCustomerDetails(accountIds);
    }

    @Override
    public List<ReSellerDetails> getReSellerDetails() {
        List<Integer> accountIds = Arrays.asList(2123, 2244, 2245, 138, 411, 412, 413);
        return commonRepository.getReSellerDetails(accountIds);
    }

    @Override
    public List<Transactions> getTransactions() {
        List<ModifyUserTypesResponse> modifyUserTypesResponses = getUserTypes();
        List<Transactions> transactions = new ArrayList<>();
        List<Integer> customerList = new ArrayList<>();
        List<Integer> merchantList = new ArrayList<>();
        List<Integer> resellerList = new ArrayList<>();
        logger.info(modifyUserTypesResponses.toString());
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

        logger.info("customer details : {}", customerList);
        logger.info("merhcant details : {}", merchantList);
        logger.info("reseller details : {}", resellerList);

        List<CustomerDetails> customerDetails = commonRepository.getCustomerDetails(customerList);
        List<MerchantDetails> merchantDetails = commonRepository.getMerchantDetails(merchantList);
        List<ReSellerDetails> reSellerDetails = commonRepository.getReSellerDetails(resellerList);
//        List<Object> details = new ArrayList<>();
//        details.addAll(customerDetails);
//        details.addAll(merchantDetails);
//        details.addAll(reSellerDetails);
        List<CommonDetails> commonDetails = new ArrayList<>();
        for (CustomerDetails customerDetails1 : customerDetails) {
            commonDetails.add(convertService.mapCustomerToCommon(customerDetails1));
        }
        for (MerchantDetails merchantDetails1 : merchantDetails) {
            commonDetails.add(convertService.mapMerchantToCommon(merchantDetails1));
        }
        for (ReSellerDetails reSellerDetails1 : reSellerDetails) {
            commonDetails.add(convertService.mapReSellerToCommon(reSellerDetails1));
        }

        logger.info("end");

        for (ModifyUserTypesResponse modifyUserTypesResponse : modifyUserTypesResponses) {
            Transactions transaction = new Transactions();
            transaction.setTransactionNumber(modifyUserTypesResponse.getTransactionNumber());
            transaction.setTxnAmount(modifyUserTypesResponse.getTxnAmount());
            transaction.setFromAccountId(modifyUserTypesResponse.getFromAccNo());
            transaction.setToAccountId(modifyUserTypesResponse.getToAccNo());
            transaction.setTransactionDescription(modifyUserTypesResponse.getTransactionDescription());
            transaction.setDateTransaction(modifyUserTypesResponse.getDateTransaction());
            transaction.setTransmodeCode(modifyUserTypesResponse.getTransmodeCode());

            for (CommonDetails commonDetails1 : commonDetails) {
                if (modifyUserTypesResponse.getFromAccNo() == commonDetails1.getId()) {
                    transaction.setFrom(commonDetails1);
                }
            }
            for (CommonDetails commonDetails1 : commonDetails) {
                if (modifyUserTypesResponse.getToAccNo() == commonDetails1.getId()) {
                    transaction.setTo(commonDetails1);
                }
            }

            transactions.add(transaction);
        }

        return transactions;
    }

    public String setUserType(String accountType, String walletType) {
        if (walletType.equals(WalletType.CUS.toString()) && accountType.equals(WalletType.CUS.toString())) {
            return UserTypes.CUSTOMER.toString();
        }
        if (walletType.equals(WalletType.RES.toString()) && accountType.equals(WalletType.RES.toString())) {
            return UserTypes.RETAILER.toString();
        }
        if (walletType.equals(WalletType.RES.toString()) && accountType.equals(WalletType.MER.toString())) {
            // MMRETAILER
            return UserTypes.MERCHANT.toString();
        }
        if (walletType.equals(WalletType.MER.toString()) && accountType.equals(WalletType.MER.toString())) {
            // VENDOR
            return UserTypes.MERCHANT.toString();
        }
        if (walletType.equals(WalletType.UTL.toString()) && accountType.equals(WalletType.MER.toString())) {
            // UTILITY
            return UserTypes.MERCHANT.toString();
        }
        if (walletType.equals(WalletType.DIS.toString()) && accountType.equals(WalletType.DIS.toString())) {
            // DISTRIBUTOR
            return UserTypes.CUSTOMER.toString();
        }
        if (walletType.equals(WalletType.TCA.toString()) && accountType.equals(WalletType.TCA.toString())) {
            return UserTypes.CUSTOMER.toString();
        }
        return UserTypes.CUSTOMER.toString();
    }

    @Override
    public String getTransactionsXml() throws JsonProcessingException {

        List<Transactions> transactions = getTransactions();
        List<Transaction> transactionList = new ArrayList<>();
        for (Transactions transactions1 : transactions) {
            Transaction transaction = new Transaction();
            transaction.setTransactionNumber(transactions1.getTransactionNumber());
            transaction.setAmountLocal(transactions1.getTxnAmount());
            transaction.setTransactionDescription(transactions1.getTransactionDescription());
            transaction.setDateTransaction(transactions1.getDateTransaction().toString());
            if (transactions1.getTransmodeCode().equals("TXN")) {
                transaction.setTransmodeCode(ConductionTypes.AGNT);
            } else {
                transaction.setTransmodeCode(ConductionTypes.OTH);
            }
            TFromMyClient tFromMyClient = convertService.convertDataToFromMyClient(transactions1.getFrom());
            transaction.setTFromMyClient(tFromMyClient);
            TToMyClient tToMyClient = convertService.convertDataTOTTOMyClient(transactions1.getTo());
            transaction.setTToMyClient(tToMyClient);
            transactionList.add(transaction);
        }

        Report report = new Report(
                153, //
                "Head Office",
                SubmissionTypes.E, //
                ReportCodes.EFT, //
                null,
                null,
                String.valueOf(new Timestamp(System.currentTimeMillis())), //
                Currencies.LKR,
                reportingPerson,
                new TAddress(ContactTypes.CORS,
                        "148/15, Lesley Ranagala Mw, Baseline Road, Colombo 08",
                        null,null,
                        "Colombo",
                        null,
                        CountryCodes.LK.toString(),
                        null,
                        null),
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


    TPersonRegistrationInReport reportingPerson = new TPersonRegistrationInReport(
            GenderTypes.F,  // gender
            null,            // title
            "DILHANI",           // firstName
            null,             // middleName
            null,            // prefix
            "ABEYRATHNE",            // lastName
            null, // birthdate
            null,       // birthPlace
            null,       // mothersName
            null,             // alias
            "815182995V",    // ssn
            null,   // passport
            "001",       // idNumber
            CountryCodes.LK.toString(),            // nationality1
            null,         // nationality2
            null,             // nationality3
            null,     // residence
            null,     // phone
            null,   // address
            "dilhania@mobitel.lk", // email
            null,  // occupation
            null,          // employerName
            null,       // employerAddressId
            null,         // employerPhoneId
            null, // identification
            null,       // deceased
            null,         // taxNumber
            null,         // taxRegNumber
            null,  // sourceOfWealth
            null// comments
    );
}
