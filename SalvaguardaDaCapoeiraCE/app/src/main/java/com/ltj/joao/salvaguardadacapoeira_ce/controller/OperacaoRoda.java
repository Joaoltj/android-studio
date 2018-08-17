package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;
import android.support.v7.widget.SearchView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Usuario;
import com.ltj.joao.salvaguardadacapoeira_ce.view.CadastrarCapoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.view.Mapa;
import com.ltj.joao.salvaguardadacapoeira_ce.view.Perfil;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarCapoeiristas;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarRoda;

import java.util.ArrayList;
import java.util.List;

public class OperacaoRoda {
    public FirebaseDatabase fb;
    public DatabaseReference dr;
    public FirebaseStorage fs;
    public StorageReference sr;
    public Context context;
    public Roda roda;

    public SharedPreferences preferences;
    public OperacaoUsuario ou;
    GrupoAdapter adapter;
    public GoogleMap map;
    public SharedPreferences.Editor editor;
    Usuario usuario;
    static List<Roda>rodas = new ArrayList<>();
    public OperacaoRoda(Context context, SharedPreferences preferences, SharedPreferences.Editor editor){
        FirebaseDB.init();
        FirebaseSG.init();
        this.context = context;
        this.fb = FirebaseDB.getFb();
        this.dr = FirebaseDB.getDr("usuarios");
        this.editor = editor;
        this.preferences = preferences;
        ou = new OperacaoUsuario(context);
        ou.listar();
    }
    public void inserir(Roda r) {
        this.roda = r;
        for(Usuario u:ou.usuarios){
            if(u.getId().equals(preferences.getString("id",""))){
                usuario = u;
                break;
            }
        }
        usuario.setRoda(roda);
        dr.child(usuario.getId()).setValue(usuario);
    }

    public void listar(final GoogleMap m){
        this.map = m;
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                rodas.clear();
                Mapa.mMap.clear();
                for(DataSnapshot dsLista:dataSnapshot.getChildren()){

                    Usuario u = dsLista.getValue(Usuario.class);
                    if(u.getRoda() != null){
                        rodas.add(u.getRoda());

                        LatLng latLng = new LatLng(Double.valueOf(u.getRoda().getLatitude()), Double.valueOf(u.getRoda().getLongitude()));
                        inserirMarker(latLng,u.getRoda().getGrupoOrganizador());
                    }
                }



                Mapa.mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {
                        LatLng ll = marker.getPosition();


                        for(Roda r:rodas){
                            if(r.getLatitude().toLowerCase().contains(""+ll.latitude) && r.getLongitude().toLowerCase().contains(""+ll.longitude)){
                                VisualizarRoda.setRoda(r);
                                context.startActivity(new Intent(context,VisualizarRoda.class));
                            }

                        }
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    private void inserirMarker(LatLng point,String titulo) {
        try {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title(titulo);
            markerOptions.position(point);
            Mapa.mMap.addMarker(markerOptions);
        } catch (Exception e){
            Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public void exibirMensagem(String mensagem){
        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
    }
}
