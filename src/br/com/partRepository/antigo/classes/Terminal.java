package br.com.partRepository.antigo.classes;

import br.com.partRepository.antigo.telas.MainScreen;
import br.com.partRepository.antigo.telas.Screen;

public class Terminal {

	private Screen CurrentScreen;

	public Terminal(){
		
		this.CurrentScreen = new MainScreen(this);
	}
	
	public static void main(String[] args) {
		
		
	}
}