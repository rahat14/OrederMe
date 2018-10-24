package com.example.anull.orederme;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Resturant_list extends AppCompatActivity {
        Toolbar toolbar ;
        CardView el_classico ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resturant_list);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ভোজন বিলাস");

        el_classico = (CardView)findViewById(R.id.elclassio_btn);




        el_classico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  in = new Intent(getApplicationContext() ,EL_classico.class) ;
                startActivity(in);

            }
        });








    }

}
