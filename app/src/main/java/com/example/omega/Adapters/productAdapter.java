package com.example.omega.Adapters;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omega.Interfaces.itemClickListener;
import com.example.omega.Model.product;
import com.example.omega.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class productAdapter extends RecyclerView.Adapter<productAdapter.ViewHolder> {
 public ArrayList<product> productList;
 public Context context;

    public productAdapter(ArrayList<product> productList,Context context) {
        this.productList=productList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_layout,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final product prod=productList.get(position);
        holder.productName.setText(prod.getProductName());
        holder.desc.setText(prod.getProductDescription());
        holder.price.setText(Double.toString(prod.getProductPrice()));
        Picasso.get().load(prod.getImageUrl()).into(holder.productImage);

        holder.cardView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Long id=prod.getId();
                Bundle bundle=new Bundle();
                bundle.putLong("productId",id);

            }
        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView productName,desc,price;
        public ImageView productImage;
        public itemClickListener listener;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage=(ImageView)itemView.findViewById(R.id.product_image);
            productName=(TextView)itemView.findViewById(R.id.product_name);
            desc=(TextView)itemView.findViewById(R.id.product_description);
            price=(TextView)itemView.findViewById(R.id.product_price);
            cardView=(CardView)itemView.findViewById(R.id.cardView);
        }


        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition(),false);

        }
    }

    public void Refresh(List<product> productList){
        this.productList=new ArrayList<>();
        this.productList.addAll(productList);
        notifyDataSetChanged();

    }
}
