package com.example.dell.mdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SelectDesignActivity extends AppCompatActivity {

    TextView wc_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_design);


    wc_detail=(TextView)findViewById(R.id.tv_wedding_deteal);
        String nyour_name= getIntent().getStringExtra("your name");
        String nparter_name= getIntent().getStringExtra("partner name");
        String ndate_time= getIntent().getStringExtra("dt");
        String nwedding_msg= getIntent().getStringExtra("msg");
        String nlocation= getIntent().getStringExtra("location");

        wc_detail.setText("Wedding Invitation \n Together with their families"+nyour_name+"\n weds \n"+nparter_name+"\n"+nwedding_msg+"\n"+ndate_time+"\nLocation \n"+nlocation);

    }
}
