pipeline {
  agent any
  stages {
    stage('init') {
      steps {
        sh 'mvn clean package -Dmaven.skip.test=true'
      }
    }

  }
}