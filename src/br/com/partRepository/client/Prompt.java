package br.com.partRepository.client;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.partRepository.classes.CList;
import br.com.partRepository.classes.Piece;
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
				if(!command.getText().trim().isEmpty()){
					String cmd = command.getText().toLowerCase();
					String[]inputs = cmd.split(" ");
					if(inputs.length < 1) wrong();
					else{
						if(inputs[0].equals("help")) printOptions();
						else execute(inputs);
					}
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
		System.setProperty("java.security.policy","C:\\Users\\Guilherme\\workspace\\EP1-DSDI\\src\\config\\rmi.policy");
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
		output.append("addp \"nome\" \"descrição da peça\" " +"\t"+"adiciona uma peça ao repositório. A lista de subpeças"
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
				String remoteName = "rmi://127.0.0.1/"+cmd[1];
				PartRepository repo = (PartRepository) Naming.lookup(remoteName);
				this.repo = repo;
				output.append("Conectado no servidor "+remoteName);
			} catch (Exception e) {
				//System.out.println("Erroooooou");
				e.printStackTrace();
			}
		}
		
	}
	
	public void listp(){
		if(repo == null) output.append("Favor conectar com o servidor antes");
		else{
			List<Part> list;
			try {
				list = repo.listAll();
				if(list != null && !list.isEmpty()){
					Iterator<Part> it = list.iterator();
					while(it.hasNext()){
						Part aux = it.next();
						output.append(aux.toString());
					}
				}
				else output.append("Não há parts no repositório");
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void getp(String[]cmd){
		if(repo == null) output.append("Favor conectar com o servidor antes");
		else{
			if(cmd.length == 2){
				try {
					Part test = repo.getPart(cmd[1]);
					if(test != null){
						this.component = test;
						output.append("Achou");
					}
					else{
						output.append("Não encontrado");
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			}
			else output.append("Opção utilizada incorretamente, digite help");
		}
	}
	
	public void showp(){
		if(this.component != null) output.append(this.component.toString());
		else output.append("Não há peça selecionada, digite help");
	}
	
	public void clearList(){
		this.subPart.clear();
	}
	
	public void addSubPart(String[]cmd){
		if(cmd.length != 2) output.append("Opção utilizada incorretamente, digite help");
		else{
			int n = Integer.parseInt(cmd[1]);
			this.subPart.addComponent(component, n);
		}
	}
	
	public void addp(String[]cmd){
		if(repo == null) output.append("Favor conectar com o servidor antes");
		else{
			if(cmd.length != 3) output.append("Opção utilizada incorretamente, digite help");
			else{
				Part p;
				try {
					p = new Piece();
					p.setPartName(cmd[1]);
					p.setPartInfo(cmd[2]);
					p.setComponentsList(this.subPart);
					if(repo.addPart(p)) output.append("Inserção com sucesso");
					else output.append("Erro na inserção");
				
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				
			}
		}
	}
	
	public void quit(){
		this.dispose();
	}
	
	public void wrong(){
		output.append("Opção inválida: para verificar as opções digite help");
	}
	
}
