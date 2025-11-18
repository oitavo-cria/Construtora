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
		mostrarBoasVindas();
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
	
	public static void mostrarBoasVindas() {
		System.out.println("= = = = = = = = = = CONSTRUIRE = = = = = = = = = =");
		System.out.println("Software de gerenciamento de obras e funcionários.");
		System.out.println("--------------------------------------------------");
		System.out.println("     Seja muito bem-vindo ao nosso software de     ");
		System.out.println("         gerenciamento para construtoras.          ");
		System.out.println("--------------------------------------------------");
		System.out.println("");
		System.out.println("O que deseja para hoje?");
		System.out.println("");
	}
	
	public static void mostrarMenu() {
		int escolha = -1;
		do {
		System.out.println("============================== MENU ==============================");
		System.out.println("1 - Realizar novo cadastro");
		System.out.println("2 - Verificar cadastros");
		System.out.println("3 - Filtrar obras e funcionários");
		System.out.println("4 - Atualizar cadastros");
		System.out.println("5 - Remover cadastros");
		System.out.println("0 - Sair do sistema");
		
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
			mostrarAtualizar();
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
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 || escolha > 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha > 2);
		
		if(escolha == 1) {
			cadastrarFuncionario();
		}else if(escolha == 2){
			cadastrarConstrucao();
		}else {
			return;
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
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 || escolha > 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha > 2);
		
		if(escolha == 1) {
			verificarFuncionario();
		}else if(escolha == 2){
			verificarConstrucao();
		}else {
			return;
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
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 || escolha > 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha > 2);
		
		if(escolha == 1) {
			filtrarConstrucoesDoFuncionario();
		}else if(escolha == 2){
			filtrarFuncionariosDaConstrucao();
		}else {
			return;
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
	
	//AtualizarInicio------------------------------------------------------------------------------------------//
	
	public static void mostrarAtualizar() {
		int escolha = 0;
		do {
			System.out.println("O que deseja atualizar?");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Construção");
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 || escolha > 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha > 2);
		
		if(escolha == 1) {
			atualizarFuncionario();
		}else if(escolha == 2){
			atualizarConstrucao();
		}else {
			return;
		}
	}
	
	public static void atualizarFuncionario() {
		int escolha = 0;
		int selecao = 0;
		verificarFuncionario();
		do {
            System.out.print("Selecione o funcionário que deseja atualizar!");
            selecao = (scan.nextInt()-1);
            scan.nextLine();
            
            if (selecao < 0 || selecao >= listaFuncionarios.size()) {
            	System.out.println("Número inválido!");
            }
        }while(selecao < 0 || selecao >= listaFuncionarios.size());
		
		listaFuncionarios.get(selecao).mostrarFuncionarioCompleto();
		
		do {
			System.out.println("Quais dados deseja atualizar?");
			System.out.println("1 - Nome");
			System.out.println("2 - Cargo");
			System.out.println("3 - Salário");
			System.out.println("4 - Construções");
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 && escolha > 4) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 && escolha > 4);
		
		switch (escolha) {
		case 1:
			atualizarNomeFuncionario(selecao);
			break;
		case 2:
			atualizarCargoFuncionario(selecao);
			break;
		case 3:
			atualizarSalarioFuncionario(selecao);
			break;
		case 4:
			atualizarConstrucoesDoFuncionario(selecao);
			break;
		default:
			return;
		}
		
	}
	
	//---------------------------------------------------------------------------------------------------------//
	
	public static void atualizarNomeFuncionario(int selecao) {
		int resposta = 0;
		String novo;
		
		System.out.println("Insira o novo nome:");
		novo = scan.nextLine();
		
		resposta = confirmarAtualizaçãoFuncionario();
		
		if(resposta == 1) {
			Funcionario atualizado = listaFuncionarios.get(selecao);
			System.out.printf("Nome do funcionário %s atualizado para %s com sucesso!\n", atualizado.getNome(), novo);
			listaFuncionarios.get(selecao).setNome(novo);
		}else {
			return;
		}
	}
	
	public static void atualizarCargoFuncionario(int selecao) {
		int resposta = 0;
		String novo;
		
		System.out.println("Insira o novo cargo:");
		novo = scan.nextLine();
		
		resposta = confirmarAtualizaçãoFuncionario();
		
		if(resposta == 1) {
			Funcionario atualizado = listaFuncionarios.get(selecao);
			System.out.printf("Cargo do funcionário %s atualizado de %s para %s com sucesso!\n", atualizado.getNome(), atualizado.getCargo(), novo);
			listaFuncionarios.get(selecao).setCargo(novo);
		}else {
			return;
		}
	}
	
	public static void atualizarSalarioFuncionario(int selecao) {
		int resposta = 0;
		double novo;
		
		System.out.println("Insira o novo salario:");
		novo = scan.nextDouble();
		
		resposta = confirmarAtualizaçãoFuncionario();
		
		if(resposta == 1) {
			Funcionario atualizado = listaFuncionarios.get(selecao);
			System.out.printf("Salario do funcionário %s atualizado de R$%.2f para R$%.2f com sucesso!\n", atualizado.getNome(), atualizado.getSalario(), novo);
			listaFuncionarios.get(selecao).setSalario(novo);
		}else {
			return;
		}
	}
	
	public static void atualizarConstrucoesDoFuncionario(int selecao) {
		int resposta = 0;
		do {
			System.out.println("O que deseja fazer?");
			System.out.println("1 - Adicionar construções ao cadastro");
			System.out.println("2 - Excluir construções do cadastro");
			System.out.println("3 - Excluir TODAS as construções do cadastro");
			System.out.println("0 - Cancelar");
			resposta=scan.nextInt();
			scan.nextLine();
			
			if(resposta < 0 && resposta > 3) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(resposta < 0 && resposta > 3);
		
		switch (resposta) {
		case 1:
			adicionarConstrucoesCadasto(selecao);
			break;
		case 2:
			removerConstrucoesCadasto(selecao);
			break;
		case 3:
			removerTodasAsConstrucoesDoCadastro(selecao);
			break;
		default:
			return;
		}
	}
	
	public static void adicionarConstrucoesCadasto(int selecao) {
		int confirmacao = 0;
		int resposta = 0;
		int escolha = 0;
		
		Funcionario atualizado = listaFuncionarios.get(selecao);
		
		if (listaConstrucoes.isEmpty()) {
            System.out.println("Não há construções cadastradas!");
        } else {
            verificarConstrucao();
            
            do {
	            do {
	                System.out.print("Digite o número da construção: ");
	                escolha = (scan.nextInt()-1);
	                scan.nextLine();
	                
	                if (escolha < 0 || escolha >= listaConstrucoes.size()) {
	                	System.out.println("Número inválido!");
	                }
	            }while(escolha < 0 || escolha >= listaConstrucoes.size());
	            
	            atualizado.adicionarConstrucao(listaConstrucoes.get(escolha));
	            System.out.println("Construção adicionada com sucesso!");
	            
	            do {
	            	System.out.println("Deseja adicionar mais construções?");
	            	System.out.println("1 - SIM");
	            	System.out.println("2 - NÃO");
	            	confirmacao = scan.nextInt();
	            	scan.nextLine();
	            	
	            	if(confirmacao != 1 && confirmacao != 2) {
	            		System.out.println("Escolha uma opção válida!");
	            	}
	            }while(confirmacao != 1 && confirmacao != 2);
	            
	        }while(confirmacao != 2);
        }
		resposta = confirmarAtualizaçãoFuncionario();
		
		if(resposta == 1) {
			for (int i = 0; i < atualizado.getConstrucoes().size(); i++) {
			    listaFuncionarios.get(selecao).adicionarConstrucao(atualizado.getConstrucoes().get(i));
			}
			System.out.println("Cadastro atualizado com sucesso!");
		}else {
			return;
		}
	}
	
	public static void removerConstrucoesCadasto(int selecao) {
		int escolha = 0;
		
		Funcionario atualizado = listaFuncionarios.get(selecao);
		ArrayList<Construcao> obras = atualizado.getConstrucoes();
		if (obras.isEmpty()) {
		    System.out.println("Esse funcionário não está em nenhuma construção!");
		    return;
		} else {
	            for (int i = 0; i < obras.size(); i++) {
	                    System.out.printf("%d - %s\n", i+1, obras.get(i).getNome());
	                   
	                 }
	            do {
	            System.out.println("Selecione a construção que deseja remover!");
                escolha = (scan.nextInt()-1);
                scan.nextLine();
                
                if(escolha < 0 || escolha >= obras.size()) {
                	System.out.println("Escolha uma opção válida!");
                }
                }while(escolha < 0 || escolha >= obras.size());
                
               	Construcao c = obras.get(escolha);
                atualizado.removerConstrucao(c);
                System.out.println("Construção removida com sucesso!");
	            	
			
			for (int i = 0; i < atualizado.getConstrucoes().size(); i++) {
			    listaFuncionarios.get(selecao).adicionarConstrucao(atualizado.getConstrucoes().get(i));
			}
		}
	}
	
	public static void removerTodasAsConstrucoesDoCadastro(int selecao) {
		int alerta = 0;
		
		Funcionario atualizado = listaFuncionarios.get(selecao);
		ArrayList<Construcao> obras = atualizado.getConstrucoes();
		
		do {
			System.out.println("Tem certeza que deseja remover todas as construções deste funcionário?");
			System.out.println("ATENÇÃO: Esta ação não poderá ser revertida!");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			alerta = scan.nextInt();
			scan.nextLine();
			
			if(alerta != 1 && alerta != 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(alerta != 1 && alerta != 2);
		
		if(alerta == 1) {
			for(int i = obras.size()-1; i >= 0;i--) {
				obras.remove(i);
			}
			System.out.println("Todas as construções foram removidas com sucesso!");
		}else {
			return;
		}
		
	}
	
	public static int confirmarAtualizaçãoFuncionario() {
		int aviso =0;
		do {
			System.out.println("Confirma atualização do funcionário?");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			aviso = scan.nextInt();
			scan.nextLine();
			
			if(aviso != 1 && aviso != 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(aviso != 1 && aviso != 2);
		return aviso;
	}
	
	//---------------------------------------------------------------------------------------------------------//
	
	public static void atualizarConstrucao() {
		int escolha = 0;
		int selecao = 0;
		verificarConstrucao();
		do {
            System.out.print("Selecione a construção que deseja atualizar!");
            selecao = (scan.nextInt()-1);
            scan.nextLine();
            
            if (selecao < 0 || selecao >= listaConstrucoes.size()) {
            	System.out.println("Número inválido!");
            }
        }while(selecao < 0 || selecao >= listaConstrucoes.size());
		
		listaConstrucoes.get(selecao).mostrarConstrucaoCompleto();
		
		do {
			System.out.println("Quais dados deseja atualizar?");
			System.out.println("1 - Nome");
			System.out.println("2 - Endereço");
			System.out.println("3 - Data de início");
			System.out.println("4 - Funcionários");
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 && escolha > 4) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 && escolha > 4);
		
		switch (escolha) {
		case 1:
			atualizarNomeConstrucao(selecao);
			break;
		case 2:
			atualizarEnderecoConstrucao(selecao);
			break;
		case 3:
			atualizarInicioConstrucao(selecao);
			break;
		case 4:
			atualizarFuncionariosDaConstrucao(selecao);
			break;
		default:
			return;
		}
		
	}
	
	//---------------------------------------------------------------------------------------------------------//

	public static void atualizarNomeConstrucao(int selecao) {
		int resposta = 0;
		String novo;
		
		System.out.println("Insira o novo nome:");
		novo = scan.nextLine();
		
		resposta = confirmarAtualizaçãoConstrucao();
		
		if(resposta == 1) {
			Construcao atualizada = listaConstrucoes.get(selecao);
			System.out.printf("Nome da construção %s atualizado para %s com sucesso!\n", atualizada.getNome(), novo);
			listaFuncionarios.get(selecao).setNome(novo);
		}else {
			return;
		}
	}
	
	public static void atualizarEnderecoConstrucao(int selecao) {
		int resposta = 0;
		String novo;
		
		System.out.println("Insira o novo endereço:");
		novo = scan.nextLine();
		
		resposta = confirmarAtualizaçãoConstrucao();
		
		if(resposta == 1) {
			Construcao atualizada = listaConstrucoes.get(selecao);
			System.out.printf("Endereço da construção %s atualizado de %s para %s com sucesso!\n", atualizada.getNome(), atualizada.getEndereco(), novo);
			listaConstrucoes.get(selecao).setEndereco(novo);
		}else {
			return;
		}
	}
	
	public static void atualizarInicioConstrucao(int selecao) {
		int resposta = 0;
		String novo;
		
		System.out.println("Insira a nova data:");
		novo = scan.nextLine();
		
		resposta = confirmarAtualizaçãoConstrucao();
		
		if(resposta == 1) {
			Construcao atualizada = listaConstrucoes.get(selecao);
			System.out.printf("Data de início da construção alterada %s atualizado de %s para %s com sucesso!\n", atualizada.getNome(), atualizada.getInicio(), novo);
			listaConstrucoes.get(selecao).setInicio(novo);
		}else {
			return;
		}
	}
	
	public static void atualizarFuncionariosDaConstrucao(int selecao) {
		int resposta = 0;
		do {
			System.out.println("O que deseja fazer?");
			System.out.println("1 - Adicionar funcionários ao cadastro");
			System.out.println("2 - Excluir funcionários do cadastro");
			System.out.println("3 - Excluir TODOS os funcionários do cadastro");
			System.out.println("0 - Cancelar");
			resposta=scan.nextInt();
			scan.nextLine();
			
			if(resposta < 0 && resposta > 3) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(resposta < 0 && resposta > 3);
		
		switch (resposta) {
		case 1:
			adicionarFuncionariosCadasto(selecao);
			break;
		case 2:
			removerFuncionariosCadasto(selecao);
			break;
		case 3:
			removerTodosOsFuncionariosDoCadastro(selecao);
			break;
		default:
			return;
		}
	}
	
	public static void adicionarFuncionariosCadasto(int selecao) {
		int confirmacao = 0;
		int resposta = 0;
		int escolha = 0;
		
		Construcao atualizada = listaConstrucoes.get(selecao);
		
		if (listaFuncionarios.isEmpty()) {
            System.out.println("Não há funcionários cadastrados!");
        } else {
            verificarFuncionario();
            
            do {
	            do {
	                System.out.print("Digite o número do funcionário: ");
	                escolha = (scan.nextInt()-1);
	                scan.nextLine();
	                
	                if (escolha < 0 || escolha >= listaFuncionarios.size()) {
	                	System.out.println("Número inválido!");
	                }
	            }while(escolha < 0 || escolha >= listaFuncionarios.size());
	            
	            atualizada.adicionarFuncionario(listaFuncionarios.get(escolha));
	            System.out.println("Funcionário adicionado com sucesso!");
	            
	            do {
	            	System.out.println("Deseja adicionar mais funcionários?");
	            	System.out.println("1 - SIM");
	            	System.out.println("2 - NÃO");
	            	confirmacao = scan.nextInt();
	            	scan.nextLine();
	            	
	            	if(confirmacao != 1 && confirmacao != 2) {
	            		System.out.println("Escolha uma opção válida!");
	            	}
	            }while(confirmacao != 1 && confirmacao != 2);
	            
	        }while(confirmacao != 2);
        }
		resposta = confirmarAtualizaçãoConstrucao();
		
		if(resposta == 1) {
			for (int i = 0; i < atualizada.getFuncionarios().size(); i++) {
			    listaConstrucoes.get(selecao).adicionarFuncionario(atualizada.getFuncionarios().get(i));
			}
			System.out.println("Cadastro atualizado com sucesso!");
		}else {
			return;
		}
	}
	
	public static void removerFuncionariosCadasto(int selecao) {
		int escolha = 0;
		
		Construcao atualizada = listaConstrucoes.get(selecao);
		ArrayList<Funcionario> operarios = atualizada.getFuncionarios();
		if (operarios.isEmpty()) {
		    System.out.println("Essa construção não possui nenhum funcionário");
		    return;
		} else {
	            for (int i = 0; i < operarios.size(); i++) {
	                    System.out.printf("%d - %s\n", i+1, operarios.get(i).getNome());
	                   
	                 }
	            do {
	            System.out.println("Selecione o funcionário que deseja remover!");
                escolha = (scan.nextInt()-1);
                scan.nextLine();
                
                if(escolha < 0 || escolha >= operarios.size()) {
                	System.out.println("Escolha uma opção válida!");
                }
                }while(escolha < 0 || escolha >= operarios.size());
                
               	Funcionario f = operarios.get(escolha);
                atualizada.removerFuncionario(f);
                System.out.println("Funcionário removido com sucesso!");
	            	
			
			for (int i = 0; i < atualizada.getFuncionarios().size(); i++) {
			    listaConstrucoes.get(selecao).adicionarFuncionario(atualizada.getFuncionarios().get(i));
			}
		}
	}
	
	public static void removerTodosOsFuncionariosDoCadastro(int selecao) {
		int alerta = 0;
		
		Construcao atualizada = listaConstrucoes.get(selecao);
		ArrayList<Funcionario> operarios = atualizada.getFuncionarios();
		
		do {
			System.out.println("Tem certeza que deseja remover todos os funcionários desta construção?");
			System.out.println("ATENÇÃO: Esta ação não poderá ser revertida!");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			alerta = scan.nextInt();
			scan.nextLine();
			
			if(alerta != 1 && alerta != 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(alerta != 1 && alerta != 2);
		
		if(alerta == 1) {
			for(int i = operarios.size()-1; i >= 0;i--) {
				operarios.remove(i);
			}
			System.out.println("Todos os funcionários foram removidas com sucesso!");
		}else {
			return;
		}
		
	}
	
	public static int confirmarAtualizaçãoConstrucao() {
		int aviso =0;
		do {
			System.out.println("Confirma atualização da construção?");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			aviso = scan.nextInt();
			scan.nextLine();
			
			if(aviso != 1 && aviso != 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(aviso != 1 && aviso != 2);
		return aviso;
	}
	
	//AtualizarFim---------------------------------------------------------------------------------------------//
	
	//RemoverInicio--------------------------------------------------------------------------------------------//
	
	public static void mostrarRemover() {
		int escolha = 0;
		do {
			System.out.println("O que deseja remover?");
			System.out.println("1 - Funcionário");
			System.out.println("2 - Construção");
			System.out.println("0 - Cancelar");
			escolha = scan.nextInt();
			scan.nextLine();
			
			if(escolha < 0 || escolha > 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(escolha < 0 || escolha > 2);
		
		if(escolha == 1) {
			removerFuncionario();
		}else if(escolha == 2){
			removerConstrucao();
		}else {
			return;
		}
	}
	
	public static void removerFuncionario() {
		int aviso = 0;
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
		
		do {
			System.out.println("Tem certeza que deseja remover este funcionário?");
			System.out.println("ATENÇÃO: Esta ação não poderá ser revertida!");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			aviso = scan.nextInt();
			scan.nextLine();
			
			if(aviso != 1 && aviso != 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(aviso != 1 && aviso != 2);
		
		if(aviso == 1) {
			Funcionario removido = listaFuncionarios.remove(escolha);
			
			for (int i = 0; i < listaConstrucoes.size(); i++) {
		        Construcao c = listaConstrucoes.get(i);
		        c.getFuncionarios().remove(removido);
		    }
			System.out.printf("Funcionário %s removido com sucesso!\n",removido.getNome());
		}else {
			return;
		}
	}
	
	public static void removerConstrucao() {
		int aviso = 0;
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
		
		do {
			System.out.println("Tem certeza que deseja remover esta construção?");
			System.out.println("ATENÇÃO: Esta ação não poderá ser revertida!");
			System.out.println("1 - SIM");
			System.out.println("2 - NÃO");
			aviso = scan.nextInt();
			scan.nextLine();
			
			if(aviso != 1 && aviso != 2) {
				System.out.println("Escolha uma opção válida!");
			}
		}while(aviso != 1 && aviso != 2);
		
		if(aviso == 1) {
			Construcao removida = listaConstrucoes.remove(escolha);
			
			for (int i = 0; i < listaFuncionarios.size(); i++) {
		        Funcionario f = listaFuncionarios.get(i);
		        f.getConstrucoes().remove(removida);
			}
			System.out.printf("Construção %s removida com sucesso!\n",removida.getNome());
		}else {
			return;
		}
	}
	
	//RemoverFim-----------------------------------------------------------------------------------------------//

}