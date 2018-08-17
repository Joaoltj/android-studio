package com.ltj.joao.amorsecreto.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.View;

import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.ltj.joao.amorsecreto.R;
import com.ltj.joao.amorsecreto.controller.AdapterAgenda;
import com.ltj.joao.amorsecreto.controller.FirebaseDB;
import com.ltj.joao.amorsecreto.controller.OperacaoAgenda;
import com.ltj.joao.amorsecreto.model.Festa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Agenda extends AppCompatActivity {
    private RecyclerView lstAgenda;
    private OperacaoAgenda opAgenda;
    private AdapterAgenda adpAgenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);
        this.lstAgenda = (RecyclerView)findViewById(R.id.lstAgenda);
        this.lstAgenda.setHasFixedSize(true);
        LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        this.lstAgenda.setLayoutManager(layoutManager);
        this.lstAgenda.setLayoutManager(layoutManager);
        this.opAgenda = new OperacaoAgenda(getApplicationContext());
        listar();
    }

    public void initIntent(){
        startActivity(new Intent(this,AgendaInsert.class));
    }
    public void listar(){
        opAgenda.listar(lstAgenda);
    }

    public void inserir(View v){
        startActivity(new Intent(this,AgendaInsert.class));

    }

}
