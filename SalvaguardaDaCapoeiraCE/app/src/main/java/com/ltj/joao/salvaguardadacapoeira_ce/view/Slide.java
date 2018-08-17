package com.ltj.joao.salvaguardadacapoeira_ce.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.SlideAdapter;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class Slide extends AppCompatActivity {
    TextView indicador[];
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private Button btnBack,btnNext;
    private int pagina;
    private GifImageView imgCarregando;
    CapoeiraDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);



        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        if(preferences.getBoolean("slide",false) == true){
            btnNext = (Button)findViewById(R.id.btnNext);

            btnNext.setEnabled(false);
            btnNext.setVisibility(View.GONE);
            btnNext.setText("");
            loading();
            verificarConexao();

        }else{
            viewPager = (ViewPager)findViewById(R.id.viewPager);
            viewPager.setAdapter(new SlideAdapter(this));
            viewPager.setOnPageChangeListener(listener);
            linearLayout = (LinearLayout)findViewById(R.id.linearLayout);
            btnNext = (Button)findViewById(R.id.btnNext);

            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(btnNext.getText().toString().equals("Finalizar")){


                        btnNext.setEnabled(false);
                        btnNext.setVisibility(View.GONE);
                        btnNext.setText("");
                        btnBack.setEnabled(false);
                        btnBack.setVisibility(View.GONE);
                        btnBack.setText("");
                        linearLayout.setVisibility(View.GONE);
                        //finish();

                        editor.putBoolean("slide",true);
                        editor.apply();

                        verificarConexao();

                    }else{
                        viewPager.setCurrentItem(pagina+1);
                    }

                }
            });
            btnBack = (Button)findViewById(R.id.btnBack);

            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    viewPager.setCurrentItem(pagina-1);
                }
            });
            adicionarIndicador();
        }




    }

    public void loading(){
        this.imgCarregando = (GifImageView)findViewById(R.id.imgCarregando);
        this.imgCarregando.setImageResource(R.drawable.carregando);

    }

    public void verificarConexao(){
        try{

            db = new CapoeiraDB();
            Thread th = new Thread(){
                @Override
                public void run(){
                    final String resposta = db.verificarConexao();

                    if(resposta.equals("1")){

                        Intent it = new Intent(getApplicationContext(),SemConexao.class);
                        it.putExtra("tipo","Verifique sua conexão");
                        startActivity(it);
                        overridePendingTransition(R.anim.slide_left,R.anim.slide_right);
                        finish();
                    }else if(resposta.equals("2")){

                        Intent it = new Intent(getApplicationContext(),SemConexao.class);
                        it.putExtra("tipo","Sem conexão");
                        startActivity(it);
                        overridePendingTransition(R.anim.slide_left,R.anim.slide_right);
                        finish();
                    }else if(resposta.equals("3")){

                        Intent it = new Intent(getApplicationContext(),SemConexao.class);
                        it.putExtra("tipo","Sem conexão");
                        startActivity(it);
                        overridePendingTransition(R.anim.slide_left,R.anim.slide_right);
                        finish();



                    }else if(resposta.equals("conectado")){
                        //imgCarregando = (ImageView)findViewById(R.id.imgCarregando);
                        //imgCarregando.setImageResource(R.drawable.carregando);


                        Intent it = new Intent(getApplicationContext(), Principal.class);
                        startActivity(it);
                        overridePendingTransition(R.anim.slide_left,R.anim.slide_right);
                        finish();


                    }else{


                        Intent it = new Intent(getApplicationContext(), Principal.class);
                        startActivity(it);
                        overridePendingTransition(R.anim.slide_left,R.anim.slide_right);
                        finish();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                        }
                    });
                }
            };th.start();
        }catch (Exception e){

        }
    }
    @Override
    public void finish(){
        super.finish();

    }

    public void adicionarIndicador() {
        this.indicador = new TextView[3];
        for (int i = 0; i < 3; i++) {
            indicador[i] = new TextView(this);
            indicador[i].setText(Html.fromHtml("&#8226;"));
            indicador[i].setTextSize(35);
            if (i == 0) {
                indicador[i].setTextColor(getResources().getColor(R.color.colorWhite));
            } else {
                indicador[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            }

            linearLayout.addView(indicador[i]);
        }
    }

    public void trocarCorIndicador(int position){

        if(position == 0){
            indicador[1].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            indicador[2].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
        } else if(position == 1){
            indicador[0].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            indicador[2].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
        }
        else{
            indicador[0].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            indicador[1].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
        }

        indicador[position].setTextColor(getResources().getColor(R.color.colorWhite));

    }
    public void buttonOpcao(int position){
        if(position == 0){
            btnBack.setEnabled(false);
            btnBack.setText("");
            btnBack.setVisibility(View.INVISIBLE);
            btnNext.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("Avançar");
        }else if(position == 1){
            btnBack.setEnabled(true);
            btnBack.setText("Voltar");
            btnBack.setVisibility(View.VISIBLE);
            btnNext.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("Avançar");
        }else{
            btnBack.setEnabled(true);
            btnBack.setText("Voltar");
            btnBack.setVisibility(View.VISIBLE);
            btnNext.setEnabled(true);
            btnNext.setVisibility(View.VISIBLE);
            btnNext.setText("Finalizar");
        }

    }






    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            pagina = position;
            trocarCorIndicador(position);
            buttonOpcao(position);




        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


}
