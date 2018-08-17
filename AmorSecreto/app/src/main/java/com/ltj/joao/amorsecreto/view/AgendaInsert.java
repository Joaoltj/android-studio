package com.ltj.joao.amorsecreto.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ltj.joao.amorsecreto.R;
import com.ltj.joao.amorsecreto.controller.FirebaseDB;
import com.ltj.joao.amorsecreto.controller.OperacaoAgenda;
import com.ltj.joao.amorsecreto.model.Festa;

import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class AgendaInsert extends AppCompatActivity implements View.OnClickListener{
    private Button btn_insert_save;
    public static Festa festa;
    private EditText edtTitulo,edtLocal,edtData;
    private OperacaoAgenda opAgenda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda_insert);
        btn_insert_save = (Button)findViewById(R.id.btn_insert_save_festa);
        btn_insert_save.setOnClickListener(this);
        edtTitulo = (EditText)findViewById(R.id.edtTitulo);
        edtLocal = (EditText)findViewById(R.id.edtLocal);
        edtData = (EditText)findViewById(R.id.edtData);
        opAgenda = new OperacaoAgenda(this);
        initCampos();

    }

    public void initCampos(){
        if(festa != null){
            btn_insert_save.setText(R.string.salvar_festa);
            edtTitulo.setText(festa.getTitulo());
            edtLocal.setText(festa.getLocal());
            edtData.setText(festa.getData());
        }
    }

    @Override
    public void onClick(View view) {
        String titulo = edtTitulo.getText().toString();
        String local = edtLocal.getText().toString();
        String data = edtData.getText().toString();
        if(festa == null){
            if(!titulo.trim().equals("") && !local.trim().equals("") && !data.trim().equals("")){
                enableButton(false);
                festa = new Festa();
                festa.setUid(String.valueOf(System.currentTimeMillis()));
                festa.setTitulo(titulo);
                festa.setLocal(local);
                festa.setData(data);
                opAgenda.inserir(festa);
                limparCapos();
                exibirMensagem("Festa criada com sucesso");


            }else{
                if(titulo.trim().equals("")){
                    edtTitulo.requestFocus();
                }else if(local.trim().equals("")){
                    edtLocal.requestFocus();
                }else{
                    edtData.requestFocus();
                }
                exibirMensagem("Preencha todos os campos");
            }
        }else{
            if(!titulo.trim().equals("") && !local.trim().equals("") && !data.trim().equals("")){
                enableButton(false);
                festa.setTitulo(titulo);
                festa.setLocal(local);
                festa.setData(data);
                opAgenda.atualizar(festa);
                exibirMensagem("Festa atualizada com sucesso");
                finish();
                festa = null;

            }else{
                if(titulo.trim().equals("")){
                    edtTitulo.requestFocus();
                }else if(local.trim().equals("")){
                    edtLocal.requestFocus();
                }else{
                    edtData.requestFocus();
                }
                exibirMensagem("Preencha todos os campos");
            }

        }
    }

    public void limparCapos(){
        edtTitulo.setText("");
        edtLocal.setText("");
        edtData.setText("");
        enableButton(true);
        festa = null;

    }
    public void enableButton(boolean en){
        btn_insert_save.setEnabled(en);
    }

    public void exibirMensagem(String mensagem){
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show();
    }
}
