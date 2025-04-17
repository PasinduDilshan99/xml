package com.example.test_xml.util;

public class RegexPattern {

    public static final String OLD_NIC_PATTERN = "^[0-9]{9}[VX]$";
    public static final String NEW_NIC_PATTERN = "^[0-9]{12}$";
    public static final String PASSPORT_NUMBER_PATTERN = "^[a-zA-Z0-9]+$";
    public static final String EMAIL_ADDRESS_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String MOBILE_NUMBER_PATTERN = "^\\d+$";
    public static final String INCORPORATION_NUMBER_PATTERN = "^[A-Z]{2}\\d+$";
    public static final String SWIFT_PATTERN = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$";
    public static final String LANKA_CLEAR_PATTERN = "^\\d{4,}$";
    public static final String LANKA_FIN_PATTERN = "^\\d{4,}$";
    public static final String DATE_FORMAT_PATTERN = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";

}
