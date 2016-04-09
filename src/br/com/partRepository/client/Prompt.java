package br.com.partRepository.client;

import java.lang.invoke.MethodHandles.Lookup;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UID;

import br.com.partRepository.repository.CList;
import br.com.partRepository.repository.Part;
import br.com.partRepository.server.PartRepository;

public class Prompt {

	private PartRepository currentRepository;
	private Part currentPart;
	private CList ComponentList;

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		Messenger msg = (Messenger) Naming.lookup("http://localhost/test");
		System.out.println(msg.echo("It's alive!"));
	}
}