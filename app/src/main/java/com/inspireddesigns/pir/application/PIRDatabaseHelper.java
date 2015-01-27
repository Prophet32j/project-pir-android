package com.inspireddesigns.pir.application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.inspireddesigns.pir.model.Parent;
import com.inspireddesigns.pir.model.Reader;
import com.inspireddesigns.pir.model.User;

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
    private static final String PARENT_TABLE = "parents";
    private static final String READER_TABLE = "readers";
    private static final String AVAILABILITY_TABLE = "availability";
    private static final String USERS_TABLE = "users";

    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_PARENT = "CREATE TABLE parents (parent_id TEXT PRIMARY KEY, email TEXT, first_name TEXT, last_name TEXT) ";
    private static final String CREATE_TABLE_READER = "CREATE TABLE readers (FOREIGN KEY(_id) REFERENCES parents(parent_id), TEXT first_name, " +
            "TEXT last_name, TEXT image, TEXT gender, INTEGER age, TEXT grade, TEXT alt_phone, TEXT alt_parent, INTEGER special_needs, " +
            "INTEGER language_needs, TEXT about_me, TEXT pair)";
    private static final String CREATE_TABLE_AVAILABILITY = "CREATE TABLE availability";
    private static final String CREATE_TABLE_USERS = "CREATE TABLE users(user_id TEXT PRIMARY KEY NOT NULL, type TEXT, password TEXT, email TEXT, _v TEXT, activated INT, last_login TEXT, created TEXT);";


    //Shared columns
    private static final String EMAIL_COL = "email";

    //Parent columns
    private static final String PARENT_ID_COL = "parent_id";
    private static final String FIRST_NAME_COL = "first_name";
    private static final String LAST_NAME_COL = "last_name";

    //Reader columns
    private static final String READER_ID_COL = "reader_id";
    private static final String IMAGE_COL = "image";
    private static final String GENDER_COL = "gender";
    private static final String AGE_COL = "age";
    private static final String GRADE_COL = "grade";
    private static final String ALT_PHONE_COL = "alt_phone";
    private static final String ALT_PARENT_COL = "alt_parent";
    private static final String SPECIAL_NEEDS_COL = "special_needs";
    private static final String LANGUAGE_NEEDS_COL = "language_needs";
    private static final String ABOUT_ME_COL = "about_me";
    private static final String PAIR_COL = "pair";

    //User columns
    private static final String USER_ID_COL = "user_id";
    private static final String TYPE_COL = "type";
    private static final String PASSWORD_COL = "password";
    private static final String _v_COL = "_v";
    private static final String ACTIVATED_COL = "activated";
    private static final String LAST_LOGIN_COL = "last_login";
    private static final String CREATED_COL = "created";


    private static final String READER_WHERE_CLAUSE = "WHERE reader_id=?";

    private static final String SELECT_PARENT = "SELECT * FROM parents WHERE id=?";
    private static final String SELECT_FIRST_PARENT = "SELECT TOP 1 * FROM parents";
    private static final String SELECT_FIRST_USER = "SELECT * FROM users LIMIT 1";
    private static final String SELECT_READERS_FOR_PARENT = "SELECT * FROM readers WHERE parent_id=?";

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
        database.execSQL(CREATE_TABLE_USERS);
