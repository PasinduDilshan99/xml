package com.example.test_xml.model.xmlDto.pae;

import com.example.test_xml.model.enums.AccountTypes;
import com.example.test_xml.model.enums.Currencies;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import java.math.BigDecimal;

@Data
@JacksonXmlRootElement(localName = "t_account_my_client")
public class TAccountMyClient {

    @JacksonXmlProperty(localName = "institution_name")
    private String institutionName;

    @JacksonXmlProperty(localName = "institution_code")
    private String institutionCode;

    @JacksonXmlProperty(localName = "swift")
    private String swift;

    @JacksonXmlProperty(localName = "non_bank_institution")
    private Boolean nonBankInstitution;

    @JacksonXmlProperty(localName = "branch")
    private String branch;

    @JacksonXmlProperty(localName = "account")
    private String account;

    @JacksonXmlProperty(localName = "currency_code")
    private Currencies currencyCode;

    @JacksonXmlProperty(localName = "account_name")
    private String accountName;

    @JacksonXmlProperty(localName = "iban")
    private String iban;

    @JacksonXmlProperty(localName = "client_number")
    private String clientNumber;

    @JacksonXmlProperty(localName = "personal_account_type")
    private AccountTypes personalAccountType;

    @JacksonXmlProperty(localName = "t_entity")
    private TEntityMyClient tEntity;

    @JacksonXmlElementWrapper(localName = "signatory")
    private TPersonMyClient signatory;

    @JacksonXmlProperty(localName = "is_primary")
    private Boolean isPrimary;

    @JacksonXmlProperty(localName = "role")
    private String role;

    @JacksonXmlProperty(localName = "opened")
    private String opened;

    @JacksonXmlProperty(localName = "closed")
    private String closed;

    @JacksonXmlProperty(localName = "balance")
    private BigDecimal balance;

    @JacksonXmlProperty(localName = "date_balance")
    private String dateBalance;

    @JacksonXmlProperty(localName = "status_code")
    private String statusCode;

    @JacksonXmlProperty(localName = "beneficiary")
    private String beneficiary;

    @JacksonXmlProperty(localName = "beneficiary_comment")
    private String beneficiaryComment;

    @JacksonXmlProperty(localName = "comments")
    private String comments;

}

