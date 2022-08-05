#!/usr/bin/env bash

set -e

projectName=$1
company=$2
automatic=$3
projectWithoutSlash=$(echo $projectName | sed "s/-//g")
replacePackageName='s/com.redbee/'
replaceFolderName='s=com/redbee='
folder="${projectName//.//}"
replaceEnd='/g'
replaceFolderEnd='=g'


cd $company
git clone http://${username}:${password}@github.com/redbeestudios/spring-java-seed.git

cd spring-java-seed

find . -type f -name "*.java" -exec sed -i $replacePackageName$projectWithoutSlash$replaceEnd {} +

if($automatic == true)then {
  git checkout aws-bot-auto-generate
  cd ..
  git clone http://${username}:${password}@github.com/ememosconi/ci
  cd ci
  find . -type f -name "create-ms-java.sh" -exec  sed -i $replaceFolderName$folder$replaceFolderEnd {} +
  }
  rm -rf .git/
fi


cd ..

mkdir -p ./spring-java-seed/src/main/java/$folder
mkdir -p ./spring-java-seed/src/test/java/$folder
mkdir ./ms-seed
mv ./spring-java-seed/src/main/java/com/redbee/msseed ./spring-java-seed/src/main/java/$folder/msseed
mv ./spring-java-seed/src/test/java/com/redbee/msseed ./spring-java-seed/src/test/java/$folder/msseed
rm -rf ./spring-java-seed/src/main/java/com/redbee
rm -rf ./spring-java-seed/src/test/java/com/redbee
mv ./spring-java-seed/* ./ms-seed
rm -rf ./spring-java-seed


