package br.com.fiap.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.fiap.DAO.ApoliceDAO;
import br.com.fiap.Exception.InfoException;
import br.com.fiap.Exception.NotFoundException;


public class ApoliceService {

private static final String ConnectionFactory = null;
private ApoliceDAO apoliceDao;
	
	public ApoliceService() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		apoliceDao = new ApoliceDAO(conexao);
	}
	
	public void cadastrar(ApoliceDAO apolice) throws ClassNotFoundException, SQLException, InfoException {
		validar(apolice);
		apoliceDao.cadastrarApolice(apolice);
	}

	private void validar(ApoliceDAO apolice) throws InfoException {

		if (apolice.getNumero() < 0) {
			throw new InfoException("O número deve ser sempre positivo");
		}

		if (apolice.getSeguradora() == null || apolice.getSeguradora().length() > 100) {
			throw new InfoException("O número deve ser diferente de nulo e no máximo 100 caracteres");
		}
		if (apolice.getValor() < 0) {
			throw new InfoException("Por favor, especifique o valor");
		}
		if (apolice.getDataInicio() == null) {
			throw new InfoException("Por favor, informe a data inicial");
		}
		
		if (apolice.getDataFim() == null) {
			throw new InfoException("Por favor, informe a data final");
		}
	}
	
	public void atualizar(ApoliceDAO apolice) throws ClassNotFoundException, SQLException, NotFoundException, InfoException {
		validar(apolice);
		apoliceDao.atualizarApolice(apolice);
	}
	
	public void remover(int codigo) throws ClassNotFoundException, SQLException, NotFoundException {
		apoliceDao.removerApolice(codigo);
	}
	
	public List<ApoliceDAO> listar() throws ClassNotFoundException, SQLException{
		return apoliceDao.listarApolice();
	}
	
	public List<ApoliceDAO> pesquisarPorNome(String nome) throws SQLException{
		return apoliceDao.pesquisaPorNome(nome);
	}
	
	public ApoliceDAO pesquisar(int codigo) throws ClassNotFoundException, SQLException, NotFoundException{
		ApoliceDAO a = apoliceDao.pesquisaPorId(codigo);
		
		return a;
	}
}
