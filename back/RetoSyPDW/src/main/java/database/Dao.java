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
	
	
	public static List<Curso> listaCurso() { // Punto 1
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
	
	public static List<Curso> listaCurso(int id) {
        Connection con = Conexion.getConexion();
        List<Curso> cursos = new ArrayList<Curso>();
        
        try {                        
            String sql = "SELECT idcurso, curso FROM curso WHERE idcurso = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                cursos.add(new Curso(rs.getInt("idcurso"), rs.getString("curso")));
            }
            
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return cursos;
	}
	
	public static List<Alumno> listaAlumno() {
        Connection con = Conexion.getConexion();
        List<Alumno> alumnos = new ArrayList<Alumno>();
        
        try {
            Statement stmt = con.createStatement();
            
            String sql = "SELECT idalumno,nombre,fechanac,sexo,apellidos FROM alumno";
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
            	alumnos.add(new Alumno(rs.getInt("idalumno"), rs.getString("nombre"), rs.getString("fechanac"), rs.getString("sexo"), rs.getString("apellidos")));
            }
            
            rs.close();
            stmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return alumnos;
	}
	
	public static List<Alumno> listaAlumno(int id) {
        Connection con = Conexion.getConexion();
        List<Alumno> alumnos = new ArrayList<Alumno>();
        
        try {                        
            String sql = "SELECT idalumno,nombre,fechanac,sexo,apellidos FROM alumno WHERE idalumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	alumnos.add(new Alumno(rs.getInt("idalumno"), rs.getString("nombre"), rs.getString("fechanac"), rs.getString("sexo"), rs.getString("apellidos")));
            }
            
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return alumnos;
	}
	
	public static List<AlumnoCurso> listaAlumnos(int id) { // Punto 2
        Connection con = Conexion.getConexion();
        List<AlumnoCurso> alumnocursos = new ArrayList<AlumnoCurso>();
        
        try {            
            String sql = "SELECT idalumnocurso,idcurso,ac.idalumno,fechamatricula,notamedia "
            		+ " FROM alumnocurso ac "
            		+ " INNER JOIN alumno al ON al.idalumno = ac.idalumno "
            		+ " WHERE idcurso = ? "
            		+ " ORDER BY al.apellidos";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	alumnocursos.add(new AlumnoCurso(rs.getInt("idalumnocurso"), listaCurso(rs.getInt("idcurso")).get(0), listaAlumno(rs.getInt("idalumno")).get(0), rs.getString("fechamatricula"), rs.getDouble("notamedia")));
            }
            
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return alumnocursos;		
		
	}
		
	public static List<AlumnoAsignatura> alumnoasignatura(int id){ // Punto 3
        Connection con = Conexion.getConexion();
        List<AlumnoAsignatura> alumnoasignatura = new ArrayList<AlumnoAsignatura>();
        
        try {            
            String sql = "SELECT idalumnoasignatura,idalumno,asignatura,nota "
            		+ " FROM alumnoasignatura "
            		+ " WHERE idalumno = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	alumnoasignatura.add(new AlumnoAsignatura(rs.getInt("idalumnoasignatura"), listaAlumno(rs.getInt("idalumno")).get(0), rs.getString("asignatura"), rs.getInt("nota")));
            }
            
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        return alumnoasignatura;		
	}
	
	
	public static String testBD() { // EJEMPLO
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
