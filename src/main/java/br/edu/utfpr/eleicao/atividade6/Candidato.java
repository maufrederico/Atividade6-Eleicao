package br.edu.utfpr.eleicao.atividade6;

import java.io.Serializable;


public class Candidato implements Serializable {
    
    private String nome;
    private String partido;
    private int numero;
    private Cargo cargo;

    public Candidato(String nome, String partido, int numero, Cargo cargo) {
        this.nome = nome;
        this.partido = partido;
        this.numero = numero;
        this.cargo = cargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
    
    
}
