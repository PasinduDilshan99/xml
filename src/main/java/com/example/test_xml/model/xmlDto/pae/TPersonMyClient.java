package com.example.test_xml.model.xmlDto.pae;

import com.example.test_xml.model.enums.EntityPersonRoleTypes;
import com.example.test_xml.model.enums.GenderTypes;
import com.example.test_xml.model.xmlDto.*;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JacksonXmlRootElement(localName = "t_person_my_client")
public class TPersonMyClient {

    @JacksonXmlProperty(localName = "gender")
    private String gender;

    @JacksonXmlProperty(localName = "title")
    private String title;

    @JacksonXmlProperty(localName = "first_name")
    private String firstName;

    @JacksonXmlProperty(localName = "middle_name")
    private String middleName;

    @JacksonXmlProperty(localName = "prefix")
    private String prefix;

    @JacksonXmlProperty(localName = "last_name")
    private String lastName;

    @JacksonXmlProperty(localName = "birthdate")
    private String birthdate;

    @JacksonXmlProperty(localName = "birth_place")
    private String birthPlace;

    @JacksonXmlProperty(localName = "mothers_name")
    private String mothersName;

    @JacksonXmlProperty(localName = "alias")
    private String alias;

    @JacksonXmlProperty(localName = "ssn")
    private String ssn;

    @JacksonXmlProperty(localName = "passport")
    private List<Passport> passports;

    @JacksonXmlProperty(localName = "id_number")
    private String idNumber;

    @JacksonXmlProperty(localName = "nationality1")
    private String nationality1;

    @JacksonXmlProperty(localName = "nationality2")
    private String nationality2;

    @JacksonXmlProperty(localName = "nationality3")
    private String nationality3;

    @JacksonXmlProperty(localName = "residence")
    private String residence;

    @JacksonXmlProperty(localName = "phones")
    private TPhone phone;

    @JacksonXmlProperty(localName = "addresses")
    private TAddress address;

    @JacksonXmlProperty(localName = "email")
    private String email;

    @JacksonXmlProperty(localName = "occupation")
    private String occupation;

    @JacksonXmlProperty(localName = "employer_name")
    private String employerName;

    @JacksonXmlProperty(localName = "employer_address_id")
    private TAddress employerAddressId;

    @JacksonXmlProperty(localName = "employer_phone_id")
    private TPhone employerPhoneId;

    @JacksonXmlProperty(localName = "identification")
    private TPersonIdentification identification;

    @JacksonXmlProperty(localName = "deceased")
    private Deceased deceased;

    @JacksonXmlProperty(localName = "tax_number")
    private String taxNumber;

    @JacksonXmlProperty(localName = "tex_reg_number")
    private String taxRegNumber;

    @JacksonXmlProperty(localName = "source_of_wealth")
    private String sourceOfWealth;

    @JacksonXmlProperty(localName = "comments")
    private String comments;

}
