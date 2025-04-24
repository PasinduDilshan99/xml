package com.example.test_xml.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDetails extends CommonDetailsResponse{

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

}
