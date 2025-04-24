package com.example.test_xml.model.xmlDto.from;

import com.example.test_xml.model.xmlDto.pae.TPersonMyClient;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "from_person")
public class FromPerson extends TPersonMyClient {}
