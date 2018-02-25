package com.vf.admin.vf_rep;

/**
 * Created by Daniel on 2018/02/06.
 */


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MySOAPCallActivity
{
    public final String SOAP_ACTION = "http://tempuri.org/GetUserForTag";

    public final String SOAP_ACTIONAmIOnline = "http://tempuri.org/AmIOnline";
    public final String SOAP_ACTIONLoginSiteUser = "http://tempuri.org/LoginSiteUser";
    public final String SOAP_ACTIONGetStoresForUser = "http://tempuri.org/GetStoresForUser";

    public final String SOAP_ACTIONSaveMyStores = "http://tempuri.org/SaveMyStores";
    public final String SOAP_ACTIONGetOpenRequestsForUser = "http://tempuri.org/GetOpenRequestsForUser";
    public final String SOAP_ACTIONSaveMyOpenRequests = "http://tempuri.org/SaveMyOpenRequests";
    public final String SOAP_ACTIONGetAppointmentsForUser = "http://tempuri.org/GetAppointmentsForUser";
    public final String SOAP_ACTIONSaveMyAppointments = "http://tempuri.org/SaveMyAppointments";
    public final String SOAP_ACTIONGetUnitListsForUser = "http://tempuri.org/GetUnitListsForUser";
    public final String SOAP_ACTIONGetUnitListsForUser1 = "http://tempuri.org/GetUnitListsForUser1";
    public final String SOAP_ACTIONGetUnitListsForUserExplicit = "http://tempuri.org/GetUnitListsForUserExplicit";

    public final String SOAP_ACTIONUploadStoreItemImage = "http://tempuri.org/UploadStoreItemImage";
    public final String SOAP_ACTIONGpsCheckin = "http://tempuri.org/GpsCheckin";
    public final String SOAP_ACTIONIsInstallComplete = "http://tempuri.org/IsInstallComplete";
    public final String SOAP_ACTIONInstallStatusList = "http://tempuri.org/InstallStatusList";

    public final String SOAP_ACTIONSaveSurvey = "http://tempuri.org/SaveSurvey";
    public final String SOAP_ACTIONIsSurveyComplete = "http://tempuri.org/IsSurveyComplete";
    public final String SOAP_ACTIONGetSurvey = "http://tempuri.org/GetSurvey";
    public final String SOAP_ACTIONSaveAndroidUnitExplicits = "http://tempuri.org/SaveAndroidUnitExplicits";

    public final String SOAP_ACTIONGetDocumentCount = "http://tempuri.org/GetDocumentCount";

    public final String SOAP_ACTIONAndroidDocumentTypes = "http://tempuri.org/AndroidDocumentTypes";



    public  final String GetUserForTag = "GetUserForTag";

    public  final String AmIOnline = "AmIOnline";
    public  final String LoginSiteUser = "LoginSiteUser";
    public  final String GetStoresForUser = "GetStoresForUser";

    public  final String SaveMyStores = "SaveMyStores";
    public  final String GetOpenRequestsForUser = "GetOpenRequestsForUser";
    public  final String SaveMyOpenRequests = "SaveMyOpenRequests";
    public  final String GetAppointmentsForUser = "GetAppointmentsForUser";
    public  final String SaveMyAppointments = "SaveMyAppointments";
    public  final String GetUnitListsForUser = "GetUnitListsForUser";
    public  final String GetUnitListsForUser1 = "GetUnitListsForUser1";
    public  final String GetUnitListsForUserExplicit = "GetUnitListsForUserExplicit";

    public  final String UploadStoreItemImage = "UploadStoreItemImage";

    public  final String GpsCheckin = "GpsCheckin";
    public  final String IsInstallComplete = "IsInstallComplete";

    public  final String InstallStatusList = "InstallStatusList";
    public  final String SaveSurvey = "SaveSurvey";
    public  final String IsSurveyComplete = "IsSurveyComplete";
    public  final String GetSurvey = "GetSurvey";
    public  final String SaveAndroidUnitExplicits = "SaveAndroidUnitExplicits";
    public  final String GetDocumentCount = "GetDocumentCount";
    public  final String AndroidDocumentTypes = "AndroidDocumentTypes";




    public  final String WSDL_TARGET_NAMESPACE = "http://tempuri.org/";

    //LIVE
    public  final String SOAP_ADDRESS = "http://testingwayo2.winninginontrade.miid.co.za/WayoWebWebService.asmx";
    //public  final String SOAP_ADDRESS = "http://www.orbitdevtest.co.za/VFEFCIPWebService.asmx";
    public MySOAPCallActivity()
    {
    }

    public String InstallStatusList()
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,InstallStatusList);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONInstallStatusList, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String AndroidDocumentTypes()
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,AndroidDocumentTypes);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONAndroidDocumentTypes, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String Call(String TagNumber)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetUserForTag);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("TagNumber");
        pi.setValue(TagNumber);
        pi.setType(String.class);
        request.addProperty(pi);
        //pi=new PropertyInfo();
        //pi.setName("b");
        //pi.setValue(b);
        //pi.setType(Integer.class);
        //request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String GetUnitListsForUser(String UserName)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetUnitListsForUser);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);
        //pi=new PropertyInfo();
        //pi.setName("b");
        //pi.setValue(b);
        //pi.setType(Integer.class);
        //request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetUnitListsForUser, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String GetUnitListsForUser1(String UserName, String PhaseMatters)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetUnitListsForUser1);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);
        PropertyInfo pi2=new PropertyInfo();
        pi2.setName("PhaseMatters");
        pi2.setValue(PhaseMatters);
        pi2.setType(String.class);
        request.addProperty(pi2);
        //pi=new PropertyInfo();
        //pi.setName("b");
        //pi.setValue(b);
        //pi.setType(Integer.class);
        //request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetUnitListsForUser1, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String GetUnitListsForUserExplicit(String UserName, Boolean IsSurvey, Integer StoreID)
    {
        //For installation team
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetUnitListsForUserExplicit);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);

        PropertyInfo pi1=new PropertyInfo();
        pi1.setName("IsSurvey");
        pi1.setValue(IsSurvey);
        pi1.setType(Boolean.class);
        request.addProperty(pi1);

        PropertyInfo pStoreID=new PropertyInfo();
        pStoreID.setName("StoreID");
        pStoreID.setValue(StoreID);
        pStoreID.setType(Integer.class);
        request.addProperty(pStoreID);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetUnitListsForUserExplicit, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }



    public String UploadStoreItemImage(Integer StoreItemID, Integer StoreID, String Base64Image, String UserName, String Type, String ProductComplaint, String Barcode)
    {
        //For installation team
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,UploadStoreItemImage);
        PropertyInfo piUserName=new PropertyInfo();
        piUserName.setName("UserName");
        piUserName.setValue(UserName);
        piUserName.setType(String.class);
        request.addProperty(piUserName);

        PropertyInfo piType=new PropertyInfo();
        piType.setName("Type");
        piType.setValue(Type);
        piType.setType(String.class);
        request.addProperty(piType);

        PropertyInfo piBase64Image=new PropertyInfo();
        piBase64Image.setName("Base64Image");
        piBase64Image.setValue(Base64Image);
        piBase64Image.setType(String.class);
        request.addProperty(piBase64Image);

        PropertyInfo piStoreItemID=new PropertyInfo();
        piStoreItemID.setName("StoreItemID");
        piStoreItemID.setValue(StoreItemID);
        piStoreItemID.setType(Integer.class);
        request.addProperty(piStoreItemID);

        PropertyInfo piStoreID=new PropertyInfo();
        piStoreID.setName("StoreID");
        piStoreID.setValue(StoreID);
        piStoreID.setType(Integer.class);
        request.addProperty(piStoreID);

        PropertyInfo piProductComplaint=new PropertyInfo();
        piProductComplaint.setName("ProductComplaint");
        piProductComplaint.setValue(ProductComplaint);
        piProductComplaint.setType(String.class);
        request.addProperty(piProductComplaint);

        PropertyInfo piBarcode=new PropertyInfo();
        piBarcode.setName("Barcode");
        piBarcode.setValue(Barcode);
        piBarcode.setType(String.class);
        request.addProperty(piBarcode);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONUploadStoreItemImage, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
    public String AmIOnline()
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,AmIOnline);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONAmIOnline, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String Login(String UserName, String Password)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,LoginSiteUser);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);

        PropertyInfo pi2 =new PropertyInfo();
        pi2.setName("Password");
        pi2.setValue(Password);
        pi2.setType(String.class);
        request.addProperty(pi2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONLoginSiteUser, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String GetOpenRequestsForUser(String UserName, String RequestType)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetOpenRequestsForUser);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);

        PropertyInfo pi2 =new PropertyInfo();
        pi2.setName("RequestType");
        pi2.setValue(RequestType);
        pi2.setType(String.class);
        request.addProperty(pi2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetOpenRequestsForUser, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String GetAppointmentsForUser(String UserName, String RequestType)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetAppointmentsForUser);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);

        PropertyInfo pi2 =new PropertyInfo();
        pi2.setName("RequestType");
        pi2.setValue(RequestType);
        pi2.setType(String.class);
        request.addProperty(pi2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetAppointmentsForUser, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String GetStoresForUser(String UserName, String Phase)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetStoresForUser);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("UserName");
        pi.setValue(UserName);
        pi.setType(String.class);
        request.addProperty(pi);
        PropertyInfo pi1=new PropertyInfo();
        pi1.setName("Phase");
        pi1.setValue(Phase);
        pi1.setType(String.class);
        request.addProperty(pi1);




        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetStoresForUser, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }
    public String SaveMyStores(String JsonStores)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SaveMyStores);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("JsonStores");
        pi.setValue(JsonStores);
        pi.setType(String.class);
        request.addProperty(pi);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONSaveMyStores, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String SaveMyOpenRequests( String JsonRequests)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SaveMyOpenRequests);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("JsonRequests");
        pi.setValue(JsonRequests);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONSaveMyOpenRequests, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String SaveMyAppointments( String JsonRequests)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SaveMyAppointments);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("JsonRequests");
        pi.setValue(JsonRequests);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONSaveMyAppointments, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }



    public String SaveSurvey( String JsonSurveys)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SaveSurvey);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("JsonSurveys");
        pi.setValue(JsonSurveys);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONSaveSurvey, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String SaveAndroidUnitExplicits( String jsonStoreItems)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,SaveAndroidUnitExplicits);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("jsonStoreItems");
        pi.setValue(jsonStoreItems);
        pi.setType(String.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONSaveAndroidUnitExplicits, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }



    public String GetSurvey( Integer SurveyID)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetSurvey);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("SurveyID");
        pi.setValue(SurveyID);
        pi.setType(Integer.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetSurvey, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String GpsCheckin(String UserName, Integer RequestID, String GpsLatLng, Boolean IsCheckIn,  String InstallStatus, Integer Mileage)
    {

        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GpsCheckin);
        PropertyInfo piUserName=new PropertyInfo();
        piUserName.setName("UserName");
        piUserName.setValue(UserName);
        piUserName.setType(String.class);
        request.addProperty(piUserName);

        PropertyInfo piRequestID=new PropertyInfo();
        piRequestID.setName("RequestID");
        piRequestID.setValue(RequestID);
        piRequestID.setType(Integer.class);
        request.addProperty(piRequestID);

        PropertyInfo piGpsLatLng=new PropertyInfo();
        piGpsLatLng.setName("GpsLatLng");
        piGpsLatLng.setValue(GpsLatLng);
        piGpsLatLng.setType(String.class);
        request.addProperty(piGpsLatLng);

        PropertyInfo piIsCheckIn=new PropertyInfo();
        piIsCheckIn.setName("IsCheckIn");
        piIsCheckIn.setValue(IsCheckIn);
        piIsCheckIn.setType(Boolean.class);
        request.addProperty(piIsCheckIn);

        PropertyInfo piInstallStatus =new PropertyInfo();
        piInstallStatus.setName("InstallStatus");
        piInstallStatus.setValue(InstallStatus);
        piInstallStatus.setType(String.class);
        request.addProperty(piInstallStatus);

        PropertyInfo piMileage=new PropertyInfo();
        piMileage.setName("Mileage");
        piMileage.setValue(Mileage);
        piMileage.setType(Integer.class);
        request.addProperty(piMileage);



        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS, 14000);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGpsCheckin, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();

    }

    public String IsInstallComplete(Integer StoreID)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,IsInstallComplete);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("StoreID");
        pi.setValue(StoreID);
        pi.setType(Integer.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONIsInstallComplete, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

    public String IsSurveyComplete(Integer StoreID)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,IsSurveyComplete);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("StoreID");
        pi.setValue(StoreID);
        pi.setType(Integer.class);
        request.addProperty(pi);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONIsSurveyComplete, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }


    public String GetDocumentCount(Integer StoreID, String  DocumentType)
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE,GetDocumentCount);
        PropertyInfo pi=new PropertyInfo();
        pi.setName("StoreID");
        pi.setValue(StoreID);
        pi.setType(Integer.class);
        request.addProperty(pi);
        PropertyInfo pi2=new PropertyInfo();
        pi2.setName("DocumentType");
        pi2.setValue(DocumentType);
        pi2.setType(String.class);
        request.addProperty(pi2);


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response=null;
        try
        {
            httpTransport.call(SOAP_ACTIONGetDocumentCount, envelope);
            response = envelope.getResponse();
        }
        catch (Exception exception)
        {
            response=exception.toString();
        }
        return response.toString();
    }

}


