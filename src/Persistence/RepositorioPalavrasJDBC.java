package Persistence;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Palavra;
import Exceptions.ConexaoInexistenteException;
import Exceptions.ErroInternoException;

public class RepositorioPalavrasJDBC implements RepositorioPalavras {
	
	private Connection connection;

	@Override
	public void adicionar(Palavra palavra) throws ErroInternoException, 
					ConexaoInexistenteException {
		PreparedStatement ps = null;
		String sql = "INSERT INTO PALAVRAS (CONTADOR, PALAVRA1, PALAVRA2, LINGUAGEM1, LINGUAGEM2) VALUES (?,?,?,?,?)";

		try {
			
			this.connection = ConnectionFactory.getConnection();

			ps = connection.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setString(2, palavra.getPalavra1());
			ps.setString(3, palavra.getPalavra2());
			ps.setString(4, palavra.getLinguagem1());
			ps.setString(5, palavra.getLinguagem2());
			
			ps.executeUpdate();

			ps.close();
			connection.close();
			

		} catch (ConexaoInexistenteException e) {
			throw new ConexaoInexistenteException(e);
		} catch (SQLException e) {
			throw new ErroInternoException(e);
		}
	}

	@Override
	public void remover(Palavra palavra) throws ErroInternoException,
			ConexaoInexistenteException {
		PreparedStatement ps = null;
		String sql = "DELETE FROM PALAVRAS WHERE ID=?";

		try {
			this.connection = ConnectionFactory.getConnection();

			ps = connection.prepareStatement(sql);
			ps.setLong(1, palavra.getCodigo());
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (ConexaoInexistenteException e) {
			throw new ConexaoInexistenteException(e);
		} catch (SQLException e) {
			throw new ErroInternoException(e);
		}
		
	}

	@Override
	public void atualizar(Palavra palavra) throws ErroInternoException,
			ConexaoInexistenteException {
		
		PreparedStatement ps = null;
		String sql = "UPDATE PALAVRAS SET CONTADOR=?, PALAVRA1=?, "
				+ "PALAVRA2=?, LINGUAGEM1=?, LINGUAGEM2=?  WHERE ID=?";

		try {
			this.connection = ConnectionFactory.getConnection();

			ps = connection.prepareStatement(sql);
			ps.setInt(1, palavra.getContador()+1);
			ps.setString(2, palavra.getPalavra1());
			ps.setString(3, palavra.getPalavra2());
			ps.setString(4, palavra.getLinguagem1());
			ps.setString(5, palavra.getLinguagem2());
			ps.setLong(6, palavra.getCodigo());
			ps.executeUpdate();
			ps.close();
			connection.close();

		} catch (ConexaoInexistenteException e) {
			throw new ConexaoInexistenteException(e);
		} catch (SQLException e) {
			throw new ErroInternoException(e);
		}
		
	}

	@Override
	public Palavra buscar(String palavra1) throws ErroInternoException,
			ConexaoInexistenteException {
		
		Palavra temp = new Palavra();
		PreparedStatement ps = null;
		ResultSet rs;
		String sql = "SELECT * FROM PALAVRAS WHERE PALAVRA1 = ?";

		try {
			this.connection = ConnectionFactory.getConnection();

			ps = connection.prepareStatement(sql);
			ps.setString(1, palavra1);
			rs = ps.executeQuery();

			if (rs.next()) {
				temp.setCodigo(rs.getLong("ID"));
				temp.setContador(rs.getInt("CONTADOR"));
				temp.setPalavra1(rs.getString("PALAVRA1"));
				temp.setPalavra2(rs.getString("PALAVRA2"));
				temp.setLinguagem1(rs.getString("LINGUAGEM1"));
				temp.setLinguagem2(rs.getString("LINGUAGEM2"));
			}

			ps.close();
			connection.close();
			
			
			return temp;
			
		}catch (ConexaoInexistenteException e) {
			throw new ConexaoInexistenteException(e);
		}catch (SQLException e) {
			throw new ErroInternoException(e);
		}
	}
	
	public List<Palavra> listaPalavras() throws ErroInternoException,
				ConexaoInexistenteException{
		List<Palavra> resultados = new ArrayList<Palavra>();
		PreparedStatement ps = null;
		ResultSet rs;
		String sql = "SELECT * FROM PALAVRAS";

		try {
			this.connection = ConnectionFactory.getConnection();

			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();

			Palavra temp = null;
			while (rs.next()) {
				temp = new Palavra();
				temp.setCodigo(rs.getLong("ID"));
				temp.setContador(rs.getInt("CONTADOR"));
				temp.setPalavra1(rs.getString("PALAVRA1"));
				temp.setPalavra2(rs.getString("PALAVRA2"));
				temp.setLinguagem1(rs.getString("LINGUAGEM1"));
				temp.setLinguagem2(rs.getString("LINGUAGEM2"));

				resultados.add(temp);
			}

			ps.close();
			connection.close();

			return resultados;
		} catch (ConexaoInexistenteException e) {
			throw new ConexaoInexistenteException(e);
		} catch (SQLException e) {
			throw new ErroInternoException(e);
		}
	}

}
