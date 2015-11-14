package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;

import Exceptions.ConexaoInexistenteException;


public class ConnectionFactory {
	private static Connection connection;
	
	public static Connection getConnection() throws ConexaoInexistenteException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tradutor", "root", "123");
			
			System.out.println("Conexão Bem-Sucedida");
		} catch (Exception e) {
			throw new ConexaoInexistenteException(e);
		}
		
		return connection;
	}
}


