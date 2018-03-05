package br.edu.etec.lojainformatica.model;

public class Hardware {
	private int id_hardware;
	private String descricao;
	private double preco_unit;
	private int qtde_atual;
	private int qtde_minima;
	//private Image img;
	
	public int getId_hardware() {
		return id_hardware;
	}
	public void setId_hardware(int id_hardware) {
		this.id_hardware = id_hardware;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPreco_unit() {
		return preco_unit;
	}
	public void setPreco_unit(double preco_unit) {
		this.preco_unit = preco_unit;
	}
	public int getQtde_atual() {
		return qtde_atual;
	}
	public void setQtde_atual(int qtde_atual) {
		this.qtde_atual = qtde_atual;
	}
	public int getQtde_minima() {
		return qtde_minima;
	}
	public void setQtde_minima(int qtde_minima) {
		this.qtde_minima = qtde_minima;
	}
	
}
