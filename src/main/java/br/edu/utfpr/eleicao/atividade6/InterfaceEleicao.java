/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.eleicao.atividade6;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author maufr
 */
public interface InterfaceEleicao extends Remote {
    
    public void contarVotos() throws RemoteException;
    
    public List<Candidato> retornaCandidatoCadastrados() throws RemoteException;
      
    public void enviarVotos(Map<String, Long> resultado, Cargo cargo) throws RemoteException;


}
