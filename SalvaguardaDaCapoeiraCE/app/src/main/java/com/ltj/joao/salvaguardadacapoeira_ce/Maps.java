package com.ltj.joao.salvaguardadacapoeira_ce;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarRoda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maps extends SupportMapFragment implements OnMapReadyCallback{

    private static GoogleMap mMap;
    private CapoeiraDB db;
    public static List<Roda> rodas;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getMapAsync(this);
        if(rodas != null && rodas.size() > 0){

        }else{
            rodas = new ArrayList<>();
        }



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {

            mMap = googleMap;
            mMap.getUiSettings().setZoomControlsEnabled(true);


            for (Roda r :rodas) {

                LatLng latLng = new LatLng(Double.valueOf(r.getLatitude()), Double.valueOf(r.getLongitude()));
                inserirMarker(latLng,r.getGrupoOrganizador());

            }

            CameraPosition cp = new CameraPosition.Builder().zoom(7).target(new LatLng(-5.262458, -39.539827)).build();




            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    LatLng ll = marker.getPosition();


                    for(Roda r:rodas){
                        if(r.getLatitude().toLowerCase().contains(""+ll.latitude) && r.getLongitude().toLowerCase().contains(""+ll.longitude)){
                            VisualizarRoda.setRoda(r);
                            startActivity(new Intent(getContext(),VisualizarRoda.class));
                        }

                    }
                }
            });
        }catch (Exception e){
            Toast.makeText(getContext(),"ErAQUIo1"+e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    private void inserirMarker(LatLng point,String titulo) {
        try {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(titulo);
            markerOptions.position(point);
            mMap.addMarker(markerOptions);
        } catch (Exception e){
            Toast.makeText(getContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

}
