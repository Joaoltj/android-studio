package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;

public class SemConexao extends AppCompatActivity {
    CapoeiraDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_conexao);
        String tipo = getIntent().getStringExtra("tipo");
        TextView tipoConexao = (TextView)findViewById(R.id.tipoConexao);
        tipoConexao.setText(tipo);

    }

    public void verificarConexao(View view){
        try{

            db = new CapoeiraDB();
            Thread th = new Thread(){
                @Override
                public void run(){
                    final String resposta = db.verificarConexao();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(resposta.equals("1")){

                                Toast.makeText(getApplicationContext(),"Verifique sua conexão",Toast.LENGTH_SHORT).show();
                            }else if(resposta.equals("2")){
                                Toast.makeText(getApplicationContext(),"Sem conexão",Toast.LENGTH_SHORT).show();

                            }else if(resposta.equals("3")){
                                Toast.makeText(getApplicationContext(),"Sem conexão",Toast.LENGTH_SHORT).show();
                            }else if(resposta.equals("conectado")){
                                finish();
                                Intent it = new Intent(getApplicationContext(),Principal.class);
                                startActivity(it);
                            }

                        }
                    });
                }
            };th.start();
        }catch (Exception e){

        }
    }
}
