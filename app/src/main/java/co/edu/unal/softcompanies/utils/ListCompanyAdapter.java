package co.edu.unal.softcompanies.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.unal.softcompanies.R;
import co.edu.unal.softcompanies.database.models.Company;

/**
 * SoftCompanies
 * Created by Jhon Ramirez on 11/11/17.
 * Universidad Nacional de Colombia
 */
public class ListCompanyAdapter extends ArrayAdapter<Company> {

    private ArrayList<Company> companies;
    private Context context;

    public ListCompanyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Company> objects) {
        super(context, resource, objects);
        this.context = context;
        companies = objects;
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        String[] classifications = context.getResources().getStringArray(R.array.classification_company_array);

        View view;
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            assert inflater != null;
            view = inflater.inflate(R.layout.company_item_layout,null);
        }else{
            view = convertView;
        }

        TextView companyNameText = view.findViewById(R.id.companyNameText);
        TextView companyClassificationText = view.findViewById(R.id.companyClassificationText);

        companyNameText.setText(companies.get(position).getName());
        companyClassificationText.setText(classifications[companies.get(position).getClassification()]);
        return view;
  }
}
