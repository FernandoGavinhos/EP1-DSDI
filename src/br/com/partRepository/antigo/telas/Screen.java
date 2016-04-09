package br.com.partRepository.antigo.telas;

import java.io.IOException;

public interface Screen {
	
	public void printOptions() throws IOException;
	public void execute(String cmd);
}