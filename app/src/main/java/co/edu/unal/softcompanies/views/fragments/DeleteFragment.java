package co.edu.unal.softcompanies.views.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import java.util.HashMap;

import co.edu.unal.softcompanies.R;
import co.edu.unal.softcompanies.controllers.CompanyController;
import co.edu.unal.softcompanies.database.models.Company;
import co.edu.unal.softcompanies.views.activities.CompanyListActivity;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/4/17.
 * Universidad Nacional de Colombia
 */
public class DeleteFragment extends AppCompatDialogFragment {

    private HashMap<Company.COMPANY_KEYS, String> company;
    private CompanyController controller;

    public void setCompany(HashMap<Company.COMPANY_KEYS, String> company) {
        this.company = company;
    }

    public void setController(CompanyController controller) {
        this.controller = controller;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String name = company.get(Company.COMPANY_KEYS.NAME);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format("Are you sure you want to delete \"%s\" company?", name))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String message = getString(R.string.company_crud_error);
                        if(controller.delete(Long.parseLong(company.get(Company.COMPANY_KEYS.ID)))){
                            message = String.format("%s Deleted!", name);
                        }
                        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), CompanyListActivity.class));
                        getActivity().finish();
                    }
                })
                .setNegativeButton("No", null);
        return builder.create();
    }
}
