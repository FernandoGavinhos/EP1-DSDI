package br.com.partRepository.classes;

public class PartRepositoryClient {

	private int clientId;
	private String clientName;
	private PartRepositoryServer server;
	
	//constructor
	public PartRepositoryClient(int id, String name, PartRepositoryServer server){
		this.clientId = id;
		this.clientName = name;
		this.server = server;
	}
	
	//getters
	public String getClientName() {
		return clientName;
	}
	
	public int getClientId() {
		return clientId;
	}

	//setters
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public void command(String cmd){
		
		//execute the command ordered by the user
		switch (cmd){
		//comandos... [PAREI AQUI]
		default:
			System.out.println("Comando inválido.");
		}
	}
}
