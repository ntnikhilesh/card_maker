package com.example.dell.mdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class Profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);



        get_user_details();



        Button update_pass=(Button)findViewById(R.id.botton_goto_update_page);
        update_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i12=new Intent(Profile_Activity.this,Update_Password_Activity.class);
                startActivity(i12);

            }
        });
    }

    public void get_user_details()
    {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {
                // Id of the provider (ex: google.com)
               // String providerId = profile.getProviderId();

                // UID specific to the provider
                String uid = profile.getUid();

                // Name, email address, and profile photo Url
                String u_name = profile.getDisplayName();
                String u_email = profile.getEmail();
                Uri u_photoUrl = profile.getPhotoUrl();
                ImageView u_image=(ImageView)findViewById(R.id.iv_user_imape);
                Picasso.with(Profile_Activity.this).load(u_photoUrl
                ).into(u_image);

                TextView u_detail=(TextView)findViewById(R.id.tv_user_data);
                u_detail.setText("Name :"+u_name+
                        "\n Email:"+uid);

                Log.w("nikhil_full_details", "uid ="+uid+"\nuname ="+u_name+"\n image url="+u_photoUrl+"\n uemail="+u_email);
            };
        }
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
