package com.example.test_xml.model.response;

import com.example.test_xml.model.enums.db.UserTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModifyUserTypesResponse {
    private int transactionId;
    private int txnAmount;
    private String transactionDescription;
    private Date dateTransaction;
    private String transmodeCode;
    private String transactionNumber;
    private UserTypes fromUserTypes;
    private int fromAccNo;
    private UserTypes toUserTypes;
    private int toAccNo;
}
