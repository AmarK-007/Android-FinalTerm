package com.android.assignment1.shoecart.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.assignment1.shoecart.R;
import com.android.assignment1.shoecart.db.UserDataSource;
import com.android.assignment1.shoecart.utils.Utility;

/** This class is responsible for handling the login screen activity.
 * */
 public class LoginScreenActivity extends AppCompatActivity {
    private static final String TAG = LoginScreenActivity.class.getSimpleName();
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

    /** This method is called when the activity is first created.
     * It sets the content view and initializes the username, password and login button.
     * @param savedInstanceState
     */
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_btn);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (!Utility.validateUserName(username)) {
                    Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_not_valid_username), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Utility.validateSymbolsInUserName(username)) {
                    Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_enter_alpha_numeric), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Utility.validateSpaceInUserName(username)) {
                    Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_space_not_allowed), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Utility.validateIsPasswordEmpty(password)) {
                    Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_password_field_cant_be_empty), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Utility.validatePassword(password)) {
                    Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_enter_min_5characters), Toast.LENGTH_SHORT).show();
                    return;
                }

                // If all validations pass, proceed with login
                performLogin(username, password);
            }
        });

        TextView signUpButton = findViewById(R.id.signUpTxt);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreenActivity.this, SignUpScreenActivity.class);
                startActivity(intent);
            }
        });
    }

    /** This method is called when the user clicks on the login button.
     * It validates the user credentials and logs the user in if the credentials are correct.
     * @param username
     * @param password
     */
 private void performLogin(String username, String password) {
        UserDataSource userDataSource = new UserDataSource(this);
        if (userDataSource.validateUser(username, password)) {

            SharedPreferences sharedPreferences = getSharedPreferences("Login_Username", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName", username);
            editor.apply();
            Utility.storeUser(userDataSource.getUser(username, password), this);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_login_failed), Toast.LENGTH_SHORT).show();
        }
    }


}