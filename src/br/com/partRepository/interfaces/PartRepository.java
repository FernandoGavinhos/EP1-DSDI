package br.com.partRepository.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote{

	//add a part into the repository
	boolean addPart(Part component) throws RemoteException;
	
	//get a part by id
	Part getPart(Part component) throws RemoteException;
	
	//list all parts into the repository
	void listAll() throws RemoteException;
	
}
