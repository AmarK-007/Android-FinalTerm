package com.android.assignment1.shoecart.fragments;
// Import necessary libraries and packages
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.airbnb.lottie.LottieAnimationView;
import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.interfaces.OnDialogClickListener;

// CustomAlertDialogFragment class that extends DialogFragment
public class CustomAlertDialogFragment extends DialogFragment {

    // Declare OnDialogClickListener variable
    private OnDialogClickListener listener;

    // Method to create a new instance of CustomAlertDialogFragment
    public static CustomAlertDialogFragment newInstance(OnDialogClickListener listener, String type) {
        CustomAlertDialogFragment fragment = new CustomAlertDialogFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        fragment.setArguments(args);
        fragment.listener = listener;
        return fragment;
    }

    // onStart method to set the dialog's dimensions and dim amount
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
        }
    }

    // onCreateDialog method to create the dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.fragment_custom_alert_dialog, null);
        builder.setView(view);
        LottieAnimationView animationView = view.findViewById(R.id.animation_view);
        TextView tvMessage = view.findViewById(R.id.success_message);

        // Retrieve the type from the bundle
        String type = getArguments().getString("type");

        // Handle different cases based on the type
        if (getResources().getString(R.string.order_placed).equals(type)) {
            animationView.setAnimation(R.raw.shoecart_orderplaced);
            tvMessage.setText(type);
        } else if (getResources().getString(R.string.add_to_cart).equals(type)) {
            animationView.setAnimation(R.raw.shoecart_addtocart);
            tvMessage.setText(type);
        }

        // Set onClickListener for the OK button
        view.findViewById(R.id.btnOk).setOnClickListener(v -> {
            listener.onDialogButtonClick();
            dismiss();
        });

        return builder.create();
    }

    // onActivityCreated method to animate the dialog
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Animate the dialog
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        }
    }
}