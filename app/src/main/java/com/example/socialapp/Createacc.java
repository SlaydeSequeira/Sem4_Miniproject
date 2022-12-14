package com.example.socialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import util.JournalUser;

public class Createacc extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private  FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference =db.collection("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc);

        firebaseAuth = FirebaseAuth.getInstance();

        EditText password1 =(EditText) findViewById(R.id.text3);
        EditText email1 = (EditText) findViewById(R.id.text2);
        EditText username1=(EditText) findViewById(R.id.text1);
        Button login=(Button)  findViewById(R.id.button);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if(currentUser != null)
                {

                }
            }
        };
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(email1.getText().toString()) && !TextUtils.isEmpty(password1.getText().toString())){
                    String email = email1.getText().toString().trim();
                    String password = password1.getText().toString().trim();
                    String username= username1.getText().toString().trim();

                    CreateUserEmailAccount(email,password,username);

                }
                else{
                    Toast.makeText(Createacc.this,"Empty",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void CreateUserEmailAccount(String email, String password,final String username) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    currentUser = firebaseAuth.getCurrentUser();
                    assert currentUser!= null;
                    final String currentUserId = currentUser.getUid();

                    Map<String, String> userObj =new HashMap<>();
                    userObj.put("UserId",currentUserId);
                    userObj.put("username",username);

                    collectionReference.add(userObj).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    if(Objects.requireNonNull(task.getResult()).exists()){
                                        String name = task.getResult().getString("username");

                                        JournalUser journalUser = JournalUser.getInstance();
                                        journalUser.setUsername(currentUserId);
                                        journalUser.setUsername(name);

                                        Intent i = new Intent(Createacc.this,HomePage.class);
                                        i.putExtra("username",name);
                                        i.putExtra("userId",currentUserId);
                                        startActivity(i);
                                    }
                                    else{

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Createacc.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}