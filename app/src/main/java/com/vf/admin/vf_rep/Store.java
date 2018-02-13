package com.vf.admin.vf_rep;

import com.google.api.client.util.DateTime;

/**
 * Created by daniel on 28/03/2017.
 */

public class Store {

    public int ID;
    public int SeqNo;
    public String URN;
    public String StoreName;
    public String ContactPerson;
    public String ContactEmail;
    public String ContactPhone;
    public String AddressLine1;
    public String AddressLine2;
    public String TownCity;
    public int RegionID;
    public DateTime OpeningTime;
    public DateTime ClosingTime;
    public boolean Agreed;
    public DateTime AgreedDate;
    public int BrandID;
    public int OutletTypeID;
    public int TierTypeID;
    public double Investment;
    public boolean Validated;
    public DateTime ValidateDate;
    public int ValidatedByUserID;
    public double GpsLat;
    public double GpsLng;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getSeqNo() {
        return SeqNo;
    }

    public void setSeqNo(int SeqNo) {
        this.SeqNo = SeqNo;
    }
    public String getURN() {
        return URN;
    }

    public void setURN(String URN) {
        this.URN = URN;
    }
    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }
    public String getContactPerson() {
        return ContactPerson;
    }

    public void setContactPerson(String ContactPerson) {
        this.ContactPerson = ContactPerson;
    }
    public String getContactEmail() {
        return ContactEmail;
    }

    public void setContactEmail(String ContactEmail) {
        this.ContactEmail = ContactEmail;
    }
    public String getContactPhone() {
        return ContactPhone;
    }

    public void setContactPhone(String ContactPhone) {
        this.ContactPhone = ContactPhone;
    }
    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String AddressLine1) {
        this.AddressLine1 = AddressLine1;
    }
    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String AddressLine2) {
        this.AddressLine2 = AddressLine2;
    }
    public String getTownCity() {
        return TownCity;
    }

    public void setTownCity(String TownCity) {
        this.TownCity = TownCity;
    }
    public int getRegionID() {
        return RegionID;
    }

    public void setRegionID(int RegionID) {
        this.RegionID = RegionID;
    }
    public DateTime getOpeningTime() {
        return OpeningTime;
    }

    public void setOpeningTime(DateTime OpeningTime) {
        this.OpeningTime = OpeningTime;
    }
    public DateTime getClosingTime() {
        return ClosingTime;
    }

    public void setClosingTime(DateTime ClosingTime) {
        this.ClosingTime = ClosingTime;
    }
    public boolean getAgreed() {
        return Agreed;
    }

    public void setAgreed(boolean Agreed) {
        this.Agreed = Agreed;
    }
    public DateTime getAgreedDate() {
        return AgreedDate;
    }

    public void setAgreedDate(DateTime AgreedDate) {
        this.AgreedDate = AgreedDate;
    }
    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }
    public int getOutletTypeID() {
        return OutletTypeID;
    }

    public void setOutletTypeID(int OutletTypeID) {
        this.OutletTypeID = OutletTypeID;
    }
    public int getTierTypeID() {
        return TierTypeID;
    }

    public void setTierTypeID(int TierTypeID) {
        this.TierTypeID = TierTypeID;
    }
    public double getInvestment() {
        return Investment;
    }

    public void setInvestment(double Investment) {
        this.Investment = Investment;
    }
    public boolean getValidated() {
        return Validated;
    }

    public void setValidated(boolean Validated) {
        this.Validated = Validated;
    }
    public DateTime getValidateDate() {
        return ValidateDate;
    }

    public void setValidateDate(DateTime ValidateDate) {
        this.ValidateDate = ValidateDate;
    }
    public int getValidatedByUserID() {
        return ValidatedByUserID;
    }

    public void setValidatedByUserID(int ValidatedByUserID) {
        this.ValidatedByUserID = ValidatedByUserID;
    }
    public double getGpsLat() {
        return GpsLat;
    }

    public void setGpsLat(double GpsLat) {
        this.GpsLat = GpsLat;
    }
    public double getGpsLng() {
        return GpsLng;
    }

    public void setGpsLng(double GpsLng) {
        this.GpsLng = GpsLng;
    }


}
