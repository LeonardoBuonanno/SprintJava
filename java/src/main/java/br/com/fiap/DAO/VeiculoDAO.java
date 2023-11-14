package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.Exception.NotFoundException;

public class VeiculoDAO {

	private Connection conexao;

	public VeiculoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public VeiculoDAO(int id, String marca, String modelo, int ano) {
		// TODO Auto-generated constructor stub
	}

	public void cadastrarVeiculo(VeiculoDAO veiculo) throws ClassNotFoundException, SQLException {
		PreparedStatement stm = this.conexao.prepareStatement(
				"INSERT INTO TB_VEICULO (cd_veiculo, nm_marca, nm_modelo, nr_ano) values (?, ?, ?, ?)");
		stm.setInt(1, veiculo.getId());

		stm.setString(2, veiculo.getMarca());

		stm.setString(3, veiculo.getModelo());

		stm.setInt(4, veiculo.getAno());

		stm.executeUpdate();
	}

	private int getAno() {
		// TODO Auto-generated method stub
		return getAno();
	}

	public String getModelo() {
		// TODO Auto-generated method stub
		return getModelo();
	}

	public String getMarca() {
		// TODO Auto-generated method stub
		return getMarca();
	}

	private int getId() {
		// TODO Auto-generated method stub
		return getId();
	}

	private VeiculoDAO parse(ResultSet resultado) throws SQLException {

		int id = resultado.getInt("cd_veiculo");

		String marca = resultado.getString("nm_marca");

		String modelo = resultado.getString("nm_modelo");

		int ano = resultado.getInt("nr_ano");

		VeiculoDAO veiculo = new VeiculoDAO(id, marca, modelo, ano);
		return veiculo;
	}

	public List<VeiculoDAO> listarVeiculo() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_veiculo");

		ResultSet resultado = stm.executeQuery();

		List<VeiculoDAO> lista = new ArrayList<VeiculoDAO>();

		while (resultado.next()) {
			VeiculoDAO veiculo = parse(resultado);

			lista.add(veiculo);
		}

		return lista;
	}

	public VeiculoDAO pesquisaPorId(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conexao.prepareStatement("select * from" + " tb_veiculo where cd_veiculo = ?");

		stm.setInt(1, id);

		ResultSet resultado = stm.executeQuery();

		if (!resultado.next()) {

			throw new NotFoundException("Veiculo não encontrado");
		}
		VeiculoDAO veiculo = parse(resultado);

		return veiculo;
	}

	public List<VeiculoDAO> pesquisaPorNome(String modelo) throws SQLException {

		PreparedStatement stm = conexao.prepareStatement("select * from tb_veiculo where nm_modelo like ?");

		stm.setString(1, "%" + modelo + "%");

		ResultSet resultado = stm.executeQuery();

		List<VeiculoDAO> lista = new ArrayList<VeiculoDAO>();

		while (resultado.next()) {
			VeiculoDAO veiculo = parse(resultado);
			lista.add(veiculo);
		}

		return lista;
	}

	public void atualizarVeiculo(VeiculoDAO veiculo) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conexao.prepareStatement(
				"update tb_veiculo set " + "nm_modelo = ?, nm_marca = ?, nr_ano = ? where cd_veiculo = ?");

		stm.setString(1, veiculo.getModelo());

		stm.setString(2, veiculo.getMarca());

		stm.setInt(3, veiculo.getAno());

		stm.setInt(4, veiculo.getId());

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Não foi possivel encotrar o veículo para a atualização");
	}

	public void removerVeiculo(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conexao.prepareStatement("delete from tb_veiculo where cd_veiculo = ?");

		stm.setInt(1, id);

		int linha = stm.executeUpdate();

		if (linha == 0)
			throw new NotFoundException("Não foi possivel encontrar o veículo para ser removido");
	}
}
