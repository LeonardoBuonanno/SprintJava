package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.Exception.NotFoundException;

public class CargaDAO {

	private Connection conexao;

	public CargaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public CargaDAO(int id, String descricao, double peso) {
		// TODO Auto-generated constructor stub
	}

	public void cadastrarCarga(CargaDAO carga) throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conexao
				.prepareStatement("INSERT INTO" + " TB_CARGA (cd_carga, ds_carga, nr_peso) " + "values (?, ?, ?)");

		stm.setInt(1, carga.getId());

		stm.setString(2, carga.getDescricao());

		stm.setDouble(3, carga.getPeso());

		stm.executeUpdate();
	}

	public double getPeso() {
		// TODO Auto-generated method stub
		return getPeso();
	}

	public String getDescricao() {
		// TODO Auto-generated method stub
		return getDescricao();
	}

	private int getId() {
		// TODO Auto-generated method stub
		return getId();
	}

	private CargaDAO parse(ResultSet resultado) throws SQLException {

		int id = resultado.getInt("cd_carga");

		String descricao = resultado.getString("ds_carga");

		double peso = resultado.getDouble("nr_peso");

		CargaDAO carga = new CargaDAO(id, descricao, peso);
		return carga;
	}

	public List<CargaDAO> listarCarga() throws ClassNotFoundException, SQLException {

		PreparedStatement stm = conexao.prepareStatement("SELECT * FROM tb_carga");

		ResultSet resultado = stm.executeQuery();

		List<CargaDAO> lista = new ArrayList<CargaDAO>();

		while (resultado.next()) {
			CargaDAO carga = parse(resultado);

			lista.add(carga);
		}
		return lista;
	}

	public CargaDAO pesquisaPorId(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conexao.prepareStatement("select * from tb_carga where cd_carga = ?");

		stm.setInt(1, id);

		ResultSet resultado = stm.executeQuery();

		if (!resultado.next()) {

			throw new NotFoundException("Por favor especifique o ID da carga!");
		}
		CargaDAO carga = parse(resultado);

		return carga;
	}

	public List<CargaDAO> pesquisaPorNome(String descricao) throws SQLException {

		PreparedStatement stm = conexao.prepareStatement("select * from tb_carga where ds_carga like ?");

		stm.setString(1, "%" + descricao + "%");

		ResultSet resultado = stm.executeQuery();

		List<CargaDAO> lista = new ArrayList<CargaDAO>();

		while (resultado.next()) {
			CargaDAO carga = parse(resultado);
			lista.add(carga);
		}
		return lista;
	}

	public void atualizarCarga(CargaDAO carga) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conexao
				.prepareStatement("update tb_carga set " + "ds_carga = ?, nr_peso = ? where cd_carga = ?");

		stm.setString(1, carga.getDescricao());

		stm.setDouble(2, carga.getPeso());

		stm.setInt(3, carga.getId());

		int linha = stm.executeUpdate();
		if (linha == 0)
			throw new NotFoundException("Não foi possivel encontrar a carga para a atualização");
	}

	public void removerCarga(int id) throws ClassNotFoundException, SQLException, NotFoundException {

		PreparedStatement stm = conexao.prepareStatement("delete from tb_carga where cd_carga = ?");

		stm.setInt(1, id);

		int linha = stm.executeUpdate();

		if (linha == 0)
			throw new NotFoundException("Não foi possivle encontrar a carga para a remoção");
	}
}
