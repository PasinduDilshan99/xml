package com.example.test_xml.model.enums;

public enum AccountStatusTypes {

    ACTV("Active"),
    CLSD("Closed"),
    CLBC("Closed by customer"),
    CLRE("Closed by reporting entity"),
    DRMT("Dormant"),
    INAC("Inactive"),
    FRZN("Frozen/Suspended"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    AccountStatusTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
