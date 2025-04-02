package com.example.test_xml.service;


import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.xmlDto.*;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;


@Service
public class DepositService {



//    public String getXmlDeposits() throws Exception {
//
//
//        // Wrap inside DepositData
//        DepositData dto = new DepositData();
//        dto.setDepositList(depositDTOs);
//
//        // Convert to XML
//        XmlMapper xmlMapper = new XmlMapper();
//        return xmlMapper.writeValueAsString(dto);
//    }

    public String getTest() throws JsonProcessingException {
        TPersonRegistrationInReport reportingPerson = new TPersonRegistrationInReport(
                GenderTypes.M,  // gender
                "Mr.",            // title
                "John",           // firstName
                "A.",             // middleName
                "Dr.",            // prefix
                "Doe",            // lastName
                null, // birthdate
                "New York",       // birthPlace
                "Jane Doe",       // mothersName
                "JD",             // alias
                "123-45-6789",    // ssn
                new Passport(),   // passport
                "ID123456",       // idNumber
                "USA",            // nationality1
                "Canada",         // nationality2
                "UK",             // nationality3
                "California",     // residence
                new TPhone(),     // phone
                new TAddress(),   // address
                "john.doe@email.com", // email
                "Software Engineer",  // occupation
                "Tech Corp",          // employerName
                new TAddress(),       // employerAddressId
                new TPhone(),         // employerPhoneId
                new TPersonIdentification(), // identification
                new Deceased(),       // deceased
                "TAX-123456",         // taxNumber
                "TAXREG-789",         // taxRegNumber
                "Software business",  // sourceOfWealth
                "No comments"         // comments
        );

        TAddress location = new TAddress(
                ContactTypes.BUSN, // addressType
                "123 Main St",     // address
                "New York",        // town
                null,
                "10001",           // zip
                CountryCodes.MA,   // country_code
                "NY",              // State
                "Home",            // tphContactType
                "No additional info" // comments
        );

        Transaction transaction = new Transaction(
                "TXN-001",             // transactionNumber
                "REF-456",             // internalRefNumber
                "New York Branch",     // transactionLocation
                "Wire Transfer",       // transactionDescription
                null,   // dateTransaction
                "Teller123",           // teller
                "John Manager",        // authorized
                false,                 // lateDeposit
                null,   // datePosting
                null,   // valueDate
                ConductionTypes.EMAL,  // transmodeCode
                "Handled in person",   // transmodeComment
                null,               // amountLocal
                new TFromMyClient(),   // tFromMyClient
null        ,        "Electronics Purchase", // goodsServices
                "Urgent transaction"   // comments
        );

        // Creating the Report object using the parameterized constructor
        Report report = new Report(
                123,                             // rentityId
                "Main Branch",                   // rentityBranch
                SubmissionTypes.M,          // submissionCode (Replace with actual enum)
                ReportCodes.AIFT,          // reportCode (Replace with actual enum)
                "ENT-456789",                    // entityReference
                "FIU-987654",                    // fiuRefNumber
                null,             // submissionDate
                Currencies.USD,                  // currencyCodeLocal (Replace with actual enum)
                reportingPerson,                 // reportingPerson
                location,                         // location
                "Suspicious activity detected",  // reason
                "Further investigation required", // action
                null,                      // transaction
                null       // reportIndicators (Replace with actual enum)
        );
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.writeValueAsString(report);
    }
}