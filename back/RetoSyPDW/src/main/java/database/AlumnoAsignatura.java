package database;


public class AlumnoAsignatura {
	int idalumnoasignatura;
	Alumno alumno;
	String asignatura;
	int nota;
	public int getIdalumnoasignatura() {
		return idalumnoasignatura;
	}
	public void setIdalumnoasignatura(int idalumnoasignatura) {
		this.idalumnoasignatura = idalumnoasignatura;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	@Override
	public String toString() {
		return "AlumnoAsignatura [idalumnoasignatura=" + idalumnoasignatura + ", alumno=" + alumno + ", asignatura="
				+ asignatura + ", nota=" + nota + "]";
	}
	public AlumnoAsignatura(int idalumnoasignatura, Alumno alumno, String asignatura, int nota) {
		super();
		this.idalumnoasignatura = idalumnoasignatura;
		this.alumno = alumno;
		this.asignatura = asignatura;
		this.nota = nota;
	}
	public AlumnoAsignatura() {
		super();
	}
	
	
}
