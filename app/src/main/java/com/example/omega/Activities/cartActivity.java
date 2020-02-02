package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.omega.Adapters.cartAdapter;
import com.example.omega.Model.cart;
import com.example.omega.R;
import com.example.omega.prevalent;

import java.util.List;

import static java.security.AccessController.getContext;

public class cartActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private cartAdapter adapters;
    double total;
    TextView numberOfitems;
    TextView totalCost;
    int quantity;
    RecyclerView.LayoutManager layoutManager;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        checkout = (Button) findViewById(R.id.checkoutbtn);
        numberOfitems=(TextView)findViewById(R.id.numOfItems);
        totalCost=(TextView)findViewById(R.id.totalAmountCart);
        recyclerView = (RecyclerView)findViewById(R.id.productCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<cart> listOfCarts = cart.findWithQuery(cart.class, "Select * from cart where customer=? and status=?",prevalent.currentOnlineCustomer.getId().toString(),"NOTPAID");
        adapters = new cartAdapter(listOfCarts, this);
        recyclerView.setAdapter(adapters);


        for (cart cart1 :listOfCarts) {//To get the total amount and total number of items in checkout
             total += ((cart1.getProducts().getProductPrice()) * (cart1.getQuantity()));
             quantity += cart1.getQuantity();

        }

       numberOfitems.setText("No of items:"+String.valueOf(quantity));//total number of items in cart
        totalCost.setText("Total :"+String.valueOf(total));//here we set the total amount and the no of items in the checkout menu




    }
}
