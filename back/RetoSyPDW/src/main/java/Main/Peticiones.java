package Main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import database.Conexion;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public class Peticiones {

	@WebMethod
	public List<database.Curso> listaCurso()
	{
		return database.Dao.listaCurso();
	}
	
	@WebMethod
	public List<database.AlumnoCurso> listaAlumnos(@WebParam (name="idcurso") int idcurso)
	{
		return database.Dao.listaAlumnos(idcurso);
	}
	
	@WebMethod
	public List<database.AlumnoAsignatura> alumnoasignatura(@WebParam (name="idalumno") int idalumno)
	{
		return database.Dao.alumnoasignatura(idalumno);
	}
	
}