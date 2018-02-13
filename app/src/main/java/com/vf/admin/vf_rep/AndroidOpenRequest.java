package com.vf.admin.vf_rep;

import com.google.api.client.util.DateTime;

/**
 * Created by daniel on 31/03/2017.
 */

public class AndroidOpenRequest {

    public int ID;
    public String RequestTypeName;
    public String StoreName;
    public String UserName;
    public String URN;
    public String CurrentPhase;
    public String ContactPerson;
    public String ContactEmail;
    public String ContactPhone;
    public String OpeningTime;
    public String ClosingTime;
    public int TotalUnitCount;
    public int StoreID;
    public String DateRequested;
    public String DateAccepted;
    public String DateRecordChanged;

    public String StoreNameURN;

    public String getStoreNameURN() {
        return StoreNameURN;
    }

    public void setStoreNameURN(String StoreNameURN) {
        this.StoreNameURN = StoreNameURN;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String getRequestTypeName() {
        return RequestTypeName;
    }

    public void setRequestTypeName(String RequestTypeName) {
        this.RequestTypeName = RequestTypeName;
    }
    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    
    public String getURN() {
        return URN;
    }

    public void setURN(String URN) {
        this.URN = URN;
    }
    public String getCurrentPhase() {
        return CurrentPhase;
    }

    public void setCurrentPhase(String CurrentPhase) {
        this.CurrentPhase = CurrentPhase;
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
    public String getOpeningTime() {
        return OpeningTime;
    }

    public void setOpeningTime(String OpeningTime) {
        this.OpeningTime = OpeningTime;
    }
    public String getClosingTime() {
        return ClosingTime;
    }

    public void setClosingTime(String ClosingTime) {
        this.ClosingTime = ClosingTime;
    }
    public int getTotalUnitCount() {
        return TotalUnitCount;
    }

    public void setTotalUnitCount(int TotalUnitCount) {
        this.TotalUnitCount = TotalUnitCount;
    }
    public int getStoreID() {
        return StoreID;
    }

    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }
    public String getDateRequested() {
        return DateRequested;
    }

    public void setDateRequested(String DateRequested) {
        this.DateRequested = DateRequested;
    }
    public String getDateAccepted() {
        return DateAccepted;
    }

    public void setDateAccepted(String DateAccepted) {
        this.DateAccepted = DateAccepted;
    }
    public String getDateRecordChanged() {
        return DateRecordChanged;
    }

    public void setDateRecordChanged(String DateRecordChanged) {
        this.DateRecordChanged = DateRecordChanged;
    }
}
