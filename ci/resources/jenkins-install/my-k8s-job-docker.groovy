pipeline {
    
  agent {
      label "docker"
  }
  
  stages {
    stage('Check Workspace & Building') {
      steps {
          sh """
             [[ -d myworkspace ]] && \
             { echo "Se borra workspace" && sudo rm -rf myworkspace ; } \
             || echo "No existe el workspace"
             """
          sh "mkdir myworkspace && echo Se crea el workspace"
          withCredentials([usernamePassword(credentialsId: 'ci-repo', passwordVariable: 'password', usernameVariable: 'username')]) {
           sh "git clone http://${env.username}:${env.password}@repo.tools.rfsc.cl/scm/mob/appmobile.git myworkspace"
           sh "cd myworkspace && git checkout develop"
           sh "pwd && cd myworkspace && ls -lrthF"
           sh """
              docker run --rm -v ${WORKSPACE}/myworkspace:/workdir \
              -v /var/node_modules:/workdir/node_modules \
              -v /var/gradle:/home/gradle/.gradle -w /workdir \
              mournblade/gradlenpm bash -c "npm install && npm run apk-desa"
              """
          }
      }
    }
    
    stage('Check the artefact') {
      steps {
          sh 'pwd && ls -lrthF myworkspace/android/app/build/outputs/apk/release'
      }
    }
    
    stage ('Push the artifact to Blob Storage') {
        steps {
          sh """
             docker run --rm -v ${WORKSPACE}/myworkspace:/myworkspace \
             -v /root/.docker:/root/.docker \
             registryrfsc.azurecr.io/mobile/azureblob:v0.2 appbucket desa `find . -name "*.apk"`
             """
             
        }
    }
  }
  
}
