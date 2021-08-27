/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maufr
 */


public class ServidorRMI implements InterfaceEleicao, Runnable{

    
    
    private final List<Candidato> candidatos = new ArrayList<>();
    private final List<Apuracao> apuracoes = new ArrayList<>();
    private final Map<String,Map<String,Long>> apuracao = new HashMap<>();
    public ServidorRMI() {
        cadastraCandidatos();
    }
    
    
    public static void main(String[] args) {
        try {
            
            ServidorRMI servidor = new ServidorRMI();
            
            Registry registro = LocateRegistry.createRegistry(1099);
            
            InterfaceEleicao skeleton = (InterfaceEleicao) UnicastRemoteObject.exportObject(servidor, 0);
            registro.rebind("metodoEleicao", skeleton);
            
            System.out.println("Inicio da Apuração...\n");
            
           new Thread(servidor).start();
           
      
            
        } catch (RemoteException ex) {
            Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public void contarVotos() throws RemoteException {
     
        System.out.println("Ultima Atualizacao: "+LocalDateTime.now());
        System.out.println();
        for (Map.Entry<String, Map<String, Long>> entry : apuracao.entrySet()) {
             System.out.println("--------------"+entry.getKey()+"--------------");
             System.out.println();
            Map<String, Long> valor = entry.getValue();
            
            valor.entrySet().stream()
                    .sorted((k1, k2) -> -k1.getValue().compareTo(k2.getValue()))
                    .forEach((k) -> System.out.println(k.getKey() +" - "+k.getValue()));
            
            System.out.println();
        }
        
    }


    @Override
    public synchronized void receberVotos(Map<String, Long> resultado, Cargo cargo) throws RemoteException {
        //  Map<String, Long> hashmap = new HashMap<>(resultado);
      
        for (Entry<String, Long> entry : resultado.entrySet()) {
            
             apuracao.computeIfPresent(cargo.name(), (k,v) -> {
                 v.computeIfPresent(entry.getKey(), (k1, v1)->v1 +entry.getValue())  ;
            
                 v.putIfAbsent(entry.getKey(), entry.getValue());
                 return v;
       
             });
        
        }
        apuracao.putIfAbsent(cargo.name(), resultado);
               
    }
    
     @Override
    public List<Candidato> retornaCandidatoCadastrados() throws RemoteException {
        return candidatos;
    }

    @Override
    public void run() {
        
         while(true){
             try {
                 contarVotos();
                 Thread.sleep(5000);
             } catch (RemoteException ex) {
                 Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
             } catch (InterruptedException ex) {
                 Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
             }
               
            }
       
    }
    
    
    private  void cadastraCandidatos() {
        //presidentes
        candidatos.add(new Candidato("Fernando Haddad", "PT",13 , Cargo.Presidente));
        candidatos.add(new Candidato("Jair Messias Bolsonaro", "PSL",17 , Cargo.Presidente));
        candidatos.add(new Candidato("Geraldo Alckmin", "PSDB",45 , Cargo.Presidente));
        candidatos.add(new Candidato("Henrique De Campos Meirelles", "MDB",15 , Cargo.Presidente));
        
        //govenadores
        
        candidatos.add(new Candidato("Luiz Marinho", "PT",13 , Cargo.Governador));
        candidatos.add(new Candidato("Lílian Cristina Miranda", "PCO",29 , Cargo.Governador));
        candidatos.add(new Candidato("João Doria", "PSDB",45 , Cargo.Governador));
       
        
    }

}
