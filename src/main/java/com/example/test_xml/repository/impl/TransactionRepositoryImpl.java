package com.example.test_xml.repository.impl;

import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.GetUserTypesResponse;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;
import com.example.test_xml.repository.TransactionRepository;
import com.example.test_xml.util.Queries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final Logger logger = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TransactionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<GetUserTypesResponse> getUserTypes() {
        logger.info("Start executing getUserTypes");
        String SQL_QUERY = Queries.GET_USER_TYPES;

        try {
            return jdbcTemplate.query(SQL_QUERY, (rs, rowNum) -> new GetUserTypesResponse(
                    rs.getInt("TRANSACTION_ID"),
                    rs.getBigDecimal("TXN_AMOUNT"),
                    rs.getString("TRANSACTION_DESCRIPTION"),
                    rs.getTimestamp("DATE_TRANSACTION"),
                    rs.getString("TRANSMODE"),
                    rs.getString("TRANSACTION_NUMBER"),
                    rs.getString("FROM_WALLET_TYPE"),
                    rs.getString("FROM_ACC_TYPE"),
                    rs.getInt("FROM_ACC_NO"),
                    rs.getString("TO_WALLET_TYPE"),
                    rs.getString("TO_ACC_TYPE"),
                    rs.getInt("TO_ACC_NO")
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

        String query = String.format(Queries.GET_MERCHANT_DETAILS, inSql);

        try {
            return jdbcTemplate.query(query, accountIds.toArray(), (rs, rowNum) -> {
                MerchantDetails merchantDetails = new MerchantDetails();
                merchantDetails.setDetailsType("MERCHANT");
                merchantDetails.setId(rs.getInt("id"));
                merchantDetails.setFirstName(rs.getString("first_name"));
                merchantDetails.setAddressName(rs.getString("address_name"));
                merchantDetails.setAddressLine1(rs.getString("address_line1"));
                merchantDetails.setAddressLine2(rs.getString("address_line2"));
                merchantDetails.setAddressLine3(rs.getString("address_line3"));
                merchantDetails.setAlias(rs.getString("alias"));
                merchantDetails.setTitle(rs.getString("title"));
                merchantDetails.setIdNumber(rs.getString("id_number"));
                merchantDetails.setBirthdate(rs.getDate("birthdate"));
                merchantDetails.setMothersName(rs.getString("mothers_name"));
                merchantDetails.setNationality1(rs.getString("nationality1"));
                merchantDetails.setCity(rs.getString("city"));
                merchantDetails.setState(rs.getString("state"));
                merchantDetails.setPhonesPrimary(rs.getString("phones_primary"));
                merchantDetails.setPhonesSecondary(rs.getString("phones_secondary"));
                merchantDetails.setEmail(rs.getString("email"));
                merchantDetails.setOccupation(rs.getString("occupation"));
                merchantDetails.setEmployerName(rs.getString("employer_name"));
                merchantDetails.setEmployerAddress(rs.getString("employer_address"));
                merchantDetails.setSourceOfWealth(rs.getString("source_of_wealth"));
                merchantDetails.setUserVerified(rs.getBoolean("user_verified"));
                merchantDetails.setDocumentsVerified(rs.getBoolean("documents_verified"));
                merchantDetails.setAnnualIncome(rs.getString("annual_income"));
                merchantDetails.setBankAccountNumber(rs.getString("bank_account_number"));
                merchantDetails.setBankCode(rs.getString("bank_code"));
                merchantDetails.setBankName(rs.getString("bank_name"));
                merchantDetails.setBranchCode(rs.getString("branch_code"));
                merchantDetails.setBranchName(rs.getString("branch_name"));
                merchantDetails.setBusinessAlreadyRegistered(rs.getBoolean("business_already_registered"));
                merchantDetails.setBusinessCommencementDate(rs.getDate("business_commencement_date"));
                merchantDetails.setBusinessRegistrationNumber(rs.getString("business_registration_number"));
                merchantDetails.setBusinessPhonePrimary(rs.getString("business_phone_primary"));
                merchantDetails.setBusinessPhoneSecondary(rs.getString("business_phone_secondary"));
                merchantDetails.setContactPerson1(rs.getString("contact_person1"));
                merchantDetails.setContactPerson2(rs.getString("contact_person2"));
                merchantDetails.setDifferentMailingAddress(rs.getBoolean("different_mailing_address"));
                merchantDetails.setBusinessEmail(rs.getString("business_email"));
                merchantDetails.setIncomeTaxFileNumber(rs.getString("income_tax_file_no"));
                merchantDetails.setOtherBusinessContacts(rs.getString("other_business_contacts"));
                merchantDetails.setSettlementMethod(rs.getString("settlement_method"));
                merchantDetails.setSourceOfIncome(rs.getString("source_of_income"));
                merchantDetails.setOtherSourceOfIncome(rs.getString("other_source_of_income"));
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
        logger.info("accountId" + accountIds);
        logger.info("insql" + inSql);

        String query = String.format(Queries.GET_CUSTOMER_DETAILS, inSql);

        try {
            return jdbcTemplate.query(query, accountIds.toArray(), (rs, rowNum) -> {
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setId(rs.getInt("id"));
                customerDetails.setFirstName(rs.getString("first_name"));
                customerDetails.setAddressName(rs.getString("address_name"));
                customerDetails.setAddressLine1(rs.getString("address_line1"));
                customerDetails.setAddressLine2(rs.getString("address_line2"));
                customerDetails.setAddressLine3(rs.getString("address_line3"));
                customerDetails.setAlias(rs.getString("alias"));
                customerDetails.setTitle(rs.getString("title"));
                customerDetails.setIdNumber(rs.getString("id_number"));
                customerDetails.setBirthdate(rs.getDate("birthdate"));
                customerDetails.setMothersName(rs.getString("mothers_name"));
                customerDetails.setNationality1(rs.getString("nationality1"));
                customerDetails.setCity(rs.getString("city"));
                customerDetails.setState(rs.getString("state"));
                customerDetails.setPhonesPrimary(rs.getString("phones_primary"));
                customerDetails.setPhonesSecondary(rs.getString("phones_secondary"));
                customerDetails.setEmail(rs.getString("email"));
                customerDetails.setOccupation(rs.getString("occupation"));
                customerDetails.setEmployerName(rs.getString("employer_name"));
                customerDetails.setEmployerAddress(rs.getString("employer_address"));
                customerDetails.setSourceOfWealth(rs.getString("source_of_wealth"));
                customerDetails.setUserVerified(rs.getBoolean("user_verified"));
                customerDetails.setDocumentsVerified(rs.getBoolean("documents_verified"));
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
        logger.info("accountId" + accountIds);
        logger.info("insql" + inSql);
        String query = String.format(Queries.GET_RESELLER_DETAILS, inSql);

        try {
            return jdbcTemplate.query(query, accountIds.toArray(), (rs, rowNum) -> {
                ReSellerDetails reSellerDetails = new ReSellerDetails();
                reSellerDetails.setId(rs.getInt("id"));
                reSellerDetails.setFirstName(rs.getString("first_name"));
                reSellerDetails.setAddressName(rs.getString("address_name"));
                reSellerDetails.setAddressLine1(rs.getString("address_line1"));
                reSellerDetails.setAddressLine2(rs.getString("address_line2"));
                reSellerDetails.setAddressLine3(rs.getString("address_line3"));
                reSellerDetails.setAlias(rs.getString("alias"));
                reSellerDetails.setTitle(rs.getString("title"));
                reSellerDetails.setIdNumber(rs.getString("id_number"));
                reSellerDetails.setBirthdate(rs.getDate("birthdate"));
                reSellerDetails.setMothersName(rs.getString("mothers_name"));
                reSellerDetails.setNationality1(rs.getString("nationality1"));
                reSellerDetails.setCity(rs.getString("city"));
                reSellerDetails.setState(rs.getString("state"));
                reSellerDetails.setPhonesPrimary(rs.getString("phones_primary"));
                reSellerDetails.setPhonesSecondary(rs.getString("phones_secondary"));
                reSellerDetails.setEmail(rs.getString("email"));
                reSellerDetails.setOccupation(rs.getString("occupation"));
                reSellerDetails.setEmployerName(rs.getString("employer_name"));
                reSellerDetails.setEmployerAddress(rs.getString("employer_address"));
                reSellerDetails.setSourceOfWealth(rs.getString("source_of_wealth"));
                reSellerDetails.setUserVerified(rs.getBoolean("user_verified"));
                reSellerDetails.setDocumentsVerified(rs.getBoolean("documents_verified"));
                reSellerDetails.setAnnualIncome(rs.getString("annual_income"));
                reSellerDetails.setBankAccountNumber(rs.getString("bank_account_number"));
                reSellerDetails.setBankCode(rs.getString("bank_code"));
                reSellerDetails.setBankName(rs.getString("bank_name"));
                reSellerDetails.setBranchCode(rs.getString("branch_code"));
                reSellerDetails.setBranchName(rs.getString("branch_name"));
                reSellerDetails.setBusinessAlreadyRegistered(rs.getBoolean("business_already_registered"));
                reSellerDetails.setBusinessCommencementDate(rs.getDate("business_commencement_date"));
                reSellerDetails.setBusinessRegistrationNumber(rs.getString("business_registration_number"));
                reSellerDetails.setBusinessPhonePrimary(rs.getString("business_phone_primary"));
                reSellerDetails.setBusinessPhoneSecondary(rs.getString("business_phone_secondary"));
                reSellerDetails.setBusinessContactPerson(rs.getString("business_contact_person"));
                reSellerDetails.setDifferentMailingAddress(rs.getBoolean("different_mailing_address"));
                reSellerDetails.setBusinessEmail(rs.getString("business_email"));
                reSellerDetails.setIncomeTaxFileNumber(rs.getString("income_tax_file_number"));
                reSellerDetails.setOtherConnectedBusiness(rs.getString("other_connected_business"));
                reSellerDetails.setPartnerCode(rs.getString("partner_code"));
                reSellerDetails.setSettlementStatus(rs.getString("settlement_status"));
                reSellerDetails.setSettlementMethod(rs.getString("settlement_method"));
                reSellerDetails.setSourceOfIncome(rs.getString("source_of_income"));
                reSellerDetails.setSourceOfIncomeOther(rs.getString("source_of_income_other"));
                return reSellerDetails;
            });
        } catch (Exception e) {
            logger.error("Error occurred while retrieving reseller details.", e);
            return Collections.emptyList();
        }
    }


}
