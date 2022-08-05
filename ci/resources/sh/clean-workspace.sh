#!/usr/bin/env bash
set -e

projectName=$1

echo limpiando el proyecto $projectName
rm -rf $projectName
echo Listo la limpieza de  $projectName