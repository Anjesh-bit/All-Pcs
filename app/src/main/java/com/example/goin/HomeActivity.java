package com.example.goin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;

import com.example.goin.databinding.ActivityHomeBinding;
import com.example.goin.databinding.ActivityLoginBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

@RequiresApi(api = Build.VERSION_CODES.Q)
public class HomeActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_All_LOCATION =1 ;
    private FusedLocationProviderClient fusedLocationClient;
    private ActivityHomeBinding hBinding;
    private static final String[] REQUIRED_SDK_PERMISSIONS = new String[] {
            Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hBinding= ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(hBinding.getRoot());
        //instantiate
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(HomeActivity.this);
        fetchLocation();
    }
    private void fetchLocation() {

for(final String allPermissions:REQUIRED_SDK_PERMISSIONS)
{
    if (ContextCompat.checkSelfPermission(HomeActivity.this,
           allPermissions)
            != PackageManager.PERMISSION_GRANTED) {

        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(HomeActivity.this,
                allPermissions)) {
            new AlertDialog.Builder(HomeActivity.this)
                    .setTitle("Required Location Permission")
                    .setMessage("You have to give the permission to access the full features")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(HomeActivity.this,REQUIRED_SDK_PERMISSIONS
                                    ,
                                    MY_PERMISSIONS_REQUEST_ACCESS_All_LOCATION);

                        }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();




        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(HomeActivity.this,
                    REQUIRED_SDK_PERMISSIONS,
                    MY_PERMISSIONS_REQUEST_ACCESS_All_LOCATION);


        }
    }

        else {
            // Permission has already been granted
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                // Logic to handle location object
                            }
                        }
                    });

        }
}
    }
    //check whether we got the permission!!

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}




