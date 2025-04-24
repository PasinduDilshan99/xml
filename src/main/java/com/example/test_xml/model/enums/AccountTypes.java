package com.example.test_xml.model.enums;

public enum AccountTypes {
    CRNT("Current"),
    SAVG("Savings"),
    TRDP("Term deposit"),
    CODP("Certificate of deposit"),
    LOAN("Loan"),
    LEAS("Leasing"),
    PAWN("Pawning"),
    MORT("Mortgage"),
    TRDG("Trading"),
    TRST("Trust"),
    SDBX("Safe deposit box"),
    MOBW("Mobile wallet"),
    ELCW("Electronic wallet"),
    DIGW("Digital wallet"),
    INPL("Insurance policy - Life"),
    INPG("Insurance policy - General"),
    DEBT("Debts"),
    EQTY("Equities"),
    CRCD("Credit card"),
    INVS("Investment"),
    MGTD("Margin trading"),
    FCTR("Factoring"),
    OTH("Other"),
    MINR("Minor"),
    UNKNOWN("Unknown");

    private final String description;

    AccountTypes(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
