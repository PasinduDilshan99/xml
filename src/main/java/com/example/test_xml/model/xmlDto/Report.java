package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.Currencies;
import com.example.test_xml.model.enums.ReportCodes;
import com.example.test_xml.model.enums.ReportIndicatorTypes;
import com.example.test_xml.model.enums.SubmissionTypes;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "report")
public class Report {

    @JacksonXmlProperty(localName = "rentity_id")
    private Integer rentityId;

    @JacksonXmlProperty(localName = "rentity_branch")
    private String rentityBranch;

    @JacksonXmlProperty(localName = "submission_code")
    private SubmissionTypes submissionCode;

    @JacksonXmlProperty(localName = "report_code")
    private ReportCodes reportCode;

    @JacksonXmlProperty(localName = "entity_reference")
    private String entityReference;

    @JacksonXmlProperty(localName = "fiu_ref_number")
    private String fiuRefNumber;

    @JacksonXmlProperty(localName = "submission_date")
    private Date submissionDate;

    @JacksonXmlProperty(localName = "currency_code_local")
    private Currencies currencyCodeLocal;

    @JacksonXmlProperty(localName = "reporting_person")
    private TPersonRegistrationInReport reportingPerson;

    @JacksonXmlProperty(localName = "location")
    private TAddress location;

    @JacksonXmlProperty(localName = "reason")
    private String reason;

    @JacksonXmlProperty(localName = "action")
    private String action;

    @JacksonXmlProperty(localName = "transaction")
    private List<Transaction> transaction;

    @JacksonXmlProperty(localName = "report_indicators")
    private ReportIndicator reportIndicator;


}
