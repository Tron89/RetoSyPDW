<?php
    class Alumno {
        public $idAlumno;
        public $nombre;
        public $fechaNac;
        public $sexo;
        public $apellidos;
        public $fechaMatricula;

        public function __construct($id, $nombre, $fechaNacimiento, $sexo, $apellidos, $fechaMatricula) {
            $this->id = $id;
            $this->nombre = $nombre;
            $this->fechaNacimiento = $fechaNacimiento;
            $this->sexo = $sexo;
            $this->apellidos = $apellidos;
            $this->fechaMatricula = $fechaMatricula;
        }
    }
?>