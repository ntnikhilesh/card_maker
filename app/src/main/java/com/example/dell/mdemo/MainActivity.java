package com.example.dell.mdemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.mdemo.generator.NetworkApiGenerator;
import com.example.dell.mdemo.interfaces.MaptagServiceInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText enter_mapatg,address_line1,address_line2,city,state,zip,phone,lat,lon;
    ImageView maptag_image;

    private MaptagServiceInterface mv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enter_mapatg=(EditText)findViewById(R.id.et_entermaptag);
        address_line1=(EditText)findViewById(R.id.et_ad_line1);
        address_line2=(EditText)findViewById(R.id.et_ad_line2);
        city=(EditText)findViewById(R.id.et_city);
        state=(EditText)findViewById(R.id.et_state);
        zip=(EditText)findViewById(R.id.et_zip);
        phone=(EditText)findViewById(R.id.et_phone);
        lat=(EditText)findViewById(R.id.et_lat);
        lon=(EditText)findViewById(R.id.et_lon);

        maptag_image=(ImageView)findViewById(R.id.iv_maptag_image);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //fetch maptag detail using getMaptag API


    public void getdetails(View v) {

        if(enter_mapatg.getText().length()>0)
        {
        send();
        }
        else
        {
            Toast.makeText(this,"Please enter your Maptag",Toast.LENGTH_LONG).show();
        }

    }


    public void send()

    {                mv = NetworkApiGenerator.createService(MaptagServiceInterface.class);

        try {
            String m_tag=enter_mapatg.getText().toString();
            mv.confirmSlot(m_tag, new Callback<GetMaptagModel>() {
                @Override
                public void success(GetMaptagModel getMaptagModel, Response response) {

                    Log.d("nikhil response", response.getUrl());
                    Log.d("nikhil loginmodel data", getMaptagModel.toString());

                    //Button b=(Button)findViewById(R.id.but);
                   // b.setText(getMaptagModel.getName().toString());

                    address_line1.setText(getMaptagModel.getAddress_line_1().toString());
                    address_line2.setText(getMaptagModel.getAddress_line_2().toString());
                    city.setText(getMaptagModel.getCity().toString());
                    state.setText(getMaptagModel.getState().toString());
                    zip.setText(getMaptagModel.getZip().toString());
                    phone.setText(getMaptagModel.getPhone().toString());
                    lat.setText(getMaptagModel.getLat().toString());
                    lon.setText(getMaptagModel.getLng().toString());

                    List<String> img=getMaptagModel.getImages();
                   Log.d("nikhil image",getMaptagModel.getImages()+"");
                    //maptag_image.setImageURI(Uri.parse(img.toString()));

                    if(img.size()>0) {

                        Picasso.with(MainActivity.this).load(img.get(0)).into(maptag_image);
                    }
                    else
                    {
                        maptag_image.setImageResource(R.drawable.maptag_logo);
                    }


                }

                @Override
                public void failure(RetrofitError error) {

                }
            });
        }catch (Exception e) {

        }
    }


}

