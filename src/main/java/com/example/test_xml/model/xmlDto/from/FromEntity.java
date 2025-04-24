package com.example.test_xml.model.xmlDto.from;

import com.example.test_xml.model.xmlDto.pae.TEntityMyClient;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "from_entity")
public class FromEntity extends TEntityMyClient {}
