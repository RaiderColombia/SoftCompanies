package co.edu.unal.softcompanies.database.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/8/17.
 * Universidad Nacional de Colombia
 */
public class CompanyDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "SoftCompanies.db";

    private static final String SQL_CREATE_COMPANIES =
        "CREATE TABLE " + CompanyContract.CompanyTable.TABLE_NAME + " (" +
                CompanyContract.CompanyTable._ID + " INTEGER PRIMARY KEY," +
                CompanyContract.CompanyTable.COLUMN_NAME_NAME + " TEXT," +
                CompanyContract.CompanyTable.COLUMN_NAME_URL + " TEXT," +
                CompanyContract.CompanyTable.COLUMN_NAME_PHONE + " TEXT," +
                CompanyContract.CompanyTable.COLUMN_NAME_EMAIL + " TEXT," +
                CompanyContract.CompanyTable.COLUMN_NAME_PRODUCTS + " TEXT," +
                CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION + " INTEGER)";

    private static final String SQL_DELETE_COMPANIES = "DROP TABLE IF EXISTS " + CompanyContract.CompanyTable.TABLE_NAME;

    public CompanyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_COMPANIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_COMPANIES);
        onCreate(db);
    }
}
