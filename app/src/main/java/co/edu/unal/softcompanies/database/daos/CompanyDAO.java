package co.edu.unal.softcompanies.database.daos;

import java.util.ArrayList;

import co.edu.unal.softcompanies.database.models.Company;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/5/17.
 * Universidad Nacional de Colombia
 */
public class CompanyDAO {

    private ArrayList<Company> companies;

    public CompanyDAO() {
        companies = new ArrayList<>();
    }

    public boolean persist(Company company){
        return companies.add(company);
    }

    public ArrayList<Company> findAllCompanies(){
        for (int i = 0; i < 10; i++) {
            Company c = new Company();
            c.setId(i);
            c.setName(String.format("Company %d", i));
            companies.add(c);
        }
        return companies;
    }

    public Company findById(int id){
        findAllCompanies();
        for (Company company : companies) {
            if (company.getId() == id) {
                return company;
            }
        }
        return null;
    }
}
