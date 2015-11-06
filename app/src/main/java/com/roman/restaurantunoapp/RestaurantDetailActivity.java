package com.roman.restaurantunoapp;

import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class RestaurantDetailActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,
        DownloadTask.Communicator {

    GoogleApiClient mGoogleApiClient;
    GoogleMap mGoogleMap;
    LocationRequest mLocationRequest;
    Marker marker;

    TextView restaurantName;
    TextView restaurantHours;
    Button restaurantPhone;

    RestaurantObject obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        /*
        //for switching layout files if map fails
        if (googleServicesAvailable()) {
            Toast.makeText(this, "Perfect - Maps Working", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_restaurant_detail);
         }
        */

        obj = getIntent().getParcelableExtra("theRestObject");
        restaurantName = (TextView) findViewById(R.id.restNameTV);
        restaurantHours = (TextView) findViewById(R.id.hoursTV);
        restaurantPhone = (Button) findViewById(R.id.phoneButton);

        restaurantName.setText(obj.restName);
        restaurantPhone.setText(obj.restPhone);
        restaurantHours.setText(obj.restDay1 + ": " + obj.restStartTime1 + " - " + obj.restEndTime1 + "\n" +
                obj.restDay2 + ": " + obj.restStartTime2 + " - " + obj.restEndTime2 + "\n" +
                obj.restDay3 + ": " + obj.restStartTime3 + " - " + obj.restEndTime3 + "\n" +
                obj.restDay4 + ": " + obj.restStartTime4 + " - " + obj.restEndTime4 + "\n" +
                obj.restDay5 + ": " + obj.restStartTime5 + " - " + obj.restEndTime5 + "\n" +
                obj.restDay6 + ": " + obj.restStartTime6 + " - " + obj.restEndTime6 + "\n" +
                obj.restDay7 + ": " + obj.restStartTime7 + " - " + obj.restEndTime7 +  "\n");

        if (initMap()) {
            mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API).
                    addConnectionCallbacks(this).addOnConnectionFailedListener(this).build();
            mGoogleApiClient.connect();
            mGoogleMap.setMyLocationEnabled(false);
            Toast.makeText(this, "Perfect - Maps Working", Toast.LENGTH_LONG).show();
            goToLocation(obj.restLat, obj.restLong, 15);
        }
    }

    private boolean initMap() {
        if (mGoogleMap == null) {
            MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mGoogleMap = mapFrag.getMap();
        }
        return (mGoogleMap != null);
    }

    private void goToLocation(double lat, double lng, int zoom) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
        mGoogleMap.moveCamera(update);
        setMarker("" + obj.restName, lat, lng);
    }


    public boolean googleServicesAvailable() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, 0);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant connect to play services", Toast.LENGTH_LONG).show();
        }
        return false;
    }

    @Override
    public void updateProgressTo(int progress) {

    }

    @Override
    public void updateUI(ArrayList<RestaurantObject> photosArrayList) {
        goToLocation(obj.restLat, obj.restLong, 15);           //mGoogleMap

        if (marker != null) {
            marker.remove();
        }
        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(obj.restLat, obj.restLong));
        marker = mGoogleMap.addMarker(options);
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(3000000); // Update location every 3000 second
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            Toast.makeText(this, "Cant get Current location", Toast.LENGTH_LONG).show();
        } else {
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 15);
            mGoogleMap.animateCamera(update);
            setMarker("" + obj.restName, location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void setMarker(String locality, double lat, double lng) {
        if (marker != null) {
            marker.remove();
        }
        MarkerOptions options = new MarkerOptions().title(locality).position(new LatLng(lat, lng)).
                icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        marker = mGoogleMap.addMarker(options);
    }

    public void callClicked(View view) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + restaurantPhone.getText().toString()));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(callIntent);
    }
}
