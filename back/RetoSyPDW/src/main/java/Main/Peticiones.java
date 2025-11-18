package Main;

import java.util.List;

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
	
	@WebMethod
	public void actualizaNotas(@WebParam (name="lista") database.AlumnoAsignatura[] lista)
	{
		database.Dao.actualizaNotas(lista);
	}
	
}