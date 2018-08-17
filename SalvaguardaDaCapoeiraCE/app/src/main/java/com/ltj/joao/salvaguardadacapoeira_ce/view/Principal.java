package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.Maps;
import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.view.CadastrarCapoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarCapoeiristas;

public class Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
SharedPreferences preferences;
SharedPreferences.Editor editor;
static String nome;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("usuario",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();


        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SharedPreferences preferences = getSharedPreferences("usuario",MODE_PRIVATE);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.content,new Mapa(),"Mapa");
        transaction.commitAllowingStateLoss();
        if(preferences.getBoolean("aviso",true)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Aviso");
            builder.setMessage("Cada usuário só poderá inserir um campoeirista, um grupo e uma roda.");
            builder.create();
            builder.show();
            editor.putBoolean("aviso",false);
            editor.apply();
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



   // @Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      //  int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
       //     return true;
       // }

      //  return super.onOptionsItemSelected(item);
   // }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.cadastrarCapoeirista) {
            if(preferences.getString("id","") != "" ){
                Intent it = new Intent(this, CadastrarCapoeirista.class);
                startActivity(it);
            }else{
                Toast.makeText(this,"Faça login para cadastrar capoeirista",Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.cadastrarGrupo) {

            if(preferences.getString("id","") != "" ){
                Intent it = new Intent(this,CadastrarGrupo.class);
                startActivity(it);
            }else{
                Toast.makeText(this,"Faça login para cadastrar grupo",Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.cadastrarRodas) {

            if(preferences.getString("id","") != "" ){
                Intent it = new Intent(this,CadastrarRoda.class);
                startActivity(it);
            }else{
                Toast.makeText(this,"Faça login para cadastrar roda",Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.visualizarCapoeirista) {
            Intent it = new Intent(this, VisualizarCapoeiristas.class);
            startActivity(it);
        } else if (id == R.id.visualizarGrupos) {
            Intent it = new Intent(this, VisualizarGrupos.class);
            startActivity(it);
        }else if(id == R.id.visualizarHistoria){
            Intent it = new Intent(this,Historia.class);
            startActivity(it);
        }else if (id == R.id.desenvolvedores) {
            Intent it = new Intent(this, Desenvolvedores.class);
            startActivity(it);
        }else if (id == R.id.login) {
            Intent it = new Intent(this, Login.class);
            startActivity(it);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
