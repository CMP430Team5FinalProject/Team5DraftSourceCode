package com.example.projectcmp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    //Determining the strength of the password
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    TextInputLayout textInputEmail;
    TextInputLayout textInputPassword;
    TextInputLayout textInputUsername;
    TextView alreadyMemberSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        textInputEmail = findViewById(R.id.sign_up_email);
        textInputPassword = findViewById(R.id.sign_up_password);
        textInputUsername = findViewById(R.id.sign_up_username);
        alreadyMemberSignIn = findViewById(R.id.returning_member__sign_in);

        // Changes color of SIGN IN
        TextView textView = findViewById(R.id.returning_member__sign_in);
        String text = "Already a Member? SIGN IN";
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcsRed, 18,25, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);

        //onClickListner that takes you to the SignIn Activity
        alreadyMemberSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri webpage = Uri.parse("https://www.espn.com");
                //Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                //startActivity(webIntent);
                //finish();
                Intent i = new Intent(SignUp.this,SignIn.class);
                startActivity(i);
                finish();

            }
        });


    }

    // Client-side username validation
    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();
        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Username too long");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }
    // Client-side email validation
    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            textInputEmail.setError("Please enter a valid email address");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }
    // Client-side password validation
    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        }  else if (passwordInput.length() < 6) {
            textInputPassword.setError("Password too short");
            return false;
        }   else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            textInputPassword.setError("Password too weak");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }
    //Action after SIGN UP button is clicked
    public void confirmInput(View v) {
        if (!validateUsername() | !validateEmail() | !validatePassword()) {
            return;
        }
        String input = "Username: " + textInputUsername.getEditText().getText().toString();
        input += "\n";
        input += "Email: " + textInputEmail.getEditText().getText().toString();
        input += "\n";
        input += "Password: " + textInputPassword.getEditText().getText().toString();
        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
    }

}
