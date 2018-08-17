package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.widget.Toast;
import android.support.v7.widget.SearchView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Usuario;
import com.ltj.joao.salvaguardadacapoeira_ce.view.CadastrarCapoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.view.Perfil;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarCapoeiristas;

import java.util.ArrayList;
import java.util.List;

public class OperacaoGrupo {
    public FirebaseDatabase fb;
    public DatabaseReference dr;
    public FirebaseStorage fs;
    public StorageReference sr;
    public Context context;
    public Grupo grupo;

    public SharedPreferences preferences;
    public OperacaoUsuario ou;
    GrupoAdapter adapter;
    public SharedPreferences.Editor editor;
    Usuario usuario;
    List<Grupo>grupos = new ArrayList<>();
    public OperacaoGrupo(Context context, SharedPreferences preferences, SharedPreferences.Editor editor){
        FirebaseDB.init();
        FirebaseSG.init();
        this.context = context;
        this.fb = FirebaseDB.getFb();
        this.dr = FirebaseDB.getDr("usuarios");
        this.fs = FirebaseSG.getFs();
        this.sr = FirebaseSG.getSr("grupos");
        this.editor = editor;
        this.preferences = preferences;
        ou = new OperacaoUsuario(context);
        ou.listar();




    }
    public void inserir(Grupo g, Uri imagem,String imgDefault) {
        if(imagem != null){

        }

        this.grupo = g;
        for(Usuario u:ou.usuarios){

            if(u.getId().equals(preferences.getString("id",""))){
                usuario = u;
                break;
            }
        }
        usuario.setGrupo(grupo);

        if(imagem != null){
            sr.child(usuario.getId()).putFile(imagem).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    sr.child(usuario.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            grupo.setUrlImagem(uri.toString());
                            editor.putString("grupo-url-imagem",grupo.getUrlImagem());
                            editor.apply();
                            dr.child(usuario.getId()).setValue(usuario);
                            Toast.makeText(context,"Salvo com sucesso",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            grupo.setUrlImagem(imgDefault);
            usuario.setGrupo(grupo);
            Toast.makeText(context,"Salvo com sucesso",Toast.LENGTH_SHORT).show();
            editor.apply();
            dr.child(usuario.getId()).setValue(usuario);
        }


    }



    public void listar(final RecyclerView recyclerView){

               dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               grupos.clear();
                for(DataSnapshot dsLIsta:dataSnapshot.getChildren()){
                   Usuario u = dsLIsta.getValue(Usuario.class);
                    if(u.getGrupo() != null) {
                        grupos.add(u.getGrupo());
                    }

                }
               adapter = new GrupoAdapter(context,grupos);
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }










    public void exibirMensagem(String mensagem){
        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
   }
}
