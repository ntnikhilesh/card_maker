package com.example.dell.mdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.android.gms.vision.text.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeddingDetailActivity extends AppCompatActivity {

    EditText your_name,partner_name,wedding_msg,location;
    String myour_name,mpartner_name,mwedding_msg,mdate_time,mlocation;
    Button nextb,datetimeb;
    TextView tv_date_time;

    //date and time picker

    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private Button mButton;

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            mdate_time=mFormatter.format(date);
            tv_date_time.setText(mdate_time);
            Toast.makeText(WeddingDetailActivity.this,
                    mFormatter.format(date), Toast.LENGTH_SHORT).show();
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
            Toast.makeText(WeddingDetailActivity.this,
                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wedding_detail);

        your_name=(EditText)findViewById(R.id.et_your_name);
        partner_name=(EditText)findViewById(R.id.et_partner_name);
        tv_date_time=(TextView)findViewById(R.id.tv_date_time);
        wedding_msg=(EditText)findViewById(R.id.et_wedding_msg);
        location=(EditText)findViewById(R.id.et_location);
        datetimeb=(Button)findViewById(R.id.button_set_datetime);
        datetimeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        //.setMinDate(minDate)
                        //.setMaxDate(maxDate)
                        //.setIs24HourTime(true)
                        //.setTheme(SlideDateTimePicker.HOLO_DARK)
                        //.setIndicatorColor(Color.parseColor("#990000"))
                        .build()
                        .show();
            }
        });
        nextb=(Button)findViewById(R.id.button_next);
        nextb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myour_name=your_name.getText().toString();
                mpartner_name=partner_name.getText().toString();
                mwedding_msg=wedding_msg.getText().toString();
                mlocation=location.getText().toString();





                Intent myintent=new Intent(WeddingDetailActivity.this, SelectDesignActivity.class);
                       myintent .putExtra("your name",myour_name);
                myintent .putExtra("partner name",mpartner_name);
                myintent .putExtra("dt",mdate_time);
                myintent .putExtra("msg",mwedding_msg);
                myintent .putExtra("location",mlocation);

                startActivity(myintent);

            }
        });



    }
}
