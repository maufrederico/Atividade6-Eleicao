/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;

/**
 *
 * @author maufr
 */
public class Voto {
    
     private Candidato candidato;

    public Voto(Candidato candidato) {
        this.candidato = candidato;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }
     
     
//    private Cargo cargo;
//    private int numero;
//
//    public Voto(Cargo cargo, int numero) {
//        this.cargo = cargo;
//        this.numero = numero;
//    }
//
//    public Cargo getCargo() {
//        return cargo;
//    }
//
//    public void setCargo(Cargo cargo) {
//        this.cargo = cargo;
//    }
//
//    public int getNumero() {
//        return numero;
//    }
//
//    public void setNumero(int numero) {
//        this.numero = numero;
//    }
//
//    @Override
//    public String toString() {
//        return cargo + " - "+numero;
//    }

    public Voto() {
    }
    
    
    
    
}
