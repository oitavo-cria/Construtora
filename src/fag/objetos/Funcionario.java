package fag.objetos;

import java.util.ArrayList;
//import java.util.List;
//import fag.objetos.Construcao;

public class Funcionario {
	
	private String nome;
	private String cargo;
	private double salario;
	private ArrayList<Construcao> construcoes;

	public Funcionario() {
		construcoes = new ArrayList<>();
	}

	public Funcionario(String nome, String cargo, double salario) {
		setNome(nome);
		setCargo(cargo);
		setSalario(salario);
		construcoes = new ArrayList<>();
	}
	
	public ArrayList<Construcao> getConstrucoes() {
		return construcoes;
	}
	
	public void adicionarConstrucao(Construcao c) {
	    if(!this.construcoes.contains(c)) {
	        this.construcoes.add(c);
	        c.adicionarFuncionario(this);
	    }
	}
	
	public void removerConstrucao(Construcao c) {
	    if (this.construcoes.contains(c)) {
	        this.construcoes.remove(c);
	        c.getFuncionarios().remove(this);
	    }
	}
	
	//GettersInicio-----------------------------------------------------------------//
	
	public String getNome() {
		return nome;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public double getSalario() {
		return salario;
	}
	
	//GettersFim--------------------------------------------------------------------//
	
	//SettersInicio-----------------------------------------------------------------//
	
	public void setNome(String nome) {
		if(nome != null && !nome.isBlank()) {
		this.nome = nome;
		}
	}
	
	public void setCargo(String cargo) {
		if(cargo != null && !cargo.isBlank()) {
		this.cargo = cargo;
		}
	}
	
	public void setSalario(double salario) {
		if(salario>0) {
			this.salario = salario;
		}
	}
	
	//SettersFim--------------------------------------------------------------------//
	
	public void mostrarFuncionario() {
		System.out.printf("%s no cargo de %s. Salário: R$%.2f\n", nome, cargo, salario);
	}
	
	public void mostrarFuncionarioCompleto() {
		System.out.printf("%s no cargo de %s. Salário: R$%.2f", nome, cargo, salario);
		
		if (construcoes.isEmpty()) {
			System.out.printf(" - Não está trabalhando em nenhuma construção no momento.\n");
		} else {
			System.out.printf(" - Construções:\n");
			for (int i=0;i < construcoes.size();i++) {
				System.out.printf("  - %s\n", construcoes.get(i).getNome());
			}
			System.out.printf("\n");
		}
	}
	
	@Override
	public String toString() {
		return "Funcionario [nome=" + nome + ", cargo=" + cargo + ", salario=" + salario + "]";
	}
	
}
