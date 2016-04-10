package br.com.partRepository.classes;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import br.com.partRepository.interfaces.PartRepository;

public class StartServer {

	public static void main(String[]args)throws RemoteException{
//		if(System.getSecurityManager() == null){
//			System.setSecurityManager(new SecurityManager());
//		}
		
//		PartRepository computerRepo = new Repository("Teste");
//		PartRepository stub;
		try {
			//stub = (PartRepository) UnicastRemoteObject.exportObject(computerRepo, 0);
			//Registry registry = LocateRegistry.getRegistry();
			//Naming.rebind("servidor",new Repository("teste"));
			Naming.rebind(args[0].toLowerCase(),new Repository(args[0]));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
