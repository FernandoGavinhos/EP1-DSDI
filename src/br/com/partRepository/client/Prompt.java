package br.com.partRepository.client;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.partRepository.classes.CList;
import br.com.partRepository.interfaces.Part;
import br.com.partRepository.interfaces.PartRepository;
import br.com.partRepository.interfaces.Screen;

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
		if(System.getSecurityManager() == null){
			System.setSecurityManager(new SecurityManager());
		}
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
					String cmd = command.getText().toLowerCase();
					String[]inputs = cmd.split(" ");
					if(inputs.length < 1) wrong();
					//TODO somente um teste, copia o que digitou no output
					else{
						if(inputs[0].equals("help")) printOptions();
						else execute(inputs);
					}
					output.append("\n");
					command.setText("");
					command.requestFocusInWindow();
					
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

	public void printOptions(){
		output.append("Comandos" + "\t" + "Descrição");
		output.append("\n");
		output.append("bind 'servidor' " +"\t"+"Faz o cliente se conectar a outro servidor e muda o repositório corrente."
				+ "Este comando recebe o nome de um repositório e obtém do serviço de nomes uma referência para esse "
				+ "repositório, que passa a ser o repositório corrente.");
		output.append("\n");
		output.append("listp " +"\t"+"lista as peças do servidor corrente");
		output.append("\n");
		output.append("getp 'id' " +"\t"+"busca e referencia uma peça pelo id");
		output.append("\n");
		output.append("showp " +"\t"+"mostra atributos da peça atual");
		output.append("\n");
		output.append("clearlist " +"\t"+"esvazia a lista de subpeças atual");
		output.append("\n");
		output.append("addsubpart 'n' " +"\t"+"adiciona a lista de subpeças atual n unidades da peça atual");
		output.append("\n");
		output.append("quit " +"\t"+"encerra a execução");
		output.append("\n");
		output.append("addp 'nome' 'descrição da peça' " +"\t"+"adiciona uma peça ao repositório. A lista de subpeças"
				+ "corrente é usada como lista de subcomponentes");
		output.append("\n");
		
	}

	public void execute(String[] cmd) {
		String option = cmd[0];
		switch (option) {
		case "bind":
			bind(cmd);
			break;
		case "listp":
			listp();
			break;
		case "getp":
			getp(cmd);
			break;
		case "showp":
			showp();
			break;
		case "clearlist":
			clearList();
			break;
		case "addsubpart":
			addSubPart(cmd);
			break;
		case "addp":
			addp(cmd);
			break;
		case "quit":
			quit();
			break;
		default:
			wrong();
			break;
		}
	}
	
	public void bind(String[]cmd){
		if(cmd.length != 2){
			output.append("Opção utilizada incorretamente, digite help");
		}
		else{
			try {
				String remoteName = cmd[1];
				Registry registry = LocateRegistry.getRegistry("TODO");//TODO
				PartRepository repo = (PartRepository) registry.lookup(remoteName);
				this.repo = repo;
			} catch (Exception e) {
				System.out.println("Erroooooou");
				e.printStackTrace();
			}
		}
		
	}
	
	public void listp(){
		//TODO
	}
	
	public void getp(String[]cmd){
		//TODO
	}
	
	public void showp(){
		//TODO
	}
	
	public void clearList(){
		//TODO
	}
	
	public void addSubPart(String[]cmd){
		//TODO
	}
	
	public void addp(String[]cmd){
		//TODO
	}
	
	public void quit(){
		//TODO
	}
	
	public void wrong(){
		output.append("Opção inválida: para verificar as opções digite help");
	}
	
}
