package co.edu.unal.softcompanies.utils;

import android.content.ContentValues;

import java.util.HashMap;

import co.edu.unal.softcompanies.database.models.Company;
import co.edu.unal.softcompanies.database.sqlite.CompanyContract;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/10/17.
 * Universidad Nacional de Colombia
 */
public class Utils {

    public static ContentValues buildValues(Company company){
        ContentValues values = new ContentValues();
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_NAME, company.getName());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_URL, company.getUrl());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_PHONE, company.getPhone());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_EMAIL, company.getEmail());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_PRODUCTS, company.getProducts());
        values.put(CompanyContract.CompanyTable.COLUMN_NAME_CLASSIFICATION, company.getClassification());
        return values;
    }

    public static Company buildCompanyFromHashMap(HashMap<Company.COMPANY_KEYS, String> companyMap){
        Company company = new Company();
        if(companyMap.containsKey(Company.COMPANY_KEYS.ID)) {
            company.setId(Long.parseLong(companyMap.get(Company.COMPANY_KEYS.ID)));
        }
        company.setName(companyMap.get(Company.COMPANY_KEYS.NAME));
        company.setUrl(companyMap.get(Company.COMPANY_KEYS.URL));
        company.setPhone(companyMap.get(Company.COMPANY_KEYS.PHONE));
        company.setEmail(companyMap.get(Company.COMPANY_KEYS.EMAIL));
        company.setProducts(companyMap.get(Company.COMPANY_KEYS.PRODUCTS));
        company.setClassification(Integer.parseInt(companyMap.get(Company.COMPANY_KEYS.CLASSIFICATION)));
        return company;
    }
}
