package com.example.test_xml.model.enums;

public enum ConductionTypes {
    BRCH("Branch"),
    ATM("ATM/CDM"),
    INTR("Internet/Web portal"),
    TLBN("Telebanking"),
    MOAP("Mobile app"),
    AGNT("Agent/Broker"),
    STOR("Standing order"),
    POS("POS"),
    IPG("IPG"),
    SMS("SMS/USSD"),
    EMAL("Email"),
    LETR("Letter/Post"),
    BKOF("Back office"),
    MBWL("Mobile wallet"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    ConductionTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
