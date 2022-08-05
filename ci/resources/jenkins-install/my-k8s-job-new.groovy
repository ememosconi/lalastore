pipeline {
    
  agent {
    kubernetes {
      yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    pod: main-pod
spec:
  nodeName: aks-nodepool1-12624212-vmss000007
  containers:
  - name: gradlenpm
    image: mournblade/gradlenpm
    command:
    - cat
    tty: true
    resources:
      limits:
        cpu: 2
        memory: 2Gi
      requests:
        cpu: 1
        memory: 1Gi
  - name: docker
    image: docker:dind
    command:
    - cat
    tty: true
    volumeMounts:
    - mountPath: /var/run/docker.sock
      name: docker-socket
  volumes:
  - name: docker-socket
    hostPath:
      path: /var/run/docker.sock
      type: Socket
"""
    }
  }
  
  stages {
    stage('Check Workspace & Building') {
      steps {
        container('gradlenpm') {
          withCredentials([usernamePassword(credentialsId: 'ci-repo', passwordVariable: 'password', usernameVariable: 'username')]) {
           sh "git clone http://${env.username}:${env.password}@repo.tools.rfsc.cl/scm/mob/appmobile.git"
           sh "cd appmobile && git checkout develop"
           sh "pwd && cd appmobile && ls -lrthF"
           sh "cd appmobile && npm install && npm run apk-desa"
          }
        }
      }
    }
    stage('Check the artefact') {
      steps {
        container('docker') {
          sh 'pwd && ls -lrthF appmobile/android/app/build/outputs/apk/release'
        }
      }
    }
  }
  
}
