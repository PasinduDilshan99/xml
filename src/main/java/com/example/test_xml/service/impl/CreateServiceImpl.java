package com.example.test_xml.service.impl;

import com.example.test_xml.model.enums.*;
import com.example.test_xml.model.otherEnums.BusinessCategory;
import com.example.test_xml.model.otherEnums.OccupationCategory;
import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.response.ExtractNameResponse;
import com.example.test_xml.model.response.TransactionResponse;
import com.example.test_xml.model.xmlDto.DirectorId;
import com.example.test_xml.model.xmlDto.TAddress;
import com.example.test_xml.model.xmlDto.Transaction;
import com.example.test_xml.model.xmlDto.from.FromEntity;
import com.example.test_xml.model.xmlDto.from.FromPerson;
import com.example.test_xml.model.xmlDto.from.TFromMyClient;
import com.example.test_xml.model.xmlDto.to.TToMyClient;
import com.example.test_xml.model.xmlDto.to.ToAccount;
import com.example.test_xml.service.CreateService;
import com.example.test_xml.service.MyClientService;
import com.example.test_xml.service.PreProcessService;
import com.example.test_xml.validationServices.FromAndToValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class CreateServiceImpl implements CreateService {

    private final PreProcessService preProcessService;
    private final MyClientService myClientService;
    private final FromAndToValidationService fromAndToValidationService;

    @Autowired
    public CreateServiceImpl(PreProcessService preProcessService,
                             @Lazy MyClientService myClientService,
                             FromAndToValidationService fromAndToValidationService) {
        this.preProcessService = preProcessService;
        this.myClientService = myClientService;
        this.fromAndToValidationService = fromAndToValidationService;
    }

    private final Logger logger = LoggerFactory.getLogger(CreateServiceImpl.class);

    @Override
    public FromEntity createTEntityMyClient(CommonDetails from) {
        FromEntity fromEntity = new FromEntity();
        fromEntity.setName(from.getFirstName()); //
        fromEntity.setCommercialName(null);
        fromEntity.setIncorporationLegalForm(preProcessService.prepareBusinessType(from.getBusinessType())); //
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
        fromEntity.setDirectorId(createDirectorId(from)); //
        fromEntity.setIncorporationDate(null);
        fromEntity.setBusinessClosed(null);
        fromEntity.setDateBusinessClosed(null);
        fromEntity.setTaxNumber(null);// optional --> fromEntity.setTaxNumber(from.getIncomeTaxFileNumber());
        fromEntity.setTaxRegNumber(null);
        fromEntity.setComments(null);
        return fromEntity;
    }

    @Override
    public FromPerson createTPersonMyClient(CommonDetails from) {
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
    public ToAccount createTAccountMyClient(CommonDetails to) {
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
        toAccount.setTEntity(createTEntityMyClient(to));
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

    @Override
    public DirectorId createDirectorId(CommonDetails to) {
        DirectorId directorId = new DirectorId();
        directorId.setGender(null);
        directorId.setTitle(null);
        if (to.getContactPerson1() != null) {
            ExtractNameResponse extractNameResponse = preProcessService.extractFirstNameAndLastName(to.getContactPerson1());
            directorId.setFirstName(extractNameResponse.getFirstName()); //
            directorId.setLastName(extractNameResponse.getLastName()); //
        }
        directorId.setMiddleName(null);
        directorId.setPrefix(null);
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

    @Override
    public Transaction createTransaction(TransactionResponse transactionResponse1) {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(transactionResponse1.getTransactionNumber()); //
        transaction.setInternalRefNumber(null);
        transaction.setTransactionLocation(null);
//                if (transaction.getTransmodeCode() != null && transaction.getTransmodeCode().equals(ConductionTypes.BRCH)) { // <-- if transmode_code is branch
//                    transaction.setTransactionLocation("Location"); // change this
//                } else {
//                    transaction.setTransactionLocation(null);
//                }
        transaction.setTransactionDescription(transactionResponse1.getTransactionDescription()); //
        transaction.setDateTransaction(preProcessService.formatDateInCorrectFormat(transactionResponse1.getDateTransaction().toString())); //
        transaction.setTeller(null);
        transaction.setAuthorized(null);
        transaction.setLateDeposit(null);
        transaction.setDatePosting(null);
        transaction.setValueDate(null);
        transaction.setTransmodeCode(ConductionTypes.MBWL);
        transaction.setTransmodeComment(null);
//                if (transaction.getTransmodeCode() != null && transaction.getTransmodeCode().equals(ConductionTypes.UNKNOWN)) { // <-- if transmode code is unknown
//                    transaction.setTransmodeComment("Comment"); // change this
//                } else {
//                    transaction.setTransmodeComment(null);
//                }
        transaction.setAmountLocal(transactionResponse1.getTxnAmount()); //
        // From side
        TFromMyClient tFromMyClient = myClientService.convertDataToTFromMyClient(transactionResponse1.getFrom());
        fromAndToValidationService.validateTFromMyClient(tFromMyClient);  // tFromMyClient Validation
        transaction.setTFromMyClient(tFromMyClient);
        // To side
        TToMyClient tToMyClient = myClientService.convertDataTOTTOMyClient(transactionResponse1.getTo());
        fromAndToValidationService.validateTToMyClient(tToMyClient); // tToMyClient Validation
        transaction.setTToMyClient(tToMyClient);
        transaction.setGoodsServices(null);
        transaction.setComments(null);
        return transaction;
    }
}
