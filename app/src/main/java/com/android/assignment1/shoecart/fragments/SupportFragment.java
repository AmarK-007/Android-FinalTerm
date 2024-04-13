package com.android.assignment1.shoecart.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.utils.Utility;

public class SupportFragment extends Fragment {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText issueEditText;
    private Button submitButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_support, container, false);

        nameEditText = view.findViewById(R.id.support_name);
        emailEditText = view.findViewById(R.id.support_email);
        issueEditText = view.findViewById(R.id.issue_text);
        submitButton = view.findViewById(R.id.support_btn);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String issue = issueEditText.getText().toString();

                if(Utility.validateIsNameEmpty(name)){
                    Toast.makeText(requireContext(), "Name is Empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!Utility.validateEmail(email)){
                    Toast.makeText(requireContext(), "Invalid Email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Utility.validateIsTextAreaEmpty(issue)){
                    Toast.makeText(requireContext(), "Your Issue is Empty", Toast.LENGTH_SHORT).show();
                }
                showSupportRequestSendDialog(requireContext());

            }
        });
        return view;
    }
    public void emptyAllFields() {
        nameEditText.setText("");
        emailEditText.setText("");
        issueEditText.setText("");
    }

    AlertDialog.Builder alertDialog;
    public void showSupportRequestSendDialog(final Context context){
        alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        alertDialog.setMessage(R.string.request_message);
        alertDialog.setNeutralButton("Ok", (dialog, which) -> {
            dialog.dismiss();
            emptyAllFields();
        });
        alertDialog.show();
    }


}