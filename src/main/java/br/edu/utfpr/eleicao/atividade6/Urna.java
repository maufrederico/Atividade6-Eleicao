/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;


import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
                stub.receberVotos(resultado, cargo);
            }
           
         //   stub.contarVotos();
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Urna.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void simulaEleicao(){
              
        
         for (Cargo cargo : Cargo.values()){
             
             List<Candidato> candidatosCargo = candidatos.stream()
                                                .filter(candidato -> candidato.getCargo() == cargo)
                                                .collect(Collectors.toList());
              
            for (int i = 0; i < 500; i++) {

                //apenas para dar tempo para mais urnas enviarem para o servidor
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Urna.class.getName()).log(Level.SEVERE, null, ex);
                }


                int candidatoAleatorio = new Random().nextInt(candidatosCargo.size() );

                votacao.getVotos().add(new Voto( candidatosCargo.get(candidatoAleatorio)));

            }
         }
        
     
    }

   

    
    
}
