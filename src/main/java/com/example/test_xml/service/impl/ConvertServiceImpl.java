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
import com.example.test_xml.service.CommonMethodService;
import com.example.test_xml.service.ConvertService;
import com.example.test_xml.service.validations.TPersonMyClientValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConvertServiceImpl implements ConvertService {

    private final Logger logger = LoggerFactory.getLogger(ConvertServiceImpl.class);

    private final CommonMethodService commonMethodService;
    private final TPersonMyClientValidation tPersonMyClientValidation;

    @Autowired
    public ConvertServiceImpl(CommonMethodService commonMethodService,
                              TPersonMyClientValidation tPersonMyClientValidation) {
        this.commonMethodService = commonMethodService;
        this.tPersonMyClientValidation = tPersonMyClientValidation;
    }

    @Override
    public CommonDetails mapCustomerToCommon(CustomerDetails customer) {

        logger.info("Start the excecute mapCustomerToCommon");

        if (customer == null) {
            return null;
        }

        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setId(customer.getId());
        commonDetails.setFirstName(customer.getFirstName());
        commonDetails.setAddressName(customer.getAddressName());
        commonDetails.setAddressLine1(customer.getAddressLine1());
        commonDetails.setAddressLine2(customer.getAddressLine2());
        commonDetails.setAddressLine3(customer.getAddressLine3());
        commonDetails.setAlias(customer.getAlias());
        commonDetails.setTitle(customer.getTitle());
        commonDetails.setIdNumber(customer.getIdNumber());
        commonDetails.setBirthdate(customer.getBirthdate());
        commonDetails.setMothersName(customer.getMothersName());
        commonDetails.setNationality1(customer.getNationality1());
        commonDetails.setCity(customer.getCity());
        commonDetails.setState(customer.getState());
        commonDetails.setPhonesPrimary(customer.getPhonesPrimary());
        commonDetails.setPhonesSecondary(customer.getPhonesSecondary());
        commonDetails.setEmail(customer.getEmail());
        commonDetails.setOccupation(customer.getOccupation());
        commonDetails.setEmployerName(customer.getEmployerName());
        commonDetails.setEmployerAddress(customer.getEmployerAddress());
        commonDetails.setSourceOfWealth(customer.getSourceOfWealth());
        commonDetails.setUserVerified(customer.isUserVerified());
        commonDetails.setDocumentsVerified(customer.isDocumentsVerified());
        commonDetails.setUserType(UserTypes.CUSTOMER);
        return commonDetails;
    }

    @Override
    public CommonDetails mapReSellerToCommon(ReSellerDetails reSeller) {
        logger.info("Start the excecute mapReSellerToCommon");

        if (reSeller == null) {
            return null;
        }

        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setId(reSeller.getId());
        commonDetails.setFirstName(reSeller.getFirstName());
        commonDetails.setAddressName(reSeller.getAddressName());
        commonDetails.setAddressLine1(reSeller.getAddressLine1());
        commonDetails.setAddressLine2(reSeller.getAddressLine2());
        commonDetails.setAddressLine3(reSeller.getAddressLine3());
        commonDetails.setAlias(reSeller.getAlias());
        commonDetails.setTitle(reSeller.getTitle());
        commonDetails.setIdNumber(reSeller.getIdNumber());
        commonDetails.setBirthdate(reSeller.getBirthdate());
        commonDetails.setMothersName(reSeller.getMothersName());
        commonDetails.setNationality1(reSeller.getNationality1());
        commonDetails.setCity(reSeller.getCity());
        commonDetails.setState(reSeller.getState());
        commonDetails.setPhonesPrimary(reSeller.getPhonesPrimary());
        commonDetails.setPhonesSecondary(reSeller.getPhonesSecondary());
        commonDetails.setEmail(reSeller.getEmail());
        commonDetails.setOccupation(reSeller.getOccupation());
        commonDetails.setEmployerName(reSeller.getEmployerName());
        commonDetails.setEmployerAddress(reSeller.getEmployerAddress());
        commonDetails.setSourceOfWealth(reSeller.getSourceOfWealth());
        commonDetails.setUserVerified(reSeller.isUserVerified());
        commonDetails.setDocumentsVerified(reSeller.isDocumentsVerified());
        commonDetails.setAnnualIncome(reSeller.getAnnualIncome());
        commonDetails.setBankAccountNumber(reSeller.getBankAccountNumber());
        commonDetails.setBankCode(reSeller.getBankCode());
        commonDetails.setBankName(reSeller.getBankName());
        commonDetails.setBranchCode(reSeller.getBranchCode());
        commonDetails.setBranchName(reSeller.getBranchName());
        commonDetails.setBusinessAlreadyRegistered(reSeller.isBusinessAlreadyRegistered());
        commonDetails.setBusinessCommencementDate(reSeller.getBusinessCommencementDate());
        commonDetails.setBusinessRegistrationNumber(reSeller.getBusinessRegistrationNumber());
        commonDetails.setBusinessPhonePrimary(reSeller.getBusinessPhonePrimary());
        commonDetails.setBusinessPhoneSecondary(reSeller.getBusinessPhoneSecondary());
        commonDetails.setBusinessContactPerson(reSeller.getBusinessContactPerson());
        commonDetails.setDifferentMailingAddress(reSeller.isDifferentMailingAddress());
        commonDetails.setBusinessEmail(reSeller.getBusinessEmail());
        commonDetails.setIncomeTaxFileNumber(reSeller.getIncomeTaxFileNumber());
        commonDetails.setOtherConnectedBusiness(reSeller.getOtherConnectedBusiness());
        commonDetails.setPartnerCode(reSeller.getPartnerCode());
        commonDetails.setSettlementStatus(reSeller.getSettlementStatus());
        commonDetails.setSettlementMethod(reSeller.getSettlementMethod());
        commonDetails.setSourceOfIncome(reSeller.getSourceOfIncome());
        commonDetails.setSourceOfIncomeOther(reSeller.getSourceOfIncomeOther());
        commonDetails.setUserType(UserTypes.RETAILER);
        return commonDetails;
    }

    @Override
    public CommonDetails mapMerchantToCommon(MerchantDetails merchant) {

        if (merchant == null) {
            return null;
        }

        CommonDetails commonDetails = new CommonDetails();
        commonDetails.setId(merchant.getId());
        commonDetails.setFirstName(merchant.getFirstName());
        commonDetails.setAddressName(merchant.getAddressName());
        commonDetails.setAddressLine1(merchant.getAddressLine1());
        commonDetails.setAddressLine2(merchant.getAddressLine2());
        commonDetails.setAddressLine3(merchant.getAddressLine3());
        commonDetails.setAlias(merchant.getAlias());
        commonDetails.setTitle(merchant.getTitle());
        commonDetails.setIdNumber(merchant.getIdNumber());
        commonDetails.setBirthdate(merchant.getBirthdate());
        commonDetails.setMothersName(merchant.getMothersName());
        commonDetails.setNationality1(merchant.getNationality1());
        commonDetails.setCity(merchant.getCity());
        commonDetails.setState(merchant.getState());
        commonDetails.setPhonesPrimary(merchant.getPhonesPrimary());
        commonDetails.setPhonesSecondary(merchant.getPhonesSecondary());
        commonDetails.setEmail(merchant.getEmail());
        commonDetails.setOccupation(merchant.getOccupation());
        commonDetails.setEmployerName(merchant.getEmployerName());
        commonDetails.setEmployerAddress(merchant.getEmployerAddress());
        commonDetails.setSourceOfWealth(merchant.getSourceOfWealth());
        commonDetails.setUserVerified(merchant.isUserVerified());
        commonDetails.setDocumentsVerified(merchant.isDocumentsVerified());
        commonDetails.setAnnualIncome(merchant.getAnnualIncome());
        commonDetails.setBankAccountNumber(merchant.getBankAccountNumber());
        commonDetails.setBankCode(merchant.getBankCode());
        commonDetails.setBankName(merchant.getBankName());
        commonDetails.setBranchCode(merchant.getBranchCode());
        commonDetails.setBranchName(merchant.getBranchName());
        commonDetails.setBusinessAlreadyRegistered(merchant.isBusinessAlreadyRegistered());
        commonDetails.setBusinessCommencementDate(merchant.getBusinessCommencementDate());
        commonDetails.setBusinessRegistrationNumber(merchant.getBusinessRegistrationNumber());
        commonDetails.setBusinessPhonePrimary(merchant.getBusinessPhonePrimary());
        commonDetails.setBusinessPhoneSecondary(merchant.getBusinessPhoneSecondary());
        commonDetails.setBusinessContactPerson(merchant.getContactPerson1());
        commonDetails.setDifferentMailingAddress(merchant.isDifferentMailingAddress());
        commonDetails.setBusinessEmail(merchant.getBusinessEmail());
        commonDetails.setIncomeTaxFileNumber(merchant.getIncomeTaxFileNumber());
        commonDetails.setOtherConnectedBusiness(merchant.getOtherBusinessContacts());
        commonDetails.setSettlementMethod(merchant.getSettlementMethod());
        commonDetails.setSourceOfIncome(merchant.getSourceOfIncome());
        commonDetails.setSourceOfIncomeOther(merchant.getOtherSourceOfIncome());
        commonDetails.setBranchValidationFlag(merchant.isBranchValidationFlag());
        commonDetails.setCommunicationEmail(merchant.getCommunicationEmail());
        commonDetails.setContactEmail1(merchant.getContactEmail1());
        commonDetails.setContactEmail2(merchant.getContactEmail2());
        commonDetails.setContactPerson1(merchant.getContactPerson1());
        commonDetails.setContactPerson2(merchant.getContactPerson2());
        commonDetails.setFaxNumber(merchant.getFaxNumber());
        commonDetails.setFacebookAccount(merchant.getFacebookAccount());
        commonDetails.setGooglePlusAccount(merchant.getGooglePlusAccount());
        commonDetails.setHotline(merchant.getHotline());
        commonDetails.setInsurancePaymentReference(merchant.getInsurancePaymentReference());
        commonDetails.setLinkedinAccount(merchant.getLinkedinAccount());
        commonDetails.setLowerTransactionLimit(merchant.getLowerTransactionLimit());
        commonDetails.setMerchantCategoryCode(merchant.getMerchantCategoryCode());
        commonDetails.setMerchantShortCode(merchant.getMerchantShortCode());
        commonDetails.setNotes(merchant.getNotes());
        commonDetails.setOtherBusinessContacts(merchant.getOtherBusinessContacts());
        commonDetails.setOtpNumber(merchant.getOtpNumber());
        commonDetails.setPaymentConfirmationMobileNo(merchant.getPaymentConfirmationMobileNo());
        commonDetails.setPaymentConfirmationSmsRequired(merchant.isPaymentConfirmationSmsRequired());
        commonDetails.setPaymentMode(merchant.getPaymentMode());
        commonDetails.setPaymentProcessingMode(merchant.getPaymentProcessingMode());
        commonDetails.setResidenceCategory(merchant.getResidenceCategory());
        commonDetails.setSettlementPlan(merchant.getSettlementPlan());
        commonDetails.setSkypeAccount(merchant.getSkypeAccount());
        commonDetails.setStoreLabel(merchant.getStoreLabel());
        commonDetails.setTestValue(merchant.getTestValue());
        commonDetails.setTransactionMethod(merchant.getTransactionMethod());
        commonDetails.setTwitterAccount(merchant.getTwitterAccount());
        commonDetails.setUpperTransactionLimit(merchant.getUpperTransactionLimit());
        commonDetails.setUtilityPaymentReference(merchant.getUtilityPaymentReference());
        commonDetails.setUtilityCompany(merchant.getUtilityCompany());
        commonDetails.setWebAddress(merchant.getWebAddress());
        commonDetails.setUserType(UserTypes.MERCHANT);
        return commonDetails;
    }

    @Override
    public TFromMyClient convertDataToFromMyClient(CommonDetails from) {
        TFromMyClient tFromMyClient = new TFromMyClient();
        tFromMyClient.setFromFundsCode(FundsTypes.MOBL); //

        if (from.getUserType().equals(UserTypes.CUSTOMER)) {
            tFromMyClient.setTPersonMyClient(createFromPersonForFrom(from));
        } else if (from.getUserType().equals(UserTypes.MERCHANT) || from.getUserType().equals(UserTypes.RETAILER)) {
            tFromMyClient.setTEntityMyClient(createFromEntityForFrom(from));
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
        ExtractNameResponse extractNameResponse = commonMethodService.extractFirstNameAndLastName(from.getFirstName());
        fromPerson.setFirstName(extractNameResponse.getFirstName()); //
        fromPerson.setMiddleName(null);
        fromPerson.setPrefix(null);
        fromPerson.setLastName(extractNameResponse.getLastName().toUpperCase()); //
        fromPerson.setBirthdate(String.valueOf(from.getBirthdate())); //
        fromPerson.setBirthPlace(null);
//        fromPerson.setMothersName(commonMethodService.decodeBase64(from.getMothersName()));
        fromPerson.setAlias(null);
        fromPerson.setSsn(from.getIdNumber()); //NIC
        fromPerson.setPassports(null);
        fromPerson.setIdNumber(null);
//        fromPerson.setNationality1(commonMethodService.convertNationality(from.getNationality1())); // used category
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
