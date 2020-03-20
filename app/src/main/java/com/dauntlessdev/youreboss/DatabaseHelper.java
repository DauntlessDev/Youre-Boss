package com.dauntlessdev.youreboss;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.dauntlessdev.youreboss.Model.Contract;

import java.util.ArrayList;


public class DatabaseHelper  extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "note_db";

    //for contract table and columns
    private static final String CONT_TABLE = "contract_table";
    private static final String CONT_ID = "id";
    private static final String CONT_TITLE = "title";
    private static final String CONT_CONTENT = "content";
    private static final String CONT_DATE = "date";

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + CONT_TABLE + "(" +
                CONT_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                CONT_TITLE + " TEXT," +
                CONT_CONTENT +" TEXT," +
                CONT_DATE +" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONT_TABLE);
        onCreate(sqLiteDatabase);
    }

    public long addContract(Contract contract){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONT_TITLE, contract.getTitle());
        contentValues.put(CONT_CONTENT, contract.getContent());
        contentValues.put(CONT_DATE, contract.getDatetime());

        long res = db.insert(CONT_TABLE, null, contentValues);
        db.close();
        Log.i("Res", String.valueOf(res));
        return res;

    }

    public Contract getSingleContract(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_NAME, new String[]{CONT_ID,CONT_TITLE,CONT_CONTENT,CONT_DATE},
        CONT_ID + "=?", new String[]{String.valueOf(id)}, null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Contract contract = new Contract(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3));

        return contract;
    }


    public ArrayList<Contract> getAllContract(){
        ArrayList<Contract> listOfContracts = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT *from " + CONT_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                Contract contract = new Contract(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));

                listOfContracts.add(contract);

            }while (cursor.moveToNext());
        }

        return listOfContracts;
    }

}
