package database;


public class AlumnoCurso {
	int idalumnocurso;
	Curso curso;
	Alumno alumno;
	String fechamatricula;
	double notamedia;
	public int getIdalumnocurso() {
		return idalumnocurso;
	}
	public void setIdalumnocurso(int idalumnocurso) {
		this.idalumnocurso = idalumnocurso;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public String getFechamatricula() {
		return fechamatricula;
	}
	public void setFechamatricula(String fechamatricula) {
		this.fechamatricula = fechamatricula;
	}
	public double getNotamedia() {
		return notamedia;
	}
	public void setNotamedia(double notamedia) {
		this.notamedia = notamedia;
	}
	@Override
	public String toString() {
		return "AlumnoCurso [idalumnocurso=" + idalumnocurso + ", curso=" + curso + ", alumno=" + alumno
				+ ", fechamatricula=" + fechamatricula + ", notamedia=" + notamedia + "]";
	}
	public AlumnoCurso(int idalumnocurso, Curso curso, Alumno alumno, String fechamatricula,
			double notamedia) {
		super();
		this.idalumnocurso = idalumnocurso;
		this.curso = curso;
		this.alumno = alumno;
		this.fechamatricula = fechamatricula;
		this.notamedia = notamedia;
	}
	public AlumnoCurso() {
		super();
	}
	
	

}
