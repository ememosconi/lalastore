podTemplate(
    label: 'kubernetes',
    containers: [
        containerTemplate(name: 'maven', image: 'maven:alpine', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'golang', image: 'golang:alpine', ttyEnabled: true, command: 'cat'),
        containerTemplate(name: 'docker', image: 'docker:dind', ttyEnabled: true, command: 'cat', privileged: true)
    ],
    volumes: [hostPathVolume(hostPath: '/var/run/docker.sock', mountPath: '/var/run/docker.sock')]
)

{
    node('kubernetes') {
        container('maven') {
            stage('build') {
                sh 'mvn --version'
            }
            stage('unit-test') {
                sh 'java -version'
            }
        }
        container('golang') {
            stage('deploy') {
                sh 'go version'
            }
        }
        container('docker') {
            stage('integration-test'){
                sh 'docker version'
            }
        }
    }
}
