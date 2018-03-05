package br.edu.etec.lojainformatica;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class TelaDeCadastro extends JPanel{
	
	JPanel painelDeBotoes = new JPanel();
	JPanel painelParaCampos = new JPanel(); 
	JPanel painelListagem = new JPanel();
	JButton btnSalvar = new JButton("Salvar");
	JButton btnLimpar = new JButton("Limpar");
	JButton btnCancelar = new JButton("Cancelar");
	JButton btnListar = new JButton("Listar");
	JButton btnAlterar = new JButton("Alterar");
	JTextField txtId = new JTextField("Digite Id Para Alterar");
	JList<String> list;
	
	public TelaDeCadastro(int nLinhas,int nColunas) {
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/layoutlist.html
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
		BorderLayout borderLayout = new BorderLayout();
		this.setLayout(borderLayout);

		//PAINEL PARA CAMPOS DO FORMULARIO
		//https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
		GridLayout layoutParaCampos = new GridLayout(nLinhas,nColunas);		
		painelParaCampos.setLayout(layoutParaCampos);					
		this.add(painelParaCampos, BorderLayout.CENTER);
		
		//PAINEL DE BOTOES
		this.painelDeBotoes = new JPanel();
		this.btnSalvar = new JButton("Salvar");
		this.btnLimpar = new JButton("Limpar");
		this.btnCancelar = new JButton("Cancelar");		
		this.painelDeBotoes.add(btnSalvar);
		this.painelDeBotoes.add(btnLimpar);
		this.painelDeBotoes.add(btnCancelar);
		this.painelDeBotoes.add(btnListar);
		this.painelDeBotoes.add(btnAlterar);
		this.painelDeBotoes.add(txtId);
		
		this.add(painelDeBotoes,BorderLayout.SOUTH);
		
		//PAINEL PARA LISTAGEM
		list = new JList<String>();
		this.painelListagem.add(list);
		this.add(painelListagem, BorderLayout.EAST);
		
	}
	
	abstract void limparFormulario() throws SQLException;
	abstract void salvar() throws SQLException;
	abstract void cancelar() throws SQLException;
	abstract void alterar() throws 	SQLException;
	abstract void excluir() throws 	SQLException;
	abstract void listar() throws 	SQLException;
	
}
