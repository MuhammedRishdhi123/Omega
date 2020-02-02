package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omega.Model.customer;
import com.example.omega.R;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText username_edt,email_edt,phoneNumber_edt,password_edt;
    Button register_btn,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username_edt=(EditText)findViewById(R.id.name);
        cancel=(Button)findViewById(R.id.cancel);
                    email_edt=(EditText)findViewById(R.id.email);
                    phoneNumber_edt=(EditText)findViewById(R.id.phone);
                    password_edt=(EditText)findViewById(R.id.password);
                    register_btn=(Button)findViewById(R.id.register);
                    register_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String username=username_edt.getText().toString();
                            String email=email_edt.getText().toString();
                            String phoneNumber=phoneNumber_edt.getText().toString();
                            String password=password_edt.getText().toString();

                            if(username.equals("")||email.equals("")||password.equals("")){
                                Toast.makeText(getApplicationContext(),"Please fill all fields",Toast.LENGTH_SHORT).show();
                            }
                            else{
                    Boolean checkMail=true;
                    List<customer> customerList=customer.listAll(customer.class);
                    for(customer c:customerList){
                        if(email.equals(c.getEmail())){
                            checkMail=false;
                        }
                    }
                    if(checkMail){
                        customer c=new customer(username,email,phoneNumber,password);
                        c.save();

                        Toast.makeText(getApplicationContext(),"Registered Sucessfully !",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Email is already existing in the System!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            RegisterActivity.super.onBackPressed();
                        }
                    });



    }
}
