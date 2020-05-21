package com.cantarino.kafkademo.Models;

public class Mensagem {

    private String nome;
    private String sobreNome;

    public Mensagem() { }

    public Mensagem(String nome, String sobreNome) {
        this.nome = nome;
        this.sobreNome = sobreNome;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }
}
