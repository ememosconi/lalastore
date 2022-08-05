def call(){
  pipeline {
    agent any
    stages {
      stage('Create proyect') {
        steps {
          create_proyect(env.package,env.company,env.automatico)
        }
        post {
          success {
            echo "-> El Proyecto fue creado correctamente."
          }
        }
      }

      stage('create repo') {
        steps {
          create_repo_pryect(env.company)
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
