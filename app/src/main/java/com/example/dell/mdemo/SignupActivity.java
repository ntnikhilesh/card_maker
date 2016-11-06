package com.example.dell.mdemo;

import android.content.Intent;
import android.net.Uri;
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
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity {

    String sigup_email;
    String signup_pass;

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
                    update_profile();
                    update_email();
                    send_mail();

                    Log.d("nikhil id if signup", "onAuthStateChanged:signed_in:" + user.getUid());
                    FirebaseAuth.getInstance().signOut();
                    Intent i3=new Intent(SignupActivity.this,LoginActivity.class);
                    startActivity(i3);
                    Toast.makeText(SignupActivity.this,"Signup successfuly....Login please",Toast.LENGTH_LONG).show();

                } else {
                    // User is signed out
                    Log.d("nikhil id ekse signup", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };



    }// end on create



    //send verification mail

    public void send_mail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("nikhil_email", "Email sent.");
                        }
                    }
                });

    }

    //Update email

    public void update_email()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.updateEmail(sigup_email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("nikhil_email", "User email address updated.");
                        }
                    }
                });
    }


    // Update profile

    public void update_profile()
    {
        Log.d("nikhil update profile", "done");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Nikhilesh")
                .setPhotoUri(Uri.parse("http://i.imgur.com/i4f9f9I.jpg"))
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("nupdate_profile", "User profile updated.");
                        }
                    }
                });
    }


    public void go_to_signin(View view)
    {
        Intent i5=new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(i5);
    }


    public void msignup()
    {

       sigup_email=mEmailView_signup.getText().toString();
       signup_pass=mPasswordView_signup.getText().toString();
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
