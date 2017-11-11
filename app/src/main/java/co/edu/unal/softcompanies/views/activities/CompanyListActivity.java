package co.edu.unal.softcompanies.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import co.edu.unal.softcompanies.R;
import co.edu.unal.softcompanies.controllers.CompanyController;
import co.edu.unal.softcompanies.database.models.Company;
import co.edu.unal.softcompanies.views.fragments.ExitFragment;

public class CompanyListActivity extends AppCompatActivity {

    public static final String EXTRA_OPERATION_COMPANY = "co.edu.unal.softcompanies.COMPANY_OPERATION";
    public static final String EXTRA_SELECTED_COMPANY = "co.edu.unal.softcompanies.COMPANY_SELECTED";

    private ArrayList<Company> companiesList;
    private ArrayAdapter<Company> companyArrayAdapter;

    private CompanyController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView companiesListView = findViewById(R.id.companiesListView);
        controller = new CompanyController(getApplicationContext());

        companiesList = controller.retrieveAll();
        companyArrayAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                companiesList);
        companiesListView.setAdapter(companyArrayAdapter);

        companiesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Company company = (Company) companiesListView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), CompanyActivity.class);
                intent.putExtra(EXTRA_OPERATION_COMPANY, getString(R.string.update_label));
                intent.putExtra(EXTRA_SELECTED_COMPANY, company.getId());
                startActivity(intent);
            }
        });

        final EditText filterEditText = findViewById(R.id.filterEditText);
        filterEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                updateCompaniesList(controller.retrieveAllByName(filterEditText.getText().toString()));
            }
        });
    }

    private void updateCompaniesList(ArrayList<Company> companies){
        companiesList = companies;
        companyArrayAdapter.clear();
        for (Company company : companies) {
            companyArrayAdapter.add(company);
        }
        companyArrayAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        controller.close();
        super.onDestroy();
    }

    @Override
    public void onRestart(){
        super.onRestart();
        companyArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        new ExitFragment().show(getSupportFragmentManager(), "exitSoftCompanies");
    }

    public void newCompany(View view){
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra(EXTRA_OPERATION_COMPANY, getString(R.string.new_label));
        startActivity(intent);
    }
}
