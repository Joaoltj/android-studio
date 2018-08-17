package com.ltj.joao.salvaguardadacapoeira_ce.controller;

;
import android.content.Context;
import android.support.v4.view.PagerAdapter;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ltj.joao.salvaguardadacapoeira_ce.R;


public class SlideAdapter extends PagerAdapter{

    private Context context;
    private LayoutInflater layoutInflater;
    private int imagens[] = {R.drawable.logo,R.drawable.logo,R.drawable.logo};
    private String titulos[] ={"Salvaguarda da Capoeira - CE","",""};



    private String descricoes[] = { "","O aplicativo visa contribuir  na Salvaguarda da Capoeira do Estado do Ceará, possibilitando de forma direta o registro dos sujeitos da capoeira cearense através do cadastro das rodas, mestres e grupos.","Iê vamos jogar camará!"};

    public SlideAdapter (Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return imagens.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object ;
    }

    @Override
    public Object instantiateItem(ViewGroup conteiner, int position){
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,null);

        ImageView imagem = (ImageView)view.findViewById(R.id.slide_imagem);
        imagem.setImageResource(imagens[position]);

        TextView titulo = (TextView)view.findViewById(R.id.slide_titulo);
        titulo.setText(titulos[position]);

        TextView descricao = (TextView)view.findViewById(R.id.slide_descricao);
        descricao.setText(Html.fromHtml("<p align='center'>"+descricoes[position]+"</p>"));

        conteiner.addView(view);



        return view;
    }
    @Override
    public void destroyItem(ViewGroup conteiner, int position,Object object){
        conteiner.removeView((View) object);
    }
}
