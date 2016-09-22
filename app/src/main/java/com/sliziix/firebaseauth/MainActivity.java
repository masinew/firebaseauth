package com.sliziix.firebaseauth;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private AuthStateListener mAuthListener;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext()); // This line must be written before setContentView();
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);


        LoginButton loginButton = (LoginButton) findViewById(R.id.btFacebook);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                handleFacebookAccessTocken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this,
                            "onAuthStateChanged:signed_in:" + user.getUid(),
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,
                            "onAuthStateChanged:signed_out",
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void handleFacebookAccessTocken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(MainActivity.this, "Authentication Success.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private String email = null;
    private String password = null;
    public void login(View view) {
        getUserInputInformation();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this,
                                "signInWithEmail:onComplete:" + task.isSuccessful(),
                                Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(MainActivity.this,
                                "Authentication Successful.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void register(View view) {
        getUserInputInformation();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(MainActivity.this,
                                "createUserWithEmail:onComplete:" + task.isSuccessful(),
                                Toast.LENGTH_SHORT).show();

                        if (!task.isSuccessful()) {
                            Toast.makeText(MainActivity.this,
                                    "Authentication Failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(MainActivity.this,
                                "Authentication Successful.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void logout(View view) {
        if (mAuth != null) {
            mAuth.signOut();
        }
    }


    public void showUserInfo(View view) {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();

            Toast.makeText(MainActivity.this,
                    "Name: " + name + ", Email: " + email,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void getUserInputInformation() {
        EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
    }
}
