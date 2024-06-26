package com.android.assignment1.shoecart.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.db.UserDataSource;
import com.android.assignment1.shoecart.models.User;
import com.android.assignment1.shoecart.utils.Utility;

/**
 * This class is responsible for handling the signup screen activity.
 */
public class SignUpScreenActivity extends AppCompatActivity {
    private static final String TAG = SignUpScreenActivity.class.getSimpleName();
    private EditText firstnameEditText;
    private EditText lastnameEditText;
    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button signupButton;
    private TextView loginTextView;

    /**
     * This method is called when the activity is first created.
     * It sets the content view and initializes the first name, last name, username, email, password, signup button and login text view.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupscreen);

        firstnameEditText = findViewById(R.id.firstname);
        lastnameEditText = findViewById(R.id.lastname);
        usernameEditText = findViewById(R.id.username);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signupButton = findViewById(R.id.signup_btn);
        loginTextView = findViewById(R.id.loginTxt);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname = firstnameEditText.getText().toString();
                String lastname = lastnameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (firstname.isEmpty()) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_first_name_empty), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (lastname.isEmpty()) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_last_name_empty), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Utility.validateUserName(username)) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_not_valid_username), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Utility.validateSymbolsInUserName(username)) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_enter_alpha_numeric), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Utility.validateSpaceInUserName(username)) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_space_not_allowed), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Utility.validateEmail(email)) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_enter_valid_email), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Utility.validateIsPasswordEmpty(password)) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_password_field_cant_be_empty), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Utility.validatePassword(password)) {
                    Toast.makeText(SignUpScreenActivity.this, getString(R.string.msg_enter_min_5characters), Toast.LENGTH_SHORT).show();
                    return;
                }

                // If all validations pass, proceed with signup
                User user = new User();
                user.setName(firstname + " " + lastname); // 'name' is a combination of first and last names
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.setPurchaseHistory("");
                user.setShippingAddress1("");
                user.setShippingAddress2("");
                user.setCity("");
                user.setProvince("");
                user.setPincode("");

                UserDataSource userDataSource = new UserDataSource(SignUpScreenActivity.this);
                boolean success = userDataSource.insertUser(user);

                if (success) {
                    showAppSuccessAlert(SignUpScreenActivity.this);
                } else {
                    showAppFailureAlert(SignUpScreenActivity.this);
                }
            }
        });
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpScreenActivity.this, LoginScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    AlertDialog.Builder alertDialog;

    /**
     * This method is called when the user successfully signs up.
     * It displays an alert dialog with a success message.
     *
     * @param context
     */
    public void showAppSuccessAlert(final Context context) {
        alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        alertDialog.setTitle(Utility.getAppNameString(context));
        alertDialog.setMessage(getString(R.string.msg_signup_success));
        alertDialog.setPositiveButton(getString(R.string.button_ok),
                (dialog, which) -> {
                    dialog.dismiss();
                    alertDialog = null;
                    SignUpScreenActivity.this.finishAndRemoveTask();
                });
        alertDialog.setNegativeButton(getString(R.string.button_cancel), (dialog, which) -> {
            dialog.dismiss();
            alertDialog = null;
        });
        alertDialog.show();
    }

    /**
     * This method is called when the user fails to sign up.
     * It displays an alert dialog with a failure message.
     *
     * @param context
     */
    public void showAppFailureAlert(final Context context) {
        alertDialog = new AlertDialog.Builder(context, R.style.MyDialogTheme);
        alertDialog.setTitle(Utility.getAppNameString(context));
        alertDialog.setMessage(getString(R.string.msg_signup_failed));
        alertDialog.setPositiveButton(getString(R.string.button_ok),
                (dialog, which) -> {
                    dialog.dismiss();
                    alertDialog = null;
                });
        alertDialog.show();
    }
}