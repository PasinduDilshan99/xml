package com.example.test_xml.model.response;

import com.example.test_xml.model.enums.db.UserTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonDetails {
    private int id;
    private String firstName;
    private String addressName;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String alias;
    private String title;
    private String idNumber;
    private Date birthdate;
    private String mothersName;
    private String nationality1;
    private String city;
    private String state;
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
    private Boolean businessAlreadyRegistered;
    private Date businessCommencementDate;
    private String businessRegistrationNumber;
    private String businessPhonePrimary;
    private String businessPhoneSecondary;
    private String businessContactPerson;
    private Boolean differentMailingAddress;
    private String businessEmail;
    private String incomeTaxFileNumber;
    private String otherConnectedBusiness;
    private String partnerCode;
    private String settlementStatus;
    private String settlementMethod;
    private String sourceOfIncome;
    private String sourceOfIncomeOther;
    private Boolean branchValidationFlag;
    private String communicationEmail;
    private String contactEmail1;
    private String contactEmail2;
    private String contactPerson1;
    private String contactPerson2;
    private String faxNumber;
    private String facebookAccount;
    private String googlePlusAccount;
    private String hotline;
    private String insurancePaymentReference;
    private String linkedinAccount;
    private Double lowerTransactionLimit;
    private String merchantCategoryCode;
    private String merchantShortCode;
    private String notes;
    private String otherBusinessContacts;
    private String otpNumber;
    private String paymentConfirmationMobileNo;
    private Boolean paymentConfirmationSmsRequired;
    private String paymentMode;
    private String paymentProcessingMode;
    private String residenceCategory;
    private String settlementPlan;
    private String skypeAccount;
    private String storeLabel;
    private String testValue;
    private String transactionMethod;
    private String twitterAccount;
    private Double upperTransactionLimit;
    private String utilityPaymentReference;
    private String utilityCompany;
    private String webAddress;
    private UserTypes userType;

}
