package main.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;



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
			sql_ins.setDouble(5, rubrica.getPreviousCredit());
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

	public int updateRow(Row rubrica){
		int res = 0;

		try{
			this.openConnection();

			String sql_str = "UPDATE public.rubrica SET (description = ?, \"previousBalance\" = ?, \"previousDebt\" = ?, \"previousCredit\" = ?, \"isPercent\" = ?, \"valueChange\" = ?, \"currentBalance\" = ?) "
			+ "WHERE (code = ? AND year = ? AND month = ?);";
			

			PreparedStatement sql_ins = connection.prepareStatement(sql_str);

			sql_ins.setString(1, rubrica.getDescription());
			sql_ins.setDouble(2, rubrica.getPreviousBalance());
			sql_ins.setDouble(3, rubrica.getPreviousDebt());
			sql_ins.setDouble(4, rubrica.getPreviousCredit());
			sql_ins.setBoolean(5, rubrica.getIsPercent());
			sql_ins.setDouble(6, rubrica.getValueChange());
			sql_ins.setDouble(7, rubrica.getCurrentBalance());
			sql_ins.setInt(8, rubrica.getCode());
			sql_ins.setInt(9, rubrica.getYear());
			sql_ins.setInt(10, rubrica.getMonth());

			res = sql_ins.executeUpdate();

			connection.commit();

			this.closeConnection();
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;
	}

	public int deleteRow(Row rubrica){
		int res = 0;

		try{
			this.openConnection();

			String sql_str = "DELETE FROM public.rubrica "
			+ "WHERE code = ? AND year = ? AND month = ?);";
			

			PreparedStatement sql_ins = connection.prepareStatement(sql_str);

			sql_ins.setInt(1, rubrica.getCode());
			sql_ins.setInt(2, rubrica.getYear());
			sql_ins.setInt(3, rubrica.getMonth());

			res = sql_ins.executeUpdate();

			connection.commit();

			this.closeConnection();
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;
	}

	private ResultSet selectAllRS(){
		ResultSet res = null;

		try{
			this.openConnection();

			String sql_str = "SELECT code, description, \"currentBalance\", \"previousBalance\", \"previousDebt\", \"previousCredit\", \"isPercent\", \"valueChange\", year, month FROM rubrica "
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

	private ResultSet selectDateRS(int year, int month){
		ResultSet res = null;

		try{
			this.openConnection();

			String sql_str = "SELECT code, description, \"currentBalance\", \"previousBalance\", \"previousDebt\", \"previousCredit\", \"isPercent\", \"valueChange\", year, month FROM rubrica "
			+ "WHERE (year = ? AND month = ?)"
			+ "ORDER BY year, month;";

			PreparedStatement sql_qry = connection.prepareStatement(sql_str);

			sql_qry.setInt(1, year);
			sql_qry.setInt(2, month);

			res = sql_qry.executeQuery();

			this.closeConnection();
			
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;

	}

	private ResultSet selectRangeRS(DataRange range){

		ResultSet res = null;

		try{
			this.openConnection();

			String sql_str = "SELECT code, description, \"currentBalance\", \"previousBalance\", \"previousDebt\", \"previousCredit\", \"isPercent\", \"valueChange\", year, month FROM rubrica "
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

	public ArrayList<Row> selectDate(int year, int month){
		ResultSet rs = null;
		ArrayList<Row> res = new ArrayList<Row>();
		try{
			rs = this.selectDateRS(year, month);

			/*
			SQL -

			1 - code
			2 - description
			3 - currentBalance
			4 - previousBalance
			5 - previousDebt
			6 - previousCredit
			7 - isPercent
			8 - valueChange
			9 - year
			10- month

			*/

			/*

			Constructor -

			2 - String description
			1 - int code
			4 - float previousBalance
			5 - float previousDebt
			6 - float previousCredit
			3 - float currentBalance
			7 - boolean isPercent
			8 - float valueChange
			9 - int year
			10- int month
			X - boolean updated
			X - boolean loaded

			*/

			while (rs.next()){
				res.add(
					new Row(
						rs.getString(2),
						rs.getInt(1),
						(float)rs.getDouble(4),
						(float)rs.getDouble(5),
						(float)rs.getDouble(6),
						(float)rs.getDouble(3),
						rs.getBoolean(7),
						(float)rs.getDouble(8),
						rs.getInt(9),
						rs.getInt(10),
						false,
						true
					)
				);
			}
		}catch(SQLException e){
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ":  " + e.getMessage());
		}

		return res;
	}

    public ArrayList<Row> selectAll(){
        ResultSet rs = null;
        ArrayList<Row> res = new ArrayList<Row>();
        try{
            rs = this.selectAllRS();


	        /*

			SQL -

			1 - code
			2 - description
			3 - currentBalance
			4 - previousBalance
			5 - previousDebt
			6 - previousCredit
			7 - isPercent
			8 - valueChange
			9 - year
			10- month

			*/

			/*

			Constructor -

			2 - String description
			1 - int code
			4 - float previousBalance
			5 - float previousDebt
			6 - float previousCredit
			3 - float currentBalance
			7 - boolean isPercent
			8 - float valueChange
			9 - int year
			10- int month
			X - boolean updated
			X - boolean loaded

			*/

            while (rs.next()){
                res.add(
                        new Row(
                                rs.getString(2),
                                rs.getInt(1),
                                (float)rs.getDouble(4),
                                (float)rs.getDouble(5),
                                (float)rs.getDouble(6),
                                (float)rs.getDouble(3),
                                rs.getBoolean(7),
                                (float)rs.getDouble(8),
                                rs.getInt(9),
                                rs.getInt(10),
                                false,
                                true
                        )
                );
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ":  " + e.getMessage());
        }

        return res;
    }

}