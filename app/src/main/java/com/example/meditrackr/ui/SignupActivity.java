package com.example.meditrackr.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.meditrackr.R;

public class SignupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        final EditText username = (EditText) findViewById(R.id.username);
        final Button login = (Button) findViewById(R.id.login_button);
        final Button signup = (Button) findViewById(R.id.signup_button);
        //final Spinner spinnerRegister = (Spinner) findViewById(R.id.spinner_register);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                //R.array.user_options, android.R.layout.simple_spinner_item);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinnerRegister.setAdapter(adapter);
    }

}
