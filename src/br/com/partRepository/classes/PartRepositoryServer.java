/*package br.com.partRepository.classes;

import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.Set;
import java.util.UUID;

import br.com.partRepository.interfaces.*;

public class PartRepositoryServer implements PartRepository, Part {

	private String serverName;
	private LinkedList<PartRepositoryClient> clients;
	
	public PartRepositoryServer(String serverName){
		this.serverName = serverName;
	}

	@Override
	public UUID getPartId() {
		return null;
	}

	@Override
	public String getPartName() {
		return null;
	}

	@Override
	public String getPartInfo() {
		return null;
	}

	@Override
	public CList getComponentList() {
		return null;
	}

	@Override
	public void setPartName(String partName) {
		
	}

	@Override
	public void setPartInfo(String partInfo) {
		
	}

	@Override
	public void AddComponent(Part Component, int quant) {
		
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Set<Part> getComponents() {
		return null;
	}

	@Override
	public void setName(String repoName) {
		
	}

	@Override
	public void setComponents(Set<Part> components) {
		
	}

	@Override
	public boolean addPart(Part component) throws RemoteException {
		return false;
	}

// estava dando erro
//	@Override
//	public Part getPart(Part component) throws RemoteException {
//		return null;
//	}

	@Override
	public Set<Part> listAll() throws RemoteException {
		return null;
	}

	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Part getPart(String id) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}*/