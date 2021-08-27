/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author maufr
 */
public class Apuracao {
    
    private Cargo cargo;
    private Map<String,Long> resultado;

    public Apuracao(Cargo cargo, Map<String, Long> resultado) {
        this.cargo = cargo;
        this.resultado = resultado;
    }

    public Apuracao() {
        
        resultado = new HashMap<>();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Map<String, Long> getResultado() {
        return resultado;
    }

    public void setResultado(Map<String, Long> resultado) {
        this.resultado = resultado;
    }
    
    
    
    
    
    
}
