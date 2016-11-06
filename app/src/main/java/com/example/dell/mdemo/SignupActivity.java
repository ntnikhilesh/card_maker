package com.example.dell.mdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    Button signup_button;

    // UI references.
    private AutoCompleteTextView mEmailView_signup;
    private EditText mPasswordView_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEmailView_signup = (AutoCompleteTextView) findViewById(R.id.et_email_on_signup);
       // populateAutoComplete();

        mPasswordView_signup = (EditText) findViewById(R.id.et_password_on_signup);

        signup_button=(Button)findViewById(R.id.button_signup);
        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create a new account by passing the new user's email address and password to createUserWithEmailAndPassword:

               msignup();

            }
        });

        //get the shared instance of the FirebaseAuth object

        mAuth = FirebaseAuth.getInstance();


        //Set up an AuthStateListener that responds to changes in the user's sign-in state:

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("nikhil id if signup", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent i3=new Intent(SignupActivity.this,Profile_Activity.class);
                    startActivity(i3);

                } else {
                    // User is signed out
                    Log.d("nikhil id ekse signup", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



    }// end on create



    public void go_to_signin(View view)
    {
        Intent i5=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(i5);
    }


    public void msignup()
    {

        String sigup_email=mEmailView_signup.getText().toString();
        String signup_pass=mPasswordView_signup.getText().toString();
        Log.d("nsignup data",sigup_email+""+signup_pass);

        mAuth.createUserWithEmailAndPassword(sigup_email,signup_pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("nihhil signup1", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.


                        //If the new account was created, the user is also signed in,
                        // and the AuthStateListener runs the onAuthStateChanged callback.
                        // In the callback, you can use the getCurrentUser method to get the user's account data.

                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    /*public void mlogout(View v)
    {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(SignupActivity.this, "Logout...",
                Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
