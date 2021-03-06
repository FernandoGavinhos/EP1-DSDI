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
import java.util.Map;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.com.partRepository.classes.CList;
import br.com.partRepository.classes.Piece;
import br.com.partRepository.classes.SubComponent;
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

		new JLabel("Sa�da");
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
		System.setProperty("java.security.policy","config\\rmi.policy");
		new Prompt();
	}

	public void printOptions(){
		output.append("Comandos" + "\t" + "Descri��o");
		output.append("\n");
		output.append("bind 'servidor' " +"\t"+"Faz o cliente se conectar a outro servidor e muda o reposit�rio corrente."
				+ "Este comando recebe o nome de um reposit�rio e obt�m do servi�o de nomes uma refer�ncia para esse "
				+ "reposit�rio, que passa a ser o reposit�rio corrente.");
		output.append("\n");
		output.append("listp " +"\t"+"lista as pe�as do servidor corrente");
		output.append("\n");
		output.append("getp 'id' " +"\t"+"busca e referencia uma pe�a pelo id");
		output.append("\n");
		output.append("showp " +"\t"+"mostra atributos da pe�a atual");
		output.append("\n");
		output.append("clearlist " +"\t"+"esvazia a lista de subpe�as atual");
		output.append("\n");
		output.append("addsubpart 'n' " +"\t"+"adiciona a lista de subpe�as atual n unidades da pe�a atual");
		output.append("\n");
		output.append("quit " +"\t"+"encerra a execu��o");
		output.append("\n");
		output.append("addp \"nome\" \"descri��o da pe�a\" " +"\t"+"adiciona uma pe�a ao reposit�rio. A lista de subpe�as"
				+ "corrente � usada como lista de subcomponentes");
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
			output.append("Op��o utilizada incorretamente, digite help");
		}
		else{
			String remoteName = "rmi://127.0.0.1/" + cmd[1].toLowerCase();
			try {
				PartRepository repo = (PartRepository) Naming.lookup(remoteName);
				this.repo = repo;
				output.append("Conectado no servidor " + remoteName);
			} catch (Exception e) {
				output.append("erro ao conectar ao servidor " + remoteName);
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
						output.append("ID: " + aux.getPartId().toString() +
								"\tNome: " + aux.getPartName() +
								"\tDescri��o: " + aux.getPartInfo());
						output.append("\n");

						//subcomponents
						if(aux.getComponentList() != null){
							Iterator<SubComponent> i = aux.getComponentList().getCList().iterator();
							while (i.hasNext()){
								SubComponent subI = i.next();
								output.append("\t Subcomponente: ID: " + subI.getPart().getPartId().toString() +
										"\tNome: " + subI.getPart().getPartName() +
										"\tQuantidade: " + subI.getQuantity());
								output.append("\n");
							}
						}
					}
				}
				else output.append("N�o h� parts no reposit�rio");
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
						output.append("N�o encontrado a part de id " + cmd[1]);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e2){
					output.append("O id deve possuir a estrutura '00000008-0009-000a-000b-00000000000c', contendo n�meros hexadecimais");
				}

			}
			else output.append("Op��o utilizada incorretamente, digite help");
		}
	}

	public void showp(){
		try{
			if(this.component != null) output.append(
					"ID: " + this.component.getPartId().toString() +
					"\tNome: " + this.component.getPartName() +
					"\tDescri��o: " + this.component.getPartInfo());
			else output.append("N�o h� pe�a selecionada, digite help");
		} catch(RemoteException e){
			e.printStackTrace();
		}
	}

	public void clearList(){
		if(this.subPart != null){
			this.subPart.clear();
			output.append("Todos elementos da lista de subparts removidos");
		}
		else output.append("A lista de subparts j� est� vazia");
	}

	public void addSubPart(String[]cmd){
		if(cmd.length != 2) output.append("Op��o utilizada incorretamente, digite help");
		else{
			int n = Integer.parseInt(cmd[1]);
			if(this.subPart == null) this.subPart = new CList();
			if(this.component != null){
				this.subPart.addComponent(component, n);
				output.append("Part adicionada � lista de subParts");
			}
			else output.append("N�o h� Part para adicionar, consulte as op��es no help");
		}
	}

	public void addp(String[]cmd){
		if(repo == null) output.append("Favor conectar com o servidor antes");
		else{
			if(cmd.length < 3) output.append("Op��o utilizada incorretamente, digite help");
			else{
				Part p;
				try {
					p = new Piece();
					p.setPartName(cmd[1]);
					String partInfo = new String();
					for(int i = 2;i < cmd.length;i++){
						partInfo = partInfo + " "+cmd[i];
					}
					p.setPartInfo(partInfo);
					p.setComponentsList(this.subPart);
					if(repo.addPart(p)) {
						output.append("Inser��o com sucesso " + "ID:" + p.getPartId().toString() 
								+ " Nome:" + p.getPartName() + " Descri��o:" + p.getPartInfo());

					}
					else output.append("Erro na inser��o");

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
		output.append("Op��o inv�lida: para verificar as op��es digite help");
	}

}
