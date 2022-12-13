package com.example.socialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Username=(EditText) findViewById(R.id.edittext);
        EditText Password=(EditText) findViewById(R.id.edittext2);
        Button Newacc=(Button) findViewById(R.id.button);
        Button Login=(Button) findViewById(R.id.loginbtn);
        String ID =Username.getText().toString();
        String PASS =Password.getText().toString();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Username.getText().toString().equals("admin") && Password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    openHomePage();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_LONG).show();
                }
            }
        });
        Newacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivity();
            }
        });
    }

    public void openActivity() {
        Intent intent= new Intent(this,Createacc.class);
        startActivity(intent);
    }

    public void openHomePage() {
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);
    }

}