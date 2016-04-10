package br.com.partRepository.interfaces;

import java.util.UUID;

import br.com.partRepository.classes.CList;

public interface Part{
	
	//getters
	public UUID getPartId();
	public String getPartName();
	public String getPartInfo();
	public CList getComponentList();

	//setters
	public void setPartName(String partName);
	public void setPartInfo(String partInfo);
	public void AddComponent(Part Component, int quant);
	public void setId(String id);
	public void setComponentsList(CList list);
	
	@Override
	boolean equals(Object obj);
	
	@Override
	int hashCode();
}
