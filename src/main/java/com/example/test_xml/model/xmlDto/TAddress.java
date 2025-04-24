package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.ContactTypes;
import com.example.test_xml.model.enums.CountryCodes;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "address")
public class TAddress {

    @JacksonXmlProperty(localName = "address_type")
    private ContactTypes addressType;

    @JacksonXmlProperty(localName = "address")
    private String address;

    @JacksonXmlProperty(localName = "town")
    private String town;

    @JacksonXmlProperty(localName = "city")
    private String city;

    @JacksonXmlProperty(localName = "zip")
    private String zip;

    @JacksonXmlProperty(localName = "country_code")
    private CountryCodes countryCode;

    @JacksonXmlProperty(localName = "State")
    private String state;

    @JacksonXmlProperty(localName = "comments")
    private String comments;

}
