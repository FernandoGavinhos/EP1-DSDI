package br.com.partRepository.classes;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import br.com.partRepository.interfaces.Part;
import br.com.partRepository.interfaces.PartRepository;

public class Repository implements PartRepository {
	
	String repoName;
	Set<Part> components;
	
	@Override
	public String getName() throws RemoteException{
		return this.repoName;
	}
	
	@Override
	public Set<Part> getComponents() throws RemoteException{
		return this.components;
	}
	
	@Override
	public void setName(String repoName) throws RemoteException{
		this.repoName = repoName;
	}
	
	@Override
	public void setComponents(Set<Part> components) throws RemoteException{
		this.components = components;
	}
	
	public Repository(String repoName) {
		this.repoName = repoName;
		components = new HashSet<Part>();
	
	}
	
	
	@Override
	public boolean addPart(Part component) throws RemoteException {
		if(components.add(component)) return true;
		return false;
	}

	@Override
	public Part getPart(String id) throws RemoteException {
		Part component = new Piece();
		component.setId(id);
		if(components.contains(component)){
			Iterator<Part> i = components.iterator();
			while(i.hasNext()){
				Part found = i.next();
				if(found.equals(component)) return found;
			}
		}
		return null;
	}

	@Override
	public Set<Part> listAll() throws RemoteException {
		// TODO Auto-generated method stub
		if(!components.isEmpty()) return components;
		return null;
	}
	
	public static void main(String[]args){
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
		
		PartRepository computerRepo = new Repository("Teste");
		PartRepository stub;
		try {
			stub = (PartRepository) UnicastRemoteObject.exportObject(computerRepo, 0);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(computerRepo.getName(), stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
