package com.ltj.joao.salvaguardadacapoeira_ce.model;

public class Usuario {

    private String id;
    private String email;
    private String senha;
    private Grupo Grupo;
    private Roda roda;
    private Capoeirista capoeirista;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo getGrupo() {
        return Grupo;
    }

    public void setGrupo(com.ltj.joao.salvaguardadacapoeira_ce.model.Grupo grupo) {
        Grupo = grupo;
    }

    public Roda getRoda() {
        return roda;
    }

    public void setRoda(Roda roda) {
        this.roda = roda;
    }

    public Capoeirista getCapoeirista() {
        return capoeirista;
    }

    public void setCapoeirista(Capoeirista capoeirista) {
        this.capoeirista = capoeirista;
    }
}
