package com.example.recyclerx.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerx.MainActivity;
import com.example.recyclerx.Model.User;
import com.example.recyclerx.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import static com.example.recyclerx.Model.Variable.chack;
import static com.example.recyclerx.Model.Variable.myDatabase;

public class OTPActivity extends AppCompatActivity {
    public String number;
    private EditText editTextCode;
    private String mVerificationId;
    private FirebaseAuth mAuth;
    public String TAG="Verification";
    private DatabaseReference mDatabase;
    private Toolbar tb;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        tb = findViewById(R.id.toolbarOTP);
        setSupportActionBar(tb);

        //Add backbutton




        editTextCode=findViewById(R.id.verify_codeId);
        Button confromButton=findViewById(R.id.confrom_code_btnId);


            mAuth = FirebaseAuth.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        number=getIntent().getStringExtra("number");
     //   Toast.makeText(OTPActivity.this,number,Toast.LENGTH_SHORT).show();

        getSupportActionBar().setTitle(number);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sendVerificationCode(number);


        confromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("Enter valid code");
                    editTextCode.requestFocus();
                    return;
                }

                verifyVerificationCode(code);

            }
        });

    }
    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                editTextCode.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.i(TAG, "onVerificationFailed: "+e.getMessage());
         //   Toast.makeText(OTPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            //storing the verification id that is sent to the user
            mVerificationId = s;


        }

    };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        //signing the user
        signInWithPhoneAuthCredential(credential);

    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {


        chack = 1;

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(OTPActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            startActivity(new Intent(OTPActivity.this, MainActivity.class));
                            dataInsertMethod();
                            finish();

                        } else
                        {
                            //verification unsuccessful.. display an error message
                            String message = "Somthing is wrong, we will fix it soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException)
                            {
                                message = "Invalid code entered...";
                            }
                          //  Toast.makeText(OTPActivity.this,"" +message,Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void dataInsertMethod() {


        try {


            ValueEventListener userDetails = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    User user = null;
                    try {

                        user = dataSnapshot.getValue(User.class);
                    }catch (Exception e)
                    {
                        //Toast.makeText(OTPActivity.this, " error auth", Toast.LENGTH_SHORT).show();
                    }
                    if (user == null) {
                        if (chack == 1) {

                            user = new User(number, number, number + "@ssbd.com", "You have no address");
                            mDatabase.child(number).setValue(user);

                            chack = 0;
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };

            myDatabase.addValueEventListener(userDetails);
        }
        catch (Exception e)
        {

        }


    }

}