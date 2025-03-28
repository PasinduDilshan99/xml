package com.example.test_xml.model.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DepositDTO {

    @JacksonXmlProperty(localName = "late_deposit")
    private boolean lateDeposit;

    @JacksonXmlProperty(localName = "date_posting")
    private String datePosting;

    // Constructor
    public DepositDTO(boolean lateDeposit, String datePosting) {
        this.lateDeposit = lateDeposit;
        this.datePosting = datePosting;
    }

    // Getters and Setters
    public boolean isLateDeposit() {
        return lateDeposit;
    }

    public void setLateDeposit(boolean lateDeposit) {
        this.lateDeposit = lateDeposit;
    }

    public String getDatePosting() {
        return datePosting;
    }

    public void setDatePosting(String datePosting) {
        this.datePosting = datePosting;
    }
}