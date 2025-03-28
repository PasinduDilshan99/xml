package com.example.test_xml.model.enums;

public enum EntityLegalFormTypes {
    SOLE("Sole Proprietorship"),
    PTNR("Partnership"),
    PVTL("Private Limited Company"),
    PUBL("Public Limited Company"),
    UNLT("Unlimited Company"),
    GOVT("Government Institution"),
    STAT("State Owned Enterprises"),
    FNDT("Foundation/Other legal arrangements"),
    TRST("Trust"),
    NGO("NGO"),
    CLUB("Club"),
    SOCT("Society"),
    CHTY("Charity"),
    ASOC("Association"),
    OFSH("Offshore/Overseas Company"),
    LTDG("Limited by Guarantee"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    EntityLegalFormTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
