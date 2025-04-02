package com.example.test_xml.model.xmlDto.from;

import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.enums.Currencies;
import com.example.test_xml.model.enums.FundsTypes;
import com.example.test_xml.model.xmlDto.TForeignCurrency;
import com.example.test_xml.model.xmlDto.pae.TEntityMyClient;
import com.example.test_xml.model.xmlDto.pae.TPersonMyClient;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "t_from_my_client")
public class TFromMyClient {

    @JacksonXmlProperty(localName = "from_funds_code")
    private FundsTypes fromFundsCode;

    @JacksonXmlProperty(localName = "from_funds_comment")
    private String fromFundsComment;

    @JacksonXmlProperty(localName = "from_foreign_currency")
    private TForeignCurrency fromForeignCurrency;

    @JacksonXmlProperty(localName = "t_conductor")
    private String tConductor;

    @JacksonXmlProperty(localName = "from_person")
    private TPersonMyClient tPersonMyClient;

    @JacksonXmlProperty(localName = "from_entity")
    private TEntityMyClient tEntityMyClient;

    @JacksonXmlProperty(localName = "from_country")
    private CountryCodes fromCountry;

}
