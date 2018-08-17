package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.CapoeiristaAdapter;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.ItemClickListener;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoCapoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoUsuario;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;

;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VisualizarCapoeiristas extends AppCompatActivity{

    List<Capoeirista> capoeiristas = new ArrayList<Capoeirista>();
    RecyclerView lstCapoeiristas;



    OperacaoCapoeirista oc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_capoeiristas);
        configurar();


    }




    public void configurar(){
        oc = new OperacaoCapoeirista(this,null,null);



        lstCapoeiristas = (RecyclerView)findViewById(R.id.lstCapoeiristas);

        lstCapoeiristas.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        lstCapoeiristas.setLayoutManager(layoutManager);
        oc.listar(lstCapoeiristas);
    }







}
