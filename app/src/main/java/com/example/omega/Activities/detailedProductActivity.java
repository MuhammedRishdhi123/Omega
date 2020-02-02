package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.omega.Model.cart;
import com.example.omega.Model.customer;
import com.example.omega.Model.product;
import com.example.omega.R;
import com.example.omega.prevalent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

public class detailedProductActivity extends AppCompatActivity {
    private Button addProductTocartbtn;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice,productDescription,productName;
    private Button shareBtn;
    private Long productId=0l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_product);
        productId=getIntent().getLongExtra("pid",0);

        numberButton=(ElegantNumberButton)findViewById(R.id.number);
        productImage=(ImageView)findViewById(R.id.detailedimage);
        productPrice=(TextView)findViewById(R.id.product_price);
        productDescription=(TextView)findViewById(R.id.product_description);
        productName=(TextView)findViewById(R.id.product_name);
        addProductTocartbtn=(Button)findViewById(R.id.addtoCartbtn);
        shareBtn=(Button)findViewById(R.id.shareproduct);


        getProductDetails(productId);

        addProductTocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              addingToCart();
            }
        });


        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product shareProd=product.findById(product.class,productId);
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String imageUrl=shareProd.getImageUrl().toString();
                intent.putExtra(Intent.EXTRA_SUBJECT,shareProd.getProductName());
                intent.putExtra(Intent.EXTRA_TEXT, shareProd.getFullDescription());
                intent.putExtra(Intent.EXTRA_TEXT,shareProd.getImageUrl());
                startActivity(Intent.createChooser(intent, "Choose method"));
            }
        });



    }

    private void addingToCart() {
        String savepDate;
        Long cartId=0l;
        Long prodId=0l;
        Long custId = prevalent.currentOnlineCustomer.getId();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat pDate = new SimpleDateFormat("MMM dd, yyyy");
        savepDate = pDate.format(c.getTime());
        product p = SugarRecord.findById(product.class, productId);//getting the product object
        customer customer = SugarRecord.findById(customer.class, custId);//getting the current customer object
        List<cart> cartcheck = cart.listAll(cart.class);//checking for the customers cart
        for(cart cart:cartcheck) {
            if (cart.getProducts().getId().equals(productId) && cart.getCustomer().getId().equals(custId) && cart.getStatus().equals("NOTPAID")) {
                cartId=cart.getId();//getting the product cart if it is already ordered
                prodId=cart.getProducts().getId();
            }
       }
        int orderQuantity=Integer.parseInt(numberButton.getNumber());
        if(orderQuantity>p.getStock()){
            Toast.makeText(getApplicationContext(),"Sorry we are out of stock",Toast.LENGTH_SHORT).show();
        }
        else{
            if(prodId.equals(p.getId())) {
                cart tempCart = cart.findById(cart.class, cartId);//getting the product cart
                tempCart.setQuantity(orderQuantity + tempCart.getQuantity());
                tempCart.setStatus("NOTPAID");
                tempCart.setTotal(tempCart.getQuantity() * p.getProductPrice());
                tempCart.save();
                Toast.makeText(getApplicationContext(),"Producted added to cart",Toast.LENGTH_SHORT).show();
            }
            else{
                cart tempCart=new cart();
                tempCart.setProducts(p);
                tempCart.setCustomer(customer);
                tempCart.setStatus("NOTPAID");
                tempCart.setQuantity(orderQuantity);
                tempCart.setTotal(orderQuantity*p.getProductPrice());
                tempCart.save();
                Toast.makeText(getApplicationContext(),"Producted added to cart",Toast.LENGTH_SHORT).show();
            }
            p.setStock(p.getStock()-orderQuantity);//updating the stocks after the order is placed
        }
    }

    private void getProductDetails(Long productId) {
        List<product> productList= product.listAll(product.class);

        for(product p:productList){
            if(productId==p.getId()){
                productName.setText(p.getProductName());
                productPrice.setText(Double.toString(p.getProductPrice()));
                productDescription.setText(p.getFullDescription());
                Picasso.get().load(p.getImageUrl()).into(productImage);
            }
        }
    }
}
