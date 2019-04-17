package com.example.recyclerx.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerx.R;

public class LoginActivity extends AppCompatActivity {

    private String number;
    private Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tb = findViewById(R.id.toolbarLogin);
        setSupportActionBar(tb);

        //Add backbutton
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        final EditText set_number = findViewById(R.id.set_phone_numberId);
        Button reg_btn = findViewById(R.id.registation_btnId);


        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                number = set_number.getText().toString();
                Toast.makeText(getApplicationContext(), "" + number, Toast.LENGTH_LONG).show();

                if ( !number.isEmpty() &&  number.length() == 14){
                    Intent in = new Intent(LoginActivity.this, OTPActivity.class);
                    in.putExtra("number", number);
                   // Toast.makeText(LoginActivity.this,number,Toast.LENGTH_SHORT).show();
                  //  System.out.println("Number "+ number);
                   // Log.d("Number",number);
                    startActivity(in);
                }else {
                    Toast.makeText(LoginActivity.this, "Please Empty The Valid Number", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
