package br.com.partRepository.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface PartRepository extends Remote{

	//getters
	String getName() throws RemoteException;
	Set<Part> getComponents() throws RemoteException;
	
	//setters
	void setName(String repoName) throws RemoteException;
	void setComponents(Set<Part> components) throws RemoteException;
	
	//add a part into the repository
	boolean addPart(Part component) throws RemoteException;
	
	//get a part by id
	Part getPart(String id) throws RemoteException;
	
	//list all parts into the repository
	Set<Part> listAll() throws RemoteException;
	
}
