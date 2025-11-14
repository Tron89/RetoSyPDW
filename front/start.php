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
    <select name="curso" id="curso" class="form-select mt-4">
        <?php //" Codigo php, ya vere :) " ?>
    </select>
    <br>
    <input type="button" value="Cargar alumnos" id="cargarAlumnos" class="btn btn-primary">
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
             <tr>
                <td>Diaz Ruiz, Hugo</td>
                <td>02/11/2010</td>
                <td>H</td>
                <td>6.94</td>
                <td>01/09/2024</td>
                <td><a href="data.php?id=1&nombre=<?php
                echo urlencode("Diaz Ruiz, Hugo");
                ?>" class="btn btn-primary">></a></td>
             </tr>
        </tbody>
    </table>
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>