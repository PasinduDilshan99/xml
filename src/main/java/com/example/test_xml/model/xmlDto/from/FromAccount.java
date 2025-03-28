package com.example.test_xml.model.xmlDto.from;

import com.example.test_xml.model.xmlDto.pae.TAccountMyClient;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@JacksonXmlRootElement(localName = "from_account")
public class FromAccount extends TAccountMyClient {
}
