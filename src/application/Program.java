package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Program {

	public static void main(String[] args) {

		Connection conn = null; // VARIAVEL DE CONEXÃO 
		Statement st = null;    // VARIAVEL TIPO STATEMENT
		ResultSet rs = null;    // VARIAVEL TIPO RESULTSET
		
		try {
			conn = DB.getConnection(); // CONECTA AO BANDO DE DADOS
			
			st = conn.createStatement(); // PREPARA UMA CONSULTA SQL
			
			rs = st.executeQuery("select * from department"); // EXECUTA A CONSULTA DO BANCO DE DADOS TRAZENDO O RESULTADO PRO 'RS'
			
			while(rs.next()) { // O NEXT() MOVE-SE PELO BANCO DE DADOS, CASO NÃO TENHA NADA ELE VEM COMO NULO
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name")); // IMPRIME O BANCO, PEGANDO O DADO(TIPO ESPECIFICO) COM O METODO GET E COM O NOME DA COLUNA NO ATRIBUTO
			}
	
		}
		catch (SQLException e) { // CAPTURA O ERRO SQL
			throw new DbException(e.getMessage()); // TRATA O ERRO USANDOO DB QUE CRIAMOS, DESSA FORMA NÃO PRECISA TRATAR, O DB É GERAL
		}
		finally { // COMO O CONNECTION, STATEMENT E RESULTA SÃO RECURSOS EXTERNOS(FORA DA JVM), ELES PRECISAM SER FECHADOS MANUALMENTE, PRA NÃO VAZAR MEMORIA
			
			DB.closeStatement(st);  
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
		
	}

}
