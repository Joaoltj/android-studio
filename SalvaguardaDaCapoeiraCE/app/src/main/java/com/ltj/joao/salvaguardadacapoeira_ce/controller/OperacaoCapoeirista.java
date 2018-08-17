package com.ltj.joao.salvaguardadacapoeira_ce.controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
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
import com.ltj.joao.salvaguardadacapoeira_ce.model.Usuario;
import com.ltj.joao.salvaguardadacapoeira_ce.view.CadastrarCapoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.view.Perfil;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarCapoeiristas;

import java.util.ArrayList;
import java.util.List;

public class OperacaoCapoeirista implements DialogInterface.OnClickListener {
    public FirebaseDatabase fb;
    public DatabaseReference dr;
    public FirebaseStorage fs;
    public StorageReference sr;
    public Context context;
    private int capoeiristaSelecionado= 0;
    public SharedPreferences preferences;
    public OperacaoUsuario ou;
    public Capoeirista capoeirista;
    CapoeiristaAdapter adapter;
    public SharedPreferences.Editor editor;
    Usuario usuario;
    List<Capoeirista>capoeiristas = new ArrayList<>();
    public OperacaoCapoeirista(Context context, SharedPreferences preferences, SharedPreferences.Editor editor){
        FirebaseDB.init();
        FirebaseSG.init();
        this.context = context;
        this.fb = FirebaseDB.getFb();
        this.dr = FirebaseDB.getDr("usuarios");
        this.fs = FirebaseSG.getFs();
        this.sr = FirebaseSG.getSr("capoeiristas");
        this.editor = editor;
        this.preferences = preferences;
        ou = new OperacaoUsuario(context);
        ou.listar();




    }
    public void inserir(Capoeirista c, Uri imagem,String imgDefault) {
        this.capoeirista = c;
            for(Usuario u:ou.usuarios){

                if(u.getId().equals(preferences.getString("id",""))){
                    usuario = u;
                    break;
                }
            }
            usuario.setCapoeirista(capoeirista);

        if(imagem != null){
            sr.child(usuario.getId()).putFile(imagem).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    sr.child(usuario.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            capoeirista.setUrlImagem(uri.toString());
                            editor.putString("capoeirista-url-imagem",capoeirista.getUrlImagem());
                            editor.apply();
                            dr.child(usuario.getId()).setValue(usuario);
                            Toast.makeText(context,"Salvo com sucesso",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(context,"Tente Novamente",Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            capoeirista.setUrlImagem(imgDefault);
            editor.putString("capoeirista-url-imagem",capoeirista.getUrlImagem());
            editor.apply();
            usuario.setCapoeirista(capoeirista);
            dr.child(usuario.getId()).setValue(usuario);
            Toast.makeText(context,"Salvo com sucesso",Toast.LENGTH_SHORT).show();
        }

    }

    public void listar(final RecyclerView recyclerView){

        //criarDialog();
        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                capoeiristas.clear();
                for(DataSnapshot dsLIsta:dataSnapshot.getChildren()){
                    Usuario u = dsLIsta.getValue(Usuario.class);
                    if(u.getCapoeirista() != null){
                    capoeiristas.add(u.getCapoeirista());
                    }
                }
                Log.i("MrScript",capoeiristas.size()+"");

                    adapter = new CapoeiristaAdapter(context,capoeiristas);
                    recyclerView.setAdapter(adapter);


                //searchView.setOnQueryTextListener(OperacaoCapoeirista.this);
                adapter.setOnItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        capoeiristaSelecionado = position;
                        criarDialog().show();
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    private AlertDialog criarDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        CharSequence[] ops = {"Ver Perfil"};
        builder.setTitle("Opções");
        builder.setItems(ops,OperacaoCapoeirista.this);
      return builder.create();
    }





   @Override
   public void onClick(DialogInterface dialog, int item) {
        switch (item){
            case 0:

              Perfil.setCapoeirista(capoeiristas.get(capoeiristaSelecionado));
                Toast.makeText(context,capoeiristas.get(capoeiristaSelecionado).getNome(),Toast.LENGTH_SHORT).show();
               Intent it = new Intent(context,Perfil.class);
               context.startActivity(it);
               break;
           case 1:
                break;
        }
    }
    public void exibirMensagem(String mensagem){
        Toast.makeText(context,mensagem,Toast.LENGTH_SHORT).show();
    }
}
