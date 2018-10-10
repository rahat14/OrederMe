package com.example.anull.orederme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private FirebaseAuth mauth ;
    Button  register_btn  ;
    EditText email , password  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mauth = FirebaseAuth.getInstance();

        register_btn = (Button)findViewById(R.id.register_button);
        email =(EditText)findViewById(R.id.register_email);
        password = (EditText)findViewById(R.id.register_password) ;

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String  register_email =  email.getText().toString() ;
                String   register_pass = password.getText().toString() ;

                if(!TextUtils.isEmpty(register_email) && !TextUtils.isEmpty(register_pass)){




                mauth.createUserWithEmailAndPassword(register_email , register_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if(task.isSuccessful()){

                            Intent i = new Intent(getApplicationContext()  , HomePage.class) ;
                            startActivity(i);
                            finish();



                        }
                        else
                            {
                            String e  = task.getException().getMessage() ;
                        Toast.makeText(getApplicationContext() , "Error "+ e  , Toast.LENGTH_LONG).show();


                    }
                    }
                });





                }






            }
        });



    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser  user = mauth.getCurrentUser();
        if(user != null){
            Intent i = new Intent(getApplicationContext()  , HomePage.class) ;
            startActivity(i);
            finish();


        }
    }
}



