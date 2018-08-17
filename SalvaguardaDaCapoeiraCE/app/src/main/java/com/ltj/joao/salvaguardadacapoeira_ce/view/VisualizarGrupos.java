package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.CapoeiristaAdapter;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.GrupoAdapter;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoGrupo;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

public class VisualizarGrupos extends AppCompatActivity{


private GrupoAdapter adapter;
private RecyclerView lstGrupos;
private OperacaoGrupo og;
private SharedPreferences preferences;
private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_grupos);
        preferences = getSharedPreferences("usuario",MODE_PRIVATE);
        editor = preferences.edit();
        og = new OperacaoGrupo(this,preferences,editor);
       configurar();
    }


    public void configurar(){

        lstGrupos = (RecyclerView)findViewById(R.id.lstGrupos);

        lstGrupos.setHasFixedSize(true);
        // adapter.setViewBinder(new ViewBinderAdapter());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        lstGrupos.setLayoutManager(layoutManager);
        og.listar(lstGrupos);
    }

}
