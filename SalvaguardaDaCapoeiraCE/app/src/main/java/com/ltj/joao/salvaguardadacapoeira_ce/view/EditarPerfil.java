package com.ltj.joao.salvaguardadacapoeira_ce.view;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.database.CapoeiraDB;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Foto;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditarPerfil extends AppCompatActivity implements DialogInterface.OnClickListener{
    private static Capoeirista capoeirista;
    private final int INTENT_FOTO = 12;
    private final int PERMISION_REQUEST = 2;
    private Uri imagemSelecionada;
    private Capoeirista c;
    Foto foto = null;

    public static Capoeirista getCapoeirista() {
        return capoeirista;
    }

    public static void setCapoeirista(Capoeirista capoeirista) {
        EditarPerfil.capoeirista = capoeirista;
    }

    private EditText senhaAtual,novaSenha,nomeEditarPerfil,telefoneEditarPerfil,enderecoEditarPerfil,whatsappEditarPerfil,emailEditarPerfil,apelidoEditarPerfil,grupoEditarPerfil,nomeMestreEditarPerfil,apelidoMestreEditarPerfil;
    private Button dataNascimentoEditarPerfil,anoGraduacaoEditarPerfil;
    private Spinner sexoEditarPerfil,graduacaoEditarPerfil,estiloEditarPerfil,graduacaoMestreEditarPerfil;
    private ImageLoader imageLoader;
    private CircleImageView imgEditarPerfil;
    private int ano,mes,dia;
    private CapoeiraDB db;
   private AlertDialog cfExcluir;
    List<String> emails = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        buscarEmails();
        cfExcluir = criarDialog();
        db = new CapoeiraDB();
        //Permissão para acessar a galeria
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            Log.i("if1","entrou");
        }if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            Log.i("if2","entrou");
        }else{


            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISION_REQUEST);
            Log.i("CadastrarCapoeirista","1if");


        }


        try{
            Calendar calendar = Calendar.getInstance();
            ano = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            imgEditarPerfil = (CircleImageView)findViewById(R.id.ivFotoEditarPerfil);


            DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.drawable.sucess)
                    .showImageOnFail(R.drawable.fail)
                    .showImageOnLoading(R.drawable.loading)
                    .cacheInMemory(true)
                    // .displayer(new FadeInBitmapDisplayer(1000))
                    .cacheOnDisk(true)
                    .build();
            ImageLoaderConfiguration imageLoaderConfiguration = new ImageLoaderConfiguration.Builder(EditarPerfil.this)
                    .defaultDisplayImageOptions(displayImageOptions)
                    .memoryCacheSize(50 * 1024 *1024)
                    .diskCacheSize(50 * 1024 *1024)
                    .threadPoolSize(5)
                    .build();

            imageLoader = ImageLoader.getInstance();
            imageLoader.init(imageLoaderConfiguration);

            String imagem = "https://app-1529948227.000webhostapp.com/iphan/fotosCapoeiristas/"+capoeirista.getUrlImagem();
            imageLoader.displayImage(imagem,imgEditarPerfil);

            senhaAtual = (EditText) findViewById(R.id.senhaAtualEditarPerfil);
            novaSenha = (EditText) findViewById(R.id.novaSenhaEditarPerfil);

            nomeEditarPerfil = (EditText) findViewById(R.id.nomeEditarPerfil);
            telefoneEditarPerfil = (EditText)findViewById(R.id.telefoneEditarPerfil);
            whatsappEditarPerfil = (EditText)findViewById(R.id.whatsappEditarPerfil);
            dataNascimentoEditarPerfil = (Button)findViewById(R.id.dataNascimentoEditarPerfil);
            sexoEditarPerfil = (Spinner)findViewById(R.id.sexoEditarPerfil);
            emailEditarPerfil = (EditText)findViewById(R.id.emailEditarPerfil);
            apelidoEditarPerfil = (EditText)findViewById(R.id.apelidoEditarPerfil);
            graduacaoEditarPerfil = (Spinner)findViewById(R.id.graduacaoEditarPerfil);
            anoGraduacaoEditarPerfil = (Button)findViewById(R.id.anoGraduacaoEditarPerfil);
            grupoEditarPerfil = (EditText)findViewById(R.id.grupoEditarPerfil);
            estiloEditarPerfil = (Spinner) findViewById(R.id.estiloEditarPerfil);
            nomeMestreEditarPerfil = (EditText)findViewById(R.id.nomeMestreEditarPerfil);
            apelidoMestreEditarPerfil = (EditText)findViewById(R.id.apelidoMestreEditarPerfil);
            graduacaoMestreEditarPerfil = (Spinner)findViewById(R.id.graduacaoMestreEditarPerfil);
            enderecoEditarPerfil = (EditText)findViewById(R.id.enderecoEditarPerfil);

            nomeEditarPerfil.setText(capoeirista.getNome());
            telefoneEditarPerfil.setText(capoeirista.getTelefone());
            whatsappEditarPerfil.setText(capoeirista.getWhatsapp());
            dataNascimentoEditarPerfil.setText(capoeirista.getDataNascimento());
           // sexoEditarPerfil.setText(capoeirista.getSexo());

            apelidoEditarPerfil.setText(capoeirista.getApelido());
            //graduacaoEditarPerfil.setText(capoeirista.getGraduacao());
            anoGraduacaoEditarPerfil.setText(capoeirista.getAnoGraduacao());
            grupoEditarPerfil.setText(capoeirista.getGrupo());
            //estiloEditarPerfil.setText(capoeirista.getEstilo());
            nomeMestreEditarPerfil.setText(capoeirista.getNomeMestre());
            apelidoMestreEditarPerfil.setText(capoeirista.getApelidoMestre());
            //graduacaoMestreEditarPerfil.setText(capoeirista.getGraduacaoMestre());
            enderecoEditarPerfil.setText(capoeirista.getEndereco());
            iniciarSpinnerSexo(capoeirista.getSexo());
            iniciarSpinnerGraduacao(capoeirista.getGraduacao(),1);
            iniciarSpinnerGraduacao(capoeirista.getGraduacaoMestre(),2);
            iniciarSpinnerEstilo(capoeirista.getEstilo());
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    public void iniciarSpinnerSexo(String inicial){
        String []nomes;

        if(inicial.equals("Masculino")){
            nomes = new String[]{"Masculino","Feminino","Outro"};


        }else if(inicial.equals("Feminino")){
            nomes = new String[]{"Feminino","Masculino","Outro"};
        }else{
            nomes = new String[]{"Outro","Masculino","Feminino"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,nomes);
        this.sexoEditarPerfil.setAdapter(adapter);
    }
    public void iniciarSpinnerGraduacao(String inicial,int i){
        String []graduacoes;

        if(inicial.equals("Mestre")){
            graduacoes = new String[]{"Mestre","Contra-mestre","Professor","Treinel","Aluno formado","Aluno"};


        }else if(inicial.equals("Contramestre")){
            graduacoes = new String[]{"Contramestre","Mestre","Professor","Treinel","Aluno formado","Aluno"};

        }else if(inicial.equals("Professor")){
            graduacoes = new String[]{"Professor","Mestre","Contramestre","Treinel","Aluno formado","Aluno"};

        }else if(inicial.equals("Treinel")){
            graduacoes = new String[]{"Treinel","Mestre","Contramestre","Professor","Aluno formado","Aluno"};

        }else if(inicial.equals("Aluno formado")){
            graduacoes = new String[]{"Aluno formado","Mestre","Contramestre","Professor","Treinel","Aluno"};

        } else{
            graduacoes = new String[]{"Aluno","Mestre","Contramestre","Professor","Treinel","Aluno formado"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,graduacoes);
        if(i == 1){
            this.graduacaoEditarPerfil.setAdapter(adapter);
        }else{
            this.graduacaoMestreEditarPerfil.setAdapter(adapter);
        }

    }

    public void buscarEmails(){
        try{

            db = new CapoeiraDB();
            Thread th = new Thread(){
                @Override
                public void run(){
                    emails = db.buscarEmails();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                        }
                    });
                }
            };th.start();
        }catch (Exception e){
            Log.i("erro","1");
        }

    }

    public void iniciarSpinnerEstilo(String inicial){
        String []estilos;

        if(inicial.equals("Angola")){
            estilos = new String[] {"Angola","Regional","Capoeira de Rua","Contemporânea","Capoeira"};


        }else if(inicial.equals("Regional")){
            estilos = new String[] {"Regional","Angola","Capoeira de Rua","Contemporânea","Capoeira"};

        }else if(inicial.equals("Capoeira de Rua")){
            estilos = new String[] {"Capoeira de Rua","Angola","Regional","Contemporânea","Capoeira"};

        }else if(inicial.equals("Contemporânea")){
            estilos = new String[] {"Contemporânea","Angola","Regional","Capoeira de Rua","Capoeira"};

        } else{
            estilos = new String[] {"Capoeira","Angola","Regional","Capoeira de Rua","Contemporânea"};
        }
        ArrayAdapter<String> adapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,estilos);

            this.estiloEditarPerfil.setAdapter(adapter);


    }
    private int idButton;
    public void selecionarDataEditar(View v) {
        idButton = v.getId();
        showDialog(v.getId());
    }


    //criando dialog da data
    @Override
    public Dialog onCreateDialog(int id){

        return new DatePickerDialog(this,listenerData,ano,mes,dia);

    }

    DatePickerDialog.OnDateSetListener listenerData = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int a, int m, int d) {
            ano = a;
            mes = m;
            dia = d;
            Button btn = (Button)findViewById(R.id.btnDataNascimentoCapoeirista);
            Button btn2 = (Button)findViewById(R.id.btnAnoGraduacaoCapoeirista);
            String data = "";


            if(dia < 10){
                data = "0"+dia+"/"+(mes+1)+"/"+ano;
                if(mes < 10){
                    data = "0"+dia+"/0"+(mes+1)+"/"+ano;
                }else{
                    data = "0"+dia+"/"+(mes+1)+"/"+ano;
                }
            }else{
                data = dia+"/"+(mes+1)+"/"+ano;
                if(mes < 10){
                    data = dia+"/0"+(mes+1)+"/"+ano;
                }else{
                    data = dia+"/"+(mes+1)+"/"+ano;
                }
            }
            Toast.makeText(getApplicationContext(), data,Toast.LENGTH_SHORT).show();
            if(idButton == R.id.dataNascimentoEditarPerfil){
                dataNascimentoEditarPerfil.setText(data);
            }else{
                anoGraduacaoEditarPerfil.setText(data);
            }

        }
    };

    //abrir galeria
    public void selecionarFotoCapoeiristaEditar(View view){


        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        it.setType("image/*");
        startActivityForResult(it,INTENT_FOTO);
    }
    //permissao para abrir a galeria
    @Override
    public void onRequestPermissionsResult(int resultCode,String[] permissions,int [] grantResults) {
        if (resultCode == PERMISION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
            return;
        }

    }
    //pega imagem da galeria
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent intent) {
        if (resultCode == RESULT_OK) {
            if (requestCode == INTENT_FOTO) {
                imagemSelecionada = intent.getData();
                final String[] colunas = {MediaStore.Images.Media.DATA};
                Cursor resultado = getContentResolver().query(imagemSelecionada, colunas, null, null, null);
                resultado.moveToFirst();
                int indexColuna = resultado.getColumnIndex(colunas[0]);
                String path = resultado.getString(indexColuna);
                resultado.close();
                File file = new File(path);
                if(file != null){
                    imgEditarPerfil = (CircleImageView)findViewById(R.id.ivFotoEditarPerfil);
                    try {
                        foto = new Foto();
                        foto.setExtensaoDaFoto(path);
                        foto.ajustarTamanho(this,file,300,300);
                    } catch (IOException e) {
                        Toast.makeText(this,"Erro na rotacao",Toast.LENGTH_SHORT).show();
                    }
                    foto.setImagemNome("outra");
                    imgEditarPerfil.setImageBitmap(foto.getBitmap());
                }





            }
        }
    }

    public boolean verificarEmail(){
        for(String email:emails){
            if(emailEditarPerfil.getText().toString().trim().equals(email)){
                return true;
            }
        }
        return false;
    }
    public void salvarCapoeirista(View view){

        if(foto != null){

            if(!senhaAtual.getText().toString().trim().equals("") && !nomeEditarPerfil.getText().toString().trim().equals("") && !emailEditarPerfil.getText().toString().trim().equals("") && !nomeMestreEditarPerfil.getText().toString().trim().equals("") && !apelidoMestreEditarPerfil.getText().toString().trim().equals("")&& !apelidoEditarPerfil.getText().toString().trim().equals("")) {


                        setSalvando(false);
                        c = new Capoeirista();

                        //Colocando os valores dos campos no objeto
                        c.setNome(this.nomeEditarPerfil.getText().toString());
                        c.setDataNascimento(this.dataNascimentoEditarPerfil.getText().toString());
                        c.setSexo(this.sexoEditarPerfil.getSelectedItem().toString());

                        c.setId(capoeirista.getId());
                        c.setTelefone(this.telefoneEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.telefoneEditarPerfil.getText().toString());
                        c.setApelido(this.apelidoEditarPerfil.getText().toString());
                        c.setWhatsapp(this.whatsappEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.whatsappEditarPerfil.getText().toString());
                        c.setGraduacao(this.graduacaoEditarPerfil.getSelectedItem().toString());
                        c.setAnoGraduacao(this.anoGraduacaoEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.anoGraduacaoEditarPerfil.getText().toString());
                        c.setGrupo(this.grupoEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.grupoEditarPerfil.getText().toString());
                        c.setEstilo(this.estiloEditarPerfil.getSelectedItem().toString());
                        c.setNomeMestre(this.nomeMestreEditarPerfil.getText().toString());
                        c.setApelidoMestre(this.apelidoMestreEditarPerfil.getText().toString());
                        c.setGraduacaoMestre(this.graduacaoEditarPerfil.getSelectedItem().toString());



                        Thread th = new Thread() {
                            @Override
                            public void run() {
                                final String respota = db.salvarCapoeirista(c);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {

                                            setSalvando(true);
                                            Toast.makeText(getApplicationContext(), respota, Toast.LENGTH_LONG).show();

                                        } catch (Exception e) {

                                        }

                                    }
                                });
                            }
                        };
                        th.start();





            }else{
                if(nomeEditarPerfil.getText().toString().trim().equals("")){
                    nomeEditarPerfil.requestFocus();
                }else if(emailEditarPerfil.getText().toString().trim().equals("")){
                    emailEditarPerfil.requestFocus();
                }else if(apelidoEditarPerfil.getText().toString().trim().equals("")){
                    apelidoEditarPerfil.requestFocus();
                }else if(nomeMestreEditarPerfil.getText().toString().trim().equals("")){
                    nomeMestreEditarPerfil.requestFocus();
                }else if(apelidoMestreEditarPerfil.getText().toString().trim().equals("")){
                    apelidoMestreEditarPerfil.requestFocus();
                }else if(senhaAtual.getText().toString().trim().equals("")){
                    senhaAtual.requestFocus();
                }
                Toast.makeText(getApplicationContext(),"Preencha os campos corretamente",Toast.LENGTH_SHORT).show();
            }
        }else{
            if(!senhaAtual.getText().toString().trim().equals("") && !nomeEditarPerfil.getText().toString().trim().equals("") && !emailEditarPerfil.getText().toString().trim().equals("") && !nomeMestreEditarPerfil.getText().toString().trim().equals("") && !apelidoMestreEditarPerfil.getText().toString().trim().equals("")&& !apelidoEditarPerfil.getText().toString().trim().equals("")) {


                        setSalvando(false);
                        c = new Capoeirista();

                        //Colocando os valores dos campos no objeto
                        c.setNome(this.nomeEditarPerfil.getText().toString());
                        c.setDataNascimento(this.dataNascimentoEditarPerfil.getText().toString());
                        c.setSexo(this.sexoEditarPerfil.getSelectedItem().toString());

                        c.setId(capoeirista.getId());
                        c.setTelefone(this.telefoneEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.telefoneEditarPerfil.getText().toString());

                        c.setApelido(this.apelidoEditarPerfil.getText().toString());
                        c.setWhatsapp(this.whatsappEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.whatsappEditarPerfil.getText().toString());
                        c.setGraduacao(this.graduacaoEditarPerfil.getSelectedItem().toString());
                        c.setAnoGraduacao(this.anoGraduacaoEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.anoGraduacaoEditarPerfil.getText().toString());
                        c.setGrupo(this.grupoEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.grupoEditarPerfil.getText().toString());
                        c.setEstilo(this.estiloEditarPerfil.getSelectedItem().toString());
                        c.setNomeMestre(this.nomeMestreEditarPerfil.getText().toString());
                        c.setApelidoMestre(this.apelidoMestreEditarPerfil.getText().toString());
                        c.setGraduacaoMestre(this.graduacaoEditarPerfil.getSelectedItem().toString());
                        c.setEndereco(this.enderecoEditarPerfil.getText().toString().trim().equals("") ? "NULL" : this.enderecoEditarPerfil.getText().toString());



                        Thread th = new Thread() {
                            @Override
                            public void run() {
                                final String respota = db.salvarCapoeiristaSemFoto(c);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {

                                            setSalvando(true);
                                            Toast.makeText(getApplicationContext(), respota, Toast.LENGTH_LONG).show();

                                        } catch (Exception e) {

                                        }

                                    }
                                });
                            }
                        };
                        th.start();





            }else{
                if(nomeEditarPerfil.getText().toString().trim().equals("")){
                    nomeEditarPerfil.requestFocus();
                }else if(emailEditarPerfil.getText().toString().trim().equals("")){
                    emailEditarPerfil.requestFocus();
                }else if(apelidoEditarPerfil.getText().toString().trim().equals("")){
                    apelidoEditarPerfil.requestFocus();
                }else if(nomeMestreEditarPerfil.getText().toString().trim().equals("")){
                    nomeMestreEditarPerfil.requestFocus();
                }else if(apelidoMestreEditarPerfil.getText().toString().trim().equals("")){
                    apelidoMestreEditarPerfil.requestFocus();
                }else if(senhaAtual.getText().toString().trim().equals("")){
                    senhaAtual.requestFocus();
                }
                Toast.makeText(getApplicationContext(),"Preencha os campos corretamente",Toast.LENGTH_SHORT).show();
            }
        }

    }


   public void excluirCapoeirista(View view){

    cfExcluir.show();
   }
    public void setSalvando(boolean op){

         Button button = (Button)findViewById(R.id.salvarCapoeirista);
        if(op == false){
            button.setText("Salvando...");
            button.setEnabled(op);
        }else {
            button.setText("Salvar");
            button.setEnabled(op);
        }

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        imageLoader.clearMemoryCache();
        imageLoader.clearDiskCache();
        imageLoader.stop();
    }
    private AlertDialog criarDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Deseja realmente excluir?");
        builder.setPositiveButton("Sim",this);  c.setUrlImagem(capoeirista.getUrlImagem());
        builder.setNegativeButton("Não",this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i){
            case DialogInterface.BUTTON_POSITIVE:
                Thread th = new Thread() {
                    @Override
                    public void run() {
                        final String respota = db.deletarCapoeirista(capoeirista.getId());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {


                                    Toast.makeText(getApplicationContext(), respota, Toast.LENGTH_LONG).show();
                                    finish();
                                } catch (Exception e) {

                                }

                            }
                        });
                    }
                };
                th.start();
                break;
                case DialogInterface.BUTTON_NEGATIVE:
                    cfExcluir.dismiss();
                    break;
        }
    }
}
