package br.com.partRepository.antigo.telas;

import java.io.IOException;

import br.com.partRepository.antigo.classes.Terminal;

public class MainScreen implements Screen {

	private Terminal terminal;
	private String br = System.getProperty("line.separator");
	
	public MainScreen(Terminal terminal){
		
		this.terminal = terminal;
	}
	
	@Override
	public void printOptions() throws IOException {
		
		Runtime.getRuntime().exec("cls");
		System.out.println(
				"Options:" + this.br + 
				"1 - New server" + this.br + 
				"2 - Enter server" + this.br + 
				"3 - Delete server"
				);
	}

	@Override
	public void execute(String cmd) {
		
		switch (cmd){
			case "1":
				//calls screen 'Create Server'
			case "2":
				//calls screen 'Access Server'
			case "3":
				//calls screen 'Delete Server'
			default:
				System.out.println("Invalid Command");
		}
	}

}
