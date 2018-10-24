package com.example.anull.orederme;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button btnsignin , btnsignUp ;
    TextView txtslogan ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();



        btnsignin = (Button)findViewById(R.id.btnSignin) ;
        btnsignUp =(Button)findViewById(R.id.btnSignup) ;


        txtslogan= (TextView)findViewById(R.id.sloganTx);


        // listening to buttons
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext() ,SignIn.class) ;
                startActivity(i);



            }
        });
        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext() ,Register.class) ;
                startActivity(signup);
            }
        });



    }



    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currnetUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currnetUser == null){
            Intent i = new Intent(getApplicationContext()  , SignIn.class) ;
            startActivity(i);
            finish();


        }

    }
}
