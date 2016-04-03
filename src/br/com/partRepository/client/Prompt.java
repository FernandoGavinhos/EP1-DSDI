package br.com.partRepository.client;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.partRepository.classes.CList;
import br.com.partRepository.interfaces.Part;
import br.com.partRepository.interfaces.PartRepository;

public class Prompt extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField command;//user input
	private JTextArea output;//command output
	private JLabel commandLabel;
	private Container window;
	private JButton send;
	private PartRepository repo; //current repository
	private Part component; //current part
	private CList subPart; //current subPart
	
	public Prompt(){
		super("Part Repository");
		
		commandLabel = new JLabel("Comando: ");
		commandLabel.setToolTipText("Digite o comando");
		command = new JTextField();
		
		//listener - enter keyboard key
		command.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 10 && !command.getText().trim().isEmpty()){
					output.append(command.getText());
					output.append("\n");
					command.setText("");
					command.requestFocusInWindow();
					//TODO somente um teste, copia o que digitou no output
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
		
		new JLabel("Saída");
		output = new JTextArea();
		output.setEditable(false);
		
		send = new JButton("Enviar");
		send.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Somente um teste, desconsiderar, talvez nem precise do botão
				if(!command.getText().trim().isEmpty()){
					output.append(command.getText());
					output.append("\n");
				}
				command.setText("");
				command.requestFocusInWindow();
			}
		});
		
		window = getContentPane();
		window.setLayout(new GridLayout(4, 2,10,10));
		window.add(commandLabel);
		window.add(command);
		window.add(send);
		window.add(output);
		window.add(new JScrollPane(output));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 400);
		setVisible(true);
	}
	
	public static void main(String[]args){
		new Prompt();
	}
}
