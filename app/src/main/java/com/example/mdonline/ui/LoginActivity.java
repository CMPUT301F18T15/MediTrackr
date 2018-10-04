package com.example.mdonline.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mdonline.Patient;
import com.example.mdonline.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ConstraintLayout loginHeader = (ConstraintLayout) findViewById(R.id.sign_in_header);
        ConstraintLayout registerHeader = (ConstraintLayout) findViewById(R.id.register_header);
        final EditText registerUser = (EditText) findViewById(R.id.register_username);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText email = (EditText) findViewById(R.id.username);
        final EditText phone = (EditText) findViewById(R.id.phone);
        final Button login = (Button) findViewById(R.id.login_button);
        final Button registerButton = (Button) findViewById(R.id.register_button);
        final Spinner spinnerRegister = (Spinner) findViewById(R.id.spinner_register);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegister.setAdapter(adapter);








        loginHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLogin(true);
            }
        });
        registerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLogin(false);
            }
        });

    }

    private void toggleLogin(boolean login){
        ConstraintLayout loginHeader = findViewById(R.id.sign_in_header);
        ConstraintLayout registerHeader = findViewById(R.id.register_header);
        ConstraintLayout.LayoutParams loginParams = (ConstraintLayout.LayoutParams) loginHeader.getLayoutParams();
        ConstraintLayout.LayoutParams registerParams = (ConstraintLayout.LayoutParams) registerHeader.getLayoutParams();
        ConstraintLayout loginLayout = findViewById(R.id.sign_in);
        ConstraintLayout registerLayout = findViewById(R.id.register);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int px = 4 * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);

        if(login){
            loginHeader.setBackgroundColor(Color.BLACK);
            registerHeader.setBackgroundColor(Color.parseColor("#555555"));
            loginParams.setMargins(0,0,0,0);
            registerParams.setMargins(0,px,0,0);
            loginLayout.setVisibility(View.VISIBLE);
            registerLayout.setVisibility(View.INVISIBLE);

        }
        else {
            registerHeader.setBackgroundColor(Color.BLACK);
            loginHeader.setBackgroundColor(Color.parseColor("#555555"));
            registerParams.setMargins(0,0,0,0);
            loginParams.setMargins(0,px,0,0);
            registerLayout.setVisibility(View.VISIBLE);
            loginLayout.setVisibility(View.INVISIBLE);
        }
        loginHeader.setLayoutParams(loginParams);
        registerHeader.setLayoutParams(registerParams);
    }
}
