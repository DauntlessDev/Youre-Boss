package com.dauntlessdev.youreboss.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dauntlessdev.youreboss.Model.Contract;
import com.dauntlessdev.youreboss.Model.Task;

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



    //for task table and columns
    private static final String TASK_TABLE = "task_table";
    private static final String TASK_ID= "id";
    private static final String TASK_NAME = "name";

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


        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TASK_TABLE + "(" +
                        TASK_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                        TASK_NAME + " TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CONT_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(sqLiteDatabase);
    }



    public long addContract(Contract contract){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONT_TITLE, contract.getTitle());
        contentValues.put(CONT_CONTENT, contract.getContent());
        contentValues.put(CONT_DATE, contract.getDatetime());

        long res = db.insert(CONT_TABLE, null, contentValues);
        return res;

    }

    public long updateContract(Contract contract){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONT_TITLE, contract.getTitle());
        contentValues.put(CONT_CONTENT, contract.getContent());
        contentValues.put(CONT_DATE, contract.getDatetime());

        long res = db.update(CONT_TABLE, contentValues,
                CONT_ID + "=?",new String[] {String.valueOf(contract.getId())});
        return res;

    }


    public Contract getSingleContract(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(CONT_TABLE, new String[]{CONT_ID,CONT_TITLE,CONT_CONTENT,CONT_DATE},
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

    void deleteContract(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CONT_TABLE, CONT_ID + "=?", new String[]{String.valueOf(id)});
    }


    // SECTION FOR FUNCTIONS OF TASK FRAGMENT

    public long addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_NAME, task.getName());

        long res = db.insert(TASK_TABLE, null, contentValues);
        return res;

    }

    public long updateTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TASK_ID, task.getId());
        contentValues.put(TASK_NAME, task.getName());

        long res = db.update(TASK_TABLE, contentValues,
                TASK_ID + "=?",new String[] {String.valueOf(task.getId())});
        return res;

    }


    public Task getSingleTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TASK_TABLE, new String[]{TASK_ID,TASK_NAME},
                TASK_ID + "=?", new String[]{String.valueOf(id)}, null,null,null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Task task = new Task(cursor.getInt(0),
                cursor.getString(1));
        return task;
    }

    public ArrayList<Task> getAllTask(){
        ArrayList<Task> listOfTasks = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * from " + TASK_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do{
                Task task = new Task(cursor.getInt(0),
                        cursor.getString(1));

                listOfTasks.add(task);

            }while (cursor.moveToNext());
        }

        return listOfTasks;
    }

    public void deleteTask(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE, TASK_ID + "=?", new String[]{String.valueOf(id)});
    }

}
