package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTypesResponse {
    private int transactionId;
    private int txnAmount;
    private String transactionDescription;
    private Date dateTransaction;
    private String transmodeCode;
    private String transactionNumber;
    private String fromWalletType;
    private String fromAccType;
    private int fromAccNo;
    private String toWalletType;
    private String toAccType;
    private int toAccNo;
}
