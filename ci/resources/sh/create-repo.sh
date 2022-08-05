#!/usr/bin/env bash
set -e

projectName=$1


echo  creando el repo $projectName
echo | curl -i -H 'Authorization: token '${password}     -d "{ \"name\": \"$projectName\"}"     https://api.github.com/user/repos
echo $projectName
cd $projectName
git init
git remote add origin https://${username}:${password}@github.com/ememosconi/$projectName
git config user.email "dummy@jenkins.com"
git config user.name "jenkins"
echo creando el branch develop
git checkout -b develop
git add .
echo realizando commit
git commit -m "first commit"
echo realizando push a develop
git push origin develop
echo realizando el puhs a master
git checkout -b master
git push origin master
echo Listo para trabaja el  proyecto $projectName