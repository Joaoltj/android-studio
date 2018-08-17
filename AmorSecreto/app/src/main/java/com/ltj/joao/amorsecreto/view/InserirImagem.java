package com.ltj.joao.amorsecreto.view;


import android.content.ContentResolver;

import android.content.Intent;

import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.ltj.joao.amorsecreto.R;

import com.ltj.joao.amorsecreto.controller.OperacaoImagem;
import com.squareup.picasso.Picasso;


public class InserirImagem extends AppCompatActivity {

    private final int IMAGEM_REQUEST = 1;
    private Button btnEscolherFoto,btnUpload;
    private TextView txtVerFotos;
    private ProgressBar pbProgresso;
    private ImageView ivFoto;
    private Uri imageUri;
    private String extensao;
    private EditText edtDescricao;
    private OperacaoImagem opImagem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagem);

        opImagem = new OperacaoImagem(this);
        btnEscolherFoto = (Button)findViewById(R.id.btnEscolherFoto);
        btnUpload = (Button)findViewById(R.id.btnUpload);
        txtVerFotos = (TextView) findViewById(R.id.txtVerFotos);
        pbProgresso = (ProgressBar) findViewById(R.id.pbProgresso);
        ivFoto = (ImageView) findViewById(R.id.ivFoto);
        edtDescricao = (EditText)findViewById(R.id.edtDescricaoImagem);
        btnEscolherFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(imageUri != null){

                    opImagem.upload(imageUri,edtDescricao.getText().toString(),extensao,pbProgresso,btnUpload);

                }
            }
        });

    }
    public void openGallery(){
        Intent it = new Intent();
        it.setType("image/*");
        it.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(it,IMAGEM_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGEM_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
           Picasso.get().load(imageUri).fit().centerInside().into(ivFoto);
           extensao = getExtensao(imageUri);
        }
    }
    public String getExtensao(Uri uri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(cr.getType(uri));
    }

}
