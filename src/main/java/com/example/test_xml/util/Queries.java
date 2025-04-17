package com.example.test_xml.util;

public class Queries {

    public static final String GET_USER_TYPES = """
        SELECT
              t.ID AS TRANSACTION_ID,
              t.TXN_AMOUNT,
              t.TXN_CODE,
              t.NOTE AS TRANSACTION_DESCRIPTION,
              t.TXN_DATE AS DATE_TRANSACTION,
              t.TXN_MODE AS TRANSMODE,
              t.TXN_REF AS TRANSACTION_NUMBER,
              uw_from.WALLET_TYPE AS FROM_WALLET_TYPE,
              ua_from.ACC_TYPE AS FROM_ACC_TYPE,
              ua_from.ACC_NO AS FROM_ACC_NO,
              uw_to.WALLET_TYPE AS TO_WALLET_TYPE,
              ua_to.ACC_TYPE AS TO_ACC_TYPE,
              ua_to.ACC_NO AS TO_ACC_NO
          FROM mwalletuser.MWT_WALLET_TRANSACTIONS t
          JOIN mwalletuser.MWT_USER_ACCOUNT ua_from ON t.A_NUMBER = ua_from.ACC_NO
          JOIN mwalletuser.MWT_USER_WALLET uw_from ON t.A_NUMBER = uw_from.ACC_ID
          JOIN mwalletuser.MWT_USER_ACCOUNT ua_to ON t.B_NUMBER = ua_to.ACC_NO
          JOIN mwalletuser.MWT_USER_WALLET uw_to ON t.B_NUMBER = uw_to.ACC_ID
          WHERE t.TXN_AMOUNT >= 1000000
          AND t.txn_code NOT IN (6, 7, 8, 12, 13, 42, 18, 21, 24, 25, 26, 27, 32, 34, 35, 36)
          AND ua_from.ACC_TYPE NOT IN ('TCA', 'ACR', 'MCR', 'RLC', 'MCA', 'UTL', 'OOW', 'DCA', 'RLD', 'IOW')
          AND t.TXN_DATE > TO_DATE('2022-12-31', 'YYYY-MM-DD')
            """;

    public static final String GET_MERCHANT_DETAILS = """
            SELECT
                uw.ACC_ID AS id,
                uw.NAME AS first_name,
                uw.ADDR_NAME AS address_name,
                uw.ADDR_LINE1 AS address_line1,
                uw.ADDR_LINE2 AS address_line2,
                uw.ADDR_LINE3 AS address_line3,
                uw.SHORT_NAME AS alias,
                uw.TITLE AS title,
                uw.NIC_NO AS id_number,
                uw.DOB AS birthdate,
                uw.MOTHER_NAME AS mothers_name,
                uw.NATIONALITY AS nationality1,
                uw.CITY AS city,
                uw.DISTRICT AS state,
                uw.CONTACT_NO1 AS phones_primary,
                uw.CONTACT_NO2 AS phones_secondary,
                uw.EMAIL AS email,
                uw.OCCUPATION AS occupation,
                uw.NAME_OF_EMP AS employer_name,
                uw.ADDR_OF_EMP AS employer_address,
                uw.SOR_OF_INCOME AS source_of_wealth,
                uw.USER_VERIFIED AS user_verified,
                uw.DOCCUMENTS_VERIFIED AS documents_verified,
                md.ANNUAL_INCOME AS annual_income,
                md.BANK_ACC_NO AS bank_account_number,
                md.BANK_CODE AS bank_code,
                md.BANK_NAME AS bank_name,
                md.BRANCH_CODE AS branch_code,
                md.BRANCH_NAME AS branch_name,
                md.BUSI_ALRDY_REG AS business_already_registered,
                md.BUSI_COMMENCE_AT AS business_commencement_date,
                md.BUSSINESS_REG_NO AS business_registration_number,
                md.COMM_EMAIL AS communication_email,
                md.CONTACT_EMAIL1 AS contact_email1,
                md.CONTACT_EMAIL2 AS contact_email2,
                md.CONTACT_NO1 AS business_phone_primary,
                md.CONTACT_NO2 AS business_phone_secondary,
                md.CONTACT_PERSON1 AS contact_person1,
                md.CONTACT_PERSON2 AS contact_person2,
                md.DIFF_MAIL_ADDRESS AS different_mailing_address,
                md.BUSINESS_EMAIL AS business_email,
                md.INCOME_TAX_FILE_NO AS income_tax_file_no,
                md.OTHER_CON_BUSI AS other_business_contacts,
                md.SETTLEMENT_METHOD AS settlement_method,
                md.SOURCE_OF_INCOME AS source_of_income,
                md.SOURCE_OF_INCOME_OTHER AS other_source_of_income
            FROM mwalletuser.MWT_USER_WALLET_DETAILS uw
            JOIN mwalletuser.MWT_MERCHANT_DETAILS md ON uw.ACC_ID = md.ACC_ID
            WHERE uw.ACC_ID IN (%s)
            """;

