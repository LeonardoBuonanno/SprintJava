package br.com.fiap.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import br.com.fiap.DAO.CargaDAO;
import br.com.fiap.Exception.InfoException;
import br.com.fiap.Exception.NotFoundException;

public class CargaService {

private static final String ConnectionFactory = null;
private CargaDAO cargaDao;
	
	public CargaService() throws ClassNotFoundException, SQLException {
		Connection conexao = ConnectionFactory.getConnection();
		cargaDao = new CargaDAO(conexao);
	}
	
	public void cadastrar(CargaDAO carga) throws ClassNotFoundException, SQLException, InfoException {
		validar(carga);
		cargaDao.cadastrarCarga(carga);
	}

	private void validar(CargaDAO carga) throws InfoException {
		
		if (carga.getDescricao() == null || carga.getDescricao().length() > 200) {
			throw new InfoException("Deve ser diferente de nulo e no máximo 200 caracteres");
		}
		if (carga.getPeso() < 0) {
			throw new InfoException("A carga deve ser diferente de nulo");
		}
		
	}
	
	public void atualizar(CargaDAO carga) throws ClassNotFoundException, SQLException, NotFoundException, InfoException {
		validar(carga);
		cargaDao.atualizarCarga(carga);
	}
	
	public void remover(int codigo) throws ClassNotFoundException, SQLException, NotFoundException {
		cargaDao.removerCarga(codigo);
	}
	
	public List<CargaDAO> listar() throws ClassNotFoundException, SQLException{
		return cargaDao.listarCarga();
	}
	
	public List<CargaDAO> pesquisarPorNome(String nome) throws SQLException{
		return cargaDao.pesquisaPorNome(nome);
	}
	
	public CargaDAO pesquisar(int codigo) throws ClassNotFoundException, SQLException, NotFoundException{
		CargaDAO c = cargaDao.pesquisaPorId(codigo);
		return c;
	}
}
