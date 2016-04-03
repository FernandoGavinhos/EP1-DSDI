package br.com.partRepository.classes;

import java.util.HashMap;
import java.util.Map;

import br.com.partRepository.interfaces.Part;

public class CList {

	//comp list<subPart, quant>
	private Map<Part, Integer> cList;

	//initialize the map
	public CList(){
		cList = new HashMap<Part,Integer>(); 
	}
	
	//the component is primitive if it's subcomponent list is empty
	public boolean isPrimitive(){
		return this.cList.isEmpty();
	}

	//if the component already exists in the subcomponent list, it's quantity is added to the existing component. If not, a new subcomponent is created
	public void addComponent(Part component, int quant){
		
		//iterates over each entry of the subcomponent list searching for a component with the same name of the component to be added
		for (Map.Entry<Part, Integer> entry : this.cList.entrySet()){
			//if a component with the same name of the component to be added is found, increment it's quantity e exit from the method
			if(entry.getKey().getPartName() == component.getPartName()){
				entry.setValue(entry.getValue() + quant);
				return;
			}
		}
		//if no component were found, add the component to the subcomponent list
		this.cList.put(component, quant);
	}
}
