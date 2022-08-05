def call(){

    pipeline {
        agent any
        stages {
            stage('Prepare workspace'){
                steps{
                    prepare_proyect(env.company)
                }
            }
            stage('Create proyect') {
                stages {
                    stage('Create micro servicios'){
                        when{
                            expression { env.TIPO_PROYECTO.contains('microservicios') }
                        }
                        steps{
                            create_proyect(env.package,env.company,env.automatico)
                        }
                    }
                    stage('Create front'){
                        when{
                            expression { env.TIPO_PROYECTO.contains('front') }
                        }
                        steps{
                            echo "-> creando front"
                        }
                    }
                    stage ('Create bff'){
                        when{
                            expression { env.TIPO_PROYECTO.contains('bff') }
                        }
                        steps{
                            echo "-> creando bff"
                        }
                    }
                }

            }



            stage('create repo') {
                steps {
                    create_repo_proyect(env.company)
                }
                post {
                    success {
                        echo "-> Se creo el repo github"
                    }
                }
            }

            stage('clean workspace') {
                steps {
                    clean_proyect(env.company)
                }
                post {
                    success {
                        echo "-> Se creo el repo github"
                    }
                }
            }
        }
    }

}
