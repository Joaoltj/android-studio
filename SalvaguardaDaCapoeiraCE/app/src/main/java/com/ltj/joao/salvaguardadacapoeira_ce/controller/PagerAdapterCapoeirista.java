package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ltj.joao.salvaguardadacapoeira_ce.R;

/**
 * Created by João Santos on 14/03/2018.
 */

public class PagerAdapterCapoeirista extends PagerAdapter{
    private Context context;
    public ArrayAdapter<String> adapterGraduacao;
    public View viewc1;
    private LayoutInflater layoutInflater;
    public ArrayAdapter<String> adapterEstilos;
    public PagerAdapterCapoeirista(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup conteiner,int position){


        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = null;
        if(position == 0){


        }else if(position == 1){
            view = layoutInflater.inflate(R.layout.cadastrar_capoeirista_c2,null);
            Spinner graduacaoCapoeirista = (Spinner)view.findViewById(R.id.spnGraduacaoCapoeirista);
            Spinner graduacaoMestre = (Spinner)view.findViewById(R.id.spnGraduacaoMestre);
            Spinner estiloCapoeirista = (Spinner)view.findViewById(R.id.spnEstiloCapoeirista);
            String graduacoes[] = {"Mestre","Contramestre","Professor","Treinel","Aluno formado","Aluno"};
            String estilos[] = {"Angola","Regional","Capoeira de Rua","Contemporânea","Capoeira"};
            adapterGraduacao = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,graduacoes);
             adapterEstilos = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,estilos);
            graduacaoCapoeirista.setAdapter(adapterGraduacao);
            graduacaoMestre.setAdapter(adapterGraduacao);
            estiloCapoeirista.setAdapter(adapterEstilos);
        }else{
            view = layoutInflater.inflate(R.layout.cadastrar_capoeirista_c2,null);
            Spinner graduacaoCapoeirista = (Spinner)view.findViewById(R.id.spnGraduacaoCapoeirista);
            Spinner graduacaoMestre = (Spinner)view.findViewById(R.id.spnGraduacaoMestre);
            Spinner estiloCapoeirista = (Spinner)view.findViewById(R.id.spnEstiloCapoeirista);
            String graduacoes[] = {"Mestre","Contramestre","Professor","Treinel","Aluno formado","Aluno"};
            String estilos[] = {"Angola","Regional","Capoeira de Rua","Contemporânea","Capoeira"};
            adapterGraduacao = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,graduacoes);
            adapterEstilos = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_dropdown_item,estilos);
            graduacaoCapoeirista.setAdapter(adapterGraduacao);
            graduacaoMestre.setAdapter(adapterGraduacao);
            estiloCapoeirista.setAdapter(adapterEstilos);
        }

        conteiner.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup conteiner,int position,Object object){
        conteiner.removeView((View)object);
    }
}
