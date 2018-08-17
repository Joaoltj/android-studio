package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.controller.OperacaoUsuario;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;

import java.util.List;
import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Usuario;

public class Login extends AppCompatActivity {
    private CapoeiraDB db;
    private List<Capoeirista> capoeiristas;
    private List<Roda> rodas;
    private EditText email,senha;
    private Spinner loginTipo;
    private OperacaoUsuario ou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = (EditText)findViewById(R.id.login_email);
        senha = (EditText)findViewById(R.id.login_senha);
        ou = new OperacaoUsuario(this);
        ou.listar();


    }

    public void cadastrarGo(View view){
        startActivity(new Intent(this,CadastrarUsuario.class));
    }

    public void entrar(View v){

    for(Usuario u:ou.usuarios){
        if(u.getEmail().equals(email.getText().toString()) && u.getSenha().equals(senha.getText().toString()) ){
            exibirMensagem("logado com sucesso");
            SharedPreferences preferences = getSharedPreferences("usuario",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("id",u.getId());
            editor.putString("email",u.getEmail());
            editor.putString("senha",u.getSenha());
            if(u.getCapoeirista() != null){
                editor.putBoolean("capoeirista",true);
                editor.putString("capoeirista-id",u.getCapoeirista().getId());
                editor.putString("capoeirista-nome",u.getCapoeirista().getNome());
                editor.putString("capoeirista-cpf",u.getCapoeirista().getCpf());
                editor.putString("capoeirista-rg",u.getCapoeirista().getRg());
                editor.putString("capoeirista-nascimento",u.getCapoeirista().getDataNascimento());
                editor.putString("capoeirista-sexo",u.getCapoeirista().getSexo());
                editor.putString("capoeirista-telefone",u.getCapoeirista().getTelefone());
                editor.putString("capoeirista-endereco",u.getCapoeirista().getEndereco());
                editor.putString("capoeirista-apelido",u.getCapoeirista().getApelido());
                editor.putString("capoeirista-whatsapp",u.getCapoeirista().getWhatsapp());
                editor.putString("capoeirista-graduacao",u.getCapoeirista().getGraduacao());
                editor.putString("capoeirista-ano-graduacao",u.getCapoeirista().getAnoGraduacao());
                editor.putString("capoeirista-grupo",u.getCapoeirista().getGrupo());
                editor.putString("capoeirista-estilo",u.getCapoeirista().getEstilo());
                editor.putString("capoeirista-nome-mestre",u.getCapoeirista().getNomeMestre());
                editor.putString("capoeirista-apelido-mestre",u.getCapoeirista().getApelidoMestre());
                editor.putString("capoeirista-graduacao-mestre",u.getCapoeirista().getGraduacaoMestre());
                editor.putString("capoeirista-url-imagem",u.getCapoeirista().getUrlImagem());
            }
            if(u.getGrupo() != null){
                editor.putBoolean("grupo",true);
                editor.putString("grupo-id",u.getGrupo().getId());
                editor.putString("grupo-nome",u.getGrupo().getNomeGrupo());
                editor.putString("grupo-mestre",u.getGrupo().getMestreGrupo());
                editor.putString("grupo-url-imagem",u.getGrupo().getUrlImagem());
                editor.putString("grupo-responsavel",u.getGrupo().getNomeResponsavel());
                editor.putString("grupo-estado",u.getGrupo().getEstado());
                editor.putString("grupo-cidade",u.getGrupo().getCidade());
                editor.putString("grupo-bairro",u.getGrupo().getBairro());
                editor.putString("grupo-rua",u.getGrupo().getRua());
                editor.putString("grupo-complemento",u.getGrupo().getComplemento());

            }
            if(u.getRoda() != null){
                editor.putBoolean("roda",true);
                editor.putString("roda-dia",u.getRoda().getDiaDaSemana());
                editor.putString("roda-horario",u.getRoda().getHorario());
                editor.putString("roda-organizador",u.getRoda().getGrupoOrganizador());
                editor.putString("roda-responsavel",u.getRoda().getResponsavel());
                editor.putString("roda-estado",u.getRoda().getEstado());
                editor.putString("roda-cidade",u.getRoda().getCidade());
                editor.putString("roda-bairro",u.getRoda().getBairro());
                editor.putString("roda-rua",u.getRoda().getRua());
                editor.putString("roda-complemento",u.getRoda().getComplemento());
                editor.putString("roda-latitude",u.getRoda().getLatitude());
                editor.putString("roda-longitude",u.getRoda().getLongitude());

            }
            editor.putString("senha",u.getSenha());
            editor.apply();


            exibirMensagem(preferences.getString("id",null));
            finish();

            break;
        }



    }
        exibirMensagem("Usuário não cadastrado");




    }

    public void exibirMensagem(String mensagem){
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show();
    }
}
