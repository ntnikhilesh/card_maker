package com.example.dell.mdemo;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dell.mdemo.generator.NetworkApiGenerator;
import com.example.dell.mdemo.interfaces.MaptagServiceInterface;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    EditText enter_mapatg,address_line1,address_line2,city,state,zip,phone,lat,lon;
    ImageView maptag_image;

    double lati=12.92021997582485;
    double longi=77.61453927203593;

    private MaptagServiceInterface mv;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



        enter_mapatg=(EditText)findViewById(R.id.et_entermaptag);
        address_line1=(EditText)findViewById(R.id.et_ad_line1);
        address_line2=(EditText)findViewById(R.id.et_ad_line2);
        city=(EditText)findViewById(R.id.et_city);
        state=(EditText)findViewById(R.id.et_state);
        zip=(EditText)findViewById(R.id.et_zip);
        phone=(EditText)findViewById(R.id.et_phone);
       // lat=(EditText)findViewById(R.id.et_lat);
       // lon=(EditText)findViewById(R.id.et_lon);

        maptag_image=(ImageView)findViewById(R.id.iv_maptag_image);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("latdlong",lati+"and "+longi);
        //double lati=Double.parseDouble(lat.getText().toString().trim());
        //double longi=Double.parseDouble(lon.toString().trim());
        // Add a marker in Sydney and move the camera
        LatLng latLng = new LatLng(lati,longi);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Nikhil Home"));
        float zoomLevel = (float) 16.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }

    public void updateMarker()
    {
        LatLng latLng = new LatLng(lati,longi);
        mMap.addMarker(new MarkerOptions().position(latLng).title(enter_mapatg.getText().toString()));
        float zoomLevel = (float) 16.0; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
    }





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
//                    lat.setText(getMaptagModel.getLat().toString());
      //              lon.setText(getMaptagModel.getLng().toString());

                    lati=Double.parseDouble(getMaptagModel.getLat().toString());
                    longi=Double.parseDouble(getMaptagModel.getLng().toString());

                    updateMarker();

                    List<String> img=getMaptagModel.getImages();
                    Log.d("nikhil image",getMaptagModel.getImages()+"");
                    //maptag_image.setImageURI(Uri.parse(img.toString()));

                    if(img.size()>0) {

                        Picasso.with(MapsActivity.this).load(img.get(0)).into(maptag_image);
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
