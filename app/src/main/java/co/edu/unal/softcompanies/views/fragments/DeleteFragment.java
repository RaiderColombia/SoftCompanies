package co.edu.unal.softcompanies.views.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import co.edu.unal.softcompanies.views.activities.CompanyListActivity;
import co.edu.unal.softcompanies.database.models.Company;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/4/17.
 * Universidad Nacional de Colombia
 */
public class DeleteFragment extends AppCompatDialogFragment {

    private Company company;

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(String.format("Are you sure you want to delete %s company?", company.getName()))
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity().getApplicationContext(), String.format("%s Deleted!", company.getName()), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), CompanyListActivity.class));
                        getActivity().finish();
                    }
                })
                .setNegativeButton("No", null);
        return builder.create();
    }
}
