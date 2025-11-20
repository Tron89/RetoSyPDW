<?php
    $client = new SoapClient("http://localhost:9000/Peticiones?wsdl");
    
    $id = $_POST['idalumno'] ?? -1;
    $asignaturas = $client->alumnoasignatura(['idalumno' => $id])->return ?? [];
    
    if (sizeof($asignaturas) > 0) {
        $nombre = $asignaturas[0]->alumno->nombre ?? 'NOMBRE';
        $appellidos = $asignaturas[0]->alumno->apellidos ?? 'APELLIDOS';
    } else {
        $alumno = $client->listaAlumno(['idalumno' => $id])->return;
        $nombre = 'NOMBRE';
        $appellidos = 'APELLIDOS';
    }

    $valName = 'nota_';

    foreach ($client->__getFunctions() as $type) {
        echo htmlspecialchars($type) . "<br>";
    }

    echo "<br><br>";
    
    foreach ($client->__getTypes() as $type) {
        echo htmlspecialchars($type) . "<br>";
    }

    $hayCambios = false;
    foreach ($asignaturas as $asignatura) {
        if (isset($_POST[$valName . $asignatura->idalumnoasignatura])) {
            $nuevaNota = $_POST[$valName . $asignatura->idalumnoasignatura];
            if ($nuevaNota >= 0 && $nuevaNota <= 10) {
                $asignatura->nota = $nuevaNota;
            }
            $hayCambios = true;
        }
    }

    if ($hayCambios){
        $client->actualizaNotas(['lista' => $asignaturas]);
    }
?>

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
    <form action="alumnoInfo.php" class="form" method="post">
        <table class="table table-stripped">
            <thead>
                <th>ID</th>
                <th>ASIGNATURA</th>
                <th>NOTA</th>
            </thead>
            <tbody>
                <?php
                    if (sizeof($asignaturas) == 0) {
                        echo "<tr><td colspan='3'>No hay asignaturas para este alumno.</td></tr>";
                    } else {
                        foreach ($asignaturas as $asignatura) {
                            echo "<tr>";
                                echo "<td>" . htmlspecialchars($asignatura->idalumnoasignatura) . "</td>";
                                echo "<td>" . htmlspecialchars($asignatura->asignatura) . "</td>";
                                echo "<td><input class=\"form-control\" type='number' name='" . $valName .
                                htmlspecialchars($asignatura->idalumnoasignatura) . "' value='" . htmlspecialchars($asignatura->nota) . "' min='0' max='10' step='0.01'></td>";
                            echo "</tr>";
                        }
                    }
                ?>
            </tbody>
        </table>
        <input type="hidden" name="idalumno" value="<?=htmlspecialchars($id)?>">
        <input type="submit" value="Guardar notas" class="btn btn-primary">
        <a href="inicio.php" class="btn btn-secondary">Volver</a>
    </form>
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>