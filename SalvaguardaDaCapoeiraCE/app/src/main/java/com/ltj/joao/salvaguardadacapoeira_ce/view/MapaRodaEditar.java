package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapaRodaEditar extends SupportMapFragment implements OnMapReadyCallback{

    private static GoogleMap mMap;
    private static Marker marker;
    private EditText edtLatitude,edtLongitude;
    private Geocoder geocoder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

  getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        geocoder = new Geocoder(getContext());
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
             if(marker != null){
                 marker.remove();
             }

                try {
                    List<Address> enderecos = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    addMarker(latLng);

                } catch (IOException e) {
                    e.printStackTrace();
                }




            }
        });
    }


    public static void addMarker(LatLng latLng){
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Roda De Capoeira");
        marker = mMap.addMarker(markerOptions);

        CameraPosition cp = new CameraPosition.Builder().target(latLng).zoom(20).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
    }


}
