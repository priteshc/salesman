package com.example.dec.mylocation.BackTask;

import com.example.dec.mylocation.pojo.AllVisitDetailsPojo;
import com.example.dec.mylocation.pojo.AquLoginPojo;
import com.example.dec.mylocation.pojo.ContactPersonPojo;
import com.example.dec.mylocation.pojo.ContactPojo;
import com.example.dec.mylocation.pojo.ExpenseAllpojo;
import com.example.dec.mylocation.pojo.LeaveReportPojo;
import com.example.dec.mylocation.pojo.LocationPojo;
import com.example.dec.mylocation.pojo.MapPojo;
import com.example.dec.mylocation.pojo.NotificationPojo;
import com.example.dec.mylocation.pojo.ProductDetailsPojo;
import com.example.dec.mylocation.pojo.PurposePojo;
import com.example.dec.mylocation.pojo.StatusPojo;
import com.example.dec.mylocation.pojo.VisitDetailsPojo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by pritesh on 3/27/2017.
 */

public interface AllApi {

    @FormUrlEncoded
    @POST("/php/Location_Insert.php")
    Observable<LocationPojo> getConform(@Field("Location_Name") String name,@Field("Lat") Double lat,@Field("Lng") Double lng,@Field("date") String date1,@Field("user") String login,@Field("time") String time);


    @FormUrlEncoded
    @POST("/php/Visit.php")
    Observable<LocationPojo> getData(@Field("Cid") String cid,@Field("Cust_Name") String name,@Field("Addresss") String addr,@Field("State") String state,@Field("City") String city,@Field("Region") String region,@Field("Pincode") String pin,@Field("OfficeTel") String offtel,@Field("Email") String emil,@Field("Descrip") String descr,@Field("Date") String date,@Field("Time") String time);


    @FormUrlEncoded
    @POST("/php/Visit_Details.php")
    Observable<VisitDetailsPojo> getVisitdata(@Field("region") String reg);



    @FormUrlEncoded
    @POST("/php/Visit_Details_Filter.php")
    Observable<VisitDetailsPojo> getVisitdatafilter(@Field("cname") String cname);


    @FormUrlEncoded
    @POST("/php/All_Visit.php")
    Observable<LocationPojo> getVisit(@Field("vid") String vid,@Field("Date") String date,@Field("Time") String time,@Field("purpose_id") String purposeid,@Field("status_id") String statusid,@Field("product_id") String pid,@Field("description") String desc,@Field("cust_id") String cuid,@Field("contact_name") String name,@Field("remark") String reamrk,@Field("fdate") String fdat,@Field("ftime") String ftim,@Field("lname") String logname,@Field("id") String lid,@Field("ftime") String ftime,@Field("ttime") String ttime,@Field("thrs") String hrs);


    @FormUrlEncoded
    @POST("/php/All_Visit_Data.php")
    Observable<AllVisitDetailsPojo> getAllVisitdata(@Field("id") String vid);


    @FormUrlEncoded
    @POST("/php/ContactDetail.php")
    Observable<LocationPojo> getContact(@Field("id") String id,@Field("name") String name,@Field("desgination") String desg,@Field("mobile") String mob,@Field("mobile2") String mob2,@Field("email") String emil);


    @FormUrlEncoded
    @POST("/php/GetContact.php")
    Observable<ContactPojo> getAllcont(@Field("id") String cid);



    @GET("/php/ContactPerson.php")
    Observable<ContactPersonPojo> getAllContact();

    @FormUrlEncoded
    @POST("/php/Maps_Data.php")
    Observable<MapPojo> getMapdata(@Field("Test") String user,@Field("date") String cdate);


    @FormUrlEncoded
    @POST("/php/Aquanomics_Login.php")
    Observable<AquLoginPojo> getLogin(@Field("Email") String email,@Field("password") String pass);

    @FormUrlEncoded
    @POST("/php/Aquanomics_Token.php")
    Observable<AquLoginPojo> getTDetail(@Field("Logid") String logid,@Field("token") String token);


    @FormUrlEncoded
    @POST("/php/Expense_Details.php")
    Observable<LocationPojo> getExpense(@Field("id") String email,@Field("date") String pass,@Field("company_name") String name,@Field("description") String desc,@Field("km") String dist,@Field("amount") String amount);

    @GET("/php/Status_Details.php")
    Observable<StatusPojo> getAllStatus();


    @GET("/php/Purpose_Details.php")
    Observable<PurposePojo> getAllPurpose();


    @FormUrlEncoded
    @POST("/php/Product_Details.php")
    Observable<ProductDetailsPojo> getAllproduct(@Field("cate") String cate);


    @FormUrlEncoded
    @POST("/php/Leave_Request.php")
    Observable<LocationPojo> getleave(@Field("date") String date,@Field("logid") String lgid,@Field("leavetype") String lev,@Field("lfrom") String from,@Field("lupto") String upto,@Field("nofday") String days,@Field("levresone") String levreson,@Field("reptmgrid") String mgrid,@Field("status") String status,@Field("remark") String remrk,@Field("usertoken") String usrtoken,@Field("mgrtoken") String mgtoken);

    @FormUrlEncoded
    @POST("/php/Visit_Filterdata.php")
    Observable<AllVisitDetailsPojo> getVisitFilter(@Field("cdate") String date,@Field("cname") String cuname);

    @FormUrlEncoded
    @POST("/php/ExpenseAll.php")
    Observable<ExpenseAllpojo> getAllExpense(@Field("lid") String id);


    @FormUrlEncoded
    @POST("/php/AqunomicPushNotification.php")
    Observable<NotificationPojo> getNotification(@Field("desc") String desc, @Field("ctoken") String token,@Field("reptid") String id,@Field("utoken") String utoken,@Field("name") String name,@Field("type") String type,@Field("id") String uid);


    @FormUrlEncoded
    @POST("/php/Update_LeaveStatus.php")
    Observable<LocationPojo> getUpdate(@Field("vid") String id,@Field("status") String status);


    @FormUrlEncoded
    @POST("/php/Leave_Report.php")
    Observable<LeaveReportPojo> getReport(@Field("from") String from, @Field("to") String to,@Field("lid") String id);

    @FormUrlEncoded
    @POST("/php/Repoti_Name.php")
    Observable<AquLoginPojo> getReportname(@Field("Lid") String lid);


    @FormUrlEncoded
    @POST("/php/Followup_Report.php")
    Observable<AllVisitDetailsPojo> getFollowupData(@Field("lid") String lid,@Field("date") String date);



}
