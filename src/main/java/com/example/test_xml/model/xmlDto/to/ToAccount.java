package com.example.test_xml.model.xmlDto.to;

import com.example.test_xml.model.xmlDto.pae.TAccountMyClient;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "to_account")
public class ToAccount extends TAccountMyClient {}
