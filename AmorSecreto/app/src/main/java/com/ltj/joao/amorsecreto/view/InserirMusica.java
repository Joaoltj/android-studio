package com.ltj.joao.amorsecreto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.ltj.joao.amorsecreto.R;
import com.ltj.joao.amorsecreto.controller.FirebaseDB;
import com.ltj.joao.amorsecreto.model.CD;
import com.ltj.joao.amorsecreto.model.Musica;

import java.util.ArrayList;
import java.util.List;

public class InserirMusica extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inserir_musica);
        FirebaseDB.initFirebase();
        DatabaseReference dr  = FirebaseDB.getDr("CDS");
        CD cd = new CD();
        cd.setImagemUrl("amorsecreto.png");
        cd.setKey("1");
        cd.setNome("Falando de Amor");
        Musica msc = new Musica();
        msc.setKey("1");
        msc.setFaixa("Faixa 1");
        msc.setNome("Já era");
        msc.setUrl("jaera.mp3");
        Musica msc2 = new Musica();
        msc2.setKey("2");
        msc2.setFaixa("Faixa 2");
        msc2.setNome("Já era");
        msc2.setUrl("sovoce.mp3");
        List<Musica> musicas = new ArrayList<>();
        musicas.add(msc);
        musicas.add(msc2);
        cd.setMusicas(musicas);
        dr.child("0").setValue(cd);
    }
}
