package com.android.assignment1.shoecart.activity;

import android.content.Intent;
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

public class LoginScreenActivity extends AppCompatActivity {
    private static final String TAG = LoginScreenActivity.class.getSimpleName();
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;

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

    private void performLogin(String username, String password) {
        UserDataSource userDataSource = new UserDataSource(this);
        if (userDataSource.validateUser(username, password)) {

            Utility.storeUser(userDataSource.getUser(username, password), this);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginScreenActivity.this, getString(R.string.msg_login_failed), Toast.LENGTH_SHORT).show();
        }
    }


}