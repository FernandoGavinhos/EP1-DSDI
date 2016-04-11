package br.com.partRepository.classes;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

import br.com.partRepository.interfaces.Part;

public class CList implements Serializable {

	//comp list<subPart, quant>
	private LinkedList<SubComponent> cList;

	private static final long serialVersionUID = 1L;

	//initialize the map
	public CList(){
		cList = new LinkedList<SubComponent>(); 
	}

	//the component is primitive if it's subcomponent list is empty
	public boolean isPrimitive(){
		return this.cList.isEmpty();
	}

	//if the component already exists in the subcomponent list, it's quantity is added to the existing component. If not, a new subcomponent is created
	public void addComponent(Part component, int quant){

		SubComponent sub = new SubComponent(component, quant);

		//iterates over each entry of the subcomponent list searching for a component with the same name of the component to be added

		try{
			Iterator<SubComponent> i = cList.iterator();
			while (i.hasNext()){
				SubComponent subI = i.next();
				//if a component with the same name of the component to be added is found, increment it's quantity e exit from the method
				if(subI.getPart().getPartId().equals(component.getPartId())){
					subI.setQuantity(subI.getQuantity() + quant);
					return;
				}
			}
			//if no component were found, add the component to the subcomponent list
			this.cList.add(sub);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

	public void clear(){
		this.cList.clear();
	}

	public LinkedList<SubComponent> getCList(){
		return this.cList;
	}
}
