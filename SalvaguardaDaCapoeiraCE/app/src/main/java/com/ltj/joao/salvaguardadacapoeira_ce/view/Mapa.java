package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoRoda;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Mapa extends SupportMapFragment implements OnMapReadyCallback{

    public static GoogleMap mMap;
    public OperacaoRoda or;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

or = new OperacaoRoda(getContext(),null,null);
        getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {

            mMap = googleMap;
            mMap.getUiSettings().setZoomControlsEnabled(true);




            CameraPosition cp = new CameraPosition.Builder().zoom(7).target(new LatLng(-5.262458, -39.539827)).build();




            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cp));
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    TextView txtView = new TextView(getContext());
                    txtView.setBackgroundResource(R.color.centerColor);
                    txtView.setText(Html.fromHtml("<h3>"+marker.getTitle()+"</h3>"));

                    txtView.setHeight(50);
                   txtView.setTextColor(Color.WHITE);
                    return txtView;
                }

                @Override
                public View getInfoContents(Marker marker) {

                    return null;
                }
            });

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    return false;
                }
            });
            or.listar(mMap);

        }catch (Exception e){

        }
    }



}