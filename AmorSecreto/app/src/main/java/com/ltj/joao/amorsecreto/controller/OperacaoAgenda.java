package com.ltj.joao.amorsecreto.controller;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ltj.joao.amorsecreto.model.Festa;
import com.ltj.joao.amorsecreto.view.Agenda;
import com.ltj.joao.amorsecreto.view.AgendaInsert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OperacaoAgenda implements AdapterAgenda.OnItemClickListener{
    public DatabaseReference dr;
    private Context context;
    private AdapterAgenda adpAgenda;
    private List<Festa> agenda = new ArrayList<Festa>();
    public OperacaoAgenda(Context context){
        FirebaseDB.initFirebase();
        this.context = context;
        this.dr = FirebaseDB.getDr("agenda");
    }
    public void inserir(Festa festa){
        dr.child(festa.getUid()).setValue(festa);
    }
    public void listar(final RecyclerView lstAgenda){


        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                agenda.clear();
                for(DataSnapshot dsFesta:dataSnapshot.getChildren()){

                    Festa festa = dsFesta.getValue(Festa.class);
                    agenda.add(festa);

                }
                Collections.reverse(agenda);
                adpAgenda = new AdapterAgenda(context,agenda);

                adpAgenda.setOnItemClickListener(OperacaoAgenda.this);
                lstAgenda.setAdapter(adpAgenda);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void atualizar(Festa festa){
        dr.child(festa.getUid()).setValue(festa);
    }

    @Override
    public void itemClick(int position) {

    }

    @Override
    public void itemDelete(int position) {
        Festa festa = agenda.get(position);
        dr.child(festa.getUid()).removeValue();
    }

    @Override
    public void itemUpdate(int position) {
        AgendaInsert.festa = agenda.get(position);
        context.startActivity(new Intent(context,AgendaInsert.class));

    }
}
