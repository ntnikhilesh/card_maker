package com.example.dell.mdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeddingDetailActivity extends AppCompatActivity {

    EditText your_name;
    String myour_name;
    Button nextb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_detail);

        your_name=(EditText)findViewById(R.id.et_your_name);
        nextb=(Button)findViewById(R.id.button_next);
        nextb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myour_name=your_name.getText().toString();



                Intent myintent=new Intent(WeddingDetailActivity.this, SelectDesignActivity.class).putExtra("your name",myour_name);
                startActivity(myintent);

            }
        });



    }
}