//        database.execSQL(CREATE_TABLE_PARENT);
//        database.execSQL(CREATE_TABLE_READER);
//        database.execSQL(CREATE_TABLE_AVAILABILITY);
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
        values.put(PARENT_ID_COL, parent.get_id());
        values.put(EMAIL_COL, parent.getEmail());
        //TODO save readers
        saveReader(parent.getReaders(), parent.get_id());

        result = db.insert(PARENT_TABLE, null, values);

        return result > -1 ? true : false;
    }

    private boolean saveReader(List<Reader> readers, String parent_id){
        SQLiteDatabase db = getDbInstance();
        ContentValues values = new ContentValues();
        Cursor csr = db.rawQuery(SELECT_READERS_FOR_PARENT, new String[]{parent_id});
        long result = -1;

        for(Reader reader: readers){
            values.put(READER_ID_COL, reader.get_id());
            values.put(PARENT_ID_COL, parent_id);
            values.put(FIRST_NAME_COL, reader.getFirst_name());
            values.put(LAST_NAME_COL, reader.getLast_name());
            values.put(IMAGE_COL, reader.getImage());
            values.put(GENDER_COL, reader.getGender());
            values.put(AGE_COL, reader.getAge());
            values.put(GRADE_COL, reader.getGrade());
            values.put(ALT_PHONE_COL, reader.getAlt_phone());
            values.put(ALT_PARENT_COL, reader.getAlt_parent());
            int isSpecialNeeds = reader.isSpecial_needs() ?  1 : 0;
            values.put(SPECIAL_NEEDS_COL, isSpecialNeeds);
            int isLanguageNeeds = reader.isLanguage_needs() ? 1 : 0;
            values.put(LANGUAGE_NEEDS_COL, isLanguageNeeds);
            values.put(ABOUT_ME_COL, reader.getAbout_me());
            values.put(PAIR_COL, reader.getPair());
            saveReaderAvailability(parent_id);

            if(csr == null){
                result = db.insert(READER_TABLE, null, values);
            }else{
                result = db.update(READER_TABLE, values, READER_WHERE_CLAUSE, new String[]{reader.get_id()});
            }
        }
        return result != -1;
    }

    private boolean saveReaderAvailability(String parent_id) {

        return false;
    }

    //TODO public boolean saveParents(List<Parent> parents){}


    /**
     * Queries the readers table and returns all Readers that a Parent has registered.
     *
     * @param parent The parent who has readers
     * @return List of readers for parent
     */
    public List<Reader> getReadersForParent(Parent parent) {
        List<Reader> readers = new ArrayList<>();
        SQLiteDatabase db = getDbInstance();
        Cursor csr = db.rawQuery(SELECT_READERS_FOR_PARENT, new String[]{parent.get_id()});
        if (csr != null) {
            if (csr.moveToFirst()) {
                while (!csr.isAfterLast() && csr.getCount() > 0) {
                    readers.add(buildReader(csr));
                    csr.moveToNext();
                }
            }
            csr.close();
        }
        return readers;
    }

    public boolean saveUser(User user){
        SQLiteDatabase db = getDbInstance();
        ContentValues values = new ContentValues();
        values.put(USER_ID_COL, user.get_id());
        values.put(TYPE_COL, user.getType());
        values.put(PASSWORD_COL, user.getPassword());
        values.put(EMAIL_COL, user.getEmail());
        values.put(_v_COL, user.get_v());
        int activated = user.isActivated() == true ? 1 : 0;
        values.put(ACTIVATED_COL, activated);
        values.put(LAST_LOGIN_COL, user.getLast_login());
        values.put(CREATED_COL, user.getCreated());

        long result = db.insert(USERS_TABLE, null, values);
        return result != -1;
    }

    public User getUser() {
        User user = null;
        SQLiteDatabase db = getDbInstance();
        Cursor csr = db.rawQuery(SELECT_FIRST_USER, null);
        if (csr != null) {
            if (csr.moveToFirst()) {
                user = buildUser(csr);
            }
            csr.close();
        }
        Log.i(ApplicationController.TAG, "Retrieving user from database: " + user.getEmail());
        return user;
    }

    //TODO public boolean saveReaders(List<Reader> readers){}

    /**
     * Used to retrieve total number or Readers in the system
     *
     * @return number of readers
     */
    public long getReaderCount() {
        SQLiteDatabase db = getDbInstance();
        return DatabaseUtils.queryNumEntries(db, READER_TABLE);
    }

    /**
     * Used to retrieve total number of Parents in the system.
     *
     * @return number of parents
     */
    public long getParentCount() {
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

    private User buildUser(Cursor csr){
        User user = new User();
        user.set_id(csr.getString(csr.getColumnIndex(USER_ID_COL)));
        user.setType(csr.getString(csr.getColumnIndex(TYPE_COL)));
        user.setPassword(csr.getString(csr.getColumnIndex(PASSWORD_COL)));
        user.setEmail(csr.getString(csr.getColumnIndex(EMAIL_COL)));
        user.set_v(csr.getString(csr.getColumnIndex(_v_COL)));
        boolean activated = csr.getInt(csr.getColumnIndex(ACTIVATED_COL)) == 1 ? true : false;
        user.setActivated(activated);
        String last_login = csr.getString(csr.getColumnIndex(LAST_LOGIN_COL));
        if(last_login == null){
            user.setLast_login("First login");
        }else{
            user.setLast_login(last_login);
        }
        user.setCreated(csr.getString(csr.getColumnIndex(CREATED_COL)));
        return user;
    }

}
