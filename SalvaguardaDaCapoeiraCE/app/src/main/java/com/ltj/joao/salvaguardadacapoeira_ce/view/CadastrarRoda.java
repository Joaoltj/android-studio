package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoRoda;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;

import java.io.IOException;
import java.util.List;

public class CadastrarRoda extends AppCompatActivity {
    private FragmentTransaction transaction;
    private FragmentManager fragmentManager;
    private Geocoder geocoder;
    private CapoeiraDB db;
    private Roda roda;
    private OperacaoRoda or;
    public static EditText edtLatitude,edtLongitude,edtEstado,edtCidade,edtBairro,edtRua,edtComplemeto;
    private EditText edtDiaSemana,edtHorarioRoda,edtResponsavelRoda,edtOrganizadorRoda;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_roda);
        db = new CapoeiraDB();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.contentRoda,new MapaRoda(),"MapaRoda");
        transaction.commitAllowingStateLoss();
        edtLatitude = (EditText)findViewById(R.id.edtLatitude);
        edtLongitude = (EditText)findViewById(R.id.edtLongitude);
        edtEstado = (EditText)findViewById(R.id.edtEstadoRoda);
        edtCidade = (EditText)findViewById(R.id.edtCidadeRoda);
        edtBairro = (EditText)findViewById(R.id.edtBairroRoda);
        edtRua = (EditText)findViewById(R.id.edtRuaRoda);
        edtComplemeto = (EditText)findViewById(R.id.edtComplementoRoda);

        edtDiaSemana = (EditText)findViewById(R.id.edtDiaSemana);
        edtHorarioRoda = (EditText)findViewById(R.id.edtHorarioRoda);
        edtResponsavelRoda = (EditText)findViewById(R.id.edtResponsavelRoda);
        edtOrganizadorRoda = (EditText)findViewById(R.id.edtGrupoOrganizador);



        edtLatitude.setEnabled(false);
        edtLongitude.setEnabled(false);

        preferences = getSharedPreferences("usuario",MODE_PRIVATE);
        editor = preferences.edit();
        or = new OperacaoRoda(this,preferences,editor);
        if(preferences.getBoolean("roda",false)){
            initRoda();
        }
    }
    public void initRoda(){
        edtLatitude.setText(preferences.getString("roda-latitude",""));
        edtLongitude.setText(preferences.getString("roda-longitude",""));
        edtEstado.setText(preferences.getString("roda-estado",""));
        edtCidade.setText(preferences.getString("roda-cidade",""));
        edtBairro.setText(preferences.getString("roda-bairro",""));
        edtRua.setText(preferences.getString("roda-rua",""));
        edtComplemeto.setText(preferences.getString("roda-complemento",""));

        edtDiaSemana.setText(preferences.getString("roda-dia",""));
        edtHorarioRoda.setText(preferences.getString("roda-horario",""));
        edtResponsavelRoda.setText(preferences.getString("roda-responsavel",""));
        edtOrganizadorRoda.setText(preferences.getString("roda-organizador",""));



    }
    public void inserirPreferences(Roda r){
        editor.putBoolean("roda",true);
        editor.putString("roda-dia",r.getDiaDaSemana());
        editor.putString("roda-horario",r.getHorario());
        editor.putString("roda-organizador",r.getGrupoOrganizador());
        editor.putString("roda-responsavel",r.getResponsavel());
        editor.putString("roda-estado",r.getEstado());
        editor.putString("roda-cidade",r.getCidade());
        editor.putString("roda-bairro",r.getBairro());
        editor.putString("roda-rua",r.getRua());
        editor.putString("roda-complemento",r.getDiaDaSemana());
        editor.putString("roda-latitude",r.getLatitude());
        editor.putString("roda-longitude",r.getLongitude());
        editor.apply();
    }
    public void obterLatLong(View view){
    if(!edtEstado.getText().toString().trim().equals("") && !edtCidade.getText().toString().trim().equals("") && !edtBairro.getText().toString().trim().equals("") && !edtRua.getText().toString().trim().equals("")){
        try {
            geocoder = new Geocoder(CadastrarRoda.this);
            String local = edtRua.getText().toString()+","+edtBairro.getText().toString()+","+edtCidade.getText().toString()+","+edtEstado.getText().toString()+",Brasil";
            List<Address> enderecoLista = geocoder.getFromLocationName(local,1);

            LatLng latLng = new LatLng(enderecoLista.get(0).getLatitude(),enderecoLista.get(0).getLongitude());
            edtLatitude.setText(""+latLng.latitude);
            edtLongitude.setText(""+latLng.longitude);
            MapaRoda.addMarker(latLng);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }else{
        if(edtEstado.getText().toString().trim().equals("")){
            edtEstado.requestFocus();
        }else if(edtCidade.getText().toString().trim().equals("")){
            edtCidade.requestFocus();
        }else if(edtBairro.getText().toString().trim().equals("")){
            edtBairro.requestFocus();
        }else if(edtRua.getText().toString().trim().equals("")){
            edtRua.requestFocus();
        }
    }


    }
    public void setCadastrar(boolean op){

        Button button = (Button)findViewById(R.id.btnCadastrarRoda);
        if(op == false){
            button.setText("Cadastrando...");
            button.setEnabled(op);
        }else {
            button.setText("Cadastrar");
            button.setEnabled(op);
        }

    }

    public void limparCampos(){
        this.edtDiaSemana.setText("");
        this.edtHorarioRoda.setText("");
        this.edtResponsavelRoda.setText("");
        this.edtOrganizadorRoda.setText("");
        this.edtCidade.setText("");
        this.edtEstado.setText("");
        this.edtBairro.setText("");
        this.edtRua.setText("");
        this.edtComplemeto.setText("");
        this.edtLatitude.setText("");
        this.edtLongitude.setText("");
        finish();
    }


    public void cadastrarRoda(View view){
        if(!edtDiaSemana.getText().toString().trim().equals("") && !edtHorarioRoda.getText().toString().trim().equals("") && !edtResponsavelRoda.getText().toString().trim().equals("") && !edtOrganizadorRoda.getText().toString().trim().equals("") && !edtEstado.getText().toString().trim().equals("") && !edtCidade.getText().toString().trim().equals("") && !edtBairro.getText().toString().trim().equals("") && !edtRua.getText().toString().trim().equals("") && !edtLatitude.getText().toString().trim().equals("") && !edtLongitude.getText().toString().trim().equals("")){

               setCadastrar(false);
               roda = new Roda();
               roda.setDiaDaSemana(edtDiaSemana.getText().toString());
               roda.setHorario(edtHorarioRoda.getText().toString());
               roda.setGrupoOrganizador(edtOrganizadorRoda.getText().toString());
               roda.setResponsavel(edtResponsavelRoda.getText().toString());
               roda.setEstado(edtEstado.getText().toString());
               roda.setCidade(edtCidade.getText().toString());
               roda.setBairro(edtBairro.getText().toString());
               roda.setRua(edtRua.getText().toString());
               roda.setComplemento(!edtComplemeto.getText().toString().trim().equals("")?edtComplemeto.getText().toString():"");
               roda.setLatitude(edtLatitude.getText().toString());
               roda.setLongitude(edtLongitude.getText().toString());

               or.inserir(roda);
            inserirPreferences(roda);
                                   setCadastrar(true);
                                   limparCampos();





        }else{
            if(edtDiaSemana.getText().toString().trim().equals("")){
                edtDiaSemana.requestFocus();
            }else if(edtHorarioRoda.getText().toString().trim().equals("")){
                edtHorarioRoda.requestFocus();
            }else if(edtOrganizadorRoda.getText().toString().trim().equals("")){
                edtOrganizadorRoda.requestFocus();
            }else if(edtResponsavelRoda.getText().toString().trim().equals("")){
                edtResponsavelRoda.requestFocus();
            }else if(edtEstado.getText().toString().trim().equals("")){
                edtEstado.requestFocus();
            }else if(edtCidade.getText().toString().trim().equals("")){
                edtCidade.requestFocus();
            }else if(edtBairro.getText().toString().trim().equals("")){
                edtBairro.requestFocus();
            }else if(edtRua.getText().toString().trim().equals("")){
                edtRua.requestFocus();
            }else if(edtLatitude.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(),"Clique em Obter latitude",Toast.LENGTH_SHORT).show();
            }else if(edtLongitude.getText().toString().trim().equals("")){
                Toast.makeText(getApplicationContext(),"Clique em Obter latitude",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
