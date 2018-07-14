package com.uat.hbc.insurance.controller;

import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.ModelAndView;

import com.universal.IService1;
import com.universal.Service1;

@Controller
public class HealthCalculatorUniversalController {
	  String path=System.getProperty("user.home");
	  
	  @RequestMapping("user/HealthUniversal")
		public ModelAndView callJsp(HttpServletRequest httpServletRequest,
				HttpServletResponse httpServletResponse) {
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("user/HealthUniversal");
			return modelAndView;
		}
	  @RequestMapping(value = "/healthCalculator", method = RequestMethod.POST, produces ="application/json; charset=UTF-8")
		public @ResponseBody String getParameters(HttpServletRequest request,
				HttpServletResponse response) throws JDOMException, IOException {
		
		    String xmlData = xmlFile();

			Service1 service1 = new Service1();
			IService1 api= service1.getBasicHttpBindingIService1();
			String responseXml = api.commBRIDGEFusionHEALTH(xmlData);
			System.out.println("ResponseXml =============>>" + responseXml);
					return responseXml;
			
		}
		public String xmlFile(){
			String xx = "";
			Document document2;
			org.w3c.dom.Document document = null;
			org.jdom2.Element rootelement = new org.jdom2.Element("Root");
			document2 = new Document(rootelement);

			org.jdom2.Element Authentication = new org.jdom2.Element("Authentication");
			
			org.jdom2.Element WACode = new org.jdom2.Element("WACode");
			WACode.setText("E1000002");
			
			org.jdom2.Element WAAppCode = new org.jdom2.Element("WAAppCode");
			WAAppCode.setText("A1000002");
			
			org.jdom2.Element WAUserID = new org.jdom2.Element("WAUserID");
			WAUserID.setText("USER01");
			
			org.jdom2.Element WAUserPwd = new org.jdom2.Element("WAUserPwd");
			WAUserPwd.setText("pass@123");
			
			org.jdom2.Element WAType = new org.jdom2.Element("WAType");
			WAType.setText("0");
			
			Authentication.addContent(WACode);
			Authentication.addContent(WAAppCode);
			Authentication.addContent(WAUserID);
			Authentication.addContent(WAUserPwd);
			Authentication.addContent(WAType);
			
			org.jdom2.Element Customer = new org.jdom2.Element("Customer");
			
			org.jdom2.Element CustomerType = new org.jdom2.Element("CustomerType");
			CustomerType.setText("Individual");
			
			org.jdom2.Element CustomerName = new org.jdom2.Element("CustomerName");
			CustomerName.setText("Raja Malik");
			
			org.jdom2.Element DOB = new org.jdom2.Element("DOB");
			DOB.setText("01/01/1988");
			
			org.jdom2.Element Gender = new org.jdom2.Element("Gender");
			Gender.setText("M");
			
			org.jdom2.Element CanBeParent = new org.jdom2.Element("CanBeParent");
			CanBeParent.setText("0");
			
			org.jdom2.Element ContactTelephoneSTD = new org.jdom2.Element("ContactTelephoneSTD");
			ContactTelephoneSTD.setText("02255671653");
			
			org.jdom2.Element MobileNo = new org.jdom2.Element("MobileNo");
			MobileNo.setText("9874561231");
			
			org.jdom2.Element Emailid = new org.jdom2.Element("Emailid");
			Emailid.setText("rajam@gmail.com");
			
			org.jdom2.Element PresentAddressLine1 = new org.jdom2.Element("PresentAddressLine1");
			PresentAddressLine1.setText("234,Tembi Naka");
			
			org.jdom2.Element PresentAddressLine2 = new org.jdom2.Element("PresentAddressLine2");
			PresentAddressLine2.setText("Thane");
			
			org.jdom2.Element PresentStateCode = new org.jdom2.Element("PresentStateCode");
			PresentStateCode.setText("MAHARASHTRA");
			
			org.jdom2.Element PresentCityDistCode = new org.jdom2.Element("PresentCityDistCode");
			PresentCityDistCode.setText("THANE");
			
			org.jdom2.Element PresentPinCode = new org.jdom2.Element("PresentPinCode");
			PresentPinCode.setText("400601");
			
			org.jdom2.Element PermanentAddressLine1 = new org.jdom2.Element("PermanentAddressLine1");
			PermanentAddressLine1.setText("234,Tembi Naka");
			
			org.jdom2.Element PermanentAddressLine2 = new org.jdom2.Element("PermanentAddressLine2");
			PermanentAddressLine2.setText("Thane");
			
			org.jdom2.Element PermanentStateCode = new org.jdom2.Element("PermanentStateCode");
			PermanentStateCode.setText("MAHARASHTRA");
			
			org.jdom2.Element PermanentCityDistCode = new org.jdom2.Element("PermanentCityDistCode");
			PermanentCityDistCode.setText("THANE");
			
			org.jdom2.Element PermanentPinCode = new org.jdom2.Element("PermanentPinCode");
			PermanentPinCode.setText("400601");
			
			org.jdom2.Element CustGSTNo = new org.jdom2.Element("CustGSTNo");
			CustGSTNo.setText("00000000000");
			
			org.jdom2.Element ProductName = new org.jdom2.Element("ProductName");
			ProductName.setText("Complete HealthCare Insurance");
			
			org.jdom2.Element InstrumentNo = new org.jdom2.Element("InstrumentNo");
			InstrumentNo.setText("NULL");
			
			org.jdom2.Element InstrumentDate = new org.jdom2.Element("InstrumentDate");
			InstrumentDate.setText("NULL");
			
			org.jdom2.Element BankID = new org.jdom2.Element("BankID");
			BankID.setText("NULL");
			
			org.jdom2.Element PosPolicyNo = new org.jdom2.Element("PosPolicyNo");
			PosPolicyNo.setText("WAQuoteNo");
			
			org.jdom2.Element ProductCode = new org.jdom2.Element("ProductCode");
			ProductCode.setText("2825");
			
			org.jdom2.Element WAURN = new org.jdom2.Element("WAURN");
			WAURN.setText("NULL");
			
			org.jdom2.Element NomineeName = new org.jdom2.Element("NomineeName");
			NomineeName.setText("NULL");
			
			org.jdom2.Element NomineeRelation = new org.jdom2.Element("NomineeRelation");
			NomineeRelation.setText("NULL");
			
			Customer.addContent(CustomerType);
			Customer.addContent(CustomerName);
			Customer.addContent(DOB);
			Customer.addContent(Gender);
			Customer.addContent(CanBeParent);
			Customer.addContent(ContactTelephoneSTD);
			Customer.addContent(MobileNo);
			Customer.addContent(Emailid);
			Customer.addContent(PresentAddressLine1);
			Customer.addContent(PresentAddressLine2);
			Customer.addContent(PresentStateCode);
			Customer.addContent(PresentCityDistCode);
			Customer.addContent(PresentPinCode);
			Customer.addContent(PermanentAddressLine1);
			Customer.addContent(PermanentAddressLine2);
			Customer.addContent(PermanentStateCode);
			Customer.addContent(PermanentCityDistCode);
			Customer.addContent(PermanentPinCode);
			Customer.addContent(CustGSTNo);
			Customer.addContent(ProductName);
			Customer.addContent(ProductCode);
			Customer.addContent(InstrumentNo);
			Customer.addContent(InstrumentDate);
			Customer.addContent(BankID);
			Customer.addContent(PosPolicyNo);
			Customer.addContent(WAURN);
			Customer.addContent(NomineeName);
			Customer.addContent(NomineeRelation);
			
			//Product//

			org.jdom2.Element Product = new org.jdom2.Element("Product");	
			Product.setAttribute("Name","Complete HealthCare Insurance");  
			
			org.jdom2.Element GeneralProposal = new org.jdom2.Element("GeneralProposal");	
			GeneralProposal.setAttribute("Name", "General Proposal"); 
			
			org.jdom2.Element GeneralProposalGroup = new org.jdom2.Element("GeneralProposalGroup");	
			GeneralProposalGroup.setAttribute("Name", "General Proposal Group");   
			
			org.jdom2.Element DistributionChannel= new org.jdom2.Element("DistributionChannel");	
			DistributionChannel.setAttribute("Name", "Distribution Channel"); 
			
			org.jdom2.Element BranchDetails= new org.jdom2.Element("BranchDetails");	
			BranchDetails.setAttribute("Name", "Branch Details");    
			
			org.jdom2.Element IMDBranchName= new org.jdom2.Element("IMDBranchName");	
			IMDBranchName.setAttribute("Name", "IMDBranchName");   
			IMDBranchName.setAttribute("Value","");
			
			org.jdom2.Element IMDBranchCode= new org.jdom2.Element("IMDBranchCode");	
			IMDBranchCode.setAttribute("Name", "IMDBranch Code");     
			IMDBranchCode.setAttribute("Value", "");    
			
			BranchDetails.addContent(IMDBranchName);
			BranchDetails.addContent(IMDBranchCode);
			
			org.jdom2.Element SPDetails= new org.jdom2.Element("SPDetails");	
			SPDetails.setAttribute("Name", "SP Details");    
			
			org.jdom2.Element SPName= new org.jdom2.Element("SPName");	
			SPName.setAttribute("Name", "SP Name");   
			
			SPName.setAttribute("Value", "");     
			org.jdom2.Element SPCode= new org.jdom2.Element("SPCode");	
			
			SPCode.setAttribute("Name", "SP Code"); 
			SPCode.setAttribute("Value", "");     
			
			SPDetails.addContent(SPName);
			SPDetails.addContent(SPCode);
			
			DistributionChannel.addContent(BranchDetails);
			DistributionChannel.addContent(SPDetails);
			
			org.jdom2.Element GeneralProposalInformation= new org.jdom2.Element("GeneralProposalInformation");	
			GeneralProposalInformation.setAttribute("Name", "General Proposal Information");
			
			org.jdom2.Element TypeOfBusiness= new org.jdom2.Element("TypeOfBusiness");	
			TypeOfBusiness.setAttribute("Name", "Type Of Business");     
			TypeOfBusiness.setAttribute("Value", "FROM INTERMEDIARY");
			
			org.jdom2.Element ServiceTaxExemptionCategory = new org.jdom2.Element("ServiceTaxExemptionCategory");	
			ServiceTaxExemptionCategory .setAttribute("Name", "Service Tax Exemption Category");     
			ServiceTaxExemptionCategory .setAttribute("Value", "No Exemption");
			
			org.jdom2.Element BusinessType = new org.jdom2.Element("BusinessType");	
			BusinessType.setAttribute("Name", "Transaction Type");     
			BusinessType.setAttribute("Value", "New");
			
			org.jdom2.Element Sector = new org.jdom2.Element("Sector");	
			Sector.setAttribute("Name", "Sector");     
			Sector.setAttribute("Value", "Others");
			
			org.jdom2.Element DealId = new org.jdom2.Element("DealId");	
			DealId.setAttribute("Name", "Deal Id");     
			DealId.setAttribute("Value", "");
			
			org.jdom2.Element PolicyType = new org.jdom2.Element("PolicyType");	
			PolicyType.setAttribute("Name", "Policy Type");     
			PolicyType.setAttribute("Value", "Family Floater");
			
			org.jdom2.Element FamilyComposition = new org.jdom2.Element("FamilyComposition");	
			FamilyComposition.setAttribute("Name", "Floater Option");     
			FamilyComposition.setAttribute("Value", "1 Adult + 1 Child");
			
			org.jdom2.Element PlanType = new org.jdom2.Element("PlanType");	
			PlanType.setAttribute("Name", "Plan Type");     
			PlanType.setAttribute("Value", "Basic-Priviledge-Essential");
			
			org.jdom2.Element ProposalDate = new org.jdom2.Element("ProposalDate");	
			ProposalDate.setAttribute("Name", "Proposal Date");     
			ProposalDate.setAttribute("Value", "09/03/2018");
			
			org.jdom2.Element PolicyDuration = new org.jdom2.Element("PolicyDuration");	
			PolicyDuration.setAttribute("Name", "Policy Duration");     
			PolicyDuration.setAttribute("Value", "1-2-3");
			
			
			org.jdom2.Element PolicyNumberChar = new org.jdom2.Element("PolicyNumberChar");	
			PolicyNumberChar.setAttribute("Name", "PolicyNumberChar");     
			PolicyNumberChar.setAttribute("Value", "");

			
			org.jdom2.Element PolicyEffectiveDate = new org.jdom2.Element("PolicyEffectiveDate");	
			PolicyEffectiveDate.setAttribute("Name", "Policy Effective Date");     
			
			org.jdom2.Element Fromdate = new org.jdom2.Element("Fromdate");	
			Fromdate.setAttribute("Name", "From Date");     
			Fromdate.setAttribute("Value", "09/03/2018");
			
			org.jdom2.Element Todate = new org.jdom2.Element("Todate");	
			Todate.setAttribute("Name", "To Date");     
			Todate.setAttribute("Value", "08/03/2019");
			
			org.jdom2.Element Fromhour = new org.jdom2.Element("Fromhour");	
			Fromhour.setAttribute("Name", "From Hour");     
			Fromhour.setAttribute("Value", "18:04");
			
			org.jdom2.Element Tohour = new org.jdom2.Element("Tohour");	
			Tohour.setAttribute("Name", "To Hour");     
			Tohour.setAttribute("Value", "23:59");
			
			PolicyEffectiveDate.addContent(Fromdate);
			PolicyEffectiveDate.addContent(Todate);
			PolicyEffectiveDate.addContent(Fromhour);
			PolicyEffectiveDate.addContent(Tohour);
			
			org.jdom2.Element SubCategory = new org.jdom2.Element("SubCategory");	
			SubCategory.setAttribute("Name", "SubCategory");     
			SubCategory.setAttribute("Value", "A");
			
			GeneralProposalInformation.addContent(TypeOfBusiness);
			GeneralProposalInformation.addContent(ServiceTaxExemptionCategory);
			GeneralProposalInformation.addContent(BusinessType);
			GeneralProposalInformation.addContent(Sector);
			GeneralProposalInformation.addContent(DealId);
			GeneralProposalInformation.addContent(PolicyType);
			GeneralProposalInformation.addContent(FamilyComposition);
			GeneralProposalInformation.addContent(PlanType);
			GeneralProposalInformation.addContent(ProposalDate);
			GeneralProposalInformation.addContent(PolicyDuration);
			GeneralProposalInformation.addContent(PolicyNumberChar);
			GeneralProposalInformation.addContent(PolicyEffectiveDate);
			GeneralProposalInformation.addContent(SubCategory);
			
			GeneralProposalGroup.addContent(DistributionChannel);
			GeneralProposalGroup.addContent(GeneralProposalInformation);
			
			GeneralProposal.addContent(GeneralProposalGroup);
			
			org.jdom2.Element FinancierDetails= new org.jdom2.Element("FinancierDetails");	
			FinancierDetails.setAttribute("Name", "Financier Details");
			
			org.jdom2.Element FinancierDtlGrp= new org.jdom2.Element("FinancierDtlGrp");
			FinancierDtlGrp.setAttribute("Type", "Group");    
			FinancierDtlGrp.setAttribute("Name", "Financier Dtl Group");
			
			org.jdom2.Element FinancierDtlGrpData= new org.jdom2.Element("FinancierDtlGrpData");
			FinancierDtlGrpData.setAttribute("Type", "GroupData");
			
			org.jdom2.Element FinancierCode= new org.jdom2.Element("FinancierCode");
			FinancierCode.setAttribute("Name", "Financier Code");    
			FinancierCode.setAttribute("Value", "1");
			
			org.jdom2.Element AgreementType= new org.jdom2.Element("AgreementType");
			AgreementType.setAttribute("Name", "Agreement Type");    
			AgreementType.setAttribute("Value", "");
			
			org.jdom2.Element BranchName= new org.jdom2.Element("BranchName");
			BranchName.setAttribute("Name", "Branch Name");    
			BranchName.setAttribute("Value", "Thane");    
			
			org.jdom2.Element FinancierName= new org.jdom2.Element("FinancierName");
			FinancierName.setAttribute("Name", "Financier Name");    
			FinancierName.setAttribute("Value","HDFC Bank");    
			
			org.jdom2.Element SrNo= new org.jdom2.Element("SrNo");
			SrNo.setAttribute("Name", "Sr No");    
			SrNo.setAttribute("Value", "1");    

			FinancierDtlGrpData.addContent(FinancierCode);
			FinancierDtlGrpData.addContent(AgreementType);
			FinancierDtlGrpData.addContent(BranchName);
			FinancierDtlGrpData.addContent(FinancierName);
			FinancierDtlGrpData.addContent(SrNo);
			
			FinancierDtlGrp.addContent(FinancierDtlGrpData);
			FinancierDetails.addContent(FinancierDtlGrp);

			GeneralProposal.addContent(FinancierDetails);
			
			org.jdom2.Element PreviousPolicyDetails= new org.jdom2.Element("PreviousPolicyDetails");
			PreviousPolicyDetails.setAttribute("Name", "Previous Policy Details");
			
			org.jdom2.Element PreviousPolDtlGroup= new org.jdom2.Element("PreviousPolDtlGroup");
			PreviousPolDtlGroup.setAttribute("Type", "Group");    
			PreviousPolDtlGroup.setAttribute("Name", "Previous Pol Dtl Group");  
			
			org.jdom2.Element PreviousPolDtlGroupData= new org.jdom2.Element("PreviousPolDtlGroupData");
			PreviousPolDtlGroupData.setAttribute("Type", "GroupData"); 
			
			org.jdom2.Element ProductCodePrev= new org.jdom2.Element("ProductCode");
			ProductCodePrev.setAttribute("Name", "Product Code");    
			ProductCodePrev.setAttribute("Value", "");  
			
			org.jdom2.Element ClaimSettled= new org.jdom2.Element("ClaimSettled");
			ClaimSettled.setAttribute("Name", "Claim Settled");    
			ClaimSettled.setAttribute("Value", "");   
			
			org.jdom2.Element ClaimPremium= new org.jdom2.Element("ClaimPremium");
			ClaimPremium.setAttribute("Name", "Claim Premium");    
			ClaimPremium.setAttribute("Value", "");   
			
			org.jdom2.Element ClaimAmount= new org.jdom2.Element("ClaimAmount");
			ClaimAmount.setAttribute("Name", "Claim Amount");    
			ClaimAmount.setAttribute("Value", "");  
			
			org.jdom2.Element DateofLoss= new org.jdom2.Element("DateofLoss");
			DateofLoss.setAttribute("Name", "Date Of Loss");    
			DateofLoss.setAttribute("Value", "");  
			
			org.jdom2.Element NatureofLoss= new org.jdom2.Element("NatureofLoss");
			NatureofLoss.setAttribute("Name", "Nature Of Loss");    
			NatureofLoss.setAttribute("Value",""); 
			
			org.jdom2.Element ClaimNo= new org.jdom2.Element("ClaimNo");
			ClaimNo.setAttribute("Name", "Claim No");    
			ClaimNo.setAttribute("Value", "9999999999"); 
			
			org.jdom2.Element PolicyEffectiveTo= new org.jdom2.Element("PolicyEffectiveTo");
			PolicyEffectiveTo.setAttribute("Name", "Policy Effective To");    
			PolicyEffectiveTo.setAttribute("Value", "99/99/9999");   
								
			org.jdom2.Element PolicyEffectiveFrom= new org.jdom2.Element("PolicyEffectiveFrom");
			PolicyEffectiveFrom.setAttribute("Name", "Policy Effective From");    
			PolicyEffectiveFrom.setAttribute("Value","99/99/9999");    
			
			org.jdom2.Element PolicyPremium= new org.jdom2.Element("PolicyPremium");
			PolicyPremium.setAttribute("Name", "PolicyPremium");    
			PolicyPremium.setAttribute("Value", "10000.00");    
			
			org.jdom2.Element PolicyNo= new org.jdom2.Element("PolicyNo");
			PolicyNo.setAttribute("Name", "Policy No");    
			PolicyNo.setAttribute("Value", "");    
			
			org.jdom2.Element PolicyYear= new org.jdom2.Element("PolicyYear");
			PolicyYear.setAttribute("Name", "Policy Year");    
			PolicyYear.setAttribute("Value", "");    
			
			org.jdom2.Element OfficeCode= new org.jdom2.Element("OfficeCode");
			OfficeCode.setAttribute("Name", "Office Name");    
			OfficeCode.setAttribute("Value", "");   
			
			org.jdom2.Element CorporateCustomerId= new org.jdom2.Element("CorporateCustomerId");
			CorporateCustomerId.setAttribute("Name", "Corporate Customer Id");    
			CorporateCustomerId.setAttribute("Value", "");  
			
			org.jdom2.Element InsurerName= new org.jdom2.Element("InsurerName");
			InsurerName.setAttribute("Name", "InsurerName");    
			InsurerName.setAttribute("Value", "");    
			
			org.jdom2.Element InsurerAddress= new org.jdom2.Element("InsurerAddress");
			InsurerAddress.setAttribute("Name", "InsurerAddress");    
			InsurerAddress.setAttribute("Value", "");    
			
			
			PreviousPolDtlGroupData.addContent(ProductCodePrev);
			PreviousPolDtlGroupData.addContent(ClaimSettled);
			PreviousPolDtlGroupData.addContent(ClaimPremium);
			PreviousPolDtlGroupData.addContent(ClaimAmount);
			PreviousPolDtlGroupData.addContent(DateofLoss);
			PreviousPolDtlGroupData.addContent(NatureofLoss);
			PreviousPolDtlGroupData.addContent(ClaimNo);
			PreviousPolDtlGroupData.addContent(PolicyEffectiveTo);
			PreviousPolDtlGroupData.addContent(PolicyEffectiveFrom);
			PreviousPolDtlGroupData.addContent(PolicyPremium);
			PreviousPolDtlGroupData.addContent(PolicyNo);
			PreviousPolDtlGroupData.addContent(PolicyYear);
			PreviousPolDtlGroupData.addContent(OfficeCode);
			PreviousPolDtlGroupData.addContent(CorporateCustomerId);
			PreviousPolDtlGroupData.addContent(InsurerName);
			PreviousPolDtlGroupData.addContent(InsurerAddress);
			
			PreviousPolDtlGroup.addContent(PreviousPolDtlGroupData);
			PreviousPolicyDetails.addContent(PreviousPolDtlGroup);		
			GeneralProposal.addContent(PreviousPolicyDetails);
			
			
			org.jdom2.Element PreviousPolicyType= new org.jdom2.Element("PreviousPolicyType");
			PreviousPolicyType.setAttribute("Name", "Previous Policy Type");
			PreviousPolicyType.setAttribute("Value", "Package Policy");
			
			org.jdom2.Element OfficeAddress= new org.jdom2.Element("OfficeAddress");
			OfficeAddress.setAttribute("Name", "Office Address");    
			OfficeAddress.setAttribute("Value", "");    
			
			PreviousPolicyDetails.addContent(PreviousPolicyType);
			PreviousPolicyDetails.addContent(OfficeAddress);

//			PreviousPolicyDetails.addContent(PreviousPolDtlGroup);
			
			
			
			org.jdom2.Element PremiumCalculation= new org.jdom2.Element("PremiumCalculation");
			PremiumCalculation.setAttribute("Name", "Premium Calculation");    
			
			org.jdom2.Element NetPremium= new org.jdom2.Element("NetPremium");
			NetPremium.setAttribute("Name", "Net Premium");    
			NetPremium.setAttribute("Value", "4810.82");  
			
			org.jdom2.Element ServiceTax= new org.jdom2.Element("ServiceTax");
			ServiceTax.setAttribute("Name", "Service Tax");    
			ServiceTax.setAttribute("Value", "0"); 
			
			org.jdom2.Element StampDuty2= new org.jdom2.Element("StampDuty2");
			StampDuty2.setAttribute("Name", "Stamp Duty");    
			StampDuty2.setAttribute("Value", "0.5");  
			
			org.jdom2.Element CGST= new org.jdom2.Element("CGST");
			CGST.setAttribute("Name", "CGST");    
			CGST.setAttribute("Value", "433");   
			
			org.jdom2.Element SGST= new org.jdom2.Element("SGST");
			SGST.setAttribute("Name", "SGST");    
			SGST.setAttribute("Value", "433");  
			
			org.jdom2.Element UGST= new org.jdom2.Element("UGST");
			UGST.setAttribute("Name", "UGST");    
			UGST.setAttribute("Value", "0");  
			
			org.jdom2.Element IGST= new org.jdom2.Element("IGST");
			IGST.setAttribute("Name", "IGST");    
			IGST.setAttribute("Value", "0");   
			
			org.jdom2.Element TotalPremium= new org.jdom2.Element("TotalPremium");
			TotalPremium.setAttribute("Name", "Total Premium");    
			TotalPremium.setAttribute("Value", "5677");    
			
			PremiumCalculation.addContent(NetPremium);
			PremiumCalculation.addContent(ServiceTax);
			PremiumCalculation.addContent(StampDuty2);
			PremiumCalculation.addContent(CGST);
			PremiumCalculation.addContent(SGST);
			PremiumCalculation.addContent(UGST);
			PremiumCalculation.addContent(IGST);
			PremiumCalculation.addContent(TotalPremium);
			
			
			org.jdom2.Element Risks= new org.jdom2.Element("Risks");
			Risks.setAttribute("Name", "Risks");    
			
			org.jdom2.Element Risk= new org.jdom2.Element("Risk");
			Risk.setAttribute("Type", "Group");    
			Risk.setAttribute("Name", "Risks");  
			
			org.jdom2.Element RisksData= new org.jdom2.Element("RisksData");
			RisksData.setAttribute("Type", "GroupData");  
			
			org.jdom2.Element InsuredDetails= new org.jdom2.Element("InsuredDetails");
			InsuredDetails.setAttribute("Name", "Insured Details");   
			
			org.jdom2.Element InsuredDetailGroup= new org.jdom2.Element("InsuredDetailGroup");
			InsuredDetailGroup.setAttribute("Type", "Group");    
			InsuredDetailGroup.setAttribute("Name", "Insured Details");
			
			InsuredDetails.addContent(InsuredDetailGroup);
			RisksData.addContent(InsuredDetails);

	    	org.jdom2.Element OtherLoadings= new org.jdom2.Element("OtherLoadings");
	    	OtherLoadings.setAttribute("Name", "OtherLoadings");    
				
	    	org.jdom2.Element OtherLoadingGroup= new org.jdom2.Element("OtherLoadingGroup");
	    	OtherLoadingGroup.setAttribute("Name", "Other Loading Group");    
	    	OtherLoadingGroup.setAttribute("Type", "Group");    
	    	
	    	
	    	org.jdom2.Element OtherLoadingGroupData= new org.jdom2.Element("OtherLoadingGroupData");
	    	OtherLoadingGroupData.setAttribute("Type", "Group");
	    	
	    	org.jdom2.Element LoadingAmount= new org.jdom2.Element("LoadingAmount");
	    	LoadingAmount.setAttribute("Name", "Loading Amount");    
	    	LoadingAmount.setAttribute("Value", "");    
	    	
	    	org.jdom2.Element LoadingRate= new org.jdom2.Element("LoadingRate");
	    	LoadingRate.setAttribute("Name", "Loading Rate");    
	    	LoadingRate.setAttribute("Value", "");    
	    	
	    	org.jdom2.Element SumInsured= new org.jdom2.Element("SumInsured");
	    	SumInsured.setAttribute("Name", "SumInsured");    
	    	SumInsured.setAttribute("Value", "");    
	    	
	    	org.jdom2.Element Rate= new org.jdom2.Element("Rate");
	    	Rate.setAttribute("Name", "Rate");    
	    	Rate.setAttribute("Value", "");    
	    	
	    	org.jdom2.Element Premium= new org.jdom2.Element("Premium");
	    	Premium.setAttribute("Name", "Premium");    
	    	Premium.setAttribute("Value", "");    
	    	
	    	org.jdom2.Element Applicable= new org.jdom2.Element("Applicable");
	    	Applicable.setAttribute("Name", "Applicable");    
	    	Applicable.setAttribute("Value", "True");    
	    	
	    	org.jdom2.Element Description= new org.jdom2.Element("Description");
	    	Description.setAttribute("Name", "Description");    
	    	Description.setAttribute("Value", "");    
	    	
	    	OtherLoadingGroupData.addContent(LoadingAmount);
	    	OtherLoadingGroupData.addContent(LoadingRate);
	    	OtherLoadingGroupData.addContent(SumInsured);
	    	OtherLoadingGroupData.addContent(Rate);
	    	OtherLoadingGroupData.addContent(Premium);
	    	OtherLoadingGroupData.addContent(Applicable);
	    	OtherLoadingGroupData.addContent(Description);
	    	
	    	OtherLoadingGroup.addContent(OtherLoadingGroupData);
	    	OtherLoadings.addContent(OtherLoadingGroup);
	    	
			RisksData.addContent(OtherLoadings);
			
			org.jdom2.Element OtherDiscounts= new org.jdom2.Element("OtherDiscounts");
			OtherDiscounts.setAttribute("Name", "OtherDiscounts");    
			
			
			org.jdom2.Element OtherDiscountGroup= new org.jdom2.Element("OtherDiscountGroup");
			OtherDiscountGroup.setAttribute("Name", "Other Discount Group");    
			OtherDiscountGroup.setAttribute("Type", "Group");    
			
			
			org.jdom2.Element OtherDiscountGroupData1= new org.jdom2.Element("OtherDiscountGroupData");
			OtherDiscountGroupData1.setAttribute("Type", "GroupData");    
			
			org.jdom2.Element DiscountAmount= new org.jdom2.Element("DiscountAmount");
			DiscountAmount.setAttribute("Name", "Discount Amount");    
			DiscountAmount.setAttribute("Value", "");    
			
			org.jdom2.Element DiscountRate= new org.jdom2.Element("DiscountRate");
			DiscountRate.setAttribute("Name", "Discount Rate");    
			DiscountRate.setAttribute("Value", "");    
			
			
			org.jdom2.Element SumInsuredOd= new org.jdom2.Element("SumInsured");
			SumInsuredOd.setAttribute("Name", "SumInsured");    
			SumInsuredOd.setAttribute("Value", "");    
			
			org.jdom2.Element RateOd= new org.jdom2.Element("Rate");
			RateOd.setAttribute("Name", "Rate");    
			RateOd.setAttribute("Value", "");    
			
			org.jdom2.Element PremiumOd= new org.jdom2.Element("Premium");
			PremiumOd.setAttribute("Name", "Premium");    
			PremiumOd.setAttribute("Value", "");    
			
			org.jdom2.Element ApplicableOd= new org.jdom2.Element("Applicable");
			ApplicableOd.setAttribute("Name", "Applicable");    
			ApplicableOd.setAttribute("Value", "True");    
			
			org.jdom2.Element DescriptionOd= new org.jdom2.Element("Description");
			DescriptionOd.setAttribute("Name", "Sub Category Discount");    
			
			OtherDiscountGroupData1.addContent(DiscountAmount);
			OtherDiscountGroupData1.addContent(DiscountRate);
			OtherDiscountGroupData1.addContent(SumInsuredOd);
			OtherDiscountGroupData1.addContent(RateOd);
			OtherDiscountGroupData1.addContent(PremiumOd);
			OtherDiscountGroupData1.addContent(ApplicableOd);
			OtherDiscountGroupData1.addContent(DescriptionOd);
			
			org.jdom2.Element OtherDiscountGroupData2= new org.jdom2.Element("OtherDiscountGroupData");
			OtherDiscountGroupData2.setAttribute("Type", "GroupData");  
			
			org.jdom2.Element DiscountAmount2= new org.jdom2.Element("DiscountAmount");
			DiscountAmount2.setAttribute("Name", "Discount Amount");    
			DiscountAmount2.setAttribute("Value", "");    
			
			org.jdom2.Element DiscountRate2= new org.jdom2.Element("DiscountRate");
			DiscountRate2.setAttribute("Name", "Discount Rate");    
			DiscountRate2.setAttribute("Value", "");    
			
			
			org.jdom2.Element SumInsuredOd2= new org.jdom2.Element("SumInsured");
			SumInsuredOd2.setAttribute("Name", "SumInsured");    
			SumInsuredOd2.setAttribute("Value", "");    
			
			org.jdom2.Element RateOd2= new org.jdom2.Element("Rate");
			RateOd2.setAttribute("Name", "Rate");    
			RateOd2.setAttribute("Value", "");    
			
			org.jdom2.Element PremiumOd2= new org.jdom2.Element("Premium");
			PremiumOd2.setAttribute("Name", "Premium");    
			PremiumOd2.setAttribute("Value", "");    
			
			org.jdom2.Element ApplicableOd2= new org.jdom2.Element("Applicable");
			ApplicableOd2.setAttribute("Name", "Applicable");    
			ApplicableOd2.setAttribute("Value", "True");    
			
			org.jdom2.Element DescriptionOd2= new org.jdom2.Element("Description");
			DescriptionOd2.setAttribute("Name", "Tiered Hospital Discount");
			
			OtherDiscountGroupData2.addContent(DiscountAmount2);
			OtherDiscountGroupData2.addContent(DiscountRate2);
			OtherDiscountGroupData2.addContent(SumInsuredOd2);
			OtherDiscountGroupData2.addContent(RateOd2);
			OtherDiscountGroupData2.addContent(PremiumOd2);
			OtherDiscountGroupData2.addContent(ApplicableOd2);
			OtherDiscountGroupData2.addContent(DescriptionOd2);
			
			
			
			org.jdom2.Element OtherDiscountGroupData3= new org.jdom2.Element("OtherDiscountGroupData");
			OtherDiscountGroupData3.setAttribute("Type", "GroupData");  
			
			org.jdom2.Element DiscountAmount3= new org.jdom2.Element("DiscountAmount");
			DiscountAmount3.setAttribute("Name", "Discount Amount");    
			DiscountAmount3.setAttribute("Value", "");    
			
			org.jdom2.Element DiscountRate3= new org.jdom2.Element("DiscountRate");
			DiscountRate3.setAttribute("Name", "Discount Rate");    
			DiscountRate3.setAttribute("Value", "");    
			
			
			org.jdom2.Element SumInsuredOd3= new org.jdom2.Element("SumInsured");
			SumInsuredOd3.setAttribute("Name", "SumInsured");    
			SumInsuredOd3.setAttribute("Value", "");    
			
			org.jdom2.Element RateOd3= new org.jdom2.Element("Rate");
			RateOd3.setAttribute("Name", "Rate");    
			RateOd3.setAttribute("Value", "");    
			
			org.jdom2.Element PremiumOd3= new org.jdom2.Element("Premium");
			PremiumOd3.setAttribute("Name", "Premium");    
			PremiumOd3.setAttribute("Value", "");    
			
			org.jdom2.Element ApplicableOd3= new org.jdom2.Element("Applicable");
			ApplicableOd3.setAttribute("Name", "Applicable");    
			ApplicableOd3.setAttribute("Value", "True");    
			
			org.jdom2.Element DescriptionOd3= new org.jdom2.Element("Description");
			DescriptionOd3.setAttribute("Name", "Policy Duration Discount");
			
			OtherDiscountGroupData3.addContent(DiscountAmount3);
			OtherDiscountGroupData3.addContent(DiscountRate3);
			OtherDiscountGroupData3.addContent(SumInsuredOd3);
			OtherDiscountGroupData3.addContent(RateOd3);
			OtherDiscountGroupData3.addContent(PremiumOd3);
			OtherDiscountGroupData3.addContent(ApplicableOd3);
			OtherDiscountGroupData3.addContent(DescriptionOd3);
			
			
			org.jdom2.Element OtherDiscountGroupData4= new org.jdom2.Element("OtherDiscountGroupData");
			OtherDiscountGroupData4.setAttribute("Type", "GroupData");  
			
			org.jdom2.Element DiscountAmount4= new org.jdom2.Element("DiscountAmount");
			DiscountAmount4.setAttribute("Name", "Discount Amount");    
			DiscountAmount4.setAttribute("Value", "");    
			
			org.jdom2.Element DiscountRate4= new org.jdom2.Element("DiscountRate");
			DiscountRate4.setAttribute("Name", "Discount Rate");    
			DiscountRate4.setAttribute("Value", "");    
			
			
			org.jdom2.Element SumInsuredOd4= new org.jdom2.Element("SumInsured");
			SumInsuredOd4.setAttribute("Name", "SumInsured");    
			SumInsuredOd4.setAttribute("Value", "");    
			
			org.jdom2.Element RateOd4= new org.jdom2.Element("Rate");
			RateOd4.setAttribute("Name", "Rate");    
			RateOd4.setAttribute("Value", "");    
			
			org.jdom2.Element PremiumOd4= new org.jdom2.Element("Premium");
			PremiumOd4.setAttribute("Name", "Premium");    
			PremiumOd4.setAttribute("Value", "");    
			
			org.jdom2.Element ApplicableOd4= new org.jdom2.Element("Applicable");
			ApplicableOd4.setAttribute("Name", "Applicable");    
			ApplicableOd4.setAttribute("Value", "True");    
			
			org.jdom2.Element DescriptionOd4 = new org.jdom2.Element("Description");
			DescriptionOd4.setAttribute("Name", "E-sale Discount");
			
			OtherDiscountGroupData4.addContent(DiscountAmount4);
			OtherDiscountGroupData4.addContent(DiscountRate4);
			OtherDiscountGroupData4.addContent(SumInsuredOd4);
			OtherDiscountGroupData4.addContent(RateOd4);
			OtherDiscountGroupData4.addContent(PremiumOd4);
			OtherDiscountGroupData4.addContent(ApplicableOd4);
			OtherDiscountGroupData4.addContent(DescriptionOd4);
		
			
			org.jdom2.Element OtherDiscountGroupData5= new org.jdom2.Element("OtherDiscountGroupData");
			OtherDiscountGroupData5.setAttribute("Type", "GroupData");  
			
			org.jdom2.Element DiscountAmount5= new org.jdom2.Element("DiscountAmount");
			DiscountAmount5.setAttribute("Name", "Discount Amount");    
			DiscountAmount5.setAttribute("Value", "");    
			
			org.jdom2.Element DiscountRate5= new org.jdom2.Element("DiscountRate");
			DiscountRate5.setAttribute("Name", "Discount Rate");    
			DiscountRate5.setAttribute("Value", "");    
			
			
			org.jdom2.Element SumInsuredOd5= new org.jdom2.Element("SumInsured");
			SumInsuredOd5.setAttribute("Name", "SumInsured");    
			SumInsuredOd5.setAttribute("Value", "");    
			
			org.jdom2.Element RateOd5= new org.jdom2.Element("Rate");
			RateOd5.setAttribute("Name", "Rate");    
			RateOd5.setAttribute("Value", "");    
			
			org.jdom2.Element PremiumOd5= new org.jdom2.Element("Premium");
			PremiumOd5.setAttribute("Name", "Premium");    
			PremiumOd5.setAttribute("Value", "");    
			
			org.jdom2.Element ApplicableOd5= new org.jdom2.Element("Applicable");
			ApplicableOd5.setAttribute("Name", "Applicable");    
			ApplicableOd5.setAttribute("Value", "True");    
			
			org.jdom2.Element DescriptionOd5 = new org.jdom2.Element("Description");
			DescriptionOd5.setAttribute("Name", "Life Style Discount");
			
			OtherDiscountGroupData5.addContent(DiscountAmount5);
			OtherDiscountGroupData5.addContent(DiscountRate5);
			OtherDiscountGroupData5.addContent(SumInsuredOd5);
			OtherDiscountGroupData5.addContent(RateOd5);
			OtherDiscountGroupData5.addContent(PremiumOd5);
			OtherDiscountGroupData5.addContent(ApplicableOd5);
			OtherDiscountGroupData5.addContent(DescriptionOd5);
			
			
			org.jdom2.Element OtherDiscountGroupData6= new org.jdom2.Element("OtherDiscountGroupData");
			OtherDiscountGroupData6.setAttribute("Type", "GroupData");  
			
			org.jdom2.Element DiscountAmount6= new org.jdom2.Element("DiscountAmount");
			DiscountAmount6.setAttribute("Name", "Discount Amount");    
			DiscountAmount6.setAttribute("Value", "");    
			
			org.jdom2.Element DiscountRate6= new org.jdom2.Element("DiscountRate");
			DiscountRate6.setAttribute("Name", "Discount Rate");    
			DiscountRate6.setAttribute("Value", "");    
			
			
			org.jdom2.Element SumInsuredOd6= new org.jdom2.Element("SumInsured");
			SumInsuredOd6.setAttribute("Name", "SumInsured");    
			SumInsuredOd6.setAttribute("Value", "");    
			
			org.jdom2.Element RateOd6= new org.jdom2.Element("Rate");
			RateOd6.setAttribute("Name", "Rate");    
			RateOd6.setAttribute("Value", "");    
			
			org.jdom2.Element PremiumOd6= new org.jdom2.Element("Premium");
			PremiumOd6.setAttribute("Name", "Premium");    
			PremiumOd6.setAttribute("Value", "");    
			
			org.jdom2.Element ApplicableOd6= new org.jdom2.Element("Applicable");
			ApplicableOd6.setAttribute("Name", "Applicable");    
			ApplicableOd6.setAttribute("Value", "True");    
			
			org.jdom2.Element DescriptionOd6 = new org.jdom2.Element("Description");
			DescriptionOd6.setAttribute("Name", "No of Members Discount");
			
			OtherDiscountGroupData6.addContent(DiscountAmount6);
			OtherDiscountGroupData6.addContent(DiscountRate6);
			OtherDiscountGroupData6.addContent(SumInsuredOd6);
			OtherDiscountGroupData6.addContent(RateOd6);
			OtherDiscountGroupData6.addContent(PremiumOd6);
			OtherDiscountGroupData6.addContent(ApplicableOd6);
			OtherDiscountGroupData6.addContent(DescriptionOd6);
			
			OtherDiscountGroup.addContent(OtherDiscountGroupData1);
			OtherDiscountGroup.addContent(OtherDiscountGroupData2);
			OtherDiscountGroup.addContent(OtherDiscountGroupData3);
			OtherDiscountGroup.addContent(OtherDiscountGroupData4);
			OtherDiscountGroup.addContent(OtherDiscountGroupData5);
			OtherDiscountGroup.addContent(OtherDiscountGroupData6);
			
			OtherDiscounts.addContent(OtherDiscountGroup);
			RisksData.addContent(OtherDiscounts);
			Risk.addContent(RisksData);
			Risks.addContent(Risk);
			Product.addContent(Risks);
			
			
			org.jdom2.Element PaymentDetails = new org.jdom2.Element("PaymentDetails");
			org.jdom2.Element PaymentEntry = new org.jdom2.Element("PaymentEntry");
			org.jdom2.Element PaymentId = new org.jdom2.Element("PaymentId");
			org.jdom2.Element MICRCheque = new org.jdom2.Element("MICRCheque");
			org.jdom2.Element InstrumentDatePay = new org.jdom2.Element("InstrumentDate");
			org.jdom2.Element DraweeBankName = new org.jdom2.Element("DraweeBankName");
			org.jdom2.Element HOUSEBANKNAME = new org.jdom2.Element("HOUSEBANKNAME");
			org.jdom2.Element AmountPaid = new org.jdom2.Element("AmountPaid");
			org.jdom2.Element PaymentType = new org.jdom2.Element("PaymentType");
			org.jdom2.Element PaymentMode = new org.jdom2.Element("PaymentMode");
			org.jdom2.Element InstrumentNoPay = new org.jdom2.Element("InstrumentNo");
			org.jdom2.Element Status = new org.jdom2.Element("Status");
			org.jdom2.Element DepositSlipNo = new org.jdom2.Element("DepositSlipNo");
			org.jdom2.Element PayerType = new org.jdom2.Element("PayerType");
			
			PaymentEntry.addContent(PaymentId);
			PaymentEntry.addContent(MICRCheque);
			PaymentEntry.addContent(InstrumentDatePay);
			PaymentEntry.addContent(DraweeBankName);
			PaymentEntry.addContent(HOUSEBANKNAME);
			PaymentEntry.addContent(AmountPaid);
			PaymentEntry.addContent(PaymentType);
			PaymentEntry.addContent(PaymentMode);
			PaymentEntry.addContent(InstrumentNoPay);
			PaymentEntry.addContent(Status);
			PaymentEntry.addContent(DepositSlipNo);
			PaymentEntry.addContent(PayerType);
			
			PaymentDetails.addContent(PaymentEntry);
			
			org.jdom2.Element Errors=new org.jdom2.Element("Errors");	
			org.jdom2.Element ErrorCode=new org.jdom2.Element("ErrorCode");	
			org.jdom2.Element ErrDescription=new org.jdom2.Element("ErrDescription");	
		
			Errors.addContent(ErrorCode);
			Errors.addContent(ErrDescription);
			rootelement.addContent(Authentication) ;
			rootelement.addContent(Customer) ;
			rootelement.addContent(Product) ;
			rootelement.addContent(PaymentDetails);
			rootelement.addContent(Errors);
			XMLOutputter xmlOutput = new XMLOutputter();
			xmlOutput.setFormat(Format.getPrettyFormat());
			xx = xmlOutput.outputString(document2);

			try {
				xmlOutput.output(document2, new FileWriter(path+"\\UniversalHealthRequest.xml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
	      return xx;
		
		}
}
