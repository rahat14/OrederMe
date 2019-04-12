package com.example.anull.orederme;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;





public class EL_classico extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    LinearLayoutManager mLayoutManager; //for sorting
    SharedPreferences mSharedPref; //for saving sort settings
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    Toolbar toolbar ;
FirebaseRecyclerAdapter<Model , ViewHolder>firebaseRecyclerAdapter ;
FirebaseRecyclerOptions<Model>options ;



    private ProgressBar job_progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_el_classico);

        Toast toast = Toast.makeText(getApplicationContext(), "Wait For The  Loading To End" ,Toast.LENGTH_LONG);
        toast.show();



        job_progressBar = (ProgressBar) findViewById(R.id.progressbar_job_ad) ;
        job_progressBar.setVisibility(View.GONE);

        //Actionbar

        //set title
        mSharedPref = getSharedPreferences("SortSettings", MODE_PRIVATE);
        String mSorting = mSharedPref.getString("Sort", "newest"); //where if no settingsis selected newest will be default

        if (mSorting.equals("newest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means newest first
            mLayoutManager.setReverseLayout(true);
            mLayoutManager.setStackFromEnd(true);
        } else if (mSorting.equals("oldest")) {
            mLayoutManager = new LinearLayoutManager(this);
            //this will load the items from bottom means oldest first
            mLayoutManager.setReverseLayout(false);
            mLayoutManager.setStackFromEnd(false);
        }

        //RecyclerView
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);

        //set layout as LinearLayout


        //send Query to FirebaseDatabase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("El-classico");
        mRef.keepSynced(true);



showData();
    }


    private  void showData(){

        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(mRef , Model.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                holder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage()  , model.getPrice());

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);

                ViewHolder viewHolder = new ViewHolder(itemVIew);

                //itemClicklistener
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);



                        //get data from views
                        String mTitle = getItem(position).getTitle() ;                         //mTitleTv.getText().toString();
                        String mDesc = getItem(position).getDescription();                                  //mDescTv.getText().toString();
                        String mImage =  getItem(position).getImage();
                        String mprice = getItem(position).getPrice();


                        Intent intent = new Intent(getApplicationContext() , PostDetailActivity.class);




                        //put bitmap image as array of bytes
                        intent.putExtra("title", mTitle); // put title
                        intent.putExtra("description", mDesc); //put description
                        intent.putExtra("image",mImage); //put image
                        intent.putExtra("price",mprice);


                        startActivity(intent); //start activity
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });



                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    //search data
    private void firebaseSearch(String searchText) {

        //convert string entered in SearchView to lowercase
        String query = searchText;

        Query firebaseSearchQuery = mRef.orderByChild("title").startAt(query).endAt(query + "\uf8ff");


        options = new FirebaseRecyclerOptions.Builder<Model>().setQuery(firebaseSearchQuery , Model.class)
                .build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Model, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Model model) {
                holder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage()  , model.getPrice());

            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

                //INflate the row
                Context context;
                View itemVIew = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, viewGroup, false);

                ViewHolder viewHolder = new ViewHolder(itemVIew);

                //itemClicklistener
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Views
                        //   TextView mTitleTv = view.findViewById(R.id.rTitleTv);
                        //    TextView mDescTv = view.findViewById(R.id.rDescriptionTv);
                        //     ImageView mImageView = view.findViewById(R.id.rImageView);



                        //get data from views
                        String mTitle = getItem(position).getTitle() ;                         //mTitleTv.getText().toString();
                        String mDesc = getItem(position).getDescription();                                  //mDescTv.getText().toString();
                        String mImage =  getItem(position).getImage();
                        String mprice = getItem(position).getPrice();


                        Intent intent = new Intent(getApplicationContext() , PostDetailActivity.class);




                        //put bitmap image as array of bytes
                        intent.putExtra("title", mTitle); // put title
                        intent.putExtra("description", mDesc); //put description
                        intent.putExtra("image",mImage); //put image
                        intent.putExtra("price",mprice);


                        startActivity(intent); //start activity
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                });



                return viewHolder;
            }
        };

        mRecyclerView.setLayoutManager(mLayoutManager);
        firebaseRecyclerAdapter.startListening();
        //setting adapter

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

    }


    //load data into recycler view onStart
    @Override
    protected void onStart() {
        super.onStart();
                    if(firebaseRecyclerAdapter !=null){
                        firebaseRecyclerAdapter.startListening();
                    }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it present
        getMenuInflater().inflate(R.menu.sort_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Filter as you type
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //handle other action bar item clicks here
        if (id == R.id.action_sort) {
            //display alert dialog to choose sorting
            showSortDialog();
            return true;
        }

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showSortDialog() {
        //options to display in dialog
        String[] sortOptions = {" Newest", " Oldest"};
        //create alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sort by") //set title
                //.setIcon(R.drawable.ic_action_sort) //set icon
                .setItems(sortOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position of the selected item
                        // 0 means "Newest" and 1 means "oldest"
                        if (which == 0) {
                            //sort by newest
                            //Edit our shared preferences
                            SharedPreferences.Editor editor = mSharedPref.edit();
                            editor.putString("Sort", "newest"); //where 'Sort' is key & 'newest' is value
                            editor.apply(); // apply/save the value in our shared preferences
                            recreate(); //restart activity to take effect
                        } else if (which == 1) {
                            {
                                //sort by oldest
                                //Edit our shared preferences
                                SharedPreferences.Editor editor = mSharedPref.edit();
                                editor.putString("Sort", "oldest"); //where 'Sort' is key & 'oldest' is value
                                editor.apply(); // apply/save the value in our shared preferences
                                recreate(); //restart activity to take effect
                            }
                        }
                    }
                });
        builder.show();
    }





}