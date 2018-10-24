package com.example.anull.orederme;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PostDetailActivity extends AppCompatActivity {

    TextView mTitleTv, mDetailTv, mPriceTv;
    ImageView mImageIv;


EditText quan ;


    Button mcartBtn, CheckoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_post_detail);
        mTitleTv = findViewById(R.id.titleTv);
        mDetailTv = findViewById(R.id.descriptionTv);
        mImageIv = findViewById(R.id.imageView);
        mcartBtn = findViewById(R.id.saveBtn);
        CheckoutBtn = findViewById(R.id.Check_Btn);
        mPriceTv = findViewById(R.id.PriceTv);
        quan =(EditText) findViewById(R.id.quantity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Food Menu");


        //get data from intent
        String image = getIntent().getStringExtra("image");
        final String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("description");
        final String price = getIntent().getStringExtra("price");





        //    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(desc);
        mPriceTv.setText(price);
        Picasso.get().load(image).into(mImageIv);


        mcartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  qunatity=quan.getText().toString();
                new Database(getBaseContext()).addToCart(new Order(

                      title,qunatity,price


                ));

                Toast.makeText(getApplicationContext() , "Food Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });


        CheckoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Cart.class);
                startActivity(i);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.check_out_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();


        if (id == R.id.menu_check_out_) {
            //display alert dialog to choose sorting
            sendtoCheckout();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public  void sendtoCheckout(){

        Intent i  = new Intent(getApplicationContext() , Cart.class);
        startActivity(i);




    }


}





