package com.example.dell.mdemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Update_Password_Activity extends AppCompatActivity {

    private String new_password;
    private EditText update_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__password_);

        update_pass=(EditText)findViewById(R.id.et_new_pass);
        new_password=update_pass.getText().toString();
        Button button_update=(Button)findViewById(R.id.botton_update_pass);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("new password1",new_password+"1");
                update_password();
            }
        });
    }

    public void update_password()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            Log.d("new password",new_password+"1");
        user.updatePassword(new_password)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {

                            Intent i3=new Intent(Update_Password_Activity.this,Profile_Activity.class);
                            startActivity(i3);
                            Toast.makeText(Update_Password_Activity.this,"Password updated successfuly..",Toast.LENGTH_LONG).show();
                            Log.d("nikhil_new_pass", "User password updated.");
                        }
                    }
                });
    }
}
