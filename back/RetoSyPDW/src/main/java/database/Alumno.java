package database;

public class Alumno {
	private int idAlumno;
	private String nombre, fechanac, sexo, apellidos;
	public int getIdAlumno() {
		return idAlumno;
	}
	public void setId(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFechanac() {
		return fechanac;
	}
	public void setFechanac(String fechanac) {
		this.fechanac = fechanac;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	@Override
	public String toString() {
		return "Alumno [id=" + idAlumno + ", nombre=" + nombre + ", fechanac=" + fechanac + ", sexo=" + sexo + ", apellidos="
				+ apellidos + "]";
	}
	public Alumno(int idAlumno, String nombre, String fechanac, String sexo, String apellidos) {
		super();
		this.idAlumno = idAlumno;
		this.nombre = nombre;
		this.fechanac = fechanac;
		this.sexo = sexo;
		this.apellidos = apellidos;
	}
	public Alumno() {
		super();
	}

	
}
