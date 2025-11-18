<?php
    $nombre = $_GET['nombre'] ?? 'NOMBRE';
    $id = $_GET['id'] ?? 'ID';
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
    <h1>Notas de <?=$nombre?></h1>
    <form action="data.php" class="form">
        <table class="table table-stripped">
            <thead>
                <th>ASIGNATURA</th>
                <th>NOTA</th>
            </thead>
            <tbody>
                <tr>
                    <td>Ciencias Naturales</td>
                    <td><input class="form-control" type="number" name="notaNaturales" id="notaNaturales"></td>
                </tr>
                <tr>
                    <td>Educacion Fisica</td>
                    <td><input class="form-control" type="number" name="notaEF" id="notaEF"></td>
                </tr>
                <tr>
                    <td>Lengua Castellana</td>
                    <td><input class="form-control" type="number" name="notaLengua" id="notaLengua"></td>
                </tr>
                <tr>
                    <td>Matematicas</td>
                    <td><input class="form-control" type="number" name="notaMates" id="notaMates"></td>
                </tr>
            </tbody>
        </table>
        <input type="button" value="Guardar notas" class="btn btn-primary">
        <input type="button" onclick="javascript:history.go(-1)" value="Volver" class="btn btn-secondary">
    </form>
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>