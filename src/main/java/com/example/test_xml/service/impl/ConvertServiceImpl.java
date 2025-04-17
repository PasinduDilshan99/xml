package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.otherEnums.BusinessCategory;
import com.example.test_xml.model.otherEnums.OccupationCategory;
import com.example.test_xml.model.response.*;
import com.example.test_xml.model.xmlDto.DirectorId;
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
        tFromMyClient.setFromFundsComment(null);
//        if (tFromMyClient.getFromFundsCode() != null && tFromMyClient.getFromFundsCode().equals(FundsTypes.OTH)) { // <-- if fund_code is other
//            tFromMyClient.setFromFundsComment("comment"); // chane this
//        } else {
//            tFromMyClient.setFromFundsComment(null);
//        }
        tFromMyClient.setFromForeignCurrency(null);
        tFromMyClient.setTConductor(null);
        if (from.getUserType().equals(UserTypes.CUSTOMER)) {
            // From person
            tFromMyClient.setTPersonMyClient(createFromPersonForFrom(from));
            tPersonMyClientValidation.validateTPersonMyClient(tFromMyClient.getTPersonMyClient());  // tPersonMyClient validation
        } else if (from.getUserType().equals(UserTypes.MERCHANT) || from.getUserType().equals(UserTypes.RETAILER)) {
            // From entity
            tFromMyClient.setTEntityMyClient(createFromEntityForFrom(from));
            tEntityMyClientValidationService.validateTEntityMyClient(tFromMyClient.getTEntityMyClient());  // tEntityMyClient validation
        }
        tFromMyClient.setFromCountry(CountryCodes.LK); //
        return tFromMyClient;
    }

    private FromEntity createFromEntityForFrom(CommonDetails from) {
        FromEntity fromEntity = new FromEntity();
        fromEntity.setName(from.getFirstName()); //
        fromEntity.setCommercialName(null);
        fromEntity.setIncorporationLegalForm(null); //
        fromEntity.setIncorporationNumber(null);// optional --> fromEntity.setIncorporationNumber(from.getBusinessRegistrationNumber());
        fromEntity.setBusiness(BusinessCategory.SLSIC.toString()); //
        fromEntity.setPhone(null);
//        if (from.getBusinessPhonePrimary() != null) {
//            if (from.getBusinessPhonePrimary().trim().startsWith("07")) {
//                fromEntity.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.MOPH, "94", from.getBusinessPhonePrimary(), null, null));
//            } else {
//                fromEntity.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.LNPH, "94", from.getBusinessPhonePrimary(), null, null));
//            }
//        }
        fromEntity.setAddress(new TAddress(ContactTypes.BUSN, from.getAddressLine1() + from.getAddressLine2() + from.getAddressLine3(), null, from.getCity(), null, CountryCodes.LK, null, null)); //
        fromEntity.setEmail(null);// optional --> fromEntity.setEmail(from.getBusinessEmail());
        fromEntity.setUrl(null);// optional --> fromEntity.setUrl(from.getWebAddress());
        fromEntity.setIncorporationState(null);
        fromEntity.setIncorporationCountryCode(CountryCodes.LK.toString()); //
        fromEntity.setDirectorId(null); //
        fromEntity.setIncorporationDate(null);
        fromEntity.setBusinessClosed(null);
        fromEntity.setDateBusinessClosed(null);
        fromEntity.setTaxNumber(null);// optional --> fromEntity.setTaxNumber(from.getIncomeTaxFileNumber());
        fromEntity.setTaxRegNumber(null);
        fromEntity.setComments(null);
        return fromEntity;
    }

    private FromPerson createFromPersonForFrom(CommonDetails from) {
        FromPerson fromPerson = new FromPerson();
        fromPerson.setGender(null);
        fromPerson.setTitle(null);
        ExtractNameResponse extractNameResponse = preProcessService.extractFirstNameAndLastName(from.getFirstName());
        if (extractNameResponse.getFirstName() != null && extractNameResponse.getFirstName().length() > 99) { //
            String updateFirstName = preProcessService.reduceFirstNameCharacters(extractNameResponse.getFirstName());
            fromPerson.setFirstName(updateFirstName);
        } else {
            fromPerson.setFirstName(extractNameResponse.getFirstName());
        }
        fromPerson.setMiddleName(null);
        fromPerson.setPrefix(null);
        if (extractNameResponse.getLastName() != null) { //
            fromPerson.setLastName(extractNameResponse.getLastName().toUpperCase());
        }
//        fromPerson.setBirthdate(preProcessService.formatDateInCorrectFormat(String.valueOf(from.getBirthdate()))); //
        fromPerson.setBirthdate(String.valueOf(from.getBirthdate()));
        fromPerson.setBirthPlace(null);
        fromPerson.setMothersName(null);
//        if (fromPerson.getMothersName() != null) {
//            fromPerson.setMothersName(preProcessService.decodeBase64(from.getMothersName()));
//        }
        fromPerson.setAlias(null);
        fromPerson.setSsn(from.getIdNumber()); //NIC
        fromPerson.setPassports(null);
        fromPerson.setIdNumber(null);
        fromPerson.setNationality1(CountryCodes.LK.toString());// <-- fromPerson.setNationality1(preProcessService.convertNationality(from.getNationality1()));
        fromPerson.setNationality2(null);
        fromPerson.setNationality3(null);
        fromPerson.setResidence(CountryCodes.LK.toString()); // fromPerson.setResidence(from.getResidenceCategory());
        fromPerson.setPhone(null);
//        if (from.getPhonesPrimary() != null && !from.getPhonesPrimary().equals("null") && !from.getPhonesPrimary().equals("NA")) {
//            if (preProcessService.mobileOrNot(from.getPhonesPrimary())) {
//                fromPerson.setPhone(new TPhone(ContactTypes.PRVT,
//                        CommunicationTypes.MOPH,
//                        "94",
//                        preProcessService.mobileNumberStandardization(from.getPhonesPrimary()),
//                        null,
//                        null));
//            } else {
//                fromPerson.setPhone(new TPhone(ContactTypes.PERM,
//                        CommunicationTypes.LNPH,
//                        "94",
//                        from.getPhonesPrimary(),
//                        null,
//                        null));
//            }
//        } else {
//            fromPerson.setPhone(null);
//        }

        if (from.getAddressLine1() != null && from.getCity() != null) { //
            fromPerson.setAddress(new TAddress(ContactTypes.BUSN,
                    preProcessService.createAddressUsingAddressLines(from.getAddressLine1(), from.getAddressLine2(), from.getAddressLine3()),
                    null,
                    from.getCity(), // required
                    null,
                    CountryCodes.LK,
                    null,
                    null));
        }
        fromPerson.setEmail(null); // optional --> fromPerson.setEmail(from.getEmail())
        fromPerson.setOccupation(OccupationCategory.SLSCO.toString()); // fromPerson.setOccupation(from.getOccupation()); //
        fromPerson.setEmployerName(null); // optional --> fromPerson.setEmployerName(from.getEmployerName());
        fromPerson.setEmployerAddressId(null); // optional --> if (from.getEmployerAddress() != null) {fromPerson.setEmployerAddressId(new TAddress(ContactTypes.PERM, from.getEmployerAddress(), null, null, null, CountryCodes.LK, null, null));}
        fromPerson.setEmployerPhoneId(null);
        fromPerson.setIdentification(null);
        fromPerson.setDeceased(null);
        fromPerson.setTaxNumber(null); // optional --> fromPerson.setTaxNumber(from.getIncomeTaxFileNumber());
        fromPerson.setTaxRegNumber(null);
        fromPerson.setSourceOfWealth(null);
        fromPerson.setComments(null);
        return fromPerson;
    }

    @Override
    public TToMyClient convertDataTOTTOMyClient(CommonDetails to) {
        TToMyClient tToMyClient = new TToMyClient();
        tToMyClient.setToFundsCode(FundsTypes.MOBL);
        tToMyClient.setToFundsComment(null); // --> required if to_funds_code is other
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
        toAccount.setNonBankInstitution(null); // optional --> toAccount.setNonBankInstitution(Boolean.TRUE);
        toAccount.setBranch("Head Office"); //
        toAccount.setAccount(to.getBankAccountNumber()); //
        toAccount.setCurrencyCode(Currencies.LKR); //
        toAccount.setAccountName(null);
        toAccount.setIban(null);
        toAccount.setClientNumber(null);
        toAccount.setPersonalAccountType(AccountTypes.MOBW);
        toAccount.setTEntity(createTEntityMyClientForTo(to));
        //        if (to.getUserType().equals(UserTypes.CUSTOMER)) {
//            toAccount.setSignatory(createTPersonClientForTo(to));
//        } else if (to.getUserType().equals(UserTypes.MERCHANT) || to.getUserType().equals(UserTypes.RETAILER)) {
//            toAccount.setTEntity(createTEntityMyClientForTo(to));
//        }
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
        return toAccount;
    }

    private TPersonMyClient createTPersonClientForTo(CommonDetails to) {
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

    private TEntityMyClient createTEntityMyClientForTo(CommonDetails to) {
        TEntityMyClient tEntityMyClient = new TEntityMyClient();
        tEntityMyClient.setName(to.getFirstName()); //
        tEntityMyClient.setCommercialName(null);
        tEntityMyClient.setIncorporationLegalForm(EntityLegalFormTypes.OTH.toString()); // please change
        tEntityMyClient.setIncorporationNumber(to.getBusinessRegistrationNumber());
        tEntityMyClient.setBusiness(BusinessCategory.SLSIC.toString()); //
        tEntityMyClient.setPhone(null);
//        if (to.getBusinessPhonePrimary() != null) {
//            if (to.getBusinessPhonePrimary().trim().startsWith("07")) {
//                tEntityMyClient.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.MOPH, "94", to.getBusinessPhonePrimary(), null, null));
//            } else {
//                tEntityMyClient.setPhone(new TPhone(ContactTypes.BUSN, CommunicationTypes.LNPH, "94", to.getBusinessPhonePrimary(), null, null));
//            }
//        }
        tEntityMyClient.setAddress(new TAddress(ContactTypes.UNKNOWN, to.getAddressLine1() + to.getAddressLine2() + to.getAddressLine3(), null, to.getCity(), null, CountryCodes.LK, null, null)); //
        tEntityMyClient.setEmail(to.getBusinessEmail()); //
        tEntityMyClient.setUrl(to.getWebAddress());
        tEntityMyClient.setIncorporationState(null);
        tEntityMyClient.setIncorporationCountryCode(CountryCodes.LK.toString()); //
        tEntityMyClient.setDirectorId(createDirectorId(to)); //
        tEntityMyClient.setIncorporationDate(null);
        tEntityMyClient.setBusinessClosed(null);
        tEntityMyClient.setDateBusinessClosed(null);
        tEntityMyClient.setTaxNumber(to.getIncomeTaxFileNumber());
        tEntityMyClient.setTaxNumber(null);
        tEntityMyClient.setComments(null);
        return tEntityMyClient;
    }

    private DirectorId createDirectorId(CommonDetails to) {
        DirectorId directorId = new DirectorId();
        directorId.setGender(null);
        directorId.setTitle(null);
        directorId.setFirstName("First Name"); //
        directorId.setMiddleName(null);
        directorId.setPrefix(null);
        directorId.setLastName("Last Name"); //
        directorId.setBirthdate(null);
        directorId.setBirthPlace(null);
        directorId.setMothersName(null);
        directorId.setAlias(null);
        directorId.setSsn(null);
        directorId.setPassports(null);
        directorId.setIdNumber(null);
        directorId.setNationality1(CountryCodes.LK.toString()); //
        directorId.setNationality2(null);
        directorId.setNationality3(null);
        directorId.setResidence(null); // optional --> directorId.setResidence(CountryCodes.LK.toString());
        directorId.setPhone(null);
        directorId.setAddress(null);
        directorId.setEmail(null);
        directorId.setOccupation(null);
        directorId.setEmployerName(null);
        directorId.setEmployerAddressId(null);
        directorId.setEmployerPhoneId(null);
        directorId.setIdentification(null);
        directorId.setDeceased(null);
        directorId.setTaxNumber(null);
        directorId.setTaxRegNumber(null);
        directorId.setSourceOfWealth(null);
        directorId.setComments(null);
        directorId.setRole(EntityPersonRoleTypes.OTH); // please change according to the data
        return directorId;
    }

}
