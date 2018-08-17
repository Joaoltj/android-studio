package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoGrupo;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Foto;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;


public class CadastrarGrupo extends AppCompatActivity {
    private EditText edtNomeGrupo,edtNomeResponsavel,edtMestreGrupo,edtEstadoGrupo,edtCidadeGrupo,edtBairroGrupo,edtRuaGrupo,edtComplemento;
    private   Grupo grupo;
    private final int PERMISION_REQUEST = 13;
    private final int INTENT_FOTO = 13;
    private CircleImageView fotoGrupo = null;
    private Uri imagemSelecionada =null;
    private OperacaoGrupo og;
    private String imgDefault = "NULL";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_grupo);
        preferences = getSharedPreferences("usuario",MODE_PRIVATE);
        editor = preferences.edit();
        og = new OperacaoGrupo(this,preferences,editor);
        permissao();
        this.edtNomeGrupo = (EditText)findViewById(R.id.edtNomeGrupo);
        this.edtNomeResponsavel = (EditText)findViewById(R.id.edtNomeResponsavel);
        this.edtMestreGrupo = (EditText)findViewById(R.id.edtMestreGrupo);
        this.edtEstadoGrupo = (EditText)findViewById(R.id.edtEstadoGrupo);
        this.edtCidadeGrupo = (EditText)findViewById(R.id.edtCidadeGrupo);
        this.edtBairroGrupo = (EditText)findViewById(R.id.edtBairroGrupo);
        this.edtRuaGrupo = (EditText)findViewById(R.id.edtRuaGrupo);
        this.edtComplemento = (EditText)findViewById(R.id.edtComplementoGrupo);
        fotoGrupo = (CircleImageView)findViewById(R.id.ivFotoGrupo);
        if(preferences.getBoolean("grupo",false)){
            initGrupo();
        }

    }

    public void initGrupo(){
        this.edtNomeGrupo.setText(preferences.getString("grupo-nome",""));
        this.edtNomeResponsavel.setText(preferences.getString("grupo-responsavel",""));;
        this.edtMestreGrupo.setText(preferences.getString("grupo-mestre",""));
        this.edtEstadoGrupo.setText(preferences.getString("grupo-estado",""));
        this.edtCidadeGrupo.setText(preferences.getString("grupo-cidade",""));
        this.edtBairroGrupo.setText(preferences.getString("grupo-bairro",""));
        this.edtRuaGrupo.setText(preferences.getString("grupo-rua",""));
        this.edtComplemento.setText(preferences.getString("grupo-complemento",""));
        imgDefault = preferences.getString("grupo-url-imagem","");
        if(!imgDefault.equals("NULL")){
            Picasso.get().load(imgDefault).into(fotoGrupo);
        }



    }

    public void permissao(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            Log.i("if1","entrou");
        }if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Log.i("if2","entrou");
        }else{


            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISION_REQUEST);
            Log.i("CadastrarCapoeirista","1if");


        }
    }
    public void cadastrarGrupo(View view){






        if(!this.edtNomeGrupo.getText().toString().trim().equals("") && !this.edtNomeResponsavel.getText().toString().trim().equals("") && !this.edtMestreGrupo.getText().toString().trim().equals("")  && !this.edtEstadoGrupo.getText().toString().trim().equals("") && !this.edtCidadeGrupo.getText().toString().trim().equals("") && !this.edtBairroGrupo.getText().toString().trim().equals("") && !this.edtRuaGrupo.getText().toString().trim().equals("")){
            setCadastrar(false);
            grupo = new Grupo();
            grupo.setId(preferences.getString("id","null"));
            grupo.setNomeGrupo(this.edtNomeGrupo.getText().toString());
            grupo.setNomeResponsavel(this.edtNomeResponsavel.getText().toString());
            grupo.setMestreGrupo(this.edtMestreGrupo.getText().toString());
            grupo.setEstado(this.edtEstadoGrupo.getText().toString());
            grupo.setCidade(this.edtCidadeGrupo.getText().toString());
            grupo.setBairro(this.edtBairroGrupo.getText().toString());
            grupo.setRua(this.edtRuaGrupo.getText().toString());
            grupo.setComplemento(this.edtComplemento.getText().toString().trim().equals("")?"NULL":edtComplemento.getText().toString());

          og.inserir(grupo,imagemSelecionada,imgDefault);
            inserirPreferences(grupo);
            finish();

        }else{

            if(this.edtNomeGrupo.getText().toString().trim().equals("")){
                this.edtNomeGrupo.requestFocus();
            }else if(this.edtNomeResponsavel.getText().toString().trim().equals("")){
                this.edtNomeResponsavel.requestFocus();
            }else if(this.edtMestreGrupo.getText().toString().trim().equals("")){
                this. edtMestreGrupo.requestFocus();
            }else if(this.edtEstadoGrupo.getText().toString().trim().equals("")){
                this.edtEstadoGrupo.requestFocus();
            }else if(this.edtCidadeGrupo.getText().toString().trim().equals("")){
                this.edtCidadeGrupo.requestFocus();
            }else if(this.edtBairroGrupo.getText().toString().trim().equals("")){
                this.edtBairroGrupo.requestFocus();
            }else{
                this.edtRuaGrupo.requestFocus();
            }


        }



    }

    public void setCadastrar(boolean op){

        Button button = (Button)findViewById(R.id.btnCadastrarGrupo);
        if(op == false){
            button.setText("Cadastrando...");
            button.setEnabled(op);
        }else {
            button.setText("Cadastrar");
            button.setEnabled(op);
        }

    }
    public void limparCampos(){
        this.edtNomeGrupo.setText("");
        this.edtNomeResponsavel.setText("");
        this.edtMestreGrupo.setText("");
        this.edtEstadoGrupo.setText("");
        this.edtCidadeGrupo.setText("");
        this.edtBairroGrupo.setText("");
        this.edtRuaGrupo.setText("");
        this.edtComplemento.setText("");
        fotoGrupo.setImageResource(R.drawable.ic_inserir_foto_grupo);
        fotoGrupo = null;
    }
    public void inserirPreferences(Grupo g){

        editor.putString("grupo-id",g.getId());
        editor.putString("grupo-nome",g.getNomeGrupo());
        editor.putString("grupo-mestre",g.getMestreGrupo());
        editor.putBoolean("grupo",true);
        editor.putString("grupo-responsavel",g.getNomeResponsavel());
        editor.putString("grupo-estado",g.getEstado());
        editor.putString("grupo-cidade",g.getCidade());
        editor.putString("grupo-bairro",g.getBairro());
        editor.putString("grupo-rua",g.getRua());
        editor.putString("grupo-complemento",g.getComplemento());
        editor.apply();
    }
    public void selecionarFotoGrupo(View view){
        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        it.setType("image/*");
        startActivityForResult(it,INTENT_FOTO);
    }
    @Override
    public void onRequestPermissionsResult(int resultCode,String[] permissions,int [] grantResults){
        if(resultCode == PERMISION_REQUEST){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }else {

            }
            return;
        }
    }

    //pega imagem da galeria
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == INTENT_FOTO) {
                imagemSelecionada = intent.getData();
                Picasso.get().load(imagemSelecionada).into(fotoGrupo);

            }
        }
    }
}
