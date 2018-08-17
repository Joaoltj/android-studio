package com.ltj.joao.amorsecreto.controller;
import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ltj.joao.amorsecreto.model.Imagem;
import com.ltj.joao.amorsecreto.view.Dashboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OperacaoImagem implements AdapterImagem.OnItemClickListener{

    private DatabaseReference dr;
    private StorageReference sr;
    private Imagem imagem;
    private ProgressBar pbProgresso;
    private List<Imagem> galeria = new ArrayList<Imagem>();
    private String key;
    private Context context;
    private AdapterImagem adpImagem;
    SharedPreferences preferences;
    private DownloadFile dFile;


    public OperacaoImagem(Context context){
        FirebaseDB.initFirebase();
        FirebaseSG.initFirebase();
        dr = FirebaseDB.getDr("imagens");
        sr = FirebaseSG.getSr("imagens");
        this.context = context;
    }


    public void upload(Uri imageUri, final String descricao, final String extensao, ProgressBar progressBar, final Button btn){

        btn.setEnabled(false);
        this.pbProgresso = progressBar;
        this.key = String.valueOf(System.currentTimeMillis());
       final String nome = key+"."+extensao;

        sr.child(nome).putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                sr.child(nome).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imagem = new Imagem();
                        imagem.setKey(key);
                        imagem.setDescricao(descricao);
                        imagem.setUrl(uri.toString());
                        imagem.setExtensao(extensao);
                        dr.child(key).setValue(imagem);
                        pbProgresso.setProgress(0);
                        btn.setEnabled(true);
                        exibirMensagem("Upload feito com sucesso");
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot ts) {
                double progresso = (100.0 * ts.getBytesTransferred()/ts.getTotalByteCount());
                pbProgresso.setProgress((int)progresso);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                exibirMensagem("Ocorreu um erro tente novemente");
            }
        }).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                exibirMensagem("Upload cancelado");
            }
        });
    }

    public void exibirMensagem(String mensagem){
        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
    }

    public void remover(final Imagem i){
        exibirMensagem("remover");
        sr.getStorage().getReferenceFromUrl(i.getUrl()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                dr.child(i.getKey()).removeValue();
                exibirMensagem("Removido com sucesso");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                exibirMensagem(e.toString());
            }
        });
    }
    public void baixar(Imagem i){

        if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            dFile = new DownloadFile(context);
            if(i.getDescricao() !=  ""){
                dFile.download(i.getUrl(),"Imagens",i.getDescricao()+"."+i.getExtensao());
            }else{
                dFile.download(i.getUrl(),"Imagens","amorsecreto"+"."+i.getExtensao());
            }


        }else{
            Toast.makeText(context,"Permissão negada - Configure seu dispositivo",Toast.LENGTH_SHORT).show();
        }


    }

    public void listar(final RecyclerView lstGaleria){
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot ds) {

                galeria.clear();
                for(DataSnapshot tsImagem:ds.getChildren()){

                    Imagem i = tsImagem.getValue(Imagem.class);

                    galeria.add(i);
                }

                adpImagem = new AdapterImagem(context,galeria);
                adpImagem.setOnItemClickListener(OperacaoImagem.this);
                lstGaleria.setAdapter(adpImagem);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void itemClick(int position) {
        //Toast.makeText(context,"CLicou",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void itemClickBaixar(int position) {
        baixar(galeria.get(position));
    }

    @Override
    public void itemClickRemover(int position) {
        remover(galeria.get(position));
    }
}
