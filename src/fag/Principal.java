package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import fag.objetos.Funcionario;
import fag.objetos.Construcao;

public class Principal {
	
	static Scanner scan = new Scanner(System.in);
	static List<Funcionario> listaFuncionarios = new ArrayList<>();
	static List<Construcao> listaConstrucoes = new ArrayList<>();
	
	public static void main(String[] args) {
		popularFuncionarios();
		popularConstrucoes();
		mostrarMenu();
	}

	//Populadores-----------------------------------------------------------------//
	
	public static void popularFuncionarios() {
		Funcionario funcionarioUm = new Funcionario("João", "Pedreiro",1518);
		Funcionario funcionarioDois = new Funcionario("Pedro", "Servente",1000);
		Funcionario funcionarioTres = new Funcionario("Carlos", "Capataz",5000);
		
		listaFuncionarios.add(funcionarioUm);
		listaFuncionarios.add(funcionarioDois);
		listaFuncionarios.add(funcionarioTres);
	}
	
	public static void popularConstrucoes() {
		Construcao construcaoUm = new Construcao("Predio A", "Rua Bernabeu", "21/10/2025");
		construcaoUm.adicionarFuncionario(listaFuncionarios.get(0));
		construcaoUm.adicionarFuncionario(listaFuncionarios.get(2));
		Construcao construcaoDois = new Construcao("Predio B", "Rua Camp Nou", "12/01/2025");
		construcaoDois.adicionarFuncionario(listaFuncionarios.get(2));
		construcaoDois.adicionarFuncionario(listaFuncionarios.get(1));
		Construcao construcaoTres = new Construcao("Predio C", "Rua Arena", "03/03/2025");
		construcaoTres.adicionarFuncionario(listaFuncionarios.get(2));
		construcaoTres.adicionarFuncionario(listaFuncionarios.get(1));
		construcaoTres.adicionarFuncionario(listaFuncionarios.get(0));
		
		listaConstrucoes.add(construcaoUm);
		listaConstrucoes.add(construcaoDois);
		listaConstrucoes.add(construcaoTres);
	}
	
	//----------------------------------------------------------------------------//
	public static void mostrarMenu() {
		int escolha = -1;
		do {
		System.out.println("============================== MENU ==============================");
		System.out.println("1 - Cadastrar");
		System.out.println("2 - Verificar");
		System.out.println("3 - Filtrar");
		System.out.println("4 - Atualizar");
		System.out.println("5 - Remover");
		System.out.println("0 - Sair");
		
		escolha = scan.nextInt();
		scan.nextLine();
		validarEscolha(escolha);
		}while(escolha!=0);
	}
	
	public static void validarEscolha(int escolha) {
		switch (escolha) {
		case 1:
			mostrarCadastrar();
			break;
		case 2:
			mostrarVerificar();
			break;
		case 3:
			mostrarFiltrar();
			break;
		case 4:
			
			break;
		case 5:
			mostrarRemover();
			break;
		case 0:
			System.out.println("\nObrigado pela preferência!");
		default:
			break;
		}
	}
	
	//CadastrarInicio------------------------------------------------------------------------------------------//
	
