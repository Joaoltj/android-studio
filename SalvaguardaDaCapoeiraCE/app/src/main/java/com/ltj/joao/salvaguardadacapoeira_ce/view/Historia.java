package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.HistoriaAdapter;

public class Historia extends AppCompatActivity {
    private ViewPager pagerHistoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historia);

       HistoriaAdapter adapterHistoria = new HistoriaAdapter(this);
        pagerHistoria = (ViewPager)findViewById(R.id.pagerHistoria);
        pagerHistoria.setAdapter(adapterHistoria);


    }


}
