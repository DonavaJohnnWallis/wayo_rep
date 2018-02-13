package com.vf.admin.vf_rep;

/**
 * Created by daniel on 11/04/2017.
 */

public class AndroidStoreUnitExplicit {

    public int StoreItemID;
    public int StoreID;
    public String Barcode;
    public String StoreName;
    public String URN;
    public String ImagePath;
    public String ItemTypeName;
    public int MaxQuantityFromMatrix;
    public int ImageCount;
    public int Enumerate;
    public String StoreNameURN;
    public boolean IsSurvey;
    public int AcceptedByStore;
    public String WhyNo;

    public String getWhyNo() {
        return WhyNo;
    }

    public void setWhyNo(String WhyNo) {
        this.WhyNo = WhyNo;
    }

    public boolean getIsSurvey() {
        return IsSurvey;
    }
    public void setIsSurvey(boolean IsSurvey) {
        this.IsSurvey = IsSurvey;
    }

    public int getAcceptedByStore() {
        return AcceptedByStore;
    }
    public void setAcceptedByStore(int AcceptedByStore) {
        this.AcceptedByStore = AcceptedByStore;
    }
    
    public String getStoreNameURN() {
        return StoreNameURN;
    }

    public void setStoreNameURN(String StoreNameURN) {
        this.StoreNameURN = StoreNameURN;
    }


    public int getEnumerate() {
        return Enumerate;
    }
    public void setEnumerate(int Enumerate) {
        this.Enumerate = Enumerate;
    }

    public String getBarcode() {
        return Barcode;
    }
    public void setBarcode(String Barcode) {
        this.Barcode = Barcode;
    }

    public int getStoreItemID() {
        return StoreItemID;
    }
    public void setStoreItemID(int StoreItemID) {
        this.StoreItemID = StoreItemID;
    }


    public int getStoreID() {
        return StoreID;
    }
    public void setStoreID(int StoreID) {
        this.StoreID = StoreID;
    }


    public int getImageCount() {
        return ImageCount;
    }
    public void setImageCount(int ImageCount) {
        this.ImageCount = ImageCount;
    }
    
    
    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String StoreName) {
        this.StoreName = StoreName;
    }

    public String getURN() {
        return URN;
    }
    public void setURN(String URN) {
        this.URN = URN;
    }
    public String getImagePath() {
        return ImagePath;
    }
    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }
    public String getItemTypeName() {
        return ItemTypeName;
    }

    public void setItemTypeName(String ItemTypeName) {
        this.ItemTypeName = ItemTypeName;
    }
    public int getMaxQuantityFromMatrix() {
        return MaxQuantityFromMatrix;
    }

    public void setMaxQuantityFromMatrix(int MaxQuantityFromMatrix) {
        this.MaxQuantityFromMatrix = MaxQuantityFromMatrix;
    }



}
