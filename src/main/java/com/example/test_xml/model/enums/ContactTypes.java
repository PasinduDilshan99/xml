package com.example.test_xml.model.enums;

public enum ContactTypes {
    PRVT("Private"),
    BUSN("Business"),
    CORS("Correspondent"),
    PERM("Permanent"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    ContactTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
