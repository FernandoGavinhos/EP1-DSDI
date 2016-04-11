package br.com.partRepository.classes;

import java.io.Serializable;

import br.com.partRepository.interfaces.Part;

public class SubComponent implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Part part;
	private int quantity;
	
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public SubComponent (Part p, int q){
		this.part = p;
		this.quantity = q;
	}
}
