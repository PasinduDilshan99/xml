package com.example.test_xml.model.xmlDto.pae;

import com.example.test_xml.model.xmlDto.DirectorId;
import com.example.test_xml.model.xmlDto.TAddress;
import com.example.test_xml.model.xmlDto.TPhone;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TEntityMyClient {

    @JacksonXmlProperty(localName = "name")
    private String name;

    @JacksonXmlProperty(localName = "commercial_name")
    private String commercialName;

    @JacksonXmlProperty(localName = "incorporation_legal_form")
    private String incorporationLegalForm;

    @JacksonXmlProperty(localName = "incorporation_number")
    private String incorporationNumber;

    @JacksonXmlProperty(localName = "business")
    private String business;

    @JacksonXmlProperty(localName = "phones")
    private TPhone phone;

    @JacksonXmlProperty(localName = "address")
    private TAddress address;

    @JacksonXmlProperty(localName = "email")
    private String email;

    @JacksonXmlProperty(localName = "url")
    private String url;

    @JacksonXmlProperty(localName = "incorporation_state")
    private String incorporationState;

    @JacksonXmlProperty(localName = "incorporation_country_code")
    private String incorporationCountryCode;

    @JacksonXmlProperty(localName = "director_id")
    private DirectorId directorId;

    @JacksonXmlProperty(localName = "incorporation_date")
    private String incorporationDate;

    @JacksonXmlProperty(localName = "business_closed")
    private Boolean businessClosed;

    @JacksonXmlProperty(localName = "date_business_closed")
    private String dateBusinessClosed;

    @JacksonXmlProperty(localName = "tax_number")
    private String taxNumber;

    @JacksonXmlProperty(localName = "tax_reg_number")
    private String taxRegNumber;

    @JacksonXmlProperty(localName = "comments")
    private String comments;

}
