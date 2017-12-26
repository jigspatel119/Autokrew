package com.autokrew.models;

import java.util.List;

/**
 * Created by SONY on 03-08-2016.
 */
public class MyprofileModel {


    private List<TableBean> Table;

    public List<TableBean> getTable() {
        return Table;
    }

    public void setTable(List<TableBean> Table) {
        this.Table = Table;
    }

    public static class TableBean {
        /**
         * EmployeePK : 8816
         * EmployeeCode : CA140000126
         * FirstName : Devki
         * MiddleName :
         * LastName : Nanda
         * GenderPK : 2
         * MaritalStatusPK : 1
         * DateOfBirth : 11/06/2000
         * PersonalMobileNo : 9999999999
         * residenceNo :
         * PersonalEmail : ujjwal@ibots.tech
         * nationalityFK : 1
         * ReligionPK : 1
         * CurrentAddress : ahmedabad
         * CurrStateFK : 7
         * CurrCityFK : 4
         * CurrPincode : 380001
         * PerAddress : ahmedabad
         * PerStateFK : 7
         * PerCityFK : 4
         * PerPincode : 380001
         * HeightPK : -1
         * WeightPK : -1
         * BloodGroupPK : -1
         * IsDisability : 0
         * isSameAddress : 0
         * CompanyPK : 14
         * LocationPK : 12
         * SubLocationPK : 45
         * VerticalPK : 13
         * DepartmentPK : 42
         * SubDepartmentPK : 138
         * DesignationPK : 230
         * PhotoImageUrl : 8816/a9a46b04-76fd-4434-9f05-2cf78fff06b0_images.png
         * DisabilityText :
         * DisabilityAttachement :
         * ProbationPK : 3
         * OfferDate : 01/05/2010
         * JoiningDate : 01/05/2010
         * EmployeeTypePK : 3
         * BankName :
         * BankBranch :
         * IFSCCode :
         * BankAccountNo :
         * PFAccountNo :
         * ESICAccountNo : null
         * UANNo :
         * PANNo :
         * AadharCardNo :
         * IsSalaryByCheque : null
         * ReportingPersonFK : 8816
         * HODFK : 8816
         * FuncHeadFK : 8816
         * IsPunchCard : 0
         * IsEmail : 0
         * IsCUG : 0
         * IsDataCard : 0
         * IsMobile : 0
         * IsLaptop : 0
         * IsIpad : 0
         * PunchCardNo : 111
         * AssetEmail : ujjwal@ibots.tech
         * CUGNo :
         * DataCardNo :
         * IPadNo :
         * MobileModel : 9999999999
         * LaptopNo :
         * PunchAssignDate : null
         * PunchDocURL :
         * PunchReturnDate : null
         * PunchRemarks :
         * EmailAssigndate : null
         * EmailDocURL :
         * EmailReturnDate : null
         * EmailRemarks :
         * CUGAssignDate : null
         * CUGDocURL :
         * CUGReturnDate : null
         * CUGRemarks :
         * DataCardAssignDate : null
         * DataCardDocURL :
         * DataCardReturnDate : null
         * DataCardRemarks :
         * MobileAssignDate : null
         * MobileDocURL :
         * MobileReturnDate : null
         * MobileRemarks :
         * IpadAssignDate : null
         * IpadDocURL :
         * IpadReturnDate : null
         * IpadRemarks :
         * LaptopAssignDate : null
         * LaptopDocURL :
         * LaptopReturnDate : null
         * LaptopRemarks :
         * ReprotingLocationFk : 12
         * CostCenterPK : 2
         * OtherEmployeeCode :
         * MaritalStatus : Single
         * CurrentState : Gujarat
         * PermenantState : Gujarat
         * CurrentCity : Ahmedabad
         * PermenantCity : Ahmedabad
         * Height : -
         * Weight : -
         * BloodGroup : -
         * Religion : Hindu
         * Nationality : Indian
         */

