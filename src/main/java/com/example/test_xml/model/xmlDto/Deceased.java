package com.example.test_xml.model.xmlDto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deceased {
    @JacksonXmlProperty(localName = "deceased")
    private String deceased;

    @JacksonXmlProperty(localName = "date_deceased")
    private String dateDeceased;
}
