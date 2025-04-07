package com.example.test_xml.service.impl;

import com.example.test_xml.exception.InternalServerErrorException;
import com.example.test_xml.model.enums.db.UserTypes;
import com.example.test_xml.model.response.CommonDetails;
import com.example.test_xml.model.response.CustomerDetails;
import com.example.test_xml.model.response.MerchantDetails;
import com.example.test_xml.model.response.ReSellerDetails;
import com.example.test_xml.service.DetailsMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DetailsMapServiceImpl implements DetailsMapService {

    private final Logger logger = LoggerFactory.getLogger(DetailsMapServiceImpl.class);

    @Override
    public CommonDetails mapCustomerToCommon(CustomerDetails customer) {
        try {
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
        } catch (Exception e) {
            logger.error("Error occur when convert customer to common. customer id : {}", customer.getId());
            throw new InternalServerErrorException("Error occur when convert customer to common. customer id : " + customer.getId());
        }
    }

    @Override
    public CommonDetails mapReSellerToCommon(ReSellerDetails reSeller) {
        try {
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
        } catch (Exception e) {
            logger.error("Error occur when convert reseller to common. reseller id : {}", reSeller.getId());
            throw new InternalServerErrorException("Error occur when convert reseller to common. reseller id : " + reSeller.getId());
        }
    }

    @Override
    public CommonDetails mapMerchantToCommon(MerchantDetails merchant) {
        try {
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
        } catch (Exception e) {
            logger.error("Error occur when convert merchant to common. merchant id : {}", merchant.getId());
            throw new InternalServerErrorException("Error occur when convert merchant to common. merchant id : " + merchant.getId());
        }
    }

}
