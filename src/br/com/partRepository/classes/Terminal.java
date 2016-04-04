package br.com.partRepository.classes;

import br.com.partRepository.interfaces.Screen;
import br.com.partRepository.telas.MainScreen;

public class Terminal {

	private Screen CurrentScreen;

	public Terminal(){
		
		this.CurrentScreen = new MainScreen(this);
	}
	
	public static void main(String[] args) {
		
		
	}
}