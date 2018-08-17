package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;

public class VisualizarRoda extends AppCompatActivity {
    private TextView emailRodaPerfil,diaDaSemana,horario,grupoOrganizador,responsavel,estado,cidade,bairro,rua,complemento,latitude,longitude;
    private static Roda roda;

    public static Roda getRoda() {
        return roda;
    }

    public static void setRoda(Roda roda) {
        VisualizarRoda.roda = roda;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_roda);

        diaDaSemana = (TextView)findViewById(R.id.diaDaSemanaPerfil);
        horario= (TextView)findViewById(R.id.horarioPerfil);
        grupoOrganizador= (TextView)findViewById(R.id.grupoOrganizadoPerfil);
        responsavel= (TextView)findViewById(R.id.responsavelPerfil);
        estado= (TextView)findViewById(R.id.estadoPerfil);
        cidade= (TextView)findViewById(R.id.cidadePerfil);
        bairro= (TextView)findViewById(R.id.bairroPefil);
        rua= (TextView)findViewById(R.id.ruaPerfil);
        complemento= (TextView)findViewById(R.id.complementoPerfil);
        latitude= (TextView)findViewById(R.id.latitudePerfil);
        longitude= (TextView)findViewById(R.id.longitudePerfil);
        emailRodaPerfil= (TextView)findViewById(R.id.emailRodaPerfil);
        diaDaSemana.setText(roda.getDiaDaSemana());
        horario.setText(roda.getHorario());
        grupoOrganizador.setText(roda.getGrupoOrganizador());
        responsavel.setText(roda.getResponsavel());
        estado.setText(roda.getEstado());
        cidade.setText(roda.getCidade());
        bairro.setText(roda.getBairro());
        rua.setText(roda.getRua());
        complemento.setText(roda.getComplemento());
        latitude.setText(roda.getLatitude());
        longitude.setText(roda.getLongitude());

    }
}
