package com.example.test_xml.repository.impl;

import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.GetUserTypesResponse;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;
import com.example.test_xml.repository.TransactionRepository;
import com.example.test_xml.util.Queries;
import com.example.test_xml.util.queriesColumns.GetCustomerDetailsColumns;
import com.example.test_xml.util.queriesColumns.GetMerchantDetailsColumns;
import com.example.test_xml.util.queriesColumns.GetResellerDetailsColumns;
import com.example.test_xml.util.queriesColumns.GetUserTypesColumns;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    @Value("${minimum.transaction.value}")
    private String minimumTransactionValueStr;

    private BigDecimal minimumTransactionValue;

    private final JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    @Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        try {
            this.minimumTransactionValue = new BigDecimal(minimumTransactionValueStr);
        } catch (NumberFormatException e) {
            logger.error("Invalid minimum.transaction.value in properties: {}", minimumTransactionValueStr, e);
            this.minimumTransactionValue = BigDecimal.ZERO;
        }
    }

    public List<GetUserTypesResponse> getUserTypes() {
        logger.info("Start executing getUserTypes");
        String SQL_QUERY = Queries.GET_USER_TYPES;

        try {
            return jdbcTemplate.query(SQL_QUERY, new Object[]{minimumTransactionValue}, (rs, rowNum) -> new GetUserTypesResponse(
                    rs.getInt(GetUserTypesColumns.TRANSACTION_ID),
                    rs.getBigDecimal(GetUserTypesColumns.TXN_AMOUNT),
                    rs.getString(GetUserTypesColumns.TRANSACTION_DESCRIPTION),
                    rs.getTimestamp(GetUserTypesColumns.DATE_TRANSACTION),
                    rs.getString(GetUserTypesColumns.TRANSMODE),
                    rs.getString(GetUserTypesColumns.TRANSACTION_NUMBER),
                    rs.getString(GetUserTypesColumns.UTILITY_TYPE),
                    rs.getString(GetUserTypesColumns.FROM_WALLET_TYPE),
                    rs.getString(GetUserTypesColumns.FROM_ACC_TYPE),
                    rs.getInt(GetUserTypesColumns.FROM_ACC_NO),
                    rs.getString(GetUserTypesColumns.TO_WALLET_TYPE),
                    rs.getString(GetUserTypesColumns.TO_ACC_TYPE),
                    rs.getInt(GetUserTypesColumns.TO_ACC_NO)
            ));
        } catch (Exception e) {
            logger.error("Error occurred while retrieving user types.", e);
            return Collections.emptyList();
        }
    }

    public List<MerchantDetails> getMerchantDetails(List<Integer> accountIds) {
        logger.info("Start executing getMerchantDetails");

        if (accountIds == null || accountIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = accountIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));
        logger.info("Merchant account ids : {}", accountIds);

        String query = String.format(Queries.GET_MERCHANT_DETAILS, inSql);

        try {
            return jdbcTemplate.query(query, accountIds.toArray(), (rs, rowNum) -> {
                MerchantDetails merchantDetails = new MerchantDetails();
                merchantDetails.setDetailsType("MERCHANT");
                merchantDetails.setId(rs.getInt(GetMerchantDetailsColumns.ID));
                merchantDetails.setFirstName(rs.getString(GetMerchantDetailsColumns.FIRST_NAME));
                merchantDetails.setAddressName(rs.getString(GetMerchantDetailsColumns.ADDRESS_NAME));
                merchantDetails.setAddressLine1(rs.getString(GetMerchantDetailsColumns.ADDRESS_LINE1));
                merchantDetails.setAddressLine2(rs.getString(GetMerchantDetailsColumns.ADDRESS_LINE2));
                merchantDetails.setAddressLine3(rs.getString(GetMerchantDetailsColumns.ADDRESS_LINE3));
                merchantDetails.setAlias(rs.getString(GetMerchantDetailsColumns.ALIAS));
                merchantDetails.setTitle(rs.getString(GetMerchantDetailsColumns.TITLE));
                merchantDetails.setIdNumber(rs.getString(GetMerchantDetailsColumns.ID_NUMBER));
                merchantDetails.setBirthdate(rs.getTimestamp(GetMerchantDetailsColumns.BIRTHDATE));
                merchantDetails.setMothersName(rs.getString(GetMerchantDetailsColumns.MOTHERS_NAME));
                merchantDetails.setNationality1(rs.getString(GetMerchantDetailsColumns.NATIONALITY1));
                merchantDetails.setCity(rs.getString(GetMerchantDetailsColumns.CITY));
                merchantDetails.setState(rs.getString(GetMerchantDetailsColumns.STATE));
                merchantDetails.setBusinessType(rs.getString(GetMerchantDetailsColumns.BUSINESS_TYPE));
                merchantDetails.setPhonesPrimary(rs.getString(GetMerchantDetailsColumns.PHONES_PRIMARY));
                merchantDetails.setPhonesSecondary(rs.getString(GetMerchantDetailsColumns.PHONES_SECONDARY));
                merchantDetails.setEmail(rs.getString(GetMerchantDetailsColumns.EMAIL));
                merchantDetails.setOccupation(rs.getString(GetMerchantDetailsColumns.OCCUPATION));
                merchantDetails.setEmployerName(rs.getString(GetMerchantDetailsColumns.EMPLOYER_NAME));
                merchantDetails.setEmployerAddress(rs.getString(GetMerchantDetailsColumns.EMPLOYER_ADDRESS));
                merchantDetails.setSourceOfWealth(rs.getString(GetMerchantDetailsColumns.SOURCE_OF_WEALTH));
                merchantDetails.setUserVerified(rs.getBoolean(GetMerchantDetailsColumns.USER_VERIFIED));
                merchantDetails.setDocumentsVerified(rs.getBoolean(GetMerchantDetailsColumns.DOCUMENTS_VERIFIED));
                merchantDetails.setAnnualIncome(rs.getString(GetMerchantDetailsColumns.ANNUAL_INCOME));
                merchantDetails.setBankAccountNumber(rs.getString(GetMerchantDetailsColumns.BANK_ACCOUNT_NUMBER));
                merchantDetails.setBankCode(rs.getString(GetMerchantDetailsColumns.BANK_CODE));
                merchantDetails.setBankName(rs.getString(GetMerchantDetailsColumns.BANK_NAME));
                merchantDetails.setBranchCode(rs.getString(GetMerchantDetailsColumns.BRANCH_CODE));
                merchantDetails.setBranchName(rs.getString(GetMerchantDetailsColumns.BRANCH_NAME));
                merchantDetails.setBusinessAlreadyRegistered(rs.getBoolean(GetMerchantDetailsColumns.BUSINESS_ALREADY_REGISTERED));
                merchantDetails.setBusinessCommencementDate(rs.getDate(GetMerchantDetailsColumns.BUSINESS_COMMENCEMENT_DATE));
                merchantDetails.setBusinessRegistrationNumber(rs.getString(GetMerchantDetailsColumns.BUSINESS_REGISTRATION_NUMBER));
                merchantDetails.setBusinessPhonePrimary(rs.getString(GetMerchantDetailsColumns.BUSINESS_PHONE_PRIMARY));
                merchantDetails.setBusinessPhoneSecondary(rs.getString(GetMerchantDetailsColumns.BUSINESS_PHONE_SECONDARY));
                merchantDetails.setContactPerson1(rs.getString(GetMerchantDetailsColumns.CONTACT_PERSON1));
                merchantDetails.setContactPerson2(rs.getString(GetMerchantDetailsColumns.CONTACT_PERSON2));
                merchantDetails.setDifferentMailingAddress(rs.getBoolean(GetMerchantDetailsColumns.DIFFERENT_MAILING_ADDRESS));
                merchantDetails.setBusinessEmail(rs.getString(GetMerchantDetailsColumns.BUSINESS_EMAIL));
                merchantDetails.setIncomeTaxFileNumber(rs.getString(GetMerchantDetailsColumns.INCOME_TAX_FILE_NO));
                merchantDetails.setOtherBusinessContacts(rs.getString(GetMerchantDetailsColumns.OTHER_BUSINESS_CONTACTS));
                merchantDetails.setSettlementMethod(rs.getString(GetMerchantDetailsColumns.SETTLEMENT_METHOD));
                merchantDetails.setSourceOfIncome(rs.getString(GetMerchantDetailsColumns.SOURCE_OF_INCOME));
                merchantDetails.setOtherSourceOfIncome(rs.getString(GetMerchantDetailsColumns.OTHER_SOURCE_OF_INCOME));
                return merchantDetails;
            });
        } catch (Exception e) {
            logger.error("Error occurred while retrieving merchant details.", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<CustomerDetails> getCustomerDetails(List<Integer> accountIds) {
        logger.info("Start executing get customer details");

        if (accountIds == null || accountIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = accountIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));
        logger.info("Customer account ids : {}", accountIds);

        String query = String.format(Queries.GET_CUSTOMER_DETAILS, inSql);

        try {
            return jdbcTemplate.query(query, accountIds.toArray(), (rs, rowNum) -> {
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setId(rs.getInt(GetCustomerDetailsColumns.ID));
                customerDetails.setFirstName(rs.getString(GetCustomerDetailsColumns.FIRST_NAME));
                customerDetails.setAddressName(rs.getString(GetCustomerDetailsColumns.ADDRESS_NAME));
                customerDetails.setAddressLine1(rs.getString(GetCustomerDetailsColumns.ADDRESS_LINE1));
                customerDetails.setAddressLine2(rs.getString(GetCustomerDetailsColumns.ADDRESS_LINE2));
                customerDetails.setAddressLine3(rs.getString(GetCustomerDetailsColumns.ADDRESS_LINE3));
                customerDetails.setAlias(rs.getString(GetCustomerDetailsColumns.ALIAS));
                customerDetails.setTitle(rs.getString(GetCustomerDetailsColumns.TITLE));
                customerDetails.setIdNumber(rs.getString(GetCustomerDetailsColumns.ID_NUMBER));
                customerDetails.setBirthdate(rs.getTimestamp(GetCustomerDetailsColumns.BIRTHDATE));
                customerDetails.setMothersName(rs.getString(GetCustomerDetailsColumns.MOTHERS_NAME));
                customerDetails.setNationality1(rs.getString(GetCustomerDetailsColumns.NATIONALITY1));
                customerDetails.setCity(rs.getString(GetCustomerDetailsColumns.CITY));
                customerDetails.setState(rs.getString(GetCustomerDetailsColumns.STATE));
                customerDetails.setBusinessType(rs.getString(GetCustomerDetailsColumns.BUSINESS_TYPE));
                customerDetails.setPhonesPrimary(rs.getString(GetCustomerDetailsColumns.PHONES_PRIMARY));
                customerDetails.setPhonesSecondary(rs.getString(GetCustomerDetailsColumns.PHONES_SECONDARY));
                customerDetails.setEmail(rs.getString(GetCustomerDetailsColumns.EMAIL));
                customerDetails.setOccupation(rs.getString(GetCustomerDetailsColumns.OCCUPATION));
                customerDetails.setEmployerName(rs.getString(GetCustomerDetailsColumns.EMPLOYER_NAME));
                customerDetails.setEmployerAddress(rs.getString(GetCustomerDetailsColumns.EMPLOYER_ADDRESS));
                customerDetails.setSourceOfWealth(rs.getString(GetCustomerDetailsColumns.SOURCE_OF_WEALTH));
                customerDetails.setUserVerified(rs.getBoolean(GetCustomerDetailsColumns.USER_VERIFIED));
                customerDetails.setDocumentsVerified(rs.getBoolean(GetCustomerDetailsColumns.DOCUMENTS_VERIFIED));
                return customerDetails;
            });
        } catch (Exception e) {
            logger.error("Error occurred while retrieving customer details.", e);
            return Collections.emptyList();
        }
    }

    public List<ReSellerDetails> getReSellerDetails(List<Integer> accountIds) {
        logger.info("Start executing get reseller details");

        if (accountIds == null || accountIds.isEmpty()) {
            return Collections.emptyList();
        }

        String inSql = accountIds.stream()
                .map(id -> "?")
                .collect(Collectors.joining(", "));
        logger.info("Reseller account ids : {}", accountIds);
        String query = String.format(Queries.GET_RESELLER_DETAILS, inSql);

        try {
            return jdbcTemplate.query(query, accountIds.toArray(), (rs, rowNum) -> {
                ReSellerDetails reSellerDetails = new ReSellerDetails();
                reSellerDetails.setId(rs.getInt(GetResellerDetailsColumns.ID));
                reSellerDetails.setFirstName(rs.getString(GetResellerDetailsColumns.FIRST_NAME));
                reSellerDetails.setAddressName(rs.getString(GetResellerDetailsColumns.ADDRESS_NAME));
                reSellerDetails.setAddressLine1(rs.getString(GetResellerDetailsColumns.ADDRESS_LINE1));
                reSellerDetails.setAddressLine2(rs.getString(GetResellerDetailsColumns.ADDRESS_LINE2));
                reSellerDetails.setAddressLine3(rs.getString(GetResellerDetailsColumns.ADDRESS_LINE3));
                reSellerDetails.setAlias(rs.getString(GetResellerDetailsColumns.ALIAS));
                reSellerDetails.setTitle(rs.getString(GetResellerDetailsColumns.TITLE));
                reSellerDetails.setIdNumber(rs.getString(GetResellerDetailsColumns.ID_NUMBER));
                reSellerDetails.setBirthdate(rs.getTimestamp(GetResellerDetailsColumns.BIRTHDATE));
                reSellerDetails.setMothersName(rs.getString(GetResellerDetailsColumns.MOTHERS_NAME));
                reSellerDetails.setNationality1(rs.getString(GetResellerDetailsColumns.NATIONALITY1));
                reSellerDetails.setCity(rs.getString(GetResellerDetailsColumns.CITY));
                reSellerDetails.setState(rs.getString(GetResellerDetailsColumns.STATE));
                reSellerDetails.setBusinessType(rs.getString(GetResellerDetailsColumns.BUSINESS_TYPE));
                reSellerDetails.setPhonesPrimary(rs.getString(GetResellerDetailsColumns.PHONES_PRIMARY));
                reSellerDetails.setPhonesSecondary(rs.getString(GetResellerDetailsColumns.PHONES_SECONDARY));
                reSellerDetails.setEmail(rs.getString(GetResellerDetailsColumns.EMAIL));
                reSellerDetails.setOccupation(rs.getString(GetResellerDetailsColumns.OCCUPATION));
                reSellerDetails.setEmployerName(rs.getString(GetResellerDetailsColumns.EMPLOYER_NAME));
                reSellerDetails.setEmployerAddress(rs.getString(GetResellerDetailsColumns.EMPLOYER_ADDRESS));
                reSellerDetails.setSourceOfWealth(rs.getString(GetResellerDetailsColumns.SOURCE_OF_WEALTH));
                reSellerDetails.setUserVerified(rs.getBoolean(GetResellerDetailsColumns.USER_VERIFIED));
                reSellerDetails.setDocumentsVerified(rs.getBoolean(GetResellerDetailsColumns.DOCUMENTS_VERIFIED));
                reSellerDetails.setAnnualIncome(rs.getString(GetResellerDetailsColumns.ANNUAL_INCOME));
                reSellerDetails.setBankAccountNumber(rs.getString(GetResellerDetailsColumns.BANK_ACCOUNT_NUMBER));
                reSellerDetails.setBankCode(rs.getString(GetResellerDetailsColumns.BANK_CODE));
                reSellerDetails.setBankName(rs.getString(GetResellerDetailsColumns.BANK_NAME));
                reSellerDetails.setBranchCode(rs.getString(GetResellerDetailsColumns.BRANCH_CODE));
                reSellerDetails.setBranchName(rs.getString(GetResellerDetailsColumns.BRANCH_NAME));
                reSellerDetails.setBusinessAlreadyRegistered(rs.getBoolean(GetResellerDetailsColumns.BUSINESS_ALREADY_REGISTERED));
                reSellerDetails.setBusinessCommencementDate(rs.getDate(GetResellerDetailsColumns.BUSINESS_COMMENCEMENT_DATE));
                reSellerDetails.setBusinessRegistrationNumber(rs.getString(GetResellerDetailsColumns.BUSINESS_REGISTRATION_NUMBER));
                reSellerDetails.setBusinessPhonePrimary(rs.getString(GetResellerDetailsColumns.BUSINESS_PHONE_PRIMARY));
                reSellerDetails.setBusinessPhoneSecondary(rs.getString(GetResellerDetailsColumns.BUSINESS_PHONE_SECONDARY));
                reSellerDetails.setBusinessContactPerson(rs.getString(GetResellerDetailsColumns.BUSINESS_CONTACT_PERSON));
                reSellerDetails.setDifferentMailingAddress(rs.getBoolean(GetResellerDetailsColumns.DIFFERENT_MAILING_ADDRESS));
                reSellerDetails.setBusinessEmail(rs.getString(GetResellerDetailsColumns.BUSINESS_EMAIL));
                reSellerDetails.setIncomeTaxFileNumber(rs.getString(GetResellerDetailsColumns.INCOME_TAX_FILE_NUMBER));
                reSellerDetails.setOtherConnectedBusiness(rs.getString(GetResellerDetailsColumns.OTHER_CONNECTED_BUSINESS));
                reSellerDetails.setPartnerCode(rs.getString(GetResellerDetailsColumns.PARTNER_CODE));
                reSellerDetails.setSettlementStatus(rs.getString(GetResellerDetailsColumns.SETTLEMENT_STATUS));
                reSellerDetails.setSettlementMethod(rs.getString(GetResellerDetailsColumns.SETTLEMENT_METHOD));
                reSellerDetails.setSourceOfIncome(rs.getString(GetResellerDetailsColumns.SOURCE_OF_INCOME));
                reSellerDetails.setSourceOfIncomeOther(rs.getString(GetResellerDetailsColumns.SOURCE_OF_INCOME_OTHER));
                return reSellerDetails;
            });
        } catch (Exception e) {
            logger.error("Error occurred while retrieving reseller details.", e);
            return Collections.emptyList();
        }
    }

}
