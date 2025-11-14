package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import database.Conexion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class Saludar {

	@WebMethod
	public String diHola(@WebParam (name="nombre") String nombre)
	{
		return "Hola " + nombre;
	}
	
	@WebMethod
	public String testBD() {
        // Open connection
        Connection con = Conexion.getConexion();
        String str = "";
        
        try {
            // Create a statement
            Statement stmt = con.createStatement();
            
            // Execute a query (example: get all students)
            String sql = "SELECT * FROM alumno"; 
            ResultSet rs = stmt.executeQuery(sql);
            
            // Iterate over results
            while (rs.next()) {
                int id = rs.getInt("idalumno");
                String nombre = rs.getString("nombre");
                String sexo = rs.getString("sexo");
                String apellidos = rs.getString("apellidos");
                str += id + " | " + nombre + " | " + sexo + " | " + apellidos + "\n";
            }
            
            // Close ResultSet and Statement
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close connection
            Conexion.cierraConexion();
        }
        return str;
	}
	
}