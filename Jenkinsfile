pipeline {
  stages {
    stage('init') {
      agent any
      steps {

      }
    }
  }
  stages {
    stage('package'){
      agent {
        docker {
          image 'maven:3.5.3-jdk-8'
          args '-v /var/jenkins_home/docker/data/maven/root/.m2:/root/.m2'
        }
      }
      steps {
        script{
          sh "mvn clean package -DskipTests"
      	}
      }
    }
  }
  stage('deploy'){
  	agent any
  	steps{
  	  script {
  	      sh "ssh -o StrictHostKeyChecking=no root@$47.106.78.139 rm -rf /data/pkg/dyp/operation/"
  		}
  	  }
  	}
  }
}