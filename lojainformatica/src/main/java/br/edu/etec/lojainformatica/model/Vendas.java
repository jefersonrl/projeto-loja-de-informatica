package br.edu.etec.lojainformatica.model;

import java.util.Date;

public class Vendas {
	private Integer id_venda;
	private Integer id_cliente; 
	private Date data;
	private Double vlr_total; 
	private Double desconto;
	private Double vlr_pago;

	public Integer getId_venda() {
		return id_venda;
	}
	public void setId_venda(Integer id_venda) {
		this.id_venda = id_venda;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Double getVlr_total() {
		return vlr_total;
	}
	public void setVlr_total(Double vlr_total) {
		this.vlr_total = vlr_total;
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Double getVlr_pago() {
		return vlr_pago;
	}
	public void setVlr_pago(Double vlr_pago) {
		this.vlr_pago = vlr_pago;
	}
	
	
}
