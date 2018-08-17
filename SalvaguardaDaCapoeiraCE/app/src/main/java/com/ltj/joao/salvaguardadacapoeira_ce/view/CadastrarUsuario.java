package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoUsuario;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Usuario;

public class CadastrarUsuario extends AppCompatActivity {
    private EditText email,senha,rsenha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_usuario);
        this.email = findViewById(R.id.cadastrar_email);
        this.senha = findViewById(R.id.cadastrar_senha);
        this.rsenha = findViewById(R.id.cadastrar_rsenha);
    }

    public void cadastrarUsuario(View view){
        String semail = email.getText().toString();
        String ssenha = senha.getText().toString();
        String srsenha = rsenha.getText().toString();
        if(!semail.trim().equals("") && !ssenha.trim().equals("") &&  !srsenha.trim().equals("")  ){
            if(ssenha.equals(srsenha)){
                Usuario u = new Usuario();
                u.setId(String.valueOf(System.currentTimeMillis()));
                u.setEmail(semail);
                u.setSenha(ssenha);
                OperacaoUsuario ou = new OperacaoUsuario(this);
                ou.inserir(u);
                exibirMensagem("Inserido com sucesso");
                finish();
            }else{
                exibirMensagem("Senhas não correspondem");
            }


        }else{
            if(semail.trim().equals("")){
                email.requestFocus();
            }else if(ssenha.trim().equals("")){
                senha.requestFocus();
            }else{
                rsenha.requestFocus();
            }
            exibirMensagem("Pŕeencha os campos corretamente");
        }
    }

    public void exibirMensagem(String mensagem){
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show();
    }

}
