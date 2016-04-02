package br.com.partRepository.interfaces;

import br.com.partRepository.classes.CList;

public interface Part extends Comparable<Part>{
	
	//getters
	public int getPartId();
	public String getPartName();
	public String getPartInfo();
	public CList getComponentList();

	//setters
	public void setPartName(String partName);
	public void setPartInfo(String partInfo);
	public void AddComponent(Part Component);
	
}
