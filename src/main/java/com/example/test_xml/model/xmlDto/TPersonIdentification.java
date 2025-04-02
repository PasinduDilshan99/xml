package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.CountryCodes;
import com.example.test_xml.model.enums.IdentifierTypes;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPersonIdentification {

    @JacksonXmlProperty(localName = "type")
    private IdentifierTypes type;

    @JacksonXmlProperty(localName = "number")
    private String number;

    @JacksonXmlProperty(localName = "issue_date")
    private LocalDateTime issueDate;

    @JacksonXmlProperty(localName = "expired_date")
    private LocalDateTime expiryDate;

    @JacksonXmlProperty(localName = "issued_by")
    private String issuedBy;

    @JacksonXmlProperty(localName = "issue_country")
    private CountryCodes issueCountry;

    @JacksonXmlProperty(localName = "comments")
    private String comments;
}
