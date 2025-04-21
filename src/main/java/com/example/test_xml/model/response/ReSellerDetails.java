package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReSellerDetails extends CommonDetailsResponse{
    private int id;
    private String firstName;
    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String alias;
    private String title;
    private String idNumber;
    private Timestamp birthdate;
    private String mothersName;
    private String nationality1;
    private String city;
    private String state;
    private String businessType;
    private String phonesPrimary;
    private String phonesSecondary;
    private String email;
    private String occupation;
    private String employerName;
    private String employerAddress;
    private String sourceOfWealth;
    private boolean userVerified;
    private boolean documentsVerified;
    private String annualIncome;
    private String bankAccountNumber;
    private String bankCode;
    private String bankName;
    private String branchCode;
    private String branchName;
    private boolean businessAlreadyRegistered;
    private Date businessCommencementDate;
    private String businessRegistrationNumber;
    private String businessPhonePrimary;
    private String businessPhoneSecondary;
    private String businessContactPerson;
    private boolean differentMailingAddress;
    private String businessEmail;
    private String incomeTaxFileNumber;
    private String otherConnectedBusiness;
    private String partnerCode;
    private String settlementStatus;
    private String settlementMethod;
    private String sourceOfIncome;
    private String sourceOfIncomeOther;
}
