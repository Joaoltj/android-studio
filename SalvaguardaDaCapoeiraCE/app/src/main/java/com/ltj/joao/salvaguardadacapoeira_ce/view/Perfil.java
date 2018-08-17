package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Perfil extends AppCompatActivity {
    private static Capoeirista capoeirista;

    public static Capoeirista getCapoeirista() {
        return capoeirista;
    }

    public static void setCapoeirista(Capoeirista capoeirista) {
        Perfil.capoeirista = capoeirista;
    }
    private TextView nomePerfil,telefonePerfil,enderecoPerfil,whatsappPerfil,dataNascimentoPerfil,sexoPerfil,emailPerfil,apelidoPerfil,graduacaoPerfil,anoGraduacaoPerfil,grupoPerfil,estiloPerfil,nomeMestrePerfil,apelidoMestrePerfil,graduacaoMestrePerfil;

    private CircleImageView imgPerfil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        try{

            imgPerfil = (CircleImageView)findViewById(R.id.ivFotoPerfil);


            if(capoeirista.getUrlImagem().equals("NULL")){
               imgPerfil.setImageResource(R.drawable.ic_inserir_foto);
            }else{
                Picasso.get().load( capoeirista.getUrlImagem()).into(imgPerfil);
            }



            nomePerfil = (TextView)findViewById(R.id.nomePerfil);
            telefonePerfil = (TextView)findViewById(R.id.telefonePerfil);
            whatsappPerfil = (TextView)findViewById(R.id.whatsappPerfil);
            dataNascimentoPerfil = (TextView)findViewById(R.id.dataNascimentoPerfil);
            sexoPerfil = (TextView)findViewById(R.id.sexoPerfil);

            apelidoPerfil = (TextView)findViewById(R.id.apelidoPerfil);
            graduacaoPerfil = (TextView)findViewById(R.id.graduacaoPerfil);
            anoGraduacaoPerfil = (TextView)findViewById(R.id.anoGraduacaoPerfil);
            grupoPerfil = (TextView)findViewById(R.id.grupoPerfil);
            estiloPerfil = (TextView)findViewById(R.id.estiloPerfil);
            nomeMestrePerfil = (TextView)findViewById(R.id.nomeMestrePerfil);
            apelidoMestrePerfil = (TextView)findViewById(R.id.apelidoMestrePerfil);
            graduacaoMestrePerfil = (TextView)findViewById(R.id.graduacaoMestrePerfil);
            enderecoPerfil = (TextView)findViewById(R.id.enderecoPerfil);

            nomePerfil.setText(capoeirista.getNome());
            telefonePerfil.setText(capoeirista.getTelefone());
            whatsappPerfil.setText(capoeirista.getWhatsapp());
            dataNascimentoPerfil.setText(capoeirista.getDataNascimento());
            sexoPerfil.setText(capoeirista.getSexo());

            apelidoPerfil.setText(capoeirista.getApelido());
            graduacaoPerfil.setText(capoeirista.getGraduacao());
            anoGraduacaoPerfil.setText(capoeirista.getAnoGraduacao());
            grupoPerfil.setText(capoeirista.getGrupo());
            estiloPerfil.setText(capoeirista.getEstilo());
            nomeMestrePerfil.setText(capoeirista.getNomeMestre());
            apelidoMestrePerfil.setText(capoeirista.getApelidoMestre());
            graduacaoMestrePerfil.setText(capoeirista.getGraduacaoMestre());
            enderecoPerfil.setText(capoeirista.getEndereco());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }


    }

}
