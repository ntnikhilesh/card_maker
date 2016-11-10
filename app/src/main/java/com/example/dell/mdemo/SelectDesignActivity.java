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
        wc_detail.setText(nyour_name);

    }
}
