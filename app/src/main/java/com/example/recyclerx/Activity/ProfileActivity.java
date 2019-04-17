package com.example.recyclerx.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerx.MainActivity;
import com.example.recyclerx.Model.User;
import com.example.recyclerx.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.recyclerx.Model.Variable.*;


public class ProfileActivity extends AppCompatActivity {

    private TextView logOut,user_numberTV,user_nameTV,emailTV,addressTV,updateTV,cancalTV,updateAddressTV,clearAddressTV;
    private TextView changeUser,changeAddress,browse;
    private EditText userNameET,userAddressET;

    private User user;
    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

      /*  mAuth = FirebaseAuth.getInstance();
        myDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber());*/

        tb = findViewById(R.id.toolbarlogOut);

        user_numberTV = findViewById(R.id.user_numberTVId);
        user_nameTV = findViewById(R.id.user_nameTVId);
        emailTV = findViewById(R.id.user_mailTVId);
        addressTV = findViewById(R.id.user_addressTVId);
        userNameET = findViewById(R.id.user_nameEVId);
        userAddressET = findViewById(R.id.user_addressEVId);


        changeUser = findViewById(R.id.change_user_name_edittextId);
        changeAddress = findViewById(R.id.change_address_edittextId);
        browse = findViewById(R.id.browse_id);

        updateTV = findViewById(R.id.user_name_updateTVId);
        cancalTV = findViewById(R.id.user_cancelTVId);
        updateAddressTV = findViewById(R.id.user_address_updateTVId);
        clearAddressTV  = findViewById(R.id.user_address_cancelTVId);

        setSupportActionBar(tb);


        ValueEventListener userDetails = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {


                try {

                    user = dataSnapshot.getValue(User.class);


                    user_nameTV.setText(user.getUsername());
                    user_numberTV.setText(mAuth.getCurrentUser().getPhoneNumber());
                    addressTV.setText(user.getAddress());
                    emailTV.setText(user.getMail());
                }catch (Exception e)
                {
                   // Toast.makeText(ProfileActivity.this, "This is data change error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myDatabase.addValueEventListener(userDetails);


    /*    user_number_updateTV.setText(mAuth.getCurrentUser().getPhoneNumber());
        user_numberTV.setText(mAuth.getCurrentUser().getPhoneNumber());*/

        // Add backbutton

        getSupportActionBar().setTitle("Mobile Login");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
/*        getSupportActionBar().setTitle("Point Calculator");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        logOut = findViewById(R.id.logoutTVId);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(login);
                finish();
            }
        });

       changeUser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               LinearLayout linearLayout = findViewById(R.id.update_designLOId);
               linearLayout.setVisibility(View.VISIBLE);
               user_nameTV.setVisibility(View.GONE);
               changeUser.setVisibility(View.GONE);
               userNameET.setVisibility(View.VISIBLE);
               userNameET.setText(user_nameTV.getText().toString());
           }
       });

       changeAddress.setOnClickListener(new View.OnClickListener()
       {
           @Override
           public void onClick(View v)
           {



               updateMethod();



             /*  LinearLayout linearLayout = findViewById(R.id.update_design_addressLOId);
               linearLayout.setVisibility(View.VISIBLE);
               addressTV.setVisibility(View.GONE);
               changeAddress.setVisibility(View.GONE);
               userAddressET.setVisibility(View.VISIBLE);
               userAddressET.setText(addressTV.getText().toString());*/
           }
       });

        updateTV.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!userNameET.getText().toString().isEmpty())
                {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("username").setValue(userNameET.getText().toString());
                    clearNameCode();
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        updateAddressTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!userAddressET.getText().toString().isEmpty())
                {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("address").setValue(userAddressET.getText().toString());
                    clearAddressCode();
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Please enter your name.", Toast.LENGTH_SHORT).show();
                }

            }
        });


        cancalTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                clearNameCode();

            }
        });

        clearAddressTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             clearAddressCode();
            }
        });


    }

    private void updateMethod()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.address_update,null,false);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText name, phone,area,address,mail;
        Button submitBTN;

        submitBTN =view.findViewById(R.id.update_button_id);
        name = view.findViewById(R.id.update_name_id);
        phone = view.findViewById(R.id.update_phone_id);
        area = view.findViewById(R.id.update_area_id);
        address = view.findViewById(R.id.update_address_id);
        mail = view.findViewById(R.id.update_mail_id);

        name.setText(user.getUsername());
        address.setText(user.getAddress());
        area.setText(user.getAddress());
        mail.setText(user.getMail());
        phone.setText(mAuth.getCurrentUser().getPhoneNumber());

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {

                String nameCh,phoneCh,areaCh,addressCh,mailCh;

                nameCh = name.getText().toString();
                mailCh = mail.getText().toString();
                phoneCh = phone.getText().toString();
                areaCh = area.getText().toString();
                addressCh = address.getText().toString();


                if(nameCh.isEmpty() || phoneCh.isEmpty() || areaCh.isEmpty() || addressCh.isEmpty() || mailCh.isEmpty())
                {
                    Toast.makeText(ProfileActivity.this, " Please Fill up the All Filed ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("name").setValue(nameCh);
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("phone").setValue(phoneCh);
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("area").setValue(areaCh);
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("address").setValue(addressCh);
                    FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getPhoneNumber()).child("mail").setValue(mailCh);
                    alertDialog.dismiss();

                }


            }
        });

    }

    private void clearNameCode() {
        LinearLayout linearLayout = findViewById(R.id.update_designLOId);
        linearLayout.setVisibility(View.GONE);
        user_nameTV.setVisibility(View.VISIBLE);
        changeAddress.setVisibility(View.VISIBLE);
        userNameET.setVisibility(View.GONE);
    }

    private void clearAddressCode() {
        LinearLayout linearLayout = findViewById(R.id.update_design_addressLOId);
        linearLayout.setVisibility(View.GONE);
        addressTV.setVisibility(View.VISIBLE);
        changeAddress.setVisibility(View.VISIBLE);
        userAddressET.setVisibility(View.GONE);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            //Ends the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}