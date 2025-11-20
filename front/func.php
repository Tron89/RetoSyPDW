<?php
    function formateaFecha($fecha)
    {
        return date('d/m/Y',strtotime($fecha));
    }