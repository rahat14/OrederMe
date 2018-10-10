package com.example.anull.orederme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {
    EditText email , password  ;
    Button login_button ;
    ProgressBar mbar ;
    TextView reg_btn  ;
    private  FirebaseAuth mAuth ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance() ;
        //connecting place holders
        email = (EditText)findViewById(R.id.Sign_edit_email) ;
        password = (EditText)findViewById(R.id.edit_Sign_password) ;
        login_button = (Button)findViewById(R.id.sign_in_button) ;
        reg_btn = (TextView)findViewById(R.id.signUP_Backlink);

        mbar =(ProgressBar)findViewById(R.id.singInBar) ;  // progress bar

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext()  , Register.class) ;
                startActivity(i);
                finish();
            }
        });

        // setting up click listener
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  login_email =  email.getText().toString() ;
                String   login_pass = password.getText().toString() ;

                if(!TextUtils.isEmpty(login_email) && !TextUtils.isEmpty(login_pass)){
                    // setting the bar the is Visible


                    mAuth.signInWithEmailAndPassword(login_email,login_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent ii = new Intent(getApplicationContext()  , HomePage.class );
                                startActivity(ii);
                                finish();


                            }
                            else {
                                String e  = task.getException().getMessage() ;
                                Toast.makeText(getApplicationContext() , "Error "+ e  , Toast.LENGTH_LONG).show();

                            }

                            mbar.setVisibility(View.INVISIBLE);

                        }
                    });

                }
                else {

                    Toast.makeText(getApplicationContext() , "Please Enter The Email & Password" , Toast.LENGTH_LONG);
                }



            }
        });





    }

    @Override
    protected void onStart() {
        super.onStart();
// checking user is logged in the session or not  ;
        FirebaseUser CurrentUser = mAuth.getCurrentUser() ;
        if(CurrentUser != null){
            Intent i = new Intent(getApplicationContext()  , HomePage.class) ;
            startActivity(i);
            finish();


        }


    }
}
