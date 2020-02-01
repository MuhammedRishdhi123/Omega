package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
            //   addingToCart();
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

//    private void addingToCart() {
//        String savepDate;
//        Long custId = prevalent.currentOnlineCustomer.getId();
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat pDate = new SimpleDateFormat("MMM dd, yyyy");
//        savepDate = pDate.format(c.getTime());
//        product p = SugarRecord.findById(product.class, productId);
//        customer cust = SugarRecord.findById(customer.class, custId);
//        List<cart> cartcheck = cart.executeQuery("select * from CART where ");
//        if (cartcheck.isEmpty()) {
//            cart cart = new cart();
//            cart.setProducts(new ArrayList<product>());
//            cart.getProducts().add(p);
//            cart.setCustomer(cust);
//            cart.setQuantity(Integer.parseInt(numberButton.getNumber()));
//            cart.setStatus("NOTPAID");
//            cart.save();
//        }
//    }

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
