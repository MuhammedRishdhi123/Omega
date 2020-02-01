package com.example.omega.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.omega.Model.product;
import com.example.omega.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class detailedProductActivity extends AppCompatActivity {
    private Button addProductTocartbtn;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView productPrice,productDescription,productName;
    private Long productId;

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


        getProductDetails(productId);

        addProductTocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addingToCart();
            }
        });
    }

    private void addingToCart() {
        String savepDate;
        Calendar c= Calendar.getInstance();
        SimpleDateFormat pDate=new SimpleDateFormat("MMM dd, yyyy");
        savepDate=pDate.format(c.getTime());

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
