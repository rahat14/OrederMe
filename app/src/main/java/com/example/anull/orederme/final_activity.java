package com.example.anull.orederme;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class final_activity extends AppCompatActivity {
        Database myDb ;
    TextView User_gmail ;
    String email;
    private  FirebaseAuth mAuth ;
    Button addmore , placeOreder ;
    EditText address , phn ;
    String order_address , order_phn_num ;
    List<Order> cart = new ArrayList<>() ;
    DatabaseReference database ;


    CartAdapter adapter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_activity);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference("orderList");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
         email = user.getEmail();






        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Finalize Order");

        loadListFood();

        User_gmail = (TextView)findViewById(R.id.user_gmail);
        addmore=(Button)findViewById(R.id.adD_more);
        placeOreder = (Button)findViewById(R.id.PLaCE_oreder);
        address= (EditText)findViewById(R.id.adress_final);
        phn = (EditText)findViewById(R.id.phone_final);

        User_gmail.setText(email);


        placeOreder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Resturant_list.class);
                startActivity(i);
            }
        });


        addmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ProductName ;
                String quantity ;

                order_address = address.getText().toString();
                order_phn_num = phn.getText().toString();
                orderUpload OrderUpload = new orderUpload(


                        email , order_phn_num , order_address ,
                        cart

                );
                String orderuploadId= database.push().getKey();
                database.child(orderuploadId).setValue(OrderUpload);
                new  Database(getApplicationContext()).cleanCart();

                showAlertDialogButtonClicked(v);



            }
        });








    }


    public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Order Status");
        builder.setMessage("Your Order is Recieved\nThanks For Choosing Us");
        builder.setPositiveButton("Return To Mainmenu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

               Intent i = new Intent(getApplicationContext(), HomePage.class);
               startActivity(i);

            }
        });


        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private  void loadListFood(){
        cart = new Database(this ).getCarts() ;
        adapter = new CartAdapter(cart,this ) ;


/// calculate total price









    }































}









