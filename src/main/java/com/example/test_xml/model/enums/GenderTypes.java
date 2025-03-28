package com.example.test_xml.model.enums;

public enum GenderTypes {
    M("Male"),
    F("Female"),
    O("Other"),
    UNKNOWN("Unknown");

    private final String description;

    GenderTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
