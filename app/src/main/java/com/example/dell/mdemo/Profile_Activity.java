package com.example.dell.mdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
    }

    public void mlogout(View v)
    {
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Profile_Activity.this, "Logout...",
                Toast.LENGTH_SHORT).show();

        Intent i2=new Intent(Profile_Activity.this,LoginActivity.class);
        startActivity(i2);
    }
}
