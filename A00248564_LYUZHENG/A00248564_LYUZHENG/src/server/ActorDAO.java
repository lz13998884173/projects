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


public enum ActorDAO {
	INSTANCE;
	
	
	
	private ActorDAO() {
	
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
		

	public List<Actor> getActor() {
		
		String queryStmt = "SELECT * FROM Actor";
		Connection con = getConnection();
		List<Actor> EmptyList = new ArrayList<Actor>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStmt);
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String fname = rs.getString(2);
				String sname = rs.getString(3);
				String birthday = rs.getString(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String address = rs.getString(7);

				
				Actor actor = new Actor();
				actor.setId(id);
				actor.setFname(fname);
				actor.setSname(sname);
				actor.setBirthday(birthday);
				actor.setPhone(phone);
				actor.setEmail(email);
				actor.setAddress(address);
				EmptyList.add(actor);
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

	public Actor getId(int parseInt) {
		String queryStmt = "SELECT * FROM Actor WHERE id = " + parseInt;
		Connection con = getConnection();
		Actor actor = null;//Actor
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(queryStmt);
			if(rs.next()) {
				int id = rs.getInt(1);
				String fname = rs.getString(2);
				String sname = rs.getString(3);
				String birthday = rs.getString(4);
				String phone = rs.getString(5);
				String email = rs.getString(6);
				String address = rs.getString(7);
				
				actor = new Actor();
				actor.setId(id);
				actor.setFname(fname);
				actor.setSname(sname);
				actor.setBirthday(birthday);
				actor.setPhone(phone);
				actor.setEmail(email);
				actor.setAddress(address);
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
		return actor;
	}

	public boolean  saveActor(Actor Actor) {//SAVE add
		Connection con = getConnection();
		try {
			PreparedStatement pStmt = con.prepareStatement(
					"INSERT INTO Actor VALUES(?,?,?,?,?,?,?)");
			pStmt.setInt(1, Actor.getId());
			pStmt.setString(2, Actor.getFname());
			pStmt.setString(3, Actor.getSname());
			pStmt.setString(4, Actor.getBirthday());
			pStmt.setString(5, Actor.getPhone());
			pStmt.setString(6, Actor.getEmail());
			pStmt.setString(7, Actor.getAddress());
			
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
		String stmt = "DELETE FROM Actor WHERE id = " + id + "";
		boolean result = false;
		try {
			result = executeUpdate(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public boolean Update(Actor s) throws SQLException 
	{
		boolean result=false;
		Connection con=getConnection();
		if(con==null)
			throw new SQLException("Connect to database wrong");
		try {
			PreparedStatement prepStmt = con.prepareStatement(
					"UPDATE Actor set fname=?,sname=?,birthday=?,phone=?,email=?,address=? where id=?");
			prepStmt.setInt(7, s.getId());
			prepStmt.setString(1, s.getFname());
			prepStmt.setString(2, s.getSname());
			prepStmt.setString(3, s.getBirthday());
			prepStmt.setString(4, s.getPhone());
			prepStmt.setString(5, s.getEmail());
			prepStmt.setString(6, s.getAddress());
			prepStmt.executeUpdate();
			result=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
