package com.example.test_xml.model.enums;

public enum Currencies {
    ACU("Asian Clearing Unit"),
    AED("UAE Dirham"),
    AFN("Afghani"),
    ALL("Lek"),
    AMD("Armenian Dram"),
    ANG("Netherlands Antillean Guilder"),
    AOA("Kwanza"),
    ARS("Argentine Peso"),
    AUD("Australian Dollar"),
    AWG("Aruban Florin"),
    AZN("Azerbaijan Manat"),
    BAM("Convertible Mark"),
    BBD("Barbados Dollar"),
    BDT("Taka"),
    BGN("Bulgarian Lev"),
    BHD("Bahraini Dinar"),
    BIF("Burundi Franc"),
    BMD("Bermudian Dollar"),
    BND("Brunei Dollar"),
    BOB("Boliviano"),
    BRL("Brazilian Real"),
    BSD("Bahamian Dollar"),
    BTN("Ngultrum"),
    BWP("Pula"),
    BYN("Belarusian Ruble"),
    BZD("Belize Dollar"),
    CAD("Canadian Dollar"),
    CDF("Congolese Franc"),
    CHF("Swiss Franc"),
    CLP("Chilean Peso"),
    CNY("Yuan Renminbi"),
    COP("Colombian Peso"),
    CRC("Costa Rican Colon"),
    CUP("Cuban Peso"),
    CZK("Czech Koruna"),
    DKK("Danish Krone"),
    DOP("Dominican Peso"),
    DZD("Algerian Dinar"),
    EGP("Egyptian Pound"),
    ERN("Nakfa"),
    ETB("Ethiopian Birr"),
    EUR("Euro"),
    FJD("Fiji Dollar"),
    GBP("Pound Sterling"),
    GEL("Lari"),
    GHS("Ghana Cedi"),
    GIP("Gibraltar Pound"),
    GMD("Dalasi"),
    GNF("Guinean Franc"),
    GTQ("Quetzal"),
    GYD("Guyana Dollar"),
    HKD("Hong Kong Dollar"),
    HNL("Lempira"),
    HRK("Kuna"),
    HTG("Gourde"),
    HUF("Forint"),
    IDR("Rupiah"),
    ILS("New Israeli Sheqel"),
    INR("Indian Rupee"),
    IQD("Iraqi Dinar"),
    IRR("Iranian Rial"),
    ISK("Iceland Krona"),
    JMD("Jamaican Dollar"),
    JOD("Jordanian Dinar"),
    JPY("Yen"),
    KES("Kenyan Shilling"),
    KHR("Riel"),
    KRW("Won"),
    KWD("Kuwaiti Dinar"),
    KZT("Tenge"),
    LAK("Lao Kip"),
    LBP("Lebanese Pound"),
    LKR("Sri Lanka Rupee"),
    MAD("Moroccan Dirham"),
    MDL("Moldovan Leu"),
    MMK("Kyat"),
    MNT("Tugrik"),
    MXN("Mexican Peso"),
    MYR("Malaysian Ringgit"),
    NOK("Norwegian Krone"),
    NPR("Nepalese Rupee"),
    NZD("New Zealand Dollar"),
    OMR("Rial Omani"),
    PAB("Balboa"),
    PEN("Sol"),
    PHP("Philippine Peso"),
    PKR("Pakistan Rupee"),
    PLN("Zloty"),
    PYG("Guarani"),
    QAR("Qatari Rial"),
    RON("Romanian Leu"),
    RSD("Serbian Dinar"),
    RUB("Russian Ruble"),
    SAR("Saudi Riyal"),
    SCR("Seychelles Rupee"),
    SEK("Swedish Krona"),
    SGD("Singapore Dollar"),
    THB("Baht"),
    TND("Tunisian Dinar"),
    TRY("Turkish Lira"),
    TWD("New Taiwan Dollar"),
    USD("US Dollar"),
    VND("Dong"),
    ZAR("Rand");

    private final String description;

    Currencies(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}