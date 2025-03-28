package com.example.test_xml.model.enums;

public enum SubmissionTypes {
    E("Electronically"),
    M("Manually"),
    UNKNOWN("Unknown");

    private final String description;

    SubmissionTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
