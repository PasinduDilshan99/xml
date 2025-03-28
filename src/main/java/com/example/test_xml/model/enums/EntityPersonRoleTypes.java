package com.example.test_xml.model.enums;

public enum EntityPersonRoleTypes {
    DRCT("Director"),
    BMEM("Board Member"),
    CEO("CEO/MD"),
    CSEC("Company Secretary"),
    BENE("Beneficiary"),
    SHLD("Shareholder"),
    UBO("Ultimate Beneficial Owner"),
    TRST("Trustee/Co-Trustee"),
    SETL("Settler"),
    CHPE("Chairperson"),
    SECT("Secretary"),
    TRSR("Treasurer"),
    OWNR("Owner"),
    PRNR("Partner"),
    HOIN("Head of Institution"),
    GURN("Guarantor for a Loan"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    EntityPersonRoleTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
