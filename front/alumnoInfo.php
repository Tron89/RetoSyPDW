<?php
    $client = new SoapClient("http://localhost:9000/Peticiones?wsdl");
    
    $id = $_POST['idalumno'] ?? 'ID';
    $asignaturas = $client->alumnoasignatura(['idalumno' => $id])->return;
    // echo var_dump($asignaturas);
    $nombre = $asignaturas[0]->alumno->nombre ?? 'NOMBRE';
    $appellidos = $asignaturas[0]->alumno->apellidos ?? 'APELLIDOS';
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
    <h1 class="mt-5">Notas de <?=$appellidos . ", " . $nombre?></h1>
    <form action="data.php" class="form">
        <table class="table table-stripped">
            <thead>
                <th>ASIGNATURA</th>
                <th>NOTA</th>
            </thead>
            <tbody>
                <?php
                    foreach ($asignaturas as $asignatura) {
                        echo "<tr>";
                            echo "<td>" . htmlspecialchars($asignatura->asignatura) . "</td>";
                            echo "<td><input class=\"form-control\" type='number' name='nota_" . htmlspecialchars($asignatura->idalumnoasignatura) . "' value='" . htmlspecialchars($asignatura->nota) . "' min='0' max='10' step='0.01'></td>";
                        echo "</tr>";
                    }
                ?>
            </tbody>
        </table>
        <input type="button" value="Guardar notas" class="btn btn-primary">
        <input type="button" onclick="javascript:history.go(-1)" value="Volver" class="btn btn-secondary">
    </form>
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>