package co.edu.unal.softcompanies.database.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.edu.unal.softcompanies.database.models.Company;
import co.edu.unal.softcompanies.database.sqlite.CompanyContract;
import co.edu.unal.softcompanies.database.sqlite.CompanyDbHelper;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/5/17.
 * Universidad Nacional de Colombia
 */
public class CompanyDAO {

    private SQLiteDatabase db;
    private CompanyDbHelper helper;

    public CompanyDAO(Context context) {
        helper = new CompanyDbHelper(context);
        db = helper.getWritableDatabase();
    }

    public void closeConnection(){
        helper.close();
    }

    public boolean persist(Company company){
        ContentValues values = new ContentValues();
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_NAME, company.getName());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_URL, company.getUrl());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_PHONE, company.getPhone());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_EMAIL, company.getEmail());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_PRODUCTS, company.getProducts());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION, company.getClassification());
        long newRowId = db.insert(CompanyContract.CompanyTable.TABLE_NAME, null, values);
        company.setId(newRowId);
        return newRowId > 0;
    }

    public ArrayList<Company> findAllCompanies(){
        String[] projection = {
            CompanyContract.CompanyTable._ID,
            CompanyContract.CompanyTable.COLUMN_NAME_NAME
        };

        String sortOrder = CompanyContract.CompanyTable.COLUMN_NAME_NAME + " ASC";

        Cursor cursor = db.query(
            CompanyContract.CompanyTable.TABLE_NAME,
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        );

        ArrayList<Company> companies = new ArrayList<>();

        while(cursor.moveToNext()){
            Company company = new Company();
            company.setId(cursor.getLong(cursor.getColumnIndex(CompanyContract.CompanyTable._ID)));
            company.setName(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_NAME)));
            companies.add(company);
        }
        cursor.close();

        return companies;
    }

    public Company findById(long id){
        String[] projection = {
            CompanyContract.CompanyTable._ID,
            CompanyContract.CompanyTable.COLUMN_NAME_NAME,
            CompanyContract.CompanyTable.COLUMN_NAME_URL,
            CompanyContract.CompanyTable.COLUMN_NAME_PHONE,
            CompanyContract.CompanyTable.COLUMN_NAME_EMAIL,
            CompanyContract.CompanyTable.COLUMN_NAME_PRODUCTS,
            CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION
        };

        String selection = CompanyContract.CompanyTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        String sortOrder = CompanyContract.CompanyTable.COLUMN_NAME_NAME + " ASC";

        Cursor cursor = db.query(
                CompanyContract.CompanyTable.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );

        Company company = new Company();

        while(cursor.moveToNext()){
            company.setId(cursor.getLong(cursor.getColumnIndex(CompanyContract.CompanyTable._ID)));
            company.setName(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_NAME)));
            company.setUrl(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_URL)));
            company.setPhone(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_PHONE)));
            company.setEmail(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_EMAIL)));
            company.setProducts(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_PRODUCTS)));
            company.setClassification(cursor.getInt(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION)));
        }
        cursor.close();

        return company;
    }
}
