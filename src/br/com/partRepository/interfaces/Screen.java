package br.com.partRepository.interfaces;

import java.io.IOException;

public interface Screen {
	
	public void printOptions() throws IOException;
	public void execute(String cmd);
}