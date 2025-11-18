package fag.objetos;

import java.util.ArrayList;
//import java.util.List;
//import fag.objetos.Funcionario;

public class Construcao {
	
	private String nome;
	private String endereco;
	private String inicio;
	private ArrayList<Funcionario> funcionarios;
	
	public Construcao() {
		funcionarios = new ArrayList<>();
	}
	
	public Construcao(String nome, String endereco, String inicio) {
		setNome(nome);
		setEndereco(endereco);
		setInicio(inicio);
		funcionarios = new ArrayList<>();
	}
	
	public ArrayList<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void adicionarFuncionario(Funcionario f) {
	    if(!this.funcionarios.contains(f)) {
	        this.funcionarios.add(f);
	        f.adicionarConstrucao(this);
	    }
	}

	public void removerFuncionario(Funcionario f) {
	    if (this.funcionarios.contains(f)) {
	        this.funcionarios.remove(f);
	        f.getConstrucoes().remove(this);
	    }
	}
	
	//GettersInicio-----------------------------------------------------------------//
	
	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getInicio() {
		return inicio;
	}
	
	//GettersFim--------------------------------------------------------------------//
	
	//SettersInicio-----------------------------------------------------------------//
	
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) {
			this.nome = nome;
			}
	}
	
	public void setEndereco(String endereco) {
		if(endereco != null && !endereco.isBlank()) {
			this.endereco = endereco;
			}
	}
	
	public void setInicio(String inicio) {
		if(inicio != null && !inicio.isBlank()) {
			this.inicio = inicio;
			}
	}
	
	//SettersFim--------------------------------------------------------------------//
	
	public void mostrarConstrucao() {
		System.out.printf("%s localizada em %s, iniciou em %s\n", nome, endereco, inicio);
	}
	
	public void mostrarConstrucaoCompleto() {
		System.out.printf("%s localizada em %s, iniciou em %s", nome, endereco, inicio);
		
		if (funcionarios.isEmpty()) {
			System.out.printf(" - Nenhum funcionário associado.\n");
		} else {
			System.out.printf(" - Funcionários:\n");
			for (int i=0;i < funcionarios.size();i++) {
				System.out.printf("  - %s, %s\n", funcionarios.get(i).getNome(),funcionarios.get(i).getCargo());
			}
			System.out.printf("\n");
		}
	}
	
	@Override
	public String toString() {
		return "Construcao [nome=" + nome + ", endereco=" + endereco + ", inicio=" + inicio + "]";
	}
	
}
