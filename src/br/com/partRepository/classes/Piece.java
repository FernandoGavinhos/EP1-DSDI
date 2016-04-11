package br.com.partRepository.classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.UUID;

import br.com.partRepository.interfaces.Part;

public class Piece extends UnicastRemoteObject implements Part {

	private UUID id;
	private String partName;
	private String partInfo;
	private CList componentList;
	
	public Piece() throws RemoteException{
		this.id = UUID.randomUUID();
		this.componentList = new CList();
	}
	
	
	@Override
	public UUID getPartId() {
		return this.id;
	}

	@Override
	public String getPartName() {
		return this.partName;
	}

	@Override
	public String getPartInfo() {
		return this.partInfo;
	}

	@Override
	public CList getComponentList() {
		return this.componentList;
	}

	@Override
	public void setPartName(String partName) {
		this.partName = partName;
	}

	@Override
	public void setPartInfo(String partInfo) {
		this.partInfo = partInfo;
		
	}

	@Override
	public void AddComponent(Part Component, int quant) {
		this.componentList.addComponent(Component, quant);		
	}
	
	//Only Test
	public static void main(String[] args) {
		try{
			Piece piece = new Piece();
			piece.setPartInfo("partyInfo dsdsd");
			piece.setPartName("partname name name");
			
			Piece piece2 = new Piece();
			piece2.setPartInfo("partInfo piece2");
			piece2.setPartName("partName piece2");
			piece.componentList.addComponent(piece2, 10);
			System.out.println(piece.toString());

		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void setId(String id) throws RemoteException{
		this.id = UUID.fromString(id);	
	}


	@Override
	public void setComponentsList(CList list) throws RemoteException{
		if(list != null){
			for(int i = 0;i < list.getCList().size();i++){
				SubComponent aux = list.getCList().get(i);
				this.componentList.addComponent(aux.getPart(), aux.getQuantity());
			}
		}
		
	}

	
}