        private int EmployeePK;
        private String EmployeeCode;
        private String FirstName;
        private String MiddleName;
        private String LastName;
        private int GenderPK;
        private int MaritalStatusPK;
        private String DateOfBirth;
        private String PersonalMobileNo;
        private String residenceNo;
        private String PersonalEmail;
        private int nationalityFK;
        private int ReligionPK;
        private String CurrentAddress;
        private int CurrStateFK;
        private int CurrCityFK;
        private int CurrPincode;
        private String PerAddress;
        private int PerStateFK;
        private int PerCityFK;
        private int PerPincode;
        private int HeightPK;
        private int WeightPK;
        private int BloodGroupPK;
        private int IsDisability;
        private int isSameAddress;
        private int CompanyPK;
        private int LocationPK;
        private int SubLocationPK;
        private int VerticalPK;
        private int DepartmentPK;
        private int SubDepartmentPK;
        private int DesignationPK;
        private String PhotoImageUrl;
        private String DisabilityText;
        private String DisabilityAttachement;
        private int ProbationPK;
        private String OfferDate;
        private String JoiningDate;
        private int EmployeeTypePK;
        private String BankName;
        private String BankBranch;
        private String IFSCCode;
        private String BankAccountNo;
        private String PFAccountNo;
        private Object ESICAccountNo;
        private String UANNo;
        private String PANNo;
        private String AadharCardNo;
        private Object IsSalaryByCheque;
        private int ReportingPersonFK;
        private int HODFK;
        private int FuncHeadFK;
        private int IsPunchCard;
        private int IsEmail;
        private int IsCUG;
        private int IsDataCard;
        private int IsMobile;
        private int IsLaptop;
        private int IsIpad;
        private String PunchCardNo;
        private String AssetEmail;
        private String CUGNo;
        private String DataCardNo;
        private String IPadNo;
        private String MobileModel;
        private String LaptopNo;
        private Object PunchAssignDate;
        private String PunchDocURL;
        private Object PunchReturnDate;
        private String PunchRemarks;
        private Object EmailAssigndate;
        private String EmailDocURL;
        private Object EmailReturnDate;
        private String EmailRemarks;
        private Object CUGAssignDate;
        private String CUGDocURL;
        private Object CUGReturnDate;
        private String CUGRemarks;
        private Object DataCardAssignDate;
        private String DataCardDocURL;
        private Object DataCardReturnDate;
        private String DataCardRemarks;
        private Object MobileAssignDate;
        private String MobileDocURL;
        private Object MobileReturnDate;
        private String MobileRemarks;
        private Object IpadAssignDate;
        private String IpadDocURL;
        private Object IpadReturnDate;
        private String IpadRemarks;
        private Object LaptopAssignDate;
        private String LaptopDocURL;
        private Object LaptopReturnDate;
        private String LaptopRemarks;
        private int ReprotingLocationFk;
        private int CostCenterPK;
        private String OtherEmployeeCode;
        private String MaritalStatus;
        private String CurrentState;
        private String PermenantState;
        private String CurrentCity;
        private String PermenantCity;
        private String Height;
        private String Weight;
        private String BloodGroup;
        private String Religion;
        private String Nationality;

        public int getEmployeePK() {
            return EmployeePK;
        }

        public void setEmployeePK(int EmployeePK) {
            this.EmployeePK = EmployeePK;
        }

        public String getEmployeeCode() {
            return EmployeeCode;
        }

        public void setEmployeeCode(String EmployeeCode) {
            this.EmployeeCode = EmployeeCode;
        }

        public String getFirstName() {
            return FirstName;
        }

        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        public String getMiddleName() {
            return MiddleName;
        }

        public void setMiddleName(String MiddleName) {
            this.MiddleName = MiddleName;
        }

        public String getLastName() {
            return LastName;
        }

        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public int getGenderPK() {
            return GenderPK;
        }

        public void setGenderPK(int GenderPK) {
            this.GenderPK = GenderPK;
        }

        public int getMaritalStatusPK() {
            return MaritalStatusPK;
        }

        public void setMaritalStatusPK(int MaritalStatusPK) {
            this.MaritalStatusPK = MaritalStatusPK;
        }

        public String getDateOfBirth() {
            return DateOfBirth;
        }

        public void setDateOfBirth(String DateOfBirth) {
            this.DateOfBirth = DateOfBirth;
        }

        public String getPersonalMobileNo() {
            return PersonalMobileNo;
        }

        public void setPersonalMobileNo(String PersonalMobileNo) {
            this.PersonalMobileNo = PersonalMobileNo;
        }

        public String getResidenceNo() {
            return residenceNo;
        }

        public void setResidenceNo(String residenceNo) {
            this.residenceNo = residenceNo;
        }

        public String getPersonalEmail() {
            return PersonalEmail;
        }

