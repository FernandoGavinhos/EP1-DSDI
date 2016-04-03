package br.com.partRepository.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;

public interface PartRepository extends Remote{

	//getters
	String getName();
	Set<Part> getComponents();
	
	//setters
	void setName(String repoName);
	void setComponents(Set<Part> components);
	
	//add a part into the repository
	boolean addPart(Part component) throws RemoteException;
	
	//get a part by id
	Part getPart(Part component) throws RemoteException;
	
	//list all parts into the repository
	Set<Part> listAll() throws RemoteException;
	
}
