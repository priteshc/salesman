package com.example.dec.mylocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pritesh on 6/15/2017.
 */

public class AqunomicDatabase  {



    private static final String DATABASE_NAME = "aqu";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CUSTOMER = "entry";

    public static final String KEY_CID = "id";

    public static final String KEY_CUSTOMERNAME = "customer";
    public static final String KEY_CUSTID = "contact";



   private static final String DATABASE_CONTACT = "contact";

    public static final String KEY_CD = "cid";

    public static final String KEY_NAME = "name";
    public static final String KEY_CSTID = "csid";




    private Dbhelper oHelper;
    private final Context ocontext;
    private SQLiteDatabase oDtabase;



    private static class Dbhelper extends SQLiteOpenHelper {

        public Dbhelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(" CREATE TABLE " + DATABASE_CUSTOMER + " (" + KEY_CID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_CUSTOMERNAME + " TEXT, " + KEY_CUSTID + " TEXT);"
            );


            db.execSQL(" CREATE TABLE " + DATABASE_CONTACT + " (" + KEY_CD + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_CSTID + " TEXT);"
            );



        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXIST" + DATABASE_CUSTOMER);
            onCreate(db);


        }
    }

    public AqunomicDatabase(Context c) {
        ocontext = c;
        oHelper = new Dbhelper(c);
        oDtabase = oHelper.getWritableDatabase();
    }

    public AqunomicDatabase open() {
        oHelper = new Dbhelper(ocontext);
        oDtabase = oHelper.getWritableDatabase();
        oDtabase = oHelper.getReadableDatabase();
        return this;

    }

    public void close() {
        oHelper.close();

    }



    public long createentry (String customer,String contact){


        ContentValues lcv = new ContentValues();

        lcv.put(KEY_CUSTOMERNAME,customer);
        lcv.put(KEY_CUSTID,contact);

        return oDtabase.insert(DATABASE_CUSTOMER, null, lcv);


    }



    public long contactentry (String customername,String contactid){


       // deleteitm();

        ContentValues lcv = new ContentValues();

        lcv.put(KEY_NAME,customername);
        lcv.put(KEY_CSTID,contactid);

        return oDtabase.insert(DATABASE_CONTACT, null, lcv);


    }

    public Cursor getdata(String cust) {

        Cursor c = oDtabase.rawQuery(
                "SELECT * FROM " + DATABASE_CUSTOMER + " WHERE "
                        + KEY_CUSTOMERNAME + "='" + cust +"'" , null);

        return c;
    }



    public Cursor getcontact(String custid) {

        Cursor c1 = oDtabase.rawQuery(
                "SELECT * FROM " + DATABASE_CONTACT + " WHERE "
                        + KEY_CSTID + "='" + custid +"'" , null);
        return c1;
    }



    public void deleteitm(){


        SQLiteDatabase db = this.oHelper.getWritableDatabase();

        db.execSQL("delete from " + DATABASE_CUSTOMER);

        db.close();
    }


    public void deleteitm1(){


        SQLiteDatabase db = this.oHelper.getWritableDatabase();

        db.execSQL("delete from " + DATABASE_CONTACT);

        db.close();
    }

}
