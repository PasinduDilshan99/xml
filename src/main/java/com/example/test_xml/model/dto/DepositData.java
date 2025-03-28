package com.example.test_xml.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;

@JacksonXmlRootElement(localName = "deposits")
public class DepositData {

    @JacksonXmlElementWrapper(useWrapping = false)  // Ensures correct structure
    private List<DepositDTO> depositList;

    public List<DepositDTO> getDepositList() {
        return depositList;
    }

    public void setDepositList(List<DepositDTO> depositList) {
        this.depositList = depositList;
    }
}
