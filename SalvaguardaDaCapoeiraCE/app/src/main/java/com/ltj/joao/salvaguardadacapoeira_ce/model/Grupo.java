package com.ltj.joao.salvaguardadacapoeira_ce.model;
/**
 * Criado por João Santos em 24/03/2018.
 */
public class Grupo {

    private String id;
    private String nomeGrupo;
    private String nomeResponsavel;
    private String mestreGrupo;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    private String complemento;
    private String urlImagem;


    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getUrlImagem() {
        return urlImagem;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public String getMestreGrupo() {
        return mestreGrupo;
    }

    public void setMestreGrupo(String mestreGrupo) {
        this.mestreGrupo = mestreGrupo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }







}
