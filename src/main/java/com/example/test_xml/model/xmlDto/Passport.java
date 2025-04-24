package com.example.test_xml.model.xmlDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passport {

    @JacksonXmlProperty(localName = "passport_number")
    private String passportNumber;

    @JacksonXmlProperty(localName = "passport_country")
    private String passportCountry;

}
