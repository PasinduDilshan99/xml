package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.ConductionTypes;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "transaction")
public class Transaction {

    @JacksonXmlProperty(localName = "transaction_number")
    private String transactionNumber;

    @JacksonXmlProperty(localName = "internal_ref_number")
    private String internalRefNumber;

    @JacksonXmlProperty(localName = "transaction_location")
    private String transactionLocation;

    @JacksonXmlProperty(localName = "transaction_description")
    private String transactionDescription;

    @JacksonXmlProperty(localName = "date_transaction")
    private String dateTransaction;

    @JacksonXmlProperty(localName = "teller")
    private String teller;

    @JacksonXmlProperty(localName = "authorized")
    private String authorized;

    @JacksonXmlProperty(localName = "late_deposit")
    private Boolean lateDeposit;

    @JacksonXmlProperty(localName = "date_posting")
    private String datePosting;

    @JacksonXmlProperty(localName = "value_date")
    private String valueDate;

    @JacksonXmlProperty(localName = "transmode_code")
    private ConductionTypes transmodeCode;

    @JacksonXmlProperty(localName = "transmode_comment")
    private String transmodeComment;

    @JacksonXmlProperty(localName = "amount_local")
    private BigDecimal amountLocal;

    @JacksonXmlProperty(localName = "t_from_my_client")
    private TFromMyClient tFromMyClient;

    @JacksonXmlProperty(localName = "t_to_my_client")
    private TToMyClient tToMyClient;

    @JacksonXmlProperty(localName = "goods_services")
    private String goodsServices;

    @JacksonXmlProperty(localName = "comments")
    private String comments;

}
