<?php
    require_once 'func.php';

    $client = new SoapClient("http://localhost:9000/Peticiones?wsdl");
    $idcurso = $_POST['curso'] ?? 1;

    $cursos = $client->listaCurso()->return;
    $alumnos = $client->listaAlumnos(['idcurso' => $idcurso])->return;
?>

<!-- listaCursoResponse listaCurso(listaCurso $parameters)
alumnoasignaturaResponse alumnoasignatura(alumnoasignatura $parameters)
listaAlumnosResponse listaAlumnos(listaAlumnos $parameters)

struct listaCurso { }
struct listaCursoResponse { curso return; }
struct curso { string curso; int idCurso; }
struct alumnoasignatura { int idalumno; }
struct alumnoasignaturaResponse { alumnoAsignatura return; }
struct alumnoAsignatura { alumno alumno; string asignatura; int idalumnoasignatura; int nota; }
struct alumno { string apellidos; string fechanac; string nombre; string sexo; }
struct listaAlumnos { int idcurso; }
struct listaAlumnosResponse { alumnoCurso return; }
struct alumnoCurso { alumno alumno; curso curso; string fechamatricula; int idalumnocurso; double notamedia; } -->

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mi Proyecto</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container">
    <form action="inicio.php" method="post">
        <select name="curso" id="curso" class="form-select mt-4">
        <?php
            foreach ($cursos as $curso) {
                echo "<option value=\"" . htmlspecialchars($curso->idCurso) . "\">" . htmlspecialchars($curso->curso) . "</option>";
            }
        ?>
        </select>
        <br>
        <input type="submit" value="Cargar alumnos" id="cargarAlumnos" class="btn btn-primary">
    </form>
    <br>
    <table class="table table-stripped mt-4">
        <thead>
            <tr>
                <th>Alumno</th>
                <th>Fecha nacimiento</th>
                <th>Sexo</th>
                <th>Nota media</th>
                <th>Fecha de matricula</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <?php
                foreach ($alumnos as $alumno) {
                    echo "<form action=\"alumnoInfo.php\" method=\"post\">";
                        echo "<tr>";
                            echo "<td>" . htmlspecialchars($alumno->idalumnocurso) . "</td>";
                            echo "<td>" . htmlspecialchars($alumno->alumno->apellidos . ", " . $alumno->alumno->nombre) . "</td>";
                            echo "<td>" . htmlspecialchars(formateaFecha($alumno->alumno->fechanac)) . "</td>";
                            echo "<td>" . htmlspecialchars($alumno->alumno->sexo) . "</td>";
                            echo "<td>" . htmlspecialchars($alumno->notamedia) . "</td>";
                            echo "<td>" . htmlspecialchars(formateaFecha($alumno->fechamatricula)) . "</td>";
                            echo "<td>";
                                echo "<input type=\"hidden\" name=\"idalumno\" value=\"" . htmlspecialchars($alumno->idalumnocurso) . "\">";
                                echo "<input type=\"submit\" value=\">\" class=\"btn btn-primary\">";
                            echo "</td>";
                        echo "</tr>";
                    echo "</form>";
                }
            ?>
        </tbody>
    </table>
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>