        public void setPersonalEmail(String PersonalEmail) {
            this.PersonalEmail = PersonalEmail;
        }

        public int getNationalityFK() {
            return nationalityFK;
        }

        public void setNationalityFK(int nationalityFK) {
            this.nationalityFK = nationalityFK;
        }

        public int getReligionPK() {
            return ReligionPK;
        }

        public void setReligionPK(int ReligionPK) {
            this.ReligionPK = ReligionPK;
        }

        public String getCurrentAddress() {
            return CurrentAddress;
        }

        public void setCurrentAddress(String CurrentAddress) {
            this.CurrentAddress = CurrentAddress;
        }

        public int getCurrStateFK() {
            return CurrStateFK;
        }

        public void setCurrStateFK(int CurrStateFK) {
            this.CurrStateFK = CurrStateFK;
        }

        public int getCurrCityFK() {
            return CurrCityFK;
        }

        public void setCurrCityFK(int CurrCityFK) {
            this.CurrCityFK = CurrCityFK;
        }

        public int getCurrPincode() {
            return CurrPincode;
        }

        public void setCurrPincode(int CurrPincode) {
            this.CurrPincode = CurrPincode;
        }

        public String getPerAddress() {
            return PerAddress;
        }

        public void setPerAddress(String PerAddress) {
            this.PerAddress = PerAddress;
        }

        public int getPerStateFK() {
            return PerStateFK;
        }

        public void setPerStateFK(int PerStateFK) {
            this.PerStateFK = PerStateFK;
        }

        public int getPerCityFK() {
            return PerCityFK;
        }

        public void setPerCityFK(int PerCityFK) {
            this.PerCityFK = PerCityFK;
        }

        public int getPerPincode() {
            return PerPincode;
        }

        public void setPerPincode(int PerPincode) {
            this.PerPincode = PerPincode;
        }

        public int getHeightPK() {
            return HeightPK;
        }

        public void setHeightPK(int HeightPK) {
            this.HeightPK = HeightPK;
        }

        public int getWeightPK() {
            return WeightPK;
        }

        public void setWeightPK(int WeightPK) {
            this.WeightPK = WeightPK;
        }

        public int getBloodGroupPK() {
            return BloodGroupPK;
        }

        public void setBloodGroupPK(int BloodGroupPK) {
            this.BloodGroupPK = BloodGroupPK;
        }

        public int getIsDisability() {
            return IsDisability;
        }

        public void setIsDisability(int IsDisability) {
            this.IsDisability = IsDisability;
        }

        public int getIsSameAddress() {
            return isSameAddress;
        }

        public void setIsSameAddress(int isSameAddress) {
            this.isSameAddress = isSameAddress;
        }

        public int getCompanyPK() {
            return CompanyPK;
        }

        public void setCompanyPK(int CompanyPK) {
            this.CompanyPK = CompanyPK;
        }

        public int getLocationPK() {
            return LocationPK;
        }

        public void setLocationPK(int LocationPK) {
            this.LocationPK = LocationPK;
        }

        public int getSubLocationPK() {
            return SubLocationPK;
        }

        public void setSubLocationPK(int SubLocationPK) {
            this.SubLocationPK = SubLocationPK;
        }

        public int getVerticalPK() {
            return VerticalPK;
        }

        public void setVerticalPK(int VerticalPK) {
            this.VerticalPK = VerticalPK;
        }

        public int getDepartmentPK() {
            return DepartmentPK;
        }

        public void setDepartmentPK(int DepartmentPK) {
            this.DepartmentPK = DepartmentPK;
        }

        public int getSubDepartmentPK() {
            return SubDepartmentPK;
        }

        public void setSubDepartmentPK(int SubDepartmentPK) {
            this.SubDepartmentPK = SubDepartmentPK;
        }

        public int getDesignationPK() {
            return DesignationPK;
        }

        public void setDesignationPK(int DesignationPK) {
            this.DesignationPK = DesignationPK;
        }

        public String getPhotoImageUrl() {
            return PhotoImageUrl;
        }

        public void setPhotoImageUrl(String PhotoImageUrl) {
            this.PhotoImageUrl = PhotoImageUrl;
        }

        public String getDisabilityText() {
            return DisabilityText;
        }

        public void setDisabilityText(String DisabilityText) {
            this.DisabilityText = DisabilityText;
        }

        public String getDisabilityAttachement() {
            return DisabilityAttachement;
        }

