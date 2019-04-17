package com.example.recyclerx.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerx.R;

public class Calculator extends AppCompatActivity implements View.OnClickListener {

    //Home Page Toolbar;
    Toolbar tb;

    //Spinner Resource;
    Spinner spfriend;
    String[] names = {"Plastic","Paper","Plastic Bottle","Polythene","Metals","Books","Brass","Zinc","GI pipe","Copper","Fun Copper","Paper Carton"};
    Button totalpoint;
    EditText kg;
    TextView pointshow;

    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        //Home Page Toolbar;
        tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);


        //Add backbutton
        getSupportActionBar().setTitle("Point Calculator");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //Spinner Variable;
        spfriend = findViewById(R.id.spCountry);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,names);
        spfriend.setAdapter(arrayAdapter);


        //Calculator Variable;
        totalpoint = findViewById(R.id.addpoint);
        kg = findViewById(R.id.kg);
        pointshow = findViewById(R.id.pointshow);
        totalpoint.setOnClickListener(this);






    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            //Ends the activity
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }


    //Calculator Calculation;
    @Override
    public void onClick(View v) {
        int total = 0;
        String Kg = kg.getText().toString();
        String text = spfriend.getSelectedItem().toString();

        if (Kg.isEmpty()){

            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Plastic")){

             total = Integer.parseInt(Kg)*72;


            Toast.makeText(this, "palastic", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Paper")){
            total = Integer.parseInt(Kg)*44;
            Toast.makeText(this, "Paper", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Plastic Bottle")){
            total = Integer.parseInt(Kg)*80;
            Toast.makeText(this, "Plastic Bottle", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Polythene")){
            total = Integer.parseInt(Kg)*160;
            Toast.makeText(this, "Polythene", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Metals")){
            total = Integer.parseInt(Kg)*100;
            Toast.makeText(this, "Metals", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Books")){
            total = Integer.parseInt(Kg)*32;
            Toast.makeText(this, "Books", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Brass")){
            total = Integer.parseInt(Kg)*1200;
            Toast.makeText(this, "Brass", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Zinc")){
            total = Integer.parseInt(Kg)*400;
            Toast.makeText(this, "Zinc", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("GI pipe")){
            total = Integer.parseInt(Kg)*120;
            Toast.makeText(this, "GI pipe", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Copper")){
            total = Integer.parseInt(Kg)*2000;
            Toast.makeText(this, "Copper", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Fun Copper")){
            total = Integer.parseInt(Kg)*1200;
            Toast.makeText(this, "Fun Copper", Toast.LENGTH_SHORT).show();
        }

        else if (text.equals("Paper Carton")){
            total = Integer.parseInt(Kg)*32;
            Toast.makeText(this, "Paper Carton", Toast.LENGTH_SHORT).show();
        }

        pointshow.setText("Product recycling point you will get\n"+String.valueOf(total));
        //pointshow.setText(pointshow.getText().toString()+"\n"+String.valueOf(total));
        //pointshow.setTextSize(20f);
        //pointshow.setText(text);
    }
}
