package com.example.test_xml.model.enums;

public enum IdentifierTypes {
    BCER("Birth Certificate"),
    DL("Driver's License"),
    NIC("National Identity Card"),
    PPT("Passport"),
    PID("Postal ID"),
    TID("Temporary ID"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    IdentifierTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
