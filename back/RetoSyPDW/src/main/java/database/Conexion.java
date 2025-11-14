package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	
	private static Connection con;
	
	public static void abreConexion()
	{
		try
		{
			String url1 = "jdbc:mysql://localhost:3306/instituto";
			String user = "izquierdo";
			String password = "izquierdo";
		
			con = DriverManager.getConnection(url1, user, password);
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConexion()
	{
		if(con==null)
		{
			abreConexion();
			return con;
		}
		return con;
	}
	
	public static void cierraConexion()
	{
		try {
			if(con!=null) {
        		con.close();
        		con=null;
			}
        }
        catch (Exception ex) {
			ex.printStackTrace();
       }

	}
}
