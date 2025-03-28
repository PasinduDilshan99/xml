package com.example.test_xml.model.enums;

public enum FundsTypes {
    CASH("Cash"),
    ELEC("Electronic"),
    MOBL("Mobile"),
    CRDT("Credit"),
    VRTL("Virtual currency"),
    CHEQ("Cheque"),
    BDRF("Bank draft"),
    BGUR("Bank guarantee"),
    PROM("Promissory note"),
    INBL("Inward Bill"),
    OUBL("Outward Bill"),
    TCHQ("Traveler’s cheque"),
    STOC("Stocks"),
    LOAN("Loan"),
    LOCR("Letter of credit"),
    FCTR("Factor"),
    GVSE("Government securities"),
    SHAR("Shares"),
    CDEB("Corporate Debt"),
    TAX("Tax"),
    BKCG("Bank charges"),
    LSNG("Leasing"),
    PWNG("Pawning"),
    INTS("Interest"),
    MRGN("Margin"),
    OTH("Other"),
    UNKNOWN("Unknown");

    private final String description;

    FundsTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
