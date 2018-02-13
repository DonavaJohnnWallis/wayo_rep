package com.vf.admin.vf_rep;

import com.google.api.client.util.DateTime;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Created by daniel on 28/03/2017.
 */

public class JsonUtil {



    public static String flattenURN(String URN)
    {
        String result = "";

        result = URN.replace("-","");
        result = result.toLowerCase();

        return result;
    }

    public static ArrayList<AndroidDocumentType> parseJsonArrayAndroidDocumentTypes(String jsonStr) throws JSONException {
        ArrayList<AndroidDocumentType> documentTypes = new ArrayList<AndroidDocumentType>();

        try {

            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidDocumentType androidDocumentType = new AndroidDocumentType();


                androidDocumentType.setStoreID(parseInt(mJsonObjectProperty.getString("StoreID")));
                androidDocumentType.setCount(parseInt(mJsonObjectProperty.getString("Count")));
                androidDocumentType.setDocumentType(mJsonObjectProperty.getString("DocumentType"));

                documentTypes.add(androidDocumentType);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return documentTypes;
    }


    public static ArrayList<AndroidStore> parseJsonArrayAndroidStore(String jsonStr) throws JSONException {
        ArrayList<AndroidStore> stores = new ArrayList<AndroidStore>();

        try {

            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidStore androidstore2 = new AndroidStore();


                androidstore2.setID(parseInt(mJsonObjectProperty.getString("ID")));
                androidstore2.setStoreName(mJsonObjectProperty.getString("StoreName"));


                //androidstore2.setUserName(mJsonObjectProperty.getString("UserName"));

                androidstore2.setExpectedDistinctItemImageCount(parseInt(mJsonObjectProperty.getString("ExpectedDistinctItemImageCount")));
                //androidstore2.setActualDistinctItemImageCount(parseInt(mJsonObjectProperty.getString("ActualDistinctItemImageCount")));



                androidstore2.setBrandName(mJsonObjectProperty.getString("BrandName"));
                androidstore2.setTierTypeName(mJsonObjectProperty.getString("TierTypeName"));
                androidstore2.setOutletTypeName(mJsonObjectProperty.getString("OutletTypeName"));
                androidstore2.setContactPerson(mJsonObjectProperty.getString("ContactPerson"));
                androidstore2.setContactEmail(mJsonObjectProperty.getString("ContactEmail"));
                androidstore2.setContactPhone(mJsonObjectProperty.getString("ContactPhone"));
                androidstore2.setOpeningTime(mJsonObjectProperty.getString("OpeningTime"));
                androidstore2.setClosingTime(mJsonObjectProperty.getString("ClosingTime"));

                androidstore2.setAddressLine1(mJsonObjectProperty.getString("AddressLine1"));
                androidstore2.setAddressLine2(mJsonObjectProperty.getString("AddressLine2"));
                androidstore2.setTownCity(mJsonObjectProperty.getString("TownCity"));



                //dont set online fields
                if(!mJsonObjectProperty.isNull("URN")){
                    androidstore2.setRegionName(mJsonObjectProperty.getString("RegionName"));
                    androidstore2.setURN(mJsonObjectProperty.getString("URN"));
                    androidstore2.setCurrentPhase(mJsonObjectProperty.getString("CurrentPhase"));
                    androidstore2.setTotalUnitCount(parseInt(mJsonObjectProperty.getString("TotalUnitCount")));
                    androidstore2.setRepFirstNameSurname(mJsonObjectProperty.getString("RepFirstNameSurname"));
                    androidstore2.setRepJobTitle(mJsonObjectProperty.getString("RepJobTitle"));
                    androidstore2.setRepCellNo(mJsonObjectProperty.getString("RepCellNo"));
                    androidstore2.setTssFirstNameSurname(mJsonObjectProperty.getString("TssFirstNameSurname"));
                    androidstore2.setTssJobTitle(mJsonObjectProperty.getString("TssJobTitle"));
                    androidstore2.setTssCellNo(mJsonObjectProperty.getString("TssCellNo"));
                    androidstore2.setInsFirstNameSurname(mJsonObjectProperty.getString("InsFirstNameSurname"));
                    androidstore2.setInsJobTitle(mJsonObjectProperty.getString("InsJobTitle"));
                    androidstore2.setInsCellNo(mJsonObjectProperty.getString("InsCellNo"));
                    androidstore2.setStoreNameURN(mJsonObjectProperty.getString("StoreName") + " " + mJsonObjectProperty.getString("URN"));

                    if(!mJsonObjectProperty.isNull("GpsLat")) {
                        androidstore2.setGpsLat(parseDouble(mJsonObjectProperty.getString("GpsLat")));
                    }
                    else {
                        androidstore2.setGpsLat(0.00);
                    }
                    if(!mJsonObjectProperty.isNull("GpsLng")) {
                        androidstore2.setGpsLng(parseDouble(mJsonObjectProperty.getString("GpsLng")));
                    }
                    else {
                        androidstore2.setGpsLng(0.00);
                    }

                }

                stores.add(androidstore2);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return stores;
    }

    public static ArrayList<AndroidOpenRequest> parseJsonArrayAndroidOpenRequest(String jsonStr) throws JSONException {
        ArrayList<AndroidOpenRequest> openRequests = new ArrayList<AndroidOpenRequest>();

        try {

            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidOpenRequest androidopenrequest = new AndroidOpenRequest();
                androidopenrequest.setID(parseInt(mJsonObjectProperty.getString("ID")));
                androidopenrequest.setRequestTypeName(mJsonObjectProperty.getString("RequestTypeName"));
                androidopenrequest.setStoreName(mJsonObjectProperty.getString("StoreName"));
                androidopenrequest.setURN(mJsonObjectProperty.getString("URN"));
                androidopenrequest.setCurrentPhase(mJsonObjectProperty.getString("CurrentPhase"));
                androidopenrequest.setContactPerson(mJsonObjectProperty.getString("ContactPerson"));
                androidopenrequest.setContactEmail(mJsonObjectProperty.getString("ContactEmail"));
                androidopenrequest.setContactPhone(mJsonObjectProperty.getString("ContactPhone"));
                androidopenrequest.setOpeningTime(mJsonObjectProperty.getString("OpeningTime"));
                androidopenrequest.setClosingTime(mJsonObjectProperty.getString("ClosingTime"));
                androidopenrequest.setTotalUnitCount(parseInt(mJsonObjectProperty.getString("TotalUnitCount")));
                androidopenrequest.setStoreID(parseInt(mJsonObjectProperty.getString("StoreID")));
                androidopenrequest.setDateRequested(mJsonObjectProperty.getString("DateRequested"));
                androidopenrequest.setDateAccepted(mJsonObjectProperty.getString("DateAccepted"));
                androidopenrequest.setDateRecordChanged(mJsonObjectProperty.getString("DateRecordChanged"));
                openRequests.add(androidopenrequest);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return openRequests;
    }

    public static ArrayList<AndroidInstallStatus> parseJsonArrayAndroidInstallStatus(String jsonStr) throws JSONException {
        ArrayList<AndroidInstallStatus> installStatuses = new ArrayList<AndroidInstallStatus>();

        try {

            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidInstallStatus installStatus = new AndroidInstallStatus();

                installStatus.setID(parseInt(mJsonObjectProperty.getString("ID")));

                installStatus.setStatusName(mJsonObjectProperty.getString("StatusName"));
                installStatuses.add(installStatus);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return installStatuses;
    }

    public static ArrayList<AndroidAppointment> parseJsonArrayAndroidAppointment(String jsonStr) throws JSONException {
        ArrayList<AndroidAppointment> openRequests = new ArrayList<AndroidAppointment>();

        try {

            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidAppointment androidappointment = new AndroidAppointment();

                androidappointment.setID(parseInt(mJsonObjectProperty.getString("ID")));
                androidappointment.setMileage(parseInt(mJsonObjectProperty.getString("Mileage")));
                androidappointment.setRequestTypeName(mJsonObjectProperty.getString("RequestTypeName"));
                androidappointment.setStoreName(mJsonObjectProperty.getString("StoreName"));
                
                androidappointment.setBrandName(mJsonObjectProperty.getString("BrandName"));
                androidappointment.setOutletTypeName(mJsonObjectProperty.getString("OutletTypeName"));
                androidappointment.setTierTypeName(mJsonObjectProperty.getString("TierTypeName"));

                androidappointment.setStoreNameURN(mJsonObjectProperty.getString("StoreName") + " " + mJsonObjectProperty.getString("URN"));

                androidappointment.setURN(mJsonObjectProperty.getString("URN"));
                androidappointment.setCurrentPhase(mJsonObjectProperty.getString("CurrentPhase"));
                androidappointment.setContactPerson(mJsonObjectProperty.getString("ContactPerson"));
                androidappointment.setContactEmail(mJsonObjectProperty.getString("ContactEmail"));
                androidappointment.setContactPhone(mJsonObjectProperty.getString("ContactPhone"));
                androidappointment.setOpeningTime(mJsonObjectProperty.getString("OpeningTime"));
                androidappointment.setClosingTime(mJsonObjectProperty.getString("ClosingTime"));
                androidappointment.setTotalUnitCount(parseInt(mJsonObjectProperty.getString("TotalUnitCount")));
                androidappointment.setStoreID(parseInt(mJsonObjectProperty.getString("StoreID")));
                androidappointment.setDateRequested(mJsonObjectProperty.getString("DateRequested"));
                androidappointment.setDateAccepted(mJsonObjectProperty.getString("DateAccepted"));
                androidappointment.setAppointmentDateTime(mJsonObjectProperty.getString("AppointmentDateTime"));
                androidappointment.setAppointmentDateDay(parseInt(mJsonObjectProperty.getString("AppointmentDateDay")));
                androidappointment.setAppointmentDateMonth(parseInt(mJsonObjectProperty.getString("AppointmentDateMonth")));
                androidappointment.setAppointmentDateYear(parseInt(mJsonObjectProperty.getString("AppointmentDateYear")));
                androidappointment.setAppointmentDateTimeTime(mJsonObjectProperty.getString("AppointmentDateTimeTime"));
                androidappointment.setDateConfirmed(mJsonObjectProperty.getString("DateConfirmed"));
                androidappointment.setDateRecordChanged(mJsonObjectProperty.getString("DateRecordChanged"));
                androidappointment.setAddress(mJsonObjectProperty.getString("Address"));
                androidappointment.setRegion(mJsonObjectProperty.getString("Region"));


                openRequests.add(androidappointment);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return openRequests;
    }

    public static ArrayList<AndroidStoreUnit> parseJsonArrayAndroidStoreUnit(String jsonStr, String storeNameURN) throws JSONException {
        ArrayList<AndroidStoreUnit> androidstoreunits = new ArrayList<AndroidStoreUnit>();

        try {
            int y=0;
            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidStoreUnit androidstoreunit = new AndroidStoreUnit();



                androidstoreunit.setStoreName(mJsonObjectProperty.getString("StoreName"));
                androidstoreunit.setStoreNameURN(mJsonObjectProperty.getString("StoreName") + " " + mJsonObjectProperty.getString("URN"));

                androidstoreunit.setImagePath(mJsonObjectProperty.getString("ImagePath"));
                androidstoreunit.setItemTypeName(mJsonObjectProperty.getString("ItemTypeName"));
                androidstoreunit.setMaxQuantityFromMatrix(parseInt(mJsonObjectProperty.getString("MaxQuantityFromMatrix")));

                if(androidstoreunit.getStoreNameURN().equals(storeNameURN)) {
                    androidstoreunits.add(androidstoreunit);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return androidstoreunits;
    }

    public static ArrayList<AndroidStoreUnitExplicit> parseJsonArrayAndroidStoreUnitExplicitAll(String jsonStr) throws JSONException {
        ArrayList<AndroidStoreUnitExplicit> storeUnitExplicits = new ArrayList<AndroidStoreUnitExplicit>();

        try {
            int y=0;
            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidStoreUnitExplicit androidstoreunit = new AndroidStoreUnitExplicit();

                androidstoreunit.setIsSurvey(parseBoolean(mJsonObjectProperty.getString("IsSurvey")));
                androidstoreunit.setAcceptedByStore(parseInt(mJsonObjectProperty.getString("AcceptedByStore")));
                androidstoreunit.setWhyNo(mJsonObjectProperty.getString("WhyNo"));

                androidstoreunit.setEnumerate(parseInt(mJsonObjectProperty.getString("Enumerate")));
                androidstoreunit.setStoreID(parseInt(mJsonObjectProperty.getString("StoreID")));
                androidstoreunit.setStoreItemID(parseInt(mJsonObjectProperty.getString("StoreItemID")));
                androidstoreunit.setStoreName(mJsonObjectProperty.getString("StoreName"));
                androidstoreunit.setStoreNameURN(mJsonObjectProperty.getString("StoreName") + " " + mJsonObjectProperty.getString("URN"));

                androidstoreunit.setBarcode(mJsonObjectProperty.getString("Barcode"));
                androidstoreunit.setURN(mJsonObjectProperty.getString("URN"));
                androidstoreunit.setImageCount(parseInt(mJsonObjectProperty.getString("ImageCount")));

                androidstoreunit.setImagePath(mJsonObjectProperty.getString("ImagePath"));
                androidstoreunit.setItemTypeName(mJsonObjectProperty.getString("ItemTypeName"));
                androidstoreunit.setMaxQuantityFromMatrix(parseInt(mJsonObjectProperty.getString("MaxQuantityFromMatrix")));


                storeUnitExplicits.add(androidstoreunit);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storeUnitExplicits;
    }

    public static ArrayList<AndroidSurvey> parseJsonArrayAndroidSurvey(String jsonStr) throws JSONException {
        ArrayList<AndroidSurvey> androidstoreunits = new ArrayList<AndroidSurvey>();

        try {
            int y=0;
            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidSurvey androidsurvey = new AndroidSurvey();

                androidsurvey.setID(parseInt(mJsonObjectProperty.getString("ID")));
                androidsurvey.setRequestID(parseInt(mJsonObjectProperty.getString("RequestID")));
                androidsurvey.setSurveyorUserName(mJsonObjectProperty.getString("SurveyorUserName"));
               // androidsurvey.setSurveyDate(mJsonObjectProperty.getString("SurveyDate"));
                androidsurvey.setTalkingToPerson(mJsonObjectProperty.getString("TalkingToPerson"));
                androidsurvey.setTalkingToRole(mJsonObjectProperty.getString("TalkingToRole"));
                androidsurvey.setIsCommittedToInstallation(parseBoolean(mJsonObjectProperty.getString("IsCommittedToInstallation")));
                androidsurvey.setBrandID(parseInt((mJsonObjectProperty.getString("BrandID"))));
                androidsurvey.setOpeningTime(parseInt(mJsonObjectProperty.getString("OpeningTime")));
                androidsurvey.setClosingTime(parseInt(mJsonObjectProperty.getString("ClosingTime")));
                androidsurvey.setPreferredDeliveryTime(parseInt(mJsonObjectProperty.getString("PreferredDeliveryTime")));
                androidsurvey.setComments(mJsonObjectProperty.getString("Comments"));
                androidsurvey.setWhyNotCommitted(mJsonObjectProperty.getString("WhyNotCommitted"));
                androidsurvey.setSignatureDocumentID(parseInt((mJsonObjectProperty.getString("SignatureDocumentID"))));


                androidstoreunits.add(androidsurvey);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return androidstoreunits;
    }

    public static ArrayList<AndroidStoreUnitExplicit> parseJsonArrayAndroidStoreUnitExplicit(String jsonStr, String storeNameURN) throws JSONException {
        ArrayList<AndroidStoreUnitExplicit> storeUnitExplicits = new ArrayList<AndroidStoreUnitExplicit>();

        try {
            int y=0;
            JSONArray mJsonArrayProperty = new JSONArray(jsonStr);
            for (int i = 0; i < mJsonArrayProperty.length(); i++) {
                JSONObject mJsonObjectProperty = mJsonArrayProperty.getJSONObject(i);

                AndroidStoreUnitExplicit androidstoreunit = new AndroidStoreUnitExplicit();

                androidstoreunit.setIsSurvey(parseBoolean(mJsonObjectProperty.getString("IsSurvey")));
                androidstoreunit.setAcceptedByStore(parseInt(mJsonObjectProperty.getString("AcceptedByStore")));

                if(mJsonObjectProperty.getString("WhyNo").equals("null")) {
                    androidstoreunit.setWhyNo("");
                }
                else {
                    androidstoreunit.setWhyNo(mJsonObjectProperty.getString("WhyNo"));
                }
                
                androidstoreunit.setEnumerate(parseInt(mJsonObjectProperty.getString("Enumerate")));
                androidstoreunit.setStoreID(parseInt(mJsonObjectProperty.getString("StoreID")));
                androidstoreunit.setStoreItemID(parseInt(mJsonObjectProperty.getString("StoreItemID")));
                androidstoreunit.setStoreName(mJsonObjectProperty.getString("StoreName"));
                androidstoreunit.setStoreNameURN(mJsonObjectProperty.getString("StoreName") + " " + mJsonObjectProperty.getString("URN"));

                if(mJsonObjectProperty.getString("Barcode").equals("null")) {
                    androidstoreunit.setBarcode("");
                }
                else {
                    androidstoreunit.setBarcode(mJsonObjectProperty.getString("Barcode"));
                }
                androidstoreunit.setURN(mJsonObjectProperty.getString("URN"));
                androidstoreunit.setImageCount(parseInt(mJsonObjectProperty.getString("ImageCount")));

                androidstoreunit.setImagePath(mJsonObjectProperty.getString("ImagePath"));
                androidstoreunit.setItemTypeName(mJsonObjectProperty.getString("ItemTypeName"));
                androidstoreunit.setMaxQuantityFromMatrix(parseInt(mJsonObjectProperty.getString("MaxQuantityFromMatrix")));

                if(androidstoreunit.getStoreNameURN().equals(storeNameURN)) {
                    storeUnitExplicits.add(androidstoreunit);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return storeUnitExplicits;
    }


    //TO
    public static String toJSonStore(Store store) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("ID", store.getID());
            jsonObj.put("SeqNo", store.getSeqNo());
            jsonObj.put("URN", store.getURN());
            jsonObj.put("StoreName", store.getStoreName());
            jsonObj.put("ContactPerson", store.getContactPerson());
            jsonObj.put("ContactEmail", store.getContactEmail());
            jsonObj.put("ContactPhone", store.getContactPhone());
            jsonObj.put("AddressLine1", store.getAddressLine1());
            jsonObj.put("AddressLine2", store.getAddressLine2());
            jsonObj.put("TownCity", store.getTownCity());
            jsonObj.put("RegionID", store.getRegionID());
            jsonObj.put("OpeningTime", store.getOpeningTime());
            jsonObj.put("ClosingTime", store.getClosingTime());
            jsonObj.put("Agreed", store.getAgreed());
            jsonObj.put("AgreedDate", store.getAgreedDate());
            jsonObj.put("BrandID", store.getBrandID());
            jsonObj.put("OutletTypeID", store.getOutletTypeID());
            jsonObj.put("TierTypeID", store.getTierTypeID());
            jsonObj.put("Investment", store.getInvestment());
            jsonObj.put("Validated", store.getValidated());
            jsonObj.put("ValidateDate", store.getValidateDate());
            jsonObj.put("ValidatedByUserID", store.getValidatedByUserID());
            jsonObj.put("GpsLat", store.getGpsLat());
            jsonObj.put("GpsLng", store.getGpsLng());


            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static String toJSonAndroidStore(AndroidStore androidstore2) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("ID", androidstore2.getID());
            jsonObj.put("StoreName", androidstore2.getStoreName());
            jsonObj.put("UserName", androidstore2.getUserName());
            jsonObj.put("URN", androidstore2.getURN());
            jsonObj.put("CurrentPhase", androidstore2.getCurrentPhase());
            jsonObj.put("RegionName", androidstore2.getRegionName());
            jsonObj.put("BrandName", androidstore2.getBrandName());
            jsonObj.put("TierTypeName", androidstore2.getTierTypeName());
            jsonObj.put("OutletTypeName", androidstore2.getOutletTypeName());
            jsonObj.put("ContactPerson", androidstore2.getContactPerson());
            jsonObj.put("ContactEmail", androidstore2.getContactEmail());
            jsonObj.put("ContactPhone", androidstore2.getContactPhone());
            jsonObj.put("OpeningTime", androidstore2.getOpeningTime());
            jsonObj.put("ClosingTime", androidstore2.getClosingTime());
            jsonObj.put("TotalUnitCount", androidstore2.getTotalUnitCount());
            jsonObj.put("AddressLine1", androidstore2.getAddressLine1());
            jsonObj.put("AddressLine2", androidstore2.getAddressLine2());
            jsonObj.put("TownCity", androidstore2.getTownCity());
            jsonObj.put("RepFirstNameSurname", androidstore2.getRepFirstNameSurname());
            jsonObj.put("RepJobTitle", androidstore2.getRepJobTitle());
            jsonObj.put("RepCellNo", androidstore2.getRepCellNo());
            jsonObj.put("TssFirstNameSurname", androidstore2.getTssFirstNameSurname());
            jsonObj.put("TssJobTitle", androidstore2.getTssJobTitle());
            jsonObj.put("TssCellNo", androidstore2.getTssCellNo());
            jsonObj.put("InsFirstNameSurname", androidstore2.getInsFirstNameSurname());
            jsonObj.put("InsJobTitle", androidstore2.getInsJobTitle());
            jsonObj.put("InsCellNo", androidstore2.getInsCellNo());
            jsonObj.put("DateRecordChanged", androidstore2.getDateRecordChanged());
            jsonObj.put("GpsLat", androidstore2.getGpsLat());
            jsonObj.put("GpsLng", androidstore2.getGpsLng());
            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static String toJSonAndroidSurvey(AndroidSurvey androidsurvey) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("ID", androidsurvey.getID());
            jsonObj.put("RequestID", androidsurvey.getRequestID());
            jsonObj.put("SurveyorUserName", androidsurvey.getSurveyorUserName());
            jsonObj.put("SurveyDate", androidsurvey.getSurveyDate());
            jsonObj.put("TalkingToPerson", androidsurvey.getTalkingToPerson());
            jsonObj.put("TalkingToRole", androidsurvey.getTalkingToRole());
            jsonObj.put("IsCommittedToInstallation", androidsurvey.getIsCommittedToInstallation());
            jsonObj.put("BrandID", androidsurvey.getBrandID());
            jsonObj.put("OpeningTime", androidsurvey.getOpeningTime());
            jsonObj.put("ClosingTime", androidsurvey.getClosingTime());
            jsonObj.put("PreferredDeliveryTime", androidsurvey.getPreferredDeliveryTime());
            jsonObj.put("Comments", androidsurvey.getComments());
            jsonObj.put("WhyNotCommitted", androidsurvey.getWhyNotCommitted());

            jsonObj.put("SignatureDocumentID", androidsurvey.getSignatureDocumentID());
            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static String toJSonAndroidOpenRequest(AndroidOpenRequest androidopenrequest) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("ID", androidopenrequest.getID());
            jsonObj.put("RequestTypeName", androidopenrequest.getRequestTypeName());
            jsonObj.put("StoreName", androidopenrequest.getStoreName());
            jsonObj.put("URN", androidopenrequest.getURN());
            jsonObj.put("CurrentPhase", androidopenrequest.getCurrentPhase());
            jsonObj.put("ContactPerson", androidopenrequest.getContactPerson());
            jsonObj.put("ContactEmail", androidopenrequest.getContactEmail());
            jsonObj.put("ContactPhone", androidopenrequest.getContactPhone());
            jsonObj.put("OpeningTime", androidopenrequest.getOpeningTime());
            jsonObj.put("ClosingTime", androidopenrequest.getClosingTime());
            jsonObj.put("TotalUnitCount", androidopenrequest.getTotalUnitCount());
            jsonObj.put("StoreID", androidopenrequest.getStoreID());
            jsonObj.put("DateRequested", androidopenrequest.getDateRequested());
            jsonObj.put("DateAccepted", androidopenrequest.getDateAccepted());
            jsonObj.put("DateRecordChanged", androidopenrequest.getDateRecordChanged());
            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public static String toJSonAndroidAppointment(AndroidAppointment androidappointment) {
        try {
            // Here we convert Java Object to JSON
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("ID", androidappointment.getID());
            jsonObj.put("RequestTypeName", androidappointment.getRequestTypeName());
            jsonObj.put("StoreName", androidappointment.getStoreName());
            jsonObj.put("URN", androidappointment.getURN());
            jsonObj.put("CurrentPhase", androidappointment.getCurrentPhase());
            jsonObj.put("ContactPerson", androidappointment.getContactPerson());
            jsonObj.put("ContactEmail", androidappointment.getContactEmail());
            jsonObj.put("ContactPhone", androidappointment.getContactPhone());
            jsonObj.put("OpeningTime", androidappointment.getOpeningTime());
            jsonObj.put("ClosingTime", androidappointment.getClosingTime());
            jsonObj.put("TotalUnitCount", androidappointment.getTotalUnitCount());
            jsonObj.put("StoreID", androidappointment.getStoreID());
            jsonObj.put("DateRequested", androidappointment.getDateRequested());
            jsonObj.put("DateAccepted", androidappointment.getDateAccepted());
            jsonObj.put("AppointmentDateTime", androidappointment.getAppointmentDateTime());
            jsonObj.put("AppointmentDateDay", androidappointment.getAppointmentDateDay());
            jsonObj.put("AppointmentDateMonth", androidappointment.getAppointmentDateMonth());
            jsonObj.put("AppointmentDateYear", androidappointment.getAppointmentDateYear());
            jsonObj.put("AppointmentDateTimeTime", androidappointment.getAppointmentDateTimeTime());
            jsonObj.put("DateConfirmed", androidappointment.getDateConfirmed());
            jsonObj.put("DateRecordChanged", androidappointment.getDateRecordChanged());
            return jsonObj.toString();

        } catch (JSONException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    //FROM
    public static Store fromJsonStore(String jsonStore) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson mGson = gsonb.create();
        Store c = mGson.fromJson(jsonStore, Store.class);

        return c;
    }

    public static AndroidStore fromJsonAndroidStore(String jsonAndroidStore) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson mGson = gsonb.create();
        AndroidStore c = mGson.fromJson(jsonAndroidStore, AndroidStore.class);

        return c;
    }

    public static AndroidOpenRequest fromJsonAndroidOpenRequest(String jsonAndroidOpenRequest) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson mGson = gsonb.create();
        AndroidOpenRequest c = mGson.fromJson(jsonAndroidOpenRequest, AndroidOpenRequest.class);

        return c;
    }

    public static AndroidSurvey fromJsonAndroidSurvey(String jsonAndroidSurvey) {
        GsonBuilder gsonb = new GsonBuilder();
        Gson mGson = gsonb.create();
        AndroidSurvey c = mGson.fromJson(jsonAndroidSurvey, AndroidSurvey.class);

        return c;
    }

    //APPEND
    //add locally saved items to lists for sending to webservice later
    public static String appendJsonAndroidStoreToArray(ArrayList<AndroidStore> mylist, AndroidStore item)
    {
        mylist.add(item);

        String json = new Gson().toJson(mylist);

        return json;
    }

    public static String appendJsonAndroidAppointmentToArray(ArrayList<AndroidAppointment> mylist, AndroidAppointment item)
    {
        mylist.add(item);

        String json = new Gson().toJson(mylist);

        return json;
    }

    public static String appendJsonAndroidSurveyToArray(ArrayList<AndroidSurvey> mylist, AndroidSurvey item)
    {
        mylist.add(item);

        String json = new Gson().toJson(mylist);

        return json;
    }


    //CONVERT CLASS TO JSON ARRAY
    public static String convertAppointmentArrayToJson(ArrayList<AndroidAppointment> mylist)
    {
        String json = new Gson().toJson(mylist);

        return json;

    }

    public static String convertAndroidStoreUnitExplicitArrayToJson(ArrayList<AndroidStoreUnitExplicit> mylist)
    {
        String json = new Gson().toJson(mylist);

        return json;

    }

    public static String convertAndroidDocumentTypeArrayToJson(ArrayList<AndroidDocumentType> mylist)
    {
        String json = new Gson().toJson(mylist);

        return json;

    }



    public static String convertOpenRequestArrayToJson(ArrayList<AndroidOpenRequest> mylist)
    {
        String json = new Gson().toJson(mylist);

        return json;

    }

    public static String convertStoreArrayToJson(ArrayList<AndroidStore> mylist)
    {
        String json = new Gson().toJson(mylist);

        return json;

    }

    public static String convertSurveyArrayToJson(ArrayList<AndroidSurvey> mylist)
    {
        String json = new Gson().toJson(mylist);

        return json;

    }


    public static String appendJsonAndroidOpenRequestToArray(ArrayList<AndroidOpenRequest> mylist, AndroidOpenRequest item)
    {
        mylist.add(item);

        String json = new Gson().toJson(mylist);

        return json;
    }


    public int getCategoryPos(String category, ArrayList<String> _categories) {
        return _categories.indexOf(category);
    }
}