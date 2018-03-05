package br.edu.etec.lojainformatica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.edu.etec.lojainformatica.model.Cliente;
import br.edu.etec.lojainformatica.model.Vendas;
import br.edu.etec.lojainformatica.persistence.VendasJdbcDAO;
import br.edu.etec.lojainformatica.persistence.ClienteJdbcDAO;
import br.edu.etec.lojainformatica.persistence.JdbcUtil;

public class TelaCadDeVendas extends TelaDeCadastro {

	Vendas vendas = new Vendas();

	JLabel lbIdCliente = new JLabel("ID Cliente");
	JTextField txtIdCliente = new JTextField();

	JLabel lbIdVenda = new JLabel("ID Venda");
	JTextField txtIdVenda = new JTextField();

	JLabel lbData = new JLabel("Data");
	JTextField txtData = new JTextField();

	JLabel lbValorTotal = new JLabel("Valor total");
	JTextField txtValorTotal = new JTextField();

	JLabel lbDesc = new JLabel("Desconto");
	JTextField txtDesc = new JTextField();

	JLabel lbPago = new JLabel("Valor pago");
	JTextField txtPago = new JTextField();

	public TelaCadDeVendas() {
		super(12, 2);// quatro linhas e duas colunas
		// Na hora de add os componentes, considerar a ordem deles
		// conforme usamos abaixo
		this.painelParaCampos.add(lbIdCliente);
		this.painelParaCampos.add(txtIdCliente);

		this.painelParaCampos.add(lbIdVenda);
		this.painelParaCampos.add(txtIdVenda);

		this.painelParaCampos.add(lbData);
		this.painelParaCampos.add(txtData);

		this.painelParaCampos.add(lbValorTotal);
		this.painelParaCampos.add(txtValorTotal);

		this.painelParaCampos.add(lbDesc);
		this.painelParaCampos.add(txtDesc);

		this.painelParaCampos.add(lbPago);
		this.painelParaCampos.add(txtPago);

		// TODO MEXER NAS AÇÕES DO BOTÃO E PÔR OS ITENS NO MENU (POR AGORA)

		System.out.println("terminando de de adicionar os campos, add agora actionlistener...");

		this.btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadDeVendas.this.limparFormulario();
			}
		});

		this.btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadDeVendas.this.salvar();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		this.btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadDeVendas.this.cancelar();
			}
		});

		this.btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadDeVendas.this.alterar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		this.btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					TelaCadDeVendas.this.listar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	void limparFormulario() {
		this.txtIdVenda.setText("");
		this.txtIdCliente.setText("");
		this.txtData.setText("");
		this.txtValorTotal.setText("");
		this.txtDesc.setText("");
		this.txtPago.setText("");
	}

	@Override
	void salvar() {
		String salvarOuAlterar = "salvar";

		// o botao salvar vai salvar ou alterar. Se tiver id ele alterar se nao ele
		// salva
		String id = this.txtId.getText();
		int idInt = -1;

		try {
			idInt = Integer.parseInt(id);
			salvarOuAlterar = "alterar"; // se deu pra convertar num int entao altera
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			this.vendas.setId_venda(Integer.parseInt(this.txtIdVenda.getText()));
			this.vendas.setId_cliente(Integer.parseInt(this.txtIdCliente.getText()));
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date data = sdf.parse(this.txtData.getText());
			this.vendas.setData(data);
			this.vendas.setVlr_total(Double.parseDouble(this.txtValorTotal.getText()));
			this.vendas.setDesconto(Double.parseDouble(this.txtDesc.getText()));
			this.vendas.setVlr_pago(Double.parseDouble(this.txtPago.getText()));

			Connection connection = JdbcUtil.getConnection();
			VendasJdbcDAO vendaJdbcDAO = new VendasJdbcDAO(connection);

			if (salvarOuAlterar.equals("salvar")) {
				vendaJdbcDAO.salvar(this.vendas);
			} else {
				this.vendas.setId_cliente(idInt);
				vendaJdbcDAO.alterar(this.vendas);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	void cancelar() {
		this.setVisible(false);
	}

	@Override
	void alterar() throws SQLException {
		String id = this.txtId.getText();
		try {
			int idInt = Integer.parseInt(id);
			Connection conn = JdbcUtil.getConnection();
			VendasJdbcDAO vendasJdbcDAO = new VendasJdbcDAO(conn);
			Vendas vda = vendasJdbcDAO.findById(idInt);
			System.out.println("void alterar() throws SQLException {..."+vda);
			if (vda != null) {
				this.txtIdVenda.setText(vda.getId_venda().toString());
				this.txtIdCliente.setText(vda.getId_cliente().toString());
				this.txtData.setText(vda.getData().toString());
				this.txtValorTotal.setText(vda.getVlr_total().toString());
				this.txtDesc.setText(vda.getDesconto().toString());
				this.txtPago.setText(vda.getVlr_pago().toString());
			} else {
				JOptionPane.showMessageDialog(this, "Nao ha vendas com esse id");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	void excluir() throws SQLException {
		String id = this.txtId.getText();
		try {
			int idInt = Integer.parseInt(id);
			Connection conn = JdbcUtil.getConnection();
			VendasJdbcDAO vendaJdbcDAO = new VendasJdbcDAO(conn);
			vendaJdbcDAO.excluir(idInt);
			this.limparFormulario();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	void listar() throws SQLException {
		Connection conn;
		try {
			conn = JdbcUtil.getConnection();
			VendasJdbcDAO vendaJdbcDAO = new VendasJdbcDAO(conn);
			List<Vendas> list = vendaJdbcDAO.listar();
			String[] strArr = new String[list.size()+1];//+1 pro cabecalho
			strArr[0] = "id_venda \t idCli \t VlrTot \t Desc \t VlrPgo ";
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) != null) {
					String idVda = list.get(i).getId_venda().toString();
					String idCli = list.get(i).getId_cliente().toString();
					String vlrTotal = list.get(i).getVlr_total().toString();
					String desconto = list.get(i).getDesconto().toString();
					String vlrPago = list.get(i).getVlr_total().toString();
					strArr[i+1] = idVda + " \t " + idCli + " \t " + vlrTotal + " \t " + desconto + " \t " + vlrPago;
					System.out.println(strArr[i]);
				}
			}
			this.list.setListData(strArr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
