package com.example.test_xml.model.response;

import com.example.test_xml.model.enums.db.UserTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    private int transactionId;
    private int txnAmount;
    private String transactionDescription;
    private Date dateTransaction;
    private String transactionNumber;
    private String transmodeCode;
    private Integer fromAccountId;
    private CommonDetails from;
    private Integer toAccountId;
    private CommonDetails to;
}
