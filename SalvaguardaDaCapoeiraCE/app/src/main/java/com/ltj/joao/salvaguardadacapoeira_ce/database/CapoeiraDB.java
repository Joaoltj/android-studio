package com.ltj.joao.salvaguardadacapoeira_ce.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.ltj.joao.salvaguardadacapoeira_ce.R;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Capoeirista;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo;
import com.ltj.joao.salvaguardadacapoeira_ce.model.Roda;
import com.ltj.joao.salvaguardadacapoeira_ce.view.VisualizarCapoeiristas;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Script on 31/03/2018.
 */

public class CapoeiraDB {
    URL url ;
    HttpURLConnection conexao;
    InputStream is;
    BufferedReader br;


    public String inserir(Capoeirista capoeirista){
        String resposta = "";
        try {

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("nome",capoeirista.getNome()));
            valores.add(new BasicNameValuePair("dataNascimento",capoeirista.getDataNascimento()));
            valores.add(new BasicNameValuePair("sexo",capoeirista.getSexo()));
            valores.add(new BasicNameValuePair("cpf",capoeirista.getCpf()));
            valores.add(new BasicNameValuePair("rg",capoeirista.getRg()));
            valores.add(new BasicNameValuePair("telefone",capoeirista.getTelefone()));
            valores.add(new BasicNameValuePair("apelido",capoeirista.getApelido()));
            valores.add(new BasicNameValuePair("whatsapp",capoeirista.getWhatsapp()));
            valores.add(new BasicNameValuePair("graduacao",capoeirista.getGraduacao()));
            valores.add(new BasicNameValuePair("anoGraduacao",capoeirista.getAnoGraduacao()));
            valores.add(new BasicNameValuePair("grupo",capoeirista.getGrupo()));
            valores.add(new BasicNameValuePair("estilo",capoeirista.getEstilo()));
            valores.add(new BasicNameValuePair("nomeMestre",capoeirista.getNomeMestre()));
            valores.add(new BasicNameValuePair("apelidoMestre",capoeirista.getApelidoMestre()));
            valores.add(new BasicNameValuePair("graduacaoMestre",capoeirista.getGraduacaoMestre()));
            valores.add(new BasicNameValuePair("endereco",capoeirista.getEndereco()));

            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_capoeirista/inserirCapoeirista.php");
            conexao = (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");


            OutputStream os = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStream is = new BufferedInputStream(conexao.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha = br.readLine()) != null){
                    resposta = linha;
                }

            }

        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.toString();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }
        return resposta;
    }

    public String salvarCapoeirista(Capoeirista capoeirista){
        String resposta = "";
        try { Log.i("Error","inicio if");

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("id",capoeirista.getId()));
            valores.add(new BasicNameValuePair("urlFotoAntiga",capoeirista.getUrlImagem()));
            valores.add(new BasicNameValuePair("nome",capoeirista.getNome()));
            valores.add(new BasicNameValuePair("dataNascimento",capoeirista.getDataNascimento()));
            valores.add(new BasicNameValuePair("sexo",capoeirista.getSexo()));
            valores.add(new BasicNameValuePair("telefone",capoeirista.getTelefone()));

            valores.add(new BasicNameValuePair("apelido",capoeirista.getApelido()));
            valores.add(new BasicNameValuePair("whatsapp",capoeirista.getWhatsapp()));
            valores.add(new BasicNameValuePair("graduacao",capoeirista.getGraduacao()));
            valores.add(new BasicNameValuePair("anoGraduacao",capoeirista.getAnoGraduacao()));
            valores.add(new BasicNameValuePair("grupo",capoeirista.getGrupo()));
            valores.add(new BasicNameValuePair("estilo",capoeirista.getEstilo()));
            valores.add(new BasicNameValuePair("nomeMestre",capoeirista.getNomeMestre()));
            valores.add(new BasicNameValuePair("apelidoMestre",capoeirista.getApelidoMestre()));
            valores.add(new BasicNameValuePair("graduacaoMestre",capoeirista.getGraduacaoMestre()));
            valores.add(new BasicNameValuePair("endereco",capoeirista.getEndereco()));


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_capoeirista/salvarCapoeirista.php");
            conexao = (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");

            Log.i("Error","ou");
            OutputStream os = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();
            Log.i("Error","Antes if");

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStream is = new BufferedInputStream(conexao.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha = br.readLine()) != null){
                    resposta = linha;
                }

            }
            Log.i("Error","depos if");

        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.getMessage();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }
        return resposta;
    }
    public String salvarCapoeiristaSemFoto(Capoeirista capoeirista){
        String resposta = "";
        try { Log.i("Error","inicio if");

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("id",capoeirista.getId()));
            valores.add(new BasicNameValuePair("nome",capoeirista.getNome()));
            valores.add(new BasicNameValuePair("dataNascimento",capoeirista.getDataNascimento()));
            valores.add(new BasicNameValuePair("sexo",capoeirista.getSexo()));
            valores.add(new BasicNameValuePair("telefone",capoeirista.getTelefone()));

            valores.add(new BasicNameValuePair("apelido",capoeirista.getApelido()));
            valores.add(new BasicNameValuePair("whatsapp",capoeirista.getWhatsapp()));
            valores.add(new BasicNameValuePair("graduacao",capoeirista.getGraduacao()));
            valores.add(new BasicNameValuePair("anoGraduacao",capoeirista.getAnoGraduacao()));
            valores.add(new BasicNameValuePair("grupo",capoeirista.getGrupo()));
            valores.add(new BasicNameValuePair("estilo",capoeirista.getEstilo()));
            valores.add(new BasicNameValuePair("nomeMestre",capoeirista.getNomeMestre()));
            valores.add(new BasicNameValuePair("apelidoMestre",capoeirista.getApelidoMestre()));
            valores.add(new BasicNameValuePair("graduacaoMestre",capoeirista.getGraduacaoMestre()));
            valores.add(new BasicNameValuePair("endereco",capoeirista.getEndereco()));


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_capoeirista/salvarCapoeiristaSemFoto.php");
            conexao = (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");

            Log.i("Error","ou");
            OutputStream os = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();
            Log.i("Error","Antes if");

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStream is = new BufferedInputStream(conexao.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha = br.readLine()) != null){
                    resposta = linha;
                }

            }
            Log.i("Error","depos if");

        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.getMessage();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }
        return resposta;
    }
    public String deletarCapoeirista(String id){
        String resposta = "";
        try { Log.i("Error","inicio if");

            ArrayList<NameValuePair> valores = new ArrayList<NameValuePair>();
            valores.add(new BasicNameValuePair("id",id));


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_capoeirista/deletarCapoeirista.php");
            conexao = (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");

            Log.i("Error","ou");
            OutputStream os = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();
            Log.i("Error","Antes if");

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                InputStream is = new BufferedInputStream(conexao.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha = br.readLine()) != null){
                    resposta = linha;
                }

            }
            Log.i("Error","depos if");

        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.getMessage();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }
        return resposta;
    }
    public String ajustarParametros(ArrayList<NameValuePair> valores)  {
        StringBuilder resposta = new StringBuilder();
        boolean primeiro = true;


                try {
                    for (NameValuePair valor : valores) {


                            resposta.append("&");
                            resposta.append(URLEncoder.encode(valor.getName(), "UTF-8"));
                            resposta.append("=");
                            resposta.append(URLEncoder.encode(valor.getValue(), "UTF-8"));

                    }
                }catch(UnsupportedEncodingException e){
                 Log.i("erro",e.toString());
                }


        return resposta.toString();
    }

    public List<Capoeirista> listarCapoeiritas(){

        List<Capoeirista> capoeiristas = new ArrayList<Capoeirista>();
        try {


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_capoeirista/listarCapoeirista.php");
            conexao = (HttpURLConnection)url.openConnection();
            Log.i("Linha","antes");
            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));

                String linha = "";
                StringBuilder linhas = new StringBuilder();

                while((linha = br.readLine()) != null){
                    linhas.append(linha);

                }

                    Log.i("Linhas",linhas.toString());
                    String registro[] = linhas.toString().split("%");
                    Log.i("Numero",""+registro.length);

                    for(int j = 0; j < registro.length;j++) {
                        String dados[] = registro[j].split("&");
                        Log.i("dados", "" + dados.length);
                        if (dados.length > 1) {
                            Capoeirista capoeirista = new Capoeirista();
                            capoeirista.setId(dados[0]);
                            capoeirista.setNome(dados[1]);
                            capoeirista.setDataNascimento(dados[2]);
                            capoeirista.setSexo(dados[3]);
                            capoeirista.setCpf(dados[4]);
                            capoeirista.setRg(dados[5]);
                            capoeirista.setTelefone(dados[6]);
                            capoeirista.setApelido(dados[9]);
                            capoeirista.setWhatsapp(dados[10]);
                            capoeirista.setGraduacao(dados[11]);
                            capoeirista.setAnoGraduacao(dados[12]);
                            capoeirista.setGrupo(dados[13]);
                            capoeirista.setEstilo(dados[14]);
                            capoeirista.setNomeMestre(dados[15]);
                            capoeirista.setApelidoMestre(dados[16]);
                            capoeirista.setGraduacaoMestre(dados[17]);
                            capoeirista.setEndereco(dados[18]);
                            //capoeirista.setUrl(dados[19]);
                            capoeiristas.add(capoeirista);
                        }



                }


            }


            return capoeiristas;
        } catch (MalformedURLException e) {
            Log.i("Erro",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Erro2",e.toString());
            e.printStackTrace();
        }
        return capoeiristas;
    }
    public List<String> buscarEmails(){

        List<String> emails = new ArrayList<>();
        try {


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_capoeirista/listarCapoeirista.php");
            conexao = (HttpURLConnection)url.openConnection();

            Log.i("Linha","antes");
            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));

                String linha = "";
                StringBuilder linhas = new StringBuilder();

                while((linha = br.readLine()) != null){
                    linhas.append(linha);

                }

                Log.i("Linhas",linhas.toString());
                String registro[] = linhas.toString().split("%");
                Log.i("Numero",""+registro.length);

                for(int j = 0; j < registro.length;j++){
                    String dados[] = registro[j].split("&");
                    if (dados.length > 1) {
                        String email = dados[7];
                        emails.add(email);
                    }
                }
            }


            return emails;
        } catch (MalformedURLException e) {
            Log.i("Erro",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Erro2",e.toString());
            e.printStackTrace();
        }
        return emails;
    }


    public String verificarConexao(){
        String resposta = "";
        try {
            url = new URL("https://app-1529948227.000webhostapp.com/iphan/conectado.php");
            conexao = (HttpURLConnection)url.openConnection();
            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK) {

                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha = br.readLine()) != null){
                    resposta = linha;
                }
            }

        }catch (ConnectException e) {
            resposta ="1";}
        catch (UnknownHostException e) {
            resposta = "2";}
        catch (Exception e) {
            resposta = "3";

        }

        return resposta;

    }

    public String inserirGrupo(Grupo grupo){
        String resposta = "";
        try {
            ArrayList<NameValuePair> valores = new ArrayList<>();
            valores.add(new BasicNameValuePair("nome",grupo.getNomeGrupo()));
            valores.add(new BasicNameValuePair("responsavel",grupo.getNomeResponsavel()));
            valores.add(new BasicNameValuePair("mestre",grupo.getMestreGrupo()));
            valores.add(new BasicNameValuePair("estado",grupo.getEstado()));
            valores.add(new BasicNameValuePair("cidade",grupo.getCidade()));
            valores.add(new BasicNameValuePair("bairro",grupo.getBairro()));
            valores.add(new BasicNameValuePair("rua",grupo.getRua()));
            valores.add(new BasicNameValuePair("complemento",grupo.getComplemento()));

            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_grupo/inserirGrupo.php");
            conexao =  (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");

            OutputStream o = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(o));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha =  br.readLine()) != null){
                    resposta = linha;
                }
            }




        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.toString();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }

        return resposta;
    }


    public List<Grupo> listarGrupos(){

        List<Grupo> grupos = new ArrayList<Grupo>();
        try {


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_grupo/listarGrupos.php");
            conexao = (HttpURLConnection)url.openConnection();
            Log.i("Linha","antes");
            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));

                String linha = "";
                StringBuilder linhas = new StringBuilder();

                while((linha = br.readLine()) != null){
                    linhas.append(linha);

                }

                Log.i("Linhas",linhas.toString());
                String registro[] = linhas.toString().split("%");
                Log.i("Numero",""+registro.length);

                for(int j = 0; j < registro.length;j++) {
                    String dados[] = registro[j].split("&");
                    Log.i("dados", "" + dados.length);
                    if (dados.length > 1) {
                        Grupo grupo = new Grupo();
                        grupo.setId(dados[0]);
                        grupo.setNomeGrupo(dados[1]);
                        grupo.setNomeResponsavel(dados[2]);
                        grupo.setMestreGrupo(dados[3]);
                        grupo.setEstado(dados[4]);
                        grupo.setCidade(dados[5]);
                        grupo.setBairro(dados[6]);
                        grupo.setRua(dados[7]);
                        grupo.setComplemento(dados[8]);
                        grupo.setUrlImagem(dados[9]);
                        grupos.add(grupo);


                    }



                }


            }


            return grupos;
        } catch (MalformedURLException e) {
            Log.i("Erro",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Erro2",e.toString());
            e.printStackTrace();
        }
        return grupos;
    }

    public String inserirRoda(Roda roda){

        String resposta = "";
        try {
            ArrayList<NameValuePair> valores = new ArrayList<>();
            valores.add(new BasicNameValuePair("diaDaSemana",roda.getDiaDaSemana()));
            valores.add(new BasicNameValuePair("horario",roda.getHorario()));
            valores.add(new BasicNameValuePair("grupoOrganizador",roda.getGrupoOrganizador()));
            valores.add(new BasicNameValuePair("responsavel",roda.getResponsavel()));
            valores.add(new BasicNameValuePair("estado",roda.getEstado()));
            valores.add(new BasicNameValuePair("cidade",roda.getCidade()));
            valores.add(new BasicNameValuePair("bairro",roda.getBairro()));
            valores.add(new BasicNameValuePair("rua",roda.getRua()));
            valores.add(new BasicNameValuePair("complemento",roda.getComplemento()));
            valores.add(new BasicNameValuePair("latitude",roda.getLatitude()));
            valores.add(new BasicNameValuePair("longitude",roda.getLongitude()));

            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_roda/inserirRoda.php");
            conexao =  (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");

            OutputStream o = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(o));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha =  br.readLine()) != null){
                    resposta = linha;
                }
            }




        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.toString();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }

        return resposta;
    }


    public String salvarRoda(Roda roda){

        String resposta = "";
        try {
            ArrayList<NameValuePair> valores = new ArrayList<>();
            valores.add(new BasicNameValuePair("id",roda.getId()));
            valores.add(new BasicNameValuePair("diaDaSemana",roda.getDiaDaSemana()));
            valores.add(new BasicNameValuePair("horario",roda.getHorario()));
            valores.add(new BasicNameValuePair("grupoOrganizador",roda.getGrupoOrganizador()));
            valores.add(new BasicNameValuePair("responsavel",roda.getResponsavel()));
            valores.add(new BasicNameValuePair("estado",roda.getEstado()));
            valores.add(new BasicNameValuePair("cidade",roda.getCidade()));
            valores.add(new BasicNameValuePair("bairro",roda.getBairro()));
            valores.add(new BasicNameValuePair("rua",roda.getRua()));
            valores.add(new BasicNameValuePair("complemento",roda.getComplemento()));
            valores.add(new BasicNameValuePair("latitude",roda.getLatitude()));
            valores.add(new BasicNameValuePair("longitude",roda.getLongitude()));

            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_roda/inserirRoda.php");
            conexao =  (HttpURLConnection)url.openConnection();
            conexao.setDoOutput(true);
            conexao.setDoInput(true);
            conexao.setRequestMethod("POST");

            OutputStream o = conexao.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(o));
            bw.write(ajustarParametros(valores));
            bw.flush();
            bw.close();

            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){
                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));
                String linha = "";
                while((linha =  br.readLine()) != null){
                    resposta = linha;
                }
            }




        }catch (UnsupportedEncodingException e){
            resposta = "Erro(1):"+e.toString();
        }catch (MalformedURLException e) {
            resposta = "Erro(2):"+e.toString();
        } catch (IOException e) {
            resposta = "Erro(3):"+e.toString();
        }catch (Exception e){
            resposta = "Erro(4):"+e.toString();
        }

        return resposta;
    }



    public List<Roda> listarRodas(){


        List<Roda> rodas = new ArrayList<Roda>();
        try {


            url = new URL("https://app-1529948227.000webhostapp.com/iphan/operacao_roda/listarRodas.php");
            conexao = (HttpURLConnection)url.openConnection();
            Log.i("Linha","antes");
            if(conexao.getResponseCode() == HttpURLConnection.HTTP_OK){

                is = new BufferedInputStream(conexao.getInputStream());
                br = new BufferedReader(new InputStreamReader(is));

                String linha = "";
                StringBuilder linhas = new StringBuilder();

                while((linha = br.readLine()) != null){
                    linhas.append(linha);

                }

                Log.i("Linhas",linhas.toString());
                String registro[] = linhas.toString().split("%");
                Log.i("Numero",""+registro.length);

                for(int j = 0; j < registro.length;j++) {
                    String dados[] = registro[j].split("&");
                    Log.i("dados", "" + dados.length);
                    if (dados.length > 1) {
                        Roda roda = new Roda();
                        roda.setId(dados[0]);
                        roda.setDiaDaSemana(dados[1]);
                        roda.setHorario(dados[2]);
                        roda.setGrupoOrganizador(dados[3]);
                        roda.setResponsavel(dados[4]);
                        roda.setEstado(dados[5]);
                        roda.setCidade(dados[6]);
                        roda.setBairro(dados[7]);
                        roda.setRua(dados[8]);
                        roda.setComplemento(dados[9]);
                        roda.setLatitude(dados[10]);
                        roda.setLongitude(dados[11]);

                        rodas.add(roda);


                    }



                }


            }

            Log.i("qqqReturn","Erro");
            return rodas;
        } catch (MalformedURLException e) {
            Log.i("Errodd",e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Erro2",e.toString());
            e.printStackTrace();
        }
        return rodas;
    }



}
