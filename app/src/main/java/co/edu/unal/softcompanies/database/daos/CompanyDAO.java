package co.edu.unal.softcompanies.database.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import co.edu.unal.softcompanies.database.models.Company;
import co.edu.unal.softcompanies.database.sqlite.CompanyContract;
import co.edu.unal.softcompanies.database.sqlite.CompanyDbHelper;

import static co.edu.unal.softcompanies.utils.Utils.buildValues;

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
        ContentValues values = buildValues(company);
        long newRowId = db.insert(CompanyContract.CompanyTable.TABLE_NAME, null, values);
        company.setId(newRowId);
        return newRowId >= 0;
    }

    public boolean update(Company company){
        long id = company.getId();
        ContentValues values = buildValues(company);
        String selection = CompanyContract.CompanyTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int rowsAffected = db.update(CompanyContract.CompanyTable.TABLE_NAME, values, selection, selectionArgs);
        return rowsAffected > 0;
    }

    public boolean delete(long id){
        String selection = CompanyContract.CompanyTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(id)};
        int rowsAffected = db.delete(CompanyContract.CompanyTable.TABLE_NAME, selection, selectionArgs);
        return rowsAffected > 0;
    }

    private ArrayList<Company> find(String query){
        String[] projection = {
                CompanyContract.CompanyTable._ID,
                CompanyContract.CompanyTable.COLUMN_NAME_NAME,
                CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION
        };

        String selection = null;
        String[] selectionArgs = null;
        if(!query.equals("")) {
            selection = CompanyContract.CompanyTable.COLUMN_NAME_NAME + " like ?";
            selectionArgs = new String[]{"%" + query + "%"};
        }

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

        ArrayList<Company> companies = new ArrayList<>();

        while(cursor.moveToNext()){
            Company company = new Company();
            company.setId(cursor.getLong(cursor.getColumnIndex(CompanyContract.CompanyTable._ID)));
            company.setName(cursor.getString(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_NAME)));
            company.setClassification(cursor.getInt(cursor.getColumnIndex(CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION)));
            companies.add(company);
        }
        cursor.close();

        return companies;
    }

    public ArrayList<Company> findAllCompanies(){
        return find("");
    }

    public ArrayList<Company> findByName(String name){
        return find(name);
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
