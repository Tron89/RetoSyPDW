package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dao { // Una unica clase dao por que es mas simple c:

	// listaCurso(): devuelve la lista de objetos de tipo Curso
	//  listaAlumnos(idcurso): devuelve la lista de objetos de tipo AlumnoCurso, con los alumnos
	// de ese curso, ordenados por apellidos
	//  alumnoasignatura(idalumno): devuelve la lista de objetos de tipo AlumnoAsignatura
	//  actualizaNotas(List< AlumnoAsignatura > lista): recibe un listado de objetos de tipo
	// AlumnoAsignatura y actualiza las notas de ese alumno en la base de datos. También
	// actualiza la nota media del alumno.
	
	
	public List<Curso> listaCurso() {
        Connection con = Conexion.getConexion();
        List<Curso> cursos = new ArrayList<Curso>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT idcurso, curso FROM curso";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                cursos.add(new Curso(rs.getInt("idcurso"), rs.getString("curso")));
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return cursos;
	}
	
	
	public List<AlumnoCurso> listaAlumnos(int idcurso) { // Pfffffffffff, hacer esta relacion, buscate la vida bobo
        Connection con = Conexion.getConexion();
        List<AlumnoCurso> alumnocursos = new ArrayList<AlumnoCurso>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT idalumnocurso,idcurso,idalumno,fechamatricula,notamedia FROM alumnocurso WHERE idcurso = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, idcurso);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	
            	
            	alumnocursos.add(new AlumnoCurso(rs.getInt("idalumnocurso"), rs.getInt("idcurso"), null, rs.getString("fechamatricula"), rs.getInt("notamedia")))
                cursos.add(new Curso(rs.getInt("idcurso"), rs.getString("curso")));
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return cursos;
	}
	
	public String testBD() { // EJEMPLO
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
