/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;


import static java.lang.Math.random;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author maufr
 */
public class Urna  {
    
    private List<Candidato> candidatos ;
    private Votacao votacao;
    public Urna() {
         votacao = new Votacao();
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(List<Candidato> candidatos) {
        this.candidatos = candidatos;
    }

    public Votacao getVotacao() {
        return votacao;
    }

    public void setVotacao(Votacao votacao) {
        this.votacao = votacao;
    }
    
    
    public static void main(String[] args) {
        Urna urna = new Urna();
        
        try {
            Registry servidorRegistro = LocateRegistry.getRegistry("localhost",1099);
            InterfaceEleicao stub = (InterfaceEleicao) servidorRegistro.lookup("metodoEleicao");
            
            urna.setCandidatos(stub.retornaCandidatoCadastrados());
            urna.simulaEleicao();
            
            for (Cargo cargo : Cargo.values()){
                Map<String,Long> resultado =  urna.getVotacao().sumarizaVotos(cargo);
                stub.enviarVotos(resultado, cargo);
            }
           
         //   stub.contarVotos();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Urna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simulaEleicao(){
        List<Candidato> presidentes = candidatos.stream()
                                                .filter(candidato -> candidato.getCargo() == Cargo.Presidente)
                                                .toList();
        List<Candidato> governadores = candidatos.stream()
                                                .filter(candidato -> candidato.getCargo() == Cargo.Governador)
                                                .toList();
       
        // votacao presidente
        for (int i = 0; i < 500; i++) {
            int randomNumber = new Random().nextInt(presidentes.size() );
          //  votacao.getVotos().add(new Voto(Cargo.Presidente, presidentes.get(randomNumber).getNumero()));
            votacao.getVotos().add(new Voto( presidentes.get(randomNumber)));
        }
         // votacao governador
        for (int i = 0; i < 500; i++) {
            int randomNumber = new Random().nextInt(governadores.size());
            //votacao.getVotos().add(new Voto(Cargo.Governador, governadores.get(randomNumber).getNumero()));
            votacao.getVotos().add(new Voto( governadores.get(randomNumber)));
        }
        
       // votacao.getVotos().forEach((voto -> System.out.println(voto.toString())));
    }

    private void contagemVotos(InterfaceEleicao stub) {
        
         
    }

    
    
}
