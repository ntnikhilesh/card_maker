package com.example.dell.mdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SelectDesignActivity extends AppCompatActivity {

    TextView wc_detail,pyour_name,ppartner_name,pwedding_message,pdate_time,plocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_design);


    //wc_detail=(TextView)findViewById(R.id.tv_wedding_deteal);

        pyour_name=(TextView)findViewById(R.id.tv_w3_your_name);
        ppartner_name=(TextView)findViewById(R.id.tv_w5_partner_name);
        pwedding_message=(TextView)findViewById(R.id.tv_w6_wedding_message);
        pdate_time=(TextView)findViewById(R.id.tv_w7_time);
        plocation=(TextView)findViewById(R.id.tv_w8_location);


        String nyour_name= getIntent().getStringExtra("your name");
        String nparter_name= getIntent().getStringExtra("partner name");
        String ndate_time= getIntent().getStringExtra("dt");
        String nwedding_msg= getIntent().getStringExtra("msg");
        String nlocation= getIntent().getStringExtra("location");

        pyour_name.setText(nyour_name);
        ppartner_name.setText(nparter_name);
        pwedding_message.setText(nwedding_msg);
        pdate_time.setText(ndate_time);
        plocation.setText(nlocation);

       // wc_detail.setText("Wedding Invitation \n Together with their families"+nyour_name+"\n weds \n"+nparter_name+"\n"+nwedding_msg+"\n"+ndate_time+"\nLocation \n"+nlocation);

    }
}
