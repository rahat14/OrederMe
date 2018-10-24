package com.example.anull.orederme;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class  CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_cart_name , txt_cart_price ,txt_quantity  ;

    public void setTxt_cart_name(TextView txt_cart_name) {
        this.txt_cart_name = txt_cart_name;
    }

    public void setTxt_cart_price(TextView txt_cart_price) {
        this.txt_cart_price = txt_cart_price;
    }

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
            txt_cart_name = (TextView)itemView.findViewById(R.id.itemName_cart) ;
            txt_cart_price = (TextView)itemView.findViewById(R.id.itemPrice_cart);
            txt_quantity = (TextView)itemView.findViewById(R.id.show_quantity);


    }

    @Override
    public void onClick(View v) {

    }
}




public class CartAdapter extends  RecyclerView.Adapter<CartViewHolder>{


    private List<Order> listData = new ArrayList<>() ;
    private Context context ;

    public CartAdapter(List<Order> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.cart_layout, viewGroup  , false);
        return  new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder cartViewHolder, int i) {

       TextDrawable drawble = TextDrawable.builder()
               .buildRound(""+listData.get(i).getProductQuantity(),Color.RED);

        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(listData.get(i).getPrice()))*(Integer.parseInt(listData.get(i).getProductQuantity()));

        cartViewHolder.txt_cart_price.setText(fmt.format(price));
        cartViewHolder.txt_cart_name.setText(listData.get(i).getProductName());
        cartViewHolder.txt_quantity.setText(listData.get(i).getProductQuantity());


    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
}
