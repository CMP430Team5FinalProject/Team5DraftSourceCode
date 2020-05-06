package com.example.caloriecalculator2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.caloriecalculator2020.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccountButton;
    private EditText inputName, inputPhoneNumber, inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccountButton = (Button) findViewById(R.id.rgister_create_account);
        inputName = (EditText) findViewById(R.id.register_name);
        inputPhoneNumber = (EditText) findViewById(R.id.register_phone_number);
        inputPhoneNumber = (EditText) findViewById(R.id.register_password_input);


        // Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference tabel_user = database.getReference("User");

        createAccountButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                mDialog.setMessage("Hold on please...");
                mDialog.show();
                tabel_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Check if user exists
                        if (dataSnapshot.child(inputPhoneNumber.getText().toString()).exists()) {
                            // get user information
                            mDialog.dismiss();

                            Toast.makeText(RegisterActivity.this, "Phone Number Already Exist!", Toast.LENGTH_SHORT).show();
                        } else {
                            mDialog.dismiss();
                            User user = new User(inputName.getText().toString(), inputPassword.getText().toString());
                            tabel_user.child(inputPhoneNumber.getText().toString()).setValue(user);
                            Toast.makeText(RegisterActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });

            }

        });
    }
}
