package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class DB_Row{
	private Connection connection;

	public DB_Row(){
		connection = null;	
	}

	private void openConnection() throws SQLException{
		connection = null;
		boolean error = false;
		try{
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/orcamento", "postgres", "postgres");
		}catch (Exception e){
			error = true;
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
			System.exit(0);
		}
		if (!error)
			System.out.println("Opened DB");

		connection.setAutoCommit(false);
	}

	private void closeConnection() throws SQLException{
		connection.close();
	}

	public int insertRow(Row rubrica){
		int res = 0;

		try{
			this.openConnection();

			String sql_str = "INSERT INTO public.rubrica (code, description, \"previousBalance\", \"previousDebt\", \"previousCredit\", \"isPercent\", \"valueChange\", year, month, \"currentBalance\") "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			// int, string, double, double, double, boolean, double, int, int

			PreparedStatement sql_ins = connection.prepareStatement(sql_str);

			sql_ins.setInt(1, rubrica.getCode());
			sql_ins.setString(2, rubrica.getDescription());
			sql_ins.setDouble(3, rubrica.getPreviousBalance());
			sql_ins.setDouble(4, rubrica.getPreviousDebt());
			sql_ins.setDouble(5, rubrica.getPreviousCradit());
			sql_ins.setBoolean(6, rubrica.getIsPercent());
			sql_ins.setDouble(7, rubrica.getValueChange());
			sql_ins.setInt(8, rubrica.getYear());
			sql_ins.setInt(9, rubrica.getMonth());
			sql_ins.setDouble(10, rubrica.getCurrentBalance());

			res = sql_ins.executeUpdate();

			connection.commit();

			this.closeConnection();
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;

	}

	public ResultSet selectAll(){
		ResultSet res = null;

		try{
			this.openConnection();

			String sql_str = "SELECT (code, description, \"currentBalance\", \"previousBalance\", \"previousDebt\", \"previousCredit\", \"isPercent\", \"valueChange\", year, month) FROM rubrica "
			+ "ORDER BY year, month;";

			PreparedStatement sql_qry = connection.prepareStatement(sql_str);

			res = sql_qry.executeQuery();

			this.closeConnection();

		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;

	}

	public ResultSet selectRange(DataRange range){

		ResultSet res = null;

		try{
			this.openConnection();

			String sql_str = "SELECT (code, description, \"currentBalance\", \"previousBalance\", \"previousDebt\", \"previousCredit\", \"isPercent\", \"valueChange\", year, month) FROM rubrica "
			+ "WHERE ((year > ?) OR (year = ? AND month >= ?)) AND ((year < ?) OR (year = ? AND month <= ?))"
			+ "ORDER BY year, month;";

			PreparedStatement sql_qry = connection.prepareStatement(sql_str);

			sql_qry.setInt(1, range.getFirstYear());
			sql_qry.setInt(2, range.getFirstYear());
			sql_qry.setInt(3, range.getFirstMonth());
			sql_qry.setInt(4, range.getLastYear());
			sql_qry.setInt(5, range.getLastYear());
			sql_qry.setInt(6, range.getLastMonth());

			res = sql_qry.executeQuery();

			this.closeConnection();
			
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;

	}
}