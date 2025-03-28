package com.example.test_xml.model.enums;

public enum CommunicationTypes {
    FAX("Fax"),
    LNPH("Landline Phone"),
    MOPH("Mobile Phone"),
    PAGR("Pager"),
    STPH("Satellite Phone"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    CommunicationTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
