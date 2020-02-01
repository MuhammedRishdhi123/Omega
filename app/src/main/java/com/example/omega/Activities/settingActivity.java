package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.omega.Model.customer;
import com.example.omega.R;
import com.example.omega.prevalent;
import com.orm.SugarRecord;

import java.util.List;

public class settingActivity extends AppCompatActivity {

    EditText username, phone,email,password;
    Button updatebtn;
    String isEmailChanged="";
    Boolean isEmailExist=false;
    List<customer> customers;
    Long custId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        username=(EditText)findViewById(R.id.set_username);
        phone=(EditText)findViewById(R.id.set_phone);
        email=(EditText)findViewById(R.id.set_email);
        password=(EditText)findViewById(R.id.set_password1);
        updatebtn=(Button)findViewById(R.id.update);

        displayUserInfo(username,phone,email,password);
        customers=customer.listAll(customer.class);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (customer cust:customers) {
                    if(prevalent.currentOnlineCustomer.getId()== cust.getId()){
                        if(isEmailChanged.equals(email.getText().toString())) {
                            customer custom = SugarRecord.findById(customer.class, cust.getId());
                            custom.setUsername(username.getText().toString());
                            custom.setPhoneNumber(phone.getText().toString());
                            custom.setEmail(email.getText().toString());
                            custom.setPassword(password.getText().toString());
                            isEmailChanged=email.getText().toString();
                            custom.save();
                            prevalent.currentOnlineCustomer = custom;
                            Toast.makeText(getApplicationContext(), "Successfully updated details", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            validateEmailSave(email);
                            if(isEmailExist){
                                Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                customer custom = SugarRecord.findById(customer.class, cust.getId());
                                custom.setUsername(username.getText().toString());
                                custom.setPhoneNumber(phone.getText().toString());
                                custom.setEmail(email.getText().toString());
                                custom.setPassword(password.getText().toString());
                                custom.save();
                                prevalent.currentOnlineCustomer = custom;
                                Toast.makeText(getApplicationContext(), "Successfully updated details", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                }
            }
        });


    }

    private void displayUserInfo(EditText username, EditText phone, EditText email, EditText password) {
        customer c=prevalent.currentOnlineCustomer;
        username.setText(c.getUsername());
        phone.setText(c.getPhoneNumber());
        email.setText(c.getEmail());
        password.setText(c.getPassword());
        isEmailChanged=c.getEmail();


    }


    private void validateEmailSave(EditText email) {
        List<customer> customerList=customer.listAll(customer.class);
        for(customer c :customerList){
            if(c.getEmail().equals(email.getText().toString())){
                isEmailExist=true;
            }
        }
    }


}
