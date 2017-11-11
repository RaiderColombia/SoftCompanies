package co.edu.unal.softcompanies.views.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.widget.Toast;

import co.edu.unal.softcompanies.R;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/4/17.
 * Universidad Nacional de Colombia
 */
public class ExitFragment  extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.exit_message)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getActivity().getApplicationContext(), "See you soon!", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    }
                })
                .setNegativeButton("No", null);
        return builder.create();
    }
}
