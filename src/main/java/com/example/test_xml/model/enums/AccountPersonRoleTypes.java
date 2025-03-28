package com.example.test_xml.model.enums;

public enum AccountPersonRoleTypes {
    ACHLD("Account Holder"),
    SUPC("Supplementary Card Holder"),
    BENEF("Beneficiary"),
    LGGUD("Legal Guardian"),
    PWRAT("Power of Attorney"),
    NOMIN("Nominee"),
    GUAR("Guarantor"),
    REF("Referee"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    AccountPersonRoleTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
