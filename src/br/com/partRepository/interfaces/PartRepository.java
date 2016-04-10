package br.com.partRepository.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Set;

public interface PartRepository extends Remote{

	//getters
	String getName() throws RemoteException;
	List<Part> getComponents() throws RemoteException;
	
	//setters
	void setName(String repoName) throws RemoteException;
	void setComponents(List<Part> components) throws RemoteException;
	
	//add a part into the repository
	boolean addPart(Part component) throws RemoteException;
	
	//get a part by id
	Part getPart(String id) throws RemoteException;
	
	//list all parts into the repository
	List<Part> listAll() throws RemoteException;
	
}
