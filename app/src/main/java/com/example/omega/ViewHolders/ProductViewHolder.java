package com.example.omega.ViewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.omega.Interfaces.itemClickListener;
import com.example.omega.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView productName,desc,price;
    public ImageView productImage;
    public itemClickListener listener;
    CardView cardView;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productImage=(ImageView)itemView.findViewById(R.id.product_image);
        productName=(TextView)itemView.findViewById(R.id.product_name);
        desc=(TextView)itemView.findViewById(R.id.product_description);
        price=(TextView)itemView.findViewById(R.id.product_price);
        cardView=(CardView)itemView.findViewById(R.id.cardView);
    }
    public void setItemClickListener(itemClickListener listener){
        this.listener=listener;
    }


    @Override
    public void onClick(View view) {
        listener.onClick(view,getAdapterPosition(),false);

    }
}