package com.android.assignment1.shoecart.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.interfaces.OnDialogClickListener;

public class CustomAlertDialogFragment extends DialogFragment {

    private OnDialogClickListener listener;

    public static CustomAlertDialogFragment newInstance(OnDialogClickListener listener) {
        CustomAlertDialogFragment fragment = new CustomAlertDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            // Get the screen's dimensions
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

            // Calculate the desired width and height
            int width = (int) (displayMetrics.widthPixels * 0.85);
            int height = (int) (displayMetrics.heightPixels * 0.85);

            dialog.getWindow().setLayout(width, height);

            // Set the background's dim amount
            dialog.getWindow().setDimAmount(0.5f);

            // Set the animation
            dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.fragment_custom_alert_dialog, null);
        builder.setView(view);

        view.findViewById(R.id.btnOk).setOnClickListener(v -> {
            listener.onDialogButtonClick();
            dismiss();
        });

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Animate the dialog
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }
    }
}