	public static void mostrarCadastrar() {
		int escolha = 0;
		do {
			System.out.println("O que deseja cadastrar?");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Construção");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha!= 1 && escolha!=2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha!= 1 && escolha!=2);
		
		if(escolha == 1) {
			cadastrarFuncionario();
		}else {
			cadastrarConstrucao();
		}
	}
	
	public static void cadastrarFuncionario() {
		int respostaConstrucao = 0;
		int escolha =0;
		int num = 0;
		Funcionario funcionario = new Funcionario();
		System.out.println("Nome:");
		funcionario.setNome(scan.nextLine());
		System.out.println("Cargo:");
		funcionario.setCargo(scan.nextLine());
		System.out.println("Salario:");
		funcionario.setSalario(scan.nextDouble());
		
		boolean verificaFuncionario = false;
		for(int i=0; i<listaFuncionarios.size(); i++) {
			if(listaFuncionarios.get(i).getNome().equals(funcionario.getNome())) {
				System.out.println("Funcionário já cadastrado!");
				verificaFuncionario=true;
				break;
			}
		}
		
		if(verificaFuncionario==true) {
			return;
		}
		do {
			do {
				System.out.println("=== ADICIONAR CONSTRUÇÃO ===");
				System.out.println("Selecione:");
				System.out.println("1 - Adicionar construção já existente");
				System.out.println("2 - Nova construção");
				escolha = scan.nextInt();
				scan.nextLine();
				
				if(escolha!= 1 && escolha!=2) {
					System.out.println("Escolha uma opção válida!");
				}
			}while(escolha!=1 && escolha!=2);
			
			if(escolha==1) {
				if (listaConstrucoes.isEmpty()) {
	                System.out.println("Não há construções cadastradas!");
	            } else {
	                verificarConstrucao();
	                
	                do {
		                System.out.print("Digite o número da construção: ");
		                num = (scan.nextInt()-1);
		                scan.nextLine();
		                
		                if (num < 0 || num >= listaConstrucoes.size()) {
		                	System.out.println("Número inválido!");
		                }
	                }while(num < 0 || num >= listaConstrucoes.size());
	                
	                funcionario.adicionarConstrucao(listaConstrucoes.get(num));
	                System.out.println("Funcionário cadastrado com sucesso!");
	            }
			}else {
				Construcao construcao = new Construcao();
				System.out.println("Nome:");
				construcao.setNome(scan.nextLine());
				System.out.println("Endereço:");
				construcao.setEndereco(scan.nextLine());
				System.out.println("Data de inicio da obra:");
				construcao.setInicio(scan.nextLine());
				
				boolean existe=false;
				for(int i=0; i<listaConstrucoes.size(); i++) {
					if(listaConstrucoes.get(i).getNome().equals(construcao.getNome())) {
						funcionario.adicionarConstrucao(listaConstrucoes.get(i));
						System.out.println("Construção já existe, associação realizada com sucesso!");
						existe=true;
						break;
					}
				}
				if(!existe) {
					listaConstrucoes.add(construcao);
					funcionario.adicionarConstrucao(construcao);
					System.out.println("Construção associada com sucesso!");
				}
			}
			
			System.out.println("Deseja cadastrar mais construções para este funcionário?");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			respostaConstrucao = scan.nextInt();
			
			if(respostaConstrucao!= 1 && respostaConstrucao!=2) {
				System.out.println("Escolha uma opção válida!");
			}
			
		}while(respostaConstrucao!=2);
		funcionario.mostrarFuncionarioCompleto();
		listaFuncionarios.add(funcionario);
	}
	
	public static void cadastrarConstrucao() {
		int respostaFuncionario = 0;
		int escolha =0;
		int num = 0;
		Construcao construcao = new Construcao();
		System.out.println("Nome:");
		construcao.setNome(scan.nextLine());
		System.out.println("Endereço:");
		construcao.setEndereco(scan.nextLine());
		System.out.println("Data de inicio da obra:");
		construcao.setInicio(scan.nextLine());
		
		boolean verificaConstrucao = false;
		for(int i=0; i<listaConstrucoes.size(); i++) {
			if(listaConstrucoes.get(i).getNome().equals(construcao.getNome())) {
				System.out.println("Construção já cadastrada!");
				verificaConstrucao=true;
				break;
			}
		}
		
		if(verificaConstrucao==true) {
			return;
		}
		do {
			do {
				System.out.println("=== ADICIONAR FUNCIONÁRIO ===");
				System.out.println("Selecione:");
				System.out.println("1 - Adicionar funcionário já cadastrado");
				System.out.println("2 - Novo funcionário");
				escolha = scan.nextInt();
				scan.nextLine();
				
				if(escolha!= 1 && escolha!=2) {
					System.out.println("Escolha uma opção válida!");
				}
			}while(escolha!=1 && escolha!=2);
			
			if(escolha==1) {
				if (listaFuncionarios.isEmpty()) {
	                System.out.println("Não há funcionários disponíveis!");
	            } else {
	               verificarFuncionario();
	                
	                do {
		                System.out.print("Digite o número do funcionário: ");
		                num = (scan.nextInt()-1);
		                scan.nextLine();
		                
		                if (num < 0 || num >= listaFuncionarios.size()) {
		                	System.out.println("Número inválido!");
		                }
	                }while(num < 0 || num >= listaFuncionarios.size());
	                
	                construcao.adicionarFuncionario(listaFuncionarios.get(num));
	                System.out.println("Funcionário cadastrado com sucesso!");
	            }
			}else {
				Funcionario funcionario = new Funcionario();
				System.out.println("Nome:");
				funcionario.setNome(scan.nextLine());
				System.out.println("Cargo:");
				funcionario.setCargo(scan.nextLine());
				System.out.println("Salario:");
				funcionario.setSalario(scan.nextDouble());
				
				boolean existe = false;
				for(int i=0; i<listaFuncionarios.size(); i++) {
					if(listaFuncionarios.get(i).getNome().equals(funcionario.getNome())) {
						System.out.println("Funcionário já existe, associação realizada com sucesso!");
						existe=true;
						break;
					}
				}
				if(!existe) {
					listaFuncionarios.add(funcionario);
					construcao.adicionarFuncionario(funcionario);
					System.out.println("Funcionário associado com sucesso!");
				}
			}
			
			System.out.println("Deseja cadastrar mais funcionário para esta construção??");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			respostaFuncionario = scan.nextInt();
			
			if(respostaFuncionario!= 1 && respostaFuncionario!=2) {
				System.out.println("Escolha uma opção válida!");
			}
			
		}while(respostaFuncionario!=2);
		construcao.mostrarConstrucaoCompleto();
		listaConstrucoes.add(construcao);
	}
	
	//CadastrarFim---------------------------------------------------------------------------------------------//
	
	//VerificarInicio------------------------------------------------------------------------------------------//
	
	public static void mostrarVerificar() {
		int escolha = 0;
		do {
			System.out.println("O que deseja verificar?");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Construção");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha!= 1 && escolha!=2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha!= 1 && escolha!=2);
		
		if(escolha == 1) {
			verificarFuncionario();
		}else {
			verificarConstrucao();
		}
	}
	
	public static void verificarFuncionario() {
		System.out.println("Funcionários:");
		for(int i = 0;i < listaFuncionarios.size();i++) {
			System.out.printf("%d - ",i+1);
			listaFuncionarios.get(i).mostrarFuncionario();
		}
	}
	
	public static void verificarConstrucao() {
		System.out.println("Construções:");
		for(int i = 0;i < listaConstrucoes.size();i++) {
			System.out.printf("%d - ",i+1);
			listaConstrucoes.get(i).mostrarConstrucao();
		}
	}
	
	//VerificarFim---------------------------------------------------------------------------------------------//
	
	//FiltrarInicio--------------------------------------------------------------------------------------------//

	public static void mostrarFiltrar() {
		int escolha = 0;
		do {
			System.out.println("O que deseja filtrar?");
			System.out.println("1 - Construções por funcionário");
			System.out.println("2 - Funcionários por construção");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha!= 1 && escolha!=2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha!= 1 && escolha!=2);
		
		if(escolha == 1) {
			filtrarConstrucoesDoFuncionario();
		}else {
			filtrarFuncionariosDaConstrucao();
		}
	}
	
	public static void filtrarConstrucoesDoFuncionario() {
		int escolha = 0;
		do {
			verificarFuncionario();
			System.out.println("Selecione o funcionário que deseja filtrar!");
			escolha = (scan.nextInt()-1);
			scan.nextLine();
			
			if(escolha < 0 || escolha >= listaFuncionarios.size()) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha >= listaFuncionarios.size());
		listaFuncionarios.get(escolha).mostrarFuncionarioCompleto();
	}
	
	public static void filtrarFuncionariosDaConstrucao() {
		int escolha = 0;
		do {
			verificarConstrucao();
			System.out.println("Selecione a construção que deseja filtrar!");
			escolha = (scan.nextInt()-1);
			scan.nextLine();
			
			if(escolha < 0 || escolha >= listaConstrucoes.size()) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha >= listaConstrucoes.size());
		listaConstrucoes.get(escolha).mostrarConstrucaoCompleto();
	}
	
	//FiltrarFim-----------------------------------------------------------------------------------------------//
	
	//RemoverInicio--------------------------------------------------------------------------------------------//
	
	public static void mostrarRemover() {
		int escolha = 0;
		do {
			System.out.println("O que deseja remover?");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Construção");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha!= 1 && escolha!=2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha!= 1 && escolha!=2);
		
		if(escolha == 1) {
			removerFuncionario();
		}else {
			removerConstrucao();
		}
	}
	
	public static void removerFuncionario() {
		int escolha = 0;
		do {
			verificarFuncionario();
			System.out.println("Selecione o funcionário que deseja remover!");
			escolha = (scan.nextInt()-1);
			scan.nextLine();
			
			if(escolha < 0 || escolha >= listaFuncionarios.size()) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha >= listaFuncionarios.size());
		
		Funcionario removido = listaFuncionarios.remove(escolha);
		
		for (int i = 0; i < listaConstrucoes.size(); i++) {
	        Construcao c = listaConstrucoes.get(i);
	        c.getFuncionarios().remove(removido);
	    }
		System.out.printf("Funcionário %s removido com sucesso!\n",removido.getNome());
	}
	
	public static void removerConstrucao() {
		int escolha = 0;
		do {
			verificarConstrucao();
			System.out.println("Selecione a construção que deseja remover!");
			escolha = (scan.nextInt()-1);
			scan.nextLine();
			
			if(escolha < 0 || escolha >= listaConstrucoes.size()) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha >= listaConstrucoes.size());
		
		Construcao removida = listaConstrucoes.remove(escolha);
		
		for (int i = 0; i < listaFuncionarios.size(); i++) {
	        Funcionario f = listaFuncionarios.get(i);
	        f.getConstrucoes().remove(removida);
		}
		System.out.printf("Construção %s removida com sucesso!\n",removida.getNome());
	}
	
	//RemoverFim-----------------------------------------------------------------------------------------------//

}