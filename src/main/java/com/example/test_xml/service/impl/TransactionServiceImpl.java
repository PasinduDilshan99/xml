package com.example.test_xml.service.impl;

import com.example.test_xml.exception.ConvertErrorException;
import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.enums.db.WalletType;
import com.example.test_xml.model.response.*;
import com.example.test_xml.model.xmlDto.*;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.repository.TransactionRepository;
import com.example.test_xml.service.DetailsMapService;
import com.example.test_xml.service.TransactionService;
import com.example.test_xml.service.ConvertService;
import com.example.test_xml.validationServices.FromAndToValidationService;
import com.example.test_xml.validationServices.ReportingPersonValidationService;
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
import java.util.*;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final DetailsMapService detailsMapService;
    private final ConvertService convertService;
    private final TransactionValidationService transactionValidationService;
    private final ReportingPersonValidationService reportingPersonValidationService;
    private final FromAndToValidationService fromAndToValidationService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  DetailsMapService detailsMapService,
                                  ConvertService convertService,
                                  TransactionValidationService transactionValidationService,
                                  ReportingPersonValidationService reportingPersonValidationService,
                                  FromAndToValidationService fromAndToValidationService) {
        this.transactionRepository = transactionRepository;
        this.detailsMapService = detailsMapService;
        this.convertService = convertService;
        this.transactionValidationService = transactionValidationService;
        this.reportingPersonValidationService = reportingPersonValidationService;
        this.fromAndToValidationService = fromAndToValidationService;
    }

    @Value("${report.details.rentity.branch}")
    private String reportRentityBranch;

    @Value("${report.details.address.address}")
    private String reportAddressAddress;

    @Value("${report.details.address.zip}")
    private String reportAddressZip;

    @Value("${reporting.person.details.first.name}")
    private String reportingPersonFirstName;

    @Value("${reporting.person.details.last.name}")
    private String reportingPersonLastName;

    @Value("${reporting.person.details.ssn}")
    private String reportingPersonSsn;

    @Value("${reporting.person.details.id.number}")
    private String reportingPersonIdNumber;

    @Value("${reporting.person.details.email}")
    private String reportingPersonEmail;


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
            modifyUserTypesResponse.setFromUserTypes(UserTypes.valueOf(setUserType(getUserTypesResponse.getFromAccType(), getUserTypesResponse.getFromWalletType())));
            modifyUserTypesResponse.setToUserTypes(UserTypes.valueOf(setUserType(getUserTypesResponse.getToAccType(), getUserTypesResponse.getToWalletType())));
            modifyUserTypesResponses.add(modifyUserTypesResponse);
        }
        return modifyUserTypesResponses;
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
        logger.info("resellerList : : : " + resellerList.toString());
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
            try {
                Transaction transaction = new Transaction();
                transaction.setTransactionNumber(transactionResponse1.getTransactionNumber());
                transaction.setAmountLocal(transactionResponse1.getTxnAmount());
                transaction.setTransactionDescription(transactionResponse1.getTransactionDescription());
                transaction.setDateTransaction(transactionResponse1.getDateTransaction().toString());
                if (transactionResponse1.getTransmodeCode() == null) {
                    transaction.setTransmodeCode(ConductionTypes.OTH);
                } else if (transactionResponse1.getTransmodeCode().equals("TXN")) {
                    transaction.setTransmodeCode(ConductionTypes.AGNT);
                } else {
                    transaction.setTransmodeCode(ConductionTypes.OTH);
                }
                TFromMyClient tFromMyClient = convertService.convertDataToFromMyClient(transactionResponse1.getFrom());
                // tFromMyClient Validation
//                fromAndToValidationService.validateTFromMyClient(tFromMyClient);
                transaction.setTFromMyClient(tFromMyClient);
                TToMyClient tToMyClient = convertService.convertDataTOTTOMyClient(transactionResponse1.getTo());
                // tToMyClient Validation
//                fromAndToValidationService.validateTToMyClient(tToMyClient);
                transaction.setTToMyClient(tToMyClient);
                // transaction validation
//                transactionValidationService.validateTransaction(transaction);
                transactionList.add(transaction);
            } catch (Exception e) {
                logger.error("Error occur when create transaction list which transaction id : {}", transactionResponse1.getTransactionNumber());
                logger.error(e.toString());
                throw new ConvertErrorException("Error occur when create transaction list which transaction id : " + transactionResponse1.getTransactionNumber());
            }

        }

        Report report = new Report(
                153, //
                reportRentityBranch,
                SubmissionTypes.E, //
                ReportCodes.EFT, //
                null,
                null,
                String.valueOf(new Timestamp(System.currentTimeMillis())), //
                Currencies.LKR,
                createReportingPerson(),
                new TAddress(ContactTypes.CORS,
                        reportAddressAddress,
                        null,
                        null,
                        reportAddressZip,
                        CountryCodes.LK,
                        null,
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

    private TPersonRegistrationInReport createReportingPerson() {
        TPersonRegistrationInReport reportingPerson = new TPersonRegistrationInReport(
                GenderTypes.F,  // gender
                null,            // title
                reportingPersonFirstName,           // firstName
                null,             // middleName
                null,            // prefix
                reportingPersonLastName,            // lastName
                null, // birthdate
                null,       // birthPlace
                null,       // mothersName
                null,             // alias
                reportingPersonSsn,    // ssn
                null,   // passport
                reportingPersonIdNumber,       // idNumber
                CountryCodes.LK.toString(),            // nationality1
                null,         // nationality2
                null,             // nationality3
                null,     // residence
                null,     // phone
                null,   // address
                reportingPersonEmail, // email
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
        reportingPersonValidationService.validateReportingPerson(reportingPerson);
        return reportingPerson;
    }


}
