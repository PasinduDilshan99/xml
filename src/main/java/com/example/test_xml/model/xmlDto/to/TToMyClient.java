package com.example.test_xml.model.xmlDto.to;

import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.enums.Currencies;
import com.example.test_xml.model.enums.FundsTypes;
import com.example.test_xml.model.xmlDto.TForeignCurrency;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "t_to_my_client")
public class TToMyClient {

    @JacksonXmlProperty(localName = "to_funds_code")
    private FundsTypes toFundsCode;

    @JacksonXmlProperty(localName = "to_funds_comment")
    private String toFundsComment;

    @JacksonXmlProperty(localName = "to_foreign_currency")
    private TForeignCurrency toForeignCurrency;

    @JacksonXmlProperty(localName = "to_account")
    private ToAccount toAccount;

    @JacksonXmlProperty(localName = "to_country")
    private CountryCodes toCountry;

}
