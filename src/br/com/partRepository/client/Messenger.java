package br.com.partRepository.client;

import java.rmi.RemoteException;
import java.rmi.server.UID;

import br.com.partRepository.repository.Part;

public interface Messenger extends java.rmi.Remote{

	//Test connection
	public String echo(String msg) throws RemoteException;
	
	//Connect to the server with the name given in the parameter serverName
	public String connectToServer(String serverName) throws RemoteException;
	
	//Return the name and the number of parts of the part repository implemented by the server
	public String getRepositoryInfo() throws RemoteException;
	
	//List all the parts in the server's repository
	public String getPartList() throws RemoteException;
	
	//Find a specific part in the server's repository
	public String getPart(UID partID, Part part) throws RemoteException;
	
	//Add a part to the server's repository
	public String addPart(Part newPart) throws RemoteException;
	
	//Add a part 'newPart' to the server's repository as a sub component of the part 'part'
	public String addPart(Part part, String newPart) throws RemoteException;
	
	//Return name and description of the part
	public String getPartInfo(Part part) throws RemoteException;
	
	//Return the name of the part repository server which contains the part
	public String getPartRepositoryName(Part part) throws RemoteException;
	
	//Show if a part is primitive or not
	public String isPartPrimitive(Part part) throws RemoteException;
	
	//Get the number of direct and primitive sub components of the part
	public String countPrimitiveDirectSubComponents(Part part) throws RemoteException;
	
	//List all subcomponents of the part
	public String getSubComponentList(Part part) throws RemoteException;
}