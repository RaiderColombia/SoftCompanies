package co.edu.unal.softcompanies.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import co.edu.unal.softcompanies.database.daos.CompanyDAO;
import co.edu.unal.softcompanies.database.models.Company;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/5/17.
 * Universidad Nacional de Colombia
 */
public class CompanyController {

    private CompanyDAO dao;

    public CompanyController() {
        dao = new CompanyDAO();
    }

    public boolean create(HashMap<Company.COMPANY_KEYS, String> companyMap){
        Company company = new Company();
        company.setName(companyMap.get(Company.COMPANY_KEYS.NAME));
        company.setUrl(companyMap.get(Company.COMPANY_KEYS.URL));
        company.setPhone(companyMap.get(Company.COMPANY_KEYS.PHONE));
        company.setEmail(companyMap.get(Company.COMPANY_KEYS.EMAIL));
        company.setProducts(companyMap.get(Company.COMPANY_KEYS.PRODUCTS));
        company.setClassification(Integer.parseInt(companyMap.get(Company.COMPANY_KEYS.CLASSIFICATION)));
        return dao.persist(company);
    }

    public ArrayList<Company> retrieveAll(){
        return dao.findAllCompanies();
    }

    public Company retrieve(int id){
        return dao.findById(id);
    }

}
