package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    private int transactionId;
    private BigDecimal txnAmount;
    private String transactionDescription;
    private Timestamp dateTransaction;
    private String transactionNumber;
    private String transmodeCode;
    private Integer fromAccountId;
    private CommonDetails from;
    private Integer toAccountId;
    private CommonDetails to;

}
