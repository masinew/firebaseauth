package com.sliziix.firebaseauth;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
//    private FirebaseAuth mAuth;
//    private AuthStateListener mAuthListener;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.buttonLogin);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

//        mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    Toast.makeText(MainActivity.this,
//                            "onAuthStateChanged:signed_in:" + user.getUid(),
//                            Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(MainActivity.this,
//                            "onAuthStateChanged:signed_out",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        };
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
//
//    private String email = null;
//    private String password = null;
//    public void login(View view) {
//        getUserInputInformation();
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(MainActivity.this,
//                                "signInWithEmail:onComplete:" + task.isSuccessful(),
//                                Toast.LENGTH_SHORT).show();
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        Toast.makeText(MainActivity.this,
//                                "Authentication Successful.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    public void register(View view) {
//        getUserInputInformation();
//        mAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Toast.makeText(MainActivity.this,
//                                "createUserWithEmail:onComplete:" + task.isSuccessful(),
//                                Toast.LENGTH_SHORT).show();
//
//                        if (!task.isSuccessful()) {
//                            Toast.makeText(MainActivity.this,
//                                    "Authentication Failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        Toast.makeText(MainActivity.this,
//                                "Authentication Successful.",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    public void showUserInfo(View view) {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            String name = user.getDisplayName();
//            String email = user.getEmail();
//
//            Toast.makeText(MainActivity.this,
//                    name + " : " + email + " : ",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private void getUserInputInformation() {
//        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
//        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//
//        email = editTextEmail.getText().toString();
//        password = editTextPassword.getText().toString();
//    }
}
