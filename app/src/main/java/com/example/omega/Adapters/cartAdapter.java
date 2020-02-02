package com.example.omega.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omega.Activities.cartActivity;
import com.example.omega.Interfaces.itemClickListener;
import com.example.omega.Model.cart;
import com.example.omega.Model.product;
import com.example.omega.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class cartAdapter extends RecyclerView.Adapter<cartAdapter.ViewHolder> {
    public List<cart> cartList;
    public Context context;


    public cartAdapter (List<cart> cartList,Context context) {
        this.cartList=cartList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final cart cart1=cartList.get(position);
        holder.name.setText(cart1.getProducts().getProductName());
        holder.quantity.setText("Quantity :"+String.valueOf(cart1.getQuantity()));
        holder.price.setText("Price :"+Double.toString(cart1.getProducts().getProductPrice()));


        holder.delete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cart1.setStatus("Cancelled");
                product product1=product.findById(product.class,cart1.getProducts().getId());
                product1.setStock(cart1.getQuantity()+product1.getStock());
                product1.save();
                cart1.save();
                ((Activity)context).finish();

                //Intent cartIntent=new Intent(context, cartActivity.class);
                context.startActivity(((Activity) context).getIntent());

            }
        });

    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView image;
        public TextView quantity,name,price;
        public Button delete;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.productCartname);
            price=(TextView)itemView.findViewById(R.id.productCartprice) ;
            quantity=(TextView)itemView.findViewById(R.id.cartProductquantity);
            delete=(Button)itemView.findViewById(R.id.delete);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
        }


        @Override
        public void onClick(View view) {

        }
    }


}

