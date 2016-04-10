package br.com.partRepository.classes;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.com.partRepository.interfaces.PartRepository;

public class StartServer {

	public static void main(String[]args)throws RemoteException{
		try {
			Naming.rebind(args[0].toLowerCase(),new Repository(args[0]));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
