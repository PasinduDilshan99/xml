package com.example.test_xml.model.enums;

public enum PartyTypes {
    ACHLD("Account Holder"),
    BENF("Beneficiary"),
    NOMIN("Nominee"),
    PRMAC("Primary Account"),
    PRSP("Primary Suspect"),
    PWRAT("Power of Attorney"),
    PYEE("Payee"),
    PYER("Payer"),
    RELAC("Related Account"),
    RPRY("Related Party"),
    SIGNT("Signatory"),
    VCTM("Victim"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    PartyTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
