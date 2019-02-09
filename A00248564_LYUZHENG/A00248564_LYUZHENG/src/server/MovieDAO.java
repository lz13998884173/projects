package server;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public enum MovieDAO {
	INSTANCE;
	
	
	
	private MovieDAO() {
	
}
	
		public Connection getConnection() {
			Connection con = null;
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				 con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/oneDB", "SA", "Passw0rd");//, "sa", "Passw0rd"
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return con;
		}
		
		public boolean executeUpdate(String updateStmt) throws SQLException  {
			
			boolean result = false;
			Connection con = getConnection();
			if(con == null)
				throw new SQLException("Database connection error...");
			try {
				System.out.println(updateStmt);
				Statement stmt = con.createStatement();
				stmt.executeUpdate(updateStmt);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return result;
		}
		

	public List<Movie> getMovie() {
		
		String queryStmt = "SELECT * FROM Movie";
		Connection con = getConnection();
		List<Movie> EmptyList = new ArrayList<Movie>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStmt);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String director = rs.getString(3);
				String date = rs.getString(4);
				String price = rs.getString(5);
				String place = rs.getString(6);
				

				
				Movie movie = new Movie();
				movie.setId(id);
				movie.setName(name);
				movie.setDirector(director);
				movie.setDate(date);
				movie.setPrice(price);
				movie.setPlace(place);
				
				EmptyList.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return EmptyList;	
	}

	public Movie getId(int parseInt) {
		String queryStmt = "SELECT * FROM MOVIE WHERE id = " + parseInt;
		Connection con = getConnection();
		Movie movie = null;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStmt);
			if(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String director = rs.getString(3);
				String date = rs.getString(4);
				String price = rs.getString(5);
				String place = rs.getString(6);
				
				
				movie = new Movie();
				movie.setId(id);
				movie.setName(name);
				movie.setDirector(director);
				movie.setDate(date);
				movie.setPrice(price);
				movie.setPlace(place);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return movie;
	}

	public boolean  saveMovie(Movie movie) {//SAVE add
		Connection con = getConnection();
		try {
			PreparedStatement pStmt = con.prepareStatement(
					"INSERT INTO MOVIE VALUES(?,?,?,?,?,?)");
			pStmt.setInt(1, movie.getId());
			pStmt.setString(2, movie.getName());
			pStmt.setString(3, movie.getDirector());
			pStmt.setString(4, movie.getDate());
			pStmt.setString(5, movie.getPrice());
			pStmt.setString(6, movie.getPlace());
			
			
			pStmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} return false;
	}

	public boolean delete(int id)
	{
		String stmt = "DELETE FROM MOVIE WHERE id = " + id + "";
		boolean result = false;
		try {
			result = executeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean Update(Movie s) throws SQLException 
	{
		boolean result=false;
		Connection con=getConnection();
		if(con==null)
			throw new SQLException("Connect to database wrong");
		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"UPDATE MOVIE set name=?,director=?,date=?,price=?,place=? where id=?");
			prepStmt.setInt(6, s.getId());
			prepStmt.setString(1, s.getName());
			prepStmt.setString(2, s.getDirector());
			prepStmt.setString(3, s.getDate());
			prepStmt.setString(4, s.getPrice());
			prepStmt.setString(5, s.getPlace());
			
			prepStmt.executeUpdate();
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
