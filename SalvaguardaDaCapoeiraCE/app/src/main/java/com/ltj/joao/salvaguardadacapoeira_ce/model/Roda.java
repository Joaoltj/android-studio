package com.ltj.joao.salvaguardadacapoeira_ce.model;

public class Roda {

    private String id;
    private String DiaDaSemana;
    private String Horario;
    private String Responsavel;
    private String GrupoOrganizador;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;


    private String complemento;
    private String latitude;
    private String longitude;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiaDaSemana() {
        return DiaDaSemana;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        DiaDaSemana = diaDaSemana;
    }

    public String getHorario() {
        return Horario;
    }

    public void setHorario(String horario) {
        Horario = horario;
    }

    public String getResponsavel() {
        return Responsavel;
    }

    public void setResponsavel(String responsavel) {
        Responsavel = responsavel;
    }

    public String getGrupoOrganizador() {
        return GrupoOrganizador;
    }

    public void setGrupoOrganizador(String grupoOrganizador) {
        GrupoOrganizador = grupoOrganizador;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