        public void setDisabilityAttachement(String DisabilityAttachement) {
            this.DisabilityAttachement = DisabilityAttachement;
        }

        public int getProbationPK() {
            return ProbationPK;
        }

        public void setProbationPK(int ProbationPK) {
            this.ProbationPK = ProbationPK;
        }

        public String getOfferDate() {
            return OfferDate;
        }

        public void setOfferDate(String OfferDate) {
            this.OfferDate = OfferDate;
        }

        public String getJoiningDate() {
            return JoiningDate;
        }

        public void setJoiningDate(String JoiningDate) {
            this.JoiningDate = JoiningDate;
        }

        public int getEmployeeTypePK() {
            return EmployeeTypePK;
        }

        public void setEmployeeTypePK(int EmployeeTypePK) {
            this.EmployeeTypePK = EmployeeTypePK;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getBankBranch() {
            return BankBranch;
        }

        public void setBankBranch(String BankBranch) {
            this.BankBranch = BankBranch;
        }

        public String getIFSCCode() {
            return IFSCCode;
        }

        public void setIFSCCode(String IFSCCode) {
            this.IFSCCode = IFSCCode;
        }

        public String getBankAccountNo() {
            return BankAccountNo;
        }

        public void setBankAccountNo(String BankAccountNo) {
            this.BankAccountNo = BankAccountNo;
        }

        public String getPFAccountNo() {
            return PFAccountNo;
        }

        public void setPFAccountNo(String PFAccountNo) {
            this.PFAccountNo = PFAccountNo;
        }

        public Object getESICAccountNo() {
            return ESICAccountNo;
        }

        public void setESICAccountNo(Object ESICAccountNo) {
            this.ESICAccountNo = ESICAccountNo;
        }

        public String getUANNo() {
            return UANNo;
        }

        public void setUANNo(String UANNo) {
            this.UANNo = UANNo;
        }

        public String getPANNo() {
            return PANNo;
        }

        public void setPANNo(String PANNo) {
            this.PANNo = PANNo;
        }

        public String getAadharCardNo() {
            return AadharCardNo;
        }

        public void setAadharCardNo(String AadharCardNo) {
            this.AadharCardNo = AadharCardNo;
        }

        public Object getIsSalaryByCheque() {
            return IsSalaryByCheque;
        }

        public void setIsSalaryByCheque(Object IsSalaryByCheque) {
            this.IsSalaryByCheque = IsSalaryByCheque;
        }

        public int getReportingPersonFK() {
            return ReportingPersonFK;
        }

        public void setReportingPersonFK(int ReportingPersonFK) {
            this.ReportingPersonFK = ReportingPersonFK;
        }

        public int getHODFK() {
            return HODFK;
        }

        public void setHODFK(int HODFK) {
            this.HODFK = HODFK;
        }

        public int getFuncHeadFK() {
            return FuncHeadFK;
        }

        public void setFuncHeadFK(int FuncHeadFK) {
            this.FuncHeadFK = FuncHeadFK;
        }

        public int getIsPunchCard() {
            return IsPunchCard;
        }

        public void setIsPunchCard(int IsPunchCard) {
            this.IsPunchCard = IsPunchCard;
        }

        public int getIsEmail() {
            return IsEmail;
        }

        public void setIsEmail(int IsEmail) {
            this.IsEmail = IsEmail;
        }

        public int getIsCUG() {
            return IsCUG;
        }

        public void setIsCUG(int IsCUG) {
            this.IsCUG = IsCUG;
        }

        public int getIsDataCard() {
            return IsDataCard;
        }

        public void setIsDataCard(int IsDataCard) {
            this.IsDataCard = IsDataCard;
        }

        public int getIsMobile() {
            return IsMobile;
        }

        public void setIsMobile(int IsMobile) {
            this.IsMobile = IsMobile;
        }

        public int getIsLaptop() {
            return IsLaptop;
        }

        public void setIsLaptop(int IsLaptop) {
            this.IsLaptop = IsLaptop;
        }

        public int getIsIpad() {
            return IsIpad;
        }

        public void setIsIpad(int IsIpad) {
            this.IsIpad = IsIpad;
        }

        public String getPunchCardNo() {
            return PunchCardNo;
        }

        public void setPunchCardNo(String PunchCardNo) {
            this.PunchCardNo = PunchCardNo;
        }

        public String getAssetEmail() {
            return AssetEmail;
        }

        public void setAssetEmail(String AssetEmail) {
            this.AssetEmail = AssetEmail;
        }

        public String getCUGNo() {
            return CUGNo;
        }

        public void setCUGNo(String CUGNo) {
            this.CUGNo = CUGNo;
        }

        public String getDataCardNo() {
            return DataCardNo;
        }

        public void setDataCardNo(String DataCardNo) {
            this.DataCardNo = DataCardNo;
        }

        public String getIPadNo() {
            return IPadNo;
        }

        public void setIPadNo(String IPadNo) {
            this.IPadNo = IPadNo;
        }

        public String getMobileModel() {
            return MobileModel;
        }

        public void setMobileModel(String MobileModel) {
            this.MobileModel = MobileModel;
        }

        public String getLaptopNo() {
            return LaptopNo;
        }

        public void setLaptopNo(String LaptopNo) {
            this.LaptopNo = LaptopNo;
        }

        public Object getPunchAssignDate() {
            return PunchAssignDate;
        }

        public void setPunchAssignDate(Object PunchAssignDate) {
            this.PunchAssignDate = PunchAssignDate;
        }

        public String getPunchDocURL() {
            return PunchDocURL;
        }

        public void setPunchDocURL(String PunchDocURL) {
            this.PunchDocURL = PunchDocURL;
        }

        public Object getPunchReturnDate() {
            return PunchReturnDate;
        }

        public void setPunchReturnDate(Object PunchReturnDate) {
            this.PunchReturnDate = PunchReturnDate;
        }

        public String getPunchRemarks() {
            return PunchRemarks;
        }

        public void setPunchRemarks(String PunchRemarks) {
            this.PunchRemarks = PunchRemarks;
        }

        public Object getEmailAssigndate() {
            return EmailAssigndate;
        }

        public void setEmailAssigndate(Object EmailAssigndate) {
            this.EmailAssigndate = EmailAssigndate;
        }

        public String getEmailDocURL() {
            return EmailDocURL;
        }

        public void setEmailDocURL(String EmailDocURL) {
            this.EmailDocURL = EmailDocURL;
        }

        public Object getEmailReturnDate() {
            return EmailReturnDate;
        }

        public void setEmailReturnDate(Object EmailReturnDate) {
            this.EmailReturnDate = EmailReturnDate;
        }

        public String getEmailRemarks() {
            return EmailRemarks;
        }

        public void setEmailRemarks(String EmailRemarks) {
            this.EmailRemarks = EmailRemarks;
        }

        public Object getCUGAssignDate() {
            return CUGAssignDate;
        }

        public void setCUGAssignDate(Object CUGAssignDate) {
            this.CUGAssignDate = CUGAssignDate;
        }

        public String getCUGDocURL() {
            return CUGDocURL;
        }

        public void setCUGDocURL(String CUGDocURL) {
            this.CUGDocURL = CUGDocURL;
        }

        public Object getCUGReturnDate() {
            return CUGReturnDate;
        }

        public void setCUGReturnDate(Object CUGReturnDate) {
            this.CUGReturnDate = CUGReturnDate;
        }

        public String getCUGRemarks() {
            return CUGRemarks;
        }

        public void setCUGRemarks(String CUGRemarks) {
            this.CUGRemarks = CUGRemarks;
        }

        public Object getDataCardAssignDate() {
            return DataCardAssignDate;
        }

        public void setDataCardAssignDate(Object DataCardAssignDate) {
            this.DataCardAssignDate = DataCardAssignDate;
        }

        public String getDataCardDocURL() {
            return DataCardDocURL;
        }

        public void setDataCardDocURL(String DataCardDocURL) {
            this.DataCardDocURL = DataCardDocURL;
        }

        public Object getDataCardReturnDate() {
            return DataCardReturnDate;
        }

        public void setDataCardReturnDate(Object DataCardReturnDate) {
            this.DataCardReturnDate = DataCardReturnDate;
        }

        public String getDataCardRemarks() {
            return DataCardRemarks;
        }

        public void setDataCardRemarks(String DataCardRemarks) {
            this.DataCardRemarks = DataCardRemarks;
        }

        public Object getMobileAssignDate() {
            return MobileAssignDate;
        }

        public void setMobileAssignDate(Object MobileAssignDate) {
            this.MobileAssignDate = MobileAssignDate;
        }

        public String getMobileDocURL() {
            return MobileDocURL;
        }

        public void setMobileDocURL(String MobileDocURL) {
            this.MobileDocURL = MobileDocURL;
        }

        public Object getMobileReturnDate() {
            return MobileReturnDate;
        }

        public void setMobileReturnDate(Object MobileReturnDate) {
            this.MobileReturnDate = MobileReturnDate;
        }

        public String getMobileRemarks() {
            return MobileRemarks;
        }

        public void setMobileRemarks(String MobileRemarks) {
            this.MobileRemarks = MobileRemarks;
        }

        public Object getIpadAssignDate() {
            return IpadAssignDate;
        }

        public void setIpadAssignDate(Object IpadAssignDate) {
            this.IpadAssignDate = IpadAssignDate;
        }

        public String getIpadDocURL() {
            return IpadDocURL;
        }

        public void setIpadDocURL(String IpadDocURL) {
            this.IpadDocURL = IpadDocURL;
        }

        public Object getIpadReturnDate() {
            return IpadReturnDate;
        }

        public void setIpadReturnDate(Object IpadReturnDate) {
            this.IpadReturnDate = IpadReturnDate;
        }

        public String getIpadRemarks() {
            return IpadRemarks;
        }

        public void setIpadRemarks(String IpadRemarks) {
            this.IpadRemarks = IpadRemarks;
        }

        public Object getLaptopAssignDate() {
            return LaptopAssignDate;
        }

        public void setLaptopAssignDate(Object LaptopAssignDate) {
            this.LaptopAssignDate = LaptopAssignDate;
        }

        public String getLaptopDocURL() {
            return LaptopDocURL;
        }

        public void setLaptopDocURL(String LaptopDocURL) {
            this.LaptopDocURL = LaptopDocURL;
        }

        public Object getLaptopReturnDate() {
            return LaptopReturnDate;
        }

        public void setLaptopReturnDate(Object LaptopReturnDate) {
            this.LaptopReturnDate = LaptopReturnDate;
        }

        public String getLaptopRemarks() {
            return LaptopRemarks;
        }

        public void setLaptopRemarks(String LaptopRemarks) {
            this.LaptopRemarks = LaptopRemarks;
        }

        public int getReprotingLocationFk() {
            return ReprotingLocationFk;
        }

        public void setReprotingLocationFk(int ReprotingLocationFk) {
            this.ReprotingLocationFk = ReprotingLocationFk;
        }

        public int getCostCenterPK() {
            return CostCenterPK;
        }

        public void setCostCenterPK(int CostCenterPK) {
            this.CostCenterPK = CostCenterPK;
        }

        public String getOtherEmployeeCode() {
            return OtherEmployeeCode;
        }

        public void setOtherEmployeeCode(String OtherEmployeeCode) {
            this.OtherEmployeeCode = OtherEmployeeCode;
        }

        public String getMaritalStatus() {
            return MaritalStatus;
        }

        public void setMaritalStatus(String MaritalStatus) {
            this.MaritalStatus = MaritalStatus;
        }

        public String getCurrentState() {
            return CurrentState;
        }

        public void setCurrentState(String CurrentState) {
            this.CurrentState = CurrentState;
        }

        public String getPermenantState() {
            return PermenantState;
        }

        public void setPermenantState(String PermenantState) {
            this.PermenantState = PermenantState;
        }

        public String getCurrentCity() {
            return CurrentCity;
        }

        public void setCurrentCity(String CurrentCity) {
            this.CurrentCity = CurrentCity;
        }

        public String getPermenantCity() {
            return PermenantCity;
        }

        public void setPermenantCity(String PermenantCity) {
            this.PermenantCity = PermenantCity;
        }

        public String getHeight() {
            return Height;
        }

        public void setHeight(String Height) {
            this.Height = Height;
        }

        public String getWeight() {
            return Weight;
        }

        public void setWeight(String Weight) {
            this.Weight = Weight;
        }

        public String getBloodGroup() {
            return BloodGroup;
        }

        public void setBloodGroup(String BloodGroup) {
            this.BloodGroup = BloodGroup;
        }

        public String getReligion() {
            return Religion;
        }

        public void setReligion(String Religion) {
            this.Religion = Religion;
        }

        public String getNationality() {
            return Nationality;
        }

        public void setNationality(String Nationality) {
            this.Nationality = Nationality;
        }
    }
}
