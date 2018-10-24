package com.example.anull.orederme;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cart extends AppCompatActivity {
        RecyclerView recyclerView ;
        RecyclerView.LayoutManager layoutManager ;
        DatabaseReference request ;
        TextView txtTotalPrice ;
        Button  place_order ;
        List<Order> cart = new ArrayList<>() ;


        CartAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cart");



        recyclerView = (RecyclerView)findViewById(R.id.listCart);
       recyclerView.setHasFixedSize(true);
       layoutManager= new LinearLayoutManager(this );
       recyclerView.setLayoutManager(layoutManager);

       txtTotalPrice = (TextView)findViewById(R.id.priceView)  ;
       place_order = (Button)findViewById(R.id.order_place_button);





            loadListFood();


            place_order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    showAlertDialogButtonClicked(v);



                }
            });







    }

    private  void loadListFood(){
cart = new Database(this ).getCarts() ;
adapter = new CartAdapter(cart,this ) ;
recyclerView.setAdapter(adapter);


/// calculate total price

        int totlal  = 0 ;
        for(Order order:cart)
            totlal+=(Integer.parseInt(order.getPrice()))*(Integer.parseInt(order.getProductQuantity()));
        Locale locale = new Locale("en", "US");
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        txtTotalPrice.setText(fmt.format(totlal));







    }



        public void showAlertDialogButtonClicked(View view) {

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Payment Method");
            builder.setMessage("Please Set Your Payment Method");



            // add the buttons

            builder.setNegativeButton("Cash On Delivery", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // do something like...
                    Intent i = new Intent(getApplicationContext() , final_activity.class);
                    startActivity(i);

                }
            });

/*
            builder.setNeutralButton("Bkash", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // do something like...
                    Toast.makeText(getApplicationContext() , "It Is Not Available At This Moment", Toast.LENGTH_SHORT).show();

                }
            });
            */
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // do something like...
                    dialog.cancel();

                }
            });


            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }









}
