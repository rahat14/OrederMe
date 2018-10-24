package com.example.anull.orederme;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class HomePage extends AppCompatActivity {
            public Toolbar toolbar ;
            public FirebaseAuth  mAuth  ;
            Button searchFood ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("ভোজন বিলাস");



        mAuth = FirebaseAuth.getInstance() ;
        searchFood = findViewById(R.id.button_search_food) ;


            searchFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext() , Resturant_list.class) ;
                    startActivity(i);
                }
            });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu ,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sign_out:
                logout() ;
            return  true ;




            default:

                return  false ;






        }





    }

    public  void logout(){

        mAuth.signOut();
        sendtoLOgin();


    }

    public  void sendtoLOgin(){

        Intent i  = new Intent(getApplicationContext() , SignIn.class);
        startActivity(i);
        finish();




    }
}
