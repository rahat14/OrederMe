package com.example.anull.orederme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;


public class HomePage extends AppCompatActivity {
            public Toolbar toolbar ;
            public FirebaseAuth  mAuth  ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mAuth = FirebaseAuth.getInstance() ;
        toolbar = (Toolbar)findViewById(R.id.main_toolber) ;

            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Title OF App");





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
