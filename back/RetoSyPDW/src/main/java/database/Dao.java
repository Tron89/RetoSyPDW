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
            String sql = "SELECT idalumnocurso, cu.idcurso, cu.curso, al.idalumno, al.nombre, al.fechanac, al.sexo, al.apellidos, fechamatricula, notamedia "
            		+ " FROM alumnocurso ac "
            		+ " INNER JOIN alumno al ON al.idalumno = ac.idalumno "
            		+ " INNER JOIN curso cu ON cu.idcurso = ac.idcurso "
            		+ " WHERE ac.idcurso = ? "
            		+ " ORDER BY al.apellidos";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	alumnocursos.add(
            			new AlumnoCurso(rs.getInt("idalumnocurso"), 
            			new Curso(rs.getInt("idcurso"), rs.getString("curso")),
            			new Alumno(rs.getInt("idalumno"), rs.getString("nombre"), rs.getString("fechanac"), rs.getString("sexo"), rs.getString("apellidos")),
            			rs.getString("fechamatricula"),
            			rs.getDouble("notamedia")));
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
            String sql = "SELECT idalumnoasignatura, al.idalumno, al.nombre, al.fechanac, al.sexo, al.apellidos, asignatura, nota "
            		+ " FROM alumnoasignatura aa"
            		+ " INNER JOIN alumno al ON al.idalumno = aa.idalumno "
            		+ " WHERE aa.idalumno = ? ";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
            	alumnoasignatura.add(
            			new AlumnoAsignatura(rs.getInt("idalumnoasignatura"),
            			new Alumno(rs.getInt("idalumno"), rs.getString("nombre"), rs.getString("fechanac"), rs.getString("sexo"), rs.getString("apellidos")),
            			rs.getString("asignatura"),
            			rs.getInt("nota")));
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
	
	
	public static int actualizaNotas(AlumnoAsignatura[] lista) { // Punto 4
        Connection con = Conexion.getConexion();
        int result = 0;
        
        try {
        	String sqlWhen = "";
        	String sqlWhere = "";
               
        	for (int i = 0; i < lista.length; i++) {
				sqlWhen += " WHEN idalumnoasignatura = ? THEN ? ";
				sqlWhere += " ? ";
			}
        	
        	
            String sql = "UPDATE alumnoasignatura "
            		+ " SET "
            		+ " nota = CASE"
            		+ sqlWhen
            		+ " END "
            		+ " WHERE "
            		+ " idalumnoasignatura IN (" + sqlWhere + "); ";
            PreparedStatement ps = con.prepareStatement(sql);
            
            for (int i = 0; i < lista.length; i++) {
                ps.setInt(1+(i*3), lista[i].getIdalumnoasignatura());
                ps.setInt(2+(i*3), lista[i].getNota());
                ps.setInt(3+(i*3), lista[i].getIdalumnoasignatura());
            }
            
            result = ps.executeUpdate();
                       
            ps.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
        
        // actualizaMedias();
        
        return result;
	}
	
	public static int actualizaMedias() {
        Connection con = Conexion.getConexion();
        int result = 0;
        try {
            Statement stmt = con.createStatement();
            
            String sql = " UPDATE alumnocurso ac "
            		+ " JOIN ( "
            		+ "     SELECT idalumno, AVG(nota) AS promedio "
            		+ "    FROM alumnoasignatura "
            		+ "    GROUP BY idalumno "
            		+ " ) aa ON aa.idalumno = ac.idalumno "
            		+ " SET ac.notamedia = aa.promedio; ";
            result = stmt.executeUpdate(sql);
            
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cierraConexion();
        }
                
        return result;

	}

	
}
