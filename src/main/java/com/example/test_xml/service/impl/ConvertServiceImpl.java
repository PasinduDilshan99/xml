package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.response.*;
import com.example.test_xml.model.xmlDto.TAddress;
import com.example.test_xml.model.xmlDto.TPhone;
import com.example.test_xml.model.xmlDto.from.FromEntity;
import com.example.test_xml.model.xmlDto.from.FromPerson;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.pae.TEntityMyClient;
import com.example.test_xml.model.xmlDto.pae.TPersonMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;
import com.example.test_xml.service.PreProcessService;
import com.example.test_xml.service.ConvertService;
import com.example.test_xml.validationServices.TEntityMyClientValidationService;
import com.example.test_xml.validationServices.TPersonMyClientValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertServiceImpl implements ConvertService {

    private final Logger logger = LoggerFactory.getLogger(ConvertServiceImpl.class);

    private final PreProcessService preProcessService;
    private final TPersonMyClientValidation tPersonMyClientValidation;
    private final TEntityMyClientValidationService tEntityMyClientValidationService;

    @Autowired
    public ConvertServiceImpl(PreProcessService preProcessService,
                              TPersonMyClientValidation tPersonMyClientValidation,
                              TEntityMyClientValidationService tEntityMyClientValidationService) {
        this.preProcessService = preProcessService;
        this.tPersonMyClientValidation = tPersonMyClientValidation;
        this.tEntityMyClientValidationService = tEntityMyClientValidationService;
    }


    @Override
    public TFromMyClient convertDataToFromMyClient(CommonDetails from) {
        TFromMyClient tFromMyClient = new TFromMyClient();
        tFromMyClient.setFromFundsCode(FundsTypes.MOBL); //

        if (from.getUserType().equals(UserTypes.CUSTOMER)) {
            tFromMyClient.setTPersonMyClient(createFromPersonForFrom(from));
            // tPersonMyClient validation
//            tPersonMyClientValidation.validateTPersonMyClient(tFromMyClient.getTPersonMyClient());
        } else if (from.getUserType().equals(UserTypes.MERCHANT) || from.getUserType().equals(UserTypes.RETAILER)) {
            tFromMyClient.setTEntityMyClient(createFromEntityForFrom(from));
            // tEntityMyClient validation
        }
        tFromMyClient.setFromFundsComment(null);
        tFromMyClient.setFromForeignCurrency(null);
        tFromMyClient.setTConductor(null);
        tFromMyClient.setFromCountry(CountryCodes.LK); //
        return tFromMyClient;
    }

    private FromEntity createFromEntityForFrom(CommonDetails from){
        FromEntity fromEntity = new FromEntity();
        fromEntity.setName(from.getFirstName()); //
        fromEntity.setCommercialName(null);
        fromEntity.setIncorporationLegalForm(null); //
        fromEntity.setIncorporationNumber(from.getBusinessRegistrationNumber());
        fromEntity.setBusiness(null); //
        if (from.getBusinessPhonePrimary() != null){
            if (from.getBusinessPhonePrimary().trim().startsWith("07")) {
                fromEntity.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.MOPH, "94", from.getBusinessPhonePrimary(), null, null));
            } else {
                fromEntity.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.LNPH, "94", from.getBusinessPhonePrimary(), null, null));
            }
        }
        fromEntity.setAddress(new TAddress(ContactTypes.BUSN, from.getAddressLine1() + from.getAddressLine2() +from.getAddressLine3(), null,null, from.getCity(), null, CountryCodes.LK.toString(), null, null)); //
        fromEntity.setEmail(from.getBusinessEmail()); //
        fromEntity.setUrl(from.getWebAddress());
        fromEntity.setIncorporationState(null);
        fromEntity.setIncorporationCountryCode(CountryCodes.LK.toString()); //
        fromEntity.setDirectorId(null); //
        fromEntity.setIncorporationDate(null);
        fromEntity.setBusinessClosed(null);
        fromEntity.setDateBusinessClosed(null);
        fromEntity.setTaxNumber(from.getIncomeTaxFileNumber());
        fromEntity.setTaxNumber(null);
        fromEntity.setComments(null);
        return fromEntity;
    }

    private FromPerson createFromPersonForFrom(CommonDetails from){
        FromPerson fromPerson = new FromPerson();
        fromPerson.setGender(null);
        fromPerson.setTitle(null);
        ExtractNameResponse extractNameResponse = preProcessService.extractFirstNameAndLastName(from.getFirstName());
        fromPerson.setFirstName(extractNameResponse.getFirstName()); //
        fromPerson.setMiddleName(null);
        fromPerson.setPrefix(null);
        fromPerson.setLastName(extractNameResponse.getLastName().toUpperCase()); //
        fromPerson.setBirthdate(String.valueOf(from.getBirthdate())); //
        fromPerson.setBirthPlace(null);
//        fromPerson.setMothersName(preProcessService.decodeBase64(from.getMothersName()));
        fromPerson.setAlias(null);
        fromPerson.setSsn(from.getIdNumber()); //NIC
        fromPerson.setPassports(null);
        fromPerson.setIdNumber(null);
//        fromPerson.setNationality1(preProcessService.convertNationality(from.getNationality1())); // used category
        fromPerson.setNationality2(null);
        fromPerson.setNationality3(null);
        fromPerson.setResidence(from.getResidenceCategory()); // used category
//        fromPerson.setPhone(new TPhone(ContactTypes.PRVT, CommunicationTypes.MOPH, "94", from.getPhonesPrimary(), null, null));
        fromPerson.setPhone(null);
//        fromPerson.setAddress(new TAddress(ContactTypes.PERM, from.getAddressLine1() + from.getAddressLine2() + from.getAddressLine3(), null, from.getCity(), null, CountryCodes.LK.toString(), null, null)); //
        fromPerson.setEmail(from.getEmail());
        fromPerson.setOccupation(from.getOccupation()); //
        fromPerson.setEmployerName(from.getEmployerName());
//        fromPerson.setEmployerAddressId(new TAddress(ContactTypes.PERM, from.getEmployerAddress(), null, null, null, CountryCodes.LK.toString(), null, null));
        fromPerson.setEmployerPhoneId(null);
        fromPerson.setIdentification(null);
        fromPerson.setDeceased(null);
        fromPerson.setTaxNumber(from.getIncomeTaxFileNumber());
        fromPerson.setTaxRegNumber(null);
        fromPerson.setSourceOfWealth(null);
        fromPerson.setComments(null);
//        tPersonMyClientValidation.validateTPersonMyClient(fromPerson);
        return fromPerson;
    }

    @Override
    public TToMyClient convertDataTOTTOMyClient(CommonDetails to) {
        TToMyClient tToMyClient = new TToMyClient();
        tToMyClient.setToFundsCode(FundsTypes.MOBL);
        tToMyClient.setToFundsComment(null);
        tToMyClient.setToForeignCurrency(null);
        ToAccount toAccount = convertDataToToAccount(to);
        tToMyClient.setToAccount(toAccount);
        tToMyClient.setToCountry(CountryCodes.LK);
        return tToMyClient;
    }


    @Override
    public ToAccount convertDataToToAccount(CommonDetails to) {
        ToAccount toAccount = new ToAccount();
        toAccount.setInstitutionName("Mobitel (Pvt) Ltd"); //
        toAccount.setInstitutionCode(null);
        toAccount.setSwift("4001"); //
        toAccount.setNonBankInstitution(Boolean.TRUE);
        toAccount.setBranch(to.getBranchCode()); //
        toAccount.setAccount(to.getBankAccountNumber()); //
        toAccount.setCurrencyCode(Currencies.LKR); //
        toAccount.setAccountName(null);
        toAccount.setIban(null);
        toAccount.setClientNumber(null);
        toAccount.setPersonalAccountType(AccountTypes.MOBW);
        toAccount.setIsPrimary(null);
        toAccount.setRole(null);
        toAccount.setOpened(null);
        toAccount.setClosed(null);
        toAccount.setBalance(null);
        toAccount.setDateBalance(null);
        toAccount.setStatusCode(AccountStatusTypes.ACTV.toString()); //
        toAccount.setBeneficiary(null);
        toAccount.setBeneficiaryComment(null);
        toAccount.setComments(null);
        toAccount.setTEntity(createTEntityMyClientForTo(to));
//        if (to.getUserType().equals(UserTypes.CUSTOMER)) {
//            toAccount.setSignatory(createTPersonClientForTo(to));
//        } else if (to.getUserType().equals(UserTypes.MERCHANT) || to.getUserType().equals(UserTypes.RETAILER)) {
//            toAccount.setTEntity(createTEntityMyClientForTo(to));
//        }
        return toAccount;
    }

    private TPersonMyClient createTPersonClientForTo(CommonDetails to){
        TPersonMyClient tPersonMyClient = new TPersonMyClient();
        tPersonMyClient.setGender(null);
        tPersonMyClient.setTitle(null);
        tPersonMyClient.setFirstName(to.getFirstName()); //
        tPersonMyClient.setMiddleName(null);
        tPersonMyClient.setPrefix(null);
        tPersonMyClient.setLastName(null); //
        tPersonMyClient.setBirthdate(String.valueOf(to.getBirthdate())); //
        tPersonMyClient.setBirthPlace(null);
        tPersonMyClient.setMothersName(to.getMothersName());
        tPersonMyClient.setAlias(null);
        tPersonMyClient.setSsn(to.getIdNumber()); //
        tPersonMyClient.setPassports(null);
        tPersonMyClient.setIdNumber(null);
        tPersonMyClient.setNationality1(to.getNationality1()); // used category
        tPersonMyClient.setNationality2(null);
        tPersonMyClient.setNationality3(null);
        tPersonMyClient.setResidence(to.getResidenceCategory()); // used category
        tPersonMyClient.setPhone(new TPhone(ContactTypes.PRVT, CommunicationTypes.MOPH, "94", to.getPhonesPrimary(), null, null));
//        tPersonMyClient.setAddress(new TAddress(ContactTypes.UNKNOWN, to.getAddressLine1() + to.getAddressLine2() + to.getAddressLine3(), null, to.getCity(), null, CountryCodes.LK.toString(), null, null)); //
        tPersonMyClient.setEmail(to.getEmail());
        tPersonMyClient.setOccupation(to.getOccupation()); //
        tPersonMyClient.setEmployerName(to.getEmployerName());
//        tPersonMyClient.setEmployerAddressId(new TAddress(ContactTypes.UNKNOWN, to.getEmployerAddress(), null, null, null, CountryCodes.LK.toString(), null, null));
        tPersonMyClient.setEmployerPhoneId(null);
        tPersonMyClient.setIdentification(null);
        tPersonMyClient.setDeceased(null);
        tPersonMyClient.setTaxNumber(to.getIncomeTaxFileNumber());
        tPersonMyClient.setTaxRegNumber(null);
        tPersonMyClient.setSourceOfWealth(null);
        tPersonMyClient.setComments(null);
        return tPersonMyClient;
    }

    private TEntityMyClient createTEntityMyClientForTo(CommonDetails to){
        TEntityMyClient tEntityMyClient = new TEntityMyClient();
        tEntityMyClient.setName(to.getFirstName()); //
        tEntityMyClient.setCommercialName(null);
        tEntityMyClient.setIncorporationLegalForm(null); //
        tEntityMyClient.setIncorporationNumber(to.getBusinessRegistrationNumber());
        tEntityMyClient.setBusiness(null); //
        if (to.getBusinessPhonePrimary() != null){
            if (to.getBusinessPhonePrimary().trim().startsWith("07")) {
                tEntityMyClient.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.MOPH, "94", to.getBusinessPhonePrimary(), null, null));
            } else {
                tEntityMyClient.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.LNPH, "94", to.getBusinessPhonePrimary(), null, null));
            }
        }
//        tEntityMyClient.setAddress(new TAddress(ContactTypes.UNKNOWN, to.getAddressLine1() + to.getAddressLine2() + to.getAddressLine3(), null, to.getCity(), null, CountryCodes.LK.toString(), null, null)); //
        tEntityMyClient.setEmail(to.getBusinessEmail()); //
        tEntityMyClient.setUrl(to.getWebAddress());
        tEntityMyClient.setIncorporationState(null);
        tEntityMyClient.setIncorporationCountryCode(CountryCodes.LK.toString()); //
        tEntityMyClient.setDirectorId(null); //
        tEntityMyClient.setIncorporationDate(null);
        tEntityMyClient.setBusinessClosed(null);
        tEntityMyClient.setDateBusinessClosed(null);
        tEntityMyClient.setTaxNumber(to.getIncomeTaxFileNumber());
        tEntityMyClient.setTaxNumber(null);
        tEntityMyClient.setComments(null);
        return tEntityMyClient;
    }

}
