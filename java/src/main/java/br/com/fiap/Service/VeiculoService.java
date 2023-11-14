package br.com.fiap.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.DAO.VeiculoDAO;
import br.com.fiap.Exception.InfoException;
import br.com.fiap.Exception.NotFoundException;

public class VeiculoService {

	private static final String ConnectionFactory = null;
	private VeiculoDAO veiculoDao;
	
	public VeiculoService() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		veiculoDao = new VeiculoDAO(conexao);
	}
	
	public void cadastrar(VeiculoDAO veiculo) throws ClassNotFoundException, SQLException, InfoException {
		validar(veiculo);
		veiculoDao.cadastrarVeiculo(veiculo);
	}

	private void validar(VeiculoDAO veiculo) throws InfoException {

		if (veiculo.getModelo() == null || veiculo.getModelo().length() > 50) {
			throw new InfoException("Deve ser diferente de nulo e no máximo 50 caracteres");
		}

		if (veiculo.getMarca() == null || veiculo.getMarca().length() > 50) {
			throw new InfoException("Deve ser diferente de nulo e no máximo 50 caracteres");
		}
	}
	
	public void atualizar(VeiculoDAO veiculo) throws ClassNotFoundException, SQLException, NotFoundException, InfoException {
		validar(veiculo);
		veiculoDao.atualizarVeiculo(veiculo);
	}
	
	public void remover(int codigo) throws ClassNotFoundException, SQLException, NotFoundException {
		veiculoDao.removerVeiculo(codigo);
	}
	
	public List<VeiculoDAO> listar() throws ClassNotFoundException, SQLException{
		return veiculoDao.listarVeiculo();
	}
	
	public List<VeiculoDAO> pesquisarPorNome(String nome) throws SQLException{
		return veiculoDao.pesquisaPorNome(nome);
	}
	
	public VeiculoDAO pesquisar(int codigo) throws ClassNotFoundException, SQLException, NotFoundException{
		VeiculoDAO v = veiculoDao.pesquisaPorId(codigo);

		return v;
	}
}
