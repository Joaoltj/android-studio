package com.ltj.joao.amorsecreto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ltj.joao.amorsecreto.R;
import com.ltj.joao.amorsecreto.controller.OperacaoImagem;

public class ImagemView extends AppCompatActivity {
    private RecyclerView lstGaleria;
    OperacaoImagem opImagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagem_view);
        lstGaleria = (RecyclerView)findViewById(R.id.lstGaleria);
        lstGaleria.setLayoutManager(new LinearLayoutManager(this));
        lstGaleria.hasFixedSize();
        opImagem = new OperacaoImagem(this);
        listar();
    }
    public void listar(){

        opImagem.listar(lstGaleria);
    }
}
