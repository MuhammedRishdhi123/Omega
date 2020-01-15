package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omega.Model.customer;
import com.example.omega.R;


import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText userEmail,password;
    Button login;
    TextView register;
    TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(TextView)findViewById(R.id.register_link);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail1=userEmail.getText().toString();
                String password1=password.getText().toString();
                if(userEmail1.equals("")||password1.equals("")){
                    Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    List<customer> customerList = customer.listAll(customer.class);
                    Boolean found=false;
                    for (customer c : customerList) {
                        if (userEmail1.equals(c.getEmail()) && password1.equals(c.getPassword())) {
                            found=true;
                           // Intent home = new Intent(LoginActivity.this, HomeActivity.class);
                            //startActivity(home);
                        }

                    }
                    if(!found) {
                        Toast.makeText(getApplicationContext(), "Email and password doesn't match!", Toast.LENGTH_SHORT).show();
                        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
                        forgotPassword.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerActivity);
            }
        });
    }
}
