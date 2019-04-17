package com.example.recyclerx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerx.Activity.Calculator;
import com.example.recyclerx.Activity.LoginActivity;
import com.example.recyclerx.Activity.ProfileActivity;
import com.example.recyclerx.Adapter.ProductAdapter;
import com.example.recyclerx.Model.MyListData;
import com.example.recyclerx.Model.ProductData;
//import com.example.recyclerx.Adapter.ProductA;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.recyclerx.Model.Variable.*;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Home Page Toolbar;
    private Toolbar tb;

    //navigation Drawer;
    private DrawerLayout mdrawerlayout;
    private ActionBarDrawerToggle mToggle;

    private LinearLayout calculator;
    private ImageView guest, login;



    //Navigation View;
    private NavigationView navigationView;
    private ImageView guestLogin,normalLogin;
    private TextView gestTV,loginTV;
    //  private LinearLayout guestLogin,normalLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        onClick();


        try{

            mAuth = FirebaseAuth.getInstance();
            myDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber());

        }catch (Exception e)
        {

        }

        //Home Page Toolbar;
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);




        //Add backbutton

        getSupportActionBar().setTitle("Popular");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //navigation Drawer;
        mdrawerlayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mdrawerlayout,R.string.open,R.string.close);
        mdrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();

        //Navigation View;
        navigationView = findViewById(R.id.nav_id);
        navigationView.setNavigationItemSelectedListener(this);
     //   recyclerViewInit();


        recyclerViewProductInit();



    }

    private void onClick() {
        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Calculator.class));
            }
        });

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(mAuth.getCurrentUser() != null) {


                    logOutClassMethod();
                }
                else
                {
                    loginClassMethod();
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mAuth.getCurrentUser() != null) {
                    logOutClassMethod();
                }
                else
                {
                    loginClassMethod();

                }

            }
        });


    }

    private void init()
    {


        gestTV = findViewById(R.id.guest_TVId);
        loginTV = findViewById(R.id.login_TVId);
        guest = findViewById(R.id.guest_login_id);
        login = findViewById(R.id.login_id);

        calculator = findViewById(R.id.calculator_id);
    }

    private void recyclerViewProductInit() {

        ProductData[] productData = new ProductData[]
                {
                        new ProductData(R.drawable.product_icon_3,"Product Name ", "15kg","$16"),
                        new ProductData(R.drawable.product_icon_5,"Product Name ", "14pic","$160"),
                        new ProductData(R.drawable.apple,"Product Name ", "4pic","$20"),
                        new ProductData(R.drawable.product_icon_1,"Product Name ", "4pic","$20"),
                        new ProductData(R.drawable.product_icon_2,"Product Name ", "4pic","$10"),
                        new ProductData(R.drawable.product_icon_4,"Product Name ", "1pic","$60"),
                        new ProductData(R.drawable.product_icon_1,"Product Name ", "4pic","$20"),
                        new ProductData(R.drawable.product_icon_2,"Product Name ", "4pic","$10"),
                        new ProductData(R.drawable.product_icon_3,"Product Name ", "15kg","$16"),
                        new ProductData(R.drawable.product_icon_4,"Product Name ", "1pic","$60"),
                        new ProductData(R.drawable.product_icon_5,"Product Name ", "14pic","$160"),
                        new ProductData(R.drawable.apple,"Product Name ", "4pic","$20")

                };

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ProductAdapter adapter = new ProductAdapter(productData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void recyclerViewInit()
    {
        MyListData[] myListData = new MyListData[] {
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
                new MyListData("Email", android.R.drawable.ic_dialog_email),
                new MyListData("Info", android.R.drawable.ic_dialog_info),
                new MyListData("Delete", android.R.drawable.ic_delete),
                new MyListData("Dialer", android.R.drawable.ic_dialog_dialer),
                new MyListData("Alert", android.R.drawable.ic_dialog_alert),
                new MyListData("Map", android.R.drawable.ic_dialog_map),
        };
/*
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);*/
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id){

            case R.id.point:
                Intent about = new Intent(MainActivity.this, Calculator.class);
                startActivity(about);
                break;
          /*  case R.id.profile_image:
                Intent login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login);
                break;*/
        }

        return false;
    }


    @Override
    protected void onStart() {
        super.onStart();

        //onClickLogin();


        if(mAuth.getCurrentUser() != null)
        {
            userActiveMethod();
        }
        else
        {
            userUnActiveMethod();
        }

    }

/*
    private void onClickLogOut()
    {
        guestLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                logOutClassMethod();
            }
        });

        normalLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                logOutClassMethod();
            }
        });
    }
*/

    private void logOutClassMethod()
    {
        Intent login = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(login);
    }

/*
    private void onClickLogin()
    {
        guestLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mAuth.getCurrentUser() != null) {


                    logOutClassMethod();
                }
                else
                {
                    loginClassMethod();
                }
            }
        });

        normalLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() != null) {
                    logOutClassMethod();
                }
                else
                {
                    loginClassMethod();

                }
            }
        });
    }
*/

    private void loginClassMethod()
    {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(login);
    }


    private void userUnActiveMethod()
    {
        guest.setImageResource(R.drawable.guest_icon);
        login.setImageResource(R.drawable.login_icon);
        gestTV.setText("Guest");
        loginTV.setText("Login");
    }

    private void userActiveMethod()
    {


        guest.setImageResource(R.drawable.user_icon);
        login.setImageResource(R.drawable.settings_icon);
        gestTV.setText(mAuth.getCurrentUser().getPhoneNumber());
        loginTV.setText("settings");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ex_menu,menu);
        return true;
    }
}

