package com.android.assignment1.shoecart.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.assignment1.shoecart.R;

public class ProfileFragment extends Fragment {

    private String selectedProvince;
    TextView firstName, lastName, email, addressLine1, addressLine2, city, postalCode, province;
    ImageButton editButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        firstName = view.findViewById(R.id.profile_firstName);
        lastName = view.findViewById(R.id.profile_lastName);
        email = view.findViewById(R.id.profile_email);
        addressLine1 = view.findViewById(R.id.profile_address_firstLine);
        addressLine2 = view.findViewById(R.id.profile_address_secondLine);
        city = view.findViewById(R.id.profile_address_city);
        postalCode = view.findViewById(R.id.profile_address_postal);
        province = view.findViewById(R.id.profile_address_province);
        editButton = view.findViewById(R.id.update_address);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog updateDialog = new Dialog(getContext());
                updateDialog.setContentView(R.layout.update_address_dialog);

                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                        requireContext(),
                        R.array.canadian_provinces, // Array resource containing province names
                        R.layout.spinner_item // Custom spinner layout
                );
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                EditText updatedAddress1 = updateDialog.findViewById(R.id.update_address1);
                EditText updatedAddress2 = updateDialog.findViewById(R.id.update_address2);
                EditText updatedCity = updateDialog.findViewById(R.id.update_city);
                EditText updatedPostalCode = updateDialog.findViewById(R.id.update_postalCode);
                Spinner updatedProvince = updateDialog.findViewById(R.id.update_provinceSpinner);
                Button updateButton = updateDialog.findViewById(R.id.update_btn);

                updatedProvince.setAdapter(adapter);

                updatedProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectedProvince = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addressLine1.setText(updatedAddress1.getText().toString());
                        addressLine2.setText(updatedAddress2.getText().toString());
                        city.setText(updatedCity.getText().toString());
                        postalCode.setText(updatedPostalCode.getText().toString());
                        province.setText(selectedProvince);
                        updateDialog.dismiss();
                    }
                });
                updateDialog.show();
            }
        });
        return view;
    }
}