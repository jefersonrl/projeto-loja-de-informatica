package br.edu.etec.lojainformatica.persistence;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.etec.lojainformatica.model.Cliente;
import br.edu.etec.lojainformatica.model.Hardware;


public class HardwareJdbcDAO {

	private Connection conn;
	
	
	public HardwareJdbcDAO(Connection conn) {
		this.conn = conn;
	}
	
	
	public void salvar(Hardware h) throws SQLException {
		String sql = "insert into tb_hardware values ('"+h.getDescricao()+"','"+h.getPreco_unit()+"','"+h.getQtde_atual()+"','"+h.getQtde_minima()+"')";
		System.out.println(sql);
		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
		prepareStatement.executeUpdate();
                prepareStatement.close();
	}
	
	public void alterar(Hardware hExample) {
		String sql = "update tb_hardware set descricao='"+hExample.getDescricao()+"',preco_unit='"+hExample.getPreco_unit()+"',qtde_atual='"+hExample.getQtde_atual()+"',qtde_minima='"+hExample.getQtde_minima()+"' where id_hardware='"+hExample.getId_hardware()+"';";
		System.out.println(sql);
		PreparedStatement prepareStatement;
		try {
			prepareStatement = this.conn.prepareStatement(sql);
			prepareStatement.executeUpdate();
            prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void excluir(int id) {
		String sql = "delete from tb_hardware where id_hardware='"+id+"';";
		System.out.println(sql);
        try {
    		PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
    		prepareStatement.executeUpdate();
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}                		
	}

	public List<Hardware> listar() {
		String sql = "select * from tb_hardware";
        System.out.println(sql);		
        List<Hardware> hardwares = new ArrayList<Hardware>();
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("id_hardware");
				String descricao = rs.getString("descricao");
				int preco_unit = rs.getInt("preco_unit");
				int qtde_atual = rs.getInt("qtde_atual");
				int qtde_minima= rs.getInt("qtde_minima");
				Hardware hardware = new Hardware();
				hardware.setId_hardware(id);;
				hardware.setDescricao(descricao);
				hardware.setPreco_unit(preco_unit);
				hardware.setQtde_atual(qtde_atual);
				hardware.setQtde_minima(qtde_minima);
				hardwares.add(hardware);
			}
			prepareStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hardwares;
	}

	public Hardware findById(Integer id) {
		String sql = "select * from tb_hardware where id_hardware = "+id;
        System.out.println(sql);		                
        Hardware hard = null;
		try {
			PreparedStatement prepareStatement = this.conn.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			
			while(rs.next()) {
				int id_hardware = rs.getInt("id_hardware");
				String descricao = rs.getString("descricao");
				int preco_unit = rs.getInt("preco_unit");
				int qtde_atual = rs.getInt("qtde_atual");
				int qtde_minima= rs.getInt("qtde_minima");
				hard = new Hardware();
				hard.setId_hardware(id_hardware);;
				hard.setDescricao(descricao);
				hard.setPreco_unit(preco_unit);
				hard.setQtde_atual(qtde_atual);
				hard.setQtde_minima(qtde_minima);				
			}
			prepareStatement.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hard;
	}
}
