package com.example.test_xml.model.enums;

public enum Cities {
    AMPARA("Ampara"),
    ANURADHAPURA("Anuradhapura"),
    BADULLA("Badulla"),
    BATTICALOA("Batticaloa"),
    COLOMBO("Colombo"),
    GALLE("Galle"),
    GAMPAHA("Gampaha"),
    HAMBANTOTA("Hambantota"),
    JAFFNA("Jaffna"),
    KALUTARA("Kalutara"),
    KANDY("Kandy"),
    KEGALLE("Kegalle"),
    KILINOCHCHI("Kilinochchi"),
    KURUNEGALA("Kurunegala"),
    MANNAR("Mannar"),
    MATALE("Matale"),
    MATARA("Matara"),
    MONERAGALA("Moneragala"),
    MULLAITIVU("Mullaitivu"),
    NUWARA_ELIYA("Nuwara Eliya"),
    POLONNARUWA("Polonnaruwa"),
    PUTTALAM("Puttalam"),
    RATNAPURA("Ratnapura"),
    TRINCOMALEE("Trincomalee"),
    VAVUNIYA("Vavuniya"),
    FOREIGN("FOREIGN");

    private final String displayName;

    Cities(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
