package co.edu.unal.softcompanies.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import co.edu.unal.softcompanies.R;
import co.edu.unal.softcompanies.controllers.CompanyController;
import co.edu.unal.softcompanies.database.models.Company;

public class CompanyActivity extends AppCompatActivity {

    private static final String TAG = "SOFT_COMPANIES";

    private EditText mNameEditText;
    private EditText mUrlEditText;
    private EditText mPhoneEditText;
    private EditText mEmailEditText;
    private EditText mProductsEditText;
    private Spinner mClassificationSpinner;

    private HashMap<Company.COMPANY_KEYS, String> company;
    private CompanyController controller;
    private String label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        Intent intent = getIntent();
        label = intent.getStringExtra(CompanyListActivity.EXTRA_OPERATION_COMPANY);
        ((TextView)findViewById(R.id.companyTitleTextView)).setText(label.concat(" company"));
        ((Button)findViewById(R.id.companyButton)).setText(label);

        mNameEditText = findViewById(R.id.companyNameEditText);
        mUrlEditText = findViewById(R.id.companyURLEditText);
        mPhoneEditText = findViewById(R.id.companyPhoneEditText);
        mEmailEditText = findViewById(R.id.companyEmailEditText);
        mProductsEditText = findViewById(R.id.companyProdServEditText);
        mClassificationSpinner = findViewById(R.id.companyClassificationSpinner);

        company = new HashMap<>();
        controller = new CompanyController(getApplicationContext());

        if(label.equals(getText(R.string.new_label))){
            (findViewById(R.id.deleteCompanyButton)).setVisibility(View.GONE);
        }else{
            long id = intent.getLongExtra(CompanyListActivity.EXTRA_SELECTED_COMPANY,0);
            Company result = controller.retrieve(id);
            company.put(Company.COMPANY_KEYS.NAME, result.getName());
            company.put(Company.COMPANY_KEYS.URL, result.getUrl());
            company.put(Company.COMPANY_KEYS.PHONE, result.getPhone());
            company.put(Company.COMPANY_KEYS.EMAIL, result.getEmail());
            company.put(Company.COMPANY_KEYS.PRODUCTS, result.getProducts());
            company.put(Company.COMPANY_KEYS.CLASSIFICATION, String.valueOf(result.getClassification()));
            setViewsFromCompany();
        }

    }

    @Override
    protected void onDestroy() {
        controller.close();
        super.onDestroy();
    }

    private void buildCompanyFromViews(){
        String name = mNameEditText.getText().toString();
        String url = mUrlEditText.getText().toString();
        String phone = mPhoneEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String products = mProductsEditText.getText().toString();
        String classification = String.valueOf(mClassificationSpinner.getSelectedItemPosition());

        company.put(Company.COMPANY_KEYS.NAME, name);
        company.put(Company.COMPANY_KEYS.URL, url);
        company.put(Company.COMPANY_KEYS.PHONE, phone);
        company.put(Company.COMPANY_KEYS.EMAIL, email);
        company.put(Company.COMPANY_KEYS.PRODUCTS, products);
        company.put(Company.COMPANY_KEYS.CLASSIFICATION, classification);
    }

    private void setViewsFromCompany(){
        mNameEditText.setText(company.get(Company.COMPANY_KEYS.NAME));
        mUrlEditText.setText(company.get(Company.COMPANY_KEYS.URL));
        mPhoneEditText.setText(company.get(Company.COMPANY_KEYS.PHONE));
        mEmailEditText.setText(company.get(Company.COMPANY_KEYS.EMAIL));
        mProductsEditText.setText(company.get(Company.COMPANY_KEYS.PRODUCTS));
        mClassificationSpinner.setSelection(Integer.parseInt(company.get(Company.COMPANY_KEYS.CLASSIFICATION)));
    }

    public void saveChanges(View view){
        buildCompanyFromViews();
        if(label.equals(getText(R.string.new_label))){
            String message;
            if (controller.create(company)){
                String name = company.get(Company.COMPANY_KEYS.NAME);
                message = String.format(getString(R.string.company_persist_success), name);
                startActivity(new Intent(this, CompanyListActivity.class));
                finish();
            }else{
                message = getString(R.string.company_persist_error);
            }
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteCompany(View view){
        buildCompanyFromViews();
        /*DeleteFragment deleteFragment = new DeleteFragment();
        deleteFragment.setCompany(company);
        deleteFragment.show(getSupportFragmentManager(), "deleteSoftCompanies");*/
    }
}
