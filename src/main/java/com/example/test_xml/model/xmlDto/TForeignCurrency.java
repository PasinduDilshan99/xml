package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.ContactTypes;
import com.example.test_xml.model.enums.Currencies;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "address")
public class TForeignCurrency {

    @JacksonXmlProperty(localName = "foreign_currency_code")
    private Currencies foreignCurrencyCode;

    @JacksonXmlProperty(localName = "foreign_amount")
    private BigDecimal foreignAmount;

    @JacksonXmlProperty(localName = "foreign_exchange_rate")
    private BigDecimal foreignExchangeRate;

}
