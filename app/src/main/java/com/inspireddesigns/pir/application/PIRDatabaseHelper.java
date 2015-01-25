package com.inspireddesigns.pir.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.inspireddesigns.pir.model.Parent;
import com.inspireddesigns.pir.model.Reader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brad Siegel on 1/22/15.
 * <p/>
 * Singleton used to store and retrieve data throughout the entire application.
 */
public class PIRDatabaseHelper extends SQLiteOpenHelper {

    private static PIRDatabaseHelper mHelperInstance;
    private static SQLiteDatabase mDbInstance;

    private static final String DATABASE_NAME = "PIR";
    private static final String PARENT_TABLE = "parent";
    private static final String READER_TABLE = "reader";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_PARENT = "CREATE TABLE parent";
    private static final String CREATE_TABLE_READER = "CREATE TABLE reader";

    private static final String ID_COL = "_id";
    private static final String EMAIL_COL = "email";
    private static final String READERS_COL = "readers";
    private static final String ACTIVATED_COL = "activated";
    private static final String LAST_LOGIN_COL = "last_login";
    private static final String CREATED_COL = "created";

    private static final String SELECT_PARENT = "SELECT * FROM parent_table WHERE id=?";
    private static final String SELECT_FIRST_PARENT = "SELECT TOP 1 * FROM parent";
    private static final String SELECT_READERS_FOR_PARENT = "SELECT * FROM readers WHERE parentId=?";

    public static PIRDatabaseHelper getInstance(Context context) {
        if (mHelperInstance == null) {
            mHelperInstance = new PIRDatabaseHelper(context.getApplicationContext());
        }
        return mHelperInstance;
    }

    private PIRDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private SQLiteDatabase getDbInstance() {
        if (mDbInstance == null) {
            mDbInstance = getWritableDatabase();
        }
        return mDbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_PARENT);
        database.execSQL(CREATE_TABLE_READER);
        //create dummy data here if needed
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i2) {

    }

    /**
     * Queries the parent table based on the parentId that is passed in.
     * Should only be called from parent user type.
     *
     * @param parentId id of the parent
     * @return the Parent object with the corresponding id
     */
    public Parent getParent(String parentId) {
        Parent parent = null;
        SQLiteDatabase db = getDbInstance();
        Cursor csr = db.rawQuery(SELECT_PARENT, new String[]{parentId});
        if (csr != null) {
            if (csr.moveToFirst()) {
                parent = buildParent(csr);
            }
            csr.close();
        }
        return parent;
    }

    /**
     * Used to retrieve the Parent object of the parent user that is logged in.
     * This queries the parent table and returns the first row which will be the only
     * row in the table.
     *
     * @return The Parent object of the logged in user
     */
    public Parent getParent() {
        Parent parent = null;
        SQLiteDatabase db = getDbInstance();
        Cursor csr = db.rawQuery(SELECT_FIRST_PARENT, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                parent = buildParent(csr);
            }
            csr.close();
        }
        return parent;
    }

    /**
     * Inserts new row into the parent table if parent does not already exist. If the parent exists,
     * the parent is updated.
     *
     * @param parent the parent to save or update
     * @return true if save successful, otherwise false.
     */
    public boolean saveParent(Parent parent) {
        //TODO check if parent exists, if not insert, else update
        long result = -1;
        SQLiteDatabase db = getDbInstance();
        ContentValues values = new ContentValues();
        values.put(ID_COL, parent.get_id());
        values.put(EMAIL_COL, parent.getEmail());
        //TODO save readers
        //values.put(READERS_COL, parent.getReaders());

        result = db.insert(PARENT_TABLE, null, values);

        return result > -1 ? true : false;
    }

    //TODO public boolean saveParents(List<Parent> parents){}


    /**
     * Queries the readers table and returns all Readers that a Parent has registered.
     *
     * @param parent The parent who has readers
     * @return List of readers for parent
     */
    public List<Reader> getReadersForParent(Parent parent){
        List<Reader> readers = new ArrayList<>();
        SQLiteDatabase db = getDbInstance();
        Cursor csr = db.rawQuery(SELECT_READERS_FOR_PARENT, new String[]{parent.get_id()});
        if(csr != null){
            if(csr.moveToFirst()){
                while(!csr.isAfterLast() && csr.getCount() > 0){
                    readers.add(buildReader(csr));
                    csr.moveToNext();
                }
            }
            csr.close();
        }
        return readers;
    }

    //TODO public boolean saveReaders(List<Reader> readers){}

    /**
     * Used to retrieve total number or Readers in the system
     *
     * @return number of readers
     */
    public long getReaderCount(){
        SQLiteDatabase db = getDbInstance();
        return DatabaseUtils.queryNumEntries(db, READER_TABLE);
    }

    /**
     * Used to retrieve total number of Parents in the system.
     *
     * @return number of parents
     */
    public long getParentCount(){
        SQLiteDatabase db = getDbInstance();
        return DatabaseUtils.queryNumEntries(db, PARENT_TABLE);
    }






    //TODO saveReader(Reader reader)

    //TODO deleteReader(Reader reader)

    private Parent buildParent(Cursor csr) {
        Parent parent = new Parent();
        //TODO set all fields in parent from cursor
        return parent;
    }

    private Reader buildReader(Cursor csr) {
        Reader reader = new Reader();
        //TODO set all fields in Reader from cursor
        return reader;
    }

}
