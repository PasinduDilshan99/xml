package com.example.test_xml.model.xmlDto;

import com.example.test_xml.model.enums.CommunicationTypes;
import com.example.test_xml.model.enums.ContactTypes;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TPhone {

    @JacksonXmlProperty(localName = "tph_contact_type")
    private ContactTypes tphContactType;

    @JacksonXmlProperty(localName = "tph_communication_type")
    private CommunicationTypes tphCommunicationType;

    @JacksonXmlProperty(localName = "tph_country_prefix")
    private String tphCountryPrefix;

    @JacksonXmlProperty(localName = "tph_number")
    private String tphNumber;

    @JacksonXmlProperty(localName = "tph_extension")
    private String tphExtension;

    @JacksonXmlProperty(localName = "comments")
    private String comments;


}