    public static final String GET_CUSTOMER_DETAILS = """
            SELECT
                 ACC_ID AS id,
                 NAME AS first_name,
                 ADDR_NAME AS address_name,
                 ADDR_LINE1 AS address_line1,
                 ADDR_LINE2 AS address_line2,
                 ADDR_LINE3 AS address_line3,
                 SHORT_NAME AS alias,
                 TITLE AS title,
                 NIC_NO AS id_number,
                 DOB AS birthdate,
                 MOTHER_NAME AS mothers_name,
                 NATIONALITY AS nationality1,
                 CITY AS city,
                 DISTRICT AS state,
                 CONTACT_NO1 AS phones_primary,
                 CONTACT_NO2 AS phones_secondary,
                 EMAIL AS email,
                 OCCUPATION AS occupation,
                 NAME_OF_EMP AS employer_name,
                 ADDR_OF_EMP AS employer_address,
                 SOR_OF_INCOME AS source_of_wealth,
                 USER_VERIFIED AS user_verified,
                 DOCCUMENTS_VERIFIED AS documents_verified
             FROM mwalletuser.MWT_USER_WALLET_DETAILS WHERE ACC_ID IN (%s)
""";

    public static final String GET_RESELLER_DETAILS = """
        SELECT
            uw.ACC_ID AS id,
            uw.NAME AS first_name,
            uw.ADDR_NAME AS address_name,
            uw.ADDR_LINE1 AS address_line1,
            uw.ADDR_LINE2 AS address_line2,
            uw.ADDR_LINE3 AS address_line3,
            uw.SHORT_NAME AS alias,
            uw.TITLE AS title,
            uw.NIC_NO AS id_number,
            uw.DOB AS birthdate,
            uw.MOTHER_NAME AS mothers_name,
            uw.NATIONALITY AS nationality1,
            uw.CITY AS city,
            uw.DISTRICT AS state,
            uw.CONTACT_NO1 AS phones_primary,
            uw.CONTACT_NO2 AS phones_secondary,
            uw.EMAIL AS email,
            uw.OCCUPATION AS occupation,
            uw.NAME_OF_EMP AS employer_name,
            uw.ADDR_OF_EMP AS employer_address,
            uw.SOR_OF_INCOME AS source_of_wealth,
            uw.USER_VERIFIED AS user_verified,
            uw.DOCCUMENTS_VERIFIED AS documents_verified,
            rd.ANNUAL_INCOME AS annual_income,
            rd.BANK_ACC_NO AS bank_account_number,
            rd.BANK_CODE AS bank_code,
            rd.BANK_NAME AS bank_name,
            rd.BRANCH_CODE AS branch_code,
            rd.BRANCH_NAME AS branch_name,
            rd.BUSI_ALRDY_REG AS business_already_registered,
            rd.BUSI_COMMENCE_AT AS business_commencement_date,
            rd.BUSSINESS_REG_NO AS business_registration_number,
            rd.CONTACT_NO1 AS business_phone_primary,
            rd.CONTACT_NO2 AS business_phone_secondary,
            rd.CONTACT_PERSON AS business_contact_person,
            rd.DIFF_MAIL_ADDRESS AS different_mailing_address,
            rd.EMAIL AS business_email,
            rd.INCOME_TAX_FILE_NO AS income_tax_file_number,
            rd.OTHER_CON_BUSI AS other_connected_business,
            rd.PARTNER_CODE AS partner_code,
            rd.SETTLEMENT AS settlement_status,
            rd.SETTLEMENT_METHOD AS settlement_method,
            rd.SOURCE_OF_INCOME AS source_of_income,
            rd.SOURCE_OF_INCOME_OTHER AS source_of_income_other
        FROM mwalletuser.MWT_USER_WALLET_DETAILS uw
        JOIN mwalletuser.MWT_RESELLER_DETAILS rd ON uw.ACC_ID = rd.ACC_ID
         WHERE uw.ACC_ID IN (%s)
            """;


}
