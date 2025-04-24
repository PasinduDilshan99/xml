package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetUserTypesResponse {

    private int transactionId;
    private BigDecimal txnAmount;
    private String transactionDescription;
    private Timestamp dateTransaction;
    private String transmodeCode;
    private String transactionNumber;
    private String utilityType;
    private String fromWalletType;
    private String fromAccType;
    private int fromAccNo;
    private String toWalletType;
    private String toAccType;
    private int toAccNo;

}
