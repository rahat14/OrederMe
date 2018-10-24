package com.example.anull.orederme;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class final_activity extends AppCompatActivity {

    TextView User_gmail ;
    private  FirebaseAuth mAuth ;
    Button addmore , placeOreder ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_activity);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = user.getEmail();



        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle("Finalize Order");

        User_gmail = (TextView)findViewById(R.id.user_gmail);
        addmore=(Button)findViewById(R.id.adD_more);
        placeOreder = (Button)findViewById(R.id.PLaCE_oreder);


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





























    }









