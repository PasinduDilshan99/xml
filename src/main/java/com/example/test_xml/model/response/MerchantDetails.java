package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantDetails extends CommonDetailsResponse{
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
    private boolean branchValidationFlag;
    private String businessEmail;
    private boolean businessAlreadyRegistered;
    private Date businessCommencementDate;
    private String businessRegistrationNumber;
    private String communicationEmail;
    private String contactEmail1;
    private String contactEmail2;
    private String businessPhonePrimary;
    private String businessPhoneSecondary;
    private String contactPerson1;
    private String contactPerson2;
    private boolean differentMailingAddress;
    private String faxNumber;
    private String facebookAccount;
    private String googlePlusAccount;
    private String hotline;
    private String incomeTaxFileNumber;
    private String insurancePaymentReference;
    private String linkedinAccount;
    private double lowerTransactionLimit;
    private String merchantCategoryCode;
    private String merchantShortCode;
    private String notes;
    private String otherBusinessContacts;
    private String otpNumber;
    private String paymentConfirmationMobileNo;
    private boolean paymentConfirmationSmsRequired;
    private String paymentMode;
    private String paymentProcessingMode;
    private String residenceCategory;
    private String settlementMethod;
    private String settlementPlan;
    private String skypeAccount;
    private String sourceOfIncome;
    private String otherSourceOfIncome;
    private String storeLabel;
    private String testValue;
    private String transactionMethod;
    private String twitterAccount;
    private double upperTransactionLimit;
    private String utilityPaymentReference;
    private String utilityCompany;
    private String webAddress;
}
