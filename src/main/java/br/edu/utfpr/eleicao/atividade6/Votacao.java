/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author maufr
 */
    public class Votacao {
    private List<Voto> votos;

    public Votacao(List<Voto> votos) {
        this.votos = votos;
    }

    public Votacao() {
        votos = new ArrayList<>();
    }

    public List<Voto> getVotos() {
        return votos;
    }

    public void setVotos(List<Voto> votos) {
        this.votos = votos;
    }
    
    public Map<String,Long> sumarizaVotos(Cargo cargo){
      return  votos.stream().filter(voto -> voto.getCandidato().getCargo()== cargo)
                .collect(Collectors.groupingBy(voto -> voto.getCandidato().getNome(), Collectors.counting()));
                 
             //
            //    .entrySet().stream().sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
            // .forEach((k) -> System.out.println(k.getKey()+": "+k.getValue()));
    }
    
    
    
    
}
