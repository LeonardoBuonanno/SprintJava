package br.com.fiap.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.Exception.NotFoundException;

public class ApoliceDAO {
	private Connection conexao;

	public ApoliceDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public ApoliceDAO(int id, int numero, String seguradora, double valor, LocalDateTime localDateTime,
			LocalDateTime localDateTime2) {

	}

	public void cadastrarApolice(ApoliceDAO apolice) throws ClassNotFoundException, SQLException {
		PreparedStatement stm = this.conexao.prepareStatement(
				"INSERT INTO TB_APOLICE (cd_apolice, nr_numero, nm_seguradora, nr_valor, dt_inicio, dt_fim) values (SQ_TB_APOLICE.NEXTVAL, ?, ?, ?, ?, ?)");

		stm.setInt(1, apolice.getNumero());

		stm.setString(2, apolice.getSeguradora());

		stm.setDouble(3, apolice.getValor());

		stm.setTimestamp(4, Timestamp.valueOf(apolice.getDataInicio()));

		stm.setTimestamp(5, Timestamp.valueOf(apolice.getDataInicio()));

		stm.executeUpdate();
	}

	public String getDataInicio() {
		// TODO Auto-generated method stub
		return getDataInicio();
	}

	public double getValor() {
		// TODO Auto-generated method stub
		return getValor();
	}

	public String getSeguradora() {
		// TODO Auto-generated method stub
		return getSeguradora();
	}

	public int getNumero() {
		// TODO Auto-generated method stub
		return getNumero();
	}

	private ApoliceDAO parse(ResultSet resultado) throws SQLException {
		int id = resultado.getInt("cd_apolice");

		int numero = resultado.getInt("nr_numero");

		String seguradora = resultado.getString("nm_seguradora");

		double valor = resultado.getDouble("nr_valor");

		Timestamp dataInicio = resultado.getTimestamp("dt_inicio");

		Timestamp dataFim = resultado.getTimestamp("dt_fim");

		ApoliceDAO apolice = new ApoliceDAO(id, numero, seguradora, valor, dataInicio.toLocalDateTime(),
				dataFim.toLocalDateTime());

		return apolice;
	}

	public List<ApoliceDAO> listarApolice() throws ClassNotFoundException, SQLException {
		PreparedStatement stm = this.conexao.prepareStatement("SELECT * FROM tb_apolice");
		ResultSet resultado = stm.executeQuery();
		List<ApoliceDAO> lista = new ArrayList<ApoliceDAO>();

		while (resultado.next()) {
			ApoliceDAO apolice = this.parse(resultado);
			lista.add(apolice);
		}

		return lista;
	}

	public ApoliceDAO pesquisaPorId(int id) throws ClassNotFoundException, SQLException, NotFoundException {
		PreparedStatement stm = this.conexao.prepareStatement("select * from tb_apolice where cd_apolice = ?");
		stm.setInt(1, id);
		ResultSet resultado = stm.executeQuery();
		if (!resultado.next()) {
			throw new NotFoundException("Por favor, especifique o ID da apolice");
		} else {
			ApoliceDAO apolice = this.parse(resultado);
			return apolice;
		}
	}

	public List<ApoliceDAO> pesquisaPorNome(String seguradora) throws SQLException {
		PreparedStatement stm = this.conexao.prepareStatement("select * from tb_apolice where nm_seguradora like ?");
		stm.setString(1, "%" + seguradora + "%");
		ResultSet resultado = stm.executeQuery();
		List<ApoliceDAO> lista = new ArrayList<ApoliceDAO>();

		while (resultado.next()) {
			ApoliceDAO apolice = this.parse(resultado);
			lista.add(apolice);
		}

		return lista;
	}

	public void atualizarApolice(ApoliceDAO apolice) throws ClassNotFoundException, SQLException, NotFoundException {
		PreparedStatement stm = this.conexao.prepareStatement(
				"update tb_apolice set nr_numero = ?, nm_seguradora = ?, nr_valor = ?, dt_inicio = ?, dt_fim = ? where cd_apolice = ?");

		stm.setInt(1, apolice.getNumero());

		stm.setString(2, apolice.getSeguradora());

		stm.setDouble(3, apolice.getValor());

		stm.setTimestamp(4, Timestamp.valueOf(apolice.getDataInicio()));

		stm.setTimestamp(5, Timestamp.valueOf(apolice.getDataFim()));

		stm.setInt(6, apolice.getCodigo());

		int linha = stm.executeUpdate();
		if (linha == 0) {
			throw new NotFoundException("Não foi possivel encontrar a apolice para a atualização");
		}
	}

	public String getDataFim() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCodigo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void removerApolice(int id) throws ClassNotFoundException, SQLException, NotFoundException {
		PreparedStatement stm = this.conexao.prepareStatement("delete from tb_apolice where cd_apolice = ?");
		stm.setInt(1, id);
		int linha = stm.executeUpdate();
		if (linha == 0) {
			throw new NotFoundException("Não foi possivel encontrar a apolice para a remoção");
		}
	}
}