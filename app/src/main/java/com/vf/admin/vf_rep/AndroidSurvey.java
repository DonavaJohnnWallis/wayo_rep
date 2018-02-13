package com.vf.admin.vf_rep;

import com.google.api.client.util.DateTime;

/**
 * Created by daniel on 03/07/2017.
 */


public class AndroidSurvey
{
    public int ID;
    public int RequestID;
    public String SurveyorUserName;
    public String SurveyDate;
    public String TalkingToPerson;
    public String TalkingToRole;
    public boolean IsCommittedToInstallation;
    public int BrandID;
    public int OpeningTime;
    public int ClosingTime;
    public int PreferredDeliveryTime;
    public String Comments;
    public int SignatureDocumentID;
    public String WhyNotCommitted;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public int getRequestID() {
        return RequestID;
    }

    public void setRequestID(int RequestID) {
        this.RequestID = RequestID;
    }
    public String getSurveyorUserName() {
        return SurveyorUserName;
    }

    public void setSurveyorUserName(String SurveyorUserName) {
        this.SurveyorUserName = SurveyorUserName;
    }
    public String getSurveyDate() {
        return SurveyDate;
    }

    public void setSurveyDate(String SurveyDate) {
        this.SurveyDate = SurveyDate;
    }
    public String getTalkingToPerson() {
        return TalkingToPerson;
    }

    public void setTalkingToPerson(String TalkingToPerson) {
        this.TalkingToPerson = TalkingToPerson;
    }
    public String getTalkingToRole() {
        return TalkingToRole;
    }

    public void setTalkingToRole(String TalkingToRole) {
        this.TalkingToRole = TalkingToRole;
    }
    public boolean getIsCommittedToInstallation() {
        return IsCommittedToInstallation;
    }

    public void setIsCommittedToInstallation(boolean IsCommittedToInstallation) {
        this.IsCommittedToInstallation = IsCommittedToInstallation;
    }
    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int BrandID) {
        this.BrandID = BrandID;
    }
    public int getOpeningTime() {
        return OpeningTime;
    }

    public void setOpeningTime(int OpeningTime) {
        this.OpeningTime = OpeningTime;
    }
    public int getClosingTime() {
        return ClosingTime;
    }

    public void setClosingTime(int ClosingTime) {
        this.ClosingTime = ClosingTime;
    }
    public int getPreferredDeliveryTime() {
        return PreferredDeliveryTime;
    }

    public void setPreferredDeliveryTime(int PreferredDeliveryTime) {
        this.PreferredDeliveryTime = PreferredDeliveryTime;
    }
    public String getComments() {
        return Comments;
    }

    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    public String getWhyNotCommitted() {
        return WhyNotCommitted;
    }

    public void setWhyNotCommitted(String WhyNotCommitted) {
        this.WhyNotCommitted = WhyNotCommitted;
    }




    public int getSignatureDocumentID() {
        return SignatureDocumentID;
    }

    public void setSignatureDocumentID(int SignatureDocumentID) {
        this.SignatureDocumentID = SignatureDocumentID;
    }

}