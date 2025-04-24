package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.ReportIndicatorTypes;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportIndicator {

    @JacksonXmlProperty(localName = "indicator")
    private ReportIndicatorTypes indicator;

}
