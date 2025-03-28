package com.example.test_xml.model.enums;

public enum ReportCodes {
    CTR("Cash Transaction Report (CTR)"),
    EFT("Electronic Funds Transfer (EFT)"),
    IFT("International Funds Transfer (IFT)"),
    STR("Suspicious Transaction Report (STR)"),
    AIFT("Additional Information File (AIF) - Transactions"),
    AIFA("Additional Information File (AIF) - Activities"),
    IRD("Information Request – Domestic"),
    CFIR("Call For Information Response (CFIR)"),
    LSTRT("LankaFIN STR (Not Applicable)");

    private final String description;

    ReportCodes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
