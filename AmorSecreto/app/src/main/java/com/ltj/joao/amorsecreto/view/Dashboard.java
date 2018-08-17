package com.ltj.joao.amorsecreto.view;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.Preference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ltj.joao.amorsecreto.R;
public class Dashboard extends AppCompatActivity {
    private CardView cvMusica;
    SharedPreferences preference;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        this.cvMusica = (CardView)findViewById(R.id.cvMusica);

        checar();
    }

    public void goMusica(View view){
        startActivity(new Intent(this,TEste.class));
    }
    public void goAgenda(View view){
        startActivity(new Intent(this,Agenda.class));
    }
    public void goPhotos(View view){
        startActivity(new Intent(this,InserirImagem.class));
    }
    public void goVideos(View view) {
        startActivity(new Intent(this, ImagemView.class));
    }
    public void goNoticias(View view){
        startActivity(new Intent(this, InserirMusica.class));
    }

    public void checar(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        2);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 2: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   setPreference(true);
                } else {
                    setPreference(false);
                }

                return;
            }


        }
    }

    public void setPreference(boolean b){
        preference = getPreferences(MODE_PRIVATE);
        editor = preference.edit();
        editor.putBoolean("permissao_write_external",b);
        editor.commit();

    }
}
