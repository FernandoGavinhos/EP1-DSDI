package br.com.partRepository.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

import br.com.partRepository.classes.CList;

public interface Part extends Remote{
	
	//getters
	public UUID getPartId() throws RemoteException;
	public String getPartName() throws RemoteException;
	public String getPartInfo() throws RemoteException;
	public CList getComponentList() throws RemoteException;

	//setters

	public void setPartName(String partName) throws RemoteException;
	public void setPartInfo(String partInfo) throws RemoteException;
	public void AddComponent(Part Component, int quant) throws RemoteException;
	public void setId(String id) throws RemoteException;
	public void setComponentsList(CList list) throws RemoteException;
	
//	@Override
//	boolean equals(Object obj);
//	
//	@Override
//	int hashCode();
}
