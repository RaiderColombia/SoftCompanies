package co.edu.unal.softcompanies.controllers;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import co.edu.unal.softcompanies.database.daos.CompanyDAO;
import co.edu.unal.softcompanies.database.models.Company;

import static co.edu.unal.softcompanies.utils.Utils.buildCompanyFromHashMap;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/5/17.
 * Universidad Nacional de Colombia
 */
public class CompanyController {

    private CompanyDAO dao;

    public CompanyController(Context context) {
        dao = new CompanyDAO(context);
    }

    public void close(){dao.closeConnection();}

    public boolean create(HashMap<Company.COMPANY_KEYS, String> companyMap){
        Company company = buildCompanyFromHashMap(companyMap);
        return dao.persist(company);
    }

    public ArrayList<Company> retrieveAll(){
        return dao.findAllCompanies();
    }

    public ArrayList<Company> retrieveAllByName(String name){
        return dao.findByName(name);
    }

    public Company retrieve(long id){
        return dao.findById(id);
    }

    public boolean update(HashMap<Company.COMPANY_KEYS, String> companyMap){
        Company company = buildCompanyFromHashMap(companyMap);
        return dao.update(company);
    }

    public boolean delete(long id){
        return dao.delete(id);
    }

}